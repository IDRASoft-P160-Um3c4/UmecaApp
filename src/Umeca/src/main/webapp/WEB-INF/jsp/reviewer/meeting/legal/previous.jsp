<form id="FormPreviousLegalId" name="FormPreviousLegalId" class="form-horizontal" role="form">
<div class="row">
    <div class="col-xs-10  col-xs-offset-1">
        <div class="row element-center">
            <h2><i class="gray  icon-legal  bigger-150"></i> &nbsp;Procesos anteriores</h2>
        </div>
        <br/>
    <div class="row">
        <div class="col-xs-4">
            Primer proceso: <br/>
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 5 y m&aacute;ximo 255 caracteres"
                   data-val-length-max="255" data-val-length-min="5"
                   data-val-required="Primer proceso es un campo requerido"
                   type="text" ng-model="m.firstProceeding" id="firstProceeding"
                   name="firstProceeding"
                   ng-init='m.firstProceeding = "";'>
            <span class="field-validation-valid" data-valmsg-for="firstProceeding" data-valmsg-replace="true"></span>
        </div>
        <div class="col-xs-4">
            N&uacute;mero de procesos abiertos: <br/>
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y m&aacute;ximo 3 caracteres"
                   data-val-length-max="3" data-val-length-min="1"
                   data-val-required="Procesos abiertos es un campo requerido"
                   data-val-regex-pattern="([0-9]+)" data-val-regex="El n&uacute;mero de procesos abiertos s&oacute;lo puede contener n&uacute;meros"
                   type="text" ng-model="m.openProcessNumber" id="openProcessNumber"
                   name="openProcessNumber"
                   ng-init='m.openProcessNumber = "";'>
            <span class="field-validation-valid" data-valmsg-for="openProcessNumber" data-valmsg-replace="true"></span>
        </div>
        <div class="col-xs-4">
            N&uacute;mero de sentencias condenatorias: <br/>
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y m&aacute;ximo 3 caracteres"
                   data-val-length-max="3" data-val-length-min="1"
                   data-val-required="Sentencias condenatorias es un campo requerido"
                   data-val-regex-pattern="([0-9]+)" data-val-regex="El n&uacute;mero de sentencias condenatorias s&oacute;lo puede contener n&uacute;meros"
                   type="text" ng-model="m.numberConvictions" id="numberConvictions"
                   name="numberConvictions"
                   ng-init='m.numberConvictions = "";'>
            <span class="field-validation-valid" data-valmsg-for="numberConvictions" data-valmsg-replace="true"></span>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-4">
            <br/>
            &iquest;Cumpli&oacute; con medidas cautelares?: <br/>
            <input type="hidden" ng-model="m.complyPMId" ng-update-hidden name="complyPMId">
            <select class="form-control element-center" ng-model="m.complyPM"
                    ng-options="e.name for e in listElection"
                    ng-change="m.complyPMId = m.complyPM.id"
                    ng-init='listElection = ${listElection};'></select>
        </div>
        <div class="col-xs-4">
            <br/>
            &iquest;Cumpli&oacute; con SCPP?: <br/>
            <input type="hidden" ng-model="m.complyCSPPId" ng-update-hidden name="complyCSPPId">
            <select class="form-control element-center" ng-model="m.complyCSPP"
                    ng-options="e.name for e in listElection"
                    ng-change="m.complyCSPPId = m.complyCSPP.id"
                    ng-init='listElection = ${listElection};'></select>
        </div>
        <div class="col-xs-4">
            &iquest;Permiti&oacute; o colabor&oacute; con procesos anteriores?: <br/>
            <input type="hidden" ng-model="m.complyProcessAboveId" ng-update-hidden name="complyProcessAboveId">
            <select class="form-control element-center" ng-model="m.complyProcessAbove"
                    ng-options="e.name for e in listElection"
                    ng-change="m.complyProcessAboveId = m.complyProcessAbove.id"
                    ng-init='listElection = ${listElection};'></select>
        </div>
    </div>
        <br/>
        <div class="row">
            <input type="hidden" ng-model="listLegalBefore" ng-init='listLegalBefore = ${listLegalBefore};' ng-update-hidden/>
            <div class="col-xs-12" ng-show="listLegalBefore.length == 0">
                <div class="alert-info element-center info-font">
                    No se encontraron procesos para el imputado ${fullNameImputed}.
                </div>
            </div>
            <div class="col-xs-12" ng-show="listLegalBefore.length > 0">


                        <div class="widget-header header-color-blue">
                            <h5 class="bigger lighter">
                                Procesos legales anteriores
                            </h5>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead class="thin-border-bottom">
                                    <tr>
                                        <th>
                                            Carpeta Judicial
                                        </th>

                                        <th>
                                            Carpeta de investigaci&oacute;n
                                        </th>
                                        <th>Estatus del caso</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr ng-repeat="case in listLegalBefore">
                                        <td class="">{{case.idMP}}</td>
                                        <td>
                                          {{case.idFolder}}
                                        </td>

                                        <td>
                                            {{case.statusCase}}
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
    </form>

