package com.edu.college.service.impl;

import com.edu.college.dao.QuestionnaireMapper;
import com.edu.college.pojo.Questionnaire;
import com.edu.college.pojo.dto.AnswerDTO;
import com.edu.college.pojo.dto.Question;
import com.edu.college.pojo.dto.QuestionnaireDTO;
import com.edu.college.pojo.vo.AnswerVO;
import com.edu.college.pojo.vo.QuestionnaireVO;
import com.edu.college.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    private QuestionnaireMapper mapper;
    @Qualifier("applicationTaskExecutor")
    @Autowired
    private Executor executor;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(final Integer userId, final QuestionnaireDTO dto) {
        final Questionnaire questionnaire = new Questionnaire();
        questionnaire.setTitle(dto.getTitle());
        questionnaire.setEndTime(dto.getEndTime());
        questionnaire.setDescription(dto.getDescription());
        questionnaire.setUserId(userId);
        mapper.insertSelective(questionnaire);
        final List<Question> questions = dto.getQuestions();
        final AtomicBoolean b = new AtomicBoolean(true);
        final CountDownLatch latch = new CountDownLatch(questions.size());
        for (final Question question : questions) {
            executor.execute(() -> {
                try {
                    question.setQuesId(questionnaire.getId());
                    mapper.saveQuestion(question);
                    if (question.getType() < 3 && question.getChoices().size() > 0) {
                        mapper.saveChoices(question.getId(), question.getChoices());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    b.set(false);
                } finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.isTrue(b.get(), "保存时出现异常");
    }

    @Override
    public List<QuestionnaireVO> list() {
        return mapper.list();
    }

    @Override
    public QuestionnaireVO get(final Integer id) {
        return mapper.get(id);
    }

    @Override
    public void answer(final Integer id, final List<AnswerDTO> answers) {
        mapper.answer(id, answers);
    }

    @Override
    public List<AnswerVO> answers(final Integer id) {
        return mapper.answers(id);
    }

    @Override
    public AnswerVO answers(final Integer id, final Integer userId) {
        return mapper.viewAnswers(id, userId);
    }

    @Override
    public boolean answered(final Integer userId, final Integer id) {
        return mapper.answered(userId, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(final Integer id) {
        mapper.deleteByPrimaryKey(id);
    }
}
