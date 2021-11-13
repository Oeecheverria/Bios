<%-- 
    Document   : PaginaMaestra
    Created on : 06/12/2015, 01:06:19 PM
    Author     : Raúl
--%>

<%@tag description="Página maestra del sitio." pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="titulo" %>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        
        <title>Ejemplo de Arquitectura MVC y Capas - ${titulo}</title>
        
        <link rel="stylesheet" href="css/estilos.css" />
    </head>
    <body>
        <div class="cabezal">
            <h1>Ejemplo de Arquitectura MVC y Capas</h1>
            
            <h2>${titulo}</h2>
            
            <c:choose>
                <c:when test="${empty usuarioLogueado}">
                    <form action="inicio" method="post">
                        <p>
                            <label for="nombreUsuario">Usuario:</label>
                            <input type="text" name="nombreUsuario" id="nombreUsuario" value="${param.nombreUsuario}" />
                            
                            <label for="claveAcceso">Clave:</label>
                            <input type="password" name="claveAcceso" id="claveAcceso" value="${param.claveAcceso}" />
                            
                            <input type="hidden" name="accion" value="ingresar" />
                            
                            <input type="submit" value="Ingresar" />
                        </p>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="inicio" method="post">
                        <p>
                            Usuario: <strong>${usuarioLogueado.nombreUsuario}</strong>
                            
                            <input type="hidden" name="accion" value="salir" />
                            
                            <input type="submit" value="Salir" />
                        </p>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
        
        <jsp:doBody />
        
        <script src="js/scripts.js"></script>
    </body>
</html>
