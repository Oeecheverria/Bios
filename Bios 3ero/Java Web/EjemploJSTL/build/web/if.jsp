<%-- 
    Document   : if
    Created on : 21-may-2021, 20:30:20
    Author     : Usuario
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de JSTL - if</title>
    </head>
    <body>
        <h1>Ejemplo de JSTL</h1>
        <h2>if</h2>
                <form>
            <p><input type="text" name="valor" /><input type="submit" value="enviar" /></p>
        </form>
    <c:if test ="${param.valor >= 100}" >
        <p> epa no hay valor asi </p>
    </c:if>
    </body>
</html>
