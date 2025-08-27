<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>User Form</title></head>
<body>
<h2>User</h2>

<form action="${pageContext.request.contextPath}/users/save" method="post">
  <input type="hidden" name="userId" value="${user.userId}" />

  <label>Username</label>
  <input type="text" name="username" value="${user.username}" <sec:authorize access="!hasRole('ROLE_ADMIN')">readonly</sec:authorize> required/><br/>

  <label>First Name</label>
  <input type="text" name="firstName" value="${user.firstName}"/><br/>

  <label>Last Name</label>
  <input type="text" name="lastName" value="${user.lastName}"/><br/>

  <label>Email</label>
  <input type="email" name="email" value="${user.email}"/><br/>

  <label>Password</label>
  <input type="password" name="password" placeholder="(leave blank to keep unchanged)"/><br/>

  <!-- Admin can assign roles -->
  <sec:authorize access="hasRole('ROLE_ADMIN')">
    <label>Roles</label>
    <select name="roles" multiple size="5">
      <c:forEach var="r" items="${allRoles}">
        <option value="${r.roleId}"
          <c:if test="${user.roles != null && user.roles.contains(r)}">selected</c:if>>
          ${r.roleName}
        </option>
      </c:forEach>
    </select>
    <p><small>(Hold Ctrl/Cmd to select multiple)</small></p>
  </sec:authorize>

  <button type="submit">Save</button>
</form>

<hr/>

<sec:authorize access="hasRole('ROLE_ADMIN')">
  <p><a href="${pageContext.request.contextPath}/users/list">Back to User List</a></p>
</sec:authorize>
<p><a href="${pageContext.request.contextPath}/">Home</a></p>
</body>
</html>
