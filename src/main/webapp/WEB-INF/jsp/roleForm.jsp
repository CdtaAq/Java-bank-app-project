<!-- SAVE: banking-project/src/main/webapp/WEB-INF/jsp/roleForm.jsp -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>

<h2>Role Management</h2>

<div style="display:flex; gap:30px;">
  <div style="flex:1;">
    <h3>Create Role</h3>
    <form:form modelAttribute="role" method="post" action="${pageContext.request.contextPath}/roles">
      <div>
        <label>Name</label><br/>
        <form:input path="name" />
        <form:errors path="name" cssClass="error"/>
      </div>
      <div>
        <label>Description</label><br/>
        <form:textarea path="description" rows="3"/>
        <form:errors path="description" cssClass="error"/>
      </div>
      <div style="margin-top:10px;">
        <button type="submit">Save</button>
      </div>
    </form:form>
  </div>

  <div style="flex:2;">
    <h3>Existing Roles</h3>
    <c:set var="page" value="${page}" />
    <table border="1" cellpadding="5" cellspacing="0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Description</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="r" items="${page.content}">
        <tr>
          <td><c:out value="${r.id}"/></td>
          <td><c:out value="${r.name}"/></td>
          <td><c:out value="${r.description}"/></td>
          <td>
            <!-- Update not required by spec -->
            <form action="${pageContext.request.contextPath}/roles/${r.id}/delete" method="post" style="display:inline;">
              <button type="submit" onclick="return confirm('Delete role?')">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <div style="margin-top:10px;">
      <c:if test="${page.totalPages > 1}">
        <c:forEach begin="0" end="${page.totalPages-1}" var="p">
          <c:choose>
            <c:when test="${p == page.number}">
              <strong>[${p+1}]</strong>
            </c:when>
            <c:otherwise>
              <a href="?page=${p}&size=${page.size}">${p+1}</a>
            </c:otherwise>
          </c:choose>
          &nbsp;
        </c:forEach>
      </c:if>
    </div>
  </div>
</div>

<style>.error{color:#c00; font-size:0.9em;}</style>
