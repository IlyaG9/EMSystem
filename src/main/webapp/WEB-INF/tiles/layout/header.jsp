<%-- 
    Document   : header
    Created on : 09.04.2014, 12:10:01
    Author     : ILYA
--%>
<%@ page contentType="text/html; charset=utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="header">
    <h2><spring:message code="title"/></h2>
    <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/home"><spring:message code="link.home"/></a> |
    <security:authorize access="hasRole('ROLE_EDITOR')">
        <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/department/"><spring:message code="link.departments"/></a> | <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/employee/addForm"><spring:message code="link.addEmployee"/></a>| <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/department/addForm"><spring:message code="link.addDepartment"/></a>| 
    </security:authorize>  
    <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/logout"><spring:message code="link.logout"/></a>
   </br>
    <span>
        <a href="?lang=en">English</a> 
        | 
        <a href="?lang=ru">Русский</a>
    </span>  
</div>
