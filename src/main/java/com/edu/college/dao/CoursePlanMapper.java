package com.edu.college.dao;

import com.edu.college.pojo.CoursePlan;
import com.edu.college.pojo.vo.CoursePlanVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CoursePlan record);

    int insertSelective(CoursePlan record);

    CoursePlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoursePlan record);

    int updateByPrimaryKey(CoursePlan record);

    void saveOrUpdate(CoursePlan plan);

    List<CoursePlanVO> getAll(final Integer teacherId);

    void delete(CoursePlan plan);
}