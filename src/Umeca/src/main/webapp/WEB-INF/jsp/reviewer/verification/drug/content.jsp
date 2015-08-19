<input type="hidden" ng-init="d = listDrug[$index]">
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div class="row">
    <div class="col-xs-2">
        <span ng-class="lstSourceInfoDrug['drugs.drugType.id'+'.'+d.id]==true?'verified':'';">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('drugs.drugType.id',d.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification" code="drugs.drugType.id" id-element="{{d.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('drugs.drugType.id',d.id);"></i>
        </span>
        <span ng-class="lstFinalInfoDrug['drugs.drugType.id'+'.'+d.id]==true?'verified':'';">
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('drugs.drugType.id',d.id)"></i>
        </span>
        Sustancia:
    </div>
    <div class="col-xs-4">
        <input type="hidden" ng-model="d.typeId" ng-update-hidden name="drugs.drugType.id">
        <select class="form-control element-center" ng-model="d.type"
                ng-options="e.name for e in lstType"
                ng-change="d.typeId = d.type.id" value="{{d.type.id}}"
                ng-init='lstType = ${lstDrugType};'></select>

    </div>
    <div class="col-xs-2" ng-show="d.type.specification==true">
        Especif&iacute;que sustancia:
    </div>
    <div class="col-xs-4" ng-show="d.type.specification==true">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y m&aacute;ximo 100 caracteres"
               data-val-length-max="100" data-val-length-min="3"
               data-val-required="La especificaci&oacute;n es un campo requerido"
               type="text"  value="{{d.specificationType}}" id="specificationType" name="drugs.specificationType">
        <span class="field-validation-valid" data-valmsg-for="specificationType" data-valmsg-replace="true"></span>
    </div>

</div>
<br/>

<div class="row">
    <div class="col-xs-2">
        <span ng-class="lstSourceInfoDrug['drugs.periodicity.id'+'.'+d.id]==true?'verified':'';">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('drugs.periodicity.id',d.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification" code="drugs.periodicity.id" id-element="{{d.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('drugs.periodicity.id',d.id);"></i>
            </span>
        <span ng-class="lstFinalInfoDrug['drugs.periodicity.id'+'.'+d.id]==true?'verified':'';">
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('drugs.periodicity.id',d.id)"></i>
            </span>
        Periodicidad:
    </div>
    <div class="col-xs-4">
        <input type="hidden" ng-model="d.perId" ng-update-hidden name="drugs.periodicity.id">
        <select class="form-control element-center" ng-model="d.per"
                ng-options="e.name for e in lstPer"
                ng-change="d.perId = d.per.id"  value="{{d.per.id}}"
                ng-init='lstPer = ${lstPeriodicity};'></select>
    </div>
    <div class="col-xs-2" ng-show="d.per.specification==true">
        Especif&iacute;que periodicidad:
    </div>
    <div class="col-xs-4" ng-show="d.per.specification==true">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y m&aacute;ximo 100 caracteres"
               data-val-length-max="100" data-val-length-min="3"
               data-val-required="La especificaci&oacute;n es un campo requerido"
               type="text" value="{{d.specificationPeriodicity}}"
               id="specificationPeriodicity" name="drugs.specificationPeriodicity">

        <span class="field-validation-valid" data-valmsg-for="specificationPeriodicity"
              data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div class="row">
    <div>
        <div class="col-xs-2">
            <span ng-class="lstSourceInfoDrug['drugs.quantity'+'.'+d.id]==true?'verified':'';">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('drugs.quantity',d.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification" code="drugs.quantity" id-element="{{d.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('drugs.quantity',d.id);"></i>
                </span>
            <span ng-class="lstFinalInfoDrug['drugs.quantity'+'.'+d.id]==true?'verified':'';">
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('drugs.quantity',d.id)"></i>
            </span>
            Cantidad:
        </div>
        <div class="col-xs-4">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 2 y m&aacute;ximo 25 caracteres"
                   data-val-length-max="25" data-val-length-min="2"
                   data-val-required="La cantidad es un campo requerido"
                   type="text" value="{{d.quantity}}" name="drugs.quantity" id="quantity">
            <span class="field-validation-valid" data-valmsg-for="quantity" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div>
        <div class="col-xs-2">
            <span ng-class="lstSourceInfoDrug['drugs.lastUse'+'.'+d.id]==true?'verified':'';">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('drugs.lastUse',d.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification" code="drugs.lastUse" id-element="{{d.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('drugs.lastUse',d.id);"></i>
                </span>
            <span ng-class="lstFinalInfoDrug['drugs.lastUse'+'.'+d.id]==true?'verified':'';">
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('drugs.lastUse',d.id)"></i>
            </span>
            &Uacute;ltimo consumo:
            <br/><small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/30)</small>
        </div>
        <div class="col-xs-4">
            <div class="input-group">
                <input class="form-control date-picker" id="lastUse" data-val="true" type="text"
                       data-date-format="yyyy/mm/dd" value="{{d.lastUse}}"                      readonly="readonly"
                       name="drugs.lastUse" data-val-required="La &uacute;ltima fecha de consumo es un campo requrido"/>
				<span class="input-group-addon">
                    <i class="icon-calendar bigger-110"></i>
				</span>
            </div>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2">
        <span ng-class="lstSourceInfoDrug['drugs.onsetAge'+'.'+d.id]==true?'verified':'';">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('drugs.onsetAge',d.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification" code="drugs.onsetAge" id-element="{{d.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('drugs.onsetAge',d.id);"></i>
            </span>
        <span ng-class="lstFinalInfoDrug['drugs.onsetAge'+'.'+d.id]==true?'verified':'';">
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('drugs.onsetAge',d.id)"></i>
        </span>
        Edad de inicio:
    </div>
    <div class="col-xs-4">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m&aacute;ximo 25 caracteres" value="{{d.onsetAge}}"
               data-val-length-max="25" data-val-length-min="1" data-val-required="La edad de inicio es un campo requerido"
               type="text" name="drugs.onsetAge" id="onsetAge">
        <br/>
        <span class="field-validation-valid" data-valmsg-for="onsetAge" data-valmsg-replace="true"></span>
    </div>
</div>

