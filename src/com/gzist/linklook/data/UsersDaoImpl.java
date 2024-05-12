package com.gzist.linklook.data;

import com.gzist.linklook.utils.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl extends BaseDao implements UsersDao {
    @Override
    public Users findByLoginIdAndPwd(String loginId, String loginPwd) {
        String sql = "select * from users where loginId=? and loginPwd=?";
        Connection conn = this.getConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, loginId);
            psmt.setString(2, loginPwd);
            System.out.println(psmt);
            rs = psmt.executeQuery();
            if (rs.next()) {
                Users u = new Users();
                u.setId(rs.getInt("id"));
                u.setLoginId("loginId");
                u.setLoginPwd("loginPwd");
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(rs);
            this.close(conn);
            this.close(psmt);
        }
        return null;
    }

    @Override
    public int update(Users user) {
//		this.executeSql("update users set gradeName=? where gradeId=?", grade.getGradeName(), grade.getGradeInt());
        Connection conn = this.getConnection();
        PreparedStatement psmt = null;
        String sql = "UPDATE users SET loginPwd=? WHERE id=?";
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getLoginPwd());
            psmt.setInt(2, user.getId());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
