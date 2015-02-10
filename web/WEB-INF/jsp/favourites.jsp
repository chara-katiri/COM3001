<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favourites</title>

        <c:url value='/resources' var="resourcesUrl" />
        <!-- External CSS Files-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal CSS Files-->        
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/components/itemsList.css" />
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/favourites.css" />

        <!--External JavaScript Files-->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- JQuery -->

        <!--Internal JavaScript Files-->
        <script src="${resourcesUrl}/js/utils.js"></script>
        <script src="${resourcesUrl}/js/components/itemsList.js"></script>
        <script src="${resourcesUrl}/js/favourites.js"></script>

    </head>
    <body>
        <div id="pageContextPath" data-page-context="${pageContext.request.contextPath}"></div>
        <div class="authorizeUser">
            <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
                <div class="toggleFavourite"></div>
            </sec:authorize> 
        </div>

        <h1>Favourites</h1>

        <div class="itemsList"></div>

        
        <!--        Functionality to allow the user navigate back to Home page-->
        <p><a class="btn btn-primary btn-lg back-to-home" href="/Pinboard/index">Back to home</a></p>
    </body>
</html>