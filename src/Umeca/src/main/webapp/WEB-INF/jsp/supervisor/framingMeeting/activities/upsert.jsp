<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormActivityId");
    });

    $('#start').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false,
        timeFormat: 'hh:mm',
        ampm: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('#end').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false,
        timeFormat: 'hh:mm',
        ampm: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="activitiesController" ng-cloak>
        <div class="modal-dialog" style="width:800px" ng-init='activity=${activity}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Actividades que realiza el
                            imputado
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgErrorActivity!=''" class="alert alert-danger element-center"
                                 ng-bind-html="MsgErrorActivity">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <form id="FormActivityId" class="form-horizontal" role="form">
                            <input type="hidden" name="idCase" value="{{activity.idCase}}">
                            <input type="hidden" name="id" value="{{activity.id}}">
                            <input type="hidden" name="idActivity" value="{{activitySel.id}}">
                            <input type="hidden" name="lstSchedule" value="{{activity.lstSchedule}}">

                            <div class="widget-box">
                                <div class="widget-header">Actividades</label>
                                </div>
                                <div class="widget-body">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <br/>

                                            <div class="col-xs-6">
                                                <label>Actividad:</label>
                                                <br/>
                                                <select class="form-control element-center"
                                                        ng-model="activitySel"
                                                        ng-options="e.name for e in lstActivities"
                                                        ng-init='lstActivities = ${lstActivities};'></select>
                                            </div>
                                            <div class="col-xs-6">
                                                <label>Descripci&oacute;n</label>
                                                <br/>
                                                <textarea class="input-xxlarge form-control limited" name="description"
                                                          ng-model="activity.description"
                                                          maxlength="980" data-val="true"
                                                          data-val-required="Descripci&oacute;n es un campo requerido">
                                                </textarea>
        <span class="field-validation-valid" data-valmsg-for="description"
              data-valmsg-replace="true"></span>
                                                <br/>
                                            </div>
                                        </div>


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
                                                       ng-model="activity.day">
                                            </div>
                                            <div class="col-xs-3">
                                                <label>Inicio</label>
                                                <br/>

                                                <div class="input-group bootstrap-timepicker">
                                                    <input id="start" name="start" ng-model="activity.start"
                                                           readonly type="text" class="form-control umeca-time-picker">
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                                    <br/>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <label>Fin</label>
                                                <br/>

                                                <div class="input-group bootstrap-timepicker">
                                                    <input id="end" name="end" ng-model="activity.end"
                                                           readonly type="text" class="form-control umeca-time-picker">
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
                                                    <tr ng-repeat="schedule in activity.lstSchedule track by $index">
                                                        <td class="element-center">{{schedule.day}}</td>
                                                        <td class="element-center">{{schedule.start}}</td>
                                                        <td class="element-center">{{schedule.end}}</td>
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
                                    <br/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitActivity('#FormActivityId', '<c:url value="/supervisor/framingMeeting/activities/doUpsert.json?idCase="/>',activity.idCase);">
                          Guardar
                    </span>
                </div>

            </div>
        </div>
    </div>
</div>
