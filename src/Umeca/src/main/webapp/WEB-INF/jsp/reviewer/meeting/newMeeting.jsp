<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:500px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Agregar Entrevista</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
                        <br />
                        <div class="row">
                            <div class="col-xs-12">
                                <label>Ingrese la información requerida para poder generar un nuevo n?mero de expediente:</label>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-3 element-left">
                                Nombre:
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y máximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                       type="text" value="" ng-model="m.name">
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-3 element-left">
                                Apellido paterno:
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y máximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El apellido paterno es un campo requerido" id="lastName1"
                                       type="text" value="" ng-model="m.lastName1">
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>
                        <div class="row">
                            <div class="col-xs-3 element-left">
                                Apellido materno:
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El apellido materno es un campo requerido" id="lastName2"
                                       type="text" value="" ng-model="m.lastName2">
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="lastName2" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-12 element-center">
                                <span class="label label-pink label-lg arrowed-right">
                                    <i class="icon-warning-sign bigger-120"></i>&nbsp;
                                    Estos datos no podrán ser modificados durante la entrevista.
                                </span>
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
                          ng-click="submit('#FormCatId', '/reviewer/meeting/doNewMeeting.json');">
                          Continuar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

