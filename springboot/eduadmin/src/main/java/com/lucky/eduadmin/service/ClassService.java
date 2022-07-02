package com.lucky.eduadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.Class;

import java.util.List;

public interface ClassService {
    Class save(Class c);
    void delete(Class c);
    Page<Class> selectClass(Class c, long current, long size);
    List<Class> getAllClass();
}
