<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Customer Form</title>
</head>
<body>
    <h2>Customer Form</h2>
    <form:form action="save" modelAttribute="customer" method="post">
        <form:hidden path="customerId"/>
        First Name: <form:input path="firstName"/><br/>
        Last Name: <form:input path="lastName"/><br/>
        Email: <form:input path="email"/><br/>
        Phone: <form:input path="phone"/><br/>
        <input type="submit" value="Save"/>
    </form:form>
    <br/>
    <a href="/customers/list">Back to Customer List</a>
</body>
</html>
