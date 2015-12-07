<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>INFORME</title>
        <meta content="text/html; charset=UTF-8" http-equiv="content-type">
        <style type="text/css">
            ol{margin:0;padding:0}.c0{orphans:2;widows:2;text-align:center;direction:ltr}.c1{orphans:2;widows:2;text-align:justify;direction:ltr}.c3{background-color:#ffffff;max-width:451.4pt;padding:72pt 72pt 72pt 72pt}.c4{font-size:24pt}.c5{font-size:12pt}.c2{height:11pt}.title{padding-top:0pt;color:#000000;font-size:21pt;padding-bottom:0pt;font-family:"Trebuchet MS";line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}.subtitle{padding-top:0pt;color:#666666;font-size:13pt;padding-bottom:10pt;font-family:"Trebuchet MS";line-height:1.15;page-break-after:avoid;font-style:italic;orphans:2;widows:2;text-align:left}li{color:#000000;font-size:11pt;font-family:"Arial"}p{margin:0;color:#000000;font-size:11pt;font-family:"Arial"}h1{padding-top:10pt;color:#000000;font-size:16pt;padding-bottom:0pt;font-family:"Trebuchet MS";line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}h2{padding-top:10pt;color:#000000;font-weight:bold;font-size:13pt;padding-bottom:0pt;font-family:"Trebuchet MS";line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}h3{padding-top:8pt;color:#666666;font-weight:bold;font-size:12pt;padding-bottom:0pt;font-family:"Trebuchet MS";line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}h4{padding-top:8pt;color:#666666;text-decoration:underline;font-size:11pt;padding-bottom:0pt;font-family:"Trebuchet MS";line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}h5{padding-top:8pt;color:#666666;font-size:11pt;padding-bottom:0pt;font-family:"Trebuchet MS";line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}h6{padding-top:8pt;color:#666666;font-size:11pt;padding-bottom:0pt;font-family:"Trebuchet MS";line-height:1.15;page-break-after:avoid;font-style:italic;orphans:2;widows:2;text-align:left}
        </style>
    </head>
    <body class="c3">
    <p class="c0">
        <span class="c4">INFORME</span>
    </p>
    <br/>
    <br/>
    <p class="c1">
        <span class="c5">ASUNTO: SE REMITE INFORME</span>
    </p>
    <br/>
    <p class="c1">
        <span class="c5">
            El d&iacute;a ${dataEvent.stringDate} para el imputado ${dataEvent.fullname}, se le genero el documento de informe debido a que no cuenta con suficientes fuentes para corroborar la informaci&oacute;n.
        </span>
    </p>
    <br/>
    <br/>

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











