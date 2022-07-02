package com.lucky.eduadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.eduadmin.entity.Student;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper extends BaseMapper<Student> {
    @ResultMap("mybatis-plus_Student")
    @Select("SELECT s.*, c.class_name FROM class c LEFT JOIN student s ON c.class_id = s.class_id WHERE s.student_id = #{id}")
    Student getOneById(int i);
}
