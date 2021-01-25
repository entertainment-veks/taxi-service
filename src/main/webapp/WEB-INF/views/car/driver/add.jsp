<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add driver to car</title>
    </head>
    <body>
        <h1>Please enter data</h1>

        <form method="post" action="${pageContext.request.contextPath}/cars/drivers/add">
            Driver id: <input type="number" name="driver_id">
            Car id: <input type="number" name="car_id">

            <button type="submit">Submit</button>
        </form>
    </body>
</html>
