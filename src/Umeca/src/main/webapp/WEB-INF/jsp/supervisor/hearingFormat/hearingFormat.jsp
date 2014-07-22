<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>


    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/hearingFormat/hearingFormatCtrl.js"></script>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
    </script>
    <script type="text/javascript">

        jQuery(function ($) {
            $('#id-disable-check').on('click', function () {
                var inp = $('#form-input-readonly').get(0);
                if (inp.hasAttribute('disabled')) {
                    inp.setAttribute('readonly', 'true');
                    inp.removeAttribute('disabled');
                    inp.value = "This text field is readonly!";
                }
                else {
                    inp.setAttribute('disabled', 'disabled');
                    inp.removeAttribute('readonly');
                    inp.value = "This text field is disabled!";
                }
            });

            $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });
            $('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function () {
                $(this).next().focus();
            });

            $('#modal-form input[type=file]').ace_file_input({
                style: 'well',
                btn_choose: 'Drop files here or click to choose',
                btn_change: null,
                no_icon: 'icon-cloud-upload',
                droppable: true,
                thumbnail: 'large'
            })

            $('#modal-form').on('shown.bs.modal', function () {
                $(this).find('.chosen-container').each(function () {
                    $(this).find('a:first-child').css('width', '210px');
                    $(this).find('.chosen-drop').css('width', '210px');
                    $(this).find('.chosen-search input').css('width', '200px');
                });
            })

            $('#initTime').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            $('#endTime').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            $('#linkageTime').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });


        });
    </script>

    <title>Formato de audiencia</title>
</head>

<body scroll="no" ng-app="ptlUmc" ng-cloak>

<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<br/>

<h2 class="element-center"><i class="icon-archive"></i>&nbsp;&nbsp;Formato de audiencia</h2>

<form id="FormFormatId" name="FormFormatId" class="form-horizontal"
      role="form" ng-controller="upsertController" method="post">
<br/>

<div class="container body-content" ng-controller="hearingFormatController" ng-init='m=${hfView}'>
<%@ include file="/WEB-INF/jsp/supervisor/hearingFormat/confirmAction.jsp" %>

<input type="hidden" id="url3" value="<c:url value='/supervisor/hearingFormat/searchArrangementsByType.json'/>"/>

<input type="hidden" id="idCase" name="idCase" value="{{m.idCase}}"/>
<input type="hidden" name="lstArrangement" value="{{m.lstArrangementShow}}"/>
<input type="hidden" name="lstContactData" value="{{m.lstContactData}}"/>

<div class="row">

    <div class="col-xs-6">
        <h3 class="header smaller lighter blue">
            <small>Captur�:</small>
            &nbsp;&nbsp;{{m.userName}}
        </h3>
    </div>

    <div class="col-xs-6 element-right">
        <div ng-show="m.canSave==false">
        <span class="btn btn-default btn-sm"
              ng-click="returnUrl('<c:url value='/supervisor/hearingFormat/indexFormats.html'/>'+'?id='+m.idCase)">
                                Regresar
                            </span>
        </div>
        <div ng-show="m.canSave==true">
                            <span class="btn btn-default btn-sm"
                                  ng-click="returnUrl('<c:url value='/supervisor/hearingFormat/indexFormats.html'/>'+'?id='+m.idCase)">
                                Cancelar
                            </span>

                            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                  ng-click="saveHF('#FormFormatId','<c:url value='/supervisor/hearingFormat/doUpsert.json'/>',validateSave);">
                                                  Guardar
                                            </span>
        </div>
    </div>
</div>

<div class="row">
<div class="widget-box">
<div class="widget-header">Datos generales</div>
<div class="widget-body">
<div class="row">
<div class="col-xs-10 col-xs-offset-1">
<br/>

<div class="widget-box">
    <div class="widget-header"> Audiencia</div>
    <div class="widget-body">
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1">

                <br/>

                <div class="row">

                    <div class="col-xs-4">
                        <label for="idFolder">Carpeta de investigaci�n</label>
                        <br/>
                        <input id="idFolder" ng-model="m.idFolder" name="idFolder"
                               type="text" class="input-xxlarge" data-val="true"
                               ng-disabled="m.canEdit==false"
                               data-val-required="Carpeta de investigaci�n es un campo requerido"/>
                        <br/>
                        <span class="field-validation-valid" data-valmsg-for="idFolder"
                              data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">
                        <label for="idJudicial">Carpeta judicial</label>
                        <br/>
                        <input id="idJudicial" ng-model="m.idJudicial"
                               name="idJudicial" type="text"
                               class="input-xxlarge" data-val="true"
                               ng-disabled="m.canEdit==false"
                               data-val-required="Carpeta judicial es un campo requerido"/>
                        <br/>
                        <span class="field-validation-valid" data-valmsg-for="idJudicial"
                              data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">
                        <label for="room">Sala</label>
                        <br/>
                        <input id="room" ng-model="m.room" name="room" type="text"
                               class="input-xxlarge" data-val="true"
                               data-val-required="Sala es un campo requerido"/>
                        <br/>
                                        <span class="field-validation-valid" data-valmsg-for="room"
                                              data-valmsg-replace="true"></span>
                    </div>

                </div>

                <br/>

                <div class="row">
                    <div class="col-xs-4">

                        <label for="appointmentDate">Cita</label>

                        <div class="row">
                            <div class="col-xs-8 col-sm-11">
                                <div class="input-group">
                                    <input id="appointmentDate" name="appointmentDate" ng-model="m.appointmentDate"
                                           class="form-control date-picker" id="id-date-picker-1" type="text"
                                           data-date-format="dd/mm/yyyy" data-val="true" readonly
                                           data-val-required="Cita es un campo requerido"/>
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                </div>
                                <span class="field-validation-valid" data-valmsg-for="appointmentDate"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>

                    </div>

                    <div class="col-xs-4">
                        <label for="initTime">Hora inicio audiencia</label>

                        <div class="input-group bootstrap-timepicker">
                            <input id="initTime" name="initTime" ng-model="m.initTime"
                                   ng-change="validateInitEnd();" readonly
                                   type="text" class="form-control" data-val="true"
                                   data-val-required="Hora de inicio es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                            <br/>
                        </div>
                        <span class="field-validation-valid" data-valmsg-for="initTime"
                              data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">
                        <label for="endTime">Hora t�rmino audiencia</label>

                        <div class="input-group bootstrap-timepicker">
                            <input id="endTime" name="endTime"
                                   ng-change="validateInitEnd();" ng-model="m.endTime"
                                   readonly type="text" class="form-control" min-time="6:00am"
                                   data-val="true" data-val-required="Hora de t�rmino es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                            <br/>
                        </div>
                        <span class="field-validation-valid" data-valmsg-for="endTime"
                              data-valmsg-replace="true">{{m.errTime}}</span>
                        <span ng-class='m.errTime&&m.errTime!="" ? "field-validation-error": "field-validation-valid"'>{{m.errTime}}</span>
                    </div>
                </div>
                <br/>

                <div class="row">

                    <div class="col-xs-4">
                        <label for="judgeName">Nombre del Juez</label>
                        <br/>
                        <input id="judgeName" ng-model="m.judgeName" name="judgeName"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Nombre del juez es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="judgeName"
                  data-valmsg-replace="true"></span>
                    </div>


                    <div class="col-xs-4">

                        <label for="mpName">Nombre del Ministerio P�blico</label>
                        <br/>
                        <input id="mpName" ng-model="m.mpName" name="mpName" type="text"
                               class="input-xxlarge"
                               data-val="true"
                               data-val-required="Nombre del Ministerio P�blico es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="mpName"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">

                        <label for="defenderName">Nombre del defensor</label>
                        <br/>
                        <input id="defenderName" ng-model="m.defenderName" name="defenderName"
                               type="text"
                               class="input-xxlarge"
                               data-val="true"
                               data-val-required="Nombre del defensor es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="defenderName"
                  data-valmsg-replace="true"></span>
                    </div>

                </div>
                <br/>
            </div>
        </div>

    </div>


</div>

<br/>

<div class="widget-box">
    <div class="widget-header">Imputado</div>
    <div class="widget-body">
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1">
                <br/>

                <div class="row">
                    <div class="col-xs-4">

                        <label for="imputedName">Nombre(s)</label>
                        <br/>
                        <input id="imputedName" ng-model="m.imputedName" name="imputedName" type="text"
                               class="input-xxlarge"
                               data-val="true" ng-disabled="m.canEdit==false"
                               data-val-required="Nombre es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedName"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">

                        <label for="imputedFLastName">Apellido paterno</label>
                        <br/>
                        <input id="imputedFLastName" ng-model="m.imputedFLastName" name="imputedFLastName" type="text"
                               class="input-xxlarge" data-val="true" ng-disabled="m.canEdit==false"
                               data-val-required="Apellido paterno es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedFLastName"
                  data-valmsg-replace="true"></span>

                    </div>

                    <div class="col-xs-4">

                        <label for="imputedSLastName">Apellido materno</label>
                        <br/>
                        <input id="imputedSLastName" ng-model="m.imputedSLastName" name="imputedSLastName" type="text"
                               class="input-xxlarge" data-val="true" ng-disabled="m.canEdit==false"
                               data-val-required="Apellido materno es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedSLastName"
                  data-valmsg-replace="true"></span>

                    </div>
                </div>


                <div class="row">
                    <br/>

                    <div class="col-xs-4">

                        <label for="imputedBirthDate">Fecha de nacimiento</label>

                        <div class="row">
                            <div class="col-xs-8 col-sm-11">
                                <div class="input-group">
                                    <input class="form-control date-picker"
                                           id="imputedBirthDate" name="imputedBirthDate" type="text"
                                           data-date-format="dd/mm/yyyy" ng-disabled="m.canEdit==false"
                                           readonly ng-change="calcAge();" ng-model="m.impBthDay" data-val="true"
                                           data-val-required="Fecha de nacimiento es un campo requerido"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                                </div>
                                <span class="field-validation-valid" data-valmsg-for="imputedBirthDate"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>

                    </div>

                    <div class="col-xs-4">

                        <label for="imputedAge">Edad</label>
                        <br/>
                        <input id="imputedAge" name="imputedAge" type="text" class="input-xxlarge" readonly
                               ng-model="m.impAge"/>
                        <br/>
                        <span ng-class='m.errAge&&m.errAge!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errAge}}</span>

                    </div>

                    <div class="col-xs-4">

                        <label for="imputedTel">Telefono(s)</label>
                        <br/>
                        <input id="imputedTel" ng-model="m.imputedTel" name="imputedTel" type="text"
                               class="input-xxlarge"
                               data-val="true" ng-disabled="m.canEdit==false"
                               data-val-required="Telefono(s) es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedTel"
                  data-valmsg-replace="true"></span>

                    </div>

                </div>

                <br/>

                <div class="row">

                    <%@ include file="/WEB-INF/jsp/address/index.jsp" %>

                </div>

                <br/>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <br/>

    <div class="col-xs-6">
        <label>Delito(s)</label>
        <br/>
        <textarea class="input-xxlarge form-control limited" name="crimes"
                  ng-model="m.crimes"
                  maxlength="980" data-val="true"
                  data-val-required="Delito(s) adicionales es un campo requerido">
            </textarea>
        <span class="field-validation-valid" data-valmsg-for="crimes"
              data-valmsg-replace="true"></span>
    </div>
    <div class="col-xs-6">
        <label>Datos adicionales</label>
        <br/>

        <textarea class="input-xxlarge form-control limited" name="additionalData"
                  ng-model="m.additionalData"
                  maxlength="980" data-val="true"
                  data-val-required="Datos adicionales es un campo requerido">
            </textarea>
        <span class="field-validation-valid" data-valmsg-for="additionalData"
              data-valmsg-replace="true"></span>
    </div>
</div>

<br/>

<div class="row">
    <div class="col-xs-6">
        <div class="widget-box">
            <div class="widget-header">Control de detenci�n</div>
            <div class="widget-body">
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-3">
                        <br/>
                        <span class="field-validation-valid" data-valmsg-for="controlDetention"
                              data-valmsg-replace="true"></span>

                        <div class="radio">
                            <label ng-click="clCrtlDet();">
                                <input name="controlDetention" class="ace" type="radio" value="1"
                                       ng-model="m.ctrlDet"
                                       ng-checked="m.ctrlDet==1" data-val="true"
                                       data-val-required="Debe seleccionar un valor">
                                <span class="lbl">&nbsp;&nbsp;Legal</span>
                            </label>
                            <br/>
                            <label ng-click="clCrtlDet();">
                                <input name="controlDetention" class="ace" type="radio" value="2"
                                       ng-model="m.ctrlDet"
                                       ng-checked="m.ctrlDet==2">
                                <span class="lbl">&nbsp;&nbsp;Ilegal</span>
                            </label>

                        </div>

                        <br/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="divFormImp" class="col-xs-6">
        <div class="widget-box">
            <div class="widget-header">Formulaci�n de imputaci�n</div>
            <div class="widget-body">
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-3">
                        <br/>

                        <div class="col-xs-12">
                         <span class="field-validation-valid" data-valmsg-for="impForm"
                               data-valmsg-replace="true"></span>
                        </div>

                        <div class="radio col-xs-7">
                            <label>
                                <input class="ace" name="impForm" type="radio" ng-model="m.formImp" value="1"
                                       ng-checked="m.formImp==1" data-val="true"
                                       data-val-required="Debe seleccionar un valor">
                                <span class="lbl">&nbsp;&nbsp;Si</span>
                            </label>
                            <br/>
                            <label>
                                <input class="ace" name="impForm" type="radio" ng-model="m.formImp" value="2"
                                       ng-checked="m.formImp==2">
                                <span class="lbl">&nbsp;&nbsp;No</span>
                            </label>
                        </div>
                        <br/>

                        <div class="col-xs-7">
                            <label for="imputationDate">Fecha</label>

                            <div class="input-group">
                                <input class="form-control date-picker"
                                       id="imputationDate" name="imputationDate" ng-model="m.impDate"
                                       data-date-format="dd/mm/yyyy" type="text" readonly data-val="true"
                                       data-val-required="Fecha es un campo requerido">
                            <span class="input-group-addon">
                                <i class="icon-calendar bigger-110"></i>
                            </span>
                            </div>
                            <span class="field-validation-valid" data-valmsg-for="imputationDate"
                                  data-valmsg-replace="true"></span>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>

</div>
<br/>


<div class="row">

    <div class="col-xs-6">
        <div class="widget-box">
            <div class="widget-header">Ampliaci�n del t�rmino</div>
            <div class="widget-body">
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-3">
                        <br/>
                        <span class="field-validation-valid" data-valmsg-for="extension"
                              data-valmsg-replace="true"></span>

                        <div class="radio">
                            <label ng-click="clExtension();">
                                <input name="extension" class="ace" type="radio" value="1" ng-model="m.ext"
                                       ng-checked="m.ext==1" data-val="true"
                                       data-val-required="Debe seleccionar un valor">
                                <span class="lbl">&nbsp;&nbsp;72 hrs</span>
                            </label>
                            <br/>
                            <label ng-click="clExtension();">
                                <input name="extension" class="ace" type="radio" value="2" ng-model="m.ext"
                                       ng-checked="m.ext==2">
                                <span class="lbl">&nbsp;&nbsp;144 hrs</span>
                            </label>
                            <br/>
                            <label ng-click="clExtension();">
                                <input name="extension" class="ace" type="radio" value="3" ng-model="m.ext"
                                       ng-checked="m.ext==3">
                                <span class="lbl">&nbsp;&nbsp;No</span>
                            </label>
                        </div>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xs-6">
        <div class="widget-box">
            <div class="widget-header">Vinculaci�n a proceso</div>
            <div class="widget-body">
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-3">
                        <br/>
                         <span class="field-validation-valid" data-valmsg-for="vincProcess"
                               data-valmsg-replace="true"></span>

                        <div class="radio">
                            <label>
                                <input class="ace" name="vincProcess" type="radio" value="1"
                                       ng-model="m.vincProcess"
                                       ng-checked="m.vincProcess==1" data-val="true"
                                       data-val-required="Debe seleccionar un valor">
                                <span class="lbl">&nbsp;&nbsp;Si</span>
                            </label>
                            <br/>
                            <label>
                                <input class="ace" name="vincProcess" type="radio" value="2"
                                       ng-model="m.vincProcess"
                                       ng-checked="m.vincProcess==2">
                                <span class="lbl">&nbsp;&nbsp;No</span>
                            </label>
                        </div>
                        <br/>

                        <div class="col-xs-6">

                            <div class="row">
                                <br/>
                                <label>Sala</label>
                                <input type="text" name="linkageRoom"
                                       class="input-small" ng-model="m.linkageRoom" data-val="true"
                                       data-val-required="Sala es un campo requerido">
                                <br/>
                                <span class="field-validation-valid"
                                      data-valmsg-for="linkageRoom"
                                      data-valmsg-replace="true"></span>
                            </div>


                            <div class="row">
                                <br/>
                                <label for="linkageDate">Fecha</label>

                                <div class="input-group input-large">
                                    <input class="form-control date-picker"
                                           id="linkageDate" data-date-format="dd/mm/yyyy"
                                           name="linkageDate" ng-model="m.linkageDate"
                                           type="text" readonly data-val="true"
                                           data-val-required="Fecha es un campo requerido">
                <span class="input-group-addon">
                    <i class="icon-calendar bigger-110"></i>
                </span>
                                </div>
                         <span class="field-validation-valid" data-valmsg-for="linkageDate"
                               data-valmsg-replace="true"></span>
                            </div>

                            <div class="row">
                                <br/>
                                <label for="linkageTime">Hora</label>

                                <div class="input-group bootstrap-timepicker">
                                    <input id="linkageTime" name="linkageTime" readonly type="text"
                                           ng-model="m.linkageTime" data-val="true"
                                           data-val-required="Hora es un campo requerido">
                                            <span class="input-group-addon">
                                                <i class="icon-time bigger-110"></i>
                                            </span>
                                </div>
                                <span class="field-validation-valid" data-valmsg-for="linkageTime"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>
</div>


</div>
</div>

<br/>
</div>
</div>
</div>

<br/>

<div class="row" ng-show="m.vincProcess==1">
    <div class="widget-box">
        <div class="widget-header">Medidas cautelares</div>
        <div class="widget-body">
            <div class="row">
                <div class="col-xs-10 col-xs-offset-1">
                    <br/>

                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header">Audiencia</div>
                            <div class="widget-body">
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>

                                        <div class="element-left">
                                        <span class="field-validation-valid" data-valmsg-for="nationalArrangement"
                                              data-valmsg-replace="true"></span>
                                        </div>

                                        <div class="col-xs-3">
                                            <div class="radio">
                                                <label ng-click="loadArrangements()">
                                                    <input name="nationalArrangement" class="ace" type="radio"
                                                           value="true"
                                                           ng-model="m.nationalArrangement"
                                                           ng-checked="m.nationalArrangement==true" data-val="true"
                                                           data-val-required="Debe seleccionar un valor">
                                                    <span class="lbl">&nbsp;&nbsp;Nacional</span>
                                                </label>
                                                <br/>
                                                <label ng-click="loadArrangements()">
                                                    <input name="nationalArrangement" class="ace" type="radio"
                                                           value="false"
                                                           ng-model="m.nationalArrangement"
                                                           ng-checked="m.nationalArrangement==false" checked>
                                                    <span class="lbl">&nbsp;&nbsp;Local</span>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-3">
                                            <div class="radio">
                                                <label ng-click="loadArrangements()">
                                                    <input name="arrangementType" class="ace" type="radio" value="1"
                                                           ng-model="m.arrType" ng-checked="m.arrType==1"
                                                           data-val="true"
                                                           data-val-required="Debe seleccionar un valor">
                                                    <span class="lbl">&nbsp;&nbsp;SCPP</span>
                                                </label>
                                                <br/>
                                                <label ng-click="loadArrangements()">
                                                    <input name="arrangementType" class="ace" type="radio" value="2"
                                                           ng-model="m.arrType" ng-checked="m.arrType==2">
                                                    <span class="lbl">&nbsp;&nbsp;MC</span>
                                                </label>
                                            </div>
                                        </div>


                                        <div ng-show="m.arrType==1" class="col-xs-6">
                                            <label>Plazo</label>
                                            <br/>

                                            <textarea class="form-control limited" name="terms"
                                                      ng-model="m.terms"
                                                      ng-disabled='m.arrType==2'
                                                      maxlength="980"
                                                      data-val="true"
                                                      data-val-required="Plazo es un campo requerido">
                                                </textarea>
                                        <span class="field-validation-valid" data-valmsg-for="terms"
                                              data-valmsg-replace="true"></span>
                                        </div>

                                        <div ng-show="m.arrType==2" class="col-xs-6">
                                            <label ng-show="m.arrType==2">Plazo de investigaci�n</label>
                                            <br/>
                                            <textarea class="form-control limited" name="terms"
                                                      ng-model="m.terms"
                                                      ng-disabled='m.arrType==1'
                                                      maxlength="980"
                                                      data-val="true"
                                                      data-val-required="Plazo de investigaci�n es un campo requerido">
                                                </textarea>
                                        <span class="field-validation-valid" data-valmsg-for="terms"
                                              data-valmsg-replace="true"></span>
                                        </div>
                                    </div>
                                </div>
                                <br/>

                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <span ng-class='m.errArrmntSel&&m.errArrmntSel!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errArrmntSel}}</span>

                                        <div class="row" ng-repeat="arrangment in m.lstArrangementShow">
                                            <div class="checkbox">
                                                <label ng-click="validateArrangementSel();">
                                                    <input class="ace" ng-disabled="m.disableAll==true"
                                                           type="checkbox"
                                                           ng-model="m.lstArrangementShow[$index].selVal">
                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;{{arrangment.name}}</span>
                                                </label>
                                            </div>
                                            <div class="col-xs-offset-1">
                                                <textarea class="form-control limited" ng-disabled="m.disableAll==true"
                                                          ng-blur="validateArrangementSel()"
                                                          maxlength="980"
                                                          ng-model="m.lstArrangementShow[$index].description"
                                                          ng-show="m.lstArrangementShow[$index].selVal==true"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br/>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>

<br/>

<div class="row" ng-show="m.vincProcess==1">
    <div class="widget-box">
        <div class="widget-header">Datos de contacto</div>
        <div class="widget-body">
            <br/>

            <div class="row">
                <div class="col-xs-10 col-xs-offset-1">

                    <div class="col-xs-3">
                        <label for="contactName">Nombre</label>
                        <br/>
                        <input id="contactName" type="text"
                               class="input-xxlarge" ng-model="m.contactName"/>
                        <br/>
                    </div>

                    <div class="col-xs-3">
                        <label for="contactPhone">Tel�fono:</label>
                        <br/>
                        <input id="contactPhone" type="text"
                               class="input-xxlarge" ng-model="m.contactPhone"/>
                        <br/>
                    </div>


                    <div class="col-xs-4">
                        <label>Direcci�n</label>
                        <br/>
                        <textarea class="input-xxlarge form-control limited" id="contactAddress"
                                  maxlength="980" ng-model="m.contactAddress">
                        </textarea>
                    </div>

                    <div class="col-xs-2 element-right">
                        <br/>
                        <button type="button" class="btn btn-info" ng-click="addContact();">
                            <i class="icon-plus bigger-110"></i>
                            Agregar
                        </button>
                    </div>


                </div>
            </div>
            <br/>

            <div class="row">
                <div class="col-xs-10 col-xs-offset-1">
                    <div ng-show="MsgErrorContact!=''" class="alert alert-danger element-center">
                        <span>{{MsgErrorContact}}</span>
                    </div>
                </div>
            </div>

            <br/>

            <div class="row">
                <div class="col-xs-10 col-xs-offset-1">

                    <div class="widget-box">
                        <div class="widget-header">Lista de contactos</div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead class="thin-border-bottom">
                                    <tr>
                                        <th class="element-center">Nombre</th>
                                        <th class="element-center">Tel�fono</th>
                                        <th class="element-center">Direcci�n</th>
                                        <th class="element-center">Quitar</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <div>
                                        <tr ng-repeat="contact in m.lstContactData track by $index">
                                            <td class="element-center">{{contact.name}}</td>
                                            <td class="element-center">{{contact.phone}}</td>
                                            <td class="element-center">{{contact.address}}</td>
                                            <td class="element-center"><a href="javascript:;"
                                                                          style="display:inline-block;"
                                                                          title="Quitar de la lista"
                                                                          ng-click="removeContact($index)"><span
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

        </div>
    </div>

</div>

<div class="row element-right">
    <div ng-show="m.canSave==true">
                            <span class="btn btn-default btn-sm"
                                  ng-click="returnUrl('<c:url value='/supervisor/hearingFormat/indexFormats.html'/>'+'?id='+m.idCase)">
                                Cancelar
                            </span>

                            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                  ng-click="saveHF('#FormFormatId','<c:url value='/supervisor/hearingFormat/doUpsert.json'/>',validateSave);">
                                                  Guardar
                                            </span>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>
</div>
</form>

</body>
</html>