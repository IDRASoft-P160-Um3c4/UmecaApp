<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<h4>DATOS DEL CASO</h4>

<b>Nombre completo del imputado:</b> ${data.nameV} ${data.lastNamePV} ${data.lastNameMV}
<br/>
<b>Carpeta de investigaci&oacute;n:</b> ${data.idFolder}
<br/>
<h4>DATOS GENERALES DEL IMPUTADO</h4>
<b>Domicilio:</b> ${data.addressV}
<h4>FUENTES Y MEDIOS DE VERIFICACI&Oacute;N UTILIZADAS</h4>
<b>Fuentes:</b>
<br/>
<c:forEach var="current" items="${data.sources}">
    <tr>
        <td>${current}</td>
    </tr>
    <br/>
</c:forEach>
<br/>
<h4>INFORMACI&Oacute;N VERIFICADA</h4>
<b>Nombre</b> : ${data.nameV}
<br/>
<b>Apellido Paterno:</b> ${data.lastNamePV}
<br/>
<b>Apellido Materno:</b> ${data.lastNameMV}
<br/>
<br/>
<b>Resultado de verificaci&oacute;n:</b>
<br/>
<br/>
<br/>
<table border="5">
    <c:forEach var="section" items="${data.sections}">
        <tr>
            <td><h4>${section.name}</h4></td>
            <td>
                <table style="width:100%;">
                    <c:forEach var="value" items="${section.values}">
                        <tr>
                                <%--<td>--%>
                                <%--<div style="width:100%;border-width:1px;border-color:blue;border-style:none none double none;">--%>
                                ${value}
                                <%--</div>--%>
                                <%--</td>--%>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <hr>
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
<br/>
<br/>
<br/>

<h4>PROCESOS ANTERIORES:</h4>
<br/>
<p>
    <b>Primer proceso: </b>
    ${data.previousCriminalProceeding.firstProceeding}
</p>
<p>
    <b>N&uacute;mero de procesos abiertos: </b>
    ${data.previousCriminalProceeding.openProcessNumber}</p>
<p>
    <b>N&uacute;mero de sentencias condenatorias: </b>
    ${data.previousCriminalProceeding.numberConvictions}</p>
<p>
    <b>&iquest;Cumpli&oacute; con medidas cautelares?: </b>
    ${data.previousCriminalProceeding.complyPM.name}</p>
<p>
    <b>&iquest;Cumpli&oacute; con SCPP?: </b>
    ${data.previousCriminalProceeding.complyCSPP.name}</p>
<p>
    <b>&iquest;Permiti&oacute; o colabor&oacute; con procesos anteriores?: </b>
    ${data.previousCriminalProceeding.complyProcessAbove.name}</p>
<p>
    <b>Rubro de orden de aprehensi&oacute;n: </b>
    ${data.previousCriminalProceeding.warrant}</p>
<p>
    <b>Rubro de de plataforma M&eacute;xico: </b>
    ${data.previousCriminalProceeding.platformMexico}</p>
<p>
    <b>Rubro de AFIS: </b>
    ${data.previousCriminalProceeding.afis}</p>


<br/>
<br/>
<br/>
<h4>VALORACI&Oacute;N DE RIESGOS:</h4>
<br/>
<b>Comentarios:</b>
${data.comment}
<br/>
<br/>
<b>V&iacute;nculos comunitarios:</b>
<br/>
<c:forEach var="current_1" items="${data.questLinks}">
    <tr>
        <td>${current_1}</td>
    </tr>
    <br/>
</c:forEach>
<br/>
<br/>
<b>Riesgos procesales:</b>
<br/>
<c:forEach var="current_2" items="${data.questRisk}">
    <tr>
        <td>${current_2}</td>
    </tr>
    <br/>
</c:forEach>
<br/>
<br/>
<b>Resultado de valoraci&oacute;n</b>
<br/>
${data.result}
<br/>
</body>
</html>
