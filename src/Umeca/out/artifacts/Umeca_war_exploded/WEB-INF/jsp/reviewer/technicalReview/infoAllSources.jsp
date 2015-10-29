<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title></title>
</head>
<body>

<h4>INFORMACI&Oacute;N RECAUDADA</h4>

<b>Nombre del completo del imputado:</b> ${data.name} ${data.lastNameP} ${data.lastNameM}
<br/>
<b>Carpeta de investigaci&oacute;n:</b> ${data.idFolder}
<br/>
<b>Domicilio:</b> ${data.address}
<br/>
<br/>
<h4>Fuentes autorizadas para la verificaci&oacute;n</h4>

<c:forEach var="source" items="${data.sources}">
    <b>Datos de la fuente:</b>
<blockquote>
<table>
        <tbody>
        <tr style="color: darkslateblue;">
            <td>Nombre: ${source.fullName}</td>
        </tr>
        <tr style="color: darkslateblue;">
            <td>Relaci&oacute;n: ${source.relationship}</td>
        </tr>
        <tr style="color: darkslateblue;">
            <td>Direcci&oacute;n: ${source.address}</td>
        </tr>
        <tr style="color: darkslateblue;">
            <td>Edad: ${source.age}</td>
        </tr>
        <tr style="color: darkslateblue;">
            <td>Tel&eacute;fono: ${source.phone}</td>
        </tr>
        </tbody>
    </table></blockquote>
    <br/>
    <c:if test="${fn:length(source.sections) gt 0}">
    <table border="1">
        <c:forEach var="section" items="${source.sections}">
            <tr>
                <td>${section.name}</td>
                <td>
                    <table style="width:100%;">
                        <c:forEach var="value" items="${section.values}">
                            <tr>
                                <td>
                                    <div style="width:100%;border-width:1px;border-color:blue;border-style:none none double none;">
                                            ${value}
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td><br/><br/></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
    </c:if><c:if test="${fn:length(source.sections) eq 0}">
<blockquote> <b>No proporcion&oacute; informaci&oacute;n</b></blockquote>
    <br/><br/><br/>
    </c:if>
</c:forEach>
<br/>
<br/>
</body>
</html>
