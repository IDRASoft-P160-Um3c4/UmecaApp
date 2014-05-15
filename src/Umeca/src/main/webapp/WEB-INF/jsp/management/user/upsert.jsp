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
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
                          role="form" ng-controller="userController">
                        <br/>
                        <input type="hidden" ng-update-hidden ng-model="m.id" name="id" id="id"
                               ng-init="m.id = ${(model == null) ? 0 : model.id};">
                        <input type="hidden" ng-update-hidden ng-model="m.hasChangePass" name="hasChangePass"
                               id="hasChangePass" ng-init="m.hasChangePass = false;">
                        <input type="hidden" ng-update-hidden ng-model="m.roleId" name="roles[0].id" id="roles[0].id"
                               ng-init="m.roleId = ${roleId == null ? "undefined" : roleId};">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Información del
                                        usuario
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Usuario:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input class="form-control" data-val="true"
                                                               data-val-length="Debe tener al menos 6 y máximo 200 caracteres"
                                                               data-val-length-max="200" data-val-length-min="6"
                                                               data-val-required="Usuario es un campo requerido"
                                                               id="username" name="username"
                                                               type="text" ng-model="m.username"
                                                               ng-init="m.username = '${(model == null) ? "" : model.username}';"
                                                               ng-unique="{url:'/management/user/isUserAvailable.json', param:'username', extraParam:{id: ${(model == null)?0:model.id}},
                                                               msgError:'El usuario ya existe, por favor elija otro usuario'}"/>
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-error"
                                                              data-ng-show="FormCatId.username.$error.unique">{{errorUnique}}</span>
                                                        <span class="field-validation-valid" data-valmsg-for="username"
                                                              data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Perfil:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <select class="form-control element-center" ng-model="m.role"
                                                                ng-options="e.name for e in lstRoles"
                                                                ng-change="m.roleId = m.role.id"
                                                                ng-init='lstRoles = ${lstRoles};'></select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="m.id > 0">
                                            <br/>

                                            <div class="col-xs-6">
                                                <input type="checkbox" ng-model="m.hasChangePass"/> Modificar contraseña
                                            </div>
                                        </div>
                                        <br/>

                                        <div class="row" ng-show="m.id <= 0 || m.hasChangePass === true">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Contraseña:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input class="form-control" data-val="true"
                                                               data-val-length="Debe tener al menos 8 y máximo 50 caracteres"
                                                               data-val-length-max="50" data-val-length-min="8"
                                                               data-val-required="Contraseña es un campo requerido"
                                                               id="password" name="password"
                                                               type="password" ng-model="m.password">
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-valid" data-valmsg-for="password"
                                                              data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Confirmación:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input name="confirm" class="form-control" id="confirm"
                                                               type="password"
                                                               data-val-required="Confirmar contraseña es un campo requerido"
                                                               data-val="true" data-val-equalto-other="*.password"
                                                               data-val-equalto="La contraseña no concuerda con la confirmación"
                                                               ng-model="m.confirm"/>
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-valid" data-valmsg-for="confirm"
                                                              data-valmsg-replace="true"></span>
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
                                        <span class="glyphicon glyphicon-list"></span>&nbsp;&nbsp;Información personal
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Nombre:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input class="form-control" data-val="true"
                                                               data-val-length="Debe tener al menos 6 y máximo 200 caracteres"
                                                               data-val-length-max="200" data-val-length-min="6"
                                                               data-val-required="Nombre es un campo requerido"
                                                               id="fullname" name="fullname"
                                                               type="text" ng-model="m.fullname"
                                                               ng-init="m.fullname = '${(model == null) ? "" : model.fullname}';">
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-valid" data-valmsg-for="fullname"
                                                              data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-3 element-left">
                                                        Correo:
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <input name="email" class="form-control ng-pristine ng-valid"
                                                               id="email" type="text"
                                                               ng-model="m.email"
                                                               data-val-required="Correo electrónico es un campo requerido"
                                                               data-val="true"
                                                               data-val-length-min="5" data-val-length-max="500"
                                                               data-val-length="Debe tener al menos 5  y máximo 500 caracteres"
                                                               data-val-email="Correo electrónico no válido"
                                                               ng-init="m.email = '${(model == null) ? "" : model.email}';"/>
                                                    </div>
                                                    <div class="col-xs-9 col-xs-offset-3">
                                                        <span class="field-validation-valid" data-valmsg-for="email"
                                                              data-valmsg-replace="true"></span>
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
                    <br/>

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

