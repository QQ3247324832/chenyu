package com.chenyu.dao;

import com.chenyu.domain.User;
import com.chenyu.exception.MsgException;
import com.chenyu.util.JDBCUtils;
import com.chenyu.util.MD5Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xinYing
 */
public class UserDao {
    /**
     * 用户是否存在
     *
     * @param username 用户名称
     * @return 是否存在该用户
     */
    public Boolean findUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select * from user where username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MsgException();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }

    /**
     * 添加用户
     *
     * @param user 用户信息
     */
    public void addUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("insert into user values(null,?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getEmail());
            int i = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, null);
        }
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录状态
     */
    public User loginUser(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement("select * from user where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, MD5Utils.md5(password));
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setEmail(rs.getString("email"));
                return user;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            JDBCUtils.close(conn, ps, rs);
        }


    }
}
