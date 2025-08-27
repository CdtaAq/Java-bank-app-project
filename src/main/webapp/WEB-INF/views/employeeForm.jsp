<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Form</title>
</head>
<body>
    <h2>Employee Form</h2>
    <form action="${pageContext.request.contextPath}/employees/save" method="post">
        <input type="hidden" name="employeeId" value="${employee.employeeId}"/>

        First Name: <input type="text" name="firstName" value="${employee.firstName}" required/><br><br>
        Last Name: <input type="text" name="lastName" value="${employee.lastName}" required/><br><br>
        Email: <input type="email" name="email" value="${employee.email}" required/><br><br>
        Phone: <input type="text" name="phone" value="${employee.phone}" required/><br><br>

        Role:
        <select name="role.roleId" required>
            <c:forEach var="role" items="${roles}">
                <option value="${role.roleId}" ${employee.role != null && employee.role.roleId == role.roleId ? 'selected' : ''}>
                    ${role.roleName}
                </option>
            </c:forEach>
        </select><br><br>

        Branch:
        <select name="branch.branchId" required>
            <c:forEach var="branch" items="${branches}">
                <option value="${branch.branchId}" ${employee.branch != null && employee.branch.branchId == branch.branchId ? 'selected' : ''}>
                    ${branch.branchName}
                </option>
            </c:forEach>
        </select><br><br>

        <button type="submit">Save</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/employees/list">Back to Employee List</a>
</body>
</html>
