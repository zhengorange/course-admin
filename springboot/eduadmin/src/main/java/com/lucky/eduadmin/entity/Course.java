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
@TableName("course")
public class Course {
    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer id;

    @TableField("course_name")
    private String name;

    @TableField("course_time")
    private Integer time;

    @TableField("max_student")
    private Integer maxStudent;

    @TableField("credits")
    private Integer credits;

    @TableField("teacher_id")
    private Integer teacherId;

    @TableField(exist = false)
    private String teacherName;
}
