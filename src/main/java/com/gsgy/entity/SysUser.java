package com.gsgy.entity;

public class SysUser {

    private String username;
    private String password;
    private String permissionsStr;

    /**
     * 获得用户名
     * @return
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermissionsStr() {
        return permissionsStr;
    }

    public void setPermissionsStr(String permissionsStr) {
        this.permissionsStr = permissionsStr;
    }
}
