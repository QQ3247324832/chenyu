<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/16
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  buffer="0kb" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
  <title>欢迎注册EasyMall</title>
  <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/regist.css"/>
  <script src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"></script>
  <script src="js/util.js"></script>
  <script>
    $(function () {
      /*为空判断 密码"一致性"判断 邮箱格式效验"*/
      $("input[name='username']").blur(function () {
        utilObj.isNullParam("input[name='username']",0,"span","用户名为空");
        var username = $("input[name='username']").val();

        if (username!==""){
        $("#username").load("<%=request.getContextPath()%>/AjaxUserByUsernameServlet",{"username":username})
        }
        return false;
      });
      $("input[name='password']").blur(function () {
        utilObj.isNullParam("input[name='password']",0,"span","密码为空")
      });
      $("input[name='password2']").blur(function () {
        utilObj.isNullParam("input[name='password2']",0,"span","确认密码为空");
        /*密码验证*/
        var password = $("input[name='password']").val();
        var password2 = $("input[name='password2']").val();
        if (password!==password2){
          utilObj.isNullParam("input[name='password2']",0,"span","密码和确认密码不一致")
        }
      });
      $("input[name='nickname']").blur(function () {
        utilObj.isNullParam("input[name='nickname']",0,"span","昵称为空")
      });
      $("input[name='email']").blur(function () {
        if( !utilObj.emailLike("input[name='email']")){
          $("input[name='email']").next("span").text("邮箱格式不正确")
        }else{
          $("input[name='email']").next("span").text("");
          utilObj.isNullParam("input[name='email']",0,"span","邮箱为空")
        }

      });
      $("input[name='valistr']").blur(function () {
        utilObj.isNullParam("input[name='valistr']",1,"span","验证码为空");
        if($("input[name='valistr']").val().length!==4){
          utilObj.isNullParam("input[name='valistr']",1,"span","验证码必须为4位数")
        }
      })

      //图片单击事件--单击时更换验证码
      $("#img").click(function () {
        var date =new Date();
        var time = date.getTime();
        $(this).attr("src","${pageContext.request.contextPath}ValidateServlet?time="+time)
      });
      $("#img").click();

      /*注册验证*/

    });
    function registLike() {
      var like = true;
      like = !utilObj.isNullParam("input[name='username']",0,"span","用户名为空")&&like;
      like = !utilObj.isNullParam("input[name='password']",0,"span","密码为空") &&like;
      like =  !utilObj.isNullParam("input[name='password2']",0,"span","确认密码为空") &&like;
      like = !utilObj.isNullParam("input[name='nickname']",0,"span","昵称为空") &&like;
      if( !utilObj.emailLike("input[name='email']")){
        like =$("input[name='email']").next("span").text("邮箱格式不正确")&&like;
      }else{
        $("input[name='email']").next("span").text("");
        like = !utilObj.isNullParam("input[name='email']",0,"span","邮箱为空")&&like;
      }
      like = !utilObj.isNullParam("input[name='valistr']",1,"span","验证码为空")&&like;
      like = utilObj.emailLike("input[name='email']") &&like;
      return like;
    }
  </script>
</head>
<body>
<form action="<%=request.getContextPath()%>/RegistUserServlet" method="POST" onsubmit="return registLike()">
  <h1>欢迎注册EasyMall</h1>
  <table>
    <tr>
      <td class="tds" colspan="2" style="color: #CA141D;text-align: center">${requestScope.msg}</td>
    </tr>
    <tr>
      <td class="tds">用户名：</td>
      <td>
        <input type="text" name="username" value="${param.username}"/><span id="username"></span>
      </td>
    </tr>
    <tr>
      <td class="tds">密码：</td>
      <td>
        <input type="password" name="password"/><span></span>
      </td>
    </tr>
    <tr>
      <td class="tds">确认密码：</td>
      <td>
        <input type="password" name="password2"/><span></span>
      </td>
    </tr>
    <tr>
      <td class="tds">昵称：</td>
      <td>
        <input type="text" name="nickname" value="${param.nickname}"/><span></span>
      </td>
    </tr>
    <tr>
      <td class="tds">邮箱：</td>
      <td>
        <input type="text" name="email" value="${param.email}"/><span></span>
      </td>
    </tr>
    <tr>
      <td class="tds">验证码：</td>
      <td>
        <input type="text" name="valistr"/>
        <img id="img"  width="" height="" alt="" /><span></span>
      </td>
    </tr>
    <tr>
      <td class="sub_td" colspan="2" class="tds">
        <input type="submit" value="注册用户"/>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
