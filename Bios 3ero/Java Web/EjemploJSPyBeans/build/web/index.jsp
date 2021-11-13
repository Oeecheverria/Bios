<%-- 
    Document   : index
    Created on : 24/05/2021, 08:28:31 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo JSP y beans</title>
        
        <link href="../CSS/estilos.css" rel="stylesheet" type="text/css"/>
        
        
    </head>
    <body>
        <h1>Ejemplo JSP y beans!</h1>
        
        <jsp:useBean id = "producto" class ="beans.Producto" scope = "session">
            <jsp:setProperty name ="producto" property ="codigo" value ="1" />
              <jsp:setProperty name ="producto" property ="descripcion" value ="Tablet google nexus 7" />
              <jsp:setProperty name ="producto" property ="precio" value ="200" />                 
        
        </jsp:useBean >
        
        
        <h2>Producto actual: </h2>
        <p>Codigo:<jsp:getProperty name = "producto" property="codigo" /><p>
        <p>Descripción:<jsp:getProperty name = "producto" property="descripcion" /></p>
        <p>Precio: <jsp:getProperty name = "producto" property="precio" /></p>
    
        <form action ="CambiarProducto.jsp" method ="post" accept-charset ="ISO-8859-1">
            <div>
                <label for ="codigo">Codigo:</label>
                <input type ="text" name="codigo" id ="codigo" />
            </div>
              <div>
                <label for ="descripcion">Descripción:</label>
                <input type ="text" name="descripcion" id ="descripcion" />
            </div>
              <div>
                <label for ="precio">Precio:</label>
                <input type ="text" name="precio" id ="precio" />
            </div>
             <div>
                <input type ="submit" name="accion" value ="Cambiar Producto" />
            </div>
        </form>
        </body>
</html>
