package com.edu.college.dao;

import com.edu.college.pojo.Achievement;
import com.edu.college.pojo.dto.ReviewDTO;
import com.edu.college.pojo.vo.AchievementVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AchievementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Achievement record);

    int insertSelective(Achievement record);

    Achievement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Achievement record);

    int updateByPrimaryKeyWithBLOBs(Achievement record);

    int updateByPrimaryKey(Achievement record);

    AchievementVO get(@Param("id") Integer id);

    List<Achievement> selectByUserId(@Param("userId") Integer userId, final @Param("search") String search);

    void review(@Param("id") Integer id, @Param("userId") Integer userId, @Param("review") ReviewDTO review);

    List<Map<String, Integer>> types(Integer userId);

    List<Map<String, Integer>> dates(Integer userId);
}