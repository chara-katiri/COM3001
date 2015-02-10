<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>

        <c:url value='/resources' var="resourcesUrl" />
        <!-- External CSS Files-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal CSS Files-->
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/forms.css" />
    </head>

    <body>
        <!-- Login and logout container -->
        <div class="container">
            <c:url value='/login' var="loginUrl"></c:url>

            <!-- Validation to ensure that values are submitted in order to login and then to allow you to logout-->
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
                <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> <!-- Protection against CRSF attack. 
Hackers use Cross-Site Request Forgery attack in order to steal the cookies from the authenticated user. 
Docs.spring.io, 'Spring Security Reference', 2015. [Online]. Available: http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#csrf-using. [Accessed: 09- Feb- 2015].-->
 

                <a class="btn btn-primary" href="/Pinboard/index">Back to home</a>
                <a class="btn btn-primary" href="/Pinboard/register">Register</a>
            </form>
        </div> <!-- end of login and logout container -->
    </body>
</html>
