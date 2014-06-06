<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left"> Escuela:</div>
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
        <div class="col-xs-4 element-left"> Teléfono:</div>
        <div class="col-xs-8"><input class="form-control" data-val="true"
                                     data-val-length="Debe tener al menos 8 y máximo 25 caracteres"
                                     data-val-length-max="25" data-val-length-min="8"
                                     data-val-required="El teléfono es un campo requerido" type="text" value=""
                                     ng-model="school.phone" id="school.phone" name="school.phone"
                                     ng-init='school.phone = "${(m.school.phone == null) ? '' : m.school.phone}"'></div>
        <div class="col-xs-9 col-xs-offset-3"><span class="field-validation-valid" data-valmsg-for="school.phone"
                                                    data-valmsg-replace="true"></span></div>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">Dirección:</div>
        <div class="col-xs-10"><textarea id="school.address" class="form-control" name="school.address"
                                         ng-model="school.address" ng-init='school.address = "${(m.school.address == null) ? "" : m.school.address}"'
                                         data-val="true" data-val-required="La dirección es un campo requerido"
                                         data-val-length="Debe tener al menos 6 y máximo 500 caracteres"
                                         data-val-length-max="500" data-val-length-min="6">${m.school.address}</textarea></div>
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
            Nivel:
        </div>
        <div class="col-xs-8">
            <select class="form-control element-center" ng-model="school.level"
                    ng-options="e.name for e in lstLevel" ng-init='lstLevel = ${lstLevel};'
                    ng-change="school.levelId = school.level.id; lstGrade = school.level.grades; school.grade=lstGrade[0]"></select>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="col-xs-4">
            Grado:
        </div>
        <div class="col-xs-7">

            <select class="form-control element-center" ng-model="school.grade"
                    ng-options="e.name for e in lstGrade"
                    ng-change="school.gradeId = school.grade.id"
                    ng-init='lstGrade = school.level.grades;'></select>
            <input type="hidden" ng-update-hidden ng-model="gradeId" name="school.grade.id" id="gradeId"
                   ng-init="gradeId = ${(gradeId == null) ? 'undefined' : gradeId};">
        </div>
    </div>
</div>
<br/>

<div class="row schedule_visible">
    <div class="widget-box">
        <div class="widget-header">
            <h5><i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h5>
        </div>
        <div class="widget-body">
            <br/><br/>
            <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/schedule.jsp" %>
        </div>
    </div>
</div>