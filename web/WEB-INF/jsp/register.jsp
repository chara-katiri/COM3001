<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>

        <c:url value='/resources' var="resourcesUrl" />
        <!-- CSS Files -->
        <!-- External-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal -->
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/forms.css" />

        <!-- JavaScript Files -->
        <!--External-->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- JQuery -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>
    </head>

    <body>
        <c:url value='/register' var="registerUrl"></c:url>
        <form class="form" id="registrationForm" action="${registerUrl}" method="POST">
            <h2 class="form-heading">Register New User</h2>

            <input class="form-control" type="text" name="username" placeholder="Username"
                   data-validation="length alphanumeric" 
                   data-validation-length="4-12"
                   data-validation-error-msg="The username must be at least 4 characters long " />
            <input class="form-control" type="password" name="pass_confirmation" placeholder="Password (Min 8 Characters)"
                   data-validation="length"
                   data-validation-length="min8"
                   data-validation-error-msg="The password must be at least 8 characters long" />
            <input class="form-control" type="password" name="pass" placeholder="Confirm Password"
                   data-validation="confirmation"/>
            <input class="form-control" type="text" name="name" placeholder="Name" />
            <input class="form-control" type="email" name="email" placeholder="Email"
                   data-validation="email" />
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Register" />
            <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <a class="btn btn-primary" href="/Finding-Festivals-Forever/index">Back to home</a>
            <a class="btn btn-primary" href="/Finding-Festivals-Forever/login">Login</a>
        </form>

        <script src="${resourcesUrl}/js/register.js"></script>

    </body>
</html>
