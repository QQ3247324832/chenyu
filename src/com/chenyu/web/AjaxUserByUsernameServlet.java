package com.chenyu.web;

import com.chenyu.dao.UserDao;
import com.chenyu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xinYing
 */
@WebServlet("/AjaxUserByUsernameServlet")
public class AjaxUserByUsernameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserDao userDao = new UserDao();
        Boolean userByUsername = userDao.findUserByUsername(username);
        response.getWriter().write((userByUsername?"用户名已存在":"用户名可以使用"));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
