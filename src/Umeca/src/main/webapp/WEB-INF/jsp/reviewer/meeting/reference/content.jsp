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
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m&aacute;ximo 150 caracteres"
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
            Tel&eacute;fono:
        </div>
        <div class="col-xs-7">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 8 y m&aacute;ximo 20 caracteres"
                   data-val-length-max="20" data-val-length-min="8" data-val-required="El tel&eacute;fono es un campo requerido" id="phone"
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
                    ng-options="e.name for e in lstDoc"
                    ng-change="r.docId = r.doc.id"
                    ng-init='lstDoc = ${lstDocumentType};'></select>
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
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1y m&aacute;ximo 2 caracteres"
                   data-val-length-max="2" data-val-length-min="1" data-val-required="La edad es un campo requerido"
                   data-val-regex-pattern="([0-9]+)" data-val-regex="La edad s&oacute;lo puede contener n&uacute;meros"
                   type="text" value="${r.age}" ng-model="r.age" ng-init='r.age="${(r.age == null) ? '':r.age}"' id="age" name="age">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="age" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row" ng-show="r.doc.specification == true">
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            Especif&iacute;que:
        </div>
        <div class="col-xs-8">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 2 y m&aacute;ximo 255 caracteres"
                   data-val-length-max="255" data-val-length-min="2" data-val-required="La especificaci&oacute;n es un campo requerido"
                   type="text" value="${r.specification}" ng-model="r.specification" ng-init='r.specification="${(r.specification == null) ? '':r.specification}"' id="specification" name="specification">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="specification" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">Direcci&oacute;n:</div>
        <div class="col-xs-10">
            <textarea id="address" class="form-control"  name="address"
                      data-val="true" data-val-required="La direcci&oacute;n es un campo requerido"
                      data-val-length="Debe tener al menos 6 y m&aacute;ximo 500 caracteres"
                      data-val-length-max="500" data-val-length-min="6">${r.address}</textarea>
        </div>
    </div>
    <div class="col-xs-10 col-xs-offset-2">
        <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
    </div>
</div>