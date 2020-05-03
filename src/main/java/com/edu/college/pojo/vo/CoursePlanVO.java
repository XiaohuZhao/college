package com.edu.college.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursePlanVO {
    /**
     * 周几上课
     */
    private Integer week;

    /**
     * 第几节
     */
    private Integer section;

    /**
     * 开始周
     */
    private Integer start;

    /**
     * 结束周
     */
    private Integer end;

    /**
     * 课程
     */
    private String courseName;

    /**
     * 授课老师
     */
    private String teacherName;
}
