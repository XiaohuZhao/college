package com.edu.college.service.impl;

import com.edu.college.dao.CourseMapper;
import com.edu.college.dao.CoursePlanMapper;
import com.edu.college.pojo.Course;
import com.edu.college.pojo.CoursePlan;
import com.edu.college.pojo.vo.CoursePlanVO;
import com.edu.college.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper mapper;
    @Autowired
    private CoursePlanMapper planMapper;

    @Override
    public List<Course> list() {
        return mapper.selectAll();
    }

    @Override
    public Integer add(final String courseNames) {
        try {
            final Course course = new Course(courseNames);
            mapper.insertSelective(course);
            return course.getId();
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("已添加该课程");
        }
    }

    @Override
    public void update(final Integer id, final String courseName) {
        mapper.updateByPrimaryKey(new Course(id, courseName));
    }

    @Override
    public void delete(final Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void savePlan(final CoursePlan plan) {
        planMapper.saveOrUpdate(plan);
    }

    @Override
    public List<CoursePlanVO> getPlan() {

        return planMapper.getAll(null);
    }

    @Override
    public void deletePlan(final CoursePlan plan) {
        planMapper.delete(plan);
    }

    @Override
    public Object list(final Integer teacherId) {
        return planMapper.getAll(teacherId);
    }
}
