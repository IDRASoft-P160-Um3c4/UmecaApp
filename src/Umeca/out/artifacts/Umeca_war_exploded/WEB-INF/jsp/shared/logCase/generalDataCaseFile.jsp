<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
</head>
<body>

<table class="tableInfo">
    <tr>
        <td class="labelInfo">IMPUTADO</td>
        <td class="fieldInfo">${imputedName}</td>
    </tr>
    <tr>
        <td class="labelInfo">JUEZ</td>
        <td class="fieldInfo">${judge == ''? "Sin registar":jugde}</td>
    </tr>
    <tr>
        <td class="labelInfo">DEFENSOR</td>
        <td class="fieldInfo">${defender == null? "Sin registar":defender}</td>
    </tr>
    <tr>
        <td class="labelInfo">MINISTERIO P&Uacute;BLICO</td>
        <td class="fieldInfo">${mp == null? "Sin registar":mp}</td>
    </tr>
    <tr>
        <td class="labelInfo">DELITO</td>
        <td class="fieldInfo">
            <c:choose>
                <c:when test="${crime == null}">
                    Sin registrar
                </c:when>
                <c:otherwise>
                    <ul>
                        <c:forEach var="c" items="${crime}">
                            <li>${c}</li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td class="labelInfo">TEL&Eacute;FONO DEL IMPUTADO</td>
        <td class="fieldInfo">${imputedTel == null? "Sin registar":imputedTel}</td>
    </tr>
    <tr>
        <td class="labelInfo">DOMICILIO DEL IMPUTADO</td>
        <td class="fieldInfo">${imputedAddr == null? "Sin registar":imputedAddr}</td>
    </tr>
    <tr>
        <td class="labelInfo">APOYO MORAL</td>
        <td class="fieldInfo">${moralName == null? "Sin registar":moralName}</td>
    </tr>
    <tr>
        <td class="labelInfo">TODAS LAS RESOLUCIONES</td>
        <td class="fieldInfo">${prevResolution == null? "Sin registar":prevResolution}</td>
    </tr>
    <tr>
        <td class="labelInfo">RESOLUCI&Oacute;N ACTUAL</td>
        <td class="fieldInfo">${lastResolution == null? "Sin registar":lastResolution}</td>
    </tr>
    <tr>
        <td class="labelInfo">TIPO DE MEDIDA JUDICIAL</td>
        <td class="fieldInfo">
            <c:choose>
                <c:when test="${lstHfAssignedArrangement == null}">
                    Sin registrar
                </c:when>
                <c:otherwise>
                    <ul>
                        <c:forEach var="hfaa" items="${lstHfAssignedArrangement}">
                            <li>${hfaa.name}/${hfaa.description}</li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td class="labelInfo">COMENTARIO DE CIERRE</td>
        <td class="fieldInfo">${closeComment == null? "N/A":closeComment}</td>
    </tr>
    <tr>
        <td class="labelInfo">ACTIVIDADES DE SUPERVISI&Oacute;N</td>
        <td class="fieldInfo">
            <c:choose>
                <c:when test="${lstActivities == null}">
                    Sin registrar
                </c:when>
                <c:otherwise>
                    <ul>
                        <c:forEach var="hfaa" items="${lstActivities}">
                            <li>${hfaa.name}</li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>

    <tr>
        <td class="labelInfo">OBJETIVOS DE LA ACTIVIDAD DE SUPERVSI&Oacute;N</td>
        <td class="fieldInfo">
            <c:choose>
                <c:when test="${lstGoals == null}">
                    Sin registrar
                </c:when>
                <c:otherwise>
                    <ul>
                        <c:forEach var="hfaa" items="${lstGoals}">
                            <li>${hfaa.name}</li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td class="labelInfo">DISPONIBILIDAD</td>
        <td class="fieldInfo"> <c:choose>
            <c:when test="${schedules == null}">
                Sin registrar
            </c:when>
            <c:otherwise>
                <c:forEach var="sch" items="${schedules}">
                    - ${sch.name}
                    <ul>
                        <c:forEach var="sd" items="${sch.sch}">
                            <li>${sd.day} &nbsp;&nbsp;&nbsp;&nbsp;${sd.start}-${sd.end}</li>
                        </c:forEach>
                    </ul>
                </c:forEach>
            </c:otherwise>
        </c:choose></td>
    </tr>
    <tr>
        <td class="labelInfo">RIESGOS</td>
        <td class="fieldInfo">
            <c:choose>
                <c:when test="${lstRisk == null}">
                    Sin registrar
                </c:when>
                <c:otherwise>
                    <ul>
                        <c:forEach var="rsk" items="${lstRisk}">
                            <li>${rsk.name}</li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <td class="labelInfo">AMENAZAS</td>
        <td class="fieldInfo">
            <c:choose>
                <c:when test="${lstThreat == null}">
                    Sin registrar
                </c:when>
                <c:otherwise>
                    <ul>
                        <c:forEach var="thr" items="${lstThreat}">
                            <li>${thr.name}</li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </td class="fieldInfo">
    </tr>

</table>
</body>
</html>
