<!DOCTYPE html><HTML lang="es-ES"><HEAD><META content="IE=10.000" http-equiv="X-UA-Compatible">
<META charset="UTF-8"/>
<META name="viewport" content="width=device-width, initial-scale=1.0">
<META http-equiv="X-UA-Compatible" content="IE=9, IE=10" name="description" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8" />
    <title>UMECA</title>

    <link href="assets/content/Site.css" rel="stylesheet" type="text/css">
    <link href="assets/content/bootstrap.css" rel="stylesheet" />
    <link href="assets/content/bootstrap.theme.css" rel="stylesheet" />

    <script src="assets/scripts/modernizr-2.6.2.js"></script>
    <script src="assets/scripts/jquery-1.10.2.min.js"></script>
    <script src="assets/scripts/angular/angular.min.js"></script>
    <script src="assets/scripts/bootstrap.min.js"></script>
    <script src="assets/scripts/respond.min.js"></script>
    <script src="assets/scripts/jquery.validate.min.js"></script>
    <script src="assets/scripts/jquery.validate.unobtrusive.min.js"></script>
    <script src="assets/scripts/app/shared/mainApp.js"></script>
    <script src="assets/scripts/app/shared/menuCtrl.js"></script>
    <script src="assets/scripts/app/shared/loginCtrl.js"></script>
    <script src="assets/scripts/common.js"></script>


</head>
<body scroll="no" ng-app="ptlUmc">
<div class="navbar navbar-inverse navbar-fixed-top" ng-controller="menuController">
    <div class="container">
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/"><i class="glyphicon glyphicon-cloud"></i>&nbsp;&nbsp;Inicio</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="javascript:void(0)" ng-click="linkLogin()"><span class="glyphicon glyphicon-log-in"></span> &nbsp; Ingresar</a>
                </li>

            </ul>
        </div>
    </div>
</div>
<div class="container body-content">

    <div id="modalLogin" class="modal fade" ng-controller="loginController" ng-cloak>
        <form class="form-horizontal" id="loginForm" role="form" novalidate="novalidate">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title element-center">Ingresar al sistema</h2>
                        <div class="element-center">
                            <h4>
                                <i class="glyphicon glyphicon-cog"></i>
                                &nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-cloud"></i>
                                &nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-home"></i>
                                &nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-phone"></i>
                                &nbsp;&nbsp;&nbsp;<i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;&nbsp;
                            </h4>
                        </div>
                    </div>
                    <div class="modal-body">
                        <h4 class="element-center">Ingrese los datos para acceder al portal</h4>
                        <hr />
                        <div class="row">
                            <div class="col-xs-3">&nbsp;</div>
                            <div class="col-xs-6">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                    <input name="username" class="form-control" id="username" type="text" placeholder="Usuario" value="" ng-model="m.username" data-val-required="No ha ingresado el usuario" data-val="true">
                                </div>
                                <div class="input-group error-font">
                                    <span class="field-validation-valid" data-valmsg-replace="true" data-valmsg-for="UserName"></span>
                                </div>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-xs-3">&nbsp;</div>
                            <div class="col-xs-6">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                    <input name="password" class="form-control" id="password" type="password" placeholder="Contraseña" ng-model="m.password" data-val-required="No ha ingresado la contraseña" data-val="true">
                                </div>
                                <div class="input-group error-font">
                                    <span class="field-validation-valid" data-valmsg-replace="true" data-valmsg-for="Password"></span>
                                </div>
                            </div>
                        </div>
                        <br />
                        <br />
                        <div class="row">
                            <div class="col-xs-12">
                                <div ng-show="msgError" class="alert alert-danger element-center error-font">
                                    {{msgError}}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-click="login('#loginForm', 'Error de red. Intente más tarde')">Ingresar</span>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="jumbotron">
        <h1>Sistema UMECA</h1>
        <p class="lead">Pantallas inicial.</p>
        <p><a href="/" class="btn btn-primary btn-large">Inicio &raquo;</a></p>
    </div>

    <div class="row">
        <div class="col-xs-4">
            <h2>Noticia 1</h2>
            <p>
                Bla bla bla
            </p>
            <p><a class="btn btn-default" href="/">Ir a &raquo;</a></p>
        </div>
        <div class="col-xs-4">
            <h2>Noticia 2</h2>
            <p>
                Bla bla bla
            </p>
            <p><a class="btn btn-default" href="/">Ir a &raquo;</a></p>
        </div>
        <div class="col-xs-4">
            <h2>Noticia 3</h2>
            <p>
                Bla bla bla
            </p>
            <p><a class="btn btn-default" href="/">Ir a &raquo;</a></p>
        </div>
    </div>







    <footer>
        <div align="right">
            <small>Última modificación 22 Abril 2014 V 0.1</small>
        </div>
    </footer>

</div>
</body>
</html>

