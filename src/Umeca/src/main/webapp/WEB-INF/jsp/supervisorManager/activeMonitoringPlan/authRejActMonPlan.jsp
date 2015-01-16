<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" data-backdrop="static" data-keyboard="false" ng-cloak>
        <div ng-controller="authRejActMonPlanController" ng-init='lstActivities=${lstActivities}; init(lstActivities);
        urlToGo="<c:url value="/supervisorManager/activeMonitoringPlan/showMonActDetail.json"/>"'>
            <div class="modal-dialog" style="width:980px">
                <div class="modal-content" ng-show="detail==='-' || detail === false" ng-class="((detail === false)?'animate fleft':'')">
                    <div class="modal-header">
                        <div class="alert alert-info ">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="element-center"><i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Actividades
                                nuevas, modificadas o eliminadas del plan de supervisi&oacute;n</h4>
                        </div>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci&oacute;n
                                        general
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> Imputado</div>
                                                        <div class="profile-info-value">
                                                            <span id="fullName">${fullName}&nbsp;</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
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
                        <div class="row">
                             <div class="col-xs-5 col-xs-offset-1">
                                 <div class="row">
                                     <input type="radio" ng-change="changeAll()" ng-model="m.authAll" value="1" name="p">
                                     <span class="green">Autorizar todas</span>
                                     <strong><i class="glyphicon glyphicon-thumbs-up green"></i></strong><br/>
                                 </div>
                                 <div class="row">
                                     <input type="radio" ng-change="changeAll()" ng-model="m.authAll" value="0" name="p">
                                     <span class="red">Rechazar todas</span>
                                     <strong><i class="glyphicon glyphicon-thumbs-down red"></i></strong>
                                 </div>
                             </div>
                            <div class="col-xs-6 align-right" ng-model="filterStatusActivity">
                                <div class="col-xs-4">
                                    Ver actividades:
                                </div>
                                <div class="col-xs-7">
                                    <select class="form-control" ng-change="filterActivities();" ng-model="filterSelected">
                                        <option value="ALL">Todas</option>
                                        <option value="PRE_NUEVA">Nueva</option>
                                        <option value="PRE_MODIFICADA">Modificada</option>
                                        <option value="PRE_ELIMINADA">Eliminada</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;Listado de
                                        actividades
                                    </div>
                                    <div class="panel-body">
                                        <table class="table table-striped table-bordered table-hover"
                                               style="display: block; height: 400px; overflow-x: hidden; overflow-y: auto">
                                            <thead class="thin-border-bottom">
                                            <tr>
                                                <th class="col-xs-2">
                                                    <i class="icon-legal"></i>
                                                    Autorizar/Rechazar
                                                </th>
                                                <th class="col-xs-4 element-center">
                                                    <i class="icon-list-ul"></i>
                                                    Actividad de supervisi&oacute;n
                                                </th>
                                                <th class="col-xs-2 element-center">
                                                    <i class="icon-calendar"></i>
                                                    Fecha inicio
                                                </th>

                                                <th class="col-xs-2 element-center">
                                                    <i class="icon-calendar"></i>
                                                    Fecha fin
                                                </th>
                                                <th class="col-xs-2 element-center">
                                                    <i class="icon-tag"></i>
                                                    Estatus
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr ng-repeat="act in lstActivities" ng-show="act.visible">
                                                <td class="">
                                                    <input type="radio" name="rd{{act.activityMonId}}"
                                                           ng-change="onChangeRejAuth()" ng-model="m.lstAutRejActMon[$index].value" value="1">
                                                    &nbsp;<span
                                                        ng-class="(m.lstAutRejActMon[$index].value === '1' ? 'green' : '')">Autorizar</span>
                                                    &nbsp;&nbsp;
                                                    <strong><i ng-show="m.lstAutRejActMon[$index].value === '1'"
                                                               class="glyphicon glyphicon-thumbs-up green"></i></strong><br/>
                                                    <input type="radio" name="rd{{act.activityMonId}}"
                                                           ng-change="onChangeRejAuth()" ng-model="m.lstAutRejActMon[$index].value" value="0">
                                                    &nbsp;<span
                                                        ng-class="(m.lstAutRejActMon[$index].value === '0' ? 'red' : '')">Rechazar</span>
                                                    &nbsp;&nbsp;
                                                    <strong><i ng-show="m.lstAutRejActMon[$index].value === '0'"
                                                               class="glyphicon glyphicon-thumbs-down red"></i></strong>
                                                </td>
                                                <td class="">
                                                    <div class="row">
                                                        <div class="col-xs-2">
                                                            <button ng-click="showActivity(act.activityMonId, $index+1)"
                                                                    title="Mostrar detalles de la actividad"
                                                                    class="btn btn-info btn-sm">
                                                                <i class=" glyphicon glyphicon-th-list"></i>
                                                            </button>
                                                        </div>
                                                        <div class="col-xs-10">
                                                            <strong>{{$index+1}}.</strong>&nbsp;{{act.activityName}}
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="element-center">{{act.start}}</td>
                                                <td class="element-center">{{act.end}}</td>
                                                <td class="element-center">
                                                    <span ng-class="createLabel(act.status)">{{act.status.replace('PRE_','')}}</span>
                                                    <br/>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.isOk" ng-change="scrollBottom()"/>
                                        <span class="control-label">{{countAuth}} aprobado(s) y {{countRej}} rechazado(s), marque la casilla para estar de acuerdo y continuar</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row" ng-show="m.isOk">
                            <div class="col-xs-12">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Observaciones
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small"
                                                         class="header-color-green">
                                                        <h6>Observaciones</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <textarea id="comments" ng-change="scrollBottom()" name="comments" ng-model="m.comments" class="form-control" rows="4"></textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="m.comments">
                                            <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small"
                                                         ng-class="header-color-green">
                                                        <h6>Ingrese su contrase&ntilde;a para validar su usuario</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <input id="password" type="password" ng-change="scrollBottom()" name="password" ng-model="m.password"
                                                                   class="form-control" />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div ng-show="MsgError" class="alert alert-danger element-center"
                                ng-bind-html="MsgError">
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" id="btn-act-footer">
                        <button id="btn-cancel-upsert" class="btn btn-default btn-sm" ng-click="cancel(true)">Regresar
                        </button>
                        <button class="btn btn-primary btn-sm" ng-show="m.password && m.comments && m.isOk"
                                ng-click="doSave('<c:url value="/supervisorManager/activeMonitoringPlan/authRejLstMonAct.json"/>')">Guardar
                        </button>
                    </div>
                </div>
                <div class="modal-content animate tleft" ng-show="detail===true">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Detalle de la actividad "{{iAct}}"</h4>
                    </div>
                </div>
                <div class="modal-body">
                <div class="row">
                <div class="col-xs-12">
                <div class="tabbable">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a data-toggle="tab" href="#newAct">
                            <i ng-class="getColor(dtNew.actStatus) + ' icon-th bigger-110'"></i>
                            Actividad {{dtNew.actStatus.replace('PRE_','').toLowerCase()}}
                        </a>
                    </li>
                    <li ng-show="dtNew.actStatus === 'PRE_MODIFICADA'">
                        <a data-toggle="tab" href="#oldAct">
                            <i class="blue icon-th bigger-110"></i>
                            Actividad sin modificar
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                <div id="newAct" class="tab-pane in active">
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
                                                        <span>{{dtNew.caseId}}&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Carpeta Judicial</div>

                                                    <div class="profile-info-value">
                                                        <span>{{dtNew.mpId}}&nbsp;</span>
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
                                                        <span>{{dtNew.personName}}&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Estatus</div>
                                                    <div class="profile-info-value">
                                                        <span>{{dtNew.status}}&nbsp;</span>
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
                                                        <span>{{dtNew.supervisionActivityName}}&nbsp;<br/>&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Objetivo de la actividad</div>
                                                    <div class="profile-info-value">
                                                        <span>{{dtNew.activityGoalName}}&nbsp;<br/>&nbsp;</span>
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
                                                        <span>{{dtNew.aidSourceName}}&nbsp;<br/>&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Estatus</div>
                                                    <div class="profile-info-value">
                                                        <span ng-class="createLabel(dtNew.actStatus)">{{dtNew.actStatus.replace('PRE_','')}}</span>
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
                                                        <span><strong>{{dtNew.startSt}}</strong>&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Fecha final</div>
                                                    <div class="profile-info-value">
                                                        <span><strong>{{dtNew.endSt}}</strong>&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr hr-24"></div>
                                    <div class="row">
                                        <div class="col-xs-12">

                                            <table class="table table-bordered table-striped">
                                                <thead class="thin-border-bottom">
                                                <tr>
                                                    <th class="col-xs-10">
                                                        <i class="icon-caret-right dark"></i>
                                                        Obligaciones procesales del imputado
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td><i class="icon-bookmark blue"></i> &nbsp; {{dtNew.assignedArrangement}}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="oldAct" class="tab-pane">
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
                                                        <span>{{dtOld.caseId}}&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Carpeta Judicial</div>

                                                    <div class="profile-info-value">
                                                        <span>{{dtOld.mpId}}&nbsp;</span>
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
                                                        <span>{{dtOld.personName}}&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Estatus</div>
                                                    <div class="profile-info-value">
                                                        <span>{{dtOld.status}}&nbsp;</span>
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
                                                        <span>{{dtOld.supervisionActivityName}}&nbsp;<br/>&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Objetivo de la actividad</div>
                                                    <div class="profile-info-value">
                                                        <span>{{dtOld.activityGoalName}}&nbsp;<br/>&nbsp;</span>
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
                                                        <span>{{dtOld.aidSourceName}}&nbsp;<br/>&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Estatus</div>
                                                    <div class="profile-info-value">
                                                        <span ng-class="createLabel(dtOld.actStatus)">{{dtOld.actStatus.replace('PRE_','')}}</span>
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
                                                        <span><strong>{{dtOld.startSt}}</strong>&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"> Fecha final</div>
                                                    <div class="profile-info-value">
                                                        <span><strong>{{dtOld.endSt}}</strong>&nbsp;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="hr hr-24"></div>
                                    <div class="row">
                                        <div class="col-xs-12">

                                            <table class="table table-bordered table-striped">
                                                <thead class="thin-border-bottom">
                                                <tr>
                                                    <th class="col-xs-10">
                                                        <i class="icon-caret-right dark"></i>
                                                        Obligaciones procesales del imputado
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td><i class="icon-bookmark blue"></i> &nbsp; {{dtOld.assignedArrangement}}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
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
                </div>
                <div class="modal-footer" id="btn-act-footer-sec">
                    <button class="btn btn-default btn-sm" ng-click="switchWindow()">Regresar
                    </button>
                </div>
                </div>
                <div class="modal-content" ng-show="detail==='resMsg'" ng-class="((detail==='resMsg')?'animate tleft':'')">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-tasks"></i>&nbsp;&nbsp;Autorizar / rechazar actividades</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel panel-default panel-primary">
                                <div class="panel-heading">
                                    <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;El proceso se realiz&oacute; de forma correcta
                                </div>
                                <div class="panel-body">
                                    <div class="col-xs-12">
                                        <div ng-show="MsgResponse" class="alert alert-success element-center"
                                             ng-bind-html="MsgResponse">
                                            <br/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default btn-sm" ng-click="cancel(true)">Aceptar </button>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>

