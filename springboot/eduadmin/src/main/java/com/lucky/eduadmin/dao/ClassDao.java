package com.lucky.eduadmin.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.Class;
import com.lucky.eduadmin.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassDao {
    private final ClassMapper classMapper;

    @Autowired
    public ClassDao(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    public Class addClass(Class c){
        return classMapper.insert(c) == 1 ? c : null;
    }
    public Class updateClass(Class c){
        return classMapper.updateById(c) == 1 ? c: null;
    }
    public int deleteClass(Class c){
        return classMapper.deleteById(c);
    }
    public Page<Class> getClass(Class c, long current, long size){
        Page<Class> page = new Page<>(current, size);
        return classMapper.selectPage(page, new QueryWrapper<Class>().like("class_name", c.getName()));
    }
    public Class getClassByName(Class c){
        return classMapper.selectOne(new QueryWrapper<Class>().eq("class_name", c.getName()));
    }
    public List<Class> getAllClass(){
        return classMapper.selectList(null);
    }
}
