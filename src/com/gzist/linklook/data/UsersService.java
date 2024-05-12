package com.gzist.linklook.data;

public interface UsersService {
    /**
     *
     * @param loginId
     * @param loginPwd
     * @return
     */
    Users login(String loginId,String loginPwd);
    /**
     * 根据主健更改密码
     * @param user
     * @return
     */
    boolean updatePwd(Users user);
}
