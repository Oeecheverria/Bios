<%-- 
    Document   : choose
    Created on : 21-may-2021, 20:30:39
    Author     : Usuario
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de JSTL - choose</title>
    </head>
    <body>
        <h1>Ejemplo de JSTL</h1>
        <h2>choose</h2>
                        <form>
            <p><input type="text" name="valor" /><input type="submit" value="enviar" /></p>
    </form>
        <c:choose>
            <c:when test = "${param.valor  lt 10.0}">
                <p> el valor es menor a 10.</p>
                
            </c:when>
                
            <c:when test = "${param.valor gt 10.0}">
                <p> el valor es mayor a 10.</p>
                
            </c:when>
                
                
            <c:when test = "${param.valor  eq 10.0}">
                <p> el valor es  10.</p>
                
            </c:when>
            
                <c:otherwise>
                     <p> no dio valor.</p>
                
            </c:otherwise>
            
                </c:choose>

    </body>
</html>
