<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div ng-controller="menuController">

<div class="navbar navbar-default navbar-static-top"
     ng-init="urlCheckSession = '<c:url value="/session/checkout.json"/>'; urlHome = '<c:url value='/index.html' />'; initValueSession = ${pageContext.session.maxInactiveInterval};">
<style>
    .navbar>.container {
        padding-left: 5px !important;
        padding-right: 5px !important;
    }
    .container {
        max-width: 100% !important;
    }
</style>
<script type="text/javascript">
    try {
        ace.settings.check('navbar', 'fixed')
    } catch (e) {
    }
</script>

<div class="container" id="navbar-container">
    <div class="navbar-header pull-left">
        <a href="<c:url value='/index.html' />" class="navbar-brand">
            <i class="glyphicon glyphicon-cloud"></i>&nbsp;&nbsp;Inicio</a>
    </div>
    <!-- /.navbar-header -->

    <div class="navbar-header pull-right" role="navigation">
        <ul class="nav ace-nav">
            <li class="nav-li-blue"></li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">

                <li class="nav-li-blue"><a href="<c:url value='/management/role/index.html' />"><i
                        class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Perfiles</a></li>
                <li class="nav-li-blue"><a href="<c:url value='/management/user/index.html' />"><i
                        class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Usuarios</a></li>

            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_REVIEWER')">
                <li class="nav-li-blue"><a href="<c:url value='/reviewer/meeting/index.html' />"><i
                        class="glyphicon icon-comments-alt"></i>&nbsp;&nbsp;Entrevista</a></li>
                <li class="nav-li-blue"><a href="<c:url value='/reviewer/verification/index.html' />"><i
                        class="icon-check"></i>&nbsp;&nbsp;Verificaci&oacute;n</a>
                </li>
                <li class="nav-li-blue"><a href="<c:url value='/reviewer/technicalReview/index.html' />"><i
                        class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Instrumento de evaluaci&oacute;n de riesgos</a>
                </li>
                <li class="nav-li-blue"><a href="<c:url value='/reviewer/caseRequest/index.html' />"><i
                        class="icon icon-envelope"></i>&nbsp;&nbsp;Solicitudes a Coordinador</a></li>
                <li class="nav-li-blue"><a
                        href="<c:url value='/managereval/showCaseEvaluation/index.html' />"><i
                        class="icon icon-envelope"></i>&nbsp;&nbsp;Consulta de casos en evaluaci&oacute;n</a>
                </li>
                <li class="nav-li-blue"><a
                        href="<c:url value='/shared/messageHistory/index.html' />"><i
                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_SUPERVISOR')">
                <li class="nav-li-blue"><a href="<c:url value='/supervisor/hearingFormat/index.html' />"><i
                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Formato de audiencia</a></li>
                <li class="nav-li-blue"><a href="<c:url value='/supervisor/framingMeeting/index.html' />"><i
                        class="glyphicon glyphicon-bullhorn"></i>&nbsp;&nbsp;Entrevista de encuadre</a></li>
                <li class="dropdown nav-li-blue">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Plan de seguimiento
                        <i class="icon-caret-down"></i> </a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value='/supervisor/generateMonitoringPlan/index.html' />"><i
                                class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Generar/Modificar</a>
                        </li>
                        <li><a href="<c:url value='/supervisor/trackMonitoringPlan/index.html' />"><i
                                class="glyphicon glyphicon-thumbs-up"></i>&nbsp;&nbsp;Dar seguimiento</a></li>
                        <li><a href="<c:url value='/supervisor/manageMonitoringPlan/index.html' />"><i
                                class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Manejar casos y planes</a></li>
                    </ul>
                </li>
                <li class="dropdown nav-li-blue">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="glyphicon glyphicon-folder-close"></i>
                        <%--&nbsp;&nbsp;Bit&aacute;coras--%>
                        <%--<i class="icon-caret-down"></i>--%>
                        &nbsp;&nbsp;Historiales
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <%--<li><a href="<c:url value='/supervisor/log/index.html'/>"><i--%>
                                <%--class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Bit&aacute;coras de--%>
                            <%--supervisi&oacute;n y cumplimiento</a></li>--%>
                            <li><a href="<c:url value='/supervisor/log/index.html'/>"><i
                                    class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Historial de
                                supervisi&oacute;n y cumplimiento</a></li>
                            <li><a
                                    href="<c:url value='/shared/messageHistory/index.html' />"><i
                                    class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                            </li>
                    </ul>
                </li>
                <li class="nav-li-blue"><a href="<c:url value='/supervisor/showCaseSupervision/index.html'/>"><i
                        class="glyphicon glyphicon-bullhorn"></i>&nbsp;&nbsp;Consultar casos en
                    supervisi&oacute;n</a>
                </li>
                <li class="nav-li-blue"><a href="<c:url value='/supervisor/caseNotProsecute/index.html'/>"><i
                        class=" icon-folder-close"></i>&nbsp;&nbsp;Casos no judicializados</a>
                </li>

            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_EVALUATION_MANAGER')">
                <li class="nav-li-blue"><a href="<c:url value='/managereval/index.html'/>"><i
                        class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Autorizar fuentes</a></li>
                <li class="nav-li-blue"><a href="<c:url value='/managereval/showCaseEvaluation/index.html' />"><i
                        class="icon-check"></i>&nbsp;&nbsp;Consulta de casos en evaluaci&oacute;n</a></li>
                <li class="nav-li-blue"><a href="<c:url value='/director/excelReport/index.html'/>"><i
                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Reporte excel</a></li>
                <li class="nav-li-blue"><a href="<c:url value='/managereval/authorizeRequest/index.html' />"><i
                        class="icon icon-envelope"></i>&nbsp;&nbsp;Autorizaci&oacute;n de solcitudes</a></li>
                <li class="nav-li-blue"><a
                        href="<c:url value='/shared/messageHistory/index.html' />"><i
                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_SUPERVISOR_MANAGER')">
                <li class="dropdown nav-li-blue">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class=" glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Casos
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value='/supervisorManager/caseActive/index.html' />"><i
                                class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Activos</a></li>
                        <li><a href="<c:url value='/supervisorManager/caseClosed/index.html' />"><i
                                class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Cerrados</a></li>
                    </ul>
                </li>
                <li class="dropdown nav-li-blue">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Planes de seguimiento
                        <i class="icon-caret-down"></i></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value='/supervisorManager/assignCase/index.html' />"><i
                                class="glyphicon glyphicon-share"></i>&nbsp;&nbsp;Asignar casos</a></li>
                        <li><a href="<c:url value='/supervisorManager/authorizeMonitoringPlan/index.html' />"><i
                                class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;Autorizar / &nbsp;&nbsp;<i
                                class="glyphicon glyphicon-remove"></i>&nbsp;&nbsp;Rechazar</a></li>
                        <li><a href="<c:url value='/supervisorManager/activeMonitoringPlan/index.html' />"><i
                                class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Activos</a></li>
                        <li><a href="<c:url value='/supervisorManager/finishedMonitoringPlan/index.html' />"><i
                                class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Cerrados</a></li>
                    </ul>
                </li>
                <li class="dropdown nav-li-blue">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="glyphicon glyphicon-transfer"></i>&nbsp;&nbsp;Rol supervisi&oacute;n
                        <i class="icon-caret-down"></i></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value='/supervisorManager/rolSupervision/index.html' />"><i
                                class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Generar rol</a></li>
                    </ul>
                </li>
                <li class="nav-li-blue"><a href="<c:url value='/supervisor/showCaseSupervision/index.html'/>"><i
                        class="glyphicon glyphicon-bullhorn"></i>&nbsp;&nbsp;Consultar casos en
                    supervisi&oacute;n</a>
                </li>
                <li class="nav-li-blue"><a href="<c:url value='/director/excelReport/index.html'/>"><i
                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Reporte excel</a>
                </li>
                <li class="nav-li-blue"><a
                        href="<c:url value='/shared/messageHistory/index.html' />"><i
                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_DIRECTOR')">
                <li class="dropdown nav-li-blue">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class=" glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Consulta de casos
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value='/managereval/showCaseEvaluation/index.html' />"><i
                                class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Evaluaci&oacute;n</a>
                        </li>
                        <li><a href="<c:url value='/supervisor/showCaseSupervision/index.html' />"><i
                                class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Supervisi&oacute;n</a>
                        </li>
                        <li><a href="<c:url value='/director/excelReport/index.html'/>"><i
                                class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Reporte excel</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-li-blue"><a href="<c:url value='/director/caseRequest/show.html' />"><i
                        class="icon icon-envelope"></i>&nbsp;&nbsp;Historial de solicitudes</a></li>
                <li class="nav-li-blue"><a
                        href="<c:url value='/shared/messageHistory/index.html' />"><i
                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                </li>
            </sec:authorize>
            <li class="light-blue">
                <sec:authorize access="isAnonymous()">
                    <a href="#" ng-click="linkLogin()" ng-init="hasUser = false"><span
                            class="glyphicon glyphicon-log-in"></span> &nbsp; Ingresar</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle" ng-init="hasUser = true">
                        <img class=" nav-user-photo" src="<c:url value='/assets/avatars/avatar0.png' />"
                             alt="Usuario"/>
								<span class="user-info">
									<small>Bienvenido,</small>
									<%=SecurityContextHolder.getContext().getAuthentication().getName()%>
								</span>
                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="<c:url value='/j_spring_security_logout'/>">
                                <i class="icon-off"></i>
                                Salir
                            </a>
                        </li>
                    </ul>
                </sec:authorize>
            </li>
        </ul>
        <!-- /.ace-nav -->
    </div>
    <!-- /.navbar-header -->
</div>
<!-- /.container -->
</div>

<div id="ConfirmBoxDialogSession" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div class="alert alert-{{Type}}">
                    <h4 class="modal-title element-center" ng-bind-html="Title"></h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="element-left" ng-bind-html="Message"></div>
            </div>
            <div class="modal-footer">
                    <span class="btn btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="continueSession()">
                          Continuar
                    </span>
            </div>
        </div>
    </div>
</div>
</div>