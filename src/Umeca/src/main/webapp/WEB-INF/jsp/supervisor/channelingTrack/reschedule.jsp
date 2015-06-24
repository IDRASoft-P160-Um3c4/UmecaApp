<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormUpId");

        $('#id-date-picker-start').datepicker({autoclose: true}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $('#id-timepicker-start, #id-timepicker-end').timepicker({
            minuteStep: 15,
            showSeconds: false,
            showMeridian: false
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $(".date-picker, .input-group-addon").mouseover(function () {
            $(this).css('cursor', 'pointer');
        });

        $('.umeca-time-picker').mouseover(function () {
            $(this).css('cursor', 'pointer');
        });

        //$("#id-date-picker-start").datepicker('update', new Date());
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px">
            <div class="modal-content"  ng-controller="channelingTrackController">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h5 class="element-center"><i class="glyphicon glyphicon-check"></i>&nbsp;&nbsp;Reagendar cita</h5>
                    </div>
                </div>
                <div class="modal-body"
                     ng-init='m = ${model}; initReschedule();'>
                    <form id="FormUpId" name="FormUpId" class="form-horizontal" role="form">
                        <br/>
                        <input type="hidden" ng-update-hidden ng-model="m.actMonPlanId" name="actMonPlanId" id="actMonPlanId">
                        <input type="hidden" ng-update-hidden ng-model="m.dateStart" name="dateStart" id="dateStart">
                        <input type="hidden" ng-update-hidden ng-model="m.timeStart" name="timeStart" id="timeStart">
                        <input type="hidden" ng-update-hidden ng-model="m.timeEnd" name="timeEnd" id="timeEnd">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h6><i class="glyphicon glyphicon-hand-right"></i>&nbsp;&nbsp;{{(m.rescheduleAppointmentId >= 0 ? 'Consultar cita reagendada' : 'Reagendar cita')}}</h6>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-6 element-right">
                                                <label class="form-control-static">Imputado:</label>
                                            </div>
                                            <div class="col-xs-6 element-left">
                                                <label class="form-control-static"><b>{{m.imputed}}</b></label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6 element-right">
                                                <label class="form-control-static">Carpeta judicial:</label>
                                            </div>
                                            <div class="col-xs-6 element-left">
                                                <label class="form-control-static"><b>{{m.idMP}}</b></label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6 element-right">
                                                <label class="form-control-static">Nombre de la canalizaci&oacute;n:</label>
                                            </div>
                                            <div class="col-xs-6 element-left">
                                                <label class="form-control-static"><b>{{m.channelingName}}</b></label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6 element-right">
                                                <label class="form-control-static">Tipo de canalizaci&oacute;n:</label>
                                            </div>
                                            <div class="col-xs-6 element-left">
                                                <label class="form-control-static"><b>{{m.channelingTypeName}}</b></label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6 element-right">
                                                <label class="form-control-static">Fecha de inasistencia:</label>
                                            </div>
                                            <div class="col-xs-6 element-left">
                                                <label class="form-control-static"><b>{{m.absenceDateStart}} al {{m.absenceDateEnd}}</b></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h6><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp; Reagendar cita (Elija la fecha y la hora)</h6>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-4 col-xs-offset-4">
                                                <label for="id-date-picker-start" class="control-label">Fecha para reagendar:</label>
                                                <div class="row">
                                                    <div class="col-xs-10">
                                                        <div class="input-group">
                                                            <input class="form-control date-picker" id="id-date-picker-start" readonly="readonly"
                                                                   type="text" data-date-format="dd-mm-yyyy" ng-model="m.dateStart" ng-init=""/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-3 col-xs-offset-3">
                                                <label for="id-timepicker-start" class="control-label">Hora inicial:</label>

                                                <div class="row">
                                                    <div class="col-xs-10">
                                                        <div class="input-group bootstrap-timepicker">
                                                            <input id="id-timepicker-start" type="text" readonly="readonly" ng-model="m.timeStart"
                                                                   class="form-control umeca-time-picker"/>
															<span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <label for="id-timepicker-end" class="control-label">Hora final:</label>

                                                <div class="row">
                                                    <div class="col-xs-10">
                                                        <div class="input-group bootstrap-timepicker">
                                                            <input id="id-timepicker-end" type="text" readonly="readonly" ng-model="m.timeEnd"
                                                                   class="form-control umeca-time-picker"/>
															<span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="row" ng-show="MsgError">
                                            <br/>
                                            <div class="col-xs-12">
                                                <div class="alert alert-danger element-center"  ng-bind-html="MsgError">
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
                    <button class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </button>
                    <button ng-show="{{(m.rescheduleAppointmentId >= 0 ? false : true)}}"
                            class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                            ng-click="submitRescheduleIfValid('#FormUpId', '<c:url value='/supervisor/channelingTrack/doReschedule.json' />', undefined, FormUpId.$valid, submit)">
                        Guardar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

