<%-- 
    Document   : forEachPaso
    Created on : 21-may-2021, 20:31:18
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de JSTL - forEachPaso</title>
    </head>
    <body>
        <h1>Ejemplo de JSTL</h1>
        <h2>forEachPaso</h2>
               <form>
            <p><input type="text" name="valor" /><input type="submit" value="enviar" /></p>
            
            <p>
                <c:forEach begin="1" end = "${param.valor}"  step ="2"
                           var = "vuelta">
                    Vuelta N° ${vuelta} <br />
                </c:forEach>
                
                
                
            </p>        
            
            
    </body>
</html>
