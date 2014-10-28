<div ng-controller="schoolController">
<div class="row">
    <div class="col-xs-12">
        <label class="text-primary">Observaciones: <b>${m.commentSchool}</b></label>
    </div>
</div>
<br/>

<div class="row" ng-show="readOnly == false">
    <div class="col-xs-12">

        &nbsp;&nbsp;&nbsp;&nbsp;
        <b> Establecer toda la informaci&oacute;n de Historia Escolar
            con:</b>
    </div>
    <div class="col-xs-10 col-xs-offset-1 text-info" style="padding-top: 8px;">
        <i class="purple glyphicon glyphicon-user bigger-160"
           ng-click="showChoicesSection(6,undefined,1,'Historia escolar')" style="cursor: pointer;"></i>
        &nbsp;&nbsp;&nbsp;Informaci&oacute;n que proporcion&oacute; el imputado.
    </div>
    <div class="col-xs-10 col-xs-offset-1 text-info" style="padding-top: 8px;">
        <i class="blue icon-question-sign  icon-only bigger-160" style="cursor: pointer;"
           ng-click="showChoicesSection(6,undefined,-1,'Historia escolar')"></i>
        &nbsp;&nbsp;&nbsp;No se pudo verificar
    </div>
</div>
<div class="row" ng-show="readOnly == false">
    <div col-xs-12>
        <div class="hr hr-8"></div>
    </div>
</div>
<div class="row" ng-init='schLists = ${(listScheduleSchool == null) ? '[]': listScheduleSchool};'>
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('school.name')"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
               ng-show="verification" code="school.name"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('school.name')"></i>
            <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
               ng-click="showChoices('school.name')"></i>
            Escuela:
        </div>
        <div class="col-xs-10">
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 5 y m&aacute;ximo 200 caracteres"
                   data-val-length-max="200" data-val-length-min="5"
                   data-val-required="La escuela es un campo requerido" type="text" value=""
                   ng-init='school.name="${(m.school.name == null) ? '' : m.school.name}"'
                   ng-model="school.name" id="school.name" name="school.name">
            <span class="field-validation-valid"
                  data-valmsg-for="school.name"
                  data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('school.phone')"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
               ng-show="verification" code="school.phone"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('school.phone')"></i>
            <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
               ng-click="showChoices('school.phone')"></i>
            Tel&eacute;fono:
        </div>
        <div class="col-xs-6"><input class="form-control" data-val="true"
                                     data-val-length="Debe tener al menos 5 y m&aacute;ximo 30 caracteres"
                                     data-val-length-max="30" data-val-length-min="5"
                                     data-val-required="El tel&eacute;fono es un campo requerido" type="text" value=""
                                     ng-model="school.phone" id="school.phone" name="school.phone"
                                     ng-init='school.phone = "${(m.school.phone == null) ? '' : m.school.phone}"'>
            <span class="field-validation-valid" data-valmsg-for="school.phone"
                  data-valmsg-replace="true"></span></div>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('school.address')"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
               ng-show="verification" code="school.address"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('school.address')"></i>
            <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
               ng-click="showChoices('school.address')"></i>
            Direcci&oacute;n:
        </div>
        <div class="col-xs-10"><textarea id="school.address" class="form-control" name="school.address"
                                         ng-model="school.address"
                                         ng-init='school.address = "${(m.school.address == null) ? "" : m.school.address}"'
                                         data-val="true" data-val-required="La direcci&oacute;n es un campo requerido"
                                         data-val-length="Debe tener al menos 6 y m�ximo 500 caracteres"
                                         data-val-length-max="500"
                                         data-val-length-min="6">${m.school.address}</textarea>
            <span class="field-validation-valid"
                  data-valmsg-for="school.address"
                  data-valmsg-replace="true"></span></div>
    </div>
</div>
<br/>

<div>
    <div class="row">
        <div class="widget-box">
            <div class="widget-header">
                <div class="col-xs-10 element-left">
                    <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                       ng-click="doConfirmVerifEqual('school.degree.id')"></i>
                    <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4"
                       ng-show="verification" code="school.degree.id"></i>
                    <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                       ng-click="doConfirmVerifNotKnow('school.degree.id')"></i>
                    <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                       ng-click="showChoices('school.degree.id')"></i>
                    <h4>Actual/&Uacute;ltimo nivel de estudios</h4>
                </div>
            </div>

            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-6">
                        <div class="col-xs-4  element-left">
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
                                    ng-options="e.name for e in lstDegree" value="{{school.degree.id}}"
                                    ng-change="school.degreeId = school.degree.id"
                                    ng-init='lstDegree = school.level.degrees;'></select>
                            <input type="hidden" ng-update-hidden ng-model="school.degreeId" name="school.degree.id"
                                   id="degreeId"
                                   ng-init="degreeId = ${(degreeId == null) ? 'undefined' : degreeId};">
                        </div>
                    </div>
                </div>
                <div class="row" ng-show="school.levelId == 7">
                    <br/>

                    <div class="col-xs-12">
                        <div class="col-xs-2  element-left">
                            Especif&iacute;que:
                        </div>
                        <div class="col-xs-8">
                            <input class="form-control" data-val="true"
                                   data-val-length="Debe tener al menos 1 y m&aacute;ximo 300 caracteres"
                                   data-val-length-max="300" data-val-length-min="1"
                                   data-val-required="La especificaci&oacute;n  es un campo requerido" type="text"
                                   value=""
                                   ng-model="school.specification" id="school.specification" name="school.specification"
                                   ng-init='school.specification = "${(m.school.specification == null) ? '' : m.school.specification}"'>
                            <span class="field-validation-valid" data-valmsg-for="school.specification"
                                  data-valmsg-replace="true"></span>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>
<br/>
</div>

<div class="row" ng-show="schLists.length > 0 ">
    <div class="col-xs-12"
         ng-show="verification || selectSource || showSchedule">
        <div class="widget-header header-color-blue">
            <div class="row">
                <h6> &nbsp;
                    <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                       ng-click="doConfirmVerifEqual('school.schedule')"></i>
                    <i class="icon-remove-circle red  icon-only bigger-120" verif-schedule level-child="5"
                       ng-show="verification" id-code="school.schedule"></i>
                    <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                       ng-click="doConfirmVerifNotKnow('school.schedule')"></i>
                    <i class="purple icon-list icon-only bigger-120" ng-show="selectSource"
                       ng-click="showChoices('school.schedule')"></i>
                    &nbsp; <i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h6>
            </div>
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
                    <tr ng-repeat="sch in schLists track by $index">
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
</div>