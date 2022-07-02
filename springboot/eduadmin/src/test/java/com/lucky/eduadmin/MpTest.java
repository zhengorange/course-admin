package com.lucky.eduadmin;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.entity.Student;
import com.lucky.eduadmin.entity.TrainingPlan;
import com.lucky.eduadmin.mapper.StudentMapper;
import com.lucky.eduadmin.mapper.TrainingPlanMapper;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MpTest {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TrainingPlanMapper trainingPlanMapper;
    @Test
    public void testSelect() {
        System.out.println(("----- method test ------"));
        var obj = trainingPlanMapper.selectOneTrain(5);
        System.out.println(obj);
    }
}
