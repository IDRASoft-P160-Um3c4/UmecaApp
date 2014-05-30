<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="navbar navbar-inverse navbar-fixed-top" ng-controller="menuController">
    <div class="container">
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/index.html"><i class="glyphicon glyphicon-cloud"></i>&nbsp;&nbsp;Inicio</a>
                </li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Administración <b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a href="/management/role/index.html"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Perfiles</a></li>
                            <li><a href="/management/user/index.html"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Usuarios</a></li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_REVIEWER')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Evaluador <b class="caret"></b> </a>
                        <ul class="dropdown-menu">
                            <li><a href="/reviewer/meeting/index.html"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Entevista</a></li>
                            <li><a href="/reviewer/verification/index.html"><i class="icon-check"></i>&nbsp;&nbsp;Verificación</a></li>
                            <li><a href="/reviewer/technicalReview/index.html"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;Opinión técnica</a></li>
                            <li><a href="/reviewer/technicalReview/technicalReview.html"><i class="glyphicon glyphicon-list-alt"></i>&nbsp;&nbsp;FORMA</a></li>

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
                            <img class="nav-user-photo" src="/assets/avatars/avatar0.png" alt="Usuario" />
								<span class="user-info">
									<small>Bienvenido,</small>
									<%=SecurityContextHolder.getContext().getAuthentication().getName()%>
								</span>

                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                            <li>
                                <a href="<c:url value='j_spring_security_logout'/>">
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