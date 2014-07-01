<div ng-controller="leavingController">
    <input type="hidden" value="${m.caseDetention.id}" name="caseDetention.id">
    <input type="hidden" ng-update-hidden ng-model="l.docId" name="leaveCountry.officialDocumentation.id" id="doc"
           ng-init='l.docId = ${(m.leaveCountry.officialDocumentation.id == null)? 'undefined': m.leaveCountry.officialDocumentation.id}'>
    <input type="hidden" ng-update-hidden ng-model="l.ocId" name="leaveCountry.livedCountry.id" id="oc"
           ng-init='l.ocId = ${(m.leaveCountry.livedCountry.id == null)? 'undefined':m.leaveCountry.livedCountry.id}'>
    <input type="hidden" ng-update-hidden ng-model="l.facId" name="leaveCountry.familyAnotherCountry.id" id="fac"
           ng-init='l.facId = ${(m.leaveCountry.familyAnotherCountry.id == null)? 'undefined':m.leaveCountry.familyAnotherCountry.id}'>
    <input type="hidden" ng-update-hidden ng-model="l.cfId" name="leaveCountry.communicationFamily.id" id="cf"
           ng-init='l.cfId = ${(m.leaveCountry.communicationFamily.id == null)? 'undefined':m.leaveCountry.communicationFamily.id}'>
    <input type="hidden" ng-update-hidden ng-model="countryId" name="leaveCountry.country.id" id="country"
           ng-init='l.countryId = ${(m.leaveCountry.country.id == null)? 'undefined':m.leaveCountry.country.id}'>

    <div class="row">
        <h2><i class="blue icon-globe bigger-100"></i> &nbsp;Facilidad de abandonar el pa?s</h2>
        <br/>

        <div ng-show="msgExito" class="alert alert-success element-center success-font">
            {{msgExito}}
        </div>
        <br/>

        <div class="row">
            <div class="col-xs-12">
                <div class="col-xs-9 element-left">
                    <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                       ng-show="verification"></i>
                    ¿El detenido cuenta con documentación oficial que facilite que abandone el país?:
                </div>
                <div class="col-xs-3">
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
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4"
                           ng-show="verification"></i>
                        ¿El detenido ha vivido en otro país?:
                    </div>
                    <div class="col-xs-3">
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
                        País:
                    </div>
                    <div class="col-xs-3">
                        <select class="form-control element-center" ng-model="country"
                                ng-options="e.name for e in listCountry"
                                url-request="/catalogs/getStatesByCountry.json"
                                ng-change="countryId = country.id;" ng-init='listCountry = ${lstCountry};'></select>
                    </div>
                    <div class="col-xs-2 element-right">
                        Estado:
                    </div>
                    <div class="col-xs-4">
                        <input class="form-control" data-val="true"
                               data-val-length="Debe tener al menos 3 y m?ximo 100 caracteres"
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
            <div class="row"  ng-show="l.ocId ==1">
                <div class="col-xs-3 element-right">
                    ¿Hace cuánto tiempo?:
                </div>
                <div class="col-xs-3">
                    <input class="form-control" data-val="true"
                           data-val-length="Debe tener al menos 3 y m?ximo 25 caracteres"
                           data-val-length-max="25" data-val-length-min="3"
                           data-val-required="El tiempo es un campo requerido"
                           type="text" ng-model="l.timeAgo" id="leaveCountry.timeAgo"
                           name="leaveCountry.timeAgo"
                           ng-init='l.timeAgo = "${(m.leaveCountry.timeAgo ==  null) ? "" : m.leaveCountry.timeAgo}";'>
                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.timeAgo"
                              data-valmsg-replace="true"></span>
                </div>
                <div class="col-xs-2">
                    Motivo por el que ahora vive en México:
                </div>
                <div class="col-xs-4">
                    <textarea id="leaveCountry.reason" class="form-control" data-val="true"
                              data-val-length="Debe tener al menos 3 y m?ximo 500 caracteres"
                              data-val-length-max="500" data-val-length-min="3"
                              data-val-required="La raz?n es un campo requerido" ng-model="l.reason"
                              name="leaveCountry.reason"
                              ng-init='l.reason = "${(m.leaveCountry.reason == null) ? "" : m.leaveCountry.reason}"'></textarea>
                             <span class="field-validation-valid" data-valmsg-for="leaveCountry.reason"
                                   data-valmsg-replace="true"></span>
                </div>
            </div>
            <br/>

            <div class="row"  ng-show="l.ocId ==1">
                <div class="col-xs-3 element-right">
                    Dirección:
                </div>
                <div class="col-xs-9">
                    <textarea ng-model="l.address"
                              ng-init='l.address = "${(m.leaveCountry.address == null) ? "" : m.leaveCountry.address}"'
                              data-val="true" data-val-required="La direcci?n es un campo requerido"
                              class="form-control"
                              data-val-length="Debe tener al menos 6 y m?ximo 500 caracteres"
                              name="leaveCountry.address" id="leaveCou ntry.address"
                              data-val-length-max="500" data-val-length-min="6"></textarea></div>

                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.address"
                              data-valmsg-replace="true"></span>
            </div>


        </div>
    </div>
    <br/>

    <div>
        <div class="row">
            <br/>

            <div class="col-xs-9 element-left">
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification"></i>
                ¿El detenido cuenta con familiares y/o amigos cercanos en otro país?:
            </div>
            <div class="col-xs-3">
                <select class="form-control element-center" ng-model="l.fac"
                        ng-options="e.name for e in listElection"
                        ng-change="l.facId = l.fac.id;" ng-init='listElection = ${listElection};'></select>
            </div>

        </div>
        <br/>

        <div class="row" ng-show="l.facId ==1">

            <div class="col-xs-4 element-left">
                ¿Mantiene comunicación con ellos?:
            </div>
            <div class="col-xs-2">
                <select class="form-control element-center" ng-model="l.cf"
                        ng-options="e.name for e in listElection"
                        ng-change="l.cfId = l.cf.id;" ng-init='listElection = ${listElection};'></select>
            </div>
            <div class="col-xs-2 element-left" ng-show="l.cfId == 1">
                ¿Por qué medio?:
            </div>
            <div class="col-xs-4" ng-show="l.cfId == 1">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 2 y m?ximo 50 caracteres"
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