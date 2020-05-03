package com.edu.college.service;

import com.edu.college.pojo.Course;
import com.edu.college.pojo.CoursePlan;
import com.edu.college.pojo.vo.CoursePlanVO;

import java.util.List;

public interface CourseService {
    List<Course> list();

    Integer add(String courseNames);

    void update(final Integer id, String courseName);

    void delete(Integer id);

    void savePlan(CoursePlan plan);

    List<CoursePlanVO> getPlan();

    void deletePlan(CoursePlan plan);

    Object list(Integer teacherId);
}
