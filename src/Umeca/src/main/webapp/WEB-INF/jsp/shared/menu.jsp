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
                                    class="glyphicon glyphicon-list"></i>
                                &nbsp;&nbsp;Tablero
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a
                                        href="<c:url value='/reviewer/handingOver/index.html'/>"><i
                                        class="glyphicon icon-bell"></i>&nbsp;&nbsp;Puesta a disposici&oacute;n</a>
                                </li>
                            </ul>
                        </li>
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
                                <li><a
                                        href="<c:url value='/reviewer/formulation/index.html' />"><i
                                        class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;Cita de formulaci&oacute;n</a>
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
                                        href="<c:url value='/reviewer/caseReport/index.html' />"><i
                                        class="icon icon-file"></i>&nbsp;&nbsp;Casos con informe</a>
                                </li>
                                <li><a
                                        href="<c:url value='/shared/messageHistory/index.html' />"><i
                                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                                </li>
                                <li><a
                                        href="<c:url value='/reviewer/meeting/declined.html' />"><i
                                        class="glyphicon icon-comments-alt"></i>&nbsp;&nbsp;Entrevistas con negaci&oacute;n</a>
                                </li>

                               <li><a
                                        href="<c:url value='/reviewer/meeting/onlyMeeting.html'/>"><i
                                        class="glyphicon icon-list-alt"></i>&nbsp;&nbsp;Casos s&oacute;lo entrevista</a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-li-blue"><a href="<c:url value='/reviewer/caseRequest/index.html' />"><i
                                class="icon icon-envelope"></i>&nbsp;&nbsp;Solicitudes a coordinador</a></li>
                        <li class="nav-li-blue"><a href="<c:url value='/detentionRecord/detainedSheet.html' />"><i
                                class="icon icon-envelope"></i>&nbsp;&nbsp;S&aacute;bana de detenidos</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_SUPERVISOR')">

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-list-alt"></i>
                                &nbsp;&nbsp;Tablero
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/supervisor/framingMeeting/dates.html'/>">
                                    <i class="icon icon-calendar"></i>&nbsp;&nbsp;Cita entrevistas de encuadre</a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-volume-up"></i>
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
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbspPlan de seguimiento<i
                                    class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/supervisor/generateMonitoringPlan/index.html' />"><i
                                        class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Generar/Modificar</a>
                                </li>
                                <li><a href="<c:url value='/supervisor/trackMonitoringPlan/index.html' />"><i
                                        class="glyphicon glyphicon-thumbs-up"></i>&nbsp;&nbsp;Dar seguimiento</a></li>
                                <li><a href="<c:url value='/supervisor/manageMonitoringPlan/index.html' />"><i
                                        class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Manejar casos y planes</a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-eye-open"></i>
                                &nbsp;&nbsp;Supervisi&oacute;n
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/supervisor/framingMeeting/index.html' />"><i
                                        class="glyphicon glyphicon-bullhorn"></i>&nbsp;&nbsp;Entrevista de encuadre&nbsp;&nbsp;
                                </a></li>
                                <li><a href="<c:url value='/supervisor/channeling/index.html' />"><i
                                        class="glyphicon glyphicon-screenshot"></i>&nbsp;&nbsp;Registro de
                                    canalizaciones&nbsp;&nbsp;
                                </a></li>
                                <li><a href="<c:url value='/supervisor/requestCloseCase/index.html'/>"><i
                                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Cerrar casos</a></li>
                            </ul>
                        </li>


                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-search"></i>
                                &nbsp;&nbsp;Consultar
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/supervisor/log/index.html'/>"><i
                                        class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Historial de
                                    supervisi&oacute;n y cumplimiento</a></li>
                                <li><a href="<c:url value='/shared/messageHistory/index.html' />"><i
                                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                                </li>
                                <li><a href="<c:url value='/supervisor/showCaseSupervision/index.html'/>">
                                    <i class="glyphicon glyphicon-bullhorn"></i>&nbsp;&nbsp;Consultar casos en
                                    supervisi&oacute;n</a>
                                </li>
                                <li><a href="<c:url value='/supervisor/caseNotProsecute/index.html'/>">
                                    <i class="icon icon-folder-close"></i>&nbsp;&nbsp;Casos no judicializados</a>
                                </li>
                                <li><a href="<c:url value='/supervisor/channelingTrack/index.html'/>">
                                    <i class="glyphicon glyphicon-hand-right"></i>&nbsp;&nbsp;Seguimiento canalizaci&oacute;n</a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_EVALUATION_MANAGER')">

                        <li class="nav-li-blue"><a href="<c:url value='/managereval/formulation/index.html' />"><i
                                class="icon icon-calendar"></i>&nbsp;&nbsp;Cita formulaci&oacute;n</a></li>

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
                                <li><a href="<c:url value='/managereval/rolEvaluation/index.html'/>"><i
                                        class="glyphicon glyphicon-transfer"></i>&nbsp;&nbsp;Rol evaluaci&oacute;n</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-transfer"></i>&nbsp;&nbsp;Reportes
                                <i class="icon-caret-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/director/excelReport/index.html'/>"><i
                                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Reporte excel</a></li>
                                <li><a href="<c:url value='/shared/activityReport/index.html' />"><i
                                        class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;Informe de actividades para
                                    direcci&oacute;n</a></li>
                                <li><a href="<c:url value='/managereval/statisticReport/index.html' />"><i
                                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Estad&iacute;sticos - Reporte estad&iacute;stico</a></li>
                                <li><a href="<c:url value='/managereval/statisticOperatorReport/index.html' />"><i
                                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Reportes por operador - Estad&iacute;sticos operador</a></li>
                            </ul>
                        </li>
                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-dashboard"></i>&nbsp;&nbsp;Minutas de trabajo
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/shared/minute/index.html' />"><i
                                        class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Minutas abiertas</a>
                                </li>
                                <li><a href="<c:url value='/shared/minute/finishedIndex.html' />"><i
                                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Minutas cerradas</a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-li-blue"><a href="<c:url value='/detentionRecord/detainedSheet.html' />"><i
                                class="icon icon-envelope"></i>&nbsp;&nbsp;S&aacute;bana de detenidos</a></li>

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
                                <li><a href="<c:url value='/supervisorManager/channelingInfo/index.html' />"><i
                                        class="glyphicon glyphicon-check"></i>&nbsp;&nbsp;Autorizar/Rechazar solicitud
                                    baja</a></li>
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
                                <li><a href="<c:url value='/supervisorManager/report/reportChart.html' />"><i
                                        class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Reportes con
                                    gr&aacute;fico</a></li>
                                <li><a href="<c:url value='/shared/activityReport/index.html' />"><i
                                        class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;Informe de actividades para
                                    direcci&oacute;n</a></li>
                            </ul>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-dashboard"></i>&nbsp;&nbsp;Minutas de trabajo
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/shared/minute/index.html' />"><i
                                        class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Minutas abiertas</a>
                                </li>
                                <li><a href="<c:url value='/shared/minute/finishedIndex.html' />"><i
                                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Minutas cerradas</a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_CHANNELING_MANAGER')">
                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-copyright-mark"></i>&nbsp;&nbsp;Canalizaciones
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/channelingManager/documentRepository/index.html' />">
                                    <i class="glyphicon glyphicon-cloud-upload"></i>&nbsp;&nbsp;Repositorio de
                                    documentos</a></li>
                                <li><a href="<c:url value='/channelingManager/queryChanneling/index.html' />">
                                    <i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;Canalizaciones por caso</a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_DIRECTOR')">
                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class=" glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Actividades
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/director/taskDiary/index.html' />">
                                    <i class="glyphicon glyphicon-book"></i>&nbsp;&nbsp;Agenda de actividades</a></li>
                                <li><a href="<c:url value='/director/project/index.html' />">
                                    <i class="glyphicon glyphicon-tags"></i>&nbsp;&nbsp;Proyectos</a></li>
                            </ul>
                        </li>

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
                                    class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Hist&oacute;ricos
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/director/caseRequest/show.html' />"><i
                                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Hist&oacute;rico de solicitudes</a>
                                </li>
                                <li><a href="<c:url value='/shared/messageHistory/index.html' />"><i
                                        class="icon icon-envelope"></i>&nbsp;&nbsp;Hist&oacute;rico de mensajes</a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-transfer"></i>&nbsp;&nbsp;Reportes
                                <i class="icon-caret-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/shared/activityReport/index.html' />"><i
                                        class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;Informe de actividades</a>
                                </li>
                                <li><a href="<c:url value='/director/activityReport/index.html' />"><i
                                        class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;Informes globales de
                                    actividades</a></li>
                            </ul>
                        </li>
                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-dashboard"></i>&nbsp;&nbsp;Minutas de trabajo
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/shared/minute/index.html' />"><i
                                        class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Minutas abiertas</a>
                                </li>
                                <li><a href="<c:url value='/shared/minute/finishedIndex.html' />"><i
                                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Minutas cerradas</a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_HUMAN_RESOURCES')">
                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;Dispositivos
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/timeAttendance/device/index.html' />"><i
                                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Configuraci&oacute;n de Biom&eacute;tricos</a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;Manejo de horarios
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/humanResources/employeeSchedule/index.html' />"><i
                                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Registrar horarios</a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Manejo de personal
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/humanResources/employees/index.html' />"><i
                                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Registrar personal</a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown nav-li-blue">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                    class="glyphicon glyphicon-dashboard"></i>&nbsp;&nbsp;Minutas de trabajo
                                <i class="icon-caret-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="<c:url value='/shared/minute/index.html' />"><i
                                        class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Minutas abiertas</a>
                                </li>
                                <li><a href="<c:url value='/shared/minute/finishedIndex.html' />"><i
                                        class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Minutas cerradas</a>
                                </li>
                                <li><a href="<c:url value='/shared/minute/directorMinute.html' />"><i
                                        class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Minutas para
                                    direcci&oacute;n</a>
                                </li>
                                <li><a href="<c:url value='/humanResources/document/index.html' />"><i
                                        class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Oficios</a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_DETENTION_RECORD')">

                        <li class="nav-li-blue"><a href="<c:url value='/detentionRecord/detainedSheet.html'/>"><i
                                class="glyphicon glyphicon-transfer"></i>&nbsp;&nbsp;S&aacute;bana de detenidos</a>
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