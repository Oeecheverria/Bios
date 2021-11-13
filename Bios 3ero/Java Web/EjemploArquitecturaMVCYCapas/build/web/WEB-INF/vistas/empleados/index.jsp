<%-- 
    Document   : index
    Created on : 04/12/2015, 12:04:09 AM
    Author     : Raúl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:paginaMaestra titulo="Empleados">
    <jsp:body>
        <fmt:setLocale value="es" />
        
        <form id="formularioBusqueda">
            <p><input type="search" name="buscar" value="${param.buscar}" id="buscar" /> <input type="submit" value="Buscar" /></p>
        </form>
        
        <p><a href="empleados?accion=agregar"><i class="icono icono-agregar"></i></a></p>
        
        <table class="listado">
            <tr>
                <th>CÉDULA</th><th>NOMBRE</th><th>SUELDO</th><th></th>
            </tr>
            
            <c:forEach items="${empleados}" var="empleado">
                <tr>
                    <td class="texto-centro">${empleado.cedula}</td>
                    <td>${empleado.nombre}</td>
                    <td class="texto-derecha">
                        <fmt:formatNumber type="number" pattern="0.00" value="${empleado.sueldo}" />
                    </td>
                    <td>
                        <a href="empleados?accion=ver&cedula=${empleado.cedula}"><i class="icono icono-ver"></i></a>&nbsp;&nbsp;
                        <a href="empleados?accion=modificar&cedula=${empleado.cedula}"><i class="icono icono-modificar"></i></a>&nbsp;&nbsp;
                        <a href="empleados?accion=eliminar&cedula=${empleado.cedula}"><i class="icono icono-eliminar"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        <p><a href="inicio">Volver...</a></p>
        
        <t:mensaje />
        
        <script>
            document.getElementById('formularioBusqueda').addEventListener('search', function() {
                window.location = 'empleados?buscar=' + document.getElementById('buscar').value;
            });
            
            document.getElementById('buscar').focus();
            document.getElementById('buscar').select();
        </script>
    </jsp:body>
</t:paginaMaestra>
