package com.edu.college.pojo.vo;

import com.edu.college.pojo.dto.Question;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionnaireVO {
    private Integer id;
    private String title;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    private String name;
    private Integer userId;
    private Boolean expired;
    private Boolean del;
    private List<Question> questions;
}
