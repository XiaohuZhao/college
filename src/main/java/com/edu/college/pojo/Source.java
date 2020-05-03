package com.edu.college.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * source
 *
 * @author
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source implements Serializable {
    private Integer id;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 地址
     */
    private String url;

    /**
     * 文件大小
     */
    private Long size;

    private Integer userId;

    /**
     * 文件类型
     */
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    private String uploader;

    private Integer download;

    private static final long serialVersionUID = 1L;
}