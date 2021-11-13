<%-- 
    Document   : Error
    Created on : 21/05/2021, 07:04:29 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage = "true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>Ejemplo de PAgina de error  - Error</title>
    </head>
    <body style ="background-color: red; color:white; ">
        <h1>Error!</h1>
        
        <p> Se ha producido el siguiente error: </p>
        <p>${pageContext.errorData.throwable} </p>
    </body>
</html>
