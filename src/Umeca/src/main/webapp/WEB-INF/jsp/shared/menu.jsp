<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="navbar navbar-inverse navbar-fixed-top" ng-controller="menuController">
    <div class="container">
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="<c:url value='/index.html' />"><i class="glyphicon glyphicon-cloud"></i>&nbsp;&nbsp;Inicio</a>
                </li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Administraci&oacute;n <b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/management/role/index.html' />"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Perfiles</a></li>
                            <li><a href="<c:url value='/management/user/index.html' />"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Usuarios</a></li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_REVIEWER')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Evaluador <b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/reviewer/meeting/index.html' />"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Entevista</a></li>
                            <li><a href="<c:url value='/reviewer/verification/index.html' />"><i class="icon-check"></i>&nbsp;&nbsp;Verificaci&oacute;n</a></li>
                            <li><a href="<c:url value='/reviewer/technicalReview/index.html' />"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Opini&oacute;n t&eacute;cnica</a></li>
                        </ul>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_SUPERVISOR')">
                    <li><a href="<c:url value='/supervisor/hearingFormat/index.html' />"><i class="glyphicon glyphicon-file"></i>&nbsp;&nbsp;Formato de audiencia</a></li>
                    <li><a href="<c:url value='/supervisor/framingMeeting/index.html' />"><i class="glyphicon glyphicon-bullhorn"></i>&nbsp;&nbsp;Entrevista de encuadre</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Plan de seguimiento<b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/supervisor/generateMonitoringPlan/index.html' />"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Generar/Modificar</a></li>
                            <li><a href="<c:url value='/supervisor/trackMonitoringPlan/index.html' />"><i class="glyphicon glyphicon-thumbs-up"></i>&nbsp;&nbsp;Dar seguimiento</a></li>
                            <li><a href="<c:url value='/supervisor/manageMonitoringPlan/index.html' />"><i class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Manejar casos y planes</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Bit&aacute;coras<b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/supervisor/log/index.html' />"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Bit&aacute;coras de supervisi&oacute;n y cumplimiento</a></li>
                        </ul>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_EVALUATION_MANAGER')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Supervisi&oacute;n de Evaluadores<b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/managereval/index.html' />"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Entevista</a></li>
                        </ul>
                    </li>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_SUPERVISOR_MANAGER')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Planes de seguimiento<b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value='/supervisorManager/authorizeMonitoringPlan/index.html' />">
                                <i class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;Autorizar / &nbsp;&nbsp;<i class="glyphicon glyphicon-remove"></i>&nbsp;&nbsp;Rechazar</a></li>
                            <li><a href="<c:url value='/supervisorManager/activeMonitoringPlan/index.html' />"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Activos</a></li>
                            <li><a href="<c:url value='/supervisorManager/finishedMonitoringPlan/index.html' />"><i class="glyphicon glyphicon-folder-close"></i>&nbsp;&nbsp;Cerrados</a></li>
                        </ul>
                    </li>
                </sec:authorize>

            </ul>
            <ul class="nav ace-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li  class="nav-li-blue">
                        <a href="javascript:void(0)" ng-click="linkLogin()"><span class="glyphicon glyphicon-log-in"></span> &nbsp; Ingresar</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-li-blue">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                            <img class="nav-user-photo" src="<c:url value='/assets/avatars/avatar0.png' />" alt="Usuario" />
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
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</div>