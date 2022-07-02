package com.lucky.eduadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.dao.CourseDao;
import com.lucky.eduadmin.dao.SelectCourseDao;
import com.lucky.eduadmin.entity.Course;
import com.lucky.eduadmin.entity.SelectCourse;
import com.lucky.eduadmin.entity.TrainingItem;
import com.lucky.eduadmin.entity.TrainingPlan;
import com.lucky.eduadmin.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SelectCourseService implements com.lucky.eduadmin.service.SelectCourseService {

    private final SelectCourseDao selectCourseDao;
    private final CourseMapper courseMapper;
    @Autowired
    public SelectCourseService(SelectCourseDao selectCourseDao, CourseDao courseDao, CourseMapper courseMapper) {
        this.selectCourseDao = selectCourseDao;
        this.courseMapper = courseMapper;
    }

    @Override
    public SelectCourse addSelectCourse(SelectCourse selectCourse) {
        long sle = selectCourseDao.selectedSum(selectCourse.getCourseId());
        Course course = courseMapper.selectById(selectCourse.getCourseId());
        if(sle >= course.getMaxStudent()){
            throw new RuntimeException("课程人数已经选满");
        }
        selectCourseDao.addSelectCourse(selectCourse);
        return selectCourse;
    }

    @Override
    public void deleteSelectCourse(SelectCourse selectCourse) {
        selectCourseDao.deleteSelectCourse(selectCourse);
    }

    @Override
    public List<SelectCourse> getAllSelectCourse(Integer studentId) {
        return selectCourseDao.allSelectCourse(studentId);
    }

    @Override
    public TrainingPlan ableSelectCourse(Integer studentId) {
        return selectCourseDao.getAbleSelectCourse(studentId);
    }

    @Override
    public void addRequiredCourse(Integer classId, Integer studentId) {
        TrainingPlan trainingPlan = selectCourseDao.getTrainPlan(classId);
        for(TrainingItem item: trainingPlan.getTrainingItems()){
            if(item.getRequired() == 1){
                addSelectCourse(new SelectCourse().setCourseId(item.getCourseId()).setStudentId(studentId));
            }
        }
    }

    @Override
    public Page<SelectCourse> getGradeByCourseId(Integer classId, long current, long size) {
        return selectCourseDao.getGradeByCourseId(classId, current, size);
    }

    @Override
    public void updateGrade(Integer id, Integer grade) {
        selectCourseDao.addGrade(id, grade);
    }
}
