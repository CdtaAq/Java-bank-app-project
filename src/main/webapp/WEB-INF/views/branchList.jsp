<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<div class="container">
  <h2>Branches</h2>
  <table class="table table-hover">
    <thead><tr><th>ID</th><th>Code</th><th>Name</th><th>Address</th><th>Actions</th></tr></thead>
    <tbody>
      <c:forEach var="b" items="${branches}">
        <tr>
          <td>${b.branchId}</td>
          <td>${b.branchCode}</td>
          <td>${b.branchName}</td>
          <td>${b.branchAddress}</td>
          <td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
              <a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/branches/edit/${b.branchId}">Edit</a>
              <a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/branches/delete/${b.branchId}" onclick="return confirm('Delete?')">Delete</a>
            </sec:authorize>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

