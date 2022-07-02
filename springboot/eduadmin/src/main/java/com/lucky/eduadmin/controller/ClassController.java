package com.lucky.eduadmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.ResponseTemplate.ResBody;
import com.lucky.eduadmin.entity.Class;
import com.lucky.eduadmin.service.impl.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("class")
public class ClassController {
    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping()
    public ResBody saveClass(@RequestBody Class c) {
        Class cRes = classService.save(c);
        return new ResBody().setData(cRes);
    }

    @GetMapping()
    public ResBody getClassByName(String name, long current, long size) {
        Page<Class> cRes = classService.selectClass(new Class().setName(name), current, size);
        return new ResBody().setData(cRes);
    }

    @GetMapping("/all")
    public ResBody getAllClass() {
        List<Class> cRes = classService.getAllClass();
        return new ResBody().setData(cRes);
    }

    @DeleteMapping("/{id}")
    public ResBody deleteClass(@PathVariable Integer id) {
        classService.delete(new Class().setId(id));
        return new ResBody();

    }
}
