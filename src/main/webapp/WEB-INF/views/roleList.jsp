<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<div class="container">
  <h2>Roles</h2>
  <table class="table table-striped table-bordered">
    <thead><tr><th>ID</th><th>Name</th><th>Actions</th></tr></thead>
    <tbody>
      <c:forEach var="r" items="${roles}">
        <tr>
          <td>${r.roleId}</td>
          <td>${r.roleName}</td>
          <td>
            <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/roles/edit/${r.roleId}">Edit</a>
            <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/roles/delete/${r.roleId}" onclick="return confirm('Delete?')">Delete</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
