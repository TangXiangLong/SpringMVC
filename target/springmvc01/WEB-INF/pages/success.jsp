<%--
  Created by IntelliJ IDEA.
  User: 79333
  Date: 2019/9/17
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>入门成功</h3>
${ requestScope.msg}
${user.uname}
${user.date}
${System.out.println("success.jsp执行了....")}
</body>
</html>
