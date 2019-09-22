package com.chenyu.web;

import com.chenyu.domain.User;
import com.chenyu.service.UserService;
import com.chenyu.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author xinYing
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remname = request.getParameter("remname");
        String autologin = request.getParameter("autologin");
        //非空判断
        if(WebUtil.isNull(username)){
            request.setAttribute("msg","用户名为空");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        if(WebUtil.isNull(password)){
            request.setAttribute("msg","密码为空");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if ("true".equals(remname)){
            //记住用户名
            Cookie cookie = new Cookie("username",username);
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            Cookie cookieRemname = new Cookie("remname",remname);
            cookieRemname.setPath(request.getContextPath()+"/");
            cookieRemname.setMaxAge(60*60*24);
            response.addCookie(cookieRemname);


        }else{
            //取消用户名
            Cookie cookie = new Cookie("username","");
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            Cookie cookieRemname = new Cookie("remname","");
            cookieRemname.setPath(request.getContextPath()+"/");
            cookieRemname.setMaxAge(0);
            response.addCookie(cookieRemname);

        }

        //账号密码验证
        UserService userService = new UserService();
        try{
            User user = userService.loginUser(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            response.sendRedirect(request.getContextPath());
        }catch (RuntimeException ignored){
            request.setAttribute("msg",ignored.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
