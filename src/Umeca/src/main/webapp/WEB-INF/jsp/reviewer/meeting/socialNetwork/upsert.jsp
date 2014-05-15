<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-group "></i>&nbsp;&nbsp;Persona de su red social</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
                        <br />
                        <div class="row">
                            <div class="col-xs-12">
                            <div class="col-xs-2 element-left">
                                Nombre:
                            </div>
                            <div class="col-xs-10">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                                       type="text" value="" ng-model="m.name">
                            </div>
                            <div class="row">
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
                            </div>
                                </div>
                            </div>

                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4 element-left">
                                    Parentesco:
                                </div>
                                <div class="col-xs-8">
                                    <select class="form-control element-center" ng-model="m.role" ng-options="e.name for e in lstRoles" ng-init='lstRoles = ${lstRoles};'></select>
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <div class="col-xs-5">
                                <div class="col-xs-4 element-left">
                                    Teléfono:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                           data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                           type="text" value="" ng-model="m.name">
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4 element-left">
                                    Identificación prensentada:
                                </div>
                                <div class="col-xs-8">
                                    <select class="form-control element-center" ng-model="m.role" ng-options="e.name for e in lstRoles" ng-init='lstRoles = ${lstRoles};'></select>
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <div class="col-xs-5">
                                <div class="col-xs-4 element-left">
                                    Edad:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                           data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                           type="text" value="" ng-model="m.name">
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-4 element-left">
                                    Vive con el:
                                </div>
                                <div class="col-xs-8">
                                    <select class="form-control element-center" ng-model="m.role" ng-options="e.name for e in lstRoles" ng-init='lstRoles = ${lstRoles};'></select>
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <div class="col-xs-5">
                                <div class="col-xs-4 element-left">
                                    Dependiente económico:
                                </div>
                                <div class="col-xs-7">
                                    <select class="form-control element-center" ng-model="m.role" ng-options="e.name for e in lstRoles" ng-init='lstRoles = ${lstRoles};'></select>
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
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
                          ng-click="submit('#FormCatId', '/reviewer/meeting/socialNetwork/doUpsert.json');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

