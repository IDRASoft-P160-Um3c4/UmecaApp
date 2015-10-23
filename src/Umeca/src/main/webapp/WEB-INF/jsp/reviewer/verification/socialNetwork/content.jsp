<div ng-controller="socialNetworkVerificationController">

    <div ng-init="lsn=listSocialNetwork[$index]">

        <div class="row">
            <div class="col-xs-12">
                <div class="col-xs-2 element-left">

                <span ng-class="lstSourceInfoSocial['socialNetwork.name'+'.'+lsn.id]==true?'verified':'';">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('socialNetwork.name',lsn.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4"
                   ng-show="verification" code="socialNetwork.name" id-element="{{lsn.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('socialNetwork.name',lsn.id);"></i>
                    </span>
                <span ng-class="lstFinalInfoSocial['socialNetwork.name'+'.'+lsn.id]==true?'verified':'';">
                <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                   ng-click="showChoices('socialNetwork.name',lsn.id);"></i>
                    </span>
                    Nombre:
                </div>
                <div class="col-xs-10">
                    <input class="width-100" type="text"
                           data-val="true" data-val-length="Debe tener al menos 3 y m&aacute;ximo 150 caracteres"
                           data-val-length-max="150" data-val-length-min="3"
                           data-val-required="El Nombre es un campo requerido"
                           name="socialNetwork.name" id="name" ng-init="name = lsn.name" value="{{name}}">
                    <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
                </div>
            </div>

        </div>
        <br/>

        <div class="row">
            <div class="col-xs-6">
                <div class="col-xs-4 element-left">
                <span ng-class="lstSourceInfoSocial['socialNetwork.relationship.id'+'.'+lsn.id]==true?'verified':'';">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('socialNetwork.relationship.id',lsn.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4"
                   ng-show="verification" code="socialNetwork.relationship.id" id-element="{{lsn.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('socialNetwork.relationship.id',lsn.id);"></i>
                    </span>
                <span ng-class="lstFinalInfoSocial['socialNetwork.relationship.id'+'.'+lsn.id]==true?'verified':'';">
                <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                   ng-click="showChoices('socialNetwork.relationship.id',lsn.id)"></i>
                    </span>
                    Relaci&oacute;n:
                </div>

                <div class="col-xs-8">
                    <input type="hidden" ng-model="lsn.relId" ng-update-hidden name="socialNetwork.relationship.id"
                           id="relationship">
                    <select class="form-control element-center" ng-model="lsn.rel"
                            ng-options="e.name for e in lstRel"
                            ng-change="lsn.relId = lsn.rel.id"
                            ng-init='lstRel = ${lstRelationship}'></select>
                    <span class="field-validation-valid" data-valmsg-for="relationship"
                          data-valmsg-replace="true"></span>
                </div>
            </div>
            <div class="col-xs-6" ng-show="lsn.rel.specification == true">
                <div class="col-xs-4 element-left">
                    Especif&iacute;que<br/>relaci&oacute;n:
                </div>
                <div class="col-xs-8">
                    <input class="form-control" data-val="true" value="{{lsn.specificationRelationship}}"
                           data-val-length="Debe tener al menos 2 y m&aacute;ximo 255 caracteres"
                           data-val-length-max="255" data-val-length-min="2"
                           data-val-required="La especificaci&oacute;n es un campo requerido"
                           type="text" ng-model="lsn.specificationRelationship"
                           id="specificationRelationship" name="socialNetwork.specificationRelationship">
                </div>
                <div class="col-xs-9 col-xs-offset-3">
                    <span class="field-validation-valid" data-valmsg-for="specificationRelationship"
                          data-valmsg-replace="true"></span>
                </div>
            </div>
        </div>
        <br/>

        <div class="row">
            <div class="col-xs-12">
                <div class="col-xs-2 element-left">
                <span ng-class="lstSourceInfoSocial['socialNetwork.phone'+'.'+lsn.id]==true?'verified':'';">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('socialNetwork.phone',lsn.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification" code="socialNetwork.phone" id-element="{{lsn.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('socialNetwork.phone',lsn.id);"></i>
                    </span>
                <span ng-class="lstFinalInfoSocial['socialNetwork.phone'+'.'+lsn.id]==true?'verified':'';">
                <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                   ng-click="showChoices('socialNetwork.phone',lsn.id)"></i>
                    </span>
                    Tel&eacute;fono:
                </div>
                <div class="col-xs-10">
                <textarea class="form-control width-100" type="text" value="{{lsn.phone}}"
                          data-val="true" data-val-length="Debe tener al menos 5 y m&aacute;ximo 200 caracteres"
                          data-val-length-max="200" data-val-length-min="5"
                          data-val-required="El tel&eacute;fono es un campo requerido"
                          name="socialNetwork.phone" id="phone" ng-model="phone" ng-init="phone = lsn.phone"></textarea>
                    <span class="field-validation-valid" data-valmsg-for="phone" data-valmsg-replace="true"></span>
                </div>
            </div>

        </div>
        <br/>

        <div class="row">
            <div class="col-xs-6">
                <div class="col-xs-4 element-left">
                <span ng-class="lstSourceInfoSocial['socialNetwork.documentType.id'+'.'+lsn.id]==true?'verified':'';">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('socialNetwork.documentType.id',lsn.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification" code="socialNetwork.documentType.id" id-element="{{lsn.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('socialNetwork.documentType.id',lsn.id);"></i>
                    </span>
                <span ng-class="lstFinalInfoSocial['socialNetwork.documentType.id'+'.'+lsn.id]==true?'verified':'';">
                <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                   ng-click="showChoices('socialNetwork.documentType.id',lsn.id)"></i>
                    </span>
                    Identificaci&oacute;n prensentada:
                </div>
                <div class="col-xs-8">
                    <input type="hidden" ng-model="lsn.docId" ng-update-hidden name="socialNetwork.documentType.id">
                    <select class="form-control element-center" ng-model="lsn.doc"
                            ng-options="e.name for e in lstDoc"
                            ng-change="lsn.docId = lsn.doc.id" value="{{lsn.docId}}"
                            ng-init='lstDoc = ${lstDocumentType}'></select>
                    <br/>
                </div>

                <div class="col-xs-4 element-left" ng-show="lsn.doc.specification == true">
                    Especif&iacute;que:
                </div>
                <div class="col-xs-8" ng-show="lsn.doc.specification == true">
                    <input class="form-control" data-val="true"
                           data-val-length="Debe tener al menos 2 y m&aacute;ximo 255 caracteres"
                           value="{{lsn.specification}}"
                           data-val-length-max="255" data-val-length-min="2"
                           data-val-required="La especificaci&oacute;n es un campo requerido"
                           type="text" ng-model="specification" ng-init="specification = lsn.specification"
                           id="specification" name="socialNetwork.specification">
                    <span class="field-validation-valid" data-valmsg-for="specification"
                          data-valmsg-replace="true"></span>
                </div>


            </div>
            <div class="col-xs-6">
                <div class="col-xs-4 element-left">
                <span ng-class="lstSourceInfoSocial['socialNetwork.age'+'.'+lsn.id]==true?'verified':'';">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('socialNetwork.age',lsn.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification" code="socialNetwork.age" id-element="{{lsn.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('socialNetwork.age',lsn.id);"></i>
                    </span>
                <span ng-class="lstFinalInfoSocial['socialNetwork.age'+'.'+lsn.id]==true?'verified':'';">
                <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                   ng-click="showChoices('socialNetwork.age',lsn.id)"></i>
                    </span>
                    Edad:
                </div>
                <div class="col-xs-8">
                    <input class="width-100" data-val="true"
                           data-val-length="Debe tener al menos 1 y m&aacute;ximo 2 n&uacute;mero"
                           data-val-regex-pattern="([0-9]+)"
                           data-val-regex="La edad s&oacute;lo puede contener n&uacute;meros"
                           data-val-length-max="2" data-val-length-min="1"
                           data-val-required="La edad es un campo requerido"
                           type="text" name="socialNetwork.age" ng-init="age = lsn.age" value="{{age}}">
                    <span class="field-validation-valid" data-valmsg-for="age" data-valmsg-replace="true"></span>
                </div>
            </div>
        </div>

        <br/>

        <div class="row">
            <div class="col-xs-6">
                <div class="col-xs-4 element-left">
                <span ng-class="lstSourceInfoSocial['socialNetwork.livingWith.id'+'.'+lsn.id]==true?'verified':'';">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('socialNetwork.livingWith.id',lsn.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification" code="socialNetwork.livingWith.id" id-element="{{lsn.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('socialNetwork.livingWith.id',lsn.id);"></i>
                    </span>
                <span ng-class="lstFinalInfoSocial['socialNetwork.livingWith.id'+'.'+lsn.id]==true?'verified':'';">
                <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                   ng-click="showChoices('socialNetwork.livingWith.id',lsn.id)"></i>
                </span>
                    Vive con el:
                </div>
                <div class="col-xs-8">
                    <input type="hidden" ng-model="lsn.livId" ng-update-hidden name="socialNetwork.livingWith.id">
                    <select class="form-control element-center" ng-model="lsn.liv"
                            ng-options="e.name for e in lstLiv"
                            ng-change="lsn.livId = lsn.liv.id" value="{{lsn.livId}}"
                            ng-init='lstLiv = ${lstElection}'></select>
                    <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
                    <br/>
                </div>

                <div class="col-xs-4 element-left" ng-show="lsn.livId == 2">Direcci&oacute;n:</div>
                <div class="col-xs-8" ng-show="lsn.livId == 2">
                        <textarea id="address" class="form-control" name="socialNetwork.address" ng-model="address"
                                  ng-init="address = lsn.address"
                                  data-val="true" data-val-required="La direcci&oacute;n es un campo requerido"
                                  data-val-length="Debe tener al menos 6 y m&aacute;ximo 500 caracteres"
                                  value="{{lsn.address}}"
                                  data-val-length-max="500" data-val-length-min="6"></textarea>
                    <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="col-xs-4 element-left">
                <span ng-class="lstSourceInfoSocial['socialNetwork.dependent.id'+'.'+lsn.id]==true?'verified':'';">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('socialNetwork.dependent.id',lsn.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification" code="socialNetwork.dependent.id" id-element="{{lsn.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('socialNetwork.dependent.id',lsn.id);"></i>
                    </span>
                <span ng-class="lstFinalInfoSocial['socialNetwork.dependent.id'+'.'+lsn.id]==true?'verified':'';">
                <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                   ng-click="showChoices('socialNetwork.dependent.id',lsn.id)"></i>
                    </span>
                    Dependiente econ&oacute;mico:
                </div>
                <div class="col-xs-8">
                    <input type="hidden" ng-model="lsn.depId" ng-update-hidden name="socialNetwork.dependent.id">
                    <select class="form-control width-100 element-center" ng-model="lsn.dep"
                            ng-options="e.name for e in lstDep"
                            ng-change="lsn.depId = lsn.delsn.id" value="{{lsn.depId}}"
                            ng-init='lstDep = ${lstElection}; init();'></select>
                </div>
            </div>
        </div>
        <br/>


    </div>
</div>
