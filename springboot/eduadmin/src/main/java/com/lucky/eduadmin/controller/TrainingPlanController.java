package com.lucky.eduadmin.controller;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.ResponseTemplate.ErrorCode;
import com.lucky.eduadmin.ResponseTemplate.ResBody;
import com.lucky.eduadmin.entity.Course;
import com.lucky.eduadmin.entity.TrainingPlan;
import com.lucky.eduadmin.service.TrainingPlanService;
import com.lucky.eduadmin.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("plan")
public class TrainingPlanController {
    private final TrainingPlanService trainingPlanService;

    @Autowired
    public TrainingPlanController(TrainingPlanService trainingPlanService) {
        this.trainingPlanService = trainingPlanService;
    }

    @GetMapping()
    public ResBody getTrainingPlan(@RequestParam(defaultValue = "") String name, Integer classId, long current, long size) {

        Page<TrainingPlan> cRes = trainingPlanService.selectTrainingPlan(new TrainingPlan().setName(name).setClassId(classId), current, size);
        return new ResBody().setData(cRes);
    }

    @DeleteMapping("/{id}")
    public ResBody delTrainingPlan(@PathVariable Integer id) {
        trainingPlanService.deleteTrainingPlan(id);
        return new ResBody();
    }
    @PostMapping("/save")
    public ResBody saveTrainingPlan(@RequestBody TrainingPlan trainingPlan){
        trainingPlanService.saveTrainingPlan(trainingPlan);
        return new ResBody();
    }
}
