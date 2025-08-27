<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head><title>Home</title></head>
<body>
<h2>Banking App</h2>

<p>Logged in as: <sec:authentication property="name"/></p>
<p>Roles: <sec:authentication property="principal.authorities"/></p>
<p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>

<ul>
  <li><a href="${pageContext.request.contextPath}/branches/list">Branches</a></li>

  <sec:authorize access="hasRole('ROLE_ADMIN')">
    <li><a href="${pageContext.request.contextPath}/branches/form">Create Branch</a></li>
    <li><a href="${pageContext.request.contextPath}/roles/list">Roles</a></li>
    <li><a href="${pageContext.request.contextPath}/roles/form">Create Role</a></li>
    <li><a href="${pageContext.request.contextPath}/users/list">All Users</a></li>
    <li><a href="${pageContext.request.contextPath}/users/new">Create User</a></li>
  </sec:authorize>

  <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
    <li><a href="${pageContext.request.contextPath}/users/form">My Profile</a></li>
  </sec:authorize>
</ul>
</body>
</html>
