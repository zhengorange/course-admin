package com.lucky.eduadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.eduadmin.entity.TrainingItem;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface TrainingItemMapper extends BaseMapper<TrainingItem> {
    @ResultMap("mybatis-plus_TrainingItem")
    @Select("select p.*, c.*, t.teacher_name, count(s.select_course_id) as selected_count from training_items p " +
            "left join course c on p.course_id = c.course_id " +
            "left join teacher t on c.teacher_id = t.teacher_id " +
            "left join select_course s on c.course_id = s.course_id " +
            "where p.training_plan_id = #{0} group by plan_items_id")
    TrainingItem selectItemById(int id);
}
