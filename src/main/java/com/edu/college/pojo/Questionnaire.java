package com.edu.college.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * questionnaire
 * @author 
 */
@Data
public class Questionnaire implements Serializable {
    private Integer id;

    /**
     * 问卷标题
     */
    private String title;

    /**
     * 创建时间
     */
    private final Date createTime = new Date();

    /**
     * 截止时间
     */
    private Date endTime;

    private Integer userId;

    /**
     * 描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}