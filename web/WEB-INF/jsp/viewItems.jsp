<!--

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View items</title>
    </head>
    <body>
        <h1>View Items</h1>
        <form:form method="POST" action="/dispatcher/addItem">
   <table>
    <tr>
        <td><form:label path="title">Title</form:label></td>
        <td><form:input path="title" /></td>
    </tr>
    <tr>
        <td><form:label path="description">Description</form:label></td>
        <td><form:input path="description" /></td>
    </tr>
    <tr>
        <td><form:label path="price">Price</form:label></td>
        <td><form:input path="price" /></td>
    </tr>
     <tr>
        <td><form:label path="category">Category</form:label></td>
        <td><form:input path="category" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
        
    </body>
</html> -->
