<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Denied</title>
        <c:url value='/resources' var="resourcesUrl" />

        <!-- CSS Files -->
        <!-- External-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal-->        
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/errorPage.css" />
    </head>
    <body>
        <div class="jumbotron">
            <h1>Access Denied</h1>
            <img src="${resourcesUrl}/images/errorPage.jpg" alt="" />
            <p><a class="btn btn-primary btn-lg back-to-home" href="/Pinboard/index">Back to home</a></p>
        </div>
    </body>
</html>
