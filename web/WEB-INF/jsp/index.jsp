<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pinboard</title>

        <c:url value='/resources' var="resourcesUrl" />
        <!-- External CSS Files-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal CSS Files-->
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/default.css" />
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/components/itemsList.css" />

        <!--External JavaScript Files-->
        <script src="https://apis.google.com/js/platform.js" async defer></script> <!-- Google+ Share -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script> <!-- JQuery Form Validator -->

        <!--Internal JavaScript Files-->
        <script src="${resourcesUrl}/js/utils.js"></script>
        <script src="${resourcesUrl}/js/share.js"></script>
        <script src="${resourcesUrl}/js/default.js"></script>
    </head>

    <body>
        <div id="pageContextPath" data-page-context="${pageContext.request.contextPath}"></div>
        <div class="authorizeUser">
            <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')"> 
                <div class="toggleFavourite"></div>
            </sec:authorize>
        </div>

        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>


        <!-- Nav Bar 
        ================================================== -->

        <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>

                    </button>
                    <a class="navbar-brand" href="#">Pinboard</a>
                </div><!-- /.navbar-header -->

                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <c:url value='/favourites' var="favouritesUrl" />
                        <li><a href="${favouritesUrl}">Favourites</a></li>

                        <c:choose>
                            <c:when test="${pageContext.request.userPrincipal.name != null}">
                                <li><a>Welcome <c:out value="${pageContext.request.userPrincipal.name}" /></a></li>
                                <li><a href="javascript:formSubmit()">Logout</a></li>
                                <!--only users with admin rights are able to delete items that were not create by them. 
                                This is for maintenance purposes. -->
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <c:url value='/items/manage' var="manageItemsUrl" />
                                    <li>
                                        <a href="${manageItemsUrl}">Manage Items</a>
                                    </li>
                                </sec:authorize>
                            </c:when>

                            <c:otherwise>

                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <c:url value='/login' var="loginUrl" />
                                        <form class="navbar-form" name='loginForm' action="${loginUrl}" method='POST'>
                                            <input class="form-control" type='text' name='username' placeholder="Username" />
                                            <input class="form-control" type='password' name='password' placeholder="Password" />
                                            <form id="login" class="form-horizontal" action="" method="post" name="userForm">
                                                <fieldset>
                                                    <legend> Enter your email. You will shortly receive an email that contains a temporary password. </legend>
                                                    <div class="form-group centered">
                                                        <spring:bind path="user.user_email">
                                                            <input type="email" name="${status.expression}" value="${status.value}" placeholder="Email" class="form-control text required" id="user_email">
                                                        </spring:bind>
                                                    </div>
                                                    <div class="form-group centered">
                                                        <button id="loginSubmit" type="submit" class="btn btn-lg btn-success" onclick="addIcon()"><i id="submitIcon" class="fa fa-refresh fa-2x fa-spin hidden"></i> Continue</button>
                                                    </div>
                                            </form>    </body>

                                            <input class="btn btn-default" name="submit" type="submit" value="Login" />
                                            <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <li><a href="${pageContext.request.contextPath}/find"><span class="glyphicon glyphicon-eye-open"></span> Search </a></li>

                                        </form>
                                    </ul>
                                </li>

                                <!-- Client side validation. 
                               Client side controls are not used as a security measure because they can be easily bypassed. Client side validation is used for user's convenience to ensure valid values. 
                               Server side validation is also used in the form of prepared statements to ensure that data retrieved from users are safe and the application can trust them. -->
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Register<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <c:url value='/register' var="registerUrl" />
                                        <form class="navbar-form" name="registrationForm" action="${registerUrl}" method="POST">
                                            <input class="form-control" type="text" name="username" placeholder="Your university username"
                                                   data-validation="length alphanumeric" 
                                                   data-validation-length="4-12"
                                                   data-validation-error-msg="The username must be at least 4 characters long. Format: 'aa00001' " />
                                            <input class="form-control" type="password" name="pass_confirmation" placeholder="Password (Min 8 Characters)"
                                                   data-validation="length"
                                                   data-validation-length="min8"
                                                   data-validation-error-msg="The password must be at least 8 characters long " />
                                            <input class="form-control" type="password" name="pass" placeholder="Confirm Password"
                                                   data-validation="confirmation">
                                            <input class="form-control" type="text" name="name" placeholder="Name" />
                                            <input class="form-control" type="email" name="email" placeholder="Email"
                                                   data-validation="email" />
                                            <input class="btn btn-default" type="submit" value="Register" />
                                            <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                        </form>
                                    </ul>
                                </li>

                            </c:otherwise>
                        </c:choose>

                        <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                            <c:url value="/logout" var="logoutUrl" />

                            <!-- Protection against CRSF attack. 
                            Hackers use Cross-Site Request Forgery attack in order to steal the cookies from the authenticated user. 
                            Docs.spring.io, 'Spring Security Reference', 2015. [Online]. Available: http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#csrf-using. [Accessed: 09- Feb- 2015].-->
                            <form action="${logoutUrl}" method="POST" id="logoutForm">
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                            </form>
                        </sec:authorize>
                    </ul><!-- /.navbar-nav -->
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container -->
        </nav><!-- /.navbar -->

        <!-- /.Nav Bar
        ================================================== -->

        <div class="row featurette share">
            <div class="col-md-4">
                <div class="g-plus share-button" data-action="share"></div><br/>
                <a class="twitter-share-button share-button" href="https://twitter.com/share">Tweet</a><br/>
                <div class="fb-share-button share-button" data-href="${pageContext.request.contextPath}"></div>
            </div>
        </div> <!-- /.share.featurette -->

        <hr class="featurette-divider">

        <!-- Back to top button. It appears dynamically and once clicked to page scrolls back to top smoothly -->
        <a href="#" class="back-to-top btn btn-default">Back to Top </a> 

        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        <script src="${resourcesUrl}/js/register.js"></script>
    </body>
</html>