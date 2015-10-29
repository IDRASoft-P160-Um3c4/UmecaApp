<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormAddressId");
    });

    $('#start').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false,
        timeFormat: 'hh:mm',
        ampm: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('#end').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false,
        timeFormat: 'hh:mm',
        ampm: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

<div>
<div id="dlgUpModalId" class="modal fade" ng-controller="addressFMController" ng-cloak>

<div class="modal-dialog" style="width:800px" ng-init='fa=${addObj}'>
<div class="modal-content">
<div class="modal-header">
    <div class="alert alert-info ">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Domicilio</h4>
    </div>
</div>
<div class="modal-body">
<form id="FormAddressId" class="form-horizontal" role="form">
    <div class="row">
        <div class="col-xs-12 element-center">
            &iquest;El imputado se encuentra en situaci&oacute;n de calle?
            <br/>
            <div class="radio">
                <label>
                    <input name="isHomeless" class="ace" type="radio"
                           ng-value="true"
                           ng-model="a.isHomeless"
                           ng-checked="a.isHomeless==true">
                    <span class="lbl">&nbsp;&nbsp;S&iacute;</span>
                </label>
                &nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;
                <label>
                    <input name="isHomeless" class="ace" type="radio"
                           ng-value="false"
                           ng-model="a.isHomeless"
                           ng-checked="a.isHomeless==false"
                           data-val="true"
                           data-val-required="Debe seleccionar un valor">
                    <span class="lbl">&nbsp;&nbsp;No</span>
                </label>
            </div>

        </div>

    </div>
    <br/>
<%@ include file="/WEB-INF/jsp/address/index.jsp" %>
<br/>

<div class="row" ng-show="a.isHomeless!=true">
    <div class="col-xs-8">
        <label>Tel&eacute;fono(s)</label>
        <br/>
        <textarea class="input-xxlarge form-control limited" name="phone"
                  ng-model="fa.phone"
                  maxlength="980" data-val="true"
                  data-val-required="Tel&eacute;fono(s) es un campo requerido">
        </textarea>
        <span class="field-validation-valid" data-valmsg-for="phone"
              data-valmsg-replace="true"></span>
        <br/>
    </div>
</div>

<input type="hidden" name="registerTypeId" value="{{fa.registerType.id}}">
<input type="hidden" name="homeTypeId" value="{{fa.homeType.id}}">
<input type="hidden" name="schedule" value="{{fa.schedule}}">

<div class="row">
    <div class="col-xs-6" ng-show="a.isHomeless!=true">
        <label>Tipo de propiedad</label>
        <br/>
        <select class="form-control element-center"
                ng-model="fa.homeType"
                ng-options="e.name for e in lstHomeType"
                ng-init='lstHomeType= ${lstHomeType};'></select>
    </div>

    <div class="col-xs-6" ng-show="a.isHomeless!=true">
        <label>Tipo de domicilio</label>
        <br/>
        <select class="form-control element-center"
                ng-model="fa.registerType"
                ng-options="e.name for e in lstRegisterType"
                ng-init='lstRegisterType = ${lstRegisterType};'
                ng-change="clrFields();"></select>
    </div>
</div>
<br/>

<div class="row" ng-show="fa.homeType.specification==true">
    <div class="col-xs-8">
        <label>Especifique</label>
        <br/>
        <textarea class="input-xxlarge form-control limited" name="specification"
                  ng-model="fa.specification"
                  maxlength="980" data-val="true"
                  data-val-required="Especifique es un campo requerido">
        </textarea>
        <span class="field-validation-valid" data-valmsg-for="specification"
              data-valmsg-replace="true"></span>
        <br/>
    </div>
</div>
<br/>

<div class="row">
    <div class="widget-box">
        <div class="widget-header" ng-show="a.isHomeless!=true">Domicilio {{fa.registerType.name}}</div>
        <div class="widget-header" ng-show="a.isHomeless==true">Situaci&oacute;n de calle</div>
        <div class="widget-body">

            <div class="row" ng-show="fa.registerType.id==3">
                <div class="col-xs-12">
                    <div class="col-xs-5">
                        <br/>
                        <label for="timeLive">Tiempo de residencia</label>
                        <br/>
                        <input id="timeLive" ng-model="fa.timeLive"
                               name="timeLive" type="text"
                               class="input-xxlarge" data-val="true"
                               data-val-required="Tiempo de vivir es un campo requerido"/>
                        <br/>
                        <span class="field-validation-valid" data-valmsg-for="timeLive"
                              data-valmsg-replace="true"></span>
                        <br/>
                    </div>

                    <div class="col-xs-8">
                        <label>Motivo de la mudanza</label>
                        <br/>
                        <textarea class="input-xxlarge form-control limited" name="reasonChange"
                                  ng-model="fa.reasonChange"
                                  maxlength="980" data-val="true"
                                  data-val-required="Motivo de la mudanza es un campo requerido">
                        </textarea>
        <span class="field-validation-valid" data-valmsg-for="reasonChange"
              data-valmsg-replace="true"></span>
                        <br/>
                    </div>
                </div>
            </div>

            <div class="row" ng-show="fa.registerType.id!=3">
                <div class="col-xs-12">
                    <div class="col-xs-8">
                        <br/>
                        <label for="timeAgo" ng-show="a.isHomeless!=true">Tiempo de vivir en el domicilio</label>
                        <label for="timeAgo" ng-show="a.isHomeless==true">Tiempo de situaci&oacute;n de calle</label>

                        <br/>
                        <input id="timeAgo" ng-model="fa.timeAgo"
                               name="timeAgo" type="text"
                               class="input-xxlarge" data-val="true"
                               data-val-required="Tiempo de vivir es un campo requerido"/>
                        <br/>
                        <span class="field-validation-valid" data-valmsg-for="timeAgo"
                              data-valmsg-replace="true"></span>
                        <br/>
                    </div>

                    <div class="col-xs-8">
                        <label  ng-show="a.isHomeless!=true">Descripci&oacute;n de como llegar al domicilio<label
                                class="info-example" ng-show="a.isHomeless!=true">(color de casa, ruta, etc.)</label></label>
                        <label for="timeAgo" ng-show="a.isHomeless==true">Lugar de localizaci&oacute;n</label>
                        <br/>
                        <textarea class="input-xxlarge form-control limited" name="addressRef"
                                  ng-model="fa.addressRef"
                                  maxlength="980" data-val="true"
                                  data-val-required="Referencias del domicilio es un campo requerido">
                        </textarea>
        <span class="field-validation-valid" data-valmsg-for="addressRef"
              data-valmsg-replace="true"></span>
                        <br/>
                    </div>

                    <div class="col-xs-8" ng-show="fa.registerType.id==2">
                        <label>Raz&oacute;n por la que tiene un domicilio secundario</label>
                        <br/>
                        <textarea class="input-xxlarge form-control limited"
                                  name="reasonAnother"
                                  ng-model="fa.reasonAnother"
                                  maxlength="980" data-val="true"
                                  data-val-required="Referencias del domicilio es un campo requerido">
                        </textarea>
        <span class="field-validation-valid" data-valmsg-for="reasonAnother"
              data-valmsg-replace="true"></span>
                        <br/>
                    </div>
                </div>
            </div>

            <div class="row" ng-show="fa.registerType.id!=3 && a.isHomeless!=true">
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
                            <input id="start" name="start" ng-model="start"
                                   readonly type="text" class="form-control umeca-time-picker">
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                            <br/>
                        </div>
                    </div>
                    <div class="col-xs-3">
                        <label>Fin</label>
                        <br/>

                        <div class="input-group bootstrap-timepicker">
                            <input id="end" name="end" ng-model="end"
                                   readonly type="text" class="form-control umeca-time-picker">
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
                            <tr ng-repeat="act in fa.schedule track by $index">
                                <td class="element-center">{{act.day}}</td>
                                <td class="element-center">{{act.start}}</td>
                                <td class="element-center">{{act.end}}</td>
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
    </div>
</div>

<br/>

<div class="row">


</div>
</form>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div ng-show="MsgError" class="alert alert-danger element-center">
            {{MsgError}}
        </div>
    </div>
</div>
</div>
<div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitIdCaseParam('#FormAddressId', '<c:url value="/supervisor/framingMeeting/address/doAddressUpsert.json?idCase="/>',fa.idCase);">
                          Guardar
                    </span>
</div>
</div>
</div>
</div>
</div>

