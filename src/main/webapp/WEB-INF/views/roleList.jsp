<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Roles</title></head>
<body>
<h2>Roles</h2>

<sec:authorize access="hasRole('ROLE_ADMIN')">
  <p><a href="${pageContext.request.contextPath}/roles/form">Create Role</a></p>
</sec:authorize>

<table border="1">
  <tr><th>ID</th><th>Name</th><th>Actions</th></tr>
  <c:forEach var="r" items="${roles}">
    <tr>
      <td>${r.roleId}</td>
      <td>${r.roleName}</td>
      <td>
        <a href="${pageContext.request.contextPath}/roles/edit/${r.roleId}">Edit</a> |
        <a href="${pageContext.request.contextPath}/roles/delete/${r.roleId}" onclick="return confirm('Delete role?')">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>

<p><a href="${pageContext.request.contextPath}/">Home</a></p>
</body>
</html>
