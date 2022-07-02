package com.lucky.eduadmin.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.*;
import com.lucky.eduadmin.mapper.SelectCourseMapper;
import com.lucky.eduadmin.mapper.StudentMapper;
import com.lucky.eduadmin.mapper.TrainingPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectCourseDao {
    private final SelectCourseMapper selectCourseMapper;
    private final StudentMapper studentMapper;
    private final TrainingPlanMapper trainingPlanMapper;

    @Autowired
    public SelectCourseDao(SelectCourseMapper selectCourseMapper, StudentMapper studentMapper, TrainingPlanMapper trainingPlanMapper) {
        this.selectCourseMapper = selectCourseMapper;
        this.studentMapper = studentMapper;
        this.trainingPlanMapper = trainingPlanMapper;
    }
    public void addSelectCourse(SelectCourse selectCourse){
        selectCourseMapper.insert(selectCourse);
    }
    public void deleteSelectCourse(SelectCourse selectCourse){
        selectCourseMapper.deleteById(selectCourse);
    }
    public List<SelectCourse> allSelectCourse(Integer studentId){
        return selectCourseMapper.selectByStudentId(studentId);
    }

    public Long selectedSum(int courseId){
       return selectCourseMapper.selectCount(new QueryWrapper<SelectCourse>().eq("course_id", courseId));
    }

    public TrainingPlan getAbleSelectCourse(Integer studentId){
        Student student = studentMapper.getOneById(studentId);
        return trainingPlanMapper.selectOneTrain(student.getClassId());
    }

    public TrainingPlan getTrainPlan(Integer classId){
        return trainingPlanMapper.selectOneTrain(classId);
    }
    public Page<SelectCourse> getGradeByCourseId(Integer courseId, long current, long size){
        Page<SelectCourse> page = new Page<>(current, size);
        return selectCourseMapper.getGradeByCourseId(page, courseId);
    }

    public void addGrade(Integer id, Integer grade){
        selectCourseMapper.updateGrade(id, grade);
    }
}
