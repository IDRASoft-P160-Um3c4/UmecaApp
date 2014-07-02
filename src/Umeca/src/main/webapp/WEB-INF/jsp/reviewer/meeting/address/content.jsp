
<div class="row">
    <input type="hidden" ng-update-hidden ng-model="id" name="idCase" id="idCase"
           ng-init="idCase = ${(idCase == null) ? 0 : idCase};">
    <input type="hidden" ng-update-hidden ng-model="a.typeId" name="registerType.id" id="typeId"
           ng-init="a.typeId = ${typeId == null ? "undefined" : typeId};">
    <input type="hidden" ng-update-hidden ng-model="a.belongId" name="belong.id" id="perId"
           ng-init="a.belongId = ${belongId == null ? "undefined" : belongId};">
    <input type="hidden" ng-update-hidden ng-model="id" name="id" id="id"
           ng-init='id = "${(d.id == null)? '':d.id}"'>
    <input type="hidden" ng-update-hidden ng-model="a.timeLive" name="timeLive" id="hdnTimeLive">
    <div class="col-xs-2 element-right">
        <i class="icon-remove-sign red link-image" style="display: none;"  onclick="verification(this)"></i>
        <a class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-list-alt orange" style="display: none;"></i> </a>
            <ul class="dropdown-menu">
                <li>
                    <label for="r1"><input type="radio" name="radiocp" id="r1" onclick="checkSource()"> &nbsp;José Ramierez</label><br/>
                        <label>&nbsp;<i class="icon-remove red"> &nbsp;</i>asldjkfas fasdlfkj sdlkfj aslkdf</label>
                    <div class="hr hr-2"></div>
                </li>
                <li>
                    <label for="r2"><input type="radio" name="radiocp" id="r2" onclick="checkSource()"> &nbsp;Carlos Gómez</label><br/>
                    <label>&nbsp;<i class="icon-ok green"> &nbsp;</i>55569</label>
                    <div class="hr hr-2"></div>
                </li>
            </ul>
        </a>
        <label for="cp">Código  postal:</label>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-10 col-xs-offset-1">
    <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
        </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-3 element-right">
        ¿El domicilio es propio?:
    </div>
    <div class="col-xs-2">
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
                                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y máximo 30 caracteres"
                                           data-val-length-max="30" data-val-length-min="1" data-val-required="El tiempo de residencia es un campo requerido" id="timeLiveD"
                                           ng-model="a.timeLive" ng-init='a.timeLive = "${(d.timeLive == null) ? "" : d.timeLive}"'    name="timeLiveD"
                                           type="text">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-8 col-xs-offset-4">
                                    <span class="field-validation-valid" data-valmsg-for="timeLiveD" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row element-left">
                                <div class="col-xs-3">
                                    Motivo de la mudanza:
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="reasonChange" name="reasonChange" ng-model="a.reasonChange" ng-init='a.reasonChange = "${(d.reasonChange ==null) ? "" : d.reasonChange}"' class="form-control"
                                              data-val="true" data-val-length="Debe tener al menos 1 y máximo 500 caracteres"
                                              data-val-length-max="500" data-val-length-min="1" data-val-required="La razón de cambio es un campo requerido" ></textarea>
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
                                    <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y máximo 30 caracteres"
                                           data-val-length-max="30" data-val-length-min="1" data-val-required="El tiempo de residencia es un campo requerido" id="timeLiveA"
                                           ng-model="a.timeLive" ng-init='a.timeLive = "${(d.timeLive == null) ? "" : d.timeLive}"'        name="timeLiveA"
                                           type="text">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-8 col-xs-offset-4">
                                    <span class="field-validation-valid" data-valmsg-for="timeLiveA" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-3">
                                    Descripción de cómo llegar<br/>al domicilio:
                                </div>
                                <div class="col-xs-9">
                                    <textarea id="description" class="form-control" name="description" ng-model="a.description" ng-init='a.description = "${(d.description == null)? "" : d.description }"'
                                              data-val="true" data-val-length="Debe tener al menos 1 y máximo 500 caracteres"
                                              data-val-length-max="500" data-val-length-min="1" data-val-required="La descripción es un campo requerido"></textarea>
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