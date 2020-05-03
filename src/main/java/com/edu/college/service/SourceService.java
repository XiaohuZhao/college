package com.edu.college.service;

import com.edu.college.pojo.Source;

import java.util.List;

public interface SourceService {
    List<Source> list(Integer userId);

    void delete(Integer userId, String id);
}
