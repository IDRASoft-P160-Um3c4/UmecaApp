<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormUpId");
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px">
            <div class="modal-content"  ng-controller="channelingTrackController">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h5 class="element-center"><i class="glyphicon glyphicon-check"></i>&nbsp;&nbsp;Justificar inasistencia</h5>
                    </div>
                </div>
                <div class="modal-body"
                     ng-init='m = ${model};'>
                    <form id="FormUpId" name="FormUpId" ng-submit="submit('#FormUpId')" class="form-horizontal"
                          role="form">
                        <br/>
                        <input type="hidden" ng-update-hidden ng-model="m.actMonPlanId" name="actMonPlanId" id="actMonPlanId">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h6><i class="glyphicon glyphicon-hand-right"></i>&nbsp;&nbsp;{{(m.isJustified >= 0 ? 'Consultar' : 'Realizar')}} justificaci&oacute;n</h6>
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
                                        <h6><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;Justificaci&oacute;n de la inasistencia</strong>"</h6>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-12 element-center">
                                                <label class="form-control-static">&iquest;Desea justificar la inasistencia?</label>
                                            </div>
                                        </div>
                                        <div class="space-8"></div>
                                        <div class="row">
                                            <div class="col-xs-12 element-center">
                                                <input type="radio" name="isJustified" ng-disabled="{{(m.isJustified >= 0 ? true : false)}}"
                                                       ng-model="m.toJustified" ng-init="m.toJustified = (m.isJustified >=0 ? m.isJustified : 1)" ng-value="1">&nbsp;Si
                                                &nbsp; &nbsp; &nbsp; <input type="radio" name="isJustified" ng-model="m.toJustified" ng-value="0">&nbsp;No
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-blue2">
                                                        <h6 class="smaller">Raz&oacute;n por la que justifica o no la inasistencia</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main">
                                                            <textarea id="comment" name="comment" ng-model="m.comment" rows="5" ng-disabled="{{(m.isJustified >= 0 ? true : false)}}"
                                                                 class="form-control"></textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="row" ng-show="m.comment && {{(m.isJustified >= 0 ? false : true)}}">
                                            <div class="col-xs-8 col-xs-offset-2">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-blue2">
                                                        <h6 class="smaller">Ingrese su contrase&ntilde;a para confirmar</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main">
                                                            <input type="password" id="password" name="password" ng-model="m.password"
                                                                   class="form-control" rows="5">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
                    <button ng-show="{{(m.isJustified >= 0 ? false : true)}}"
                            m.isJustified class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true || !m.password  || !m.comment"
                            ng-click="submit('#FormUpId', '<c:url value='/supervisor/channelingTrack/doUpsert.json' />', undefined, FormUpId.$valid)">
                        Guardar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

