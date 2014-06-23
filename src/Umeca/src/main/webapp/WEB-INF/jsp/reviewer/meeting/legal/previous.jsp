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
                   data-val-length="Debe tener al menos 5 y m?ximo 255 caracteres"
                   data-val-length-max="255" data-val-length-min="5"
                   data-val-required="El primer proceso es un campo requerido"
                   type="text" ng-model="m.firstProceeding" id="firstProceeding"
                   name="firstProceeding"
                   ng-init='m.firstProceeding = "";'>
            <span class="field-validation-valid" data-valmsg-for="firstProceeding" data-valmsg-replace="true"></span>
        </div>
        <div class="col-xs-4">
            Número de procesos abiertos: <br/>
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y máximo 3 caracteres"
                   data-val-length-max="3" data-val-length-min="1"
                   data-val-required="El número de procesos abiertos es un campo requerido"
                   data-val-regex-pattern="([0-9]+)" data-val-regex="El número de procesos abiertos sólo puede contener números"
                   type="text" ng-model="m.openProcessNumber" id="openProcessNumber"
                   name="openProcessNumber"
                   ng-init='m.openProcessNumber = "";'>
            <span class="field-validation-valid" data-valmsg-for="openProcessNumber" data-valmsg-replace="true"></span>
        </div>
        <div class="col-xs-4">
            Número de sentencias condenatorias: <br/>
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y máximo 3 caracteres"
                   data-val-length-max="3" data-val-length-min="1"
                   data-val-required="El número de sentencias condenatorias es un campo requerido"
                   data-val-regex-pattern="([0-9]+)" data-val-regex="El número de sentencias condenatorias sólo puede contener números"
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
           ¿Cumplió con medidas cautelares?: <br/>
            <input type="hidden" ng-model="m.complyPMId" ng-update-hidden name="complyPMId">
            <select class="form-control element-center" ng-model="m.complyPM"
                    ng-options="e.name for e in listElection"
                    ng-change="m.complyPMId = m.complyPM.id"
                    ng-init='listElection = ${listElection};'></select>
        </div>
        <div class="col-xs-4">
            <br/>
            ¿Cumplió con SCPP?: <br/>
            <input type="hidden" ng-model="m.complyCSPPId" ng-update-hidden name="complyCSPPId">
            <select class="form-control element-center" ng-model="m.complyCSPP"
                    ng-options="e.name for e in listElection"
                    ng-change="m.complyCSPPId = m.complyCSPP.id"
                    ng-init='listElection = ${listElection};'></select>
        </div>
        <div class="col-xs-4">
            ¿Permitió o colaboró con procesos anteriores?: <br/>
            <input type="hidden" ng-model="m.complyProcessAboveId" ng-update-hidden name="complyProcessAboveId">
            <select class="form-control element-center" ng-model="m.complyProcessAbove"
                    ng-options="e.name for e in listElection"
                    ng-change="m.complyProcessAboveId = m.complyProcessAbove.id"
                    ng-init='listElection = ${listElection};'></select>
        </div>
    </div>
    </div>
</div>
    </form>

