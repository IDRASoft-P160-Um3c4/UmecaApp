<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<h4>DATOS DEL CASO</h4>

<b>Nombre del completo del imputado:</b> ${data.name} ${data.lastNameP} ${data.lastNameM}
<br/>
<b>Carpeta de investigación:</b> ${data.idFolder}
<br/>
<h4>DATOS GENERALES DEL IMPUTADO</h4>
<b>Domicilio:</b> ${data.address}
<h4>FUENTES Y MEDIOS DE VERIFICACIÓN UTILIZADAS</h4>
<b>Fuentes:</b>
<br/>
<c:forEach var="current" items="${data.sources}">
    <tr>
        <td>${current}</td>
    </tr>
    <br/>
</c:forEach>
<br/>
<h4>INFORMACIÓN VERIFICADA</h4>
<b>Nombre</b> : ${data.name}
<br/>
<b>Apellido Paterno:</b>  ${data.lastNameP}
<br/>
<b>Apellido Materno:</b> ${data.lastNameM}
<br/>
<br/>
<b>Resultado de verificación:</b>
<br/>
<br/>
<table>
<c:forEach var="section" items="${data.sections}">
    <tr>
        <td>${section.name}</td>
        <td>
    <table>
            <c:forEach var="value" items="${section.values}">
                <tr>
                    <td>${value}</td>
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
<br/>
<h4>VALORACIÓN DE RIESGOS:</h4>
<b>Comentarios:</b>
${data.comment}
<br/>
<b>Respuestas:</b>
<br/>
<c:forEach var="current_1" items="${data.questSel}">
    <tr>
        <td>${current_1}</td>
    </tr>
    <br/>
</c:forEach>
<br/>
</body>
</html>
