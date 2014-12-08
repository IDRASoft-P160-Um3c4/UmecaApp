<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <style>
        .labelInfo{
            color: whitesmoke;
            text-align: right;
            background-color: #428BCA;
            min-height: 40px;
            border-radius: 20px;
        }
        .detailsShow{
            width:100%;
            border-width:1px;
            border-color: #428BCA;
            border-style:none none solid none;
            text-align: center;
            color: #003956;
        }
        h1{
            color: #428BCA;
            font-family: "Verdana";
            text-align: center;
        }
        h4{
            color: #003956;
            font-family: "Verdana";
        }
        table{
            font-family: "Verdana";
            font-size: x-small;
        }

        table th{
            color: whitesmoke;
            text-align: center;
            background-color: #428BCA;
            min-height: 40px;
            border-radius: 20px;
        }
        .fieldInfo{
            border-bottom: 1px solid #428BCA;
        }
        td{
            padding: 5px 5px 5px 5px;
        }
    </style>
</head>
<body>
<h1>Reporte de Incumplimiento/Cumplimiento</h1>
<h4>DATOS DEL CASO</h4>
<%@ include file="/WEB-INF/jsp/shared/logCase/generalDataCaseFile.jsp"%>
<br/>
<table border="1">
    <tr class="labelInfo">
        <th>
            Fuentes y medios de verifiación utilizados
        </th>
    </tr>
    <tr>
        <td style="text-align: center;">
            <ul/>
    <c:forEach var="src" items="${lstSources}">
                 <li>${src.name}</li>
    </c:forEach>
            </ul>
        </td>
    </tr>

</table>
<br/>
<h4>Medidas inclumplidas / Condiciones incumplidas</h4>
<table border="1">
    <tr class="labelInfo">
        <th>
            Fecha y hora
        </th>
        <th>
            Descripci&oacute;n
        </th>
        <th>
            Fuente de contacto y dato de localizaci&oacute;
        </th>
        <th>
            Obligaciones procesales
        </th>
    </tr>
    <c:forEach var="act" items="${lstActivityOk}">
        <tr>
            <td style="text-align: center;">
                Inicio: ${act.start}<br/>
                Fin: ${act.end}
            </td style="text-align: center;">
            <td>
                    ${act.supActivity}
            </td>
            <td style="text-align: center;">
                    ${act.aidSource}
            </td>
            <td style="text-align: left;">
        <c:forEach var="aa" items="${act.lstAssignedArrangements}">
            ${aa}<br/>
        </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<h4>Medidas cumplidad / Condiciones cumplidas</h4>
<table border="1">
    <tr class="labelInfo">
        <th>
            Fecha y hora
        </th>
        <th>
            Descripci&oacute;n
        </th>
        <th>
            Fuente de contacto y dato de localizaci&oacute;
        </th>
        <th>
            Obligaciones procesales
        </th>
    </tr>
    <c:forEach var="act" items="${lstActivityFailed}">
        <tr>
            <td style="text-align: center;">
                Inicio: ${act.start}<br/>
                Fin: ${act.end}
            </td style="text-align: center;">
            <td>
                    ${act.supActivity}
            </td>
            <td style="text-align: center;">
                    ${act.aidSource}
            </td>
            <td style="text-align: left;">
        <c:forEach var="aa" items="${act.lstAssignedArrangements}">
            ${aa}<br/>
        </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>




</body>
</html>
