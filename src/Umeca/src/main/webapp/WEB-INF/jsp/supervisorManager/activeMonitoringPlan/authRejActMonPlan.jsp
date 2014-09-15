<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:980px" ng-controller="authRejActMonPlanController" ng-init='lstActivities=${lstActivities}; init(lstActivities);'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Actividades nuevas, modificadas o eliminadas del plan de seguimiento</h4>
                    </div>
                </div>
                <div class="modal-body">
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
                                    <span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;Listado de actividades
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead class="thin-border-bottom">
                                        <tr>
                                            <th class="col-xs-2">
                                                <i class="icon-legal"></i>
                                                Autorizar/Rechazar
                                            </th>
                                            <th class="col-xs-4">
                                                <i class="icon-list-ul"></i>
                                                Actividad de supervisi&oacute;n
                                            </th>
                                            <th class="col-xs-2">
                                                <i class="icon-calendar"></i>
                                                Fecha inicio
                                            </th>

                                            <th class="col-xs-2">
                                                <i class="icon-calendar"></i>
                                                Fecha fin
                                            </th>
                                            <th class="col-xs-2">
                                                <i class="icon-tag"></i>
                                                Estatus
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <tr ng-repeat="act in lstActivities">
                                                <td class="">
                                                    <input type="radio" name="rd{{act.activityMonId}}" ng-model = "m.lstAutRejActMon[$index].value" value="1">
                                                    &nbsp;<span ng-class="(m.lstAutRejActMon[$index].value == 1 ? 'green' : '')">Autorizar</span>  &nbsp;&nbsp;
                                                    <strong><i ng-show="m.lstAutRejActMon[$index].value == 1" class="glyphicon glyphicon-thumbs-up green"></i></strong><br/>
                                                    <input type="radio" name="rd{{act.activityMonId}}" ng-model = "m.lstAutRejActMon[$index].value" value="0">
                                                    &nbsp;<span ng-class="(m.lstAutRejActMon[$index].value == 0 ? 'red' : '')">Rechazar</span>  &nbsp;&nbsp;
                                                    <strong><i ng-show="m.lstAutRejActMon[$index].value == 0" class="glyphicon glyphicon-thumbs-down red"></i></strong>
                                                </td>
                                                <td class=""><span class="label label-info"><i class="glyphicon glyphicon-list-alt"></i></span> {{act.activityName}}</td>
                                                <td class="element-center">{{act.start}}</td>
                                                <td class="element-center">{{act.end}}</td>
                                                <td class="element-center">
                                                    <span ng-class="createLabel(act.status)">{{act.status.replace('PRE_','')}}</span> <br/>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
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
                    <button class="btn btn-default btn-sm" ng-show="!isReadOnly" ng-click="cancel()">Regresar</button>
                </div>
            </div>
        </div>
    </div>
</div>

