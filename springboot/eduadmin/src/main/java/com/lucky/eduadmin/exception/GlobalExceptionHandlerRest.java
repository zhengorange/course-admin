package com.lucky.eduadmin.exception;

import com.lucky.eduadmin.ResponseTemplate.ErrorCode;
import com.lucky.eduadmin.ResponseTemplate.ResBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlerRest {
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResBody controllerHandler(RuntimeException e){
        log.error(e.toString());
        return new ResBody().setCode(ErrorCode.WORK_ERROR).setMsg(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResBody exceptionHandler(Exception e){
        log.error("控制层出现未处理的异常");
        log.error(e.toString());
        return new ResBody().setCode(ErrorCode.SERVER_ERROR).setMsg("请求处理失败");
    }
}
