<div class="row">
    <input type="hidden" ng-update-hidden ng-model="a" ng-init="a=listImputedHome[$index]"/>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 col-xs-offset-1 element-right">
        <span ng-class="lstSourceInfoHomes['imputedHomes.address'+'.'+a.id]==true?'verified':'';">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('imputedHomes.address',a.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-address level-child="7" ng-show="verification"
           id-code="imputedHomes.address" id-element="{{a.id}}" address-id="{{a.addressId}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('imputedHomes.address',a.id);"></i>
            </span>
        <span ng-class="lstFinalInfoHomes['imputedHomes.address'+'.'+a.id]==true?'verified':'';">
        <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
           ng-click="showChoices('imputedHomes.address',a.id)"></i>
            </span>
        Direcci&oacute;n
    </div>
    <div class="col-xs-9">
        {{a.addressString}}
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-3 element-right">
    <span ng-class="lstSourceInfoHomes['imputedHomes.phone'+'.'+a.id]==true?'verified':'';">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('imputedHomes.phone',a.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification"
           code="imputedHomes.phone" id-element="{{a.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('imputedHomes.phone',a.id);"></i>
        </span>
        <span ng-class="lstFinalInfoHomes['imputedHomes.phone'+'.'+a.id]==true?'verified':'';">
        <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
           ng-click="showChoices('imputedHomes.phone',a.id)"></i>
    </span>
        Tel&eacute;fono:
    </div>
    <div class="col-xs-8">
        <textarea type="text" class="form-control width-100" ng-model="a.phone" name="imputedHomes.phone"
                  id="phone" value="{{a.phone}}"
                  data-val="true"
                  data-val-length="Debe tener al menos 5 y m&aacute;ximo 200 caracteres"
                  data-val-length-max="200" data-val-length-min="5"
                  data-val-required="El tel&eacute;efono es un campo requerido"></textarea>
         <span class="field-validation-valid" data-valmsg-for="phone"
               data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div>
    <div class="row">
        <div class="col-xs-3 element-right">
            <span ng-class="lstSourceInfoHomes['imputedHomes.homeType.id'+'.'+a.id]==true?'verified':'';">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('imputedHomes.homeType.id',a.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification"
               code="imputedHomes.homeType.id" id-element="{{a.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('imputedHomes.homeType.id',a.id);"></i>
                </span>
            <span ng-class="lstFinalInfoHomes['imputedHomes.homeType.id'+'.'+a.id]==true?'verified':'';">
            <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
               ng-click="showChoices('imputedHomes.homeType.id',a.id)"></i>
                </span>
            Tipo de propiedad:
        </div>
        <div class="col-xs-3">
            <input type="hidden" ng-model="a.homeTypeId" ng-update-hidden name="imputedHomes.homeType.id">
            <select class="form-control element-center" ng-model="a.homeType"
                    ng-options="e.name for e in lstHomeType" value="{{a.homeTypeId}}"
                    ng-change="a.homeTypeId = a.homeType.id;" ng-init='lstHomeType = ${lstHomeType};'></select>
        </div>
    </div>
    <br/>

    <div class="row" ng-show="a.homeType.specification == true">
        <div class="col-xs-3 element-right">
            Especif&iacute;que tipo de propiedad:
        </div>
        <div class="col-xs-8">
            <input type="text" class="form-control" ng-model="a.specification" name="imputedHomes.specification"
                   id="specification"
                   data-val="true" value="{{a.specification}}"
                   data-val-length="Debe tener al menos 1 y m&aacute;ximo 50 caracteres"
                   data-val-length-max="50" data-val-length-min="1"
                   data-val-required="La especificaci&oacute;n del tipo de propiedad es un campo requerido">
         <span class="field-validation-valid" data-valmsg-for="specification"
               data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>

<div>
    <div class="row removeClassHide">
        <div class="col-xs-3 element-right">
            <span ng-class="lstSourceInfoHomes['imputedHomes.registerType.id'+'.'+a.id]==true?'verified':'';">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('imputedHomes.registerType.id',a.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification"
               code="imputedHomes.registerType.id" id-element="{{a.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('imputedHomes.registerType.id',a.id);"></i>
                </span>
            <span ng-class="lstFinalInfoHomes['imputedHomes.registerType.id'+'.'+a.id]==true?'verified':'';">
            <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
               ng-click="showChoices('imputedHomes.registerType.id',a.id)"></i>
                </span>
            Tipo de domiclio:
        </div>
        <div class="col-xs-3">
            <input type="hidden" ng-model="a.typeId" ng-update-hidden name="imputedHomes.registerType.id">
            <select class="form-control element-center" ng-model="a.type"
                    ng-options="e.name for e in listType" value="{{a.typeId}}"
                    ng-change="a.typeId = a.type.id;" ng-init='listType= ${lstRegisterType };'></select>
        </div>

    </div>
    <br/>

    <div class="row" ng-show="a.typeId==1 || a.typeId==2">
        <div class="widget-box">
            <div class="widget-header">
                <div class="row">
                    <h4 ng-show="verification ==true">Domicilio {{a.type.name}}</h4>
                    <h4 ng-show="verification == false">Especificaci&oacute;n del domicilio</h4>
                </div>
            </div>
            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="row">
                            <div class="col-xs-3">
                                Tiempo de vivir en el domicilio:
                            </div>
                            <div class="col-xs-9">
                                <input class="form-control width-100" data-val="true"
                                       data-val-length="Debe tener al menos 1 y m&aacute;ximo 30 caracteres"
                                       data-val-length-max="30" data-val-length-min="1" name="imputedHomes.timeLive1"
                                       data-val-required="El tiempo de residencia es un campo requerido"
                                       id="timeLive" value="{{a.timeLive}}" type="text">
                                        <span class="field-validation-valid" data-valmsg-for="timeLive"
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
                                <textarea id="description" class="form-control width-100"
                                          name="imputedHomes.description"
                                          data-val="true" value="{{a.description}}"
                                          ng-model="a.description"
                                          data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                                          data-val-length-max="500" data-val-length-min="1"
                                          data-val-required="La descripci&oacute;n es un campo requerido"></textarea>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="description"
                                                              data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>

                        <div class="row" ng-show="a.typeId==2">
                            <div class="col-xs-3">
                                Raz&oacute;n por la que tiene <br/>un domicilio secundario:
                            </div>
                            <div class="col-xs-9">
                                <textarea id="reasonSecondary" class="form-control" name="imputedHomes.reasonSecondary"
                                          ng-model="a.reasonSecondary"
                                          data-val="true" value="{{a.reasonSecondary}}"
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
                            <div class="col-xs-12" ng-init='schListIH = pastToJson(listImputedHome[$index].schedule);'
                                 ng-show="verification || selectSource || showSchedule">
                                <div class="widget-header header-color-blue">
                                    <h5 class="bigger lighter">
                                        <h6> &nbsp;
                                            <span ng-class="lstSourceInfoHomes['imputedHomes.schedule'+'.'+a.id]==true?'verified':'';">
                                            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                                               ng-click="doConfirmVerifEqual('imputedHomes.schedule',a.id)"></i>
                                            <i class="icon-remove-circle red  icon-only bigger-120" verif-schedule
                                               ng-show="verification" id-code="imputedHomes.schedule"
                                               id-element="{{a.id}}"></i>
                                            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                                               ng-click="doConfirmVerifNotKnow('imputedHomes.schedule', a.id)"></i>
                                                </span>
                                            <span ng-class="lstFinalInfoHomes['imputedHomes.schedule'+'.'+a.id]==true?'verified':'';">
                                            <i class="red icon-list icon-only bigger-120" ng-show="selectSource"
                                               ng-click="showChoices('imputedHomes.schedule',a.id)"></i>
                                                </span>
                                            <i class="glyphicon glyphicon-calendar "></i>

                                            Disponibilidad</h6>

                                    </h5>
                                </div>

                                <div class="widget-body">
                                    <div class="widget-main no-padding">
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead class="thin-border-bottom">
                                            <tr>
                                                <th class="element-center">
                                                    D&iacute;a(s)
                                                </th>
                                                <th class="element-center">
                                                    Hora de inicio
                                                </th>
                                                <th class="element-center">
                                                    Hora de fin
                                                </th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <tr ng-repeat="sch in schListIH track by $index">
                                                <td class="element-center">
                                                    {{sch.day}}
                                                </td>
                                                <td class="element-center">
                                                    {{sch.start}}
                                                </td>
                                                <td class="element-center">
                                                    {{sch.end}}
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
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
    <div class="row" ng-show="a.typeId==3">
        <div class="col-xs-10 col-xs-offset-1">
            <div class="widget-box">
                <div class="widget-header">
                    <div class="row">
                        <h4 ng-show="verification ==true">Domicilio {{a.type.name}}</h4>
                        <h4 ng-show="verification == false">Especificaci&oacute;n del domicilio</h4>
                    </div>
                </div>
                <div class="widget-body">
                    <br/>

                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="row">
                                <div class="col-xs-3 element-left">
                                    Tiempo de residencia:
                                </div>
                                <div class="col-xs-9">
                                    <input class="form-control width-100" data-val="true"
                                           data-val-length="Debe tener al menos 1 y m&aacute;ximo 30 caracteres"
                                           data-val-length-max="30" data-val-length-min="1"
                                           data-val-required="El tiempo de residencia es un campo requerido"
                                           id="timeLiveD" name="imputedHomes.timeLive"
                                           value="{{a.timeLive}}"
                                           type="text">
                                 <span class="field-validation-valid" data-valmsg-for="timeLive"
                                       data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>

                            <div class="row element-left">
                                <div class="col-xs-3">
                                    Motivo de la mudanza:
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="reasonChange" name="imputedHomes.reasonChange"
                                              class="form-control width-100"
                                              data-val="true" value="{{a.reasonChange}}"
                                              ng-model="a.reasonChange"
                                              data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                                              data-val-length-max="500" data-val-length-min="1"
                                              data-val-required="La raz&oacute;n de cambio es un campo requerido"></textarea>
                                <span class="field-validation-valid" data-valmsg-for="reasonChange"
                                      data-valmsg-replace="true"></span>
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