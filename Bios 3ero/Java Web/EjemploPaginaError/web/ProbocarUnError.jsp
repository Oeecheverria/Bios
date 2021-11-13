<%-- 
    Document   : ProbocarUnError
    Created on : 21/05/2021, 07:13:18 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de pagina de error- Provocar Un Error</title>
    </head>
    <body
        <%
            if("Provocar Error".equals(request.getParameter("accion"))){
            int resultado = 1 / 0;
            }
            %>
        <h1>Ejemplo de pagina de error- Provocar Un Error</h1>
    
        <form>
            <p><input type = "submit" name="accion" value="Provocar Error" /></p>
        </form>
    
    </body>
</html>
