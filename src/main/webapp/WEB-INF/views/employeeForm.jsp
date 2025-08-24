<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Form</title>
</head>
<body>
    <h2>Employee Form</h2>

    <form action="${pageContext.request.contextPath}/employees/save" method="post">
        <input type="hidden" name="employeeId" value="${employee.employeeId}" />

        First Name: <input type="text" name="firstName" value="${employee.firstName}" /><br><br>
        Last Name: <input type="text" name="lastName" value="${employee.lastName}" /><br><br>
        Email: <input type="email" name="email" value="${employee.email}" /><br><br>

        Role:
        <select name="role.roleId">
            <c:forEach var="role" items="${roles}">
                <option value="${role.roleId}" 
                    <c:if test="${employee.role != null && employee.role.roleId == role.roleId}">selected</c:if>>
                    ${role.roleName}
                </option>
            </c:forEach>
        </select>
        <br><br>

        <input type="submit" value="Save Employee" />
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/employees/list">Back to Employee List</a>
</body>
</html>
