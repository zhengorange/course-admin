package com.lucky.eduadmin.controller;

import com.lucky.eduadmin.ResponseTemplate.ResBody;
import com.lucky.eduadmin.entity.Secretary;
import com.lucky.eduadmin.entity.Student;
import com.lucky.eduadmin.entity.Teacher;
import com.lucky.eduadmin.entity.requestEntity.User;
import com.lucky.eduadmin.service.UserService;
import com.lucky.eduadmin.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResBody register(@RequestBody User user) {
        Student studentRes = null;
        Teacher teacherRes = null;
        Secretary secretaryRes = null;
        switch (user.getType()) {
            case 1 ->
                    studentRes = userService.register(new Student().setGender(user.getGender()).setName(user.getName()).setPassword(user.getPassword()).setClassId(user.getClassId()));
            case 2 ->
                    teacherRes = userService.register(new Teacher().setGender(user.getGender()).setName(user.getName()).setPassword(user.getPassword()));
            case 3 ->
                    secretaryRes = userService.register(new Secretary().setName(user.getName()).setPassword(user.getPassword()));
        }
        return new ResBody().setMsg("注册成功").setData(switch (user.getType()) {
            case 1 -> studentRes;
            case 2 -> teacherRes;
            case 3 -> secretaryRes;
            default -> null;
        });
    }

    @PostMapping("login")
    public ResBody login(@RequestBody User user) {
        ResBody resBody = new ResBody();
        Student studentRes = null;
        Teacher teacherRes = null;
        Secretary secretaryRes = null;
        switch (user.getType()) {
            case 1 -> studentRes = userService.login(new Student().setId(user.getId()).setPassword(user.getPassword()));
            case 2 -> teacherRes = userService.login(new Teacher().setId(user.getId()).setPassword(user.getPassword()));
            case 3 ->
                    secretaryRes = userService.login(new Secretary().setId(user.getId()).setPassword(user.getPassword()));
            default -> throw new RuntimeException("请选择用户类型");
        }
        Map<String, String> paras = new HashMap<>();
        paras.put("id", user.getId().toString());
        String token = JWTUtils.getToken(paras);
        Map<String, Object> res = new HashMap<>();
        res.put("user", switch (user.getType()) {
            case 1 -> studentRes;
            case 2 -> teacherRes;
            case 3 -> secretaryRes;
            default -> null;
        });
        res.put("token", token);
        resBody.setData(res);
        return resBody.setMsg("登录成功");
    }
}
