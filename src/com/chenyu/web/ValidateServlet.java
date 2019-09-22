package com.chenyu.web;

import com.chenyu.util.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码
 * @author xinYing
 */
@WebServlet("/ValidateServlet")
public class ValidateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.控制浏览器不使用缓存
        response.setDateHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("pragma","no-cache");
        //获取验证码
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.drawImage(response.getOutputStream());
        HttpSession session = request.getSession();
        session.setAttribute("code", verifyCode.getCode());
    }
}
