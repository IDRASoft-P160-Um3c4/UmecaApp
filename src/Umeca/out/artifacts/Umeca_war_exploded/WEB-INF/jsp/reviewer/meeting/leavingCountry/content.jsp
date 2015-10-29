<div ng-controller="leavingController">
    <input type="hidden" value="${m.caseDetention.id}" name="caseDetention.id">

    <div class="row">

        <div class="row" ng-show="verification || selectSource">
            <div class="col-xs-10 col-xs-offset-1 element-left">
                <label class="text-primary">Observaciones: <b>${m.commentCountry}</b></label>
            </div>
        </div>
        <br/>

        <div class="row" ng-show="readOnly == false">
            <div class="col-xs-12">

                &nbsp;&nbsp;&nbsp;&nbsp;
                <b> Establecer toda la informaci&oacute;n de Facilidad de abandonar el pa&iacute;s
                    con:</b>
            </div>
            <div class="col-xs-10 col-xs-offset-1 text-info" style="padding-top: 8px;">
                <i class="purple glyphicon glyphicon-user bigger-160"
                   ng-click="showChoicesSection(8,undefined,1,'Facilidad de abandonar el pa&iacute;s')"
                   style="cursor: pointer;"></i>
                &nbsp;&nbsp;&nbsp;Informaci&oacute;n que proporcion&oacute; el imputado.
            </div>
            <div class="col-xs-10 col-xs-offset-1 text-info" style="padding-top: 8px;">
                <i class="blue icon-question-sign  icon-only bigger-160" style="cursor: pointer;"
                   ng-click="showChoicesSection(8,undefined,-1,'Facilidad de abandonar el pa&iacute;s')"></i>
                &nbsp;&nbsp;&nbsp;No se pudo verificar
            </div>
        </div>
        <div class="row" ng-show="readOnly == false">
            <div col-xs-12>
                <div class="hr hr-8"></div>
            </div>
        </div>

        <br/>

        <div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="col-xs-4 element-left">
                        <span ng-class="lstSourceInfoLeavingCountry['leaveCountry.officialDocumentation.id']==true?'verified':'';">
                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifEqual('leaveCountry.officialDocumentation.id')"></i>
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="5"
                           ng-show="verification" code="leaveCountry.officialDocumentation.id"></i>
                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifNotKnow('leaveCountry.officialDocumentation.id')"></i>
                            </span>
                        <span ng-class="lstFinalInfoLeavingCountry['leaveCountry.officialDocumentation.id']==true?'verified':'';">
                        <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                           ng-click="showChoices('leaveCountry.officialDocumentation.id')"></i>
                            </span>
                        &iquest;El imputado cuenta con documentaci&oacute;n oficial que facilite que abandone el pa&iacute;s?:
                    </div>
                    <div class="col-xs-2">
                        <input type="hidden" ng-update-hidden ng-model="l.docId"
                               name="leaveCountry.officialDocumentation.id"
                               id="doc"
                               ng-init='l.docId = ${(m.leaveCountry.officialDocumentation.id == null)? 'undefined': m.leaveCountry.officialDocumentation.id}'>
                        <select class="form-control element-center" ng-model="l.doc"
                                ng-options="e.name for e in listElection"
                                ng-change="l.docId = l.doc.id;" ng-init='listElection = ${listElection};'></select>
                    </div>
                </div>
            </div>
            <br/>

            <div class="row" ng-show="l.doc.id==1">
                <div class="col-xs-12">
                    <div class="col-xs-2 element-left">
                        Documentaci&oacute;n:
                    </div>
                    <div class="col-xs-4">
                        <input type="hidden" ng-update-hidden ng-model="l.immigrationDocId"
                               name="leaveCountry.immigrationDocument.id"
                               id="immigrationDcc" ng-show="l.doc.id==1"
                               ng-init='l.immigrationDocId = ${(m.leaveCountry.immigrationDocument.id == null)? 'undefined': m.leaveCountry.immigrationDocument.id}'>
                        <select class="form-control element-center" ng-model="l.immigrationDoc"
                                ng-options="e.name for e in listImmigrationDoc" value="{{l.immigrationDocId}}"
                                ng-change="l.immigrationDocId = l.immigrationDoc.id;"
                                ng-init='listImmigrationDoc = ${listImmigrationDoc};'></select>
                    </div>
                    <div class="col-xs-2 element-right" ng-show="l.immigrationDoc.specification">
                        Especifique documentaci&oacute;n:
                    </div>
                    <div class="col-xs-4" ng-show="l.doc.id == 1 && l.immigrationDoc.specification">
                        <input class="form-control" ng-show="l.doc.id == 1 && l.immigrationDoc.specification"
                               data-val-length="Debe tener al menos 1 y m&aacute;ximo 50 caracteres"
                               data-val-length-max="50" data-val-length-min="1"
                               data-val-required="La especificaci&oacute;n es un campo requerido"
                               type="text" ng-model="l.specficationImmigranDoc"
                               id="leaveCountry.specficationImmigranDoc"
                               name="leaveCountry.specficationImmigranDoc" value="{{l.specficationImmigranDoc}}"
                               ng-init='l.specficationImmigranDoc = "${(m.leaveCountry.specficationImmigranDoc ==  null) ? "" : m.leaveCountry.specficationImmigranDoc}";'>
                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.specficationImmigranDoc"
                              data-valmsg-replace="true"></span>
                    </div>
                </div>
            </div>
            <br/>
        </div>

        <div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="col-xs-4 element-left">
                        <span ng-class="lstSourceInfoLeavingCountry['leaveCountry.livedCountry.id']==true?'verified':'';">
                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifEqual('leaveCountry.livedCountry.id')"></i>
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="5"
                           ng-show="verification" code="leaveCountry.livedCountry.id"></i>
                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifNotKnow('leaveCountry.livedCountry.id')"></i>
                            </span>
                        <span ng-class="lstFinalInfoLeavingCountry['leaveCountry.livedCountry.id']==true?'verified':'';">
                        <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                           ng-click="showChoices('leaveCountry.livedCountry.id')"></i>
                            </span>
                        &iquest;El detenido ha vivido en otro pa&iacute;s?:
                    </div>
                    <div class="col-xs-2">
                        <input type="hidden" ng-update-hidden ng-model="l.ocId" name="leaveCountry.livedCountry.id"
                               id="oc"
                               ng-init='l.ocId = ${(m.leaveCountry.livedCountry.id == null)? 'undefined':m.leaveCountry.livedCountry.id}'>
                        <select class="form-control element-center" ng-model="l.oc"
                                ng-options="e.name for e in listElection" value="{{l.oc.id}}"
                                ng-change="l.ocId = l.oc.id;" ng-init='listElection = ${listElection};'></select>
                    </div>
                </div>
            </div>
            <br/>

            <div class="row" ng-show="l.ocId ==1">
                <div class="col-xs-12">
                    <div class="col-xs-1 element-left">
                        Pa&iacute;s:
                    </div>
                    <div class="col-xs-5">
                        <input type="hidden" ng-update-hidden ng-model="l.countryId" name="leaveCountry.country.id"
                               id="country" ng-show="l.ocId ==1"
                               ng-init='l.countryId = ${(m.leaveCountry.country.id == null)? 'undefined':m.leaveCountry.country.id}'>
                        <select class="form-control element-center" ng-model="l.country"
                                ng-options="e.name for e in listCountry" value="{{l.country.id}}"
                                url-request="/catalogs/getStatesByCountry.json"
                                ng-change="l.countryId = l.country.id;" ng-init='listCountry = ${lstCountry};'></select>
                    </div>
                    <div class="col-xs-2 element-right">
                        Estado:
                    </div>
                    <div class="col-xs-4">
                        <input class="form-control" ng-show="l.ocId ==1"
                               data-val-length="Debe tener al menos 3 y m&aacute;ximo 100 caracteres"
                               data-val-length-max="100" data-val-length-min="3"
                               data-val-required="El estado es un campo requerido"
                               type="text" ng-model="l.state" id="leaveCountry.state"
                               name="leaveCountry.state" value="{{l.state}}"
                               ng-init='l.state = "${(m.leaveCountry.state ==  null) ? "" : m.leaveCountry.state}";'>
                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.state"
                              data-valmsg-replace="true"></span>
                    </div>
                </div>
            </div>
            <br/>

            <div class="row" ng-show="l.ocId ==1">
                <div class="col-xs-12">
                    <div class="col-xs-2 element-left">
                        &iquest;Hace cu&aacute;nto tiempo?:
                    </div>
                    <div class="col-xs-4" ng-show="l.ocId ==1">
                        <input class="form-control"
                               data-val-length="Debe tener al menos 3 y m&aacute;ximo 250 caracteres"
                               data-val-length-max="250" data-val-length-min="3" ng-show="l.ocId ==1"
                               data-val-required="El tiempo es un campo requerido"
                               type="text" ng-model="l.timeAgo" id="leaveCountry.timeAgo"
                               name="leaveCountry.timeAgo" value="{{l.timeAgo}}"
                               ng-init='l.timeAgo = "${(m.leaveCountry.timeAgo ==  null) ? "" : m.leaveCountry.timeAgo}";'>
                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.timeAgo"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-2 element-right">
                        A&ntilde;os que vivi&oacute; en el extranjero:
                    </div>
                    <div class="col-xs-4" ng-show="l.ocId ==1">
                        <input class="form-control" ng-show="l.ocId ==1"
                               data-val-length="Debe tener al menos 1 y m&aacute;ximo 50 caracteres"
                               data-val-length-max="50" data-val-length-min="1"
                               data-val-required="A&ntilde;os que vivi&oacute; en el extranjero es un campo requerido"
                               type="text" ng-model="l.timeResidence" id="leaveCountry.timeResidence"
                               name="leaveCountry.timeResidence" value="{{l.timeResidence}}"
                               ng-init='l.timeResidence = "${(m.leaveCountry.timeResidence ==  null) ? "" : m.leaveCountry.timeResidence}";'>
                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.timeResidence"
                              data-valmsg-replace="true"></span>
                    </div>
                </div>
            </div>
            <br/>

            <div class="row" ng-show="l.ocId ==1">
                <div class="col-xs-12">
                    <div class="col-xs-3 element-left">
                        Motivo por el que ahora vive en M&eacute;xico:
                    </div>
                    <div class="col-xs-9" ng-show="l.ocId ==1">
            <textarea id="leaveCountry.reason" class="form-control" ng-show="l.ocId ==1"
                      data-val-length="Debe tener al menos 3 y m&aacute;ximo 500 caracteres"
                      data-val-length-max="500" data-val-length-min="3"
                      data-val-required="La raz&oacute;n es un campo requerido" ng-model="l.reason"
                      name="leaveCountry.reason" value="{{l.reason}}"
                      ng-init='l.reason = "${(m.leaveCountry.reason == null) ? "" : m.leaveCountry.reason}"'></textarea>
                    <span class="field-validation-valid" data-valmsg-for="leaveCountry.reason"
                          data-valmsg-replace="true"></span>
                    </div>
                </div>
            </div>
            <br/>

            <div class="row" ng-show="l.ocId ==1">
                <div class="col-xs-12">
                    <div class="col-xs-3 element-left">
                        Direcci&oacute;n:
                    </div>
                    <div class="col-xs-9" ng-show="l.ocId ==1">
            <textarea ng-model="l.address" ng-show="l.ocId ==1"
                      ng-init='l.address = "${(m.leaveCountry.address == null) ? "" : m.leaveCountry.address}"'
                      data-val-required="La direcci&oacute;n es un campo requerido"
                      class="form-control" value="{{l.address}}"
                      data-val-length="Debe tener al menos 6 y m&aacute;ximo 500 caracteres"
                      name="leaveCountry.address" id="leaveCountry.address"
                      data-val-length-max="500" data-val-length-min="6"></textarea>

                        <span class="field-validation-valid" data-valmsg-for="leaveCountry.address"
                              data-valmsg-replace="true"></span>
                    </div>
                </div>
            </div>
            <br/>
        </div>

        <div>
            <div class="row">
                <div class="col-xs-12">

                    <div class="col-xs-3 element-left">
                        <span ng-class="lstSourceInfoLeavingCountry['leaveCountry.familyAnotherCountry.id']==true?'verified':'';">
                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifEqual('leaveCountry.familyAnotherCountry.id')"></i>
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="5"
                           ng-show="verification" code="leaveCountry.familyAnotherCountry.id"></i>
                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifNotKnow('leaveCountry.familyAnotherCountry.id')"></i>
                            </span>
                        <span ng-class="lstFinalInfoLeavingCountry['leaveCountry.familyAnotherCountry.id']==true?'verified':'';">
                        <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                           ng-click="showChoices('leaveCountry.familyAnotherCountry.id')"></i>
                        </span>
                        &iquest;El detenido cuenta con familiares y/o amigos cercanos en otro pa&iacute;s?:
                    </div>
                    <div class="col-xs-3">
                        <input type="hidden" ng-update-hidden ng-model="l.facId"
                               name="leaveCountry.familyAnotherCountry.id"
                               id="fac"
                               ng-init='l.facId = ${(m.leaveCountry.familyAnotherCountry.id == null)? 'undefined':m.leaveCountry.familyAnotherCountry.id}'>
                        <select class="form-control element-center" ng-model="l.fac"
                                ng-options="e.name for e in listElection" value="{{l.fac.id}}"
                                ng-change="l.facId = l.fac.id;" ng-init='listElection = ${listElection};'></select>
                    </div>
                </div>
            </div>
            <br/>

            <div class="row" ng-show="l.facId == 1">
                <div class="col-xs-12">
                    <div class="col-xs-3 element-left">
                        &iquest;Mantiene comunicaci&oacute;n con ellos?:
                    </div>
                    <div class="col-xs-3" ng-show="l.facId == 1">
                        <input type="hidden" ng-update-hidden ng-model="l.cfId" name="leaveCountry.communicationFamily.id"
                               id="cf" ng-show="l.facId == 1"
                               ng-init='l.cfId = ${(m.leaveCountry.communicationFamily.id == null)? 'undefined':m.leaveCountry.communicationFamily.id}'>
                        <select class="form-control element-center" ng-model="l.cf"
                                ng-options="e.name for e in listElection" value="{{l.cf.id}}"
                                ng-change="l.cfId = l.cf.id;" ng-init='listElection = ${listElection};'></select>
                    </div>
                    <div class="col-xs-2 element-right">
                        &iquest;Por qu&eacute; medio?:
                    </div>
                    <div class="col-xs-4" ng-show="l.facId == 1">
                        <input class="form-control" ng-show="l.facId == 1"
                               data-val-length="Debe tener al menos 2 y m&aacute;ximo 50 caracteres"
                               data-val-length-max="50" data-val-length-min="2"
                               data-val-required="El medio es un campo requerido"
                               type="text" value="{{l.media}}" id="leaveCountry.media" name="leaveCountry.media"
                               ng-model="l.media"
                               ng-init='l.media = "${(m.leaveCountry.media == null) ? "":m.leaveCountry.media}";'>
                     <span class="field-validation-valid" data-valmsg-for="leaveCountry.media"
                           data-valmsg-replace="true"></span>
                    </div>
                </div>
            </div>
            <br/>

            <div class="row" ng-show="l.facId ==1">
                <div class="col-xs-12">
                    <div class="col-xs-2 element-left">
                        Relaci&oacute;n:
                    </div>
                    <div class="col-xs-4" ng-show="l.facId == 1">
                        <input type="hidden" ng-update-hidden ng-model="l.relId" name="leaveCountry.relationship.id"
                               id="rel"
                               ng-show="l.facId == 1"
                               ng-init='l.relId = ${(m.leaveCountry.relationship.id == null)? 'undefined':m.leaveCountry.relationship.id}'>
                        <select class="form-control element-center" ng-model="l.rel"
                                ng-options="e.name for e in listRel" value="{{l.rel.id}}"
                                ng-change="l.relId = l.rel.id;" ng-init='listRel = ${listRel};'></select>
                    </div>
                    <div class="col-xs-2 element-left" ng-show="l.rel.specification">
                        Especifique relaci&oacute;n:
                    </div>
                    <div class="col-xs-4" ng-show="l.facId == 1 && l.rel.specification">
                        <input class="form-control" ng-show="l.facId == 1  && l.rel.specification"
                               data-val-length="Debe tener al menos 1 y m&aacute;ximo 255 caracteres"
                               data-val-length-max="255" data-val-length-min="1"
                               data-val-required="La especifici&oacute;n es un campo requerido"
                               type="text" value="{{l.specificationRelationship}}"
                               id="leaveCountry.specificationRelationship"
                               name="leaveCountry.specificationRelationship" ng-model="l.specificationRelationship"
                               ng-init='l.specificationRelationship = "${(m.leaveCountry.specificationRelationship == null) ? "":m.leaveCountry.specificationRelationship}";'>
                     <span class="field-validation-valid" data-valmsg-for="leaveCountry.specificationRelationship"
                           data-valmsg-replace="true"></span>
                    </div>
                </div>
            </div>
        </div>
        <br/>

        <div class="row" ng-show="!verification && !selectSource">
            <div class="col-xs-12">
                <div class="col-xs-3 element-left">Observaciones:<br/>
                    <label class="info-example">(Este campo no es verificable)</label></div>
                <div class="col-xs-9">
        <textarea class="width-100" ng-model="commentCountry"
                  ng-init='commentCountry = "${m.commentCountry == null ? '' : m.commentCountry}";'

                  data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                  data-val-length-max="500"
                  data-val-length-min="1"
                  name="leaveCountry.commentCountry"></textarea>
                <span class="field-validation-valid" data-valmsg-for="leaveCountry.commentCountry"
                      data-valmsg-replace="true"></span>
                </div>
            </div>
        </div>
        <br/>

        <div class="row" ng-show="!verification && !selectSource">
            <div class="col-xs-12">
                <div class="col-xs-3 element-left">
                    Comentarios: <br/>
                    <label class="info-example">(cooperaci&oacute;n, atenci&oacute;n, ansioso, etc.)</label>
                </div>
                <div class="col-xs-9">
        <textarea class="form-control" name="leaveCountry.commentSocialEnvironment" ng-show="!verification"
                  data-val-required="Los comentarios es un campo requerido">${m.socialEnvironment.comment}</textarea>
         <span class="field-validation-valid" data-valmsg-for="leaveCountry.commentSocialEnvironment"
               data-valmsg-replace="true"></span>
                </div>
            </div>
        </div>

        <div class="row" ng-show="verification || selectSource">
            <div class="col-xs-12">
                <div class="col-xs-3 element-left">
                    <span ng-class="lstSourceInfoLeavingCountry['socialEnvironment.comment']==true?'verified':'';">
                    <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                       ng-click="doConfirmVerifEqual('socialEnvironment.comment')"></i>
                    <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4"
                       ng-show="verification"
                       code="socialEnvironment.comment"></i>
                    <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                       ng-click="doConfirmVerifNotKnow('socialEnvironment.comment')"></i>
                        </span>
                    <span ng-class="lstFinalInfoLeavingCountry['socialEnvironment.comment']==true?'verified':'';">
                    <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                       ng-click="showChoices('socialEnvironment.comment')"></i>
                        </span>
                    Comentarios: <br/>
                    <label class="info-example">(cooperaci&oacute;n, atenci&oacute;n, ansioso, etc.)</label>
                </div>
                <div class="col-xs-9">
        <textarea class="form-control" name="socialEnvironment.comment"
                  data-val-required="Los comentarios es un campo requerido">${m.socialEnvironment.comment}</textarea>
        <span class="field-validation-valid" data-valmsg-for="socialEnvironment.comment"
              data-valmsg-replace="true"></span>
                </div>
            </div>
        </div>

    </div>
</div>