<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Banking Application - Home</title>
</head>
<body>
    <h1>Welcome to Banking Application</h1>
    <hr>

    <h3>Navigation</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/roles/list">Manage Roles</a></li>
        <li><a href="${pageContext.request.contextPath}/branches/list">Manage Branches</a></li>
    </ul>
</body>
</html>
