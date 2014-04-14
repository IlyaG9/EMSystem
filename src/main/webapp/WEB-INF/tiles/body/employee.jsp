<%-- 
    Document   : employee
    Created on : 09.04.2014, 12:38:42
    Author     : ILYA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div align="center">
    <table border="1">
        <thead>
            <tr>
                <th><spring:message code="id"/></th>
                <th><spring:message code="firstname"/></th>
                <th><spring:message code="lastname"/></th>
                <th><spring:message code="salary"/></th>
                <th><spring:message code="birthdate"/></th>
                <th><spring:message code="isactive"/></th>
                <th><spring:message code="department"/></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><c:out value="${employee.id}"/></td>
                <td><c:out value="${employee.firstname}"/></td>
                <td><c:out value="${employee.lastname}"/></td>
                <td><c:out value="${employee.salary}"/></td>
                <td><c:out value="${employee.birthdate}"/></td>
                <td>
                    <c:out value="${employee.isActive()}"/>
                </td>
                <td><c:out value="${employee.department}"/></td>
        <security:authorize access="hasRole('ROLE_EDITOR')">
            <td><a href="<c:out value="${pageContext.servletContext.contextPath}"/>/employee/edit/${employee.id}"><spring:message code="button.edit"/></a></td>
        </security:authorize>
        </tr>
        </tbody>
    </table>
</div>