<div class="row">
    <input type="hidden" ng-update-hidden ng-model="id" name="idCase" id="idCase"
           ng-init="idCase = ${(idCase == null) ? 0 : idCase};">
    <input type="hidden" ng-update-hidden ng-model="a.typeId" name="registerType.id" id="typeId"
           ng-init="a.typeId = ${typeId == null ? "undefined" : typeId};">
    <input type="hidden" ng-update-hidden ng-model="a.belongId" name="belong.id" id="perId"
           ng-init="a.belongId = ${belongId == null ? "undefined" : belongId};">
    <input type="hidden" ng-update-hidden ng-model="a.locationId" name="location.id" id="locationId"
           ng-init="a.location = ${location.id == null ? "undefined" : location};">
    <input type="hidden" ng-update-hidden ng-model="id" name="id" id="id"
           ng-init='id = "${(d.id == null)? '':d.id}"'>
    <input type="hidden" ng-update-hidden ng-model="a.timeLive" name="timeLive" id="hdnTimeLive">
    <div class="col-xs-2 element-right">
        <i class="icon-remove-sign red link-image" style="display: none;"  onclick="verification(this)"></i>
        <a class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-list-alt orange" style="display: none;"></i> </a>
            <ul class="dropdown-menu">
                <li>
                    <label for="r1"><input type="radio" name="radiocp" id="r1" onclick="checkSource()"> &nbsp;Jos� Ramierez</label><br/>
                        <label>&nbsp;<i class="icon-remove red"> &nbsp;</i>asldjkfas fasdlfkj sdlkfj aslkdf</label>
                    <div class="hr hr-2"></div>
                </li>
                <li>
                    <label for="r2"><input type="radio" name="radiocp" id="r2" onclick="checkSource()"> &nbsp;Carlos G�mez</label><br/>
                    <label>&nbsp;<i class="icon-ok green"> &nbsp;</i>55569</label>
                    <div class="hr hr-2"></div>
                </li>
            </ul>
        </a>
        <label for="cp">C�digo  postal:</label>
    </div>
    <div class="col-xs-3">
        <input type="text" id="zipCode" class="form" zip-search ng-model="zipCode" ng-init='zipCode = "${(zipCode == null) ? "" : zipCode}"'
               data-val="true"  data-val-required="El c�digo postal es un campo requerido" name="zipCode" url-request='<c:url value="/catalogs/locationsByZipCode.json"/>'
               data-val-length-max="6" data-val-length-min="1" data-val-length ="Debe tener al menos 1 y m�ximo 6 caracteres."/>
        <span class="field-validation-valid" data-valmsg-for="zipCode" data-valmsg-replace="true"></span>
    </div>
    <div class="col-xs-2 col-xs-offset-1 element-right" ng-show="listLocation.length > 0">
        Elije:
    </div>
    <div class="col-xs-3" ng-show="listLocation.length > 0">
        <select class="form-control element-center" ng-model="a.location"
                ng-options="e.name for e in listLocation"
                ng-change="a.locationId = a.location.id;"></select>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2 element-right">
        Estado:
    </div>
    <div class="col-xs-3">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="3" data-val-required="El estado es un campo requerido"
               type="text" value="" readonly="readonly" ng-model="a.location.municipality.state.description">
    </div>
    <div class="col-xs-2 element-right">
        Municipio:
    </div>
    <div class="col-xs-4">
        <input class="form-control" data-val  ="true" data-val-required="El Municipio es un campo requerido"
        type="text" value="" readonly="readonly" ng-model = "a.location.municipality.name">
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2 element-right">
        Colonia:
    </div>
    <div class="col-xs-3">
        <input class="form-control" data-val="true"  data-val-required="La colonia es un campo requerido"
               type="text" value="" ng-model="a.location.name" readonly="readonly">
    </div>
    <div class="col-xs-2 element-right">
        Calle:
    </div>
    <div class="col-xs-4">
        <input class="form-control" data-val="true"  data-val-required="La calle es un campo requerido"
               data-val-length-max="100" data-val-length-min="1" data-val-length ="Debe tener al menos 1 y m�ximo 100 caracteres."
               type="text" value="" ng-model="d.street" name="street" id="street" ng-init='d.street = "${(d.street == null) ? "" : d.street}"'>
        <span class="field-validation-valid" data-valmsg-for="street" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>
<div class="row">

    <div class="col-xs-2 element-right">
        No Ext:
    </div>
    <div class="col-xs-3">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m�ximo 10 caracteres"
               data-val-length-max="10" data-val-length-min="1" data-val-required="El n�mero exterior es un campo requerido"
               type="text" ng-init='a.noOut = "${(d.noOut == null) ? "" : d.noOut}"' ng-model="a.noOut" id="noOut" name="noOut">
        <span class="field-validation-valid" data-valmsg-for="noOut" data-valmsg-replace="true"></span>
    </div>
    <div class="col-xs-2 element-right">
        No Int:
    </div>
    <div class="col-xs-4">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m�ximo 10 caracteres"
               data-val-length-max="10" data-val-length-min="1" ng-init='a.noIn = "${(d.noIn == null) ? "" : d.noIn}"' id="noIn" name="noIn"
               type="text" value="" ng-model="a.noIn">
        <span class="field-validation-valid" data-valmsg-for="noIn" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2 element-right">
        �El domicilio es propio?:
    </div>
    <div class="col-xs-3">
        <select class="form-control element-center" ng-model="a.belong"
                ng-options="e.name for e in listElection"
                ng-change="a.belongId = a.belong.id;" ng-init='listElection = ${listElection};'></select>
    </div>
    <div class="col-xs-3 element-right">
        Tipo de domiclio:
    </div>
    <div class="col-xs-3">
        <select class="form-control element-center" ng-model="a.type"
                ng-options="e.name for e in listRegisterType"
                ng-change="a.typeId = a.type.id;" ng-init='listRegisterType= ${listRegisterType};'></select>
    </div>
    <br/>
    <br/>
    <div class="row" ng-show="a.typeId==3">
        <div class="col-xs-10 col-xs-offset-1">
            <div class="widget-box">
                <div class="widget-header">
                    <h4>Domicilio Anterior</h4>
                </div>
                <div class="widget-body">
                    <br/>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="row">
                                <div class="col-xs-5 element-left">
                                    Tiempo de residencia:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m�ximo 30 caracteres"
                                           data-val-length-max="30" data-val-length-min="1" data-val-required="El tiempo de residencia es un campo requerido" id="timeLiveD"
                                           ng-model="a.timeLive" ng-init='a.timeLive = "${(d.timeLive == null) ? "" : d.timeLive}"'
                                           type="text">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-8 col-xs-offset-4">
                                    <span class="field-validation-valid" data-valmsg-for="timeLive" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row element-left">
                                <div class="col-xs-3">
                                    Motivo de la mudanza:
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="reasonChange" name="reasonChange" ng-model="a.reasonChange" ng-init='a.reasonChange = "${(d.reasonChange ==null) ? "" : d.reasonChange}"' class="form-control"
                                              data-val="true" data-val-length="Debe tener al menos 1 y m�ximo 500 caracteres"
                                              data-val-length-max="500" data-val-length-min="1" data-val-required="La raz�n de cambio es un campo requerido" ></textarea>
                                    <br/>
                                    <span class="field-validation-valid" data-valmsg-for="reasonChange" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="hr hr-8"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" ng-show="a.typeId ==1 || a.typeId ==2">
        <div class="col-xs-10 col-xs-offset-1">
            <div class="widget-box">
                <div class="widget-header">
                    <h4>Domicilio actual/secundario</h4>
                </div>

                <div class="widget-body">
                    <br/>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="row">
                                <div class="col-xs-5">
                                    Tiempo de vivir en el domicilio:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m�ximo 30 caracteres"
                                           data-val-length-max="30" data-val-length-min="1" data-val-required="El tiempo de residencia es un campo requerido" id="timeLive"
                                           ng-model="a.timeLive" ng-init='a.timeLive = "${(d.timeLive == null) ? "" : d.timeLive}"'
                                           type="text">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-8 col-xs-offset-4">
                                    <span class="field-validation-valid" data-valmsg-for="timeLive" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-3">
                                    Descripci�n:
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="description" class="form-control" name="description" ng-model="a.description" ng-init='a.description = "${(d.description == null)? "" : d.description }"'
                                              data-val="true" data-val-length="Debe tener al menos 1 y m�ximo 500 caracteres"
                                              data-val-length-max="500" data-val-length-min="1" data-val-required="La descripci�n es un campo requerido"></textarea>
                                    <br/>
                                    <span class="field-validation-valid" data-valmsg-for="description" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row schedule_visible">
                                <div class="widget-box">
                                    <div class="widget-header">
                                        <h5><i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h5>
                                    </div>

                                    <div class="widget-body">
                                        <br/>
                                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/schedule.jsp"%>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="hr hr-8"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>