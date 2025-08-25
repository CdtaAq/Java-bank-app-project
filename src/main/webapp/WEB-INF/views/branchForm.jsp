<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Branches</title>
</head>
<body>
<h2>${branch.branchId == null ? 'Create Branch' : 'Edit Branch'}</h2>

<form:form method="post"
           action="${pageContext.request.contextPath}/branches${branch.branchId != null ? '/' += branch.branchId : ''}"
           modelAttribute="branch">
    <c:if test="${branch.branchId != null}">
        <input type="hidden" name="_method" value="post"/>
    </c:if>

    <div>
        <label>Code</label><br/>
        <form:input path="branchCode"/>
        <form:errors path="branchCode" cssClass="error"/>
    </div>
    <div>
        <label>Name</label><br/>
        <form:input path="branchName"/>
        <form:errors path="branchName" cssClass="error"/>
    </div>
    <div>
        <label>Address</label><br/>
        <form:input path="branchAddress"/>
        <form:errors path="branchAddress" cssClass="error"/>
    </div>

    <br/>
    <button type="submit">${branch.branchId == null ? 'Save' : 'Update'}</button>
    <a href="${pageContext.request.contextPath}/branches">Reset</a>
</form:form>

<hr/>

<h2>Branch List</h2>

<c:set var="pageObj" value="${page}"/>
<table border="1" cellpadding="6">
    <thead>
    <tr>
        <th>ID</th>
        <th>
            <a href="?sort=branchCode&dir=${dir == 'asc' ? 'desc' : 'asc'}">Code</a>
        </th>
        <th>
            <a href="?sort=branchName&dir=${dir == 'asc' ? 'desc' : 'asc'}">Name</a>
        </th>
        <th>Address</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="b" items="${pageObj.content}">
        <tr>
            <td>${b.branchId}</td>
            <td>${b.branchCode}</td>
            <td>${b.branchName}</td>
            <td>${b.branchAddress}</td>
            <td>
                <a href="${pageContext.request.contextPath}/branches/${b.branchId}/edit">Edit</a>
                <form action="${pageContext.request.contextPath}/branches/${b.branchId}/delete" method="post" style="display:inline">
                    <button type="submit" onclick="return confirm('Delete this branch?')">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div style="margin-top:10px;">
    <c:if test="${!pageObj.first}">
        <a href="?page=${pageObj.number - 1}&size=${pageObj.size}&sort=${sort}&dir=${dir}">Prev</a>
    </c:if>
    Page ${pageObj.number + 1} of ${pageObj.totalPages}
    <c:if test="${!pageObj.last}">
        <a href="?page=${pageObj.number + 1}&size=${pageObj.size}&sort=${sort}&dir=${dir}">Next</a>
    </c:if>
</div>

</body>
</html>
