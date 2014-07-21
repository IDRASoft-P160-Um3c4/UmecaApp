<input type="hidden" ng-init="r = listReference[$index]">
<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('references.fullName',r.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="references.fullName" id-element="{{r.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('references.fullName',r.id);"></i>
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('references.fullName',r.id)"></i>
            Nombre:
        </div>
        <div class="col-xs-10">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m�ximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="6" data-val-required="El nombre es un campo requerido" id="fullName"
                   type="text"  name="references.fullName" value="{{r.fullName}}">
                <span class="field-validation-valid" data-valmsg-for="fullName" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('references.relationship',r.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="references.relationship" id-element="{{r.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('references.relationship',r.id);"></i>
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('references.relationship.id',r.id)"></i>
            Parentesco:
        </div>
        <div class="col-xs-8">
            <input type="hidden" ng-model="r.relId" ng-update-hidden name="references.relationship.id">
            <select class="form-control element-center" ng-model="r.rel"
                    ng-options="e.name for e in lstRel"
                    ng-change="r.relId = r.rel.id"
                    ng-init='lstRel = ${lstRelationship};'></select>
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div class="col-xs-5">
        <div class="col-xs-4 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('references.phone',r.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="references.phone" id-element="{{r.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('references.phone',r.id);"></i>
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('references.phone',r.id)"></i>
            Tel�fono:
        </div>
        <div class="col-xs-7">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 8 y m�ximo 20 caracteres"
                   data-val-length-max="20" data-val-length-min="8" data-val-required="El tel�fono es un campo requerido" id="phone"
                   type="text" name="references.phone" value="{{r.phone}}">
            <span class="field-validation-valid" data-valmsg-for="phone" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('references.documentType.id',r.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="references.documentType.id" id-element="{{r.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('references.documentType.id',r.id);"></i>
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('references.documentType.id',r.id)"></i>
            Identificaci�n prensentada:
        </div>
        <div class="col-xs-8">
            <input type="hidden" ng-model="r.docId" ng-update-hidden name="references.documentType.id">
            <select class="form-control element-center" ng-model="r.doc"
                    ng-options="e.name for e in lstDoc"
                    ng-change="r.docId = r.doc.id"
                    ng-init='lstDoc = ${lstDocumentType};'></select>
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
            <br/>
        </div>
                <div class="col-xs-4 element-left" ng-show="r.doc.specification == true">
                    Especif�que:
                </div>
                <div class="col-xs-8" ng-show="r.doc.specification == true">
                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 2 y m�ximo 255 caracteres"
                           data-val-length-max="255" data-val-length-min="2" data-val-required="La especificaci�n es un campo requerido"
                           type="text"  id="specification" name="references.specification" value="{{r.specification}}">
                    <span class="field-validation-valid" data-valmsg-for="specification" data-valmsg-replace="true"></span>
                </div>


    </div>
    <div class="col-xs-5">
        <div class="col-xs-4 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('references.age',r.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="references.age" id-element="{{r.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('references.age',r.id);"></i>
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('references.age',r.id)"></i>
            Edad:
        </div>
        <div class="col-xs-7">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1y m�ximo 2 caracteres"
                   data-val-length-max="2" data-val-length-min="1" data-val-required="La edad es un campo requerido"
                   data-val-regex-pattern="([0-9]+)" data-val-regex="La edad s�lo puede contener n�meros"
                   type="text" id="age" name="references.age" value="{{r.age}}">
            <span class="field-validation-valid" data-valmsg-for="age" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>

<br/>
<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('references.address',r.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="references.address" id-element="{{r.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('references.address',r.id);"></i>
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('references.address',r.id)"></i>
            Direcci�n:</div>
        <div class="col-xs-10">
            <textarea id="address" class="form-control"  name="references.address"
                      data-val="true" data-val-required="La direcci�n es un campo requerido"
                      data-val-length="Debe tener al menos 6 y m�ximo 500 caracteres"
                      data-val-length-max="500" data-val-length-min="6">{{r.address}}</textarea>
            <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>