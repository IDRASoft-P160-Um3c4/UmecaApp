<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormEmpSchId");
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px" ng-controller="employeeScheduleController"
             ng-init='empSch=${empSch}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Agregar horario de trabajo
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormEmpSchId" name="FormEmpSchId" class="form-horizontal" role="form">
                        <input type="hidden" name="id" value="{{empSch.id}}">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header">Agregar trabajo</div>
                                    <div class="widget-body">
                                        <div class="row">
                                            <div class="col-xs-12">

                                                <div class="col-xs-12">
                                                    <br/>

                                                    <div ng-show="MsgError!=''"
                                                         class="alert alert-danger element-center"
                                                         ng-bind-html="MsgError">
                                                    </div>
                                                </div>
                                                <br/>

                                                <div id="divEmpSch">
                                                    <div class="col-xs-12">
                                                        <div class="col-xs-6">
                                                            <label>Nombre</label>
                                                            <br/>
                                                            <input id="name" ng-model="empSch.name"
                                                                   name="name"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Nombre es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="name"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <label>Descripcio&oacute;n</label>
                                                            <br/>
                                                            <textarea class="input-xxlarge form-control limited"
                                                                      name="description"
                                                                      ng-model="empSch.description"
                                                                      maxlength="255" data-val="true"
                                                                      data-val-required="Descripcio&oacute;n es un campo requerido">
                                                            </textarea>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="description"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <div class="widget-box">
                                                            <div class="widget-header">D&iacute;as y horario</div>
                                                            <div class="widget-body">
                                                                <div class="row">
                                                                    <div class="col-xs-12">
                                                                        <label>lalalala</label>
                                                                        <br/>
                                                                        <input data-val-required="Nombre es un campo requerido"/>
                                                                        <br/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>

                                                </div>
                                            </div>
                                            <br/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>
                    </form>
                    <br/>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitJob('#FormEmpSchId', '<c:url value="/humanResources/employeeSchedule/doUpsertEmployeeSchedule.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>