<div class="row">
    <input type="hidden" ng-update-hidden ng-model="a" ng-init="a=listImputedHome[$index]"/>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 col-xs-offset-1 element-right">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('imputedHomes.address',a.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-address level-child="6" ng-show="verification"
           id-code="imputedHomes.address" id-element="{{a.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('imputedHomes.address',a.id);"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('imputedHomes.address',a.id)"></i>
        Direcci&oacute;n
    </div>
    <div class="col-xs-9">
        {{a.addressString}}
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-3 element-right">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('imputedHomes.address',a.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"
           code="imputedHomes.address" id-element="{{a.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('imputedHomes.address',a.id);"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('imputedHomes.belong.id',a.id)"></i>
        &iquest;El domicilio es propio?:
    </div>
    <div class="col-xs-2">
        <input type="hidden" ng-model="a.belongId" ng-update-hidden name="imputedHomes.belong.id">
        <select class="form-control element-center" ng-model="a.belong"
                ng-options="e.name for e in listElection"
                ng-change="a.belongId = a.belong.id;" ng-init='listElection = ${listElection};'></select>
    </div>
</div>
<br/>

<div>
<div class="row removeClassHide" >
    <div class="col-xs-3 element-right">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('imputedHomes.registerType.id',a.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification"
           code="imputedHomes.registerType.id" id-element="{{a.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('imputedHomes.registerType.id',a.id);"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('imputedHomes.registerType.id',a.id)"></i>
        Tipo de domiclio:
    </div>
    <div class="col-xs-3">
        <input type="hidden" ng-model="a.typeId" ng-update-hidden name="imputedHomes.registerType.id">
        <select class="form-control element-center" ng-model="a.type"
                ng-options="e.name for e in listType"
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
                                    <div class="col-xs-5">
                                        Tiempo de vivir en el domicilio:
                                    </div>
                                    <div class="col-xs-7">
                                        <input class="form-control" data-val="true"
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
                                        Descripci&oacute;n de c&oacute;mo llegar<br/>al domicilio:
                                    </div>
                                    <div class="col-xs-9">
                                        <textarea id="description" class="form-control"
                                                  name="imputedHomes.description"
                                                  data-val="true"
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

                                <div class="row">
                                    <div class="col-xs-12" ng-init='schListIH = pastToJson(listImputedHome[$index].schedule);' ng-show="verification || showChoices">
                                        <div class="widget-header header-color-blue">
                                            <h5 class="bigger lighter">
                                                <h6> &nbsp;
                                                    <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('imputedHomes.schedule',a.id)"></i>
                                                    <i class="icon-remove-circle red  icon-only bigger-120" verif-schedule
                                                       ng-show="verification" id-code ="imputedHomes.schedule" id-element ="{{a.id}}"></i>
                                                    <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('imputedHomes.schedule', a.id)"></i>
                                                    <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('imputedHomes.schedule',a.id)"></i>
                                                    <i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h6>
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
                                                    <tr ng-repeat ="sch in schListIH track by $index">
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
                            <div class="col-xs-5 element-left">
                                Tiempo de residencia:
                            </div>
                            <div class="col-xs-7">
                                <input class="form-control" data-val="true"
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
                                          class="form-control"
                                          data-val="true"
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