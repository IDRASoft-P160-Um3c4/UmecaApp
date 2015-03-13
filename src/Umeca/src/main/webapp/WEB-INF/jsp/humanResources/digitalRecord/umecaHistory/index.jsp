<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="col-xs-12">
    <div class="element-center">
        <h2><i class="orange icon-list bigger-100">&nbsp;</i>Historial UMECA</h2>
    </div>
    <br/>

    <div class="widget-box">
        <div class="widget-header">Puestos ocupados dentro de la UMECA
            <div class="widget-toolbar">
                <a data-action="collapse" href="#"><i class="icon-chevron-up"></i></a>
            </div>
        </div>
        <div class="widget-body">
            <div class="row">
                <div class="col-xs-12" style="padding: 1%">
                    <%@ include
                            file="/WEB-INF/jsp/humanResources/digitalRecord/umecaHistory/umecaJob.jsp" %>
                </div>
            </div>
        </div>
    </div>

    <div class="widget-box">
        <div class="widget-header">Cursos / Logros
            <div class="widget-toolbar">
                <a data-action="collapse" href="#"><i class="icon-chevron-up"></i></a>
            </div>
        </div>

        <div class="widget-body">
            <div class="row">
                <div class="col-xs-12" style="padding: 1%">
                    <%--<%@ include--%>
                    <%--file="/WEB-INF/jsp/humanResources/digitalRecord/umecaHistory/course.jsp" %>--%>
                </div>
            </div>

        </div>
    </div>
    <div class="widget-box">
        <div class="widget-header">Incidencias
            <div class="widget-toolbar">
                <a data-action="collapse" href="#"><i class="icon-chevron-up"></i></a>
            </div>
        </div>
        <div class="widget-body">
            <div class="row">
                <div class="col-xs-12" style="padding: 1%">
                    <%--<%@ include--%>
                    <%--file="/WEB-INF/jsp/humanResources/digitalRecord/umecaHistory/incidents.jsp" %>--%>
                </div>
            </div>
        </div>
    </div>
</div>