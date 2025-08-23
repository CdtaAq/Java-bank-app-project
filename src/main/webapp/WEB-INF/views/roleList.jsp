<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role List</title>
</head>
<body>
    <h2>All Roles</h2>

    <table border="1">
        <tr>
            <th>Role ID</th>
            <th>Role Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="role" items="${roles}">
            <tr>
                <td>${role.roleId}</td>
                <td>${role.roleName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/roles/edit/${role.roleId}">Edit</a>
                    |
                    <a href="${pageContext.request.contextPath}/roles/delete/${role.roleId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="${pageContext.request.contextPath}/roles/form">Add New Role</a>
</body>
</html>
