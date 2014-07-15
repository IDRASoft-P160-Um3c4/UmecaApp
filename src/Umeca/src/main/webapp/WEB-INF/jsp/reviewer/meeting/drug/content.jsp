<div class="row">
    <input type="hidden" ng-update-hidden ng-model="id" name="idCase" id="idCase"
           ng-init="idCase = ${(idCase == null) ? 0 : idCase};">
    <input type="hidden" ng-update-hidden ng-model="d.typeId" name="drugType.id" id="typeId"
           ng-init="d.typeId = ${typeId == null ? "undefined" : typeId};">
    <input type="hidden" ng-update-hidden ng-model="d.perId" name="periodicity.id" id="perId"
           ng-init="d.perId = ${perId == null ? "undefined" : perId};">
    <input type="hidden" ng-update-hidden ng-model="id" name="id" id="id"
           ng-init='id = "${(d.id == null)? '':d.id}"'>

    <div class="col-xs-2">
        Sustancia:
    </div>
    <div class="col-xs-4">
        <select class="form-control element-center" ng-model="d.type"
                ng-options="e.name for e in lstType"
                ng-change="d.typeId = d.type.id"
                ng-init='lstType = ${lstDrugType};'></select>

    </div>
    <div class="col-xs-2">
        Periodicidad:
    </div>
    <div class="col-xs-4">
        <select class="form-control element-center" ng-model="d.per"
                ng-options="e.name for e in lstPer"
                ng-change="d.perId = d.per.id"
                ng-init='lstPer = ${lstPeriodicity};'></select>
    </div>
</div>
<br/>
<div class="row" ng-show="d.type.specification==true">
    <div class="col-xs-2" ng-show="d.type.specification==true" >
        Especifíque sustancia:
    </div>
    <div class="col-xs-4" ng-show="d.type.specification==true">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y máximo 100 caracteres"
               data-val-length-max="100" data-val-length-min="3" data-val-required="La especificación es un campo requerido"
               type="text" value="" ng-model="d.other" ng-init='d.other = "${(d.specificationType == null) ? '' : d.specificationType}"' id="specificationType" name="specificationType">
        <br/>
        <span class="field-validation-valid" data-valmsg-for="specificationType" data-valmsg-replace="true"></span>
    </div>
    <div class="col-xs-2"   ng-show="d.per.specification==true">
        Especifíque periodicidad:
    </div>
    <div class="col-xs-4" ng-show="d.per.specification==true">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y máximo 100 caracteres"
               data-val-length-max="100" data-val-length-min="3" data-val-required="La especificación es un campo requerido"
               type="text" value="" ng-model="d.specificationPeriodicity" ng-init='d.specificationPeriodicity = "${(d.specificationPeriodicity == null) ? '' : d.specificationPeriodicity}"'
               id="specificationPeriodicity" name="specificationPeriodicity">
        <br/>
        <span class="field-validation-valid" data-valmsg-for="specificationPeriodicity" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2">
        Cantidad:
    </div>
    <div class="col-xs-4">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 2 y máximo 25 caracteres"
               data-val-length-max="25" data-val-length-min="2" data-val-required="La cantidad es un campo requerido"
               type="text"  ng-model="d.quantity" ng-init='d.quantity="${(d.quantity == null) ? '' : d.quantity}";' name="quantity" id="quantity">
        <br/>
        <span class="field-validation-valid" data-valmsg-for="quantity" data-valmsg-replace="true"></span>
    </div>

    <div class="col-xs-2">
        Último consumo:
    </div>
    <div class="col-xs-4">
        <div class="input-group">
            <input class="form-control date-picker" id="lastUse" data-val="true" type="text" data-date-format="yyyy/mm/dd" value="${d.lastUse}" readonly="readonly"
                   name="lastUse" data-val-required="La última fecha de consumo es un campo requerido"/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
        </div>
        <br/>
        <span class="field-validation-valid" data-valmsg-for="lastUse" data-valmsg-replace="true"></span>
    </div>
</div>
