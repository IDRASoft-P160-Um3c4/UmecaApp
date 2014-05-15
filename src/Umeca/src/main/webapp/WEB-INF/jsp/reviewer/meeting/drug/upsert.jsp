<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:700px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-warning-sign "></i>&nbsp;&nbsp;Consumo de sustancias</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
                        <br />
                        <div class="row">
                            <div class="col-xs-2">
                                Sustancia:
                            </div>
                            <div class="col-xs-4">
                                <select class="form-control element-center" ng-model="typeDrug" ng-init="typeDrug=0">
                                    <option value="0">Alcohol</option>
                                    <option value="1">Solventes</option>
                                    <option value="2">Marihuana</option>
                                    <option value="3">Cocaina</option>
                                    <option value="4">Pastillas</option>
                                    <option value="5">Otras</option>
                                </select>
                                <div ng-show="typeDrug==5">
                                  <br/>
                                  Especifique:&nbsp;    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                                               data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                                                               type="text" value="" ng-model="m.name">
                                </div>
                            </div>
                            <div class="col-xs-2">
                                Periodicidad:
                            </div>
                            <div class="col-xs-4">
                                <select class="form-control element-center">
                                    <option value="0">1-2 Semanal</option>
                                    <option value="1">2-3 Semanal</option>
                                    <option value="1">4-5 Semanal</option>
                                    <option value="1">6+ Semanal</option>
                                    <option value="1">1-2 Quincenal</option>
                                    <option value="1">1-25 Mensual</option>
                                </select>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-2">
                                Cantidad:
                            </div>
                            <div class="col-xs-4">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                       data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                                       type="text" value="" ng-model="m.cant">
                            </div>
                            <div class="col-xs-2">
                                Último consumo:
                            </div>
                            <div class="col-xs-4">
                                <div class="input-group">
                                    <input class="form-control date-picker" id="dpEndBefore" type="text" data-date-format="dd-mm-yyyy" />
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
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
                          ng-click="submit('#FormCatId', '/reviewer/meeting/doNewMeeting.json');">
                          Continuar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });
</script>



