<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div ng-controller="menuController">

    <div class="navbar navbar-default navbar-static-top"
         ng-init="urlCheckSession = '<c:url value="/session/checkout.json"/>'; urlHome = '<c:url value='/index.html' />'; initValueSession = ${pageContext.session.maxInactiveInterval};">
        <style>
            .navbar > .container {
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

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-folder-close"></i>
                                &nbsp;&nbsp;Evaluaci&oacute;n
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a
                                        href="<c:url value='/reviewer/meeting/index.html' />"><i
                                        class="glyphicon icon-comments-alt"></i>&nbsp;&nbsp;Entrevista de riesgos</a>
                                </li>
                                <li><a
                                        href="<c:url value='/reviewer/verification/index.html' />"><i
                                        class="icon-check"></i>&nbsp;&nbsp;Verificaci&oacute;n</a>
                                </li>
                                <li><a
                                        href="<c:url value='/reviewer/technicalReview/index.html' />"><i
                                        class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Instrumento de evaluaci&oacute;n</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-search"></i>
                                &nbsp;&nbsp;Consultar
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a
                                        href="<c:url value='/managereval/showCaseEvaluation/index.html' />"><i
                                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Casos en evaluaci&oacute;n</a>
                                </li>

                                <li><a
                                        href="<c:url value='/managereval/showCaseEvaluation/obsoleteCase.html' />"><i
                                        class="icon icon-trash"></i>&nbsp;&nbsp;Casos eliminados</a>
                                </li>
                                <li><a
                                        href="<c:url value='/shared/messageHistory/index.html' />"><i
                                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-li-blue"><a href="<c:url value='/reviewer/caseRequest/index.html' />"><i
                                class="icon icon-envelope"></i>&nbsp;&nbsp;Solicitudes a coordinador</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_SUPERVISOR')">

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-search"></i>
                                &nbsp;&nbsp;Audiencias
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/supervisor/scheduleHearings/index.html'/>">
                                    <i class="icon icon-calendar"></i>&nbsp;&nbsp;Agenda de audiencias</a>
                                </li>
                                <li><a href="<c:url value='/supervisor/hearingFormat/index.html' />"><i
                                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Formato de audiencia</a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-search"></i>
                                &nbsp;&nbsp;Supervisi&oacute;n
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/supervisor/framingMeeting/index.html' />"><i
                                        class="glyphicon glyphicon-bullhorn"></i>&nbsp;&nbsp;Entrevista de encuadre&nbsp;&nbsp;
                                </a>
                                </li>
                                <li class="dropdown-hover">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbspPlan de seguimiento<i
                                            class="icon-caret-right pull-right"></i>
                                    </a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="<c:url value='/supervisor/generateMonitoringPlan/index.html' />"><i
                                                class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Generar/Modificar</a>
                                        </li>
                                        <li><a href="<c:url value='/supervisor/trackMonitoringPlan/index.html' />"><i
                                                class="glyphicon glyphicon-thumbs-up"></i>&nbsp;&nbsp;Dar
                                            seguimiento</a></li>
                                        <li><a href="<c:url value='/supervisor/manageMonitoringPlan/index.html' />"><i
                                                class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Manejar casos y planes</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>


                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-folder-close"></i>
                                &nbsp;&nbsp;Consultar
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/supervisor/log/index.html'/>"><i
                                        class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Historial de
                                    supervisi&oacute;n y cumplimiento</a></li>
                                <li><a
                                        href="<c:url value='/shared/messageHistory/index.html' />"><i
                                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                                </li>
                                <li><a
                                        href="<c:url value='/supervisor/showCaseSupervision/index.html'/>">
                                    <i class="glyphicon glyphicon-bullhorn"></i>&nbsp;&nbsp;Consultar casos en
                                    supervisi&oacute;n</a>
                                </li>
                                <li><a
                                        href="<c:url value='/supervisor/caseNotProsecute/index.html'/>">
                                    <i class="icon icon-folder-close"></i>&nbsp;&nbsp;Casos no judicializados</a>
                                </li>
                            </ul>
                        </li>


                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_EVALUATION_MANAGER')">
                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-folder-close"></i>
                                &nbsp;&nbsp;Autorizar
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/managereval/authorizeRequest/index.html' />"><i
                                        class="icon icon-envelope"></i>&nbsp;&nbsp;Autorizaci&oacute;n de
                                    solicitudes</a></li>
                                <li><a href="<c:url value='/managereval/index.html'/>"><i
                                        class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Autorizar fuentes</a></li>
                            </ul>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-folder-close"></i>
                                &nbsp;&nbsp;Consultar
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/managereval/showCaseEvaluation/index.html' />"><i
                                        class="icon icon-eye-open"></i>&nbsp;&nbsp;Consulta de casos en evaluaci&oacute;n</a>
                                </li>
                                <li><a href="<c:url value='/managereval/showCaseEvaluation/obsoleteCase.html' />"><i
                                        class="icon icon-trash"></i>&nbsp;&nbsp;Casos eliminados</a>
                                </li>
                                <li><a href="<c:url value='/shared/messageHistory/index.html' />"><i
                                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                                </li>
                            </ul>
                        </li>

                        <li class="nav-li-blue"><a href="<c:url value='/director/excelReport/index.html'/>"><i
                                class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Reporte excel</a></li>
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
                                <li><a href="<c:url value='/supervisorManager/caseActive/prisonCases.html' />"><i
                                        class="glyphicon glyphicon-link"></i>&nbsp;&nbsp;Prisi&oacute;n preventiva</a>
                                </li>
                                <li><a href="<c:url value='/supervisorManager/caseObsolete.html' />"><i
                                        class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;Casos eliminados</a></li>
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

                        <li class="nav-li-blue"><a href="<c:url value='/supervisorManager/rolSupervision/index.html'/>"><i
                                class="glyphicon glyphicon-transfer"></i>&nbsp;&nbsp;Rol supervisi&oacute;n</a>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;Consultar
                                <i class="icon-caret-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/supervisor/showCaseSupervision/index.html' />"><i
                                        class="glyphicon glyphicon-bullhorn"></i>&nbsp;&nbsp;Consultar casos en
                                    supervisi&oacute;n</a></li>
                                <li><a href="<c:url value='/shared/messageHistory/index.html' />"><i
                                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a></li>
                            </ul>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-transfer"></i>&nbsp;&nbsp;Reportes
                                <i class="icon-caret-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/supervisorManager/report/index.html'/>"><i
                                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Reporte excel</a>
                                </li>
                                <li><a href="<c:url value='/supervisorManager/report/jasperTest.html' />"><i
                                        class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Reportes por operador</a></li>
                            </ul>
                        </li>

                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_DIRECTOR')">
                        <li class="nav-li-blue"><a href="<c:url value='/director/taskDiary/index.html' />"><i
                                class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Agenda de actividades</a></li>

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


                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-dashboard"></i>&nbsp;&nbsp;Hist&oacute;ricos
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/director/caseRequest/show.html' />"><i
                                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Hist&oacute;rico de solicitudes</a></li>
                                <li><a href="<c:url value='/shared/messageHistory/index.html' />"><i
                                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                                </li>
                            </ul>
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
            </div>
        </div>
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