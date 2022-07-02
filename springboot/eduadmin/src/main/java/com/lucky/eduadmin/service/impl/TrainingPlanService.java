package com.lucky.eduadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.dao.TrainingPlanDao;
import com.lucky.eduadmin.entity.Course;
import com.lucky.eduadmin.entity.TrainingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
public class TrainingPlanService implements com.lucky.eduadmin.service.TrainingPlanService {
    private final TrainingPlanDao trainingPlanDao;

    @Autowired
    public TrainingPlanService(TrainingPlanDao trainingPlanDao) {
        this.trainingPlanDao = trainingPlanDao;
    }

    @Override
    public Page<TrainingPlan> selectTrainingPlan(TrainingPlan trainingPlan, long current, long size) {
        return trainingPlanDao.getTrainingPlan(trainingPlan, current, size);
    }

    @Override
    public void deleteTrainingPlan(int id) {
        if(trainingPlanDao.deleteTrainingPlan(id) != 1)
            throw new RuntimeException("删除失败");
    }

    @Override
    public void saveTrainingPlan(TrainingPlan trainingPlan) {
        if(ObjectUtils.isEmpty(trainingPlan.getId())){
            trainingPlanDao.addTrainingPlan(trainingPlan);
        }else {
            trainingPlanDao.updateTrainingPlan(trainingPlan);
        }
    }


}
