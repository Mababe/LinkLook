package com.gzist.linklook.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * 工具类，把连接数据库共同的方法抽象到父类中
 **/
public class BaseDao {
    private static String url = null;
    private static String name = null;
    private static String pwd = null;
    private static String driver = null;

    // 要用静态代码块，这样加载类的时候就会完成初始化
    static {
        Properties pro = new Properties();
        String fileName = "database.properties";
        InputStream in = BaseDao.class.getClassLoader().getResourceAsStream(fileName);
        try {
            pro.load(in);
            url = pro.getProperty("url");
            name = pro.getProperty("users");
            pwd = pro.getProperty("pwd");
            driver = pro.getProperty("driver");
//			System.out.println(url+" "+name+" "+pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     */
    public Connection getConnection() {
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, name, pwd);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源
     *
     * @param auto,jdk7后的接口
     */
    public void close(AutoCloseable auto) {
        if (auto != null) {
            try {
                auto.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 动态执行sql
     *
     * @param sql:预编译的sql
     * @param objs:可变参数
     */
    public void executeSql(String sql, Object... objs) {
        // 获得连接
        Connection conn = this.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // 设置参数
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
            // 执行sql
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(ps);
            this.close(conn);
        }
    }

    /**
     * 执行一条select语名，并且将返回结果封装到List中，每条记录对一个Map
     *
     * @param sql:sql查询语句，可带参数(?)
     * @param objs:可变参数
     * @return
     */
    public List<Map<String, Object>> executeQuery(String sql, Object... objs) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Connection conn = this.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
            //执行查询
            rs = ps.executeQuery();
            //获得元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //将每条记录转成一个Map,key是列名，value是字段值
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(rs);
            this.close(ps);
            this.close(conn);
        }
        return list;
    }

    /**
     * 公共的增、删、改方法
     *
     * @param sql:sql查询语句，可带参数(?)
     * @param params
     * @return
     */
    public int executeUpdate(String sql, Object... params) {
        Connection con = this.getConnection();
        PreparedStatement psmt = null;
        try {
            psmt = con.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    psmt.setObject(i + 1, params[i]);
                }
            }
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(psmt);
            this.close(con);
        }
        return 0;
    }

    /**
     * 动态执行sql
     *
     * @param sql:sql查询语句，可带参数(?)
     * @param objs:可变参数
     */
    public boolean executeSqlLogin(String sql, Object... objs) {
        // 获得连接
        Connection conn = this.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            // 设置参数
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
            // 执行sql
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(ps);
            this.close(conn);
        }
        return false;
    }
}
