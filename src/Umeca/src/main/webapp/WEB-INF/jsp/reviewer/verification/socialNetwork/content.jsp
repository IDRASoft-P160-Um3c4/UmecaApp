<div>
    <input type="hidden" ng-model="p" ng-init="p=listSocialNetwork[$index]">
    <div class="row" >
        <div class="col-xs-12">
            <div class="col-xs-2 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.name',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification" code="socialNetwork.name" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.name',p.id);"></i>
                <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('socialNetwork.name',p.id);"></i>
                Nombre:
            </div>
            <div class="col-xs-10">
                <input class="form-control" type="text"
                       data-val="true" data-val-length="Debe tener al menos 3 y m&aacute;ximo 150 caracteres"
                       data-val-length-max="150" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                       name="socialNetwork.name" id="name" ng-init="name = p.name" value="{{name}}">
                <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
            </div>
        </div>

    </div>
    <br/>
    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.relationship',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.relationship" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.relationship',p.id);"></i>
                <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('socialNetwork.relationship.id',p.id)"></i>
                 Parentesco:
            </div>
            <div class="col-xs-8">
                <input type="hidden" ng-model="p.relId" ng-update-hidden name="socialNetwork.relationship.id">
                <select class="form-control element-center" ng-model="p.rel"
                        ng-options="e.name for e in lstRel"
                        ng-change="p.relId = p.rel.id"
                        ng-init='lstRel = ${lstRelationship};'></select>
                <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="col-xs-5">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.phone',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.phone" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.phone',p.id);"></i>
                <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('socialNetwork.phone',p.id)"></i>
                Tel&eacute;fono:
            </div>
            <div class="col-xs-7">
                <input class="form-control" type="text"
                       data-val="true" data-val-length="Debe tener al menos 8 y m&aacute;ximo 20 caracteres"
                       data-val-length-max="20" data-val-length-min="8" data-val-required="El tel&eacute;fono es un campo requerido"
                       name="socialNetwork.phone" id="phone" value="{{phone}}" ng-init ="phone = p.phone" >
                <span class="field-validation-valid" data-valmsg-for="phone" data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.documentType',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.documentType" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.documentType',p.id);"></i>
                <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('socialNetwork.documentType.id',p.id)"></i>
                Identificaci&oacute;n prensentada:
            </div>
            <div class="col-xs-8">
                <input type="hidden" ng-model="p.docId" ng-update-hidden name="socialNetwork.documentType.id">
                <select class="form-control element-center" ng-model="p.doc"
                        ng-options="e.name for e in lstDoc"
                        ng-change="p.docId = p.doc.id"
                        ng-init='lstDoc = ${lstDocumentType};'></select>
                <br/>
            </div>

                    <div class="col-xs-4 element-left" ng-show="p.doc.specification == true">
                        Especif&iacute;que:
                    </div>
                    <div class="col-xs-8" ng-show="p.doc.specification == true">
                        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 2 y m&aacute;ximo 255 caracteres"
                               data-val-length-max="255" data-val-length-min="2" data-val-required="La especificaci&oacute;n es un campo requerido"
                               type="text" ng-model="specification" ng-init="specification = p.specification" id="specification" name="socialNetwork.specification">
                        <span class="field-validation-valid" data-valmsg-for="specification" data-valmsg-replace="true"></span>
                    </div>


        </div>
        <div class="col-xs-5">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.age',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.age" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.age',p.id);"></i>
                <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('socialNetwork.age',p.id)"></i>
                Edad:
            </div>
            <div class="col-xs-7">
                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m&aacute;ximo 2 n&uacute;mero"
                       data-val-regex-pattern="([0-9]+)" data-val-regex="La edad s&oacute;lo puede contener n&uacute;meros"
                       data-val-length-max="2" data-val-length-min="1" data-val-required="La edad es un campo requerido"
                       type="text" name="socialNetwork.age" ng-init="age = p.age" value="{{age}}">
                <span class="field-validation-valid" data-valmsg-for="age" data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>

    <br/>
    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.livingWith',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.livingWith" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.livingWith',p.id);"></i>
                <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('socialNetwork.livingWith.id',p.id)"></i>
                Vive con el:
            </div>
            <div class="col-xs-8">
                <input type="hidden" ng-model="p.livId" ng-update-hidden name="socialNetwork.livingWith.id">
                <select class="form-control element-center" ng-model="p.liv"
                        ng-options="e.name for e in lstLiv"
                        ng-change="p.livId = p.liv.id"
                        ng-init='lstLiv = ${lstElection};'></select>
                <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
                <br/>
            </div>

                    <div class="col-xs-4 element-left" ng-show="p.livId == 2">Direcci&oacute;n:</div>
                    <div class="col-xs-8" ng-show="p.livId == 2">
                        <textarea id="address" class="form-control"  name="socialNetwork.address" ng-model="address" ng-init="address = p.address"
                                  data-val="true" data-val-required="La direcci&oacute;n es un campo requerido"
                                  data-val-length="Debe tener al menos 6 y m&aacute;ximo 500 caracteres"
                                  data-val-length-max="500" data-val-length-min="6"></textarea>
                        <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
                    </div>
        </div>
        <div class="col-xs-5">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.dependent',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.dependent" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.dependent',p.id);"></i>
                <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('socialNetwork.dependent.id',p.id)"></i>
                Dependiente econ&oacute;mico:
            </div>
            <div class="col-xs-7">
                <input type="hidden" ng-model="p.depId" ng-update-hidden name="socialNetwork.dependent.id">
                <select class="form-control element-center" ng-model="p.dep"
                        ng-options="e.name for e in lstDep"
                        ng-change="p.depId = p.dep.id"
                        ng-init='lstDep = ${lstElection};'></select>
            </div>
        </div>
    </div>
    <br/>

</div>