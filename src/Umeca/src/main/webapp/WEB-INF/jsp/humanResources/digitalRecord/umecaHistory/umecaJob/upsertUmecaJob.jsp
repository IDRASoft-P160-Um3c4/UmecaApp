<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormUmecaJobId");
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    jQuery(function ($) {

        $('#startTime').timepicker({
            minuteStep: 1,
            showSeconds: true,
            showMeridian: false
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $('#endTime').timepicker({
            minuteStep: 1,
            showSeconds: true,
            showMeridian: false
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });

</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px" ng-controller="umecaJobController"
             ng-init='umecaJob=${umecaJob}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Agregar puesto</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormUmecaJobId" name="FormUmecaJobId" class="form-horizontal" role="form">
                        <input type="hidden" name="idEmployee" value="{{umecaJob.idEmployee}}">
                        <input type="hidden" name="id" value="{{umecaJob.id}}">

                        <div class="row">

                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header">Agregar puesto</div>
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

                                                <div class="col-xs-12">
                                                    <div class="col-xs-6">
                                                        <label>Puesto</label>
                                                        <br/>
                                                        <select id="idRole"
                                                                class="form-control element-center"
                                                                ng-model="umecaJob.role"
                                                                ng-init='lstRole= ${lstRole}'
                                                                ng-options="e.name for e in lstRole"></select>
                                                        <input type="hidden" name="idRole"
                                                               value="{{umecaJob.role.id}}"/>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label>Distrito</label>
                                                        <br/>
                                                        <select id="idDistrict"
                                                                class="form-control element-center"
                                                                ng-model="umecaJob.district"
                                                                ng-init='lstDistrict = ${lstDistrict}'
                                                                ng-options="e.name for e in lstDistrict"></select>
                                                        <input type="hidden" name="idDistrict"
                                                               value="{{umecaJob.district.id}}"/>
                                                    </div>
                                                </div>

                                                <div class="col-xs-12">
                                                    <br/>

                                                    <div class="col-xs-6">
                                                        <label>Jefe inmediato</label>
                                                        <br/>
                                                        <input id="nameHead" ng-model="umecaJob.nameHead"
                                                               name="nameHead"
                                                               type="text"
                                                               class="input-xxlarge" data-val="true"
                                                               data-val-required="Jefe inmediato es un campo requerido"/>
                                                        <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="nameHead"
                                                                  data-valmsg-replace="true"></span>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label>Salario</label>
                                                        <br/>
                                                        <input id="salary" ng-model="umecaJob.salary" name="salary"
                                                               type="text"
                                                               class="input-xxlarge" data-val="true"
                                                               data-val-required="Salario es un campo requerido"
                                                               data-val-regex-pattern="([0-9]+(.[0-9])?)"
                                                               data-val-regex="S&oacute;lo puede contener n&uacute;meros y un punto"/>
                                                        <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="salary"
                                                                  data-valmsg-replace="true"></span>
                                                    </div>

                                                </div>

                                                <div class="col-xs-12">
                                                    <br/>

                                                    <div class="col-xs-6">
                                                        <label>Fecha de inicio</label>
                                                        <br/><small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/01)</small>

                                                        <div class="input-group">
                                                            <input class="form-control date-picker"
                                                                   name="startDate" type="text"
                                                                   data-date-format="yyyy/mm/dd"

                                                                   ng-model="umecaJob.startDate" data-val="true"
                                                                   data-val-required="Fecha de inicio es un campo requerido"/>
                                                                    <span class="input-group-addon">
                                                                        <i class="icon-calendar bigger-110"></i>
                                                                    </span>
                                                        </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="startDate"
                                                              data-valmsg-replace="true"></span>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label>Fecha de fin</label>
                                                        <br/><small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/30)</small>

                                                        <div class="input-group">
                                                            <input class="form-control date-picker"
                                                                   name="endDate" type="text"
                                                                   data-date-format="yyyy/mm/dd"

                                                                   ng-model="umecaJob.endDate" data-val="true"
                                                                   data-val-required="Fecha de fin es un campo requerido"/>
                                                                    <span class="input-group-addon">
                                                                        <i class="icon-calendar bigger-110"></i>
                                                                    </span>
                                                        </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="endDate"
                                                              data-valmsg-replace="true"></span>
                                                        <br/>
                                                    </div>
                                                </div>


                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </form>
                </div>


                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitUmecaJob('#FormUmecaJobId', '<c:url value="/humanResources/digitalRecord/doUpsertUmecaJob.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
