<div class="row">

    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            <input type="hidden" ng-update-hidden name="id" id="id" ng-model="v.id"
                   ng-init='idCase = ${(idCase == null) ? 0 : idCase}; v = ${victim == null ? '{}': victim}'>
            <input type="hidden" ng-update-hidden name="idCase" id="id" ng-model="idCase"
                   ng-init='idCase = ${(idCase == null) ? 0 : idCase}; v = ${victim == null ? '{}': victim}'>
            Nombre:
        </div>
        <div class="col-xs-10">
            <input class="width-100" data-val="true" data-val-length="Debe tener al menos 3 y m&aacute;ximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="3" data-val-required="El nombre es un campo requerido" id="fullName"
                   type="text" ng-model="v.fullname" name="fullname">
        </div>
        <div class="row">
            <div class="col-xs-9 col-xs-offset-3">
                <span class="field-validation-valid" data-valmsg-for="fullname" data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>

</div>
<br/>
<div class="row">
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            Relaci&oacute;n:
        </div>
        <div class="col-xs-8">
            <select class="form-control element-center" ng-model="v.rel"
                    ng-options="e.name for e in lstRel"
                    ng-change="v.relId = v.rel.id"
                    ng-init='lstRel = ${lstRelationship};'></select>
            <input type="hidden" ng-update-hidden name="relationship.id" ng-model="v.rel.id">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            Tel&eacute;fono(s):
        </div>
        <div class="col-xs-8">
            <textarea class="form-control" data-val="true" data-val-length="Debe tener al menos 5 y m&aacute;ximo 200 caracteres"
                      data-val-length-max="200" data-val-length-min="5" data-val-required="El tel&eacute;fono es un campo requerido" id="phone"
                      type="text" ng-model="v.phone" name="phone"></textarea>
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="phone" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row" ng-show="v.rel.specification == true">
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            Especif&iacute;que<br/>relaci&oacute;n:
        </div>
        <div class="col-xs-8">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m&aacute;ximo 255 caracteres"
                   data-val-length-max="255" data-val-length-min="1" data-val-required="La especificaci&oacute;n es un campo requerido"
                   type="text" ng-model="v.specification" name="specification">
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
            Edad:
        </div>
        <div class="col-xs-8">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m&aacute;ximo 3 caracteres"
                   data-val-length-max="3" data-val-length-min="1" data-val-required="La edad es un campo requerido"
                   data-val-regex-pattern="([0-9]+)" data-val-regex="La edad s&oacute;lo puede contener n&uacute;meros"
                   type="text"  ng-model="v.age" name="age">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="age" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-12">
            <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
            <br/>
        </div>
    </div>
</div>
