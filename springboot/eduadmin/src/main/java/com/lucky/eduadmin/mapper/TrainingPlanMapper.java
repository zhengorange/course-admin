package com.lucky.eduadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.TrainingPlan;
import org.apache.ibatis.annotations.Param;

public interface TrainingPlanMapper extends BaseMapper<TrainingPlan> {
    Page<TrainingPlan> selectTrain(Page<TrainingPlan> page, @Param("trainingPlan") TrainingPlan trainingPlan);
    TrainingPlan selectOneTrain(Integer classId);
}
