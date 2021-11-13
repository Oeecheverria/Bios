<%-- 
    Document   : index
    Created on : 19/05/2021, 07:22:40 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%!
   private String accion,nombre; 


    private String finDocumento(){
        return"Fin del documento";

}

%>
    


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saludo en JSP</title>
    </head>
    <body>
        
        <%
            accion = request.getParameter("accion");
            nombre =request.getParameter("nombre");
            
               if(session.getAttribute("primerMensaje") == null)
               {
                   session.setAttribute("primerMensaje","Bienvenido al java web");
                   session.setAttribute("segundoMensaje","gozate");
                   
               }     
        %>
        <jsp:include page = "Titulo">
            <jsp:param name="subtitulo" value = "S" />
  
            
        </jsp:include>
        
        <form>
            <p>
                <label for ="nombre">Escribe tu nombre:</label>
                <input type ="text" name="nombre" id ="nombre" />
                <input type ="submit" name="accion" value ="Saludar" />
                
                
            </p>
            
            
        </form>
        
        
            <%  if("Saludar".equals(accion) && nombre != null & nombre.length() > 0){ %>
                    <p>Hola <%= nombre %> </p>
                    
                    <p>Chau ${param.nombre}!</p>
                    
             <%   }  %>

       
        
             <p><%= session.getAttribute("primerMensaje") %> </p>
             
              <p> ${segundoMensaje}!</p>
             
        <p><%=finDocumento() %> </p>
    </body>
</html>
