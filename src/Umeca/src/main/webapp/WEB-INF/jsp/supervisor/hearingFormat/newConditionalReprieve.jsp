<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Agregar caso</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
                        <br />
                        <div class="row">
                            <div class="col-xs-12">
                                <label>Ingrese la información requerida para generar un nuevo caso por Suspensión Condicional de Proceso.</label>
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
                                       data-val-required="El nombre es un campo requerido"
                                       data-val-length-max="50" data-val-length-min="3"
                                       id="name" name="name" ng-model="name"
                                       type="text"/>
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
                                       data-val-length="Debe tener al menos 3 y máximo 50 caracteres"
                                       data-val-length-max="50" data-val-length-min="3"
                                       data-val-required="El apellido paterno es un campo requerido"
                                       id="lastNameP" name="lastNameP"
                                       type="text" ng-model="lastNameP"/>
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
                                       data-val-length-max="50" data-val-length-min="3"
                                       data-val-required="El apellido parterno es un campo requerido"
                                       id="lastNameM" name="lastNameM"
                                       type="text" ng-model="lastNameM"/>
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
                                    <input class="form-control date-picker" readonly="readonly" type="text" data-date-format="dd/mm/yyyy"
                                           data-val="true" data-val-required="La fecha de nacimiento es un campo requerido"
                                           id="dateBirth" name="dateBirth" ng-model="dateBirth"/>
											<span class="input-group-addon">
														<i class="icon-calendar bigger-110" ></i>
											</span>
                                </div>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="dateBirth" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-5 element-left">
                                Carpeta judicial:
                            </div>
                            <div class="col-xs-7">
                                <input class="form-control" type="text"    data-val-length="Debe tener al menos 1 y máximo 15 caracteres"
                                       data-val-length-max="15" data-val-length-min="1"
                                       data-val="true" data-val-required="Carpeta judicial es un campo requerido"
                                       id="idJudicial" name="idJudicial" ng-model="m.idJudicial"/>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="meeting.caseDetention.idFolder" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
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
                          ng-click="submit('#FormCatId','<c:url value="/supervisor/hearingFormat/doNewCase.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('.date-picker').datepicker({autoclose:true, endDate:new Date()}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });
</script>
