package com.lucky.eduadmin.service;

import com.lucky.eduadmin.entity.Secretary;
import com.lucky.eduadmin.entity.Student;
import com.lucky.eduadmin.entity.Teacher;

public interface UserService {
    Student login(Student student);
    Teacher login(Teacher teacher);
    Secretary login(Secretary secretary);
    Student register(Student student);
    Teacher register(Teacher teacher);
    Secretary register(Secretary secretary);
}
