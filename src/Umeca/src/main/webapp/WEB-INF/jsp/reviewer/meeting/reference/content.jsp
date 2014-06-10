<div class="row">
    <input type="hidden" ng-update-hidden ng-model="id" name="idCase" id="idCase"
           ng-init="idCase = ${(idCase == null) ? 0 : idCase};">
    <input type="hidden" ng-update-hidden ng-model="r.relId" name="relationship.id" id="relId"
           ng-init="r.relId = ${relId == null ? "undefined" : relId};">
    <input type="hidden" ng-update-hidden ng-model="r.docId" name="documentType.id" id="docId"
           ng-init="r.docId = ${docId == null ? "undefined" : docId};">
    <input type="hidden" ng-update-hidden ng-model="id" name="id" id="id"
           ng-init='id = "${(r.id== null) ? 0 : r.id}"'>
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            Nombre:
        </div>
        <div class="col-xs-10">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y máximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="6" data-val-required="El nombre es un campo requerido" id="fullName"
                   type="text" value="${r.fullName}" ng-model="r.fullName" ng-init='r.fullName="${(r.fullName)==null ? '' : r.fullName}";' name="fullName">
        </div>
        <div class="row">
            <div class="col-xs-9 col-xs-offset-3">
                <span class="field-validation-valid" data-valmsg-for="fullName" data-valmsg-replace="true"></span>
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
            <select class="form-control element-center" ng-model="r.rel"
                    ng-options="e.name for e in lstRelationship"
                    ng-change="r.relId = r.rel.id"
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
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y máximo 20 caracteres"
                   data-val-length-max="20" data-val-length-min="6" data-val-required="El teléfono es un campo requerido" id="phone"
                   type="text" value="${r.phone}" ng-model="r.phone" ng-init='r.phone="${(r.phone == null) ? '' : r.phone}"' name="phone">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
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
            <select class="form-control element-center" ng-model="r.doc"
                    ng-options="e.name for e in lstDocumentType"
                    ng-change="r.docId = r.doc.id"
                    ng-init='lstDocumentType = ${lstDocumentType};'></select>
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div class="col-xs-5">
        <div class="col-xs-4 element-left">
            Edad:
        </div>
        <div class="col-xs-7">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1y máximo 2 caracteres"
                   data-val-length-max="2" data-val-length-min="1" data-val-required="La edad es un campo requerido"
                   data-val-regex-pattern="([0-9]+)" data-val-regex="La edad sólo puede contener números"
                   type="text" value="${r.age}" ng-model="r.age" ng-init='r.age="${(r.age == null) ? '':r.age}"' id="age" name="age">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="age" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">Dirección:</div>
        <div class="col-xs-10">
            <textarea id="address" class="form-control"  name="address"
                      data-val="true" data-val-required="La dirección es un campo requerido"
                      data-val-length="Debe tener al menos 6 y máximo 500 caracteres"
                      data-val-length-max="500" data-val-length-min="6">${r.address}</textarea>
        </div>
    </div>
    <div class="col-xs-10 col-xs-offset-2">
        <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
    </div>
</div>