package com.lucky.eduadmin.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.Class;
import com.lucky.eduadmin.entity.Course;
import com.lucky.eduadmin.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDao {
    private final CourseMapper courseMapper;

    @Autowired
    public CourseDao(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public Course addCourse(Course course){
        return courseMapper.insert(course) == 1 ? course : null;
    }
    public Course updateCourse(Course course){
        return courseMapper.updateById(course) == 1 ? course: null;
    }
    public int deleteCourse(Integer id){
        return courseMapper.deleteById(id);
    }
    public Page<Course> getCourse(Course c, long current, long size){
        Page<Course> page = new Page<>(current, size);
        return courseMapper.selectPage(page, new QueryWrapper<Course>().like("course_name", c.getName()).eq("teacher_id", c.getTeacherId()));
    }
    public Course getCourseByTime(Course c){
        return courseMapper.selectOne(new QueryWrapper<Course>().eq("course_time", c.getTime()).eq("teacher_id", c.getTeacherId()));
    }

    public List<Course> getAllCourse(){
        return courseMapper.selectList(null);
    }

    public List<Course> teacherCourse(Integer teacherId){
        return courseMapper.selectList(new QueryWrapper<Course>().eq("teacher_id", teacherId));
    }
}
