<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Driver login</title>
    </head>
    <body>
        <h1>Please enter login and password</h1>

        <h4 style="color: red">${errorMsg}</h4>

        <form method="post" action="${pageContext.request.contextPath}/drivers/login">
            Login: <input type="text" name="login">
            Password: <input type="password" name="password">
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
