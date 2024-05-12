package com.gzist.linklook.data;

/**
 * 设置用户信息
 **/
public class Users {
    private int id;
    public static String nickname;
    private String loginId;
    private String loginPwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getNickname() {
        return nickname;
    }

    public static void setNickname(String nickname) {
        Users.nickname = nickname;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
}
