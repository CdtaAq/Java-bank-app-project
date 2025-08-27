<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head><title>Branch Form</title></head>
<body>
<sec:authorize access="hasRole('ROLE_ADMIN')">
  <h2>Branch</h2>
  <form action="${pageContext.request.contextPath}/branches/save" method="post">
    <input type="hidden" name="branchId" value="${branch.branchId}" />
    <label>Code</label><input type="text" name="branchCode" value="${branch.branchCode}" required/><br/>
    <label>Name</label><input type="text" name="branchName" value="${branch.branchName}" required/><br/>
    <label>Address</label><input type="text" name="branchAddress" value="${branch.branchAddress}"/><br/>
    <button type="submit">Save</button>
  </form>
  <p><a href="${pageContext.request.contextPath}/branches/list">Back to Branches</a></p>
</sec:authorize>

<sec:authorize access="!hasRole('ROLE_ADMIN')">
  <p>You are not authorized to view this page.</p>
</sec:authorize>
</body>
</html>
