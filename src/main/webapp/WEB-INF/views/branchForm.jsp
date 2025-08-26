<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Branch Form</title>
</head>
<body>
    <h2>Branch Form</h2>

    <form action="${pageContext.request.contextPath}/branches/save" method="post">
        <input type="hidden" name="branchId" value="${branch.branchId}" />

        <label>Branch Code:</label>
        <input type="text" name="branchCode" value="${branch.branchCode}" required/><br><br>

        <label>Branch Name:</label>
        <input type="text" name="branchName" value="${branch.branchName}" required/><br><br>

        <label>Branch Address:</label>
        <input type="text" name="branchAddress" value="${branch.branchAddress}" /><br><br>

        <button type="submit">Save</button>
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/branches/list">Back to List</a>
</body>
</html>
