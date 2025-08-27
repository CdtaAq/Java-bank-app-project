<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Please sign in</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    <div>
        <label>Username</label>
        <input type="text" name="username" required />
    </div>
    <div>
        <label>Password</label>
        <input type="password" name="password" required />
    </div>
    <button type="submit">Login</button>
</form>
</body>
</html>
