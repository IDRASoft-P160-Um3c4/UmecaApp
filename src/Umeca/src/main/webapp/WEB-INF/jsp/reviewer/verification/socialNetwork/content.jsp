<div>
    <input type="hidden" ng-model="p" ng-init="p=listSocialNetwork[$index]">
    <div class="row" >
        <div class="col-xs-12">
            <div class="col-xs-2 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.peopleSocialNetwork.name',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification" code="socialNetwork.peopleSocialNetwork.name" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.peopleSocialNetwork.name',p.id);"></i>
                Nombre:
            </div>
            <div class="col-xs-10">
                <input class="form-control" type="text"
                       data-val="true" data-val-length="Debe tener al menos 3 y máximo 150 caracteres"
                       data-val-length-max="150" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                       name="name" id="name" ng-init="name = p.name" value="{{name}}">
                <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
            </div>
        </div>

    </div>
    <br/>
    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.peopleSocialNetwork.relationship',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.peopleSocialNetwork.relationship" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.peopleSocialNetwork.relationship',p.id);"></i>
                 Parentesco:
            </div>
            <div class="col-xs-8">
                <select class="form-control element-center" ng-model="p.rel"
                        ng-options="e.name for e in lstRelationship"
                        ng-change="p.relId = p.rel.id"
                        ng-init='lstRelationship = ${lstRelationship};'></select>
            </div>
            <div class="col-xs-9 col-xs-offset-3">
                <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="col-xs-5">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.peopleSocialNetwork.phone',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.peopleSocialNetwork.phone" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.peopleSocialNetwork.phone',p.id);"></i>
                Teléfono:
            </div>
            <div class="col-xs-7">
                <input class="form-control" type="text"
                       data-val="true" data-val-length="Debe tener al menos 8 y máximo 20 caracteres"
                       data-val-length-max="20" data-val-length-min="8" data-val-required="El teléfono es un campo requerido"
                       name="phone" id="phone" value="{{phone}}" ng-init ="phone = p.phone" >
            </div>
            <div class="col-xs-12">
                <span class="field-validation-valid" data-valmsg-for="phone" data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.peopleSocialNetwork.documentType',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.peopleSocialNetwork.documentType" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.peopleSocialNetwork.documentType',p.id);"></i>
                Identificación prensentada:
            </div>
            <div class="col-xs-8">
                <select class="form-control element-center" ng-model="p.doc"
                        ng-options="e.name for e in lstDocumentType"
                        ng-change="p.docId = p.doc.id"
                        ng-init='lstDocumentType = ${lstDocumentType};'></select>
                <br/>
            </div>

                    <div class="col-xs-4 element-left" ng-show="p.doc.specification == true">
                        Especifíque:
                    </div>
                    <div class="col-xs-8" ng-show="p.doc.specification == true">
                        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 2 y máximo 255 caracteres"
                               data-val-length-max="255" data-val-length-min="2" data-val-required="La especificación es un campo requerido"
                               type="text" ng-model="specification" ng-init="specification = p.specification" id="specification" name="specification">
                        <span class="field-validation-valid" data-valmsg-for="specification" data-valmsg-replace="true"></span>
                    </div>


        </div>
        <div class="col-xs-5">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.peopleSocialNetwork.age',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.peopleSocialNetwork.age" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.peopleSocialNetwork.age',p.id);"></i>
                Edad:
            </div>
            <div class="col-xs-7">
                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y máximo 2 número"
                       data-val-regex-pattern="([0-9]+)" data-val-regex="La edad sólo puede contener números"
                       data-val-length-max="2" data-val-length-min="1" data-val-required="La edad es un campo requerido"
                       type="text" name="age" ng-init="age = p.age" value="{{age}}">
            </div>
            <div class="col-xs-12">
                <span class="field-validation-valid" data-valmsg-for="age" data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>

    <br/>
    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.peopleSocialNetwork.livingWith',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.peopleSocialNetwork.livingWith" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.peopleSocialNetwork.livingWith',p.id);"></i>
                Vive con el:
            </div>
            <div class="col-xs-8">
                <select class="form-control element-center" ng-model="p.liv"
                        ng-options="e.name for e in lstLiving"
                        ng-change="p.livId = p.liv.id"
                        ng-init='lstLiving = ${lstElection};'></select>
                <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
                <br/>
            </div>

                    <div class="col-xs-4 element-left" ng-show="p.livId == 2">Dirección:</div>
                    <div class="col-xs-8" ng-show="p.livId == 2">
                        <textarea id="address" class="form-control"  name="address" ng-model="address" ng-init="address = p.address"
                                  data-val="true" data-val-required="La dirección es un campo requerido"
                                  data-val-length="Debe tener al menos 6 y máximo 500 caracteres"
                                  data-val-length-max="500" data-val-length-min="6">{{address}}</textarea>
                        <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
                    </div>
        </div>
        <div class="col-xs-5">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialNetwork.peopleSocialNetwork.dependent',p.id);"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialNetwork.peopleSocialNetwork.dependent" id-element="{{p.id}}"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialNetwork.peopleSocialNetwork.dependent',p.id);"></i>
                Dependiente económico:
            </div>
            <div class="col-xs-7">
                <select class="form-control element-center" ng-model="p.dep"
                        ng-options="e.name for e in lstDep"
                        ng-change="p.depId = p.dep.id"
                        ng-init='lstDep = ${lstElection};'></select>
            </div>
            <div class="col-xs-9 col-xs-offset-3">
                <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>
    <br/>

</div>