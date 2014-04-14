<%-- 
    Document   : departments
    Created on : 09.04.2014, 12:36:48
    Author     : ILYA
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div align="center">
    <form action="search" method="GET">
        <input type="text" name="name"/>
        <input type="submit" value="<spring:message code="button.search"/>"/>
    </form>
    </br>

    <c:forEach var="department" items="${departments}">
        <a href="${pageContext.servletContext.contextPath}/department/<c:out value="${department.id}"/>">
            <c:out value="${department.name}"/>
        </a></br>
    </c:forEach>

</div>
<div id="pagin" align="center">
    <c:if test='<%= (Integer) request.getAttribute("pages") > 0%>'>
        <%
            int pages = (Integer) request.getAttribute("pages");
            for (int i = 0; i < pages; i++) {
        %>
        <a href="<c:out value="${pageContext.servletContext.contextPath}"/>/department/?page=<%=i%>"><%=i + 1%></a>
        <%
            }
        %>
    </c:if>
</div>