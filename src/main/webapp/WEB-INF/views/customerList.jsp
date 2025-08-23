<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
    <h2>Customer List</h2>
    <a href="/customers/new">Add New Customer</a>
    <table border="1">
        <tr>
            <th>ID</th><th>First Name</th><th>Last Name</th>
            <th>Email</th><th>Phone</th><th>Actions</th>
        </tr>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.customerId}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.email}</td>
                <td>${customer.phone}</td>
                <td>
                    <a href="/customers/edit/${customer.customerId}">Edit</a> |
                    <a href="/customers/delete/${customer.customerId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
