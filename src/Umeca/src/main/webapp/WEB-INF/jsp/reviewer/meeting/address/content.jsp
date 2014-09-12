<div class="row">
    <input type="hidden" ng-update-hidden ng-model="id" name="idCase" id="idCase"
           ng-init="idCase = ${(idCase == null) ? 0 : idCase};">
    <input type="hidden" ng-update-hidden ng-model="a.typeId" name="registerType.id" id="typeId"
           ng-init="a.typeId = ${typeId == null ? "undefined" : typeId};">

    <input type="hidden" ng-update-hidden ng-model="id" name="id" id="id"
           ng-init='id = "${(d.id == null)? '':d.id}"'>
    <input type="hidden" ng-update-hidden ng-model="a.timeLive" name="timeLive" id="hdnTimeLive">
</div>
<br/>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1">
        <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-2 element-left col-xs-offset-1">
        Tel&eacute;fono:
    </div>
    <div class="col-xs-8">
        <textarea type="text" class="width-100" ng-model="a.phone" name="phone"
               id="phone"
               data-val="true"
               data-val-length="Debe tener al menos 5 y m&aacute;ximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="5"
               data-val-required="El tel&eacute;efono es un campo requerido"
               ng-init='a.phone = "${d.phone == null ? "" : d.phone}"'></textarea>
         <span class="field-validation-valid" data-valmsg-for="phone"
               data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 element-left col-xs-offset-1">
        Tipo de propiedad:
    </div>
    <div class="col-xs-2">
        <input type="hidden" ng-update-hidden ng-model="a.homeTypeId" name="homeType.id" id="perId"
               ng-init="a.homeTypeId = ${homeTypeId == null ? "undefined" : homeTypeId};">
        <select class="form-control element-center" ng-model="a.homeType"
                ng-options="e.name for e in lstHomeType"
                ng-change="a.homeTypeId = a.homeType.id;" ng-init='lstHomeType = ${lstHomeType};'></select>
    </div>
    <div class="col-xs-3 element-right">
        Tipo de domiclio:
    </div>
    <div class="col-xs-3">
        <select class="form-control element-center" ng-model="a.type"
                ng-options="e.name for e in listType"
                ng-change="a.typeId = a.type.id;" ng-init='listType= ${listRegisterType};'></select>
    </div>
</div>
<br/>
<div class="row" ng-show="a.homeType.specification == true">
    <div class="col-xs-3 element-right">
        Especif&iacute;que tipo de propiedad:
    </div>
    <div class="col-xs-8">
        <input type="text" class="form-control"ng-model="a.specification" name="specification"
               id="specification"
               data-val="true"
               data-val-length="Debe tener al menos 1 y m&aacute;ximo 50 caracteres"
               data-val-length-max="50" data-val-length-min="1"
               data-val-required="La especificaci&oacute;n del tipo de propiedad es un campo requerido"
               ng-init='a.specification = "${d.specification == null ? "" : d.specification}"'>
         <span class="field-validation-valid" data-valmsg-for="specification"
               data-valmsg-replace="true"></span>
    </div>
</div>
<div class="row">
    <br/>
    <br/>

    <div class="row" ng-show="a.typeId==3">
        <div class="col-xs-10 col-xs-offset-1">
            <div class="widget-box">
                <div class="widget-header">
                    <h4>Domicilio {{a.type.name}}</h4>
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
                                    <input class="form-control" data-val="true"
                                           data-val-length="Debe tener al menos 1 y m&aacute;ximo 30 caracteres"
                                           data-val-length-max="30" data-val-length-min="1"
                                           data-val-required="El tiempo de residencia es un campo requerido"
                                           id="timeLiveD"
                                           ng-model="a.timeLive"
                                           ng-init='a.timeLive = "${(d.timeLive == null) ? "" : d.timeLive}"'
                                           name="timeLiveD"
                                           type="text">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-8 col-xs-offset-4">
                                    <span class="field-validation-valid" data-valmsg-for="timeLiveD"
                                          data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>

                            <div class="row element-left">
                                <div class="col-xs-3">
                                    Motivo de la mudanza:
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="reasonChange" name="reasonChange" ng-model="a.reasonChange"
                                              ng-init='a.reasonChange = "${(d.reasonChange ==null) ? "" : d.reasonChange}"'
                                              class="form-control"
                                              data-val="true"
                                              data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                                              data-val-length-max="500" data-val-length-min="1"
                                              data-val-required="La raz&oacute;n de cambio es un campo requerido"></textarea>
                                    <br/>
                                    <span class="field-validation-valid" data-valmsg-for="reasonChange"
                                          data-valmsg-replace="true"></span>
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
                    <h4>Domicilio {{a.type.name}}</h4>
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
                                    <input class="form-control" data-val="true"
                                           data-val-length="Debe tener al menos 1 y m&aacute;ximo 30 caracteres"
                                           data-val-length-max="30" data-val-length-min="1"
                                           data-val-required="El tiempo de residencia es un campo requerido"
                                           id="timeLiveA"
                                           ng-model="a.timeLive"
                                           ng-init='a.timeLive = "${(d.timeLive == null) ? "" : d.timeLive}"'
                                           name="timeLiveA"
                                           type="text">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-8 col-xs-offset-4">
                                    <span class="field-validation-valid" data-valmsg-for="timeLiveA"
                                          data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-xs-3">
                                    Descripci&oacute;n de c&oacute;mo llegar al domicilio:<br/>
                                    <label class="info-example">(color de casa, ruta, etc.)</label>
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="description" class="form-control" name="description"
                                              ng-model="a.description"
                                              ng-init='a.description = "${(d.description == null)? "" : d.description }"'
                                              data-val="true"
                                              data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                                              data-val-length-max="500" data-val-length-min="1"
                                              data-val-required="La descripci&oacute;n es un campo requerido"></textarea>
                                    <br/>
                                    <span class="field-validation-valid" data-valmsg-for="description"
                                          data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row" ng-show="a.typeId==2">
                                <div class="col-xs-3">
                                   Raz&oacute;n por la que tiene <br/>un domicilio secundario:
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="reasonSecondary" class="form-control" name="reasonSecondary"
                                              ng-model="a.reasonSecondary"
                                              ng-init='a.reasonSecondary = "${(d.reasonSecondary == null)? "" : d.reasonSecondary }"'
                                              data-val="true"
                                              data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                                              data-val-length-max="500" data-val-length-min="1"
                                              data-val-required="La raz&oacute;n es un campo requerido"></textarea>
                                    <br/>
                                    <span class="field-validation-valid" data-valmsg-for="reasonSecondary"
                                          data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="widget-header header-color-blue">
                                        <h5 class="bigger lighter">
                                            <h6><i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h6>
                                        </h5>
                                    </div>

                                    <div class="widget-body">
                                        <br/>
                                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/schedule.jsp" %>
                                    </div>
                                </div>
                            </div>
                            <br/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>