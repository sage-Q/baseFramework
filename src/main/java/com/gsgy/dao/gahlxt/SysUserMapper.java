package com.gsgy.dao.gahlxt;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysUserMapper {

    List<Map> getlistPage(String str);

    int insertData(Map obj);
}
