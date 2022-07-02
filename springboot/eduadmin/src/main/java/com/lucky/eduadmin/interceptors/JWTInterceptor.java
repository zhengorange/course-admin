package com.lucky.eduadmin.interceptors;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.eduadmin.ResponseTemplate.ErrorCode;
import com.lucky.eduadmin.ResponseTemplate.ResBody;
import com.lucky.eduadmin.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        ResBody resBody;
        try {
            JWTUtils.verify(token);
            return true;
        }catch (TokenExpiredException e){
            resBody = new ResBody().setCode(ErrorCode.TOKEN_EXPIRED)
                    .setMsg("token已过期");
        }catch (Exception e){
             resBody = new ResBody().setCode(ErrorCode.TOKEN_ERROR)
                     .setMsg("token错误");
        }
        String json = new ObjectMapper().writeValueAsString(resBody);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(json);
        return false;
    }
}
