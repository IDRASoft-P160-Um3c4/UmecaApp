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
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-list"></i>&nbsp;&nbsp;Administraci�n <b class="caret"></b> </a>
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

                        </ul>
                    </li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="isAnonymous()">
                    <li>
                        <a href="javascript:void(0)" ng-click="linkLogin()"><span class="glyphicon glyphicon-log-in"></span> &nbsp; Ingresar</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Bienvenido: <%=SecurityContextHolder.getContext().getAuthentication().getName()%></a>
                    </li>
                    <li>
                        <a href="<c:url value='j_spring_security_logout'/>"><span class="glyphicon glyphicon-log-in"></span> &nbsp; Salir</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</div>