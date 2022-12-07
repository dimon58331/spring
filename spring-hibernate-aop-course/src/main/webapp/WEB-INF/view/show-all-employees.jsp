<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

    <h1>All employees</h1>

    <br>

    <table>
        <tr>
            <th>name</th>
            <th>surname</th>
            <th>department</th>
            <th>salary</th>
        </tr>

        <c:forEach var="employee" items="${allEmployees}">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.surname}</td>
                <td>${employee.department}</td>
                <td>${employee.salary}</td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>