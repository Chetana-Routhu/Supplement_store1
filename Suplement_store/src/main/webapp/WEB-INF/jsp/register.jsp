<html>
<body>
<h2>Register</h2>
<form action="/register" method="post">
    Username: <input type="text" name="username" required><br/>
    Password: <input type="password" name="password" required><br/>
    Confirm Password: <input type="password" name="confirmPassword" required><br/>
    <input type="submit" value="Register">
</form>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
</body>
</html>
