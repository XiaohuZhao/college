package com.edu.college.controller;

import com.edu.college.common.annotations.LoginRequire;
import com.edu.college.common.ret.Response;
import com.edu.college.pojo.User;
import com.edu.college.pojo.dto.AnswerDTO;
import com.edu.college.pojo.dto.QuestionnaireDTO;
import com.edu.college.pojo.vo.AnswerVO;
import com.edu.college.pojo.vo.QuestionnaireVO;
import com.edu.college.service.QuestionnaireService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService service;

    @PostMapping
    @LoginRequire
    @ApiOperation("添加一个调查问卷")
    public Response newQuestionnaire(@ApiIgnore User user, @RequestBody QuestionnaireDTO questionnaireDTO) {
        Assert.notEmpty(questionnaireDTO.getQuestions(), "问题不可为空");
        service.save(user.getId(), questionnaireDTO);
        return Response.success(questionnaireDTO);
    }

    @GetMapping
    @LoginRequire
    @ApiOperation("查询调查问卷")
    public Response list(@ApiIgnore User user) {
        final List<QuestionnaireVO> list = service.list();
        for (final QuestionnaireVO vo : list) {
            vo.setDel(vo.getUserId().equals(user.getId()));
        }
        return Response.success(list);
    }

    @GetMapping("{id}")
    @LoginRequire
    @ApiOperation("查询调查问卷")
    public Response get(@ApiIgnore User user, @PathVariable final Integer id) {
        final QuestionnaireVO vo = service.get(id);
        final Response success = Response.success(vo);
        final boolean b = service.answered(user.getId(), id);
        if (b) {
            success.setCode(201);
            success.setMsg("你已经回答过这个问题,继续回答会覆盖掉之前的回答");
        }
        return success;
    }

    @DeleteMapping("{id}")
    @LoginRequire
    @ApiOperation("删除调查问卷")
    public Response delete(@PathVariable final Integer id) {
        service.delete(id);
        return Response.success();
    }

    @PostMapping("answer")
    @LoginRequire
    public Response answer(@ApiIgnore User user, @RequestBody List<AnswerDTO> answers) {
        service.answer(user.getId(), answers);
        return Response.success();
    }

    @GetMapping("answers/{id}")
    @LoginRequire
    public Response answers(@PathVariable final Integer id) {
        List<AnswerVO> answers = service.answers(id);
        return Response.success(answers);
    }

    @GetMapping("answers/{id}/{userId}")
    @LoginRequire
    public Response answers(@PathVariable final Integer id, @PathVariable final Integer userId) {
        AnswerVO answers = service.answers(id, userId);
        return Response.success(answers);
    }

    @GetMapping("answers/{id}/my")
    @LoginRequire
    public Response myAnswers(@ApiIgnore User user, @PathVariable final Integer id) {
        return answers(id, user.getId());
    }
}
