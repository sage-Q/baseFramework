package com.gsgy.base.cache;

import com.gsgy.entity.SysUser;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局缓存
 */
public class GlobalCache {

    /**
     * 所有用户
     */
    public static Map<String, SysUser> allUsers = new HashMap<>();
    static{
        allUsers.put("123456",new SysUser());
    }

}
