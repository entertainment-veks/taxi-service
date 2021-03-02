<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
    <title>All drivers</title>
    </head>
    <body>
        <h1>All drivers</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Licence Number</th>
            <th>Login</th>
            <th>Password</th>
        </tr>
        <c:forEach var="driver" items="${allDrivers}">
            <tr>
                <td>
                    <c:out value="${driver.id}"/>
                </td>
                <td>
                    <c:out value="${driver.name}"/>
                </td>
                <td>
                    <c:out value="${driver.licenceNumber}"/>
                </td>
                <td>
                    <c:out value="${driver.login}"/>
                </td>
                <td>
                    <c:out value="${driver.password}"/>
                </td>
            </tr>
        </c:forEach>
        </table>
    </body>
</html>
