<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormJobId");
    });

    $('#timeStart').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    $('#timeEnd').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function () {
        $(this).next().focus();
    });
</script>

<div>
<div id="dlgUpModalId" class="modal fade" ng-controller="framingJobController" ng-cloak>

<div class="modal-dialog" style="width:800px" ng-init='job=${job}'>
<div class="modal-content">
<div class="modal-header">
    <div class="alert alert-info ">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Historia laboral</h4>
    </div>
</div>
<div class="modal-body">
<form id="FormJobId" name="FormJobId" class="form-horizontal" role="form">

<input type="hidden" name="id" value="{{job.id}}">
<input type="hidden" name="registerTypeId" value="{{registerType.id}}">
<br/>

<div class="row">
    <div id="divHiddenJob" ng-show="false">
        <input type="hidden" name="post" value="{{job.post}}">
        <input type="hidden" name="company" value="{{job.company}}">
        <input type="hidden" name="address" value="{{job.address}}">
        <input type="hidden" name="phone" value="{{job.phone}}">
        <input type="hidden" name="nameHead" value="{{job.nameHead}}">
    </div>
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header">Historia laboral</div>
            <div class="widget-body">
                <div class="row">
                    <div class="col-xs-12">

                        <div class="col-xs-12">
                            <br/>

                            <div ng-show="MsgErrorJob!=''"
                                 class="alert alert-danger element-center"
                                 ng-bind-html="MsgErrorJob">
                            </div>
                        </div>
                        <br/>

                        <div class="col-xs-12">
                            <div class="col-xs-10">
                                <label>&iquest;El imputado trabaja actualmente?</label>
                                <br/>

                                <div class="radio">
                                    <label>
                                        <input class="ace" type="radio" ng-value="true"
                                               name="block"
                                               ng-model="hasActualJob"
                                               ng-checked="hasActualJob==true" ng-click="disableView(true);">
                                        <span class="lbl">&nbsp;&nbsp;Si</span>
                                    </label>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <label>
                                        <input class="ace" type="radio" ng-value="false"
                                               name="block"
                                               ng-model="hasActualJob"
                                               ng-checked="hasActualJob==false" ng-click="disableView(false);">
                                        <span class="lbl">&nbsp;&nbsp;No</span>
                                    </label>
                                </div>
                                <br/>
                            </div>
                        </div>

                        <br/>

                        <div id="divJob">
                            <div class="col-xs-12">
                                <div class="col-xs-12">
                                    <label>Empresa</label>
                                    <br/>
                                    <input id="company" ng-model="job.company" name="company"
                                           type="text" style=" width: 100% !important"
                                           class="input-xxlarge" data-val="true"
                                           data-val-required="Empresa es un campo requerido"/>
                                    <br/>
                                        <span class="field-validation-valid" data-valmsg-for="company"
                                              data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>

                            <div class="col-xs-12">
                                <br/>

                                <div class="col-xs-6">
                                    <label>Puesto</label>
                                    <br/>
                                    <input id="post" ng-model="job.post" name="post" type="text"
                                           class="input-xxlarge" data-val="true"
                                           data-val-required="Puesto es un campo requerido"/>
                                    <br/>
                                        <span class="field-validation-valid" data-valmsg-for="post"
                                              data-valmsg-replace="true"></span>
                                </div>

                                <div class="col-xs-6">
                                    <label>Tel&eacute;fono:</label>
                                    <br/>
                                    <textarea class="input-xxlarge form-control limited"
                                              name="phone"
                                              ng-model="job.phone"
                                              maxlength="980" data-val="true"
                                              data-val-required="Tel&eacute;fono es un campo requerido">
                                    </textarea>
        <span class="field-validation-valid" data-valmsg-for="phone"
              data-valmsg-replace="true"></span>
                                </div>

                            </div>
                            <br/>

                            <div class="col-xs-12">
                                <div class="col-xs-6">
                                    <label>Nombre del patr&oacute;n</label>
                                    <br/>
                                    <input id="nameHead" ng-model="job.nameHead" name="nameHead"
                                           type="text"
                                           class="input-xxlarge" data-val="true"
                                           data-val-required="Nombre del patr&oacute;n es un campo requerido"/>
                                    <br/>
                                        <span class="field-validation-valid" data-valmsg-for="nameHead"
                                              data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-6">
                                    <label>Tipo de empleo</label>
                                    <br/>
                                    <select class="form-control element-center"
                                            ng-model="registerType"
                                            ng-options="e.name for e in lstRegisterType"
                                            ng-init='lstRegisterType = ${lstRegisterType};'></select>
                                </div>
                            </div>
                            <br/>

                            <div class="col-xs-12">
                                <br/>

                                <div class="col-xs-12">

                                    <label>Domicilio</label>
                                    <br/>
                                    <textarea class="input-xxlarge form-control limited"
                                              name="address"
                                              ng-model="job.address"
                                              maxlength="980" data-val="true"
                                              data-val-required="Direcci&oacute;n es un campo requerido">
                                    </textarea>
        <span class="field-validation-valid" data-valmsg-for="address"
              data-valmsg-replace="true"></span>
                                    <br/>
                                    <br/>
                                </div>

                            </div>
                            <br/>
                        </div>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>

<div id="divSpecs" class="row" ng-show="hasActualJob==true">
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header">Trabajo {{registerType.name}}</div>
            <div class="widget-body">
                <br/>

                <div class="row" ng-show="registerType.id!=3">

                    <input type="hidden" name="schedule" value="{{job.schedule}}">

                    <div class="col-xs-12">
                        <div class="col-xs-6">
                            <label>Inicio</label>
                            <br/>

                            <div class="input-group">
                                <input class="form-control date-picker"
                                       id="start" name="start"
                                       type="text"
                                       data-date-format="yyyy/mm/dd"
                                       readonly ng-model="job.start"
                                       data-val="true"
                                       data-val-required="Inicio es un campo requerido"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                            </div>
                                <span class="field-validation-valid" data-valmsg-for="start"
                                      data-valmsg-replace="true"></span>
                        </div>

                        <div class="col-xs-6">
                            <label>Salario semanal</label>
                            <br/>
                            <input id="salaryWeek" ng-model="job.salaryWeek" name="salaryWeek"
                                   type="text"
                                   class="input-xxlarge" data-val="true"
                                   data-val-required="Salario semanal es un campo requerido"
                                   data-val-regex-pattern="([0-9]+(.[0-9])?)"
                                   data-val-regex="S&oacute;lo puede contener n&uacute;meros y un punto"/>
                            <br/>
                                        <span class="field-validation-valid" data-valmsg-for="salaryWeek"
                                              data-valmsg-replace="true"></span>
                        </div>
                    </div>
                    <br/>

                    <div class="col-xs-12">
                        <div class="col-xs-12">
                            <div class="col-xs-12">
                                <br/>

                                <div ng-show="MsgErrorSchedule!=''"
                                     class="alert alert-danger element-center">
                                    <span>{{MsgErrorSchedule}}</span>
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <label>D&iacute;a(s)</label>
                                <br/>
                                <input class="form-control" type="text"
                                       ng-model="day">
                            </div>
                            <div class="col-xs-3">
                                <label>Inicio</label>
                                <br/>

                                <div class="input-group bootstrap-timepicker">
                                    <input id="timeStart" ng-model="timeStart"
                                           readonly type="text" class="form-control">
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                    <br/>
                                </div>
                            </div>
                            <div class="col-xs-3">
                                <label>Fin</label>
                                <br/>

                                <div class="input-group bootstrap-timepicker">
                                    <input id="timeEnd" ng-model="timeEnd"
                                           readonly type="text" class="form-control">
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                    <br/>
                                </div>
                            </div>
                            <div class="col-xs-2">
                                <br/>
                                <button type="button" class="btn btn-info" ng-click="addSchedule();">
                                    <i class="icon-plus bigger-110"></i>
                                    Agregar
                                </button>
                            </div>
                        </div>

                        <div class="col-xs-10 col-xs-offset-1">
                            <br/>
                            <br/>

                            <table class="table table-striped table-bordered table-hover">
                                <thead class="thin-border-bottom">
                                <tr>
                                    <th class="element-center">D&iacute;a(s)</th>
                                    <th class="element-center">Inicio</th>
                                    <th class="element-center">Fin</th>
                                    <th class="element-center">Quitar</th>
                                </tr>
                                </thead>

                                <tbody>
                                <div>
                                    <tr ng-repeat="actSch in job.schedule track by $index">
                                        <td class="element-center">{{actSch.day}}</td>
                                        <td class="element-center">{{actSch.start}}</td>
                                        <td class="element-center">{{actSch.end}}</td>
                                        <td class="element-center"><a href="javascript:;"
                                                                      style="display:inline-block;"
                                                                      title="Quitar de la lista"
                                                                      ng-click="removeSchedule($index)"><span
                                                class="glyphicon glyphicon-minus blue"></span></a></td>
                                    </tr>
                                </div>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>

                <div class="row" ng-show="registerType.id==3">
                    <div class="col-xs-12">
                        <div class="col-xs-6">
                            <label>Inicio</label>
                            <br/>

                            <div class="input-group">
                                <input class="form-control date-picker"
                                       id="startPrev" name="startPrev"
                                       type="text"
                                       data-date-format="yyyy/mm/dd"
                                       readonly ng-model="job.startPrev"
                                       data-val="true"
                                       data-val-required="Inicio es un campo requerido"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                            </div>
                                <span class="field-validation-valid" data-valmsg-for="startPrev"
                                      data-valmsg-replace="true"></span>
                        </div>

                        <div class="col-xs-6">
                            <label>Fin</label>
                            <br/>

                            <div class="input-group">
                                <input class="form-control date-picker"
                                       id="end" name="end"
                                       type="text"
                                       data-date-format="yyyy/mm/dd"
                                       readonly ng-model="job.end"
                                       data-val="true"
                                       data-val-required="Fin es un campo requerido"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                            </div>
                                <span class="field-validation-valid" data-valmsg-for="end"
                                      data-valmsg-replace="true"></span>
                        </div>
                    </div>
                    <br/>

                    <div class="col-xs-12">
                        <div class="col-xs-8">
                            <br/>
                            <label>Motivo de cambio:</label>
                            <br/>
                            <textarea class="input-xxlarge form-control limited"
                                      name="reasonChange"
                                      ng-model="job.reasonChange"
                                      maxlength="980" data-val="true"
                                      data-val-required="Motivo de cambio es un campo requerido">
                            </textarea>
        <span class="field-validation-valid" data-valmsg-for="reasonChange"
              data-valmsg-replace="true"></span>
                            <br/>
                            <br/>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>

</form>
<br/>
</div>
<div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitJob('#FormJobId', '<c:url value="/supervisor/framingMeeting/job/doUpsert.json?idCase="/>',job.idCase);">
                          Guardar
                    </span>
</div>
</div>
</div>
</div>
</div>