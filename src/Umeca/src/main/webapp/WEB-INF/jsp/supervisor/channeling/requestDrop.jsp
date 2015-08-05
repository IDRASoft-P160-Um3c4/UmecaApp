<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormUpId");
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:700px">
            <div class="modal-content"  ng-controller="channelingController">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h5 class="element-center"><i class="glyphicon glyphicon-screenshot"></i>&nbsp;&nbsp;Canalizaciones</h5>
                    </div>
                </div>
                <div class="modal-body"
                         ng-init='m = ${channeling}; lstChannelingDropType = ${lstChannelingDropType}; initDropCatalogs();'>
                    <form id="FormUpId" name="FormUpId" class="form-horizontal"
                          role="form">
                        <br/>
                        <input type="hidden" ng-update-hidden ng-model="m.caseId" name="caseId" id="caseId">
                        <input type="hidden" ng-update-hidden ng-model="m.channelingId" name="channelingId" id="channelingId">
                        <input type="hidden" ng-update-hidden ng-model="m.channelingDropTypeId" name="channelingDropTypeId" id="channelingDropTypeId">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h6><i class="glyphicon glyphicon-map-marker"></i>&nbsp;&nbsp;Solicitud de baja de canalizaci&oacute;n</h6>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="row" ng-show="m.consecutiveTx">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">N&uacute;mero de canalizaci&oacuten:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.consecutiveTx}}</strong></label>
                                                    </div>
                                                </div>
                                                <div class="row" ng-show="m.name">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Canalizaci&oacuten:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.name}}</strong></label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Nombre del imputado:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.imputed}}</strong></label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Supervisor:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.supervisor}}</strong></label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Causa penal / # carpeta judicial:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.idMP}}</strong></label>
                                                    </div>
                                                </div>
                                                <br />
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Tipo de baja:</label>
                                                    </div>
                                                    <div class="col-xs-4 element-left">
                                                        <select class="form-control element-center"
                                                                ng-model="m.channelingDropType"
                                                                ng-change="m.channelingDropTypeId = m.channelingDropType.id"
                                                                ng-options="e.name for e in lstChannelingDropType"/>
                                                    </div>
                                                </div>
                                                <br/>
                                                <div class="row">
                                                    <div class="col-xs-12 element-center">
                                                        <label class="form-control-static">Observaciones:</label>
                                                    </div>
                                                    <div class="col-xs-8 col-xs-offset-2 element-center">
                                                        <textarea class="form-control" name="comments" rows="4"
                                                                  ng-required="true" ng-maxlength="100" ng-model="m.comments"/>
                                                        <span class="error" ng-show="FormUpId.comments.$error.required">Campo requerido</span>
                                                        <span class="error" ng-show="FormUpId.comments.$error.maxlength">Longitud m&aacute;xima de 100 caracteres</span>
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
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </button>
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                            ng-click="submit('#FormUpId', '<c:url value='/supervisor/channeling/doRequestDrop.json' />', undefined, FormUpId.$valid)">
                        Solicitar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

