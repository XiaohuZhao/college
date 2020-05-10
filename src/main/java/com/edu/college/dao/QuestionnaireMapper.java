package com.edu.college.dao;

import com.edu.college.pojo.Questionnaire;
import com.edu.college.pojo.dto.AnswerDTO;
import com.edu.college.pojo.dto.Question;
import com.edu.college.pojo.vo.AnswerVO;
import com.edu.college.pojo.vo.QuestionnaireVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Questionnaire record);

    int insertSelective(Questionnaire record);

    Questionnaire selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Questionnaire record);

    int updateByPrimaryKey(Questionnaire record);

    void saveQuestion(Question question);

    void saveChoices(@Param("id") Integer id, @Param("choices") List<String> choices);

    List<QuestionnaireVO> list();

    QuestionnaireVO get(Integer id);

    void answer(@Param("id") Integer id, @Param("answers") List<AnswerDTO> answers);

    List<AnswerVO> answers(Integer id);

    AnswerVO viewAnswers(@Param("id") Integer id, @Param("userId") Integer userId);

    boolean answered(@Param("userId") Integer userId, @Param("id") Integer id);

}