package com.lucky.eduadmin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucky.eduadmin.ResponseTemplate.ErrorCode;
import com.lucky.eduadmin.ResponseTemplate.ResBody;
import com.lucky.eduadmin.entity.Class;
import com.lucky.eduadmin.entity.Course;
import com.lucky.eduadmin.service.CourseService;
import com.lucky.eduadmin.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public ResBody saveCourse(@RequestBody Course c, HttpServletRequest request){
        final Integer id = JWTUtils.getId(request.getHeader("token"));
        try{
            Course cRes = courseService.save(c.setTeacherId(id));
            return new ResBody().setData(cRes);
        }catch (RuntimeException e){
            return new ResBody().setCode(ErrorCode.WORK_ERROR).setMsg(e.getMessage());
        }catch (Exception e){
            return new ResBody().setCode(ErrorCode.WORK_ERROR).setMsg("操作失败");
        }
    }
    @GetMapping()
    public ResBody getCourse(@RequestParam(defaultValue = "") String name, long current, long size, HttpServletRequest request){
        final Integer id = JWTUtils.getId(request.getHeader("token"));
        try{
            Page<Course> cRes = courseService.selectCourse(new Course().setName(name).setTeacherId(id), current, size);
            return new ResBody().setData(cRes);
        }catch (RuntimeException e){
            return new ResBody().setCode(ErrorCode.WORK_ERROR).setMsg(e.getMessage());
        }catch (Exception e){
            return new ResBody().setCode(ErrorCode.WORK_ERROR).setMsg("查询失败");
        }
    }
    @DeleteMapping("/{id}")
    public ResBody deleteCourse(@PathVariable Integer id){
        try {
            courseService.delete(id);
            return new ResBody();
        } catch (RuntimeException e) {
            return new ResBody().setCode(ErrorCode.WORK_ERROR).setMsg(e.getMessage());
        } catch (Exception e) {
            return new ResBody().setCode(ErrorCode.WORK_ERROR).setMsg("删除失败");
        }
    }

    @GetMapping("/all")
    public ResBody getAllClass() {
        List<Course> cRes = courseService.getAllCourse();
        return new ResBody().setData(cRes);
    }
    @GetMapping("/teacherCourse")
    public ResBody getTeacherCourse(HttpServletRequest request) {
        final Integer teacherId = JWTUtils.getId(request.getHeader("token"));
        List<Course> cRes = courseService.teacherCourse(teacherId);
        return new ResBody().setData(cRes);
    }
}
