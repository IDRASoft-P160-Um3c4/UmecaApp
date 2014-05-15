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
                        <h4 class="element-center"><i class="glyphicon glyphicon-home "></i>&nbsp;&nbsp;Domicilio</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
                        Ingrese su código postal para obtnener automáticamente su información
                        <br/>
                        <br/>
                        <div class="row">
                            <div class="col-xs-2 element-right">
                                Código  postal:
                            </div>
                            <div class="col-xs-3">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                       type="text" value="">
                            </div>
                            <div class="col-xs-2 col-xs-offset-1 element-right">
                                Elije:
                            </div>
                            <div class="col-xs-3">
                                <select class="form-control element-center" ng-model="colonia" ng-init="Colonia Obrera">
                                    <option value="Colonia Industrial">Colonia Industrial</option>
                                    <option value="Colonia Obrera">Colonia Obrera</option>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-2 element-right">
                                Estado:
                            </div>
                            <div class="col-xs-3">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                       type="text" value="" readonly="readonly">
                            </div>
                            <div class="col-xs-2 element-right col-xs-offset-1">
                                  Municipio:
                            </div>
                            <div class="col-xs-3">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                       type="text" value="" readonly="readonly">
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-2 element-right">
                                Colonia:
                            </div>
                            <div class="col-xs-3">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                       type="text" value="" ng-model="colonia" readonly="readonly">
                            </div>
                            <div class="col-xs-1 element-right">
                                No Ext
                            </div>
                            <div class="col-xs-2">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                       type="text" value="">
                            </div>
                            <div class="col-xs-1 element-right">
                                No Int
                            </div>
                            <div class="col-xs-2">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                                       type="text" value="">
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-2 element-right">
                               Propiedad:
                            </div>
                            <div class="col-xs-3">
                                <select class="form-control element-center" ng-model="typeDomicile">
                                    <option value="0">Si</option>
                                    <option value="1">No</option>
                                </select>
                            </div>
                            <div class="col-xs-2 col-xs-offset-1 element-right">
                                Tipo de domiclio:
                            </div>
                            <div class="col-xs-3">
                                <select class="form-control element-center">
                                    <option value="0">Actual</option>
                                    <option value="1">Secundario</option>
                                    <option value="2">Anterior</option>
                                </select>
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

