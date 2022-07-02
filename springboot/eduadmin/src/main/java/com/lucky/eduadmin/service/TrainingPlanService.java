package com.lucky.eduadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.TrainingPlan;

public interface TrainingPlanService {
    Page<TrainingPlan> selectTrainingPlan(TrainingPlan trainingPlan, long current, long size);

    void deleteTrainingPlan(int id);

    void saveTrainingPlan(TrainingPlan trainingPlan);
}
