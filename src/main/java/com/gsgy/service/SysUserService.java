package com.gsgy.service;

import com.gsgy.dao.gahlxt.SysUserMapper;
import com.gsgy.dao.ggzzsbpt.GgzzsbptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private GgzzsbptMapper ggzzsbptMapper;

    public List<Map> getlistPage(String str){
        return sysUserMapper.getlistPage(str);
    }

    public int insertData(Map obj){
        return sysUserMapper.insertData(obj);
    }

    public List<Map> getJczlistPage(){
        return ggzzsbptMapper.getlistPage();
    }
}
