<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title></title>
    <style>
        .sumField {
            padding-left: 1%;
            padding-right: 1%;
            padding-top: 1%;
            padding-bottom: 1%;
        }
    </style>
</head>
<body>
<div align="right">
    <table width="10%">
        <tbody>
        <tr>
            <td>
                <%--<input type="button"--%>
                <%--onclick="window.doReturn();"--%>
                <%--value="Regresar"--%>
                <%--style="font-size: 15px">--%>
            </td>
            <td/>
            <td>
                <input type="button"
                       onclick="window.exportDoc('${summary.minuteDto.title}');"
                       value="Exportar a Word"
                       style="font-size: 15px">
            </td>
        </tr>
        </tbody>
    </table>

</div>
<div id="page-content">

    <div align="center">
        <h1>Minuta ${summary.minuteDto.title}</h1>
    </div>
    <br/>
    <br/>

    <div>
        <table width="100%">
            <tbody>
            <tr width="100%">
                <th style="vertical-align: bottom;">
                    <h2>Reuni&oacute;n UMECA Adultos, Direcci&oacute;n y Coordinadores de &Aacute;rea</h2>
                </th>
            </tr>
            </tbody>
        </table>
    </div>

    <div align="center" style="width: 100%">
        <table style="border: 1px solid royalblue; width: 60%">
            <thead style="padding: 1%">
            <th colspan="4" align="left" style="background-color: royalblue;">
                <h4>IFORMACI&Oacute;N GENERAL</h4>
            </th>

            </thead>
            <tbody>
            <tr>
                <td class="sumField">
                    <strong>Reuni&oacute;n de fecha: </strong> ${summary.minuteDto.minuteDate}

                </td>
                <td/>
                <td class="sumField">
                    <strong>Hora:</strong> ${summary.minuteDto.startTime}

                </td>
            </tr>
            <tr/>
            <tr>
                <td class="sumField">
                    <strong>Lugar:</strong> ${summary.minuteDto.place}
                </td>
                <td/>
                <td class="sumField" style="align-content:center; width: 60%;word-break:break-all;">
                    <strong>Orden del d&iacute;a:</strong> ${summary.minuteDto.agenda}
                </td>
            </tr>
            </tbody>
        </table>
        <br/>
        <table style="border: 1px solid royalblue; width: 60%">
            <thead style="padding: 1%">
            <th colspan="4" align="left" style="background-color: royalblue;">
                <h4>ACUERDOS</h4>
            </th>

            </thead>
            <tbody>

            <tr>
                <td colspan="3" align="center"
                    style="padding-left: 2%;padding-right:2%;padding-top: 2%;">
                    <h4><strong>Objetivos de la reuni&oacute;n</strong></h4>
                </td>
            </tr>

            <tr>
                <td align="center">
                    <b>&Aacute;rea</b>
                </td>
                <td align="center">
                    <b>Acuerdo</b>
                </td>
                <td align="center">
                    <b>Temas</b>
                </td>
            </tr>

            <c:forEach var="agr" items="${summary.agreements}">
                <tr>
                    <td align="center">${agr.area}</td>
                    <td align="center">${agr.title}</td>
                    <td style="align-content:center; width: 40%;word-break:break-all;">
                            ${agr.theme}
                    </td>
                </tr>
                <br/>
            </c:forEach>

            </tbody>
        </table>
        <br/>
        <table style="border: 1px solid royalblue; width: 60%">
            <thead style="padding: 1%">
            <th colspan="3" align="left" style="background-color: royalblue;">
                <h4>ASISTENTES</h4>
            </th>
            </thead>
            <tbody>
            <tr>
                <td colspan="2" align="center">
                    Nombre
                </td>
                <td align="center">
                    Firma
                </td>
            </tr>
            <c:forEach var="assistant" items="${summary.assistants}">
                <tr>
                    <td colspan="2" align="center">
                        <br/>
                            ${assistant.name}
                    </td>
                    <td align="center">
                        <br/>
                        ___________________________
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <br/>
    </div>

    <br/>
</div>
<div align="right">
    <table width="10%">
        <tbody>
        <tr>
            <td>
                <%--<input type="button"--%>
                <%--onclick="window.doReturn();"--%>
                <%--value="Regresar"--%>
                <%--style="font-size: 15px">--%>
            </td>
            <td/>
            <td>
                <input type="button"
                       onclick="window.exportDoc('${summary.minuteDto.title}');"
                       value="Exportar a Word"
                       style="font-size: 15px">
            </td>
        </tr>
        </tbody>
    </table>

</div>

</div>


</body>
</html>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/FileSaver.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/jquery.wordexport.js"></script>

<script type="text/javascript">
    exportDoc = function (name) {
        jQuery(document).ready(function ($) {
            $("#page-content").wordExport(name);
        });
    };
</script>



