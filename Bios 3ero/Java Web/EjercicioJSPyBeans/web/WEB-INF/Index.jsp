<%-- 
    Document   : Index
    Created on : 26/05/2021, 07:29:31 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejercicio de JSP y beans</title>
    </head>
    <body>
        <h1>Ejercicio de JSP y beans</h1>
        
        
        <jsp:useBean id = "rotadorMensajes" class = "beans.RotadorMensajes" scope = "session" />
        
        <p><jsp:getProperty name ="rotadorMensajes" property = "mensaje" /></p>
        
    </body>
</html>
