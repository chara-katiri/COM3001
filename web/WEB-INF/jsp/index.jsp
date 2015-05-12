<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session ="true"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pinboard</title>

        <c:url value='/resources' var="resourcesUrl" />

        <!-- External CSS Files-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal CSS Files-->
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/itemsList.css" />       
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/default.css" />

        
        <!--External JavaScript Files-->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script> <!-- JQuery Form Validator -->
        <script src="https://apis.google.com/js/platform.js" async defer></script> <!-- Google+ Share -->

        <!--Internal JavaScript Files-->
        <script src="${resourcesUrl}/js/itemsList.js"></script>
        <script src="${resourcesUrl}/js/share.js"></script>
        <script src="${resourcesUrl}/js/utils.js"></script>       
        <script src="${resourcesUrl}/js/default.js"></script>

        
          <style>
            .jumbotron {
                padding: 50px;
            }
        </style>
        
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


        <!-- Nav Bar beginning -->

        <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Pinboard</a>
                </div><!-- /.navbar-header -->

                <div id="navbar" class="active">
                    <ul class="nav navbar-nav">
                        <c:url value='/favourites' var="favouritesUrl" />
                        <li><a href="${favouritesUrl}">Favourites</a></li>

                        <c:choose>
                            <c:when test="${pageContext.request.userPrincipal.name != null}">
                                <li><a>Welcome <c:out value="${pageContext.request.userPrincipal.name}" /></a></li>
                                <li><a href="javascript:formSubmit()">Logout</a></li>

                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <c:url value='/items/manage' var="manageItemsUrl"/>
                                    <li>
                                        <a href="${manageItemsUrl}">Manage Items</a>
                                    </li>
                                </sec:authorize>
                            </c:when>

                            <c:otherwise>
                                <c:url value='/login' var="loginUrl"></c:url>
                                <li><a href="/login">Login</a></li>
                                
                                <c:url value='/register' var="registerUrl" />
                                <li><a href="/register">Register</a></li>                                 
                           </c:otherwise>

                        </c:choose>        


<!--                        <ul>
                            <li class="active"><a href="/favourites">Favourites</a></li>
                            <li><a href="/login">Login</a></li>
                            <li><a href="/register">Register</a></li>
                        </ul>-->

<!--                        Functionality that allows the users to search items 
                        <form class="navbar-form navbar-right">
                            <input type="text" class="form-control" placeholder="Search...">
                        </form>-->


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
                    </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container -->
            </nav><!-- /.navbar -->

            <!-- /.Nav Bar ends -->


            <!--                menu -->

            <div class="itemsList">
                <div class="col-lg-4">
                    <img class="img-square" src="${resourcesUrl}/images/books.png" alt="Books" width="140" height="140">
                    <h2>Books</h2>
                    <p>Second hand books available for sale.</p>
                    <p><a class="btn btn-default" href="#" role="button">View books &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <img class="img-square" src="${resourcesUrl}/images/house.png" alt="Rooms" width="140" height="140">
                    <h2>Rooms</h2>
                    <p>Requests for accommodation. </p>
                    <p><a class="btn btn-default" href="#" role="button">View Rooms &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <img class="img-square" src="${resourcesUrl}/images/star.png" alt="Favourites" width="140" height="140">
                    <h2>Favourites</h2>
                    <p> Please login to view your favourites. </p>
                    <p><a class="btn btn-default" href="/favourites" role="button">View Favourites &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
            </div><!-- /.row -->

            <!--                /menu-->


            <div style="position: absolute; bottom: 5px;">
                <div class="share" style= "position: absolute; bottom: 5px;">           
                    <div class="g-plus share-button" data-action="share"></div><br/>
                    <a class="twitter-share-button share-button" href="https://twitter.com/share">Tweet</a><br/>
                    <div class=" fb-share-button share-button" href="${pageContext.request.contextPath}"></div>       
                </div> <!-- /.share -->
            </div>
                

            <!--        <hr class="featurette-divider">-->

            <!-- Back to top button. It appears dynamically and once clicked to page scrolls back to top smoothly -->
            <a href="#" class="back-to-top btn btn-default">Back to Top </a> 

            <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
            <script src="${resourcesUrl}/js/register.js"></script>
            
        </body>

    </html>
