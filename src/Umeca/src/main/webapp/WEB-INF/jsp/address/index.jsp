<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
<style>
    #map {
height:350px;
width:600px;
}
.infoWindowContent {
font-size:  10px !important;
border-top: 1px solid #ccc;
padding-top: 10px;
}
h2 {
margin-bottom:0;
margin-top: 0;
}
 </style>
<div ng-controller="addressComponentController">
    <div class="row element-left">
        <b>Dirección:</b>
    </div>
    <br/>
    <div class="row element-center">
        <div class="col-xs-10 col-xs-offset-1">
        <div id="map"></div>
            </div>
    </div>
    <br/>
    <div class="row">
        <!-- se deben de ejecutar los metoeos del servicio para rellenar los catalogos necesarios y para cargar el modelo-->
        <!--agregar en el controlador padre la variable nameAddress con lo que lleva el name menos las propiedades(incluyendo .)
        ejemplo: se necesita que el name de la calle sea victim.address.street; el valor de nameAddress = "victim.address."-->
        <div class="col-xs-5">
            <input type="hidden" ng-init='model = ${(address == null) ? '""' : address}'>
            <input type="hidden" ng-model="id" ng-init="id" name="{{nameAddress}}id" ng-update-hidden>
            <input type="hidden" ng-init="urlZipCode = '<c:url value="/catalogs/address/locationsByZipCode.json"/>'" ng-model="urlZipCode">
            <label for="zipCode">Código postal:</label>
            <input type="text" id="zipCode" name="zipCode" class="input-xxlarge" zip-search ng-model="zipCode"
                   data-val="true" data-val-required="El código postal es un campo requerido"
                   placeholder="Escriba su C.P. para buscar.."
                   url-request='<c:url value="/catalogs/address/locationsByZipCode.json"/>'
                   data-val-length-max="6" data-val-length-min="1"
                   data-val-length="Debe tener al menos 1 y máximo 6 caracteres."/>
            <br>
            <span class="field-validation-valid" data-valmsg-for="zipCode" data-valmsg-replace="true"></span>
        </div>
        <div class="col-xs-7">
            <br/>
            <div ng-show="msgZipCode" class="alert-danger element-center">
                {{msgZipCode}}
            </div>
        </div>
    </div>

    <br/>

    <div class="row">
        <div class="col-xs-6">
            <label>Estado:</label>
            <select class="form-control element-center" ng-model="state" find-municipality
                    url-request='<c:url value="/catalogs/address/municipalityByStId.json"/>'
                    ng-options="e.name for e in listState" ng-init='listState = ${listState}'
                    ng-change="stateId = state.id;"></select><br/>

        </div>
        <div class="col-xs-6">
            <label>Municipio:</label>
            <input type="hidden" ng-model="urlMunicipality"
                   ng-init='urlMunicipality = "<c:url value="/catalogs/address/municipalityByStId.json"/>"'>
            <select class="form-control element-center" ng-model="municipality" find-location
                    url-request='<c:url value="/catalogs/address/locationByMunId.json"/>'
                    ng-options="e.name for e in listMunicipality" ng-init='listMunicipaliti = ""'
                    ng-change="municipalityId = municipality.id;"></select><br/>

        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <label>Localidad:</label>
            <br/>
            <input type="hidden" ng-model="locationId" ng-update-hidden name="{{nameAddress}}location.id">
            <input type="hidden" ng-model="urlLocation"
                   ng-init='urlLocation = "<c:url value="/catalogs/address/locationByMunId.json"/>"'>
            <select class="form-control" ng-model="location" change-cp
                    ng-options="e.name for e in listLocation" ng-init='listLocation = ""'
                    ng-change="locationId = location.id; zipCode = location.zipCode"></select>

        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-6">
            Calle <br/>
            <input class="form-control" data-val="true" data-val-required="La calle es un campo requerido"
                   data-val-length-max="100" data-val-length-min="1"
                   data-val-length="Debe tener al menos 1 y máximo 100 caracteres."
                   type="text" value="" ng-model="street" name="{{nameAddress}}street" id="{{nameAddress}}street">
                <span class="field-validation-valid" data-valmsg-for="{{nameAddress}}street"
                      data-valmsg-replace="true"></span>
        </div>
        <div class="col-xs-3">
            No Ext <br/>
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y máximo 10 caracteres"
                   data-val-length-max="10" data-val-length-min="1"
                   data-val-required="El número exterior es un campo requerido"
                   type="text" ng-model="outNum"
                   id="{{nameAddress}}outNum"
                   name="{{nameAddress}}outNum">
                <span class="field-validation-valid" data-valmsg-for="{{nameAddress}}outNum"
                      data-valmsg-replace="true"></span>
        </div>
        <div class="col-xs-2">
            No Int <br/>
            <input class="with-100" data-val="true"
                   data-val-length="Debe tener al menos 1 y máximo 10 caracteres"
                   data-val-length-max="10" data-val-length-min="1"
                   data-val-required="El número interior es un campo requerido"
                   type="text" ng-model="innNum"
                   id="{{nameAddress}}innNum"
                   name="{{nameAddress}}innNum">
                <span class="field-validation-valid" data-valmsg-for="{{nameAddress}}innNum"
                      data-valmsg-replace="true"></span>
        </div>
    </div>
</div>