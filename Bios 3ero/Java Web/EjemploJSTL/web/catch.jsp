<%-- 
    Document   : catch
    Created on : 21-may-2021, 20:32:02
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de JSTL - catch</title>
    </head>
    <body>
        <h1>Ejemplo de JSTL</h1>
        <h2>catch</h2>
      
        
               <form>
            <p><input type="text" name="valor" /><input type="submit" value="enviar" /></p>
                </form>
            
        <c:catch var= "ex">
            
            <c:if test ="${param.valor ge 100 }">
                
                <p> epa el valor es grande</p>
            </c:if>
            
            
            
        </c:catch>
           <c:if test ="${ex != null}">
               <p>
                   
                   erro no anda porque $(ex)
                   
               </p>
                 <p>
                   
                     otra mierda ${ex.message}</p>
                   
               </p>
                
    </c:if >
                
            
            
        
    </body>
</html>
