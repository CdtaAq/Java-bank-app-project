<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head><title>Role Form</title></head>
<body>
<sec:authorize access="hasRole('ROLE_ADMIN')">
  <h2>Role</h2>
  <form action="${pageContext.request.contextPath}/roles/save" method="post">
    <input type="hidden" name="roleId" value="${role.roleId}" />
    <label>Role Name</label>
    <input type="text" name="roleName" value="${role.roleName}" placeholder="ROLE_ADMIN / ROLE_USER" required/>
    <button type="submit">Save</button>
  </form>
  <p><a href="${pageContext.request.contextPath}/roles/list">Back to Roles</a></p>
</sec:authorize>

<sec:authorize access="!hasRole('ROLE_ADMIN')">
  <p>You are not authorized to view this page.</p>
</sec:authorize>
</body>
</html>
