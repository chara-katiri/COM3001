<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>

        <c:url value='/resources' var="resourcesUrl" />
        <!-- CSS Files -->
        <!-- External-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal -->
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/forms.css" />
    </head>

    <body>
        <div class="container">
            <c:url value='/login' var="loginUrl"></c:url>

            <form class="form" name='loginForm' action="${loginUrl}" method='POST'>
                <h2 class="form-heading">Please Login to continue</h2>
                
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty logout}">
                    <div class="logout">${logout}</div>
                </c:if>
                
                <input class="form-control" type='text' name='username' placeholder="Username" required autofocus />
                <input class="form-control last-input" type='password' name='password' placeholder="Password" required />
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login" />
                <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <a class="btn btn-primary" href="/Finding-Festivals-Forever/index">Back to home</a>
                <a class="btn btn-primary" href="/Finding-Festivals-Forever/register">Register</a>
            </form>
        </div> <!-- ./container -->
    </body>
</html>
