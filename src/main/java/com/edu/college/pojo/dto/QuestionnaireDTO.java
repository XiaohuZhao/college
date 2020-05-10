package com.edu.college.pojo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionnaireDTO {
    private String title;
    private String description;
    private Date endTime;
    private List<Question> questions;

}
