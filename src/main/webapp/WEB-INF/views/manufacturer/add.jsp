<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add manufacturer</title>
    </head>
    <body>
        <h1>Please enter manufacturer data</h1>

        <form method="post" action="${pageContext.request.contextPath}/manufacturers/add">
            Name: <input type="text" name="name">
            Country: <input type="text" name="country">

            <button type="submit">Submit</button>
        </form>
    </body>
</html>

