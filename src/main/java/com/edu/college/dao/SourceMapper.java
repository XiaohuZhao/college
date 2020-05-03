package com.edu.college.dao;

import com.edu.college.pojo.Source;
import com.edu.college.pojo.dto.MicroAttachment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(@Param("record") Source record, @Param("userId") final Integer userId);

    int insertSelective(@Param("record") MicroAttachment record, @Param("userId") final Integer userId);

    Source selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Source record);

    int updateByPrimaryKey(Source record);

    List<Source> selectByUserId(Integer userId);

    void download(Integer id);

    void delete(@Param("userId") Integer userId, @Param("id") String id);
}