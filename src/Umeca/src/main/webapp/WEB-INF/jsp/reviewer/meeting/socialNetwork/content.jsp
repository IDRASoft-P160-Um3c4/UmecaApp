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
<div class="row" >
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            Nombre:
        </div>
        <div class="col-xs-10">
            <input class="form-control" type="text"
                   data-val="true" data-val-length="Debe tener al menos 3 y máximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
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
            Teléfono:
        </div>
        <div class="col-xs-7">
            <input class="form-control" type="text"
                   data-val="true" data-val-length="Debe tener al menos 5 y máximo 20 caracteres"
                   data-val-length-max="20" data-val-length-min="5" data-val-required="El teléfono es un campo requerido"
                   name="phone" id="phone" value="{{phone}}" ng-init ="phone = person.phone" >
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
            Identificación prensentada:
        </div>
        <div class="col-xs-8">
            <select class="form-control element-center" ng-model="p.doc"
                    ng-options="e.name for e in lstDocumentType"
                    ng-change="p.docId = p.doc.id"
                    ng-init='lstDocumentType = ${lstDocumentType};'></select>
        </div>
    </div>
    <div class="col-xs-5">
        <div class="col-xs-4 element-left">
            Edad:
        </div>
        <div class="col-xs-7">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y máximo 2 número"
                   data-val-length-max="2" data-val-length-min="1" data-val-required="La edad es un campo requerido"
                   type="text" name="age" ng-init="age = person.age" value="{{age}}">
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
            Vive con el:
        </div>
        <div class="col-xs-8">
            <select class="form-control element-center" ng-model="p.liv"
                    ng-options="e.name for e in lstLiving"
                    ng-change="p.livId = p.liv.id"
                    ng-init='lstLiving = ${lstElection};'></select>
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div class="col-xs-5">
        <div class="col-xs-4 element-left">
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

    </div>