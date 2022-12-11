<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <th>operations</th>
        </tr>

        <c:forEach var="employee" items="${allEmployees}">

            <c:url var="updateButton" value="/updateInfo">
                <c:param name="empId" value="${employee.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteEmployee">
                <c:param name="empId" value="${employee.id}"/>
            </c:url>

            <tr>
                <td>${employee.name}</td>
                <td>${employee.surname}</td>
                <td>${employee.department}</td>
                <td>${employee.salary}</td>
                <td>
                    <input type="button" value="Update" onclick="window.location.href='${updateButton}'">
                    <input type="button" value="Delete" onclick="window.location.href='${deleteButton}'">
                </td>
            </tr>
        </c:forEach>

    </table>

   <input type="button" value="Add" onclick="window.location.href = 'addNewEmployee'">

</body>
</html>