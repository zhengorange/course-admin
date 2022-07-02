package com.lucky.eduadmin.service.impl;

import com.lucky.eduadmin.dao.UserDao;
import com.lucky.eduadmin.entity.Secretary;
import com.lucky.eduadmin.entity.Student;
import com.lucky.eduadmin.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
public class UserService implements com.lucky.eduadmin.service.UserService {

    private final UserDao userDao;
    private final SelectCourseService selectCourseService;

    @Autowired
    UserService(UserDao userDao, SelectCourseService selectCourseService){
        this.userDao = userDao;
        this.selectCourseService = selectCourseService;
    }

    @Override
    public Student login(Student student) {
        Student studentDb = userDao.getStudentById(student);
        if(ObjectUtils.isEmpty(studentDb)){
            throw  new RuntimeException("该学生不存在");
        }
        if(!studentDb.getPassword().equals(student.getPassword())){
            throw  new RuntimeException("密码错误");
        }
        return studentDb;
    }

    @Override
    public Teacher login(Teacher teacher) {
        Teacher teacherDb = userDao.getTeacherById(teacher);
        if(ObjectUtils.isEmpty(teacherDb)){
            throw  new RuntimeException("该老师不存在");
        }
        if(!teacherDb.getPassword().equals(teacher.getPassword())){
            throw  new RuntimeException("密码错误");
        }
        return teacherDb;
    }

    @Override
    public Secretary login(Secretary secretary) {
        Secretary secretaryDb = userDao.getSecretaryById(secretary);
        if(ObjectUtils.isEmpty(secretaryDb)){
            throw new RuntimeException("该教秘不存在");
        }
        if(!secretaryDb.getPassword().equals(secretary.getPassword())){
            throw  new RuntimeException("密码错误");
        }
        return secretaryDb;
    }

    @Override
    public Student register(Student student) {
        Student stuRes = userDao.addStudent(student);
        selectCourseService.addRequiredCourse(stuRes.getClassId(), stuRes.getId());
        return stuRes;
    }

    @Override
    public Teacher register(Teacher teacher) {
        return userDao.addTeacher(teacher);
    }

    @Override
    public Secretary register(Secretary secretary) {
        return userDao.addSecretary(secretary);
    }
}
