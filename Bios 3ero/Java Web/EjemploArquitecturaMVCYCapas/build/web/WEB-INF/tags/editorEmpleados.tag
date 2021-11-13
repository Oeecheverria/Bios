<%-- 
    Document   : editorempleados
    Created on : 08/12/2015, 01:50:18 AM
    Author     : Raúl
--%>

<%@tag description="Editor de empleados." pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="deshabilitarClave"%>
<%@attribute name="idFoco" required="true"%>
<%@attribute name="textoBoton" required="true"%>

<%-- any content can be specified here e.g.: --%>
<form method="post" accept-charset="ISO-8859-1">
    <div>
        <label for="cedula">Cédula</label>
        <input type="number" name="cedula" id="cedula" value="${not empty param.cedula ? param.cedula : empleado.cedula}"${deshabilitarClave ? 'readonly="readonly"' : ''} />
    </div>
    <div>
        <label for="nombre">Nombre</label>
        <input type="text" name="nombre" id="nombre" value="${not empty param.nombre ? param.nombre : empleado.nombre}" />
    </div>
    <div>
        <label for="sueldo">Sueldo</label>
        <input type="number" step=".01" name="sueldo" id="sueldo" value="${not empty param.sueldo ? param.sueldo : empleado.sueldo}" />
    </div>
    <div>
        <input type="submit" value="${textoBoton}" />
    </div>
</form>

<script>
    var sueldo = document.getElementById('sueldo');
    sueldo.value = parseFloat(sueldo.value).toFixed(2);
    
    document.getElementById('${idFoco}').focus();
    document.getElementById('${idFoco}').select();
</script>
