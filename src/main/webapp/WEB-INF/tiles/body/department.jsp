<%-- 
    Document   : department
    Created on : 10.04.2014, 21:21:48
    Author     : ILYA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div align="center">
        <table border="1">
            <thead>
                <tr>
                <th><spring:message code="id"/></th>
                <th><spring:message code="name"/></th>
                <th><spring:message code="description"/></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><c:out value="${department.id}"/></td>
                    <td><c:out value="${department.name}"/></td>
                    <td><c:out value="${department.description}"/></td>

                    <td><a href="<c:out value="${pageContext.servletContext.contextPath}"/>/department/edit/${department.id}"><spring:message code="button.edit"/></a></td>
                </tr>
            </tbody>
        </table>
        </div>
