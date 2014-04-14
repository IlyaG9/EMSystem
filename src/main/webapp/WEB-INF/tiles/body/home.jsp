<%-- 
    Document   : home
    Created on : 09.04.2014, 12:12:44
    Author     : ILYA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div align="center">

<form method="GET" action="employee/search">
    <input type="text" name="name"/>
    <input type="submit" value="<spring:message code="button.search"/>"/>
</form> 

<c:forEach var="employee" items="${employeeList}">
    <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/employee/<c:out value="${employee.id}"/> "><c:out value="${employee.firstname}"/> <c:out value="${employee.lastname}"/></a> </br>
</c:forEach>

</div>
    <div id="pagin" align="center">
        <c:if test='<%= (Integer) request.getAttribute("pages") > 0%>'>
            <%
                int pages = (Integer) request.getAttribute("pages");
                for (int i = 0; i < pages; i++) {
            %>
            <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/home?page=<%=i%>"><%=i + 1%></a>
            <%
                }
            %>
        </c:if>
    </div>