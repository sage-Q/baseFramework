package com.gsgy.controller.sysManage;

import com.alibaba.fastjson.JSONObject;
import com.gsgy.base.interceptor.JwtIgnore;
import com.gsgy.base.response.Result;
import com.gsgy.base.response.ResultCode;
import com.gsgy.service.SysUserService;
import com.gsgy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @JwtIgnore
    @RequestMapping("/login")
    public Object login(String username,String password){
        String resourceToken = JwtUtil.createToken("123456",JwtUtil.resource_timeout);
        String refreshToken = JwtUtil.createToken("123456",JwtUtil.refresh_timeout);
        Map resultObj = new HashMap();
        resultObj.put("resourceToken",resourceToken);
        resultObj.put("refreshToken",refreshToken);
        return Result.createResult(ResultCode.SUCCESS,resultObj);
    }

    /**
     * 刷新token值
     */
    @RequestMapping("/refreshToken")
    public Object refreshToken(HttpServletRequest request){
        String authHeader = request.getHeader(JwtUtil.AUTH_HEADER_KEY);
        String token = authHeader.substring(JwtUtil.TOKEN_PREFIX.length());
        String userId = JwtUtil.verifyToken(token);
        String resourceToken = JwtUtil.createToken(userId,JwtUtil.resource_timeout);
        String refreshToken = JwtUtil.createToken(userId,JwtUtil.refresh_timeout);
        Map resultObj = new HashMap();
        resultObj.put("resourceToken",resourceToken);
        resultObj.put("refreshToken",refreshToken);
        return Result.createResult(ResultCode.SUCCESS,resultObj);
    }

}
