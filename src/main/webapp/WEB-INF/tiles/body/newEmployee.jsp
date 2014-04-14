<%-- 
    Document   : newEmployee
    Created on : 09.04.2014, 20:15:34
    Author     : ILYA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
       <script type="text/javascript">
        $(function() {
            day =<c:out value="${employee.birthdate.date}"/>;
            month =<c:out value="${employee.birthdate.month}"/> + 1;
            year =<c:out value="${employee.birthdate.year}"/> + 1900;
            var date = day + "/" + month + "/" + year;
            $("#employee_birthdate").attr("value", date);
        });
    </script>
    <sf:errors cssClass="error" path="*" delimiter=", "/>
    <sf:form  method="POST" modelAttribute="employee" id="employee_form" action="${pageContext.servletContext.contextPath}/employee/add">
        <fieldset>
            <spring:message code="firstname"/> <sf:input path="firstname" id="employee_first_name"/>
            <sf:errors path="firstname" cssClass="error" /><br>
            <spring:message code="lastname"/>   <sf:input path="lastname" id="employee_last_name"/>
            <sf:errors path="lastname" cssClass="error" /><br>
            <spring:message code="salary"/>   <sf:input path="salary" id="employee_salary"/>
            <sf:errors path="salary" cssClass="error" /><br>
            <spring:message code="birthdate"/>   <sf:input path="birthdate" id="employee_birthdate"/>
            <sf:errors path="birthdate" cssClass="error" /><br>
            <spring:message code="isactive"/>  <sf:checkbox path="active" id="employee_active"/>
            <sf:errors path="active" cssClass="error" /><br>
            <spring:message code="department"/>  <sf:select path="department" id="employee_department"><sf:options items="${departments}" itemValue="name" itemLabel="name"/>
            </sf:select><sf:errors path="department" cssClass="error" /><br> 
            <input type="submit" value="<spring:message code="button.save"/> "/>
        </fieldset>
    </sf:form>

</div>
