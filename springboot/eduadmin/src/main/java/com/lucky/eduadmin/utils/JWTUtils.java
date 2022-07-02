package com.lucky.eduadmin.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String Sign = "~(K|{nxkasndjku1!d3jnKSDJ*ASY@n?2e";

    /**
     * 生成token head.payload.sign
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 14);
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(Sign));
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token){
        return  JWT.require(Algorithm.HMAC256(Sign)).build().verify(token);
    }

    /**
     * 获取token中的id
     */
    public static Integer getId(String token){
        DecodedJWT decodedJWT = verify(token);
        return Integer.parseInt(decodedJWT.getClaim("id").asString());
    }
}
