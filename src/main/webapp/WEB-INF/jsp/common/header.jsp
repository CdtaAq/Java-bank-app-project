<!-- SAVE: banking-project/src/main/webapp/WEB-INF/jsp/common/header.jsp -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div style="padding:10px;border-bottom:1px solid #ddd;margin-bottom:15px;">
  <a href="${pageContext.request.contextPath}/roles">Roles</a>
  <!-- Add links to /users, /customers, /accounts later -->
  <span style="float:right;">
    <sec:authorize access="isAuthenticated()">
      Logged in as <sec:authentication property="name"/> |
      <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
      <a href="${pageContext.request.contextPath}/login">Login</a>
    </sec:authorize>
  </span>
</div>
