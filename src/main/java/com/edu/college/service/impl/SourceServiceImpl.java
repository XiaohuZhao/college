package com.edu.college.service.impl;

import com.edu.college.dao.SourceMapper;
import com.edu.college.pojo.Source;
import com.edu.college.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    private SourceMapper mapper;

    @Override
    public List<Source> list(final Integer userId) {
        return mapper.selectByUserId(userId);
    }

    @Override
    public void delete(final Integer userId, final String id) {
        mapper.delete(userId,id);
    }
}
