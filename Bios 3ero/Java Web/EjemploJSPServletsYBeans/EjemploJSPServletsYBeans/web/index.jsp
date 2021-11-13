<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de JSP, Servlets y Beans</title>
        <link rel="stylesheet" href="css/estilos.css" />
    </head>
    <body>
        <jsp:useBean id="producto" class="beans.Producto" scope="session">
            <jsp:setProperty name="producto" property="codigo" value="1" />
            <jsp:setProperty name="producto" property="descripcion" value="Tablet Google Nexus 7" />
            <jsp:setProperty name="producto" property="precio" value="300" />
        </jsp:useBean>
        
        <h1>Ejemplo de JSP, Servlets y Beans</h1>
        
        <h2>Producto Actual:</h2>
        
        <p>Código: <jsp:getProperty name="producto" property="codigo" /></p>
        <p>Descripción: <jsp:getProperty name="producto" property="descripcion" /></p>
        <p>Precio: <jsp:getProperty name="producto" property="precio" /></p>
        
        <h2>Cambiar Por:</h2>
        
        <form action="CambiarProducto" method="post" accept-charset="ISO-8859-1">
            <div>
                <label for="codigo">Código:</label>
                <input type="text" name="codigo" id="codigo" />
            </div>
            <div>
                <label for="descripcion">Descripción:</label>
                <input type="text" name="descripcion" id="descripcion" />
            </div>
            <div>
                <label for="precio">Precio:</label>
                <input type="text" name="precio" id="precio" />
            </div>
            <div>
                <input type="submit" name="accion" value="Cambiar Producto" />
            </div>
        </form>
    </body>
</html>