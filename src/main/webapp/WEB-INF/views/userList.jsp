<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Users</title></head>
<body>
<h2>All Users</h2>

<sec:authorize access="hasRole('ROLE_ADMIN')">
  <p><a href="${pageContext.request.contextPath}/users/new">Create User</a></p>
</sec:authorize>

<table border="1">
  <tr>
    <th>ID</th><th>Username</th><th>Name</th><th>Email</th><th>Roles</th><th>Actions</th>
  </tr>
  <c:forEach var="u" items="${users}">
    <tr>
      <td>${u.userId}</td>
      <td>${u.username}</td>
      <td>${u.firstName} ${u.lastName}</td>
      <td>${u.email}</td>
      <td>
        <c:forEach var="r" items="${u.roles}">
          <span>${r.roleName}</span><br/>
        </c:forEach>
      </td>
      <td>
        <a href="${pageContext.request.contextPath}/users/edit/${u.userId}">Edit</a>
        |
        <a href="${pageContext.request.contextPath}/users/delete/${u.userId}"
           onclick="return confirm('Delete this user?')">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>

<p><a href="${pageContext.request.contextPath}/">Home</a></p>
</body>
</html>
