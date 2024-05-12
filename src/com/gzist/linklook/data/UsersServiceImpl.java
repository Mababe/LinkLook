package com.gzist.linklook.data;

public class UsersServiceImpl implements UsersService {
    private UsersDao uDao = new UsersDaoImpl();

    @Override
    public Users login(String loginId, String loginPwd) {
        return uDao.findByLoginIdAndPwd(loginId, loginPwd);
    }

    @Override
    public boolean updatePwd(Users user) {
        if (uDao.update(user) > 0) {
            return true;
        }
        return false;
    }
}
