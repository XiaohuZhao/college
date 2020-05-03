package com.edu.college.controller;

import com.edu.college.common.annotations.LoginRequire;
import com.edu.college.common.ret.Response;
import com.edu.college.pojo.Source;
import com.edu.college.pojo.User;
import com.edu.college.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("source")
public class SourceController {
    @Autowired
    private SourceService service;

    @GetMapping("list")
    @LoginRequire
    public Response list(@ApiIgnore User user, boolean all) {
        final List<Source> list = service.list(all ? null : user.getId());
        return Response.success(list);
    }

    @DeleteMapping("{id}")
    @LoginRequire
    public Response delete(@ApiIgnore User user, @PathVariable final String id) {
        service.delete(user.getId(), id);
        return Response.success();
    }
}
