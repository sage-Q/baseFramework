package com.gsgy.controller;

import com.alibaba.fastjson.JSONObject;
import com.gsgy.base.interceptor.JwtIgnore;
import com.gsgy.entity.SysLog;
import com.gsgy.entity.SysUser;
import com.gsgy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/SysUserController")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/insertData/{id}/{user_key}/{user_name}")
    public Object insertData(@PathVariable String id,@PathVariable String user_key,@PathVariable String user_name){
        Map obj = new HashMap();
        obj.put("id",id);
        obj.put("user_key",user_key);
        obj.put("user_name",user_name);
        return sysUserService.insertData(obj);
    }

    @RequestMapping("/getlistPage/{str}")
    public Object getlistPage(@PathVariable String str){
        return sysUserService.getlistPage(str);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping("/getlistPageByCondition")
    public Object getlistPageByCondition(@RequestBody SysLog sysLog){
        System.out.println(JSONObject.toJSONString(sysLog));
        return sysLog.toString();
    }


    @RequestMapping("/sysLogin")
    public Object sysLogin(SysUser sysUser) throws Exception {
        //SysUser sysUser = new SysUser();
        if(!StringUtils.isEmpty(sysUser.getUsername()) &&
                !StringUtils.isEmpty(sysUser.getPassword())){
            sysUser.setPermissionsStr("sy,yhgl,qxgl,zygl,jsgl");
        }else {
            throw new Exception("缺失用户名或密码");
        }
        return sysUser;
    }

    @JwtIgnore
    @RequestMapping("/getJczlistPage")
    public Object getJczlistPage(){
        return sysUserService.getJczlistPage();
    }
}
