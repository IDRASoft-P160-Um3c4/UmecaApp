<div ng-controller="schoolController">
    <div class="row">
        <div class="col-xs-12 element-center" ng-init="block=${m.school.block == null ? true: m.school.block}">
            <div class="col-xs-6 element-right">
                &iquest;El imputado estudia actualmente?
            </div>
            <div class="col-xs-2">
                <input type="radio" name="school.block"
                       id="blockYes" ng-value="true" ng-model="block" ng-change="fillModel()">
                <label for="blockYes">Si</label> &nbsp;&nbsp;&nbsp;
                <input type="radio" ng-checked="block==false" name="school.block"
                       id="blockNo" ng-value="false" ng-model="block" ng-change="fillModel()">
                <label for="blockNo">No</label>
            </div>
        </div>
    </div>
    <br/>

    <div class="row">
        <div class="col-xs-2 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('school.name')"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2"
               ng-show="verification" code="school.name"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('school.name')"></i>
            Escuela:
        </div>
        <div class="col-xs-10"><input class="form-control" data-val="true" ng-readonly="!block"
                                      data-val-length="Debe tener al menos 5 y m?ximo 200 caracteres"
                                      data-val-length-max="200" data-val-length-min="5"
                                      data-val-required="La escuela es un campo requerido" type="text" value=""
                                      ng-init='school.name="${(m.school.name == null) ? '' : m.school.name}"'
                                      ng-model="school.name" id="school.name" name="school.name"></div>
        <div class="row">
            <div class="col-xs-9 col-xs-offset-2 element-left"><span class="field-validation-valid"
                                                                     data-valmsg-for="school.name"
                                                                     data-valmsg-replace="true"></span></div>
        </div>
    </div>
    <br/>

    <div class="row">
        <div class="col-xs-2 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('school.phone')"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
               ng-show="verification" code="school.phone"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('school.phone')"></i>
            Tel&eacute;fono:
        </div>
        <div class="col-xs-4"><input class="form-control" data-val="true"
                                     data-val-length="Debe tener al menos 5 y m&aacute;ximo 30 caracteres"
                                     data-val-length-max="30" data-val-length-min="5" ng-readonly="!block"
                                     data-val-required="El tel&eacute;fono es un campo requerido" type="text"
                                     value=""
                                     ng-model="school.phone" id="school.phone" name="school.phone"
                                     ng-init='school.phone = "${(m.school.phone == null) ? '' : m.school.phone}"'>
                <span class="field-validation-valid" data-valmsg-for="school.phone"
                      data-valmsg-replace="true"></span>
        </div>


    </div>
    <br/>

    <div class="row">
        <div class="col-xs-2 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('school.address')"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
               ng-show="verification" code="school.address"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('school.address')"></i>
            Direcci&oacute;n:
        </div>
        <div class="col-xs-10"><textarea id="school.address" class="form-control" name="school.address"
                                         ng-model="school.address" ng-readonly="!block"
                                         ng-init='school.address = "${(m.school.address == null) ? "" : m.school.address}"'
                                         data-val="true"
                                         data-val-required="La direcci&oacute;n es un campo requerido"
                                         data-val-length="Debe tener al menos 6 y m&aacute;ximo 500 caracteres"
                                         data-val-length-max="500"
                                         data-val-length-min="6">${m.school.address}</textarea>

            <div class="col-xs-9 col-xs-offset-2 element-left"><span class="field-validation-valid"
                                                                     data-valmsg-for="school.address"
                                                                     data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>
    <br/>

    <div class="row">
        <div class="widget-box">
            <div class="widget-header">
                <div class="col-xs-10 element-left">
                    <h4>Actual/&Uacute;ltimo nivel de estudios</h4>
                </div>
            </div>

            <div class="widget-body">
                <br/>
                <div class="row">
                <div class="col-xs-2  element-left col-xs-offset-1">
                    Nivel:
                </div>
                <div class="col-xs-4">
                    <select class="form-control element-center" ng-model="school.level"
                            ng-options="e.name for e in lstLevel" ng-init='lstLevel = ${lstLevel};'
                            ng-change="school.levelId = school.level.id; lstDegree = school.level.degrees; school.degree=lstDegree[0]"></select>
                </div>
                <div class="col-xs-1">
                    Grado:
                </div>
                <div class="col-xs-3">

                    <select class="form-control element-center" ng-model="school.degree"
                            ng-options="e.name for e in lstDegree"
                            ng-change="school.degreeId = school.degree.id"
                            ng-init='lstDegree = school.level.degrees;'></select>
                    <input type="hidden" ng-update-hidden ng-model=" school.degree.id" name="school.degree.id"
                           id="degreeId"
                           ng-init="degreeId = ${(degreeId == null) ? 'undefined' : degreeId};">
                </div>

                    <br/>
                </div>
                <br/>
                <div class="row" ng-show="school.levelId == 7">
                    <br/>

                    <div class="col-xs-2  element-left col-xs-offset-1">
                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifEqual('school.degree')"></i>
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                           ng-show="verification" code="school.degree"></i>
                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                           ng-click="doConfirmVerifNotKnow('school.degree')"></i>
                        Especif&iacute;que:
                    </div>
                    <div class="col-xs-8">
                        <input class="width-100" data-val="true"
                               data-val-length="Debe tener al menos 1 y m&aacute;ximo 300 caracteres"
                               data-val-length-max="300" data-val-length-min="1"
                               data-val-required="La especificaci&oacute;n  es un campo requerido" type="text" value=""
                               ng-model="school.specification" id="school.specification" name="school.specification"
                               ng-init='school.specification = "${(m.school.specification == null) ? '' : m.school.specification}"'>
                <span class="field-validation-valid" data-valmsg-for="school.specification"
                      data-valmsg-replace="true"></span>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>


    <div class="row" id="divSchedule" ng-show="block">
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

    </div>
    <br/><br/>

    <div class="row">
        <div class="col-xs-3 element-left">Observaciones:<br/>
            <label class="info-example">(Este campo no es verificable)</label></div>
        <div class="col-xs-9">
            <textarea class="width-100" ng-model="commentSchool"
                      ng-init='commentSchool = "${m.commentSchool == null ? '' : m.commentSchool}";'
                      data-val="true"
                      data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                      data-val-length-max="500"
                      data-val-length-min="1"
                      name="school.commentSchool"></textarea>
                <span class="field-validation-valid" data-valmsg-for="school.commentSchool"
                      data-valmsg-replace="true"></span>
        </div>
        <br/>
    </div>
</div>