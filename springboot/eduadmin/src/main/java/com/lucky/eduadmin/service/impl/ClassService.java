package com.lucky.eduadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.dao.ClassDao;
import com.lucky.eduadmin.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional
public class ClassService implements com.lucky.eduadmin.service.ClassService {
    private final ClassDao classDao;

    @Autowired
    ClassService(ClassDao classDao){
        this.classDao = classDao;
    }

    @Override
    public Class save(Class c) {
        if(ObjectUtils.isEmpty(c.getId())){
            Class cDb = classDao.getClassByName(c);
            if(cDb == null){
                return classDao.addClass(c);
            }else{
                throw new RuntimeException("班级名已存在");
            }

        }else {
            return classDao.updateClass(c);
        }
    }

    @Override
    public void delete(Class c) {
        if(classDao.deleteClass(c) != 1){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public Page<Class> selectClass(Class c, long current, long size) {
        return classDao.getClass(c, current, size);
    }

    @Override
    public List<Class> getAllClass() {
        return classDao.getAllClass();
    }
}
