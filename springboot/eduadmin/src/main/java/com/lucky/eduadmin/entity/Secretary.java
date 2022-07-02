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
@TableName("secretary")
public class Secretary {
    @TableId(value = "secretary_id", type= IdType.AUTO)
    private Integer id;

    @TableField("secretary_name")
    private String name;

    @TableField("secretary_password")
    private String password;
}
