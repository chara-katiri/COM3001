<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>

        <c:url value='/resources' var="resourcesUrl"></c:url>
            <!-- External CSS Files-->
            <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->
            <script scr="http://code.jquery.com/jquery-2.1.1.min.js"></script>
            <script src="js/jquery.validate.js"></script>

            <!--Internal CSS Files-->
            <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/forms.css" />
    </head>

       <body onload='document.loginForm.username.focus();'>
      <!--        Login and logout container -->
            <div class="container">
    
    <!-- Validation to ensure that values are submitted in order to login and then to allow you to logout-->
    <form class="form" name='loginForm'  action="${loginUrl}" method='POST'>

        <c:url value='/login' var="loginUrl"></c:url>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <c:if test="${not empty logout}">
            <div class="logout">${logout}</div>
        </c:if>


        <h2 class="form-heading">Please Login to continue</h2>
        <!--                 input text and validation   -->
        <input class="form-control" type='text' name='username' placeholder="Username" required autofocus 
               data-validation="length alphanumeric" 
               data-validation-length="7"
               data-validation-error-msg="The username must 7 characters long. Format: 'aa00123' "
               />

        <input class="form-control last-input" type='password' name='password' placeholder="Password" required 
               data-validation="length"
               data-validation-length="min8"
               data-validation-error-msg="The password must be at least 8 characters long "
               />
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login" />

<!--                 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->
        <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />  
        <!--    Protection against CRSF attack. Hackers use Cross-Site Request Forgery attack in order to steal the cookies from the authenticated user. 
                Docs.spring.io, 'Spring Security Reference', 2015. [Online]. Available: http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#csrf-using. 
                [Accessed: 09- Feb- 2015].-->


        <a class="btn btn-primary" href="index">Back to home</a>
        <a class="btn btn-primary" href="register">Register</a>
    </form>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> 
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.2.1/jquery.form-validator.min.js"></script>
    <script> $("#loginForm").validate();</script> 
</div> <!-- end of login and logout container -->
</body>
</html>
