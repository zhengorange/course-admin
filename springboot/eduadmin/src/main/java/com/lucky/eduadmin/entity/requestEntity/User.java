package com.lucky.eduadmin.entity.requestEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    private Integer id;
    private String name;
    private Integer gender;
    private Integer classId;
    private String password;
    private Integer type;
}
