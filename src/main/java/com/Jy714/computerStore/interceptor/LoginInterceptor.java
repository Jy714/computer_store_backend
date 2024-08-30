package com.Jy714.computerStore.interceptor;


import com.Jy714.computerStore.utils.Jwt;
import com.Jy714.computerStore.utils.Result;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private Jwt jwt;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        if(!StringUtils.hasLength(token)){

            Result responseResult = Result.error(4000, "NOT_LOGIN");

            String json = JSONObject.toJSONString(responseResult);
            response.getWriter().write(json);
            return false;
        }

        try {
            jwt.parseJwt(token);
        }catch (Exception e){
            Result responseResult = Result.error(4000, "NOT_LOGIN");

            String json = JSONObject.toJSONString(responseResult);
            response.getWriter().write(json);
            return false;
        }
        return true;
    }
}
