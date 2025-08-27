<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
    <h2>Employee List</h2>
    <a href="${pageContext.request.contextPath}/employees/form">Add Employee</a>
    <br><br>
    <table border="1">
        <tr>
            <th>ID</th><th>First Name</th><th>Last Name</th>
            <th>Email</th><th>Phone</th><th>Role</th><th>Branch</th><th>Actions</th>
        </tr>
        <c:forEach var="emp" items="${employees}">
            <tr>
                <td>${emp.employeeId}</td>
                <td>${emp.firstName}</td>
                <td>${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.phone}</td>
                <td>${emp.role.roleName}</td>
                <td>${emp.branch.branchName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/employees/edit/${emp.employeeId}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/employees/delete/${emp.employeeId}" onclick="return confirm('Delete this employee?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/">Back to Home</a>
</body>
</html>
