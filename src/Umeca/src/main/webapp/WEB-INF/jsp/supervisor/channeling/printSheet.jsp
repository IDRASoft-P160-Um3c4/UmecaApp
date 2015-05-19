<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Oficio de canalizaci&oacute;n</title>
</head>
<body style="text-align: center">
    <h1>Oficio de canalizaci&oacute;n</h1>
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
        </tbody>
    </table>

</body>
</html>