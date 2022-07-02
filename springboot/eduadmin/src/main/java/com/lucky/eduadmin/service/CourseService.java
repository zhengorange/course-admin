package com.lucky.eduadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.Class;
import com.lucky.eduadmin.entity.Course;

import java.util.List;

public interface CourseService {
    Course save(Course c);
    void delete(Integer id);
    Page<Course> selectCourse(Course c, long current, long size);

    List<Course> getAllCourse();

    List<Course> teacherCourse(Integer teacherId);
}
