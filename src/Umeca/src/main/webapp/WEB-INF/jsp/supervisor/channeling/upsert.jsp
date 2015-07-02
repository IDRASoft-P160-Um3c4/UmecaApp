<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormUpId");
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px">
            <div class="modal-content"  ng-controller="channelingController">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h5 class="element-center"><i class="glyphicon glyphicon-screenshot"></i>&nbsp;&nbsp;Canalizaciones</h5>
                    </div>
                </div>
                <div class="modal-body"
                     ng-init='m = ${channeling}; lstDistrict = ${lstDistrict}; lstEducationLevel = ${lstEducationLevel}; lstPreventionType = ${lstPreventionType};
                     lstEconomicSupport = ${lstEconomicSupport}; lstInstitutionType = ${lstInstitutionType}; lstChannelingType = ${lstChannelingType}; initCatalogs();'>
                    <form id="FormUpId" name="FormUpId" ng-submit="submit('#FormUpId')" class="form-horizontal"
                          role="form">
                        <br/>
                        <input type="hidden" ng-update-hidden ng-model="m.caseId" name="caseId" id="caseId">
                        <input type="hidden" ng-update-hidden ng-model="m.channelingId" name="channelingId" id="channelingId">
                        <input type="hidden" ng-update-hidden ng-model="m.channelingTypeId" name="channelingTypeId" id="channelingTypeId">
                        <input type="hidden" ng-update-hidden ng-model="m.economicSupportId" name="economicSupportId" id="economicSupportId">
                        <input type="hidden" ng-update-hidden ng-model="m.institutionTypeId" name="institutionTypeId" id="institutionTypeId">
                        <input type="hidden" ng-update-hidden ng-model="m.districtId" name="districtId" id="districtId">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h6><i class="glyphicon glyphicon-map-marker"></i>&nbsp;&nbsp;{{(m.channelingId <= 0 ? 'Agregar' : 'Editar/Consultar')}} canalizaci&oacute;n</h6>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="row" ng-show="m.consecutiveTx">
                                                    <div class="col-xs-4 element-right">
                                                        <label class="form-control-static">N&uacute;mero de canalizaci&oacuten:</label>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <label class="form-control-static"><strong>{{m.consecutiveTx}}</strong></label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-4 element-right">
                                                        <label class="form-control-static">Nombre del imputado:</label>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <label class="form-control-static"><strong>{{m.imputed}}</strong></label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-4 element-right">
                                                        <label class="form-control-static">Supervisor:</label>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <label class="form-control-static"><strong>{{m.supervisor}}</strong></label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-4 element-right">
                                                        <label class="form-control-static">Causa penal / # carpeta judicial:</label>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <label class="form-control-static"><strong>{{m.idMP}}</strong></label>
                                                    </div>
                                                </div>
                                                <br />
                                                <div class="row">
                                                    <div class="col-xs-6">
                                                        <div class="row">
                                                            <div class="col-xs-5 element-right">
                                                                <label class="form-control-static">Distrito:</label>
                                                            </div>
                                                            <div class="col-xs-7 element-left">
                                                                <select class="form-control element-center"
                                                                        ng-model="district"
                                                                        ng-options="e.name for e in lstDistrict"
                                                                        ng-change="m.districtId = district.id"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <div class="col-xs-5 element-right">
                                                            <label class="form-control-static">Tipo de canalizaci&oacute;n:</label>
                                                        </div>
                                                        <div class="col-xs-7 element-left">
                                                            <select class="form-control element-center"
                                                                    ng-model="channelingType"
                                                                    ng-change="onChangeChannelingType(); m.channelingTypeId = channelingType.id"
                                                                    ng-options="e.name for e in lstChannelingType"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br/>
                                                <div class="row">
                                                    <div class="col-xs-4 element-right">
                                                        <label class="form-control-static">Nombre de la canalizaci&oacute;n:</label>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <input class="form-control" name="name" ng-required="true" ng-maxlength="100" ng-model="m.name"/>
                                                        <span class="error" ng-show="FormUpId.name.$error.required">Campo requerido</span>
                                                        <span class="error" ng-show="FormUpId.name.$error.maxlength">Longitud m&aacute;xima de 100 caracteres</span>
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
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h6><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;Canalizaci&oacuten de tipo "<strong>{{channelingType.name}}</strong>"</h6>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-4 element-right">
                                                <label class="form-control-static">Nombre de la instituci&oacute;n:</label>
                                            </div>
                                            <div class="col-xs-8">
                                                <input class="form-control" name="institutionName" ng-required="true" ng-maxlength="100" ng-model="m.institutionName"/>
                                                <span class="error" ng-show="FormUpId.institutionName.$error.required">Campo requerido</span>
                                                <span class="error" ng-show="FormUpId.institutionName.$error.maxlength">Longitud m&aacute;xima de 100 caracteres</span>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="lstEconomicSupportNew.length > 0">
                                            <br/>
                                            <div class="col-xs-4 element-right">
                                                <label class="form-control-static">Concepto:</label>
                                            </div>
                                            <div class="col-xs-6 element-left">
                                                <select class="form-control element-center"
                                                        ng-model="economicSupport"
                                                        ng-options="e.name for e in lstEconomicSupportNew"
                                                        ng-change = "m.economicSupportId = economicSupport.id"/>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="lstPreventionTypeNew.length > 0">
                                            <br/>
                                            <div class="col-xs-4 element-right">
                                                <label class="form-control-static">Concepto:</label>
                                            </div>
                                            <div class="col-xs-6 element-left">
                                                <select class="form-control element-center"
                                                        ng-model="preventionType"
                                                        ng-options="e.name for e in lstPreventionTypeNew"
                                                        ng-change = "m.preventionTypeId = preventionType.id"/>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="lstEducationLevelNew.length > 0">
                                            <br/>
                                            <div class="col-xs-4 element-right">
                                                <label class="form-control-static">Concepto:</label>
                                            </div>
                                            <div class="col-xs-6 element-left">
                                                <select class="form-control element-center"
                                                        ng-model="educationLevel"
                                                        ng-options="e.name for e in lstEducationLevelNew"
                                                        ng-change = "m.educationLevelId = educationLevel.id"/>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="lstInstitutionTypeNew.length > 0">
                                            <br/>
                                            <div class="col-xs-4 element-right">
                                                <label class="form-control-static">Tipo de instituci&oacute;n:</label>
                                            </div>
                                            <div class="col-xs-6 element-left">
                                                <select class="form-control element-center"
                                                        ng-model="institutionType"
                                                        ng-options="e.name for e in lstInstitutionTypeNew"
                                                        ng-change = "m.institutionTypeId = institutionType.id"/>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="institutionType.hasSpec">
                                            <br/>
                                            <div class="col-xs-4 element-right">
                                                <label class="form-control-static">Especifique</label>
                                            </div>
                                            <div class="col-xs-8">
                                                <input class="form-control" name="specOther" ng-required="institutionType.hasSpec" ng-maxlength="100" ng-model="m.specOther"/>
                                                <span class="error" ng-show="FormUpId.specOther.$error.required">Campo requerido</span>
                                                <span class="error" ng-show="FormUpId.specOther.$error.maxlength">Longitud m&aacute;xima de 100 caracteres</span>
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
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                            ng-click="submit('#FormUpId', '<c:url value='/supervisor/channeling/doUpsert.json' />', undefined, FormUpId.$valid)">
                        Guardar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

