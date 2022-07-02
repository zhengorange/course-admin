package com.lucky.eduadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.SelectCourse;
import com.lucky.eduadmin.entity.TrainingPlan;

import java.util.List;

public interface SelectCourseService {
    SelectCourse addSelectCourse(SelectCourse selectCourse);
    void deleteSelectCourse(SelectCourse selectCourse);
    List<SelectCourse> getAllSelectCourse(Integer studentId);

    TrainingPlan ableSelectCourse(Integer studentId);

    void addRequiredCourse(Integer classId, Integer studentId);

    Page<SelectCourse> getGradeByCourseId(Integer classId, long current, long size);

    void updateGrade(Integer id, Integer grade);
}
