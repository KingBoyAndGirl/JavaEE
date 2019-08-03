<%--
  Created by IntelliJ IDEA.
  User: 王团结
  Date: 2019/8/2
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><%=request.getSession().getAttribute("username")%>，欢迎您</h1>
</body>
</html>
