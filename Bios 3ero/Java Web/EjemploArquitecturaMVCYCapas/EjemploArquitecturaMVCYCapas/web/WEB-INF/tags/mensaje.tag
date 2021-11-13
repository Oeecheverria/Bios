<%-- 
    Document   : mensaje
    Created on : 10/06/2017, 06:51:30 PM
    Author     : Raul
--%>

<%@tag description="Párrafo de mensaje." pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>
<c:if test="${not empty sessionScope.mensaje}">
    <p class="mensaje <c:if test="${fn:contains(sessionScope.mensaje, '¡ERROR!')}">error</c:if>">${sessionScope.mensaje}</p>
    
    <c:remove scope="session" var="mensaje" />
</c:if>
<c:if test="${not empty requestScope.mensaje}">
    <p class="mensaje <c:if test="${fn:contains(requestScope.mensaje, '¡ERROR!')}">error</c:if>">${requestScope.mensaje}</p>
</c:if>