package com.lucky.eduadmin.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.TrainingItem;
import com.lucky.eduadmin.entity.TrainingPlan;
import com.lucky.eduadmin.mapper.TrainingItemMapper;
import com.lucky.eduadmin.mapper.TrainingPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingPlanDao {

    private final TrainingPlanMapper trainingPlanMapper;
    private final TrainingItemMapper trainingItemMapper;

    @Autowired
    public TrainingPlanDao(TrainingPlanMapper trainingPlanMapper, TrainingItemMapper trainingItemMapper) {
        this.trainingPlanMapper = trainingPlanMapper;
        this.trainingItemMapper = trainingItemMapper;
    }

    public Page<TrainingPlan> getTrainingPlan(TrainingPlan trainingPlan, long current, long size) {
        Page<TrainingPlan> page = new Page<>(current, size);
        return trainingPlanMapper.selectTrain(page, trainingPlan);
    }

    public void addTrainingPlan(TrainingPlan trainingPlan) {
        trainingPlanMapper.insert(trainingPlan);
        addItems(trainingPlan.getTrainingItems(), trainingPlan.getId());
    }

    public int deleteTrainingPlan(int id) {
        deleteItems(id);
        return trainingPlanMapper.deleteById(id);
    }

    public void updateTrainingPlan(TrainingPlan trainingPlan) {
        deleteItems(trainingPlan.getId());
        trainingPlanMapper.updateById(trainingPlan);
        addItems(trainingPlan.getTrainingItems(), trainingPlan.getId());
    }

    public void deleteItems(Integer trainingPlanId) {
        trainingItemMapper.delete(new QueryWrapper<TrainingItem>().eq("training_plan_id", trainingPlanId));
    }

    public void addItems(List<TrainingItem> trainingItems, Integer planId) {
        for (TrainingItem trainingItem : trainingItems) {
            trainingItemMapper.insert(trainingItem.setPlanId(planId));
        }
    }
}
