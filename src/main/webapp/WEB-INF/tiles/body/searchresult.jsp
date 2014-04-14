<%-- 
    Document   : searchresult
    Created on : 09.04.2014, 12:42:06
    Author     : ILYA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="content">
    <form action="<c:out value="${pageContext.servletContext.contextPath}"/>/home" method="GET">
        <input type="submit" value="<spring:message code="button.reset"/>"/>
    </form>
    <ol>
        <c:forEach var="employee" items="${employeeList}">
            <li>   <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/employee/<c:out value="${employee.id}"/>"/><c:out value="${employee.firstname}"/> <c:out value="${employee.lastname}"/></a> </li    >
            </c:forEach>   
    </ol>       
</div>
    <div id="pagin" align="center">
        <c:if test='<%= (Integer) request.getAttribute("pages") > 0%>'>
            <%
                int pages = (Integer) request.getAttribute("pages");
                for (int i = 0; i < pages; i++) {
            %>
            <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/employee/search?page=<%=i%>&name=<%=request.getAttribute("name")%>"><%=i + 1%></a>
            <%
                }
            %>
        </c:if>
    </div>