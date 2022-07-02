package com.lucky.eduadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "training_plan", autoResultMap = true)
public class TrainingPlan {
    @TableId(value = "plan_id", type = IdType.AUTO)
    private Integer id;
    @TableField("plan_name")
    private String name;
    @TableField("class_id")
    private Integer classId;
    @TableField(exist = false)
    private String className;
    @TableField("electives")
    private Integer electives;
    @TableField(exist = false)
    private List<TrainingItem> trainingItems;
}
