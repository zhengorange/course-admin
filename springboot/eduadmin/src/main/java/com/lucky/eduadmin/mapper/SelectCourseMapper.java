package com.lucky.eduadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.Course;
import com.lucky.eduadmin.entity.SelectCourse;
import com.lucky.eduadmin.entity.TrainingItem;
import com.lucky.eduadmin.entity.TrainingPlan;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SelectCourseMapper extends BaseMapper<SelectCourse> {
    @ResultMap("mybatis-plus_SelectCourse")
    @Select("SELECT s.*, c.* FROM select_course s LEFT JOIN course c ON s.course_id = c.course_id WHERE student_id = #{0}")
    List<SelectCourse> selectByStudentId(int id);

    Page<SelectCourse> getGradeByCourseId(Page<SelectCourse> page, Integer id);

    @Update("UPDATE select_course SET grade = #{grade} WHERE select_course_id = #{id}")
    void updateGrade(int id, int grade);
}
