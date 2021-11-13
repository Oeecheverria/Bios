<%-- 
    Document   : PaginaProhibida
    Created on : 21/05/2021, 08:10:16 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo dePAgina de Error - Pagina Prohibida</title>
        
    </head>
    <body>
        <%
            
            if(!("admin".equals(request.getParameter("usuario")) && "admin".equals(request.getParameter("contrasena"))))
            {response.sendError(403);
            }
            %>
        
        
        <h1>Ejemplo dePAgina de Error - Pagina Prohibida</h1>
    
        <p>esta pagina esta prohibida al publico</p>
    </body>
</html>
