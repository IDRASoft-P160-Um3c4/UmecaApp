<div>

    <input type="hidden" ng-update-hidden ng-model="id" name="idCase" id="idCase"
           ng-init="idCase = ${(idCase == null) ? 0 : idCase};">
    <input type="hidden" ng-update-hidden ng-model="p.relId" name="relationship.id" id="relId"
           ng-init="p.relId = ${relId == null ? "undefined" : relId};">
    <input type="hidden" ng-update-hidden ng-model="p.docId" name="documentType.id" id="docId"
           ng-init="p.docId = ${docId == null ? "undefined" : docId};">
    <input type="hidden" ng-update-hidden ng-model="p.livId" name="livingWith.id" id="livId"
           ng-init="p.livId = ${livId == null ? "undefined" : livId};">
    <input type="hidden" ng-update-hidden ng-model="p.depId" name="dependent.id" id="depId"
           ng-init="p.depId = ${depId == null ? "undefined" : depId};">
    <input type="hidden" ng-model="person" id="person"
           ng-init='person = ${(p == null) ? '""': p}'>
    <input type="hidden" ng-update-hidden ng-model="id" name="id" id="id"
           ng-init="id = person.id">

    <div class="row">
        <div class="col-xs-10 col-xs-offset-1">

            <input type="hidden" ng-update-hidden name="isAccompaniment" ng-model="p.isAccompaniment">

            <input type="checkbox" ng-model="p.isAccompaniment"
                   ng-init="p.isAccompaniment= p.isAccompaniment == undefined || p.isAccompaniment == '' ? false: p.isAccompaniment">

            &iquest;Esta persona acompa&ntilde;ar&aacute; al imputado durante el proceso?
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-12">
            <div class="col-xs-2 element-left">
                Nombre:
            </div>
            <div class="col-xs-10">
                <input class="form-control" type="text"
                       data-val="true" data-val-length="Debe tener al menos 3 y m�ximo 150 caracteres"
                       data-val-length-max="150" data-val-length-min="3"
                       data-val-required="El Nombre es un campo requerido"
                       name="name" id="name" ng-init="name = person.name" value="{{name}}">
            </div>
            <div class="row">
                <div class="col-xs-9 col-xs-offset-3">
                    <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
                </div>
            </div>
        </div>

    </div>
    <br/>

    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                Parentesco:
            </div>
            <div class="col-xs-8">
                <select class="form-control element-center" ng-model="p.rel"
                        ng-options="e.name for e in lstRel"
                        ng-change="p.relId = p.rel.id"
                        ng-init='lstRel = ${lstRelationship};'></select>
            </div>
            <div class="col-xs-9 col-xs-offset-3">
                <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="col-xs-5">
            <div class="col-xs-4 element-left">
                Tel&eacute;fono:
            </div>
            <div class="col-xs-7">
                <input class="form-control" type="text"
                       data-val="true" data-val-length="Debe tener al menos 5 y m&aacute;ximo 20 caracteres"
                       data-val-length-max="20" data-val-length-min="5"
                       data-val-required="El tel&eacute;fono es un campo requerido"
                       name="phone" id="phone" value="{{phone}}" ng-init="phone = person.phone">
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
                Identificaci&oacute;n prensentada:
            </div>
            <div class="col-xs-8">
                <select class="form-control element-center" ng-model="p.doc"
                        ng-options="e.name for e in lstDoc"
                        ng-change="p.docId = p.doc.id"
                        ng-init='lstDoc = ${lstDocumentType};'></select>
            </div>
        </div>
        <div class="col-xs-5">
            <div class="col-xs-4 element-left">
                Edad:
            </div>
            <div class="col-xs-7">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 1 y m&aacute;ximo 2 n&uacute;mero"
                       data-val-regex-pattern="([0-9]+)"
                       data-val-regex="La edad s&oacute;lo puede contener n&uacute;meros"
                       data-val-length-max="2" data-val-length-min="1" data-val-required="La edad es un campo requerido"
                       type="text" name="age" ng-init="age = person.age" value="{{age}}">
            </div>
            <div class="col-xs-12">
                <span class="field-validation-valid" data-valmsg-for="age" data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>
    <br/>

    <div class="row" ng-show="p.doc.specification == true">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                Especif&iacute;que:
            </div>
            <div class="col-xs-8">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 2 y m&aacute;ximo 255 caracteres"
                       data-val-length-max="255" data-val-length-min="2"
                       data-val-required="La especificaci&oacute;n es un campo requerido"
                       type="text" ng-model="specification" ng-init="specification = person.specification"
                       id="specification" name="specification">
            </div>
            <div class="col-xs-9 col-xs-offset-3">
                <span class="field-validation-valid" data-valmsg-for="specification" data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>
    <br/>

    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                Vive con el:
            </div>
            <div class="col-xs-8">
                <select class="form-control element-center" ng-model="p.liv"
                        ng-options="e.name for e in lstLiv"
                        ng-change="p.livId = p.liv.id"
                        ng-init='lstLiv = ${lstElection};'></select>
            </div>
            <div class="col-xs-9 col-xs-offset-3">
                <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="col-xs-5">
            <div class="col-xs-4 element-left">
                Dependiente econ&oacute;mico:
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

    <div class="row" ng-show="p.livId == 2">
        <div class="col-xs-12">
            <div class="col-xs-2 element-left">Direcci&oacute;n:</div>
            <div class="col-xs-10">
                <textarea id="address" class="form-control" name="address" ng-model="address"
                          ng-init="address = person.address"
                          data-val="true" data-val-required="La direcci&oacute;n es un campo requerido"
                          data-val-length="Debe tener al menos 6 y m&aacute;ximo 500 caracteres"
                          data-val-length-max="500" data-val-length-min="6"></textarea>
            </div>
        </div>
        <div class="col-xs-10 col-xs-offset-2">
            <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
        </div>
    </div>

</div>