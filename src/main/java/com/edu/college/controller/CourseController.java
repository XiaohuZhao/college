package com.edu.college.controller;

import com.edu.college.common.annotations.LoginRequire;
import com.edu.college.common.ret.Response;
import com.edu.college.pojo.CoursePlan;
import com.edu.college.pojo.User;
import com.edu.college.pojo.vo.CoursePlanVO;
import com.edu.college.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService service;

    @GetMapping("list")
    @LoginRequire
    public Response list() {
        return Response.success(service.list());
    }

    @PostMapping("course/{courseName}")
    @LoginRequire(requireRoles = "系主任")
    public Response add(@PathVariable final String courseName) {
        final Integer id = service.add(courseName);
        return Response.success(id);
    }

    @PutMapping("course/{id}/{courseName}")
    @LoginRequire(requireRoles = "系主任")
    public Response update(@PathVariable final Integer id, @PathVariable final String courseName) {
        service.update(id, courseName);
        return Response.success();
    }

    @DeleteMapping("course/{id}")
    @LoginRequire(requireRoles = "系主任")
    public Response delete(@PathVariable final Integer id) {
        service.delete(id);
        return Response.success(this.list());
    }

    @GetMapping("plan")
    @LoginRequire(requireRoles = "系主任")
    public Response getCoursePlan() {
        List<CoursePlanVO> plans = service.getPlan();
        return Response.success(plans);
    }

    @GetMapping("plan/self")
    @LoginRequire
    public Response list(@ApiIgnore User user) {
        return Response.success(service.list(user.getId()));
    }

    @PostMapping("plan")
    @LoginRequire(requireRoles = "系主任")
    public Response addCoursePlan(@RequestBody CoursePlan plan) {
        service.savePlan(plan);
        return Response.success();
    }

    @DeleteMapping("plan")
    @LoginRequire(requireRoles = "系主任")
    public Response deleteCoursePlan(@RequestBody CoursePlan plan) {
        service.deletePlan(plan);
        return Response.success();
    }
}
