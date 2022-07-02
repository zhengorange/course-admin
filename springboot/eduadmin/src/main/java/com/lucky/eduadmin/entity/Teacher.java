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
@TableName("teacher")
public class Teacher {
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer id;

    @TableField("teacher_name")
    private String name;

    @TableField("teacher_gender")
    private Integer gender;

    @TableField("teacher_password")
    private String password;
}
