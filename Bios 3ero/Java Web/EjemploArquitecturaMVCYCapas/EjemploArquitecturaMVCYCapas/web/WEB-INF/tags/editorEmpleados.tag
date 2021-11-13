<%-- 
    Document   : editorempleados
    Created on : 08/12/2015, 01:50:18 AM
    Author     : Raúl
--%>

<%@tag description="Editor de empleados." pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="deshabilitarClave"%>
<%@attribute name="idFoco" required="true"%>
<%@attribute name="textoBoton" required="true"%>

<%-- any content can be specified here e.g.: --%>
<fmt:setLocale value="en-US" />

<form method="post" accept-charset="ISO-8859-1">
    <div>
        <label for="cedula">Cédula:</label>
        <c:choose>
            <c:when test="${deshabilitarClave}">
                <input type="text" name="cedula" id="cedula" value="${not empty empleado ? empleado.cedula : param.cedula}" readonly="readonly" />
            </c:when>
            <c:otherwise>
                <input type="text" name="cedula" id="cedula" value="${not empty empleado ? empleado.cedula : param.cedula}" />
            </c:otherwise>
        </c:choose>
    </div>
    <div>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" value="${not empty empleado ? empleado.nombre : param.nombre}" />
    </div>
    
    <c:set var="sueldo" scope="page" value="${not empty empleado ? empleado.sueldo : param.sueldo}" />
    
    <c:catch>
        <fmt:formatNumber type="number" pattern="0.00" value="${sueldo}" var="sueldo" scope="page" />
    </c:catch>
    
    <div>
        <label for="sueldo">Sueldo:</label>
        <input type="text" name="sueldo" id="sueldo" value="${sueldo}" />
    </div>
    <div>
        <input type="submit" value="${textoBoton}" />
    </div>
</form>

<script>
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>
