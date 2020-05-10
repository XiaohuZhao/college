package com.edu.college.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private Integer id;
    private Integer quesId;
    private String title;
    private int type;
    private List<String> choices;
}
