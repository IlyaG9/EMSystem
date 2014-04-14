<%-- 
    Document   : layout
    Created on : 09.04.2014, 12:09:06
    Author     : ILYA
--%>
<%@ page contentType="text/html; charset=utf-8"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>
    <head>
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/script.js"></script>
    </head>
    <body>

        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="body" />

    </body>
</html>