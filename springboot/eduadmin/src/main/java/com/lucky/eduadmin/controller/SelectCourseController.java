package com.lucky.eduadmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.ResponseTemplate.ResBody;
import com.lucky.eduadmin.entity.SelectCourse;
import com.lucky.eduadmin.entity.TrainingPlan;
import com.lucky.eduadmin.service.impl.SelectCourseService;
import com.lucky.eduadmin.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("select")
public class SelectCourseController {
    private final SelectCourseService selectCourseService;

    public SelectCourseController(SelectCourseService selectCourseService) {
        this.selectCourseService = selectCourseService;
    }
    @PostMapping
    public ResBody addSelectCourse(@RequestBody SelectCourse selectCourse, HttpServletRequest request){
        final Integer id = JWTUtils.getId(request.getHeader("token"));
        SelectCourse cRes = selectCourseService.addSelectCourse(selectCourse.setStudentId(id));
        return new ResBody().setData(cRes);
    }
    @DeleteMapping("/{id}")
    public ResBody deleteSelectCourse(@PathVariable Integer id){
        selectCourseService.deleteSelectCourse(new SelectCourse().setId(id));
        return new ResBody();
    }

    @GetMapping("all")
    public ResBody getAllSelectCourse(HttpServletRequest request){
        final Integer id = JWTUtils.getId(request.getHeader("token"));
        List<SelectCourse> selectCourses = selectCourseService.getAllSelectCourse(id);
        return new ResBody().setData(selectCourses);
    }

    @GetMapping("ableSelect")
    public ResBody getAbleSelectCourse(HttpServletRequest request){
        final Integer id = JWTUtils.getId(request.getHeader("token"));
        return new ResBody().setData(selectCourseService.ableSelectCourse(id));
    }

    @GetMapping("grade")
    public ResBody getGradeByCourseId(Integer courseId, long current, long size){
        return new ResBody().setData(selectCourseService.getGradeByCourseId(courseId, current, size));
    }

    @PutMapping("grade/{id}/{grade}")
    public ResBody addGrade(@PathVariable Integer id, @PathVariable Integer grade){
        selectCourseService.updateGrade(id, grade);
        return new ResBody();
    }
}
