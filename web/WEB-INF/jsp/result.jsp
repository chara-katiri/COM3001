<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Result</title>

        <c:url value='/resources' var="resourcesUrl" />
        <!-- CSS Files -->
        <!-- External-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal-->
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/result.css" />
    </head>
    <body>
        <div class="container">

            <c:url value='/login' var="loginUrl" />
            <c:url value='/register' var="registerUrl" />

            <c:choose>
                <c:when test="${result eq 'success'}">
                    <h3 class="success">Successfully registered.<br/>Please proceed to login below</h3>
                    <a class="btn btn-primary" href="${loginUrl}">Login</a>
                </c:when>
                <c:when test="${result eq 'alreadyexists'}">
                    <h3 class="alreadyexists">That user already exists.<br/>Please proceed to login below</h3>
                    <a class="btn btn-primary" href="${loginUrl}">Login</a>
                </c:when>
                <c:otherwise>
                    <h3 class="error">There has been an error.<br/>Please try again.</h3>
                    <a class="btn btn-primary" href="${registerUrl}">Register</a>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
