<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Usuario</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form" ng-controller="userController">
                        <br />
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci�n del usuario
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Usuario:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m�ximo 200 caracteres"
                                                               data-val-length-max="200" data-val-length-min="6" data-val-required="Usuario es un campo requerido" id="username" name="username"
                                                               type="text" value="" ng-model="m.username">
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-valid" data-valmsg-for="username" data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Perfil:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <select class="form-control element-center" ng-model="m.role" ng-options="e.name for e in lstRoles" ng-init='lstRoles = ${lstRoles};'></select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Contrase�a:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 8 y m�ximo 50 caracteres"
                                                               data-val-length-max="50" data-val-length-min="8" data-val-required="Contrase�a es un campo requerido" id="password" name="password"
                                                               type="password" value="" ng-model="m.password">
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-valid" data-valmsg-for="password" data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Confirmaci�n:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input name="confirm" class="form-control" id="confirm" type="password" data-val-required="Confirmar contrase�a es un campo requerido"
                                                               data-val="true" data-val-equalto-other="*.password" data-val-equalto="La contrase�a no concuerda con la confirmaci�n"
                                                               ng-model="m.confirm"/>
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-valid" data-valmsg-for="confirm" data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;Informaci�n personal
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Nombre:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m�ximo 200 caracteres"
                                                               data-val-length-max="200" data-val-length-min="6" data-val-required="Nombre es un campo requerido" id="fullname" name="fullname"
                                                               type="text" value="" ng-model="m.fullname">
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-valid" data-valmsg-for="fullname" data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Correo:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input name="email" class="form-control ng-pristine ng-valid" id="email" type="text"
                                                               ng-model="m.email" data-val-required="Correo electr�nico es un campo requerido" data-val="true" ng-init="m.email = ''"
                                                               data-val-length-min="5" data-val-length-max="500" data-val-length="Debe tener al menos 5  y m�ximo 500 caracteres"
                                                               data-val-email="Correo electr�nico no v�lido" value=""/>
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-valid" data-valmsg-for="email" data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <br />
                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError" class="alert alert-danger element-center">
                                {{MsgError}}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormCatId', '/management/user/doUpsert.json')">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

