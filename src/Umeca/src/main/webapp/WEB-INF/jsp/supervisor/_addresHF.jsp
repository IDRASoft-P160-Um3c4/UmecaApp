<div class="row">
    <input type="hidden" name="idLocation" value="{{a.locationId}}">
    <div class="col-xs-11">
        <div class="col-xs-4">
            <br/>
            <label for="zipCode">Código postal:</label>
            <input type="text" id="zipCode" name="zipCode" class="input-xxlarge" zip-search ng-model="zipCode"
                   data-val="true" data-val-required="El código postal es un campo requerido"
                   placeholder="Escriba su C.P. para buscar.."
                   url-request='<c:url value="/catalogs/locationsByZipCode.json"/>'
                   data-val-length-max="6" data-val-length-min="1"
                   data-val-length="Debe tener al menos 1 y máximo 6 caracteres."/>
            <br>
            <span class="field-validation-valid" data-valmsg-for="zipCode" data-valmsg-replace="true"></span>
        </div>

        <div class="col-xs-4 col-xs-offset-1" ng-show="listLocation.length > 0">
            <br/>
            <label>Elije:</label>
            <select class="form-control element-center" ng-model="a.location"
                    ng-options="e.name for e in listLocation"
                    ng-change="a.locationId = a.location.id;"></select>

        </div>
    </div>
</div>

<div class="row">
    <br/>

    <div class="col-xs-12">

        <div class="col-xs-4">

            <label for="state">Estado</label>
            <input class="form-control" type="text" readonly="readonly"
                   ng-model="a.location.municipality.state.description">
        </div>

        <div class="col-xs-4">

            <label for="municipality">Municipio</label>
            <input id="municipality" class="form-control"
                   data-val-required="El Municipio es un campo requerido"
                   type="text" value="" readonly="readonly" ng-model="a.location.municipality.name">
        </div>

        <div class="col-xs-4">

            <label for="state">Colonia</label>
            <input class="form-control" type="text" value="" ng-model="a.location.name"
                   readonly="readonly">

        </div>
    </div>

</div>

<div class="row">
    <div class="col-xs-12">


        <div class="col-xs-6">

            <label for="street">Calle</label>
            <input class="form-control" data-val="true" data-val-required="La calle es un campo requerido"
                   data-val-length-max="100" data-val-length-min="1"
                   data-val-length="Debe tener al menos 1 y máximo 100 caracteres."
                   type="text" value="" ng-model="a.street" name="street" id="street">
            <span class="field-validation-valid" data-valmsg-for="street" data-valmsg-replace="true"></span>
        </div>


        <div class="col-xs-3">
            <label for="outNum">No Ext</label>
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y m?ximo 10 caracteres"
                   data-val-length-max="10" data-val-length-min="1"
                   data-val-required="El número exterior es un campo requerido"
                   type="text" ng-model="a.outNum"
                   id="outNum"
                   name="outNum">
            <span class="field-validation-valid" data-valmsg-for="outNum" data-valmsg-replace="true"></span>
        </div>

        <div class="col-xs-3">
            <label for="innNum">No Int</label>
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y m?ximo 10 caracteres"
                   data-val-length-max="10" data-val-length-min="1"
                   data-val-required="El número interior es un campo requerido"
                   type="text" ng-model="a.innNum"
                   id="innNum"
                   name="innNum">
            <span class="field-validation-valid" data-valmsg-for="innNum" data-valmsg-replace="true"></span>
        </div>

    </div>
</div>
