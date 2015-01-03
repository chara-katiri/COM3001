<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pinboard Admin</title>

        <c:url value='/resources' var="resourcesUrl" />
        <!-- CSS Files -->
        <!-- External-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap -->

        <!--Internal -->
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/forms.css" />
        <link rel="stylesheet" type="text/css" href="${resourcesUrl}/css/components/itemsList.css" />

        <!-- JavaScript Files -->
        <!--External-->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>

        <!--Internal-->
        <script src="${resourcesUrl}/js/utils.js"></script>
        <script src="${resourcesUrl}/js/components/itemsList.js"></script>
        <script src="${resourcesUrl}/js/admin.js"></script>


        <style>
            .jumbotron {
                padding: 50px;
            }
        </style>
    </head>
    <body>
        <div id="pageContextPath" data-page-context="${pageContext.request.contextPath}"></div>
        <div id="csrfToken" name="${_csrf.parameterName}" data-csrf-token="${_csrf.token}"></div>

        <div role="tabpanel">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs nav-justified" role="tablist" id="tabs">
                <li role="presentation" class="active">
                    <a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a>
                </li>
                <li role="presentation">
                    <a href="#add" aria-controls="add" role="tab" data-toggle="tab">Add Item</a>
                </li>
                <li role="presentation">
                    <a href="#delete" aria-controls="delete" role="tab" data-toggle="tab">Delete Item</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade in active" id="home">
                    <div class="jumbotron">
                        <h1>Welcome <c:out value="${pageContext.request.userPrincipal.name}" /></h1>
                        <p><a class="btn btn-primary btn-lg" href="/Pinboard/index">Back to home</a></p>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="add">
                    <c:url value='/pinboard/manage/add' var="addUrl"></c:url>
                    <form class="form" id="registrationForm" action="${addUrl}" method="POST">
                        <h2 class="form-heading">Add New Item</h2>

                        <input class="form-control" type="text" name="name" placeholder="Item Name" />
                        <input class="form-control" type="text" name="genres" placeholder="Genres (Comma Separated)" />
                        <input class="form-control" type="text" name="startDate" placeholder="Start Date" />
                        <input class="form-control" type="text" name="endDate" placeholder="End Date" />
                        <input class="form-control" type="text" name="location.name" placeholder="Location" />
                        <input class="form-control last-input" type="text" name="website" placeholder="Item specification"
                               data-validation="url" />
                        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Add" />
                        <input class="form-control" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="delete">
                    <div class="itemsList"></div>
                </div>

            </div>

        </div>

        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

    </body>
</html>
