<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title></title>
</head>
<body>
<div align="center">
    <h1>EXPEDIENTE DIGITAL</h1>
</div>
<br/>
<br/>

<div>
    <%--<img src="${pageContext.request.contextPath}/${pathPhoto}">--%>
    <img src="${pageContext.request.contextPath}/${pathPhoto == null ?'assets/avatars/user.png':pathPhoto}"/>
</div>

<div align="center">
    <table style="border: 1px solid royalblue">
        <thead style="padding: 1%">
        <th colspan="4" align="left" style="background-color: royalblue;">
            <h3>Datos generales</h3>
        </th>

        </thead>
        <tbody>
        <tr>
            <td>
                <strong>Nombre:</strong> ${summary.generalData.name} ${summary.generalData.lastNameP} ${summary.generalData.lastNameM}
            </td>
            <td/>
            <td>
                <strong>Fecha de nacimiento:</strong> ${summary.generalData.birthDate}
            </td>
        </tr>
        <tr/>
        <tr>
            <td>
                <strong>Nombre:</strong> ${summary.generalData.name} ${summary.generalData.lastNameP} ${summary.generalData.lastNameM}
            </td>
            <td/>
            <td>
                <strong>Fecha de nacimiento:</strong> ${summary.generalData.birthDate}
            </td>
        </tr>
        </tbody>
    </table>
    <br/>
</div>

<%--<c:forEach var="source" items="${data.sources}">--%>
<%--<b>Datos de la fuente:</b>--%>
<%--<blockquote>--%>
<%--<table>--%>
<%--<tbody>--%>
<%--<tr style="color: darkslateblue;">--%>
<%--<td>Nombre: ${source.fullName}</td>--%>
<%--</tr>--%>
<%--<tr style="color: darkslateblue;">--%>
<%--<td>Relaci&oacute;n: ${source.relationship}</td>--%>
<%--</tr>--%>
<%--<tr style="color: darkslateblue;">--%>
<%--<td>Direcci&oacute;n: ${source.address}</td>--%>
<%--</tr>--%>
<%--<tr style="color: darkslateblue;">--%>
<%--<td>Edad: ${source.age}</td>--%>
<%--</tr>--%>
<%--<tr style="color: darkslateblue;">--%>
<%--<td>Tel&eacute;fono: ${source.phone}</td>--%>
<%--</tr>--%>
<%--</tbody>--%>
<%--</table></blockquote>--%>
<%--<br/>--%>
<%--<c:if test="${fn:length(source.sections) gt 0}">--%>
<%--<table border="1">--%>
<%--<c:forEach var="section" items="${source.sections}">--%>
<%--<tr>--%>
<%--<td>${section.name}</td>--%>
<%--<td>--%>
<%--<table style="width:100%;">--%>
<%--<c:forEach var="value" items="${section.values}">--%>
<%--<tr>--%>
<%--<td>--%>
<%--<div style="width:100%;border-width:1px;border-color:blue;border-style:none none double none;">--%>
<%--${value}--%>
<%--</div>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--<tr>--%>
<%--<td><br/><br/></td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<%--</table>--%>
<%--</c:if><c:if test="${fn:length(source.sections) eq 0}">--%>
<%--<blockquote> <b>No proporcion&oacute; informaci&oacute;n</b></blockquote>--%>
<%--<br/><br/><br/>--%>
<%--</c:if>--%>
<%--</c:forEach>--%>

<br/>
<br/>
</body>
</html>
