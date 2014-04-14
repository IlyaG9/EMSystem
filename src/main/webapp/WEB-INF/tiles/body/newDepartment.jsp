<%-- 
    Document   : newDepartment
    Created on : 10.04.2014, 20:13:23
    Author     : ILYA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <sf:errors cssClass="error" path="*" delimiter=", "/>
    <sf:form  method="POST" modelAttribute="department" id="department_form" action="${pageContext.servletContext.contextPath}/department/add">
        <fieldset>
            <spring:message code="name"/>:  <sf:input path="name" id="department_name"/>
            <sf:errors path="name" cssClass="error"/><br>
            <spring:message code="description"/>:   <sf:textarea path="description" id="department_description"/>
            <sf:errors path="description" cssClass="error" /><br>
            <input type="submit" value="<spring:message code="button.save"/> "/>
        </fieldset>
    </sf:form>

</div>