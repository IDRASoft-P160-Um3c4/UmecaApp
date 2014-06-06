
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
                                <label>Ingrese la informaci�n requerida para poder generar un nuevo n�mero de expediente:</label>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-4 element-left">
                                RFC:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener 10 caracteres"
                                       data-val-length-max="10" data-val-length-min="10"
                                       data-val-required="El RFC es un campo requerido" ng-init="m.rfc='AAAA933639'"
                                       id="rfc" name="rfc" readonly="readonly" value="AAAA933639"
                                       ng-model="m.rfc" type="text" />
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Nombre:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener al menos 3 y máximo 50 caracteres"
                                       data-val-length-max="50" data-val-length-min="3" ng-init="m.name=''"
                                       data-val-required="El nombre es un campo requerido"
                                       id="name" name="name" ng-model="m.name"
                                       type="text" rfc-calculated/>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Apellido paterno:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener al menos 3 y m�ximo 50 caracteres" ng-init="m.lastNameP=''"
                                       data-val-length-max="50" data-val-length-min="3"  rfc-calculated
                                       data-val-required="El apellido paterno es un campo requerido"
                                       id="lastNameP" name="lastNameP"
                                       type="text" ng-model="m.lastNameP"/>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="lastNameP" data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>
                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Apellido materno:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener al menos 3 y máximo 50 caracteres"
                                       data-val-length-max="50" data-val-length-min="3"          rfc-calculated
                                       data-val-required="El apellido parterno es un campo requerido" ng-inti="lastNameM=''"
                                       id="lastNameM" name="lastNameM"
                                       type="text" ng-model="m.lastNameM"/>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="lastNameM" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-5 element-left">
                                Fecha de nacimiento:
                            </div>
                            <div class="col-xs-7">
                                <div class="input-group">
                                    <input class="form-control date-picker" readonly="readonly" type="text" data-date-format="yyyy/mm/dd"    rfc-calculated
                                            data-val="true" data-val-required="La fecha de nacimiento es un campo requerido" ng-init="m.dateBirth=''"
                                            id="dateBirth" name="dateBirth" ng-model="m.dateBirth"/>
											<span class="input-group-addon">
														<i class="icon-calendar bigger-110"></i>
											</span>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-12 element-center">
                                <span class="label label-pink label-lg arrowed-right">
                                    <i class="icon-warning-sign bigger-120"></i>&nbsp;
                                    Estos datos no podr�n ser modificados durante la entrevista.
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

<script>
    $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });
</script>
