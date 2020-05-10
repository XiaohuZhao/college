package com.edu.college.service;

import com.edu.college.pojo.dto.AnswerDTO;
import com.edu.college.pojo.dto.QuestionnaireDTO;
import com.edu.college.pojo.vo.AnswerVO;
import com.edu.college.pojo.vo.QuestionnaireVO;

import java.util.List;

public interface QuestionnaireService {
    void save(final Integer userId, QuestionnaireDTO questionnaireDTO);

    List<QuestionnaireVO> list();

    QuestionnaireVO get(Integer id);

    void answer(Integer id, List<AnswerDTO> map);

    List<AnswerVO> answers(Integer id);

    AnswerVO answers(Integer id, Integer userId);

    boolean answered(Integer userId, Integer id);

    void delete(Integer id);
}
