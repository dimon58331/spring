<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<body>

<h2>Enter your name: </h2>
<br>
<br>

<%--<form action="showDetails" method="get">--%>
<%--    <input type="text" name="employeeName"--%>
<%--    placeholder="Write your name"/>--%>

<%--    <input type="submit" value="SUBMIT!"/>--%>
<%--</form>--%>

<form:form action="showDetails" modelAttribute="employee">
    Name:   <form:input path="name"/>
            <br>
            <form:errors cssStyle="color: brown" path="name"/>

    <br><br>

    Surname:<form:input path="surname"/>
            <br>
            <form:errors cssStyle="color: brown" path="surname"/>

    <br><br>

    Salary: <form:input path="salary"/>
            <br>
            <form:errors cssStyle="color: brown" path="salary"/>

    <br><br>

    Department: <form:select path="department">
                    <form:options items="${employee.departments}"/>
                </form:select>

    <br><br>

    Choose the car brand:   <form:radiobuttons path="carBrand" items="${employee.carBrands}"/>
                            <br>
                            <form:errors cssStyle="color: brown" path="carBrand"/>

    <br><br>

    Choose the foreign languages:   <form:checkboxes path="foreignLanguages" items="${employee.foreignLanguagesMap}"/>
                                    <br>
                                    <form:errors cssStyle="color: brown" path="foreignLanguages"/>

    <br><br>

    Enter your phone number (format: XX-XXX-XX-XX): <form:input path="phoneNumber" />
                                                    <br>
                                                    <form:errors cssStyle="color: brown" path="phoneNumber"/>

    <br><br>

    Enter your email: <form:input path="email" />
    <br>
    <form:errors cssStyle="color: brown" path="email"/>

    <br><br>

    <input type="submit" value="OK">
</form:form>

</body>

</html>
