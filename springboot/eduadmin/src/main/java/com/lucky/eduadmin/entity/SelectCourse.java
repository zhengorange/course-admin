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
@TableName(value = "select_course", autoResultMap = true)
public class SelectCourse {
    @TableId(value = "select_course_id", type = IdType.AUTO)
    private Integer id;
    @TableField("student_id")
    private Integer studentId;
    @TableField("course_id")
    private Integer courseId;
    @TableField("grade")
    private Integer grade;

    @TableField(exist = false)
    private String courseName;

    @TableField(exist = false)
    private Integer courseTime;

    @TableField(exist = false)
    private String studentName;
}
