<div ng-controller="schoolController">
    <div class="row">
        <div class="col-xs-12">
            <div class="col-xs-2 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('school.name')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification" code="school.name"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('school.name')"></i>
                Escuela:
            </div>
            <div class="col-xs-10"><input class="form-control" data-val="true"
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
    </div>
    <br/>

    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('school.phone')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification" code="school.phone"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('school.phone')"></i>
                Teléfono:
            </div>
            <div class="col-xs-8"><input class="form-control" data-val="true"
                                         data-val-length="Debe tener al menos 8 y máximo 25 caracteres"
                                         data-val-length-max="25" data-val-length-min="8"
                                         data-val-required="El teléfono es un campo requerido" type="text" value=""
                                         ng-model="school.phone" id="school.phone" name="school.phone"
                                         ng-init='school.phone = "${(m.school.phone == null) ? '' : m.school.phone}"'>
            </div>
            <div class="col-xs-9 col-xs-offset-3"><span class="field-validation-valid" data-valmsg-for="school.phone"
                                                        data-valmsg-replace="true"></span></div>
        </div>
    </div>
    <br/>

    <div class="row">
        <div class="col-xs-12">
            <div class="col-xs-2 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('school.address')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification"  code="school.address"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('school.address')"></i>
                Dirección:
            </div>
            <div class="col-xs-10"><textarea id="school.address" class="form-control" name="school.address"
                                             ng-model="school.address"
                                             ng-init='school.address = "${(m.school.address == null) ? "" : m.school.address}"'
                                             data-val="true" data-val-required="La dirección es un campo requerido"
                                             data-val-length="Debe tener al menos 6 y máximo 500 caracteres"
                                             data-val-length-max="500"
                                             data-val-length-min="6">${m.school.address}</textarea></div>
            <div class="row">
                <div class="col-xs-9 col-xs-offset-2 element-left"><span class="field-validation-valid"
                                                                         data-valmsg-for="school.address"
                                                                         data-valmsg-replace="true"></span></div>
            </div>
        </div>
    </div>
    <br/>

    <div class="row">
        <div class="col-xs-6">
            <div class="col-xs-4  element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('school.degree')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                   ng-show="verification" code ="school.degree"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('school.degree')"></i>
                Nivel:
            </div>
            <div class="col-xs-8">
                <select class="form-control element-center" ng-model="school.level"
                        ng-options="e.name for e in lstLevel" ng-init='lstLevel = ${lstLevel};'
                        ng-change="school.levelId = school.level.id; lstDegree = school.level.degrees; school.degree=lstDegree[0]"></select>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="col-xs-4">
                Grado:
            </div>
            <div class="col-xs-7">

                <select class="form-control element-center" ng-model="school.degree"
                        ng-options="e.name for e in lstDegree"
                        ng-change="school.degreeId = school.degree.id"
                        ng-init='lstDegree = school.level.degrees;'></select>
                <input type="hidden" ng-update-hidden ng-model="school.degreeId" name="school.degree.id" id="degreeId"
                       ng-init="degreeId = ${(degreeId == null) ? 'undefined' : degreeId};">
            </div>
        </div>
    </div>
    <br/>

    <div class="row schedule_visible">
        <div class="widget-box">
            <div class="widget-header">
                <div class="col-xs-1">
                    <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4"
                       ng-show="verification"></i>
                    <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('school.schedule')"></i>
                </div>
                <h5><i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h5>
            </div>
            <div class="widget-body">
                <br/><br/>
                <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/schedule.jsp" %>
            </div>
        </div>
    </div>
</div>