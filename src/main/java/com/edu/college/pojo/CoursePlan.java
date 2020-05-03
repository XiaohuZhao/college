package com.edu.college.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursePlan implements Serializable {
    private Integer id;

    /**
     * 周几上课
     */
    private Integer week;

    /**
     * 第几节
     */
    private Integer section;

    /**
     * 是否单双周 0否 1单周 2双周
     */
    private Integer alternate;

    /**
     * 开始周
     */
    private Integer start;

    /**
     * 结束周
     */
    private Integer end;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 授课老师id
     */
    private Integer teacherId;

    private static final long serialVersionUID = 1L;
}