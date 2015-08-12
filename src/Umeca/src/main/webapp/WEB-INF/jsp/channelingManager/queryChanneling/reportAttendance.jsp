<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actividades de canalizaci&oacute;n</title>

    <style>
        .bord {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 10px;
        }

        .bordt {
            font-size: 0.95em;
            border: 1px solid black;
            border-collapse: collapse;
            padding: 4px;
            font-weight: normal;
        }
    </style>

</head>
<body style="text-align: center">
    <h1>Actividades de canalizaci&oacute;n</h1>
    <br>
    <br>
    <h3 style="border: 1px solid black">Informaci&oacute;n personal del imputado</h3>
    <table style="text-align: center; margin: auto; font-size: medium; ">
        <tbody>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">Nombre:</td>
            <td style="min-width: 250px; text-align: left;">${data.imputed}</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">Fecha de nacimiento:</td>
            <td style="min-width: 250px; text-align: left;">${data.birthdayTx}</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">G&eacute;nero:</td>
            <td style="min-width: 250px; text-align: left;">${data.gender}</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">Tel&eacute;fono:</td>
            <td style="min-width: 250px; text-align: left;">${data.phone}</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">Causa penal:</td>
            <td style="min-width: 250px; text-align: left;">${data.idMP}</td>
        </tr>
        </tbody>
    </table>

    <br>
    <br>
    <h3 style="border: 1px solid black">Informaci&oacute;n de la canalizaci&oacute;n</h3>
    <table style="text-align: center; margin: auto; font-size: medium; ">
        <tbody>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">N&uacute;mero de canalizaci&oacute;n:</td>
            <td style="min-width: 250px; text-align: left;">${data.consecutiveTx}</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">&iquest;Canalizaci&oacute;n voluntaria?</td>
            <td style="min-width: 250px; text-align: left;">${(data.isVolunteer ? "Si" : "No") }</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">Tipo de canalizaci&oacute;n:</td>
            <td style="min-width: 250px; text-align: left;">${data.channelingType}</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">Nombre de la canalizaci&oacute;n:</td>
            <td style="min-width: 250px; text-align: left;">${data.name}</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">Tipo de la instituci&oacute;n:</td>
            <td style="min-width: 250px; text-align: left;">${data.institutionType}</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">Nombre de la instituci&oacute;n:</td>
            <td style="min-width: 250px; text-align: left;">${data.institutionName}</td>
        </tr>
        <tr>
            <td style="font-weight: bolder; min-width: 250px; text-align: left;">Imposici&oacute;n cumplida:</td>
            <td style="min-width: 250px; text-align: left;">${data.isFulFilledTx}</td>
        </tr>
        </tbody>
    </table>

    <br>
    <br>
    <h3 style="border: 1px solid black">Actividades registradas de la canalizaci&oacute;n</h3>
    <table style="text-align: center; margin: auto; font-size: 0.9em;" class="bord" >
        <thead>
        <tr class="bord">
            <td style="font-weight: bolder; width: 120px; text-align: center;" class="bord">
                Fecha inicio
            </td>
            <td style="font-weight: bolder; width: 120px; text-align: center;" class="bord">
                Fecha final
            </td>
            <td style="font-weight: bolder; width: 130px; text-align: center;" class="bord">
                Objetivo de la actividad
            </td>
            <td style="font-weight: bolder; width: 120px; text-align: center;" class="bord">
                &iquest;Asisti&oacute;?
            </td>
            <td style="font-weight: bolder; width: 80px; text-align: center;" class="bord">
                &iquest;Justificada?
            </td>
            <td style="font-weight: bolder; width: 250px; text-align: center;" class="bord">
                Comentarios de la justificaci&oacute;n
            </td>
            <td style="font-weight: bolder; width: 80px; text-align: center;" class="bord">
                &iquest;Reagendada?
            </td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lstActAtt}" var="act">
            <tr class="bordt">
                <td class="bordt"><c:out value="${act.startTx}"/></td>
                <td class="bordt"><c:out value="${act.endTx}"/></td>
                <td class="bordt"><c:out value="${act.activityGoal}"/></td>
                <td class="bordt"><c:out value='${(act.attendance == -1 ? "Por registrar resultado" : (act.attendance == 0 ? "No" : "Si"))}'/></td>
                <td class="bordt"><c:out value="${act.hasJustification}"/></td>
                <td class="bordt"><c:out value="${act.commentsJustify}"/></td>
                <td class="bordt"><c:out value="${act.hasReschedule}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>