<div ng-controller="leavingController">
    <input type="hidden" value="${m.caseDetention.id}" name="caseDetention.id">
    <div class="row">
        <div class="center row">
            <h2><i class="blue icon-globe bigger-100"></i> &nbsp;Facilidad de abandonar el pa&iacute;s</h2>
        </div>
        <br/>

        <div class="row" >
            <br/>
            <div class="row" ng-show="readOnly == false">
                <div class="col-xs-12">

                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <b>  Establecer toda la informaci&oacute;n de Facilidad de abandonar el pa&iacute;s
                        con:</b>
                </div>
                <div class="col-xs-10 col-xs-offset-1 text-info"  style="padding-top: 8px;">
                    <i class="purple glyphicon glyphicon-user bigger-160"
                       ng-click="showChoicesSection(8,undefined,1,'Facilidad de abandonar el pa&iacute;s')"  style="cursor: pointer;"></i>
                    &nbsp;&nbsp;&nbsp;Informaci&oacute;n que proporcion&oacute; el imputado.
                </div>
                <div class="col-xs-10 col-xs-offset-1 text-info" style="padding-top: 8px;">
                    <i class="blue icon-question-sign  icon-only bigger-160" style="cursor: pointer;"
                       ng-click="showChoicesSection(8,undefined,-1,'Facilidad de abandonar el pa&iacute;s')"></i>
                    &nbsp;&nbsp;&nbsp;No se pudo verificar
                </div>
            </div>
            <div class="row"  ng-show="readOnly == false">
                <div col-xs-12>
                    <div class="hr hr-8"></div>
                </div>
            </div>

        <div ng-show="msgExito" class="alert alert-success element-center success-font">
            {{msgExito}}
        </div>
        <br/>

        <div class="row">
            <div class="col-xs-12">
                <div class="col-xs-9 element-left">
                    <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                       ng-click="doConfirmVerifEqual('leaveCountry.officialDocumentation.id')"></i>
                    <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                       ng-show="verification" code="officialDocumentation"></i>
                    <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                       ng-click="doConfirmVerifNotKnow('leaveCountry.officialDocumentation.id')"></i>
                    <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('leaveCountry.officialDocumentation.id')"></i>
                    &iquest;El imputado cuenta con documentaci&oacute;n oficial que facilite que abandone el pa&iacute;s?:
                </div>
                <div class="col-xs-3">
                    <input type="hidden" ng-update-hidden ng-model="l.docId" name="leaveCountry.officialDocumentation.id" id="doc"
                           ng-init='l.docId = ${(m.leaveCountry.officialDocumentation.id == null)? 'undefined': m.leaveCountry.officialDocumentation.id}'>
                    <select class="form-control element-center" ng-model="l.doc"
                            ng-options="e.name for e in listElection"
                            ng-change="l.docId = l.doc.id;" ng-init='listElection = ${listElection};'></select>
                </div>
            </div>
        </div>
        <br/>

        <div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="col-xs-9 element-left">
                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifEqual('leaveCountry.livedCountry')"></i>
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4"
                           ng-show="verification" code="leaveCountry.livedCountry"></i>
                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifNotKnow('leaveCountry.livedCountry')"></i>
                        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('leaveCountry.livedCountry.id')"></i>
                        &iquest;El detenido ha vivido en otro pa&iacute;s?:
                    </div>
                    <div class="col-xs-3">
                        <input type="hidden" ng-update-hidden ng-model="l.ocId" name="leaveCountry.livedCountry.id" id="oc"
                               ng-init='l.ocId = ${(m.leaveCountry.livedCountry.id == null)? 'undefined':m.leaveCountry.livedCountry.id}'>
                        <select class="form-control element-center" ng-model="l.oc"
                                ng-options="e.name for e in listElection"
                                ng-change="l.ocId = l.oc.id;" ng-init='listElection = ${listElection};'></select>
                    </div>
                </div>
            </div>
            <div class="row" ng-show="l.ocId ==1">
                <br/>

                <div class="row">
                    <div class="col-xs-2 element-left col-xs-offset-1">
                        Pa&iacute;s:
                    </div>
                    <div class="col-xs-3">
                        <input type="hidden" ng-update-hidden ng-model="l.countryId" name="leaveCountry.country.id" id="country"
                               ng-init='l.countryId = ${(m.leaveCountry.country.id == null)? 'undefined':m.leaveCountry.country.id}'>
                        <select class="form-control element-center" ng-model="l.country"
                                ng-options="e.name for e in listCountry"
                                url-request="/catalogs/getStatesByCountry.json"
                                ng-change="l.countryId = l.country.id;" ng-init='listCountry = ${lstCountry};'></select>
                    </div>
                    <div class="col-xs-2 element-right">
                        Estado:
                    </div>
                    <div class="col-xs-3">
                        <input class="form-control" data-val="true"
                               data-val-length="Debe tener al menos 3 y m&aacute;ximo 100 caracteres"
                               data-val-length-max="100" data-val-length-min="3"
                               data-val-required="El estado es un campo requerido"
                               type="text" ng-model="l.state" id="leaveCountry.state"
                               name="leaveCountry.state"
                               ng-init='l.state = "${(m.leaveCountry.state ==  null) ? "" : m.leaveCountry.state}";'>
                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.state"
                              data-valmsg-replace="true"></span>
                    </div>
                </div>

                <br/>
            </div>
            <div class="row" ng-show="l.ocId ==1">
                <div class="col-xs-3 element-right">
                    &iquest;Hace cu&aacute;nto tiempo?:
                </div>
                <div class="col-xs-3">
                    <input class="form-control" data-val="true"
                           data-val-length="Debe tener al menos 3 y m&aacute;ximo 250 caracteres"
                           data-val-length-max="250" data-val-length-min="3"
                           data-val-required="El tiempo es un campo requerido"
                           type="text" ng-model="l.timeAgo" id="leaveCountry.timeAgo"
                           name="leaveCountry.timeAgo"
                           ng-init='l.timeAgo = "${(m.leaveCountry.timeAgo ==  null) ? "" : m.leaveCountry.timeAgo}";'>
                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.timeAgo"
                              data-valmsg-replace="true"></span>
                </div>
                <div class="col-xs-2 element-right">
                    Motivo por el que ahora vive en M&eacute;xico:
                </div>
                <div class="col-xs-4">
                    <textarea id="leaveCountry.reason" class="form-control" data-val="true"
                              data-val-length="Debe tener al menos 3 y m&aacute;ximo 500 caracteres"
                              data-val-length-max="500" data-val-length-min="3"
                              data-val-required="La raz&oacute;n es un campo requerido" ng-model="l.reason"
                              name="leaveCountry.reason"
                              ng-init='l.reason = "${(m.leaveCountry.reason == null) ? "" : m.leaveCountry.reason}"'></textarea>
                    <span class="field-validation-valid" data-valmsg-for="leaveCountry.reason"
                                   data-valmsg-replace="true"></span>
                </div>
            </div>
            <br/>

            <div class="row" ng-show="l.ocId ==1">
                <div class="col-xs-3 element-right">
                    Direcci&oacute;n:
                </div>
                <div class="col-xs-9">
                    <textarea ng-model="l.address"
                              ng-init='l.address = "${(m.leaveCountry.address == null) ? "" : m.leaveCountry.address}"'
                              data-val="true" data-val-required="La direcci&oacute;n es un campo requerido"
                              class="form-control"
                              data-val-length="Debe tener al menos 6 y m&aacute;ximo 500 caracteres"
                              name="leaveCountry.address" id="leaveCou ntry.address"
                              data-val-length-max="500" data-val-length-min="6"></textarea>

                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.address"
                              data-valmsg-replace="true"></span>
                </div>
            </div>
        </div>
    </div>
    <br/>

    <div>
        <div class="row">
            <br/>

            <div class="col-xs-9 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('leaveCountry.familyAnotherCountry')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification" code="leaveCountry.familyAnotherCountry"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('leaveCountry.familyAnotherCountry')"></i>
                <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('leaveCountry.familyAnotherCountry.id')"></i>
                &iquest;El detenido cuenta con familiares y/o amigos cercanos en otro pa&iacute;s?:
            </div>
            <div class="col-xs-3">
                <input type="hidden" ng-update-hidden ng-model="l.facId" name="leaveCountry.familyAnotherCountry.id" id="fac"
                       ng-init='l.facId = ${(m.leaveCountry.familyAnotherCountry.id == null)? 'undefined':m.leaveCountry.familyAnotherCountry.id}'>
                <select class="form-control element-center" ng-model="l.fac"
                        ng-options="e.name for e in listElection"
                        ng-change="l.facId = l.fac.id;" ng-init='listElection = ${listElection};'></select>
            </div>

        </div>
        <br/>

        <div class="row" ng-show="l.facId ==1">

            <div class="col-xs-4 element-left">
                &iquest;Mantiene comunicaci&oacute;n con ellos?:
            </div>
            <div class="col-xs-2">
                <input type="hidden" ng-update-hidden ng-model="l.cfId" name="leaveCountry.communicationFamily.id" id="cf"
                       ng-init='l.cfId = ${(m.leaveCountry.communicationFamily.id == null)? 'undefined':m.leaveCountry.communicationFamily.id}'>
                <select class="form-control element-center" ng-model="l.cf"
                        ng-options="e.name for e in listElection"
                        ng-change="l.cfId = l.cf.id;" ng-init='listElection = ${listElection};'></select>
            </div>
            <div class="col-xs-2 element-left" ng-show="l.cfId == 1">
                &iquest;Por qu&eacute; medio?:
            </div>
            <div class="col-xs-4" ng-show="l.cfId == 1">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 2 y m&aacute;ximo 50 caracteres"
                       data-val-length-max="50" data-val-length-min="2"
                       data-val-required="El medio es un campo requerido"
                       type="text" value="" id="leaveCountry.media" name="leaveCountry.media" ng-model="l.media"
                       ng-init='l.media = "${(m.leaveCountry.media == null) ? "":m.leaveCountry.media}";'>
                     <span class="field-validation-valid" data-valmsg-for="leaveCountry.media"
                           data-valmsg-replace="true"></span>
            </div>

        </div>
    </div>
</div>