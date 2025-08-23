<!-- SAVE: banking-project/src/main/webapp/WEB-INF/jsp/userForm.jsp -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>

<h2>User Management</h2>

<div style="display:flex; gap:30px;">
  <div style="flex:1;">
    <h3><c:choose><c:when test="${user.id != null}">Update User</c:when><c:otherwise>Create User</c:otherwise></c:choose></h3>
    <form:form modelAttribute="user" method="post"
               action="${pageContext.request.contextPath}/users<c:if test='${user.id != null}'>/${user.id}</c:if>">
      <c:if test="${user.id != null}">
        <input type="hidden" name="id" value="${user.id}"/>
      </c:if>

      <div>
        <label>Username</label><br/>
        <form:input path="username"/>
        <form:errors path="username" cssClass="error"/>
      </div>

      <div>
        <label>Email</label><br/>
        <form:input path="email"/>
        <form:errors path="email" cssClass="error"/>
      </div>

      <div>
        <label>Password</label><br/>
        <form:password path="password"/>
        <form:errors path="password" cssClass="error"/>
        <c:if test="${user.id != null}">
          <div class="hint">Leave blank to keep existing password</div>
        </c:if>
      </div>

      <div>
        <label>Confirm Password</label><br/>
        <form:password path="confirmPassword"/>
        <form:errors path="confirmPassword" cssClass="error"/>
      </div>

      <div style="margin-top:10px;">
        <label>Roles</label><br/>
        <c:forEach var="r" items="${roles}">
          <label style="display:inline-block; margin-right:10px;">
            <input type="checkbox" name="roles" value="${r.id}"
              <c:if test="${user.roles != null}">
                <c:forEach var="ur" items="${user.roles}">
                  <c:if test="${ur.id == r.id}">checked</c:if>
                </c:forEach>
              </c:if>
            />
            <c:out value="${r.name}"/>
          </label>
        </c:forEach>
        <form:errors path="roles" cssClass="error"/>
      </div>

      <div style="margin-top:10px;">
        <label>Enabled</label>
        <form:checkbox path="enabled"/>
      </div>

      <div style="margin-top:10px;">
        <button type="submit"><c:choose><c:when test="${user.id != null}">Update</c:when><c:otherwise>Save</c:otherwise></c:choose></button>
        <a href="${pageContext.request.contextPath}/users">Reset</a>
      </div>
    </form:form>
  </div>

  <div style="flex:2;">
    <h3>Existing Users</h3>
    <c:set var="page" value="${page}" />
    <table border="1" cellpadding="5" cellspacing="0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Username</th>
          <th>Email</th>
          <th>Enabled</th>
          <th>Roles</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="u" items="${page.content}">
        <tr>
          <td><c:out value="${u.id}"/></td>
          <td><c:out value="${u.username}"/></td>
          <td><c:out value="${u.email}"/></td>
          <td><c:out value="${u.enabled ? 'Yes' : 'No'}"/></td>
          <td>
            <c:forEach var="r" items="${u.roles}">
              <span><c:out value="${r.name}"/></span><c:if test="${!r.last}"> </c:if>
            </c:forEach>
          </td>
          <td>
            <a href="${pageContext.request.contextPath}/users/${u.id}/edit">Edit</a>
            <form action="${pageContext.request.contextPath}/users/${u.id}/delete" method="post" style="display:inline;">
              <button type="submit" onclick="return confirm('Delete user?')">Delete</button>
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

<style>
  .error{color:#c00; font-size:0.9em;}
  .hint{color:#666; font-size:0.85em;}
</style>
