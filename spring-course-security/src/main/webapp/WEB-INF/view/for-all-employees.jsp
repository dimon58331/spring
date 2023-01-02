<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>
    <h3>Information for all employees</h3>

    <security:authorize access="hasRole('HR')">
        Get information about employees. Only for HRs
        <input type="button" value="GET" onclick="window.location.href='hr-info'"/>
        <br><br>
    </security:authorize>

    <security:authorize access="hasRole('MANAGER')">
        Get information about salaries of employees. Only for managers
        <input type="button" value="GET" onclick="window.location.href='manager-info'"/>
        <br><br>
    </security:authorize>

</body>
</html>
