<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<body>

<h2>Your name: ${employee.name}</h2>
<h2>Your surname: ${employee.surname}</h2>
<h2>Your salary: ${employee.salary}</h2>
<h2>Your department: ${employee.department}</h2>
<h2>Your car: ${employee.carBrand}</h2>
<h2>Your foreign languages: </h2>
<ul>
    <c:forEach var="language" items="${employee.foreignLanguages}">
        <h3>
            <li>${language}</li>
        </h3>
    </c:forEach>
</ul>
<h2>Your phone number: +375-${employee.phoneNumber}</h2>
<h2>Your email: ${employee.email}</h2>

</body>

</html>