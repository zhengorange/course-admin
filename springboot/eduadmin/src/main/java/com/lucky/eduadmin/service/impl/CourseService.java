package com.lucky.eduadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.dao.CourseDao;
import com.lucky.eduadmin.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional
public class CourseService implements com.lucky.eduadmin.service.CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course save(Course course) {
        if(ObjectUtils.isEmpty(course.getId())){
            Course cDb = courseDao.getCourseByTime(course);
            if(cDb == null){
                return courseDao.addCourse(course);
            }else {
                throw new RuntimeException("与" + cDb.getName() + "课程时间冲突");
            }
        }else {
            return courseDao.updateCourse(course);
        }
    }

    @Override
    public void delete(Integer id) {
        if(courseDao.deleteCourse(id) != 1){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public Page<Course> selectCourse(Course c, long current, long size) {
        return courseDao.getCourse(c, current, size);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public List<Course> teacherCourse(Integer teacherId) {
        return courseDao.teacherCourse(teacherId);
    }
}
