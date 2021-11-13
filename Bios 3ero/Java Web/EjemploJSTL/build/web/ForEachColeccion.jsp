<%-- 
    Document   : ForEachColeccion
    Created on : 21-may-2021, 20:31:32
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>





<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de JSTL - ForEachColeccion</title>
    </head>
    <body>
        <h1>Ejemplo de JSTL</h1>
        <h2>ForEachColeccion</h2>
        
        
        <c:set var="dias" scope="page" value ="${fn:split('lunes,martes,miercoles,jueves,viernes,sabado,domingo',',')}"  />
        
        <p>
            <c:forEach items  = "${dias}" var = "dia">
                ${dia} <br />
                             
            </c:forEach>
            
            
        </p>
    </body>
</html>
