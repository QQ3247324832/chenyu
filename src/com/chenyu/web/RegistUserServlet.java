package com.chenyu.web;

import com.chenyu.dao.UserDao;
import com.chenyu.domain.User;
import com.chenyu.exception.MsgException;
import com.chenyu.service.UserService;
import com.chenyu.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注册用户
 * @author xinYing
 */
@WebServlet("/RegistUserServlet")
public class RegistUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String valistr = request.getParameter("valistr");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");

        if (valistr==null){
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        /**
         * 验证码判断
         */
        if ( !valistr.equalsIgnoreCase(code)){
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        /*
        * 非空数据验证
        * */
        if (WebUtil.isNull(username)){
            request.setAttribute("msg","账号为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (WebUtil.isNull(password)){
            request.setAttribute("msg","密码为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (WebUtil.isNull(password2)){
            request.setAttribute("msg","确认密码为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (WebUtil.isNull(nickname)){
            request.setAttribute("msg","昵称为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (WebUtil.isNull(email)){
            request.setAttribute("msg","邮箱为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if (WebUtil.isNull(valistr)){
            request.setAttribute("msg","验证码为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        //数据效验
            //密码一致性
        if (password==password2){
            request.setAttribute("msg", "确认密码与密码不一致");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
            //邮箱验证
        if (!WebUtil.emailLike(email)){
            request.setAttribute("msg", "邮箱格式错误");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        UserDao userDao = new UserDao();
        if(userDao.findUserByUsername(username)){
            request.setAttribute("msg", "用户名已存在");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        //逻辑处理
        User user = new User(username, password, nickname, email);
        UserService userService =new UserService();
        try{
            userService.addUser(user);
        }catch (MsgException e){
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        //注册完成后，跳转到网站首页
        response.getWriter().write(
                "<h1 align='center'>" +
                        "<font color='red'>恭喜，注册成功，3秒之后跳转到首页..</font>" +
                        "</h1>");
        response.setHeader("refresh","3;url=request.getContextPath()");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
