<%-- 
    Document   : out
    Created on : 21-may-2021, 20:28:39
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de JSTL - out</title>
    </head>
    <body>
        <h1>Ejemplo de JSTL</h1>
        <h2>out</h2>
        
        <form>
            <p><input type="text" name="valor" /><input type="submit" value="enviar" /></p>
        </form>
        
        <p>
            Parametro valor:
            <c:out value="${param.valor}" default="ATENCION! Para probar el ejemplo debe suministrar el parametro valor." />
        </p>
    </body>
</html>
