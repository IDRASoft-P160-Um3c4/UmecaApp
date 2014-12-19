<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    Date.prototype.addDays = function(days)
    {
        var dat = new Date(this.valueOf());
        dat.setDate(dat.getDate() + days);
        return dat;
    }

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
        var dat = new Date();
        var daysBefore = ${daysBefReminder};

        $('#hearingDateSt, #hearingReminderDateSt').datepicker({autoclose: true, startDate: dat.addDays(-1)}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $('#hearingDateSt').change(function(){
            var dt = $(this).data('datepicker').date;
            $('#hearingReminderDateSt').datepicker('update', dt.addDays(daysBefore));
        });

        $('#hearingDateSt').datepicker('update', dat.addDays(daysBefore));
        $('#hearingReminderDateSt').datepicker('update', dat);

        $('#hearingTimeSt, #hearingReminderTimeSt').timepicker({minuteStep: 15, showSeconds: false, showMeridian: false}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });


        $('#hearingTimeSt').change(function () {
            var time = $(this).data('timepicker').getTime();
            $('#hearingReminderTimeSt').timepicker('setTime', time);
        });
    });
</script>

<div>
<div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
<div class="modal-dialog" style="width:900px" ng-controller="scheduleHearingsController">
<div class="modal-content">
<div class="modal-header">
    <div class="alert alert-info ">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="element-center"><i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;Agendar audiencia</h4>
    </div>
</div>
<div class="modal-body">
<form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">

<div class="row">
    <div class="col-xs-12">
        <div class="panel panel-default panel-primary">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci&oacute;n de la cita
            </div>
            <div class="panel-body" ng-init="m.dtHearing = '#hearingDateSt'; m.dtHearingReminder = '#hearingReminderDateSt';">
                <input type="hidden" value='${lstIds}' name="lstIds" id ="lstIds" />
                <div class="row">
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name"> Fecha</div>
                                <div class="profile-info-value">
                                    <div class="input-group input-large">
                                        <input class="form-control date-picker"
                                               id="hearingDateSt" data-date-format="yyyy/mm/dd"
                                               name="hearingDateSt" ng-model="m.hearingDate"
                                               type="text" readonly data-val="true"
                                               data-val-required="Fecha es un campo requerido" />
                                        <span class="input-group-addon">
                                            <i class="icon-calendar bigger-110"></i>
                                        </span>
                                    </div>
                                    <span class="field-validation-valid" data-valmsg-for="hearingDateSt"
                                        data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name"> Hora</div>
                                <div class="profile-info-value">
                                    <div class="input-group bootstrap-timepicker">
                                        <input class="form-control" id="hearingTimeSt" name="hearingTimeSt" readonly type="text"
                                               ng-model="m.hearingTime" data-val="true"
                                               data-val-required="Hora es un campo requerido">
                                        <span class="input-group-addon">
                                            <i class="icon-time bigger-110"></i>
                                        </span>
                                    </div>
                                    <span class="field-validation-valid" data-valmsg-for="hearingTimeSt" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name"> Sala</div>
                                <div class="profile-info-value">
                                    <div>
                                        <input type="text" name="hearingRoom" style="width: 210px;"
                                               class="input-small" ng-model="m.hearingRoom" data-val="true"
                                               data-val-required="Sala es un campo requerido">
                                        <br/>
                                        <span class="field-validation-valid"
                                              data-valmsg-for="hearingRoom"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name" ng-init='lstHearingType=${lstHearingType}; m.hearingType = lstHearingType[0];'> Tipo de audiencia</div>
                                <div class="profile-info-value">
                                    <select class="form-control element-center" ng-model="m.hearingType"
                                            ng-options="e.description for e in lstHearingType"
                                            ng-change="m.hearingTypeId = m.hearingType.id">
                                    </select>
                                    <input type="hidden" name="hearingTypeId" value="{{m.hearingType.id}}">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row" ng-init = 'm.daysBefReminder = ${daysBefReminder};'>
    <div class="col-xs-9">
        <div class="col-xs-11 checkbox">
            <label>
                <input type="checkbox" value="true" ng-model="m.hasReminder" name="hasReminder" id="hasReminder"/>
                <span class="control-label">Agregar actividad de recordatorio para la audiencia</span>
            </label>
        </div>
    </div>
</div>
<div class="row">
    <br/>
    <div class="col-xs-12">
        <div class="panel panel-default panel-primary" ng-show="m.hasReminder">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci&oacute;n del recordatorio
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name"> Fecha</div>
                                <div class="profile-info-value">
                                    <div class="input-group input-large">
                                        <input class="form-control date-picker"
                                               id="hearingReminderDateSt" data-date-format="yyyy/mm/dd"
                                               name="hearingReminderDateSt" ng-model="m.hearingReminderDate"
                                               type="text" readonly data-val="true"
                                               data-val-required="Fecha es un campo requerido" />
                                            <span class="input-group-addon">
                                                <i class="icon-calendar bigger-110"></i>
                                            </span>
                                    </div>
                                        <span class="field-validation-valid" data-valmsg-for="hearingReminderDateSt"
                                              data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row two-lines">
                                <div class="profile-info-name"> Hora</div>
                                <div class="profile-info-value">
                                    <div class="input-group bootstrap-timepicker">
                                        <input class="form-control"
                                               id="hearingReminderTimeSt" name="hearingReminderTimeSt" readonly type="text"
                                               ng-model="m.hearingReminderTime" data-val="true"
                                               data-val-required="Hora es un campo requerido" />
                                            <span class="input-group-addon">
                                                <i class="icon-time bigger-110"></i>
                                            </span>
                                    </div>
                                    <span class="field-validation-valid" data-valmsg-for="hearingReminderTimeSt" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>
</form>
<br/>
    <div class="row">
        <div class="col-xs-12">
            <div ng-show="MsgErrorIn" class="alert alert-danger element-center"  ng-bind-html="MsgErrorIn"></div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div ng-show="MsgError" class="alert alert-danger element-center"  ng-bind-html="MsgError"></div>
        </div>
    </div>
</div>
<div class="modal-footer" id="btn-act-footer">
    <button class="btn btn-default btn-sm" ng-show="!isReadOnly" ng-click="cancel()">Cancelar</button>
    <button class="btn btn-success btn-sm" ng-show="isReadOnly" ng-click="cancel()">Regresar</button>
    <button class="btn btn-default btn-primary btn-sm" ng-disable="WaitFor==true"
            ng-click="submitRedirect('#FormCatId', '<c:url value='/supervisor/scheduleHearings/doScheduleNewHearing.json' />', true, validateSave)">
        Agendar
    </button>
</div>
</div>
</div>
</div>
</div>