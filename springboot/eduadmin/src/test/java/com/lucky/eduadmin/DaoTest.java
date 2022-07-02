package com.lucky.eduadmin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.dao.ClassDao;
import com.lucky.eduadmin.dao.TrainingPlanDao;
import com.lucky.eduadmin.entity.Class;
import com.lucky.eduadmin.entity.Student;
import com.lucky.eduadmin.entity.TrainingPlan;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DaoTest {

    @Autowired
    private ClassDao classDao;
    @Autowired
    private TrainingPlanDao trainingPlanDao;
    @Test
    public void func(){
        trainingPlanDao.addTrainingPlan(new TrainingPlan().setName("撒旦").setElectives(1).setClassId(4));
    }
}
