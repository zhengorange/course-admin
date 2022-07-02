package com.lucky.eduadmin.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lucky.eduadmin.entity.Secretary;
import com.lucky.eduadmin.entity.Student;
import com.lucky.eduadmin.entity.Teacher;
import com.lucky.eduadmin.mapper.SecretaryMapper;
import com.lucky.eduadmin.mapper.StudentMapper;
import com.lucky.eduadmin.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {

    private final StudentMapper studentMapper;
    private final SecretaryMapper secretaryMapper;
    private final TeacherMapper teacherMapper;

    @Autowired
    public UserDao(StudentMapper studentMapper, SecretaryMapper secretaryMapper, TeacherMapper teacherMapper) {
        this.studentMapper = studentMapper;
        this.secretaryMapper = secretaryMapper;
        this.teacherMapper = teacherMapper;
    }

    public Student getStudentById(Student student){
        return studentMapper.getOneById(student.getId());
    }
    public Student addStudent(Student student){
        return studentMapper.insert(student) == 1 ? student : null;
    }

    public Teacher getTeacherById(Teacher teacher){
        Map<String, Object> para = new HashMap<>();
        return teacherMapper.selectOne(new QueryWrapper<Teacher>().eq("teacher_id", teacher.getId()));
    }

    public Teacher addTeacher(Teacher teacher){
        return teacherMapper.insert(teacher) == 1 ? teacher : null;
    }

    public Secretary getSecretaryById(Secretary secretary){
        return secretaryMapper.selectOne(new QueryWrapper<Secretary>().eq("secretary_id", secretary.getId()));
    }
    public Secretary addSecretary(Secretary secretary){
        return secretaryMapper.insert(secretary) == 1 ? secretary : null;
    }
}
