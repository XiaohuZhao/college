package com.edu.college.controller;

import com.edu.college.common.annotations.LoginRequire;
import com.edu.college.common.ret.Response;
import com.edu.college.dao.SourceMapper;
import com.edu.college.pojo.Source;
import com.edu.college.pojo.User;
import com.edu.college.pojo.dto.MicroAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private Environment environment;
    @Autowired
    private SourceMapper sourceMapper;

    @PostMapping(value = "/upload")
    @LoginRequire
    public Response add(@ApiIgnore User user, @RequestParam(value = "file") MultipartFile multipartFile, Boolean save) throws IllegalStateException, IOException {//这里一定要写required=false 不然前端不传文件一定报错。到不了后台。
        final MicroAttachment attachment = upload(multipartFile);
        if (Objects.nonNull(save) && save) {
            sourceMapper.insertSelective(attachment, user.getId());
        }
        return Response.success(attachment);
    }

    public MicroAttachment upload(MultipartFile multipartFile) throws IOException {
        Assert.notNull(multipartFile.getContentType(), "没有上传文件");
        //获取环境中配置的自定义的系统外的路径--> [ /data/attachment/]
        String uploadDIYPath = environment.getProperty("upload.path.location");
        String pattern = "yyyy/MM/dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        //以日期当文件访问路径 及 系统路径 --- > 也是未来作为访问路径的一部分。
        String datePath = formatter.format(LocalDate.now());

        //定义上传目录//这里特地加一个file 字符串，为了让URL拦截器能识别，不拦截file下面得静态资源。
        File upload = new File(uploadDIYPath + "/file/" + datePath);

        //如果上传目录不存在，级联创建这些层级的目录。这里是只是创建了上传目录，只是目录。
        if (!upload.exists()) upload.mkdirs(); //把上传目录创建出来。

        String uploadAbsolutePath = upload.getAbsolutePath();

        //生成新的文件名。
        String fileName = multipartFile.getOriginalFilename();

        assert fileName != null;
        int index = fileName.lastIndexOf(".");

        String fileType = index != -1 ? fileName.substring(index) : "." + multipartFile.getContentType();

        final String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + fileType;

        //这里一定要拿绝对路径来创建serverFile.
        File serverFile = new File(uploadAbsolutePath, newFileName);

        //core : 绝对路径创建的serverFile，springMVC 文件转移覆盖。
        multipartFile.transferTo(serverFile);
        //上传完文件之后要返回数据出去的。比如返回可以在本作用域中获取的文件大小，文件名，文件路径（网络+文件路径=浏览器上可访问的路径），
        final String s = "file/" + datePath + "/" + newFileName;
        //文件上传成功后，要把文件保存到数据库中,并查看数据库是否保存了记录。
        return MicroAttachment.builder()
                .fileName(fileName)
                .filePath(s)
                .fileSize(multipartFile.getSize()) // 除以1024 则可以得到 KB
                .fileType(multipartFile.getContentType()).build();
    }

    @GetMapping("download/{id}")
    public void download(@PathVariable final Integer id, HttpServletResponse response) throws IOException {
        new Thread(() -> sourceMapper.download(id)).start();
        final Source source = sourceMapper.selectByPrimaryKey(id);
        final String url = source.getUrl();
        final String uploadDIYPath = environment.getProperty("upload.path.location");
        final String fileName = uploadDIYPath + url;
        response.setContentType("content-type:octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + source.getFilename());
        final File file = new File(fileName);
        final FileInputStream inputStream = new FileInputStream(file);
        final ServletOutputStream outputStream = response.getOutputStream();
        int temp;
        // 开始拷贝
        while ((temp = inputStream.read()) != -1) {
            // 边读边写
            outputStream.write(temp);
        }
        inputStream.close();
        outputStream.close();
    }
}
