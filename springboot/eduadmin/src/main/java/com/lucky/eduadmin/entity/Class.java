package com.lucky.eduadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.eduadmin.entity.requestEntity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("class")
public class Class {
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer id;

    @TableField("class_name")
    private String name;

    @TableField(exist = false)
    private List<User> users;
}
