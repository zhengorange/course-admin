package com.lucky.eduadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "training_items", autoResultMap = true)
public class TrainingItem {
    @TableId(value = "plan_items_id", type = IdType.AUTO)
    private Integer id;
    @TableField("training_plan_id")
    private Integer planId;
    @TableField("course_id")
    private Integer courseId;
    @TableField("required")
    private Integer required;

    @TableField(exist = false)
    private String courseName;
    @TableField(exist = false)
    private Integer maxStudent;
    @TableField(exist = false)
    private String teacherName;
    @TableField(exist = false)
    private Integer credits;
    @TableField(exist = false)
    private Integer courseTime;
    @TableField(exist = false)
    private Integer selectedCount;
}
