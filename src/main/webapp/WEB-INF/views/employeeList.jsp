<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h2>All Employees</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.firstName} ${employee.lastName}</td>
                <td>${employee.email}</td>
                <td>${employee.role.roleName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/employees/edit/${employee.employeeId}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/employees/delete/${employee.employeeId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <a href="${pageContext.request.contextPath}/employees/form">Add New Employee</a>
</body>
</html>
