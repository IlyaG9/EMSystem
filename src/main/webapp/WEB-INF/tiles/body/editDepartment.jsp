<%-- 
    Document   : editDepartment
    Created on : 11.04.2014, 9:09:39
    Author     : ILYA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div align="center">
    <sf:form method="POST" modelAttribute="department" action="${pageContext.servletContext.contextPath}/department/edit">
        <sf:hidden path="id"/>
        <spring:message code="name"/>:
        <sf:input path="name" id="department_name"/><sf:errors path="name" cssClass="error"/></br>
        <spring:message code="description"/>:
        <sf:textarea path="description" id="department_description"/><sf:errors path="description" cssClass="error"/></br>
        <input type="submit" value="<spring:message code="button.save"/>"/>
    </sf:form>
</div>
