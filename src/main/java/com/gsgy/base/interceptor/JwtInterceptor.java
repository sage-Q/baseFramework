package com.gsgy.base.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gsgy.base.cache.GlobalCache;
import com.gsgy.base.exception.CustomException;
import com.gsgy.base.response.ResultCode;
import com.gsgy.utils.JwtUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(JwtUtil.AUTH_HEADER_KEY);
        if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
            throw new CustomException(ResultCode.PERMISSION_TOKEN_INVALID);
        }

        // 获取token
        final String token = authHeader.substring(JwtUtil.TOKEN_PREFIX.length());
        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        try{
            String userId = JwtUtil.verifyToken(token);
            if(GlobalCache.allUsers.containsKey(userId)){
                return true;
            }else{
                throw new CustomException(ResultCode.USER_NOT_EXIST);
            }
        }catch(TokenExpiredException e) {
            throw new CustomException(ResultCode.PERMISSION_TOKEN_EXPIRED);
        }
    }
}
