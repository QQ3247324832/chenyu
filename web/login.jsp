<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/16
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" buffer="0kb" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
  <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css"/>
  <script src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
  <script>
    $(function () {
      $("input[name='username']").val(decodeURI("${cookie.username.value}"))

    })
  </script>
  <title>EasyMall欢迎您登陆</title>
</head>
<body>
<h1>欢迎登陆EasyMall</h1>
<form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
  <table>
    <tr>
      <td class="tdx" colspan="2" style="color: #CA141D; text-align: center">${msg}</td>
    </tr>
    <tr>
      <td class="tdx">用户名：</td>
      <td><input type="text" name="username" /></td>
    </tr>
    <tr>
      <td class="tdx">密&nbsp;&nbsp; 码：</td>
      <td><input type="password" name="password"/></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="checkbox" name="remname" value="true"   ${ cookie.remname.value!="true"?"":"checked='checked'"}/>记住用户名
        <input type="checkbox" name="autologin" value="true"/>30天内自动登陆
      </td>
    </tr>
    <tr>
      <td colspan="2" style="text-align:center">
        <input type="submit" value="登 陆"/>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
