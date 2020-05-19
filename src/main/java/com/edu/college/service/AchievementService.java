package com.edu.college.service;

import com.edu.college.common.page.PageQuery;
import com.edu.college.common.page.PageResult;
import com.edu.college.pojo.Achievement;
import com.edu.college.pojo.User;
import com.edu.college.pojo.dto.AchievementDTO;
import com.edu.college.pojo.dto.ReviewDTO;
import com.edu.college.pojo.vo.AchievementVO;

import java.util.List;
import java.util.Map;

public interface AchievementService {
    void add(AchievementDTO dto, final User user);

    void remove(Integer userId, Integer id);

    PageResult<Achievement> list(Integer userId, final String search, final PageQuery pageQuery);

    AchievementVO get(Integer id);

    void review(Integer id, Integer userId, ReviewDTO review);

    List<Map<String, Integer>> types(Integer userId);

    List<Map<String, Integer>> dates(Integer userId);
}
