<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/hearingFormatCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/zipSearchDrct.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
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

<body scroll="no" ng-app="ptlUmc">

<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<h2 class="element-center"><i class="icon-archive"></i>&nbsp;&nbsp;Formato de audiencia</h2>

<form id="FormFormatId" name="FormFormatId" class="form-horizontal"
      role="form" ng-controller="upsertController" method="post">
<br/>

<div class="container body-content" ng-controller="hearingFormatController">

<input type="hidden" name="arrangementType" value="{{m.arrmntType}}"/>
<input type="hidden" name="lstArrangement" value="{{m.lstArrangementShow}}"/>

<div class="row">
<div class="row element-right">
    <div ng-show="(m.canSave==false&&m.hasHF==true)||m.hasSearch==false">
        <span class="btn btn-default btn-sm"
              ng-click="returnUrl('<c:url value='/index.html'/>')">
                                Regresar
                            </span>
    </div>
    <div ng-show="m.canSave==true&&m.hasHF==false&&m.hasSearch==true">
                            <span class="btn btn-default btn-sm"
                                  ng-click="returnUrl('<c:url value='/index.html'/>')">
                                Cancelar
                            </span>
                            <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                  ng-click="submitReloadHF('#FormFormatId', '<c:url value='/supervisor/doUpsert.json'/>',false,validateSave)">
                                  Guardar
                            </span>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">
        <div ng-show="MsgError!=''" class="alert alert-danger element-center">
            <span>{{MsgError}}</span>
        </div>
    </div>
</div>

<div class="widget-box">
<div class="widget-header">Datos generales</div>

<div class="widget-body">

<div class="row">
<div class="col-xs-10 col-xs-offset-1">

<div class="row">
    <br/>

    <div class="col-xs-3">

        <label>No. Carpeta:</label>

        <div class="input-group">
            <input id="idFolderParam" type="text" class="form-control search-query input-small"
                   ng-model="m.idFolderParam"
                   name="idFolderCode"
                   placeholder="No. de carpeta..." data-val="true"
                   data-val-required="No. de carpeta es un campo requerido"/>
                                    <span class="input-group-btn">
                                        <button id="btnSearch" type="button" class="btn btn-purple btn-sm"
                                                ng-click="searchCase(m.idFolderParam,'<c:url value='/supervisor/searchCase.json'/>','<c:url value='/supervisor/searchArrangements.json'/>');">
                                            Buscar
                                            <i class="icon-search icon-on-right bigger-110"></i>
                                        </button>
                                    </span>
        </div>
        <span class="field-validation-valid" data-valmsg-for="idFolderCode"
              data-valmsg-replace="true"></span>

    </div>

    <div class="col-xs-6 col-xs-offset-3">
        <div class="col-xs-6">
            <label for="numberDate">Cita</label>
            <br/>
            <input id="numberDate" ng-model="m.numberDate" name="numberDate" type="text" class="input-large"
                   data-val="true"
                   data-val-required="Cita es un campo requerido"/>
            <br/>
            <span class="field-validation-valid" data-valmsg-for="numberDate"
                  data-valmsg-replace="true"></span>

        </div>


        <div class="col-xs-6">
            <label for="room">Sala</label>
            <br/>
            <input id="room" ng-model="m.room" name="room" type="text" class="input-large" data-val="true"
                   data-val-required="Sala es un campo requerido"/>
            <br/>
            <span class="field-validation-valid" data-valmsg-for="room"
                  data-valmsg-replace="true"></span>
        </div>
    </div>

</div>


<div class="row">
    <br/>
    <label><b>Audiencia</b></label>
</div>

<div class="row">
    <div class="col-xs-3">
        <label for="initTime">Hora inicio audiencia</label>

        <div class="input-group bootstrap-timepicker">
            <input id="initTime" name="initTime" ng-model="m.initTime" ng-change="validateInitEnd();" readonly
                   type="text"
                   ng-class='m.errTimeIn&&m.errTimeIn!="" ? "form-control input-validation-error" : "form-control"'/>
            <span class="input-group-addon"><i class="icon-time bigger-110"></i></span>
            <br/>
        </div>
        <span ng-class='m.errTimeIn&&m.errTimeIn!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errTimeIn}}</span>
    </div>

    <div class="col-xs-3">
        <label for="endTime">Hora termino audiencia</label>

        <div class="input-group bootstrap-timepicker">
            <input id="endTime" name="endTime" ng-change="validateInitEnd();" ng-model="m.endTime" readonly type="text"
                   ng-class='m.errTime&&m.errTime!="" ? "form-control input-validation-error" : "form-control"'/>
            <span class="input-group-addon"><i class="icon-time bigger-110"></i></span>
            <br/>
        </div>
        <span ng-class='m.errTime&&m.errTime!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errTime}}</span>

    </div>

    <div class="col-xs-6">
        <label for="judgeName">Nombre del Juez</label>
        <br/>
        <input id="judgeName" ng-model="m.judgeName" name="judgeName" type="text" class="input-xxlarge" data-val="true"
               data-val-required="Nombre del juez es un campo requerido"/>
        <br/>
            <span class="field-validation-valid" data-valmsg-for="judgeName"
                  data-valmsg-replace="true"></span>
    </div>
</div>

<div class="row">
    <br/>

    <div class="col-xs-6">

        <label for="mpName">Nombre del Ministerio P?blico</label>
        <br/>
        <input id="mpName" ng-model="m.mpName" name="mpName" type="text" class="input-xxlarge" data-val="true"
               data-val-required="Nombre del Ministerio P?blico es un campo requerido"/>
        <br/>
            <span class="field-validation-valid" data-valmsg-for="mpName"
                  data-valmsg-replace="true"></span>
    </div>

    <div class="col-xs-6">

        <label for="defenderName">Nombre del defensor</label>
        <br/>
        <input id="defenderName" ng-model="m.defenderName" name="defenderName" type="text" class="input-xxlarge"
               data-val="true"
               data-val-required="Nombre del defensor es un campo requerido"/>
        <br/>
            <span class="field-validation-valid" data-valmsg-for="defenderName"
                  data-valmsg-replace="true"></span>
    </div>

</div>

<div class="row">
    <br/>
    <label><b>Imputado</b></label>
</div>


<div class="row">

    <div class="col-xs-4">

        <label for="imputedName">Nombre(s)</label>
        <br/>
        <input id="imputedName" ng-model="m.imputedName" name="imputedName" type="text" class="input-xxlarge"
               data-val="true"
               data-val-required="Nombre es un campo requerido"/>
        <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedName"
                  data-valmsg-replace="true"></span>
    </div>

    <div class="col-xs-4">

        <label for="imputedFLastName">Apellido paterno</label>
        <br/>
        <input id="imputedFLastName" ng-model="m.imputedFLastName" name="imputedFLastName" type="text"
               class="input-xxlarge" data-val="true"
               data-val-required="Apellido paterno es un campo requerido"/>
        <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedFLastName"
                  data-valmsg-replace="true"></span>

    </div>

    <div class="col-xs-4">

        <label for="imputedSLastName">Apellido materno</label>
        <br/>
        <input id="imputedSLastName" ng-model="m.imputedSLastName" name="imputedSLastName" type="text"
               class="input-xxlarge" data-val="true"
               data-val-required="Apellido materno es un campo requerido"/>
        <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedSLastName"
                  data-valmsg-replace="true"></span>

    </div>

</div>

<div class="row">
    <br/>

    <div class="col-xs-4">

        <label for="imputedBirthDate">Fecha de nacimiento:</label>

        <div class="row">
            <div class="col-xs-8 col-sm-11">
                <div class="input-group">
                    <input ng-class='m.errBth&&m.errBth!=""&&!m.impBthDay ? "form-control date-picker input-validation-error" : "form-control date-picker"'
                           id="imputedBirthDate" name="imputedBirthDate" type="text"
                           data-date-format="dd/mm/yyyy"
                           readonly ng-change="calcAge();" ng-model="m.impBthDay"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                </div>
                <span ng-class='m.errBth&&m.errBth!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errBth}}</span>
            </div>
        </div>

    </div>

    <div class="col-xs-4">

        <label for="imputedAge">Edad</label>
        <br/>
        <input id="imputedAge" name="imputedAge" type="text" class="input-xxlarge" readonly ng-model="m.impAge"/>
        <br/>
        <span ng-class='m.errAge&&m.errAge!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errAge}}</span>

    </div>

    <div class="col-xs-4">

        <label for="imputedTel">Telefono(s)</label>
        <br/>
        <input id="imputedTel" ng-model="m.imputedTel" name="imputedTel" type="text" class="input-xxlarge"
               data-val="true"
               data-val-required="Telefono(s) es un campo requerido"/>
        <br/>
            <span class="field-validation-valid" data-valmsg-for="imputedTel"
                  data-valmsg-replace="true"></span>

    </div>

</div>

<div class="row">
    <br/>
    <label><b>Dirección</b></label>
    <%@ include file="/WEB-INF/jsp/supervisor/_addresHF.jsp" %>
</div>

<div class="row">
    <br/>
    <label><b>Detalles</b></label>
</div>


<div class="row">

    <div class="col-xs-6">
        <label for="crimes">Delito(s)</label>
        <br/>
        <textarea class="form-control limited" name="crimes" id="crimes"
                  ng-model="m.crimes"
                  maxlength="980"
                  data-val="true"
                  data-val-required="Delito(s) es un campo requerido">
            {{m.crimes}}</textarea>
            <span class="field-validation-valid" data-valmsg-for="crimes"
                  data-valmsg-replace="true"></span>
    </div>

    <div class="col-xs-6">
        <label for="additionalData">Datos adicionales de la audiencia</label>
        <br/>
        <textarea class="form-control limited" name="additionalData" id="additionalData"
                  maxlength="980" data-val="true"
                  ng-model="m.addtionalData"
                  data-val-required="Datos adicionales es un campo requerido">{{m.addtionalData}}</textarea>
        <span class="field-validation-valid" data-valmsg-for="additionalData"
              data-valmsg-replace="true"></span>
    </div>


</div>


<div class="row">
    <br/>

    <div class="col-xs-3 col-xs-offset-1">
        <label>Control de detenci?n</label>
        <br/>
        <span ng-class='m.errCtrlDet&&m.errCtrlDet!=""? "field-validation-error" : "input-validation-valid"'>{{m.errCtrlDet}}</span>

        <div class="radio">
            <label ng-click="clCrtlDet();">
                <input name="controlDetention" class="ace col-xs-1" type="radio" value="1" ng-model="m.ctrlDet"
                       ng-checked="m.ctrlDet==1">
                <span class="lbl col-xs-10">&nbsp;&nbsp;Legal</span>
            </label>
            <label ng-click="clCrtlDet();">
                <input name="controlDetention" class="ace col-xs-1" type="radio" value="2" ng-model="m.ctrlDet"
                       ng-checked="m.ctrlDet==2">
                <span class="lbl col-xs-10">&nbsp;&nbsp;Ilegal</span>
            </label>
        </div>
    </div>

    <div class="col-xs-3 col-xs-offset-1">
        <label>Ampliaci?n del termino</label>
        <br/>
        <span ng-class='m.errExt&&m.errExt!=""? "field-validation-error" : "input-validation-valid"'>{{m.errExt}}</span>

        <div class="radio">
            <label ng-click="clExtension();">
                <input name="extension" class="ace col-xs-1" type="radio" value="1" ng-model="m.ext"
                       ng-checked="m.ext==1">
                <span class="lbl col-xs-10">&nbsp;&nbsp;72 hrs</span>
            </label>
            <label ng-click="clExtension();">
                <input name="extension" class="ace col-xs-1" type="radio" value="2" ng-model="m.ext"
                       ng-checked="m.ext==2">
                <span class="lbl col-xs-10">&nbsp;&nbsp;144 hrs</span>
            </label>
            <label ng-click="clExtension();">
                <input name="extension" class="ace col-xs-1" type="radio" value="3" ng-model="m.ext"
                       ng-checked="m.ext==3">
                <span class="lbl col-xs-10">&nbsp;&nbsp;No</span>
            </label>
        </div>
    </div>
    <div class="col-xs-3 col-xs-offset-1">
        <label>Audiencia</label>
        <br/>
        <span ng-class='m.errHtype&&m.errHtype!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errHtype}}</span>

        <div class="radio">
            <label ng-click="clHType();">
                <input name="hearingType" class="ace col-xs-1" type="radio" value="1" ng-model="m.hType"
                       ng-checked="m.hType==1">
                <span class="lbl col-xs-10">&nbsp;&nbsp;SCPP</span>
            </label>
            <label ng-click="clHType();">
                <input name="hearingtype" class="ace col-xs-1" type="radio" value="2" ng-model="m.hType"
                       ng-checked="m.hType==2">
                <span class="lbl col-xs-10">&nbsp;&nbsp;MC</span>
            </label>
        </div>
    </div>

</div>

<div class="row">
    <br/>

    <div class="col-xs-5 col-xs-offset-1">

        <div class="row">
            <label>Formulaci?n de imputaci?n</label>
            <br/>
            <span ng-class='m.errFormImp&&m.errFormImp!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errFormImp}}</span>

            <div class="radio">

                <label ng-click="clFormImp();">
                    <input class="ace col-xs-1" type="radio" ng-model="m.formImp" value="1"
                           ng-checked="m.impDate!=''&&m.hasHF==true">
                    <span class="lbl col-xs-10">&nbsp;&nbsp;Si</span>
                </label>

                <label ng-click="clFormImp();">
                    <input class="ace col-xs-1" type="radio" ng-model="m.formImp" value="2"
                           ng-checked="m.impDate==''&&m.hasHF==true">
                    <span class="lbl col-xs-10">&nbsp;&nbsp;No</span>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6">
                <br/>
                <label for="imputationDate"><span ng-show="m.formImp==1">*</span>Fecha</label>

                <div class="input-group">

                    <input ng-class='m.errFormImpDate&&m.errFormImpDate!=""&&!m.impDate ? "form-control date-picker input-validation-error" : "form-control date-picker"'
                           ng-disabled="m.formImp!=1&&m.formImp>0"
                           id="imputationDate" name="imputationDate" ng-model="m.impDate"
                           data-date-format="dd/mm/yyyy"
                           type="text"
                           ng-change="m.errFormImpDate=''"
                           readonly/>
                            <span class="input-group-addon">
                                <i class="icon-calendar bigger-110"></i>
                            </span>
                </div>
                <span ng-class='m.errFormImpDate&&m.errFormImpDate!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errFormImpDate}}</span>

            </div>
        </div>

    </div>

    <div id="vincProcID" class="col-xs-5 col-xs-offset-1">

        <div class="row">
            <label>Vinculaci?n a proceso</label>
            <br/>
            <span ng-class='m.errLinkProc&&m.errLinkProc!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errLinkProc}}</span>


            <div class="radio">
                <label ng-click="clLinkProc();">
                    <input class="ace col-xs-1" name="vincProcessRadio" type="radio" value="1" ng-model="m.vincProcess"
                           ng-checked="m.linkageRoom!=''&&m.hasHF==true">
                    <span class="lbl col-xs-10">&nbsp;&nbsp;Si</span>
                </label>
                <label ng-click="clLinkProc();">
                    <input class="ace col-xs-1" name="vincProcessRadio" type="radio" value="2" ng-model="m.vincProcess"
                           ng-checked="m.linkageRoom==''&&m.hasHF==true">
                    <span class="lbl col-xs-10">&nbsp;&nbsp;No</span>
                </label>
            </div>
        </div>

        <div class="row">

            <div class="col-xs-6">

                <div class="row">
                    <br/>
                    <label><span ng-show="m.vincProcess==1">*</span>Sala:</label>
                    <input type="text" name="linkageRoom"
                           ng-class='m.errLnkRoom&&m.errLnkRoom!="" ? "input-validation-error input-small" : "input-small"'
                           ng-disabled="m.vincProcess&&m.vincProcess>1" ng-model="m.linkageRoom"
                           ng-change="m.errLnkRoom=''"/>
                    <br/>
                    <span ng-class='m.errLnkRoom&&m.errLnkRoom ? "field-validation-error" : "input-validation-valid"'>{{m.errLnkRoom}}</span>
                </div>


                <div class="row">
                    <br/>
                    <label for="linkageDate"><span ng-show="m.vincProcess==1">*</span>Fecha:</label>

                    <div class="input-group">
                        <input ng-class='m.errLnkDt&&m.errLnkDt!=""&&!m.linkageDate ? "form-control date-picker input-validation-error" : "form-control date-picker"'
                               ng-disabled="m.vincProcess&&m.vincProcess>1"
                               id="linkageDate"
                               data-date-format="dd/mm/yyyy"
                               ng-change="m.errLnkDt=''"
                               name="linkageDate"
                               ng-model="m.linkageDate"
                               type="text"
                               readonly/>
                <span class="input-group-addon">
                    <i class="icon-calendar bigger-110"></i>
                </span>
                    </div>
                    <span class='m.errLnkDt&&m.errLnkDt!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errLnkDt}}</span>

                </div>

                <div class="row">
                    <br/>
                    <label for="linkageTime"><span ng-show="m.vincProcess==1">*</span>Hora</label>

                    <div class="input-group bootstrap-timepicker">
                        <input id="linkageTime" name="linkageTime" readonly type="text"
                               ng-class='m.errLnkTm && m.errLnkTm!="" ? "form-control input-validation-error" : "form-control"'
                               ng-disabled="m.vincProcess&&m.vincProcess>1" ng-model="m.linkageTime"
                               ng-change="m.errLnkTm=''"/>
                                            <span class="input-group-addon">
                                                <i class="icon-time bigger-110"></i>
                                            </span>

                    </div>
                    <span ng-class='m.errLnkTm&&m.errLnkTm!="" ? "field-validation-error" : "input-validation-valid"'>{{m.errLnkTm}}</span>
                </div>
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


<div class="row" ng-show="m.hasSearch==true">


    <div class="widget-box">
        <div class="widget-header">Medidas cautelares</div>


        <div class="widget-body">

            <div class="row">
                <br/>

                <div class="col-xs-6 col-xs-offset-1">
                    <label for="room">Plazo</label>
                    <br/>

                    <textarea class="form-control limited" name="terms" id="terms"
                              ng-model="m.terms"
                              maxlength="980"
                              data-val="true"
                              data-val-required="Plazo es un campo requerido">
                        {{m.terms}}</textarea>
                    <span class="field-validation-valid" data-valmsg-for="terms"
                          data-valmsg-replace="true"></span>

                </div>
            </div>

            <div class="row">
                <br/>

                <div class="col-xs-11 col-xs-offset-1">
                    <span ng-class='m.errArrmntSel&&m.errArrmntSel!=""? "field-validation-error" : "input-validation-valid"'>{{m.errArrmntSel}}</span>
                </div>
                <br/>

                <div class="col-xs-11 col-xs-offset-1">


                    <div class="row" ng-repeat="arrangment in m.lstArrangementShow">
                        <div class="checkbox">
                            <label ng-click="validateArrangementSel();">
                                <input class="ace" ng-disabled="m.hasHF==true"
                                       type="checkbox" ng-model="m.lstArrangementShow[$index].selVal">
                                <span class="lbl col-xs-10">&nbsp;&nbsp;{{arrangment.name}}</span>
                            </label>
                        </div>
                        <div class="col-xs-offset-1">
                            <textarea class="form-control limited" ng-disabled="m.hasHF==true"
                                      ng-change="validateArrangementSel()"
                                      maxlength="980" ng-model="m.lstArrangementShow[$index].description"
                                      ng-show="m.lstArrangementShow[$index].selVal==true">{{m.lstArrangementShow[$index].description}}</textarea>
                        </div>
                    </div>
                </div>
                <br/>

            </div>

            <br/>

        </div>
    </div>
</div>

</div>

<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>

</div>
</div>
</form>
</body>
</html>