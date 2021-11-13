<%-- 
    Document   : index
    Created on : 17/11/2013, 07:16:20 PM
    Author     : Raúl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Inicio">
    <jsp:body>
        <c:if test="${not empty usuarioLogueado}">
            <h3>Menú Principal</h3>
            
            <ul>
                <li><a href="empleados">Empleados</a></li>
            </ul>
        </c:if>
        
        <t:mensaje />
        
    </jsp:body>
</t:paginaMaestra>
