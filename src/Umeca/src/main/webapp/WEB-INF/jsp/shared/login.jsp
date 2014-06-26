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
                                <input name="j_username" class="form-control" id="j_username" type="text" placeholder="Usuario" value="" ng-model="m.username" data-val-required="No ha ingresado el usuario" data-val="true">
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
                                <input name="j_password" class="form-control" id="j_password" type="password" placeholder="Contraseña" ng-model="m.password" data-val-required="No ha ingresado la contraseña" data-val="true">
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
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-click="login('#loginForm', 'Error de red. Intente más tarde', '<c:url value="/j_spring_security_check" />')">Ingresar</button>
                </div>
            </div>
        </div>
    </form>
</div>
