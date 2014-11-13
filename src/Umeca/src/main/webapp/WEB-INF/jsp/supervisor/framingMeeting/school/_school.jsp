<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="framingSchoolController">

<input type="hidden" id="getDegree"
       value="<c:url value='/supervisor/framingMeeting/school/getDegree.json?id='/>"/>

<input type="hidden" id="getAcademicLvl"
       value="<c:url value='/supervisor/framingMeeting/school/getAcademicLvl.json?'/>"/>

<input type="hidden" id="urlSchool"
       value="<c:url value='/supervisor/framingMeeting/school/loadSchool.json?idCase='/>"/>

<input type="hidden" id="schoolIdCase" value="{{fm.objView.idCase}}"/>

<div class="col-xs-10 col-xs-offset-1">

<div class="row element-center">
    <h2><i class="blue icon-eye-open bigger-100"></i> Historia Escolar
    </h2>
</div>

<div class="row">
    <div ng-show="MsgErrorSchool&&MsgErrorSchool!=''" class="alert alert-danger element-center"
         ng-bind-html="MsgErrorSchool">
    </div>

    <div ng-show="MsgSuccessSchool&&MsgSuccessSchool!=''"
         class="alert alert-success element-center" ng-bind-html="MsgSuccessSchool">
    </div>
</div>

<div class="row">

<form id="FormSchoolId" class="form-horizontal" role="form">

<input type="hidden" name="degreeId" value="{{degree.id}}">
<input type="hidden" name="idCase" value="{{fm.objView.idCase}}"/>
<input type="hidden" name="id" value="{{school.id}}"/>

<div id="divHiddenSchool">
    <input type="hidden" name="name" value="{{school.name}}">
    <input type="hidden" name="phone" value="{{school.phone}}">
    <input type="hidden" name="address" value="{{school.address}}">
</div>

<div class="row">
    <br/>

    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header">Escuela</div>
            <div class="widget-body">
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <br/>
                        <label>&iquest;El imputado estudia actualmente?</label>
                        <br/>

                        <div class="radio">
                            <label>
                                <input class="ace" type="radio" ng-value="true"
                                       name="hasActualSchool"
                                       ng-model="hasActualSchool"
                                       ng-checked="hasActualSchool==true" ng-click="disableView(true);">
                                <span class="lbl">&nbsp;&nbsp;Si</span>
                            </label>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <label>
                                <input class="ace" type="radio" ng-value="false"
                                       name="hasActualSchool"
                                       ng-model="hasActualSchool"
                                       ng-checked="hasActualSchool==false" ng-click="disableView(false);">
                                <span class="lbl">&nbsp;&nbsp;No</span>
                            </label>
                        </div>
                        <br/>

                        <div id="idSchool">
                            <div class="col-xs-12">
                                <label>Escuela</label>
                                <br/>
                                <input id="name" ng-model="school.name" name="name"
                                       type="text"
                                       class="input-xxlarge" data-val="true"
                                       data-val-required="Escuela es un campo requerido"/>
                                <br/>
                                        <span class="field-validation-valid" data-valmsg-for="name"
                                              data-valmsg-replace="true"></span>
                            </div>
                            <br/>

                            <div class="col-xs-8">
                                <br/>
                                <label>Tel&eacute;fono</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited"
                                          name="phone"
                                          ng-model="school.phone"
                                          maxlength="980" data-val="true"
                                          data-val-required="Tel&eacute;fono es un campo requerido">
                                </textarea>
        <span class="field-validation-valid" data-valmsg-for="phone"
              data-valmsg-replace="true"></span>
                            </div>

                            <div class="col-xs-8">
                                <br/>
                                <label>Direcci&oacute;n</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited"
                                          name="address"
                                          ng-model="school.address"
                                          maxlength="980" data-val="true"
                                          data-val-required="Direcci&oacute;n es un campo requerido">
                                </textarea>
        <span class="field-validation-valid" data-valmsg-for="address"
              data-valmsg-replace="true"></span>
                                <br/>
                            </div>
                        </div>
                        <div>
                            <input type="hidden" name="degreeId" value="{{degree.id}}">
                            <label class="col-xs-6">Nivel acad&eacute;mico
                                <br/>
                                <select class="form-control element-center"
                                        ng-model="academicLvl"
                                        ng-options="e.name for e in lstAcademicLvl"
                                        ng-change="getDegrees(academicLvl.id);"></select>
                            </label>


                            <label class="col-xs-6">Grado
                                <br/>
                                <select class="form-control element-center"
                                        ng-model="degree"
                                        ng-options="e.name for e in lstDegree"></select>
                            </label>
                            <br/>

                            <div class="col-xs-8" ng-show="academicLvl.id==7">
                                <br/>
                                <label>Especifique nivel acad&eacute;mico</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited"
                                          name="specification"
                                          ng-model="school.specification"
                                          maxlength="980" data-val="true"
                                          data-val-required="Especifique nivel acad&eacute;mico es un campo requerido">
                                </textarea>
        <span class="field-validation-valid" data-valmsg-for="specification"
              data-valmsg-replace="true"></span>
                                <br/>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row" ng-show="hasActualSchool==true">
    <br/>

    <input type="hidden" name="schedule" value="{{school.schedule}}">

    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header">Disponibilidad</div>
            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="col-xs-12">
                            <div class="col-xs-12">
                                <div class="col-xs-12">
                                    <br/>

                                    <div ng-show="MsgErrorSchedule!=''"
                                         class="alert alert-danger element-center">
                                        <span>{{MsgErrorSchedule}}</span>
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <label>D&iacute;a(s)</label>
                                    <br/>
                                    <input class="form-control" type="text"
                                           ng-model="day">
                                </div>
                                <div class="col-xs-3">
                                    <label>Inicio</label>
                                    <br/>

                                    <div class="input-group bootstrap-timepicker">
                                        <input id="timeStart" ng-model="timeStart"
                                               readonly type="text" class="form-control">
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                        <br/>
                                    </div>
                                </div>
                                <div class="col-xs-3">
                                    <label>Fin</label>
                                    <br/>

                                    <div class="input-group bootstrap-timepicker">
                                        <input id="timeEnd" ng-model="timeEnd"
                                               readonly type="text" class="form-control">
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                        <br/>
                                    </div>
                                </div>
                                <div class="col-xs-2">
                                    <br/>
                                    <button type="button" class="btn btn-info" ng-click="addSchedule();">
                                        <i class="icon-plus bigger-110"></i>
                                        Agregar
                                    </button>
                                </div>
                            </div>

                            <div class="col-xs-10 col-xs-offset-1">
                                <br/>
                                <br/>

                                <table class="table table-striped table-bordered table-hover">
                                    <thead class="thin-border-bottom">
                                    <tr>
                                        <th class="element-center">D&iacute;a(s)</th>
                                        <th class="element-center">Inicio</th>
                                        <th class="element-center">Fin</th>
                                        <th class="element-center">Quitar</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <div>
                                        <tr ng-repeat="actSch in school.schedule track by $index">
                                            <td class="element-center">{{actSch.day}}</td>
                                            <td class="element-center">{{actSch.start}}</td>
                                            <td class="element-center">{{actSch.end}}</td>
                                            <td class="element-center"><a href="javascript:;"
                                                                          style="display:inline-block;"
                                                                          title="Quitar de la lista"
                                                                          ng-click="removeSchedule($index)"><span
                                                    class="glyphicon glyphicon-minus blue"></span></a></td>
                                        </tr>
                                    </div>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </div>

</div>
<br/>

<div class="col-xs-8">
    <br/>
    <label>Observaciones</label>
    <br/>
    <textarea class="input-xxlarge form-control limited"
              name="commentSchool"
              ng-model="school.comments"
              maxlength="980" data-val="true"
              data-val-required="Observaciones es un campo requerido">
    </textarea>
        <span class="field-validation-valid" data-valmsg-for="commentSchool"
              data-valmsg-replace="true"></span>
</div>

</form>
</div>
<br/>

</div>

<div class="col-xs-12">
    <div class="modal-footer" ng-show="fm.objView.canTerminate==true">
        <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
              ng-click="submitSchool('#FormSchoolId', '<c:url value="/supervisor/framingMeeting/school/doUpsert.json"/>');">
            <span class="glyphicon glyphicon-cloud-upload"></span>
              Guardar
        </span>
    </div>
</div>

</div>


<script>
    $('#timeStart').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    $('#timeEnd').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

</script>
