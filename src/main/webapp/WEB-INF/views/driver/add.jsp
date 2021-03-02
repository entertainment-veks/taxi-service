<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add driver</title>
    </head>
    <body>
        <h1>Please enter driver data</h1>

        <form method="post" action="${pageContext.request.contextPath}/drivers/add">
            Name: <input type="text" name="name">
            Licence number: <input type="text" name="licence_number">
            Login: <input type="text" name="login">
            Password: <input type="text" name="password">
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
