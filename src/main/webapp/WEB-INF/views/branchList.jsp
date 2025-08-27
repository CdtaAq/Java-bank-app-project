<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Branches</title></head>
<body>
<h2>Branches</h2>
<table border="1">
  <tr><th>ID</th><th>Code</th><th>Name</th><th>Address</th><th>Actions</th></tr>
  <c:forEach var="b" items="${branches}">
    <tr>
      <td>${b.branchId}</td>
      <td>${b.branchCode}</td>
      <td>${b.branchName}</td>
      <td>${b.branchAddress}</td>
      <td>
        <!-- Admin-only actions: rely on controller/security for enforcement; optionally hide with sec:authorize -->
        <a href="${pageContext.request.contextPath}/branches/edit/${b.branchId}">Edit</a> |
        <a href="${pageContext.request.contextPath}/branches/delete/${b.branchId}" onclick="return confirm('Delete branch?')">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
<p><a href="${pageContext.request.contextPath}/">Home</a></p>
</body>
</html>
