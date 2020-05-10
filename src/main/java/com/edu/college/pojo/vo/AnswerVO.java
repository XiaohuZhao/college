package com.edu.college.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AnswerVO {
    private Integer id;
    private String title;
    private String name;
    private Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    private List<AnswerList> answers;
}
