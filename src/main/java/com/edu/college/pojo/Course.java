package com.edu.college.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * course
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Course implements Serializable {
    private Integer id;

    /**
     * 课程名
     */
    @NonNull
    private String name;

    private static final long serialVersionUID = 1L;
}