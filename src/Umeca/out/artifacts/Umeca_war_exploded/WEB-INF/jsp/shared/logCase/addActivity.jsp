<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });

    $('#timeString').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertLogCaseController" ng-cloak>
        <div class="modal-dialog" style="width:700px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-asterisk "></i>&nbsp;&nbsp;Agregar actividad espont&aacute;nea</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
                       <br/>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                               T&iacute;tulo:
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                                       data-val-length-max="500" data-val-length-min="1" data-val-required="El t&iacute;tulo es un campo requerido"
                                       type="text" value="" ng-model="m.title" id="title" name="title">
                                <span class="field-validation-valid" data-valmsg-for="title" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                Detalles:
                                <textarea class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m&aacute;ximo 1500 caracteres"
                                       data-val-length-max="1500" data-val-length-min="1" data-val-required="El detalle es un campo requerido"
                                       ng-model="m.resume" id="resume" name="resume"></textarea>
                                <span class="field-validation-valid" data-valmsg-for="resume" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-5 col-xs-offset-1">
                                <label>Fecha de realizaci&oacute;n:</label>
                                <div class="input-group">
                                    <input id="dateString" name="dateString"
                                           ng-model="m.dateString"
                                           class="form-control date-picker"
                                           type="text" data-date-format="yyyy/mm/dd" data-val="true"
                                           data-val-required="Fecha es un campo requerido"/>
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                </div>
                                <span class="field-validation-valid" data-valmsg-for="dateString"
                                      data-valmsg-replace="true"></span>
                            </div>
                            <div class="col-xs-5">
                                <label for="timeString">Hora de realizaci&oacute;n</label>
                                <div class="input-group bootstrap-timepicker">
                                    <input id="timeString" name="timeString"
                                           ng-model="m.timeString"
                                           readonly type="text"
                                           class="form-control umeca-time-picker"
                                           data-val="true"
                                           data-val-required="Hora es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                    <br/>
                                </div>
                        <span class="field-validation-valid" data-valmsg-for="timeString"
                              data-valmsg-replace="true"></span>
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
                          ng-click="submit('#FormCatId', '<c:url value="/shared/logCase/doNewLogSpontaneous.json?id=${caseId}"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>



