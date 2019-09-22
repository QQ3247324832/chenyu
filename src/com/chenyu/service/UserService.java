package com.chenyu.service;

import com.chenyu.dao.UserDao;
import com.chenyu.domain.User;
import com.chenyu.exception.MsgException;

/**
 * @author xinYing
 */
public class UserService {
    private UserDao userDao =new UserDao();
    public void addUser(User user) {
        //用户是否存在
        Boolean findUser = userDao.findUserByUsername(user.getUsername());
        if (findUser){
            //已存在
            throw new MsgException("用户名已存在");
        }else{
            //未存在
            userDao.addUser(user);
        }
    }

    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     * @return  登录状态
     */

    public User loginUser(String username, String password) {
        User user = new UserDao().loginUser(username, password);
        if (user==null){
            throw new MsgException("用户名或密码错误");
        }
        return user;
    }
}
