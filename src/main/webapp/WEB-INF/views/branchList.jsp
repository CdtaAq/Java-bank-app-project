<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Branch List</title>
</head>
<body>
    <h2>All Branches</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Branch Code</th>
            <th>Branch Name</th>
            <th>Branch Address</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="branch" items="${branches}">
            <tr>
                <td>${branch.branchId}</td>
                <td>${branch.branchCode}</td>
                <td>${branch.branchName}</td>
                <td>${branch.branchAddress}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/branches/edit/${branch.branchId}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/branches/delete/${branch.branchId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="${pageContext.request.contextPath}/branches/form">Add New Branch</a>
</body>
</html>
