<div class="row">
    <input type="hidden" ng-update-hidden ng-model="a" ng-init="a=listImputedHome[$index]"/>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 col-xs-offset-1 element-right">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('imputedHomes.address',a.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-address level-child="6" ng-show="verification"
           code="imputedHomes.address" id-element="{{a.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('imputedHomes.address',a.id);"></i>
        Dirección
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
        ¿El domicilio es propio?:
    </div>
    <div class="col-xs-2">
        <select class="form-control element-center" ng-model="a.belong"
                ng-options="e.name for e in listElection"
                ng-change="a.belongId = a.belong.id;" ng-init='listElection = ${listElection};'></select>
    </div>
</div>
<br/>

<div>
<div class="row" ng-show="verification==false">
    <div class="col-xs-3 element-right">
        Tipo de domiclio:
    </div>
    <div class="col-xs-3">
        <select class="form-control element-center" ng-model="a.type"
                ng-options="e.name for e in listRegisterType"
                ng-change="a.typeId = a.type.id;" ng-init='listRegisterType= ${lstRegisterType };'></select>
    </div>

</div>
<br/>

<div class="row" ng-show="a.typeId==1 || a.typeId==2">
    <div class="col-xs-10 col-xs-offset-1">
        <div class="widget-box">
            <div class="widget-header">
                <div class="row">
                    <div class="col-xs-1">
                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifEqual('imputedHomes.address',a.id);"></i>
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="7"
                           ng-show="verification"
                           code="imputedHomes.address" id-element="{{a.id}}"></i>
                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifNotKnow('imputedHomes.address',a.id);"></i>
                    </div>
                    <div class="col-xs-10">
                        <h4 ng-show="verification ==true">Domicilio {{a.type.name}}</h4>
                        <h4 ng-show="verification == false">Especificación del domicilio</h4>
                    </div>
                </div>
            </div>
            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="row">
                                    <div class="col-xs-5">
                                        Tiempo de vivir en el domicilio:
                                    </div>
                                    <div class="col-xs-7">
                                        <input class="form-control" data-val="true"
                                               data-val-length="Debe tener al menos 1 y máximo 30 caracteres"
                                               data-val-length-max="30" data-val-length-min="1"
                                               data-val-required="El tiempo de residencia es un campo requerido"
                                               id="timeLive" value="{{a.timeLive}}" type="text">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-8 col-xs-offset-4">
                                                        <span class="field-validation-valid" data-valmsg-for="timeLive"
                                                              data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                                <br/>

                                <div class="row">
                                    <div class="col-xs-3">
                                        Descripción de cómo llegar<br/>al domicilio:
                                    </div>
                                    <div class="col-xs-9">
                                        <textarea id="description" class="form-control"
                                                  name="description"
                                                  data-val="true"
                                                  data-val-length="Debe tener al menos 1 y máximo 500 caracteres"
                                                  data-val-length-max="500" data-val-length-min="1"
                                                  data-val-required="La descripción es un campo requerido">{{a.description}}</textarea>
                                        <br/>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="description"
                                                              data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                                <br/>

                                <div class="row schedule_visible">
                                    <div class="widget-box">
                                        <div class="widget-header">
                                            <h5><i class="glyphicon glyphicon-calendar "></i>Disponibilidad
                                            </h5>
                                        </div>

                                        <div class="widget-body">
                                            <br/>
                                            <%@ include
                                                    file="/WEB-INF/jsp/reviewer/meeting/shared/schedule.jsp" %>
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
</div>
<div class="row" ng-show="a.typeId==3">
    <div class="col-xs-10 col-xs-offset-1">
        <div class="widget-box">
            <div class="widget-header">
                <div class="row">
                    <div class="col-xs-1">
                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifEqual('imputedHomes.address',a.id);"></i>
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="7"
                           ng-show="verification"
                           code="imputedHomes.address" id-element="{{a.id}}"></i>
                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifNotKnow('imputedHomes.address',a.id);"></i>
                    </div>
                    <div class="col-xs-10">
                        <h4 ng-show="verification ==true">Domicilio {{a.type.name}}</h4>
                        <h4 ng-show="verification == false">Especificación del domicilio</h4>
                    </div>
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
                                       data-val-length="Debe tener al menos 1 y máximo 30 caracteres"
                                       data-val-length-max="30" data-val-length-min="1"
                                       data-val-required="El tiempo de residencia es un campo requerido"
                                       id="timeLiveD"
                                       value="{{a.timeLive}}"
                                       type="text">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-4">
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
                                <textarea id="reasonChange" name="reasonChange"
                                          class="form-control"
                                          data-val="true"
                                          data-val-length="Debe tener al menos 1 y máximo 500 caracteres"
                                          data-val-length-max="500" data-val-length-min="1"
                                          data-val-required="La razón de cambio es un campo requerido">{{a.reasonChange}}</textarea>
                                <br/>
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