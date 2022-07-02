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
@TableName(value = "student", autoResultMap = true)
public class Student {
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer id;

    @TableField("student_name")
    private String name;

    @TableField("student_gender")
    private Integer gender;

    @TableField("class_id")
    private int classId;

    @TableField(exist = false)
    private String className;

    @TableField("student_password")
    private String password;
}
