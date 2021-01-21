<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add car</title>
    </head>
    <body>
        <h1>Please enter car data</h1>

        <form method="post" action="${pageContext.request.contextPath}/cars/add">
            Model: <input type="text" name="model">
            Manufacturer id: <input type="number" name="manufacturer_id">

            <button type="submit">Submit</button>
        </form>
    </body>
</html>
