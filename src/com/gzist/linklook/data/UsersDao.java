package com.gzist.linklook.data;

public interface UsersDao {
    public Users findByLoginIdAndPwd(String loginId,String loginPwd);

    public int update(Users user);
}
