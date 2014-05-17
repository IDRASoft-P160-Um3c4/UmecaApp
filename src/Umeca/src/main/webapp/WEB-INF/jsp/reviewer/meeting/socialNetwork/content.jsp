<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            Nombre:
        </div>
        <div class="col-xs-10">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                   data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                   type="text" value="" ng-model="m.name">
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
            <select class="form-control element-center" ng-model="m.role" ng-options="e.name for e in lstRoles" ng-init='lstRoles = ${lstRoles};'></select>
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
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                   data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                   type="text" value="" ng-model="m.name">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
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
            <select class="form-control element-center" ng-model="m.role" ng-options="e.name for e in lstRoles" ng-init='lstRoles = ${lstRoles};'></select>
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
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                   data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                   type="text" value="" ng-model="m.name">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
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
            <select class="form-control element-center" ng-model="m.role" ng-options="e.name for e in lstRoles" ng-init='lstRoles = ${lstRoles};'></select>
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
            <select class="form-control element-center" ng-model="m.role" ng-options="e.name for e in lstRoles" ng-init='lstRoles = ${lstRoles};'></select>
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>