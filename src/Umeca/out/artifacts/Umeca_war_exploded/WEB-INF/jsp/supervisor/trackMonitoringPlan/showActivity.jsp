<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
<div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
<div class="modal-dialog" style="width:900px" ng-controller="monActivityController" ng-init="status='${status}'; isReadOnly=${isReadOnly};">
<div class="modal-content">
<div class="modal-header">
    <div class="alert alert-info ">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="element-center"><i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Actividad</h4>
    </div>
</div>
<div class="modal-body">
<form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
      role="form">
<input type="hidden" name="actMonPlanId" id="actMonPlanId" value="${actMonPlanId}"/>

<div class="row">
    <div class="col-xs-12">
        <div class="panel panel-default panel-primary">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci&oacute;n general
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Caso</div>
                                <div class="profile-info-value">
                                    <span id="case">${caseId}&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Carpeta Judicial</div>

                                <div class="profile-info-value">
                                    <span id="mpId">${mpId}&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Imputado</div>
                                <div class="profile-info-value">
                                    <span id="fullName">${fullName}&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Estatus</div>
                                <div class="profile-info-value">
                                    <span id="status">${status}&nbsp;</span>
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

<div class="row">
    <div class="col-xs-12">
        <div class="panel panel-default panel-primary">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci&oacute;n de la actividad
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Actividad de supervisi&oacute;n</div>
                                <div class="profile-info-value">
                                    <span id="actSup">${actSup}&nbsp;<br/>&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Objetivo de la actividad</div>
                                <div class="profile-info-value">
                                    <span id="objAct">${actGoal}&nbsp;<br/>&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Fuentes</div>
                                <div class="profile-info-value">
                                    <span id="actSources">${actSources}&nbsp;<br/>&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Estatus</div>
                                <div class="profile-info-value">
                                    <span id="actStatus"><strong>${actStatus}</strong>&nbsp;<br/>&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Fecha inicial</div>
                                <div class="profile-info-value">
                                    <span id="actInitDate">${actInitDate}&nbsp;<br/><strong>${actInitTime}</strong>&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Fecha final</div>
                                <div class="profile-info-value">
                                    <span id="actEndDate">${actEndDate}&nbsp;<br/><strong>${actEndTime}</strong>&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="hr hr-24"></div>
                <div class="row" ng-show="!suspendBlock">
                    <div class="col-xs-12">
                        <table class="table table-bordered table-striped">
                            <thead class="thin-border-bottom">
                            <tr>
                                <th class="col-xs-7">
                                    <i class="icon-caret-right dark"></i>
                                    Obligaciones procesales del imputado
                                </th>

                                <th>
                                    <i class="icon-caret-right dark"></i>
                                    Estado
                                </th>
                            </tr>
                            </thead>

                            <tbody ng-init='lstArrangements = ${lstArrangements}; initArrangements();'>

                            <tr ng-repeat="e in lstArrangements">
                                <td><i class="icon-bookmark blue"></i> &nbsp; {{e.name}} / {{e.description}}</td>
                                <td class="element-center">
                                    <input type="radio" ng-disabled="${isReadOnly} || m.notDo" name="rd{{e.id}}" ng-model = "m.lstArrangements[e.id]" ng-change="updateArr();" value="1">Cumplido  &nbsp;&nbsp;
                                    <input type="radio" ng-disabled="${isReadOnly} || m.notDo" name="rd{{e.id}}" ng-model = "m.lstArrangements[e.id]" ng-change="updateArr();" value="0">Incumplido &nbsp;&nbsp;
                                    <input type="radio" ng-disabled="${isReadOnly} || m.notDo" name="rd{{e.id}}" ng-model = "m.lstArrangements[e.id]" ng-change="updateArr();" value="-1">No definido
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="hidden"  name="arrangementsValues" id="arrangementsValues" ng-update-hidden ng-model="m.arrangementsValues" />
                    </div>
                </div>
                <div class="row" ng-show="hasChanneling && hasActGoalChannelingTrack"
                     ng-init="hasChanneling = ${hasChanneling}; hasActGoalChannelingTrack = ${hasActGoalChannelingTrack};" >
                    <div class="col-xs-12">
                        <table class="table table-bordered table-striped">
                            <thead class="thin-border-bottom">
                            <tr>
                                <th class="col-xs-7">
                                    <i class="icon-caret-right dark"></i>
                                    Seguimiento a la canalizaci&oacute;n
                                </th>
                                <th>
                                    <i class="icon-caret-right dark"></i>
                                    Asistencia
                                </th>
                            </tr>
                            </thead>

                            <tbody>

                            <tr>
                                <td>
                                    <div class="row">
                                        <div class="col-xs-6">
                                            <i class="icon-asterisk blue"></i> &nbsp;
                                            <strong>Tipo de canalizaci&oacute;n:</strong><br/>
                                        </div>
                                        <div class="col-xs-6">
                                            ${channelingType}
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-6">
                                            <i class="icon-asterisk blue"></i> &nbsp;
                                            <strong>Nombre de la canalizaci&oacute;n:</strong><br/>
                                        </div>
                                        <div class="col-xs-6">
                                            ${channelingName}
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="row">
                                        <div class="col-xs-offset-1 col-xs-11">
                                            <input type="radio" ng-disabled="${isReadOnly}" name="channelingAssistance"
                                                   ng-model = "m.channelingAssistance" value="1"
                                                   ng-init="m.channelingAssistance = ${channelingAssistance}"> Asisti&oacute;
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-offset-1 col-xs-11">
                                            <input type="radio" ng-disabled="${isReadOnly}"
                                                   name="channelingAssistance" ng-model = "m.channelingAssistance" value="0"> No asisti&oacute;
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<div class="row" ng-show="isReadOnly">
    <div class="col-xs-12">
        <div class="panel panel-default panel-primary">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Conclusi&oacute;n de la actividad
            </div>
            <div class="panel-body">

                <div class="row">
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Supervisor</div>
                                <div class="profile-info-value">
                                    <span id="actSupervisorDone"><strong>${actSupervisorDone}&nbsp;</strong></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Fecha conclusi&oacute;n</div>
                                <div class="profile-info-value">
                                    <span id="actEndFullDate"><strong>${actEndFullDate}&nbsp;</strong></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name"> Comentarios</div>
                                <div class="profile-info-value">
                                    <span id="actComments">${actComments}&nbsp;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row" ng-show="!isReadOnly&&!suspendBlock">
    <br/>

    <div class="col-xs-8 col-xs-offset-2">
        <div class="panel panel-default panel-success">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-check"></span>&nbsp;&nbsp;Acci&oacute;n sobre la actividad
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-4 col-xs-offset-2 element-center">
                                                <span class="btn btn-danger btn-sm" ng-click="notDo()">
                                                    <i class="glyphicon glyphicon-remove"></i>    No realizada
                                                </span>
                    </div>
                    <div class="col-xs-4 element-center">
                                                <span class="btn btn-success btn-sm" ng-click="do()">
                                                    <i class="glyphicon glyphicon-ok"></i>    Realizada
                                                </span>
                    </div>
                </div>
                <div class="row" ng-show="m.do">
                    <br/>

                    <div class="col-xs-12 widget-container-span">
                        <div class="widget-box">
                            <div class="widget-header widget-header-small header-color-green">
                                <h6>Observaciones</h6> (Ingrese las observaciones sobre la actividad realizada)
                            </div>
                            <div class="widget-body">
                                <div class="widget-main padding-12">
                                    <textarea id="commentsOk" name="commentsOk" ng-model="m.commentsOk"
                                              class="form-control" rows="5"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" ng-show="m.notDo">
                    <br/>

                    <div class="col-xs-12 widget-container-span">
                        <div class="widget-box">
                            <div class="widget-header widget-header-small header-color-red">
                                <h6>Observaciones</h6> (Ingrese las razones por la cual no se llev&oacute; acabo la actividad)
                            </div>
                            <div class="widget-body">
                                <div class="widget-main padding-12">
                                    <textarea id="commentsFail" name="commentsFail" ng-model="m.commentsFail"
                                              class="form-control" rows="5"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" ng-show="!((!m.do && !m.notDo) || (m.do && !m.commentsOk) || (m.notDo && !m.commentsFail))">
                    <div class="hr hr-2"></div>
                    <div class="checkbox">
                        <input name="form-field-checkbox" type="checkbox" ng-model="m.isAgree" />
                        <span class="lbl"> &iquest;Est&aacute; de acuerdo con el estado de las obligaciones procesales?</span>
                    </div>
                    <div class="hr hr-2"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div ng-show="MsgError" class="alert alert-danger element-center"  ng-bind-html="MsgError">
            <%--{{MsgError}}--%>
        </div>
    </div>
</div>
</div>
<div class="modal-footer" id="btn-act-footer">
    <button class="btn btn-default btn-sm" ng-show="!isReadOnly" ng-click="cancel()">Cancelar</button>
    <button class="btn btn-success btn-sm" ng-show="isReadOnly" ng-click="cancel()">Regresar</button>
    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
            ng-show="m.isAgree"
            ng-click="submit('#FormCatId', '<c:url value='/supervisor/trackMonitoringPlan/doActionActivity.json' />')">
        Aceptar
    </button>
</div>
</div>
</div>
</div>
</div>