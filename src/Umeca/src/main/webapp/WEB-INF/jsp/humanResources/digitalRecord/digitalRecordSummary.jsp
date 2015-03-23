<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title></title>
</head>
<body>
<div align="right">
    <table width="10%">
        <tbody>
        <tr>
            <td>
                <input type="button"
                       onclick="window.doReturn();"
                       value="Regresar"
                       style="font-size: 15px">
            </td>
            <td/>
            <td>
                <input type="button"
                       onclick="window.exportDoc('${summary.generalData.name}'+'_'+'${summary.generalData.lastNameP}'+'${summary.generalData.lastNameM}');"
                       value="Exportar a Word"
                       style="font-size: 15px">
            </td>
        </tr>
        </tbody>
    </table>

</div>
<div id="page-content">

    <div align="center">
        <h1>Expediente digital UMECA</h1>
    </div>
    <br/>
    <br/>

    <div id="divPhoto">

    </div>
    <div>
        <table width="100%">
            <tbody>
            <tr width="100%">
                <th width="50%" style="vertical-align: bottom;">
                    <h2>Datos generales</h2>
                </th>
                <th width="50%">
                    <img src="${pageContext.request.contextPath}/${summary.photo == null ?'assets/avatars/user.png':summary.photo}"
                         width="100px" height="100px"/>
                </th>
            </tr>
            </tbody>
        </table>
    </div>

    <div align="center" style="width: 100%">
        <table style="border: 1px solid royalblue; width: 60%">
            <thead style="padding: 1%">
            <th colspan="4" align="left" style="background-color: royalblue;">
                <h4>Informaci&oacute;n personal</h4>
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
                    <strong>G&eacute;nero:</strong> ${summary.generalData.genderStr}
                </td>
                <td/>
                <td>
                    <strong>Estado civil:</strong> ${summary.generalData.maritalStatus}
                </td>
            </tr>
            <tr>
                <td>
                    <strong>Tel&eacute;fono:</strong> ${summary.generalData.phone}
                </td>
                <td/>
                <td>
                    <strong>Dependientes econ&oacute;micos:</strong> ${summary.generalData.dependents}
                </td>
            </tr>
            <tr>
                <td>
                    <strong>Identificaci&oacute;n:</strong> ${summary.generalData.document}
                    - ${summary.generalData.documentDesc}
                </td>
                <td/>
                <td>
                    <strong>Correo el&eacute;ctronico:</strong> ${summary.generalData.email}
                </td>
            </tr>
            <tr>
                <td>
                    <strong>C&eacute;dula profesional:</strong> ${summary.generalData.certificate}
                </td>
                <td/>
            </tr>
            </tbody>
        </table>
        <br/>
        <table style="border: 1px solid royalblue; width: 60%">
            <thead style="padding: 1%">
            <th colspan="4" align="left" style="background-color: royalblue;">
                <h4>Informaci&oacute;n laboral</h4>
            </th>

            </thead>
            <tbody>
            <tr>
                <td>
                    <strong>No.
                        empleado:</strong> ${summary.generalData.noEmployee}
                </td>
                <td/>
                <td>
                    <strong>Fecha de inicio servidor p&uacute;blico:</strong> ${summary.generalData.datePublicServ}
                </td>
            </tr>
            <tr/>
            <tr>
                <td>
                    <strong>Fecha de ingreso UMECA:</strong> ${summary.generalData.dateEntryUmeca}
                </td>
                <td/>
                <td>
                    <strong>Comisionado:</strong> ${summary.generalData.commissionerStr}
                </td>
            </tr>
            <tr>
                <td>
                    <strong>No. IMSS:</strong> ${summary.generalData.noImss}
                </td>
                <td/>
                <td>
                    <strong>Nombramiento:</strong> ${summary.generalData.appointment}
                </td>
            </tr>

            </tbody>
        </table>
        <br/>
        <table style="border: 1px solid royalblue; width: 60%">
            <thead style="padding: 1%">
            <th colspan="4" align="left" style="background-color: royalblue;">
                <h4>Direcci&oacute;n</h4>
            </th>
            </thead>
            <tbody>
            <tr>
                <td>
                    <strong>Direcci&oacute;n:</strong> ${summary.generalData.addressStr}
                </td>
            </tr>
            </tbody>
        </table>

        <br/>
    </div>

    <div>
        <table width="100%">
            <tbody>
            <tr width="100%">
                <th width="50%">
                    <h2>Historia laboral</h2>
                </th>
                <th width="50%">
                </th>
            </tr>
            </tbody>
        </table>
    </div>
    <div align="center" style="width: 100%">
        <table style="border: 1px solid royalblue; width: 60%">
            <thead style="padding: 1%">
            <th colspan="4" align="left" style="background-color: royalblue;">
                <h4>Trabajos</h4>
            </th>

            </thead>
            <tbody>

            <c:forEach var="actJob" items="${summary.jobs}">
                <tr>
                    <td colspan="3">
                        <strong>Empresa:</strong> ${actJob.company}
                    </td>
                </tr>
                <tr/>
                <tr>
                    <td>
                        <strong>Puesto:</strong> ${actJob.post}
                    </td>
                    <td/>
                    <td>
                        <strong>Jefe inmediato:</strong> ${actJob.nameHead}
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Salario:</strong> ${actJob.salaryWeek}
                    </td>
                    <td/>
                    <td>
                        <strong>Tel&eacute;fono:</strong> ${actJob.phone}
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Fecha incio:</strong> ${actJob.start}
                    </td>
                    <td/>
                    <td>
                        <strong>Fecha fin:</strong> ${actJob.end}
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <hr>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br/>
    </div>

    <div>
        <table width="100%">
            <tbody>
            <tr width="100%">
                <th width="50%">
                    <h2>Cursos / Diplomados adicionales</h2>
                </th>
                <th width="50%">
                </th>
            </tr>
            </tbody>
        </table>
    </div>
    <div align="center" style="width: 100%">
        <table style="border: 1px solid royalblue; width: 60%">
            <thead style="padding: 1%">
            <th colspan="4" align="left" style="background-color: royalblue;">
                <h4>Cursos / Diplomados</h4>
            </th>

            </thead>
            <tbody>

            <c:forEach var="actCourse" items="${summary.courses}">
                <tr>
                    <td>
                        <strong>Nombre:</strong> ${actCourse.name}
                    </td>
                    <td/>
                    <td>
                        <strong>Lugar:</strong> ${actCourse.place}
                    </td>
                </tr>
                <tr/>
                <tr>
                    <td>
                        <strong>Tipo:</strong> ${actCourse.courseType} <c:if
                            test="${actCourse.specCourseType!=null && actCourse.specCourseType!=''}"> - ${actCourse.specCourseType}</c:if>
                    </td>
                    <td/>

                    <td>
                        <strong>Documento:</strong> ${actCourse.documentType} <c:if
                            test="${actCourse.specDocType!=null && actCourse.specDocType!=''}"> - ${actCourse.specDocType}</c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>Fecha incio:</strong> ${actCourse.start}
                    </td>
                    <td/>
                    <td>
                        <strong>Fecha fin:</strong> ${actCourse.end}
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <hr>
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
                <input type="button"
                       onclick="window.doReturn();"
                       value="Regresar"
                       style="font-size: 15px">
            </td>
            <td/>
            <td>
                <input type="button"
                       onclick="window.exportDoc('${summary.generalData.name}'+'_'+'${summary.generalData.lastNameP}'+'${summary.generalData.lastNameM}');"
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

    var returnUrl = '<c:url value="/humanResources/employees/index.html"/>';

    doReturn = function () {
        window.location.assign(returnUrl);
    };
</script>



