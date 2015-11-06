<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>
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

            $('#initTimeStr').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            $('#endTimeStr').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false,
                timeFormat: 'hh:mm:ss tt',
                ampm: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            $('#linkageTimeStr').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            $('#umecaTimeStr').timepicker({
                minuteStep: 1,
                showSeconds: true,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });
        });
    </script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>


    <title>Formato de audiencia</title>
</head>

<body scroll="no" ng-app="ptlUmc" ng-cloak>

<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<br/>

<h2 class="element-center"><i class="icon-archive"></i>&nbsp;&nbsp;Formato de audiencia</h2>

<form id="FormFormatId" name="FormFormatId" class="form-horizontal"
      role="form" ng-controller="upsertController" method="post">
    <br/>


    <div class="container body-content" ng-controller="hearingFormatController"
         ng-init='m=${hfView}; m.rdHasContacts = 1;' style="padding:50px;">

        <div class="blocker" ng-show="WaitFor==true">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
            </div>
        </div>

        <%@ include file="/WEB-INF/jsp/supervisor/hearingFormat/confirmAction.jsp" %>

        <input type="hidden" id="url3"
               value="<c:url value='/supervisor/hearingFormat/searchArrangementsByType.json'/>"/>
        <input type="hidden" id="urlRet"
               value="<c:url value='/supervisor/hearingFormat/indexFormats.html'/>?id={{m.idCase}}&returnId={{returnId}}"/>

        <input type="hidden" id="idCase" name="idCase" value="{{m.idCase}}"/>
        <input type="hidden" name="lstArrangement" value="{{m.lstArrangementShow}}"/>
        <input type="hidden" name="lstContactData" value="{{m.lstContactData}}"/>
        <input type="hidden" name="isFinished" value="{{m.isFinished}}"/>
        <input type="hidden" name="idFormat" value="{{m.idFormat}}"/>
        <input type="hidden" id="umecaSupervisorId" name="umecaSupervisorId" value="{{m.umecaSupervisor.id}}"/>

        <input type="hidden" name="isFinished" value="{{m.isFinished}}"/>

        <div class="row">

            <div class="col-xs-6">
                <h3 class="header smaller lighter blue">
                    <small>Nombre del supervisor:</small>
                    &nbsp;&nbsp;{{m.userName}}
                </h3>
            </div>

            <div class="col-xs-6 element-right">
                <div ng-show="m.canSave==false">
        <span class="btn btn-default btn-sm"
              ng-click="returnUrlId('<c:url value='/supervisor/hearingFormat/indexFormats.html'/>'+'?id='+m.idCase)">
                                Regresar
                            </span>
                </div>
                <div ng-show="m.canSave==true">
                            <span class="btn btn-default btn-sm"
                                  ng-click="returnUrl('<c:url value='/supervisor/hearingFormat/indexFormats.html'/>'+'?id='+m.idCase)">
                                Regresar
                            </span>

                            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                  ng-click="submitPartiaSaveHF('#FormFormatId','<c:url value='/supervisor/hearingFormat/doUpsert.json'/>');">
                                                  Guardar
                                            </span>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <div ng-show="MsgError&&MsgError!=''" ng-clean-error msg="MsgError"
                     class="umeca-toast-error element-center">
                    <p ng-bind-html="MsgError"></p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div ng-show="MsgSuccess&&MsgSuccess!=''" ng-clean-error msg="MsgSuccess"
                     class="umeca-toast-success element-center">
                    <p ng-bind-html="MsgSuccess"></p>
                </div>
            </div>
            <div class="col-xs-12">
                <div ng-show="MsgSuccess&&MsgSuccess!=''" class="alert alert-success element-center">
                    <p ng-bind-html="MsgSuccess"></p>
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

                            <div class="widget-box" id="divAudiencia">
                                <div class="widget-header"> Audiencia</div>
                                <div class="widget-body">
                                    <div class="row">
                                        <div class="col-xs-10 col-xs-offset-1">
                                            <br/>
                                            <c:if test="${hasPrevHF ==true}">
                                                <input type="hidden" id="hearingTypeId" name="hearingTypeId"
                                                       value="{{m.hearingType.id}}"/>

                                                <div class="row">
                                                    <div class="col-xs-4">
                                                        <label>Tipo de audiencia</label>
                                                        <br/>
                                                        <select class="form-control element-center"
                                                                ng-model="m.hearingType"
                                                                ng-options="e.description for e in lstHearingType"
                                                                ng-init='lstHearingType = ${lstHearingType};'
                                                                ng-change="lockArrangements();"></select>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <label>&iquest;Se present&oacute; el imputado a la
                                                            audiencia?</label>
                     <span class="field-validation-valid" data-valmsg-for="imputedPresence"
                           data-valmsg-replace="true"></span>

                                                        <div class="radio">
                                                            <label>
                                                                <input name="imputedPresence" class="ace" type="radio"
                                                                       value="1"
                                                                       ng-model="m.imputedPresence"
                                                                       ng-checked="m.imputedPresence==1" data-val="true"
                                                                       data-val-required="Debe seleccionar un valor">
                                                                <span class="lbl">&nbsp;&nbsp;Si</span>
                                                            </label>
                                                            <br/>
                                                            <label>
                                                                <input name="imputedPresence" class="ace" type="radio"
                                                                       value="2"
                                                                       ng-model="m.imputedPresence"
                                                                       ng-checked="m.imputedPresence==2">
                                                                <span class="lbl">&nbsp;&nbsp;No</span>
                                                            </label>

                                                        </div>

                                                        <div ng-show="m.imputedPresence==2">
                                                            <br/>
                                                            <label>&iquest;Declarar sustra&iacute;do al
                                                                imputado?</label>
                                                            <br/>
                                                         <span class="field-validation-valid"
                                                               data-valmsg-for="isSubstracted"
                                                               data-valmsg-replace="true"></span>

                                                            <div class="radio">
                                                                <label>
                                                                    <input name="isSubstracted" class="ace" type="radio"
                                                                           ng-value="true"
                                                                           ng-model="m.isSubstracted"
                                                                           ng-checked="m.isSubstracted"
                                                                           data-val="true"
                                                                           data-val-required="Debe seleccionar un valor">
                                                                    <span class="lbl">&nbsp;&nbsp;Si</span>
                                                                </label>
                                                                <br/>
                                                                <label>
                                                                    <input name="isSubstracted" class="ace" type="radio"
                                                                           ng-value="false"
                                                                           ng-model="m.isSubstracted"
                                                                           ng-checked="!m.isSubstracted">
                                                                    <span class="lbl">&nbsp;&nbsp;No</span>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <label>Resultado de la audiencia</label>
                                                        <br/>
            <textarea class="input-xxlarge form-control limited" name="hearingResult"
                      ng-model="m.hearingResult"
                      maxlength="980" data-val="true"
                      data-val-required="Resultado de la audiencia es un campo requerido">
            </textarea>
        <span class="field-validation-valid" data-valmsg-for="hearingResult"
              data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>

                                                <div class="row" ng-show="m.hearingType.specification==true">
                                                    <div class="col-xs-4">
                                                        <label>Especifique tipo de audiencia</label>
                                                        <br/>
            <textarea class="input-xxlarge form-control limited" name="hearingTypeSpecification"
                      ng-model="m.hearingTypeSpecification"
                      maxlength="980" data-val="true"
                      data-val-required="Especificaci&oacute;n de tipo de audiencia es un campo requerido">
            </textarea>
        <span class="field-validation-valid" data-valmsg-for="hearingTypeSpecification"
              data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                                <br/>
                                            </c:if>
                                            <div class="row">

                                                <div class="col-xs-4">
                                                    <label for="idFolder">Carpeta de investigaci&oacute;n</label>
                                                    <br/>
                                                    <input id="idFolder" ng-model="m.idFolder" name="idFolder"
                                                           type="text" class="input-xxlarge" data-val="true"
                                                           ng-disabled="m.canEdit==false"
                                                           data-val-length-max="30"
                                                           data-val-length="Debe tener m&aacute;ximo 30 caracteres"
                                                           data-val-required="Carpeta de investigaci&oacute;n es un campo requerido"/>
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
                                                           data-val-length-max="15"
                                                           data-val-length="Debe tener m&aacute;ximo 15 caracteres"
                                                           data-val-required="Carpeta judicial es un campo requerido"/>
                                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="idJudicial"
                              data-valmsg-replace="true"></span>
                                                </div>

                                                <div class="col-xs-4">
                                                    <label>Distrito</label>
                                                    <br/>
                                                    <select id="district" class="form-control element-center"
                                                            ng-model="m.district"
                                                            ng-options="e.name for e in lstDistrict"
                                                            ng-init='lstDistrict = ${lstDistrict};'
                                                            ng-disabled="m.hasPrevHF==true"></select>
                                                </div>
                                                <input id="hidDistrict" type="hidden" name="districtId"
                                                       value="{{m.district.id}}"/>
                                            </div>

                                            <br/>

                                            <div class="row">
                                                <div class="col-xs-4">

                                                    <label for="appointmentDateStr">Fecha de audiencia:</label>

                                                    <div class="row">
                                                        <div class="col-xs-8 col-sm-11">
                                                            <div class="input-group">
                                                                <input id="appointmentDateStr" name="appointmentDateStr"
                                                                       ng-model="m.appointmentDate"
                                                                       class="form-control date-picker"
                                                                       id="id-date-picker-1" type="text"
                                                                       data-date-format="yyyy/mm/dd" data-val="true"
                                                                       data-val-required="Fecha de audiencia es un campo requerido"/>
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                                            </div>
                                <span class="field-validation-valid" data-valmsg-for="appointmentDateStr"
                                      data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="col-xs-4">
                                                    <label for="initTimeStr">Hora inicio audiencia</label>

                                                    <div class="input-group bootstrap-timepicker">
                                                        <input id="initTimeStr" name="initTimeStr" ng-model="m.initTime"
                                                               ng-change="validateInitEnd();" readonly
                                                               type="text" class="form-control umeca-time-picker"
                                                               data-val="true"
                                                               data-val-required="Hora de inicio es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                                        <br/>
                                                    </div>
                        <span class="field-validation-valid" data-valmsg-for="initTimeStr"
                              data-valmsg-replace="true"></span>
                                                </div>

                                                <div class="col-xs-4" ng-show="false">
                                                    <label for="endTimeStr">Hora t&eacute;rmino audiencia</label>

                                                    <div class="input-group bootstrap-timepicker">
                                                        <input id="endTimeStr" name="endTimeStr"
                                                               ng-change="validateInitEnd();" ng-model="m.endTime"
                                                               readonly type="text"
                                                               class="form-control umeca-time-picker"
                                                               min-time="6:00am"
                                                               data-val="true"
                                                               data-val-required="Hora de t&eacute;rmino es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                                        <br/>
                                                    </div>
                        <span class="field-validation-valid" data-valmsg-for="endTimeStr"
                              data-valmsg-replace="true">{{m.errTime}}</span>
                                                    <span ng-class='m.errTime&&m.errTime!="" ? "field-validation-error": "field-validation-valid"'>{{m.errTime}}</span>
                                                </div>
                                            </div>
                                            <br/>

                                            <div class="row">

                                                <div class="col-xs-4">
                                                    <label for="judgeName">Juez de control</label>
                                                    <br/>
                                                    <input id="judgeName" ng-model="m.judgeName" name="judgeName"
                                                           type="text" class="input-xxlarge"
                                                           data-val="true"
                                                           data-val-required="Juez de control es un campo requerido"/>
                                                    <br/>
            <span class="field-validation-valid" data-valmsg-for="judgeName"
                  data-valmsg-replace="true"></span>
                                                </div>


                                                <div class="col-xs-4">

                                                    <label for="mpName">Ministerio P&uacute;blico</label>
                                                    <br/>
                                                    <input id="mpName" ng-model="m.mpName" name="mpName" type="text"
                                                           class="input-xxlarge"
                                                           data-val="true"
                                                           data-val-required="Ministerio P&uacute;blico es un campo requerido"/>
                                                    <br/>
            <span class="field-validation-valid" data-valmsg-for="mpName"
                  data-valmsg-replace="true"></span>
                                                </div>

                                                <div class="col-xs-4">

                                                    <label for="defenderName">Defensor</label>
                                                    <br/>
                                                    <input id="defenderName" ng-model="m.defenderName"
                                                           name="defenderName"
                                                           type="text"
                                                           class="input-xxlarge"
                                                           data-val="true"
                                                           data-val-required="Defensor es un campo requerido"/>
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

                            <div class="widget-box" id="divImputado">
                                <div class="widget-header">Datos del imputado</div>
                                <div class="widget-body">
                                    <div class="row">
                                        <div class="col-xs-10 col-xs-offset-1">
                                            <br/>

                                            <div class="row">
                                                <div class="col-xs-4">

                                                    <label for="imputedName">Nombre(s)</label>
                                                    <br/>
                                                    <input id="imputedName" ng-model="m.imputedName" name="imputedName"
                                                           type="text"
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
                                                    <input id="imputedFLastName" ng-model="m.imputedFLastName"
                                                           name="imputedFLastName" type="text"
                                                           class="input-xxlarge" data-val="true"
                                                           ng-disabled="m.canEdit==false"
                                                           data-val-required="Apellido paterno es un campo requerido"/>
                                                    <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedFLastName"
                  data-valmsg-replace="true"></span>

                                                </div>

                                                <div class="col-xs-4">

                                                    <label for="imputedSLastName">Apellido materno</label>
                                                    <br/>
                                                    <input id="imputedSLastName" ng-model="m.imputedSLastName"
                                                           name="imputedSLastName" type="text"
                                                           class="input-xxlarge" data-val="true"
                                                           ng-disabled="m.canEdit==false"
                                                           data-val-required="Apellido materno es un campo requerido"/>
                                                    <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedSLastName"
                  data-valmsg-replace="true"></span>

                                                </div>
                                            </div>


                                            <div class="row">
                                                <br/>

                                                <div class="col-xs-4">

                                                    <label for="imputedBirthDateStr">Fecha de nacimiento</label>

                                                    <div class="row">
                                                        <div class="col-xs-8 col-sm-11">
                                                            <div class="input-group">
                                                                <input class="form-control date-picker"
                                                                       id="imputedBirthDateStr"
                                                                       name="imputedBirthDateStr" type="text"
                                                                       data-date-format="yyyy/mm/dd"
                                                                       ng-disabled="m.canEdit==false"
                                                                       ng-change="calcAge();"
                                                                       ng-model="m.impBthDay" data-val="true"
                                                                       data-val-required="Fecha de nacimiento es un campo requerido"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                                                            </div>
                                <span class="field-validation-valid" data-valmsg-for="imputedBirthDateStr"
                                      data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="col-xs-4">

                                                    <label for="imputedAge">Edad</label>
                                                    <br/>
                                                    <input id="imputedAge" name="imputedAge" type="text"
                                                           class="input-xxlarge" readonly
                                                           ng-model="m.impAge"/>
                                                    <br/>
                                                    <span ng-class='m.errAge&&m.errAge!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errAge}}</span>

                                                </div>

                                                <div class="col-xs-4">

                                                    <label for="imputedTel">Tel&eacute;fono(s)</label>
                                                    <br/>
                                                    <input id="imputedTel" ng-model="m.imputedTel" name="imputedTel"
                                                           type="text"
                                                           class="input-xxlarge"
                                                           data-val="true" ng-disabled="m.canEdit==false"
                                                           data-val-required="Tel&eacute;fono(s) es un campo requerido"/>
                                                    <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedTel"
                  data-valmsg-replace="true"></span>

                                                </div>

                                            </div>

                                            <br/>

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
                                                                   data-val="true">
                                                            <span class="lbl">&nbsp;&nbsp;No</span>
                                                        </label>
                                                    </div>

                                                </div>

                                            </div>

                                            <br/>

                                            <div class="row">
                                                <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
                                            </div>

                                            <div class="row" ng-if="a.isHomeless==true">
                                                    <div class="col-xs-6">
                                                        <br/>
                                                        <label>Tiempo en sitauci&oacute;n de calle</label>
                                                        <br/>
                                                        <textarea class="input-xxlarge form-control limited"
                                                                  id="timeAgo"
                                                                  name="timeAgo"
                                                                  ng-model="m.timeAgo"
                                                                  maxlength="980" data-val="true"
                                                                  data-val-required="Tiempo en sitauci&oacute;n de calle es un campo requerido">
                                                        </textarea>
                                                        <span class="field-validation-valid" data-valmsg-for="timeAgo"
                                                              data-valmsg-replace="true"></span>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <br/>
                                                        <label>Lugar de localizaci&oacute;n</label>
                                                        <br/>
                                                        <textarea class="input-xxlarge form-control limited"
                                                                  id="locationPlace"
                                                                  name="locationPlace"
                                                                  ng-model="m.locationPlace"
                                                                  maxlength="980" data-val="true"
                                                                  data-val-required="Lugar de localizaci&oacute;n es un campo requerido">
                                                        </textarea>
                                                        <span class="field-validation-valid" data-valmsg-for="locationPlace"
                                                              data-valmsg-replace="true"></span>
                                                    </div>
                                            </div>

                                            <br/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <br/>

                                <div class="col-xs-12" id="divDelitos">
                                    <div class="widget-box">
                                        <div class="widget-header">Delitos</div>
                                        <div class="widget-body">
                                            <div class="row">
                                                <div class="col-xs-10 col-xs-offset-1"
                                                     ng-init="readonlyBand = ${readonlyBand == null ? false: readonlyBand};">
                                                    <br/>
                                                    <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/crime.jsp" %>
                                                    <br/>
                                                </div>
                                            </div>
                                            <br/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <br/>

                            <div class="row" id="divPreviousHearing" ng-show="m.hasPrevHF==false">
                                <div class="col-xs-12">
                                    <div class="col-xs-10">
                                        <label>&iquest;Existe el registro de una audiencia inicial?</label>
                                        <br/>
                        <span class="field-validation-valid" data-valmsg-for="previousHearing"
                              data-valmsg-replace="true"></span>

                                        <div class="radio">
                                            <label>
                                                <input name="previousHearing" class="ace" type="radio" value="1"
                                                       ng-model="m.previousHearing"
                                                       ng-checked="m.previousHearing==1" data-val="true"
                                                       data-val-required="Debe seleccionar un valor">
                                                <span class="lbl">&nbsp;&nbsp;Si</span>
                                            </label>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <label>
                                                <input name="previousHearing" class="ace" type="radio" value="2"
                                                       ng-model="m.previousHearing"
                                                       ng-checked="m.previousHearing==2" data-val="true"
                                                       data-val-required="Debe seleccionar un valor">
                                                <span class="lbl">&nbsp;&nbsp;No</span>
                                            </label>

                                        </div>

                                        <br/>
                                    </div>
                                </div>
                            </div>

                            <br/>

                            <div class="row">
                                <div class="col-xs-6" id="divCtrlDet">
                                    <div class="widget-box">
                                        <div class="widget-header">Control de detenci&oacute;n</div>
                                        <div class="widget-body">
                                            <div class="row">
                                                <div class="col-xs-10 col-xs-offset-3">
                                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="controlDetention"
                              data-valmsg-replace="true"></span>

                                                    <div class="radio">
                                                        <label>
                                                            <input name="controlDetention" class="ace" type="radio"
                                                                   value="1"
                                                                   ng-model="m.ctrlDet"
                                                                   ng-checked="m.ctrlDet==1" data-val="true"
                                                                   data-val-required="Debe seleccionar un valor">
                                                            <span class="lbl">&nbsp;&nbsp;Legal</span>
                                                        </label>
                                                        <br/>
                                                        <label>
                                                            <input name="controlDetention" class="ace" type="radio"
                                                                   value="2"
                                                                   ng-model="m.ctrlDet"
                                                                   ng-checked="m.ctrlDet==2">
                                                            <span class="lbl">&nbsp;&nbsp;Ilegal</span>
                                                        </label>
                                                        <br/>
                                                        <label ng-show="m.previousHearing==2">
                                                            <input name="controlDetention" class="ace" type="radio"
                                                                   value="3"
                                                                   ng-model="m.ctrlDet"
                                                                   ng-checked="m.ctrlDet==3">
                                                            <span class="lbl">&nbsp;&nbsp;Sin registro</span>
                                                        </label>
                                                    </div>

                                                    <br/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <c:if test="${hasPrevHF ==true}">
                                    <input type="hidden" name="impForm" value="{{m.formImp}}"/>
                                    <input type="hidden" name="imputationDateStr" value="{{m.impDate}}"/>
                                </c:if>

                                <div id="divFormImp" class="col-xs-6">
                                    <div class="widget-box">
                                        <div class="widget-header">Formulaci&oacute;n de imputaci&oacute;n</div>
                                        <div class="widget-body">
                                            <div class="row">
                                                <div class="col-xs-10 col-xs-offset-3">
                                                    <br/>

                                                    <div class="col-xs-12">
                         <span class="field-validation-valid" data-valmsg-for="impForm"
                               data-valmsg-replace="true"></span>
                                                    </div>

                                                    <div class="radio col-xs-7">
                                                        <label ng-click="chnLblFormImp(1)">
                                                            <input id="formImpYes" class="ace" name="impForm"
                                                                   type="radio" ng-model="m.formImp"
                                                                   value="1"
                                                                   ng-checked="m.formImp==1" data-val="true"
                                                                   data-val-required="Debe seleccionar un valor">
                                                            <span class="lbl">&nbsp;&nbsp;Si</span>
                                                        </label>
                                                        <br/>
                                                        <label ng-click="chnLblFormImp(2)">
                                                            <input id="formImpNo" class="ace" name="impForm"
                                                                   type="radio" ng-model="m.formImp"
                                                                   value="2"
                                                                   ng-checked="m.formImp==2">
                                                            <span class="lbl">&nbsp;&nbsp;No</span>
                                                        </label>
                                                        <br/>
                                                        <label ng-show="m.previousHearing==2">
                                                            <input name="impForm" class="ace" type="radio" value="3"
                                                                   ng-model="m.formImp"
                                                                   ng-checked="m.formImp==3">
                                                            <span class="lbl">&nbsp;&nbsp;Sin registro</span>
                                                        </label>
                                                    </div>
                                                    <br/>

                                                    <div class="col-xs-7" ng-show="m.formImp>0&&m.formImp<3">
                                                        <label><p ng-bind-html="m.labelImpForm"></p></label>

                                                        <div class="input-group">
                                                            <input class="form-control date-picker"
                                                                   id="imputationDateStr" name="imputationDateStr"
                                                                   ng-model="m.impDate"
                                                                   data-date-format="yyyy/mm/dd" type="text"
                                                                   data-val="true"
                                                                   data-val-required="Fecha es un campo requerido">
                            <span class="input-group-addon">
                                <i class="icon-calendar bigger-110"></i>
                            </span>
                                                        </div>
                            <span class="field-validation-valid" data-valmsg-for="imputationDateStr"
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

                                <div class="col-xs-6" id="divExt">
                                    <div class="widget-box" ng-show="m.formImp==1">
                                        <div class="widget-header">Ampliaci&oacute;n del t&eacute;rmino</div>
                                        <div class="widget-body">
                                            <div class="row">
                                                <div class="col-xs-10 col-xs-offset-3">
                                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="extension"
                              data-valmsg-replace="true"></span>

                                                    <div class="radio">
                                                        <label ng-click="chnExtDate(1);"
                                                               ng-show="(m.disableAll==true)||(m.hasPrevHF==false&&m.ext&&m.ext>0&&m.ext<3) || (m.canEdit==true&&m.hasPrevHF==false)">
                                                            <input name="extension" class="ace" type="radio" value="1"
                                                                   ng-model="m.ext"
                                                                   ng-checked="m.ext==1" data-val="true"
                                                                   data-val-required="Debe seleccionar un valor">
                                                            <span class="lbl">&nbsp;&nbsp;72 hrs</span>
                                                        </label>
                                                        <br/>
                                                        <label ng-click="chnExtDate(2);"
                                                               ng-show="(m.disableAll==true) || (m.hasPrevHF==false&&m.ext>0&&m.ext<3) || (m.canEdit==true&&m.hasPrevHF==false)">
                                                            <input name="extension" class="ace" type="radio" value="2"
                                                                   ng-model="m.ext"
                                                                   ng-checked="m.ext==2" data-val="true"
                                                                   data-val-required="Debe seleccionar un valor">
                                                            <span class="lbl">&nbsp;&nbsp;144 hrs</span>
                                                        </label>
                                                        <br/>
                                                        <label ng-click="chnExtDate(3);">
                                                            <input name="extension" class="ace" type="radio" value="3"
                                                                   ng-model="m.ext"
                                                                   ng-checked="m.ext==3" data-val="true"
                                                                   data-val-required="Debe seleccionar un valor">
                                                            <span class="lbl">&nbsp;&nbsp;No</span>
                                                        </label>
                                                    </div>
                                                    <br/>

                                                    <div class="col-xs-7" ng-show="m.ext>0">
                                                        <label for="extDateStr">Nueva fecha de audiencia</label>

                                                        <div class="input-group">
                                                            <input class="form-control date-picker"
                                                                   id="extDateStr" name="extDateStr"
                                                                   ng-model="m.extDate"
                                                                   data-date-format="yyyy/mm/dd" type="text"
                                                                   data-val="true"
                                                                   data-val-required="Fecha es un campo requerido">
                            <span class="input-group-addon">
                                <i class="icon-calendar bigger-110"></i>
                            </span>
                                                        </div>
                            <span class="field-validation-valid" data-valmsg-for="extDateStr"
                                  data-valmsg-replace="true"></span>
                                                    </div>
                                                    <br/>
                                                </div>
                                            </div>
                                            <br/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xs-6" id="divVincProc">
                                    <div class="widget-box">
                                        <div class="widget-header">Vinculaci&oacute;n a proceso</div>
                                        <div class="widget-body">
                                            <div class="row">
                                                <div class="col-xs-10 col-xs-offset-3">
                                                    <br/>
                         <span class="field-validation-valid" data-valmsg-for="vincProcess"
                               data-valmsg-replace="true"></span>

                                                    <div class="radio">
                                                        <label ng-click="chngVincProcess(1)">
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
                                                        <br/>
                                                        <label ng-show="m.previousHearing==2">
                                                            <input name="vincProcess" class="ace" type="radio" value="3"
                                                                   ng-model="m.vincProcess"
                                                                   ng-checked="m.vincProcess==3">
                                                            <span class="lbl">&nbsp;&nbsp;Sin registro</span>
                                                        </label>
                                                    </div>
                                                    <br/>

                                                    <div class="col-xs-6" ng-show="m.vincProcess==1">
                                                        <div class="row">
                                                            <br/>
                                                            <label>Sala</label>
                                                            <input type="text" name="linkageRoom"
                                                                   class="input-small" ng-model="m.linkageRoom"
                                                                   data-val="true"
                                                                   data-val-required="Sala es un campo requerido">
                                                            <br/>
                                <span class="field-validation-valid"
                                      data-valmsg-for="linkageRoom"
                                      data-valmsg-replace="true"></span>
                                                        </div>


                                                        <div class="row">
                                                            <br/>
                                                            <label for="linkageDateStr">Fecha</label>

                                                            <div class="input-group input-large">
                                                                <input class="form-control date-picker"
                                                                       id="linkageDateStr" data-date-format="yyyy/mm/dd"
                                                                       name="linkageDateStr" ng-model="m.linkageDate"
                                                                       type="text" data-val="true"
                                                                       data-val-required="Fecha es un campo requerido">
                <span class="input-group-addon">
                    <i class="icon-calendar bigger-110"></i>
                </span>
                                                            </div>
                         <span class="field-validation-valid" data-valmsg-for="linkageDateStr"
                               data-valmsg-replace="true"></span>
                                                        </div>

                                                        <div class="row">
                                                            <br/>
                                                            <label for="linkageTimeStr">Hora</label>

                                                            <%--<div class="input-group bootstrap-timepicker">--%>
                                                            <%--<input id="linkageTimeStr" name="linkageTimeStr" readonly type="text"--%>
                                                            <%--ng-model="m.linkageTime" data-val="true"--%>
                                                            <%--data-val-required="Hora es un campo requerido">--%>
                                                            <%--<span class="input-group-addon">--%>
                                                            <%--<i class="icon-time bigger-110"></i>--%>
                                                            <%--</span>--%>
                                                            <%--</div>--%>
                                                            <%--<span class="field-validation-valid" data-valmsg-for="linkageTimeStr"--%>
                                                            <%--data-valmsg-replace="true"></span>--%>
                                                            <div class="input-group bootstrap-timepicker">
                                                                <input id="linkageTimeStr" name="linkageTimeStr"
                                                                       ng-model="m.linkageTime"
                                                                       readonly type="text"
                                                                       class="form-control umeca-time-picker"
                                                                       data-val="true"
                                                                       data-val-required="Hora es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                                                <br/>
                                                            </div>
                        <span class="field-validation-valid" data-valmsg-for="initTimeStr"
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

        <div class="row" ng-show="m.vincProcess==1||m.vincProcess==3" id="divCitaUmeca">
            <div class="widget-box">
                <div class="widget-header">Cita UMECA para entrevista de encuadre</div>
                <div class="widget-body">
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <br/>

                            <div row>
                                <div class="col-xs-3">
                                    <label for="appointmentDateStr">Cita en la UMECA:</label>
                                    <br/>

                                    <div class="input-group">
                                        <input id="umecaDateStr" name="umecaDateStr"
                                               ng-model="m.umecaDate"
                                               class="form-control date-picker" id="id-date-picker-umeca" type="text"
                                               data-date-format="yyyy/mm/dd" data-val="true"
                                               data-val-required="Cita en la UMECA es un campo requerido"/>
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                    </div>
                                <span class="field-validation-valid" data-valmsg-for="umecaDateStr"
                                      data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-3">
                                    <label for="umecaTimeStr">Hora de la cita UMECA</label>

                                    <div class="input-group bootstrap-timepicker">
                                        <input id="umecaTimeStr" name="umecaTimeStr" ng-model="m.umecaTime"
                                               readonly type="text" class="form-control umeca-time-picker"
                                               data-val="true"
                                               data-val-required="Hora de inicio es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                        <br/>
                                    </div>
                        <span class="field-validation-valid" data-valmsg-for="umecaTimeStr"
                              data-valmsg-replace="true"></span>
                                </div>
                                <%--<div class="col-xs-5">--%>
                                <%--<label>Elige el nuevo supervisor para el caso</label>--%>
                                <%--<br/>--%>
                                <%--<select class="form-control element-center"--%>
                                <%--ng-model="m.umecaSupervisor"--%>
                                <%--ng-options="e.description for e in lstSupervisor"--%>
                                <%--ng-init='lstSupervisor = ${lstSupervisor};'></select>--%>
                                <%--</div>--%>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>

        <br/>

        <div id="divMedidasHidden">
            <input type="hidden" name="nationalArrangement" value="{{m.nationalArrangement}}"/>
            <input type="hidden" name="arrangementType" value="{{m.arrType}}"/>
        </div>

        <div class="row" ng-show="m.vincProcess==1||m.vincProcess==3" id="divMedidas">
            <div class="widget-box">
                <div class="widget-header">Obligaciones procesales</div>
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
                                                                   ng-checked="m.nationalArrangement==true"
                                                                   data-val="true"
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
                                                            <input name="arrangementType" class="ace" type="radio"
                                                                   value="2"
                                                                   ng-model="m.arrType" ng-checked="m.arrType==2"
                                                                   data-val="true"
                                                                   data-val-required="Debe seleccionar un valor">
                                                            <span class="lbl">&nbsp;&nbsp;SCPP</span>
                                                        </label>
                                                        <br/>
                                                        <label ng-click="loadArrangements()">
                                                            <input name="arrangementType" class="ace" type="radio"
                                                                   value="1"
                                                                   ng-model="m.arrType" ng-checked="m.arrType==1">
                                                            <span class="lbl">&nbsp;&nbsp;MC</span>
                                                        </label>
                                                    </div>
                                                </div>
                                                <div ng-show="m.arrType>0&&m.nationalArrangement!=undefined"
                                                     class="col-xs-6">
                                                    <label ng-bind-html="lblTerms"></label>
                                                    <br/>
                                                <textarea class="form-control limited" name="terms"
                                                          ng-model="m.terms"
                                                          maxlength="980"
                                                          data-val="true"
                                                          data-val-required="Plazo es un campo requerido">
                                                </textarea>
                                            <span class="field-validation-valid" data-valmsg-for="terms"
                                                  data-valmsg-replace="true"></span>
                                                    <input ng-if="sendTerms==true" type="hidden" name="terms"
                                                           value="{{m.terms}}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>

                                        <div class="row">
                                            <div class="col-xs-10 col-xs-offset-1">
                                        <span ng-class='m.errArrmntSel&&m.errArrmntSel!="" ? "field-validation-error" : "input-validation-valid"'
                                              ng-bind-html="m.errArrmntSel"></span>

                                                <div class="row" ng-repeat="arrangment in m.lstArrangementShow">
                                                    <div class="checkbox">
                                                        <label ng-click="validateArrangementSel($index,arrangment.id);">
                                                            <input class="ace" id="arrangement{{arrangment.id}}"
                                                                   ng-disabled=" (m.disableAll==true) || (arrangment.isDefault==true && arrangment.selVal==true) "
                                                                   type="checkbox"
                                                                   ng-model="m.lstArrangementShow[$index].selVal">
                                                            <span class="lbl col-xs-10">&nbsp;&nbsp;{{arrangment.name}}</span>
                                                        </label>
                                                    </div>
                                                    <br/>

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

        <div class="row" ng-show="m.vincProcess==1||m.vincProcess==3" id="divContact">
            <div class="widget-box">
                <div class="widget-header">Datos de contacto</div>
                <div class="widget-body">
                    <br/>

                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">

                            <div class="col-xs-12">
                                <label for="contactName">&iquest;Tiene contactos?</label>
                                <br/>

                                <div class="radio">
                                    <label ng-click="hasContacts(1)">
                                        <input class="ace" type="radio" value="1" ng-model="m.rdHasContacts"
                                               ng-checked="m.rdHasContacts==1">
                                        <span class="lbl">&nbsp;&nbsp;Si</span>
                                    </label>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <label ng-click="hasContacts(2)">
                                        <input class="ace" type="radio" value="2" ng-model="m.rdHasContacts"
                                               ng-checked="m.rdHasContacts==2">
                                        <span class="lbl">&nbsp;&nbsp;No</span>
                                    </label>
                                </div>
                            </div>

                        </div>
                    </div>

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

                            <div class="col-xs-2">
                                <label for="contactPhone">Tel&eacute;fono:</label>
                                <br/>
                                <input id="contactPhone" type="text"
                                       class="input-xxlarge" ng-model="m.contactPhone"/>
                                <br/>
                            </div>


                            <div class="col-xs-3">
                                <label>Direcci&oacute;n</label>
                                <br/>
                        <textarea class="input-xxlarge form-control limited" id="contactAddress"
                                  maxlength="980" ng-model="m.contactAddress">
                        </textarea>
                            </div>

                            <div class="col-xs-2">
                                <label>
                                    <input class="ace" id="contactLiveWith"
                                           ng-disabled=" (m.disableAll==true) || (arrangment.isDefault==true && arrangment.selVal==true) "
                                           type="checkbox"
                                           ng-model="m.contactLiveWith">
                                    <span class="lbl col-xs-10">&nbsp;&nbsp;&iquest;Vive con el imputado?</span>
                                </label>
                                <br/>
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
                                                <th class="element-center">Tel&eacute;fono</th>
                                                <th class="element-center">Direcci&oacute;n</th>
                                                <th class="element-center">&iquest;Vive con <br/> el imputado?</th>
                                                <th class="element-center">Quitar</th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <div>
                                                <tr ng-repeat="contact in m.lstContactData track by $index">
                                                    <td class="element-center">{{contact.name}}</td>
                                                    <td class="element-center">{{contact.phone}}</td>
                                                    <td class="element-center">{{contact.address}}</td>
                                                    <td class="element-center">{{contact.liveWith==true?"Si":"No"}}</td>
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
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="col-xs-6">
                                    <br/>
                                    <label>Observaciones</label>
                                    <br/>
                            <textarea class="input-xxlarge form-control limited" name="comments"
                                      ng-model="m.comments"
                                      maxlength="980" data-val="true"
                                      data-val-required="Observaciones un campo requerido">
                            </textarea>
        <span class="field-validation-valid" data-valmsg-for="comments"
              data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>

                    </div>
                    <br/>

                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-xs-12">
                <div ng-show="MsgError&&MsgError!=''" class="alert alert-danger element-center">
                    <p ng-bind-html="MsgError"></p>
                </div>
            </div>
        </div>

        <div class="row element-right">
            <div class="col-xs-6">
                <h3 class="header smaller lighter blue element-left">
                    <small>Nombre del supervisor:</small>
                    &nbsp;&nbsp;{{m.userName}}
                </h3>
            </div>
            <div class="col-xs-6" ng-show="m.isFinished==true">
                <h3 class="header smaller lighter blue element-left">
                    <small>Hora de t&eacute;rmino:</small>
                    &nbsp;&nbsp;{{m.endTime}}
                </h3>
            </div>

            <div class="col-xs-6" ng-show="m.canSave==true">
                <br/>
                            <span class="btn btn-default btn-sm"
                                  ng-click="returnUrl('<c:url value='/supervisor/hearingFormat/indexFormats.html'/>'+'?id='+m.idCase)">
                                Regresar
                            </span>

                            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                  ng-click="submitPartiaSaveHF('#FormFormatId','<c:url value='/supervisor/hearingFormat/doUpsert.json'/>');">
                                                  Guardar
                                            </span>
            </div>
        </div>

        <div class="row">
            <div id="finishConfirm" class="modal-footer" ng-show="m.canSave==true">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-confirm-action
                          confirm-message="&iquest;Est&aacute; seguro que desea terminar el formato de audiencia?"
                          confirm-title="Terminar formato de audiencia" confirm-type="info"
                          confirmed-click-action="saveHF('#FormFormatId','<c:url value='/supervisor/hearingFormat/doUpsert.json'/>',validateSave);">
                          Terminar
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

<script>
    var date = new Date();
    date.setFullYear(date.getFullYear() - 18);
    $('#imputedBirthDateStr').datepicker({autoclose: true, endDate: date}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>