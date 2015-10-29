<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/minute/minuteCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/minute/agreementCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/minute/observationCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/minute/requestFinishCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/minute/authRejRequestFinishCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

    <script>
        $(document).ready(function () {
            $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });
            $('#startTime').timepicker({
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
        });
    </script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/typeahead/ui-bootstrap-tpls-0.12.1.js"></script>

    <link href="${pageContext.request.contextPath}/assets/content/upload/jquery.fileupload.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/colorbox.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/jquery.colorbox-min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.iframe-transport.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.fileupload.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upload/uploadFileCtrl.js"></script>

    <title>Agregar minuta</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<div class="container body-content">


    <div class="row" ng-controller="minuteController">
        <div class="blocker" ng-show="WaitFor==true">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
            </div>
        </div>

        <div class="col-xs-10 col-xs-offset-1"
             ng-init='minute=${minute}; isRH=${isRH}; lstAssistantSel=${assistants}; minute.attendant=${attendant!=null?attendant:"undefined"}'
             ;>
            <div class="row">
                <div class="col-xs-12">
                    <div class="row element-center">
                        <h2><i class="purple glyphicon glyphicon-user bigger-100 element-center"></i> &nbsp;Agregar
                            minuta</h2>
                    </div>
                    <br/>

                    <div class="row">
                        <div ng-show="MsgSuccess&&MsgSuccess!=''"
                             class="alert alert-success element-center success-font">
                            <span ng-bind-html="MsgSuccess"></span>
                        </div>

                        <div ng-show="MsgError&&MsgError!=''" class="alert alert-danger element-center error-font">
                            <span ng-bind-html="MsgError"></span>
                        </div>
                    </div>

                    <div class="row" id="divMinute">
                        <form id="FormMinute" name="FormMinute" class="form-horizontal" role="form">

                            <input type="hidden" id="hidMinuteId" name="id" value="{{minute.id}}"/>
                            <input type="hidden" id="hidIsRHId" value="${isRH}"/>
                            <input type="hidden" id="hidIsDirId" value="${isDir}"/>
                            <input type="hidden" id="hidFinishedMinuteId" value="{{minute.isFinished}}"/>

                            <div class="widget-box">
                                <div class="widget-header">Datos de la reuni&oacute;n</div>
                                <div class="widget-body">
                                    <div class="row">
                                        <div class="col-xs-10 col-xs-offset-1">

                                            <div class="row">
                                                <div class="space"></div>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-4">
                                                        <label for="title">T&iacute;tulo</label>
                                                        <br/>
                                                        <input id="title" ng-model="minute.title" name="title"
                                                               type="text"
                                                               ng-disabled="minute.isFinished==true"
                                                               class="input-xxlarge"
                                                               data-val="true"7
                                                               maxlength="500"
                                                               data-val-required="T&iacute;tulo es un campo requerido"/>
                                                        <br/>
                                        <span class="field-validation-valid" data-valmsg-for="title"
                                              data-valmsg-replace="true"></span>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <label>Orden del d&iacute;a</label>
                                                        <br/>
                                        <textarea class="input-xxlarge form-control limited" name="agenda"
                                                  ng-model="minute.agenda"
                                                  maxlength="740"
                                                  data-val="true"
                                                  ng-disabled="minute.isFinished==true"
                                                  data-val-required="Orden del d&iacute;a es un campo requerido">
                                        </textarea>
                                        <span class="field-validation-valid" data-valmsg-for="agenda"
                                              data-valmsg-replace="true"></span>
                                                    </div>

                                                    <div class="col-xs-4">
                                                        <label for="minuteDate">Fecha</label>
                                                        <br/><small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/30)</small>

                                                        <div class="input-group">
                                                            <input class="form-control date-picker"
                                                                   id="minuteDate"
                                                                   name="minuteDate"
                                                                   ng-disabled="minute.isFinished==true"
                                                                   ng-model="minute.minuteDate"
                                                                   data-date-format="yyyy/mm/dd" type="text"
                                                                   data-val="true"
                                                                   data-val-required="Fecha es un campo requerido">
                                            <span class="input-group-addon">
                                                <i class="icon-calendar bigger-110"></i>
                                            </span>
                                                        </div>
                                        <span class="field-validation-valid" data-valmsg-for="minuteDate"
                                              data-valmsg-replace="true"></span>
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="space"></div>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-4">
                                                        <label for="startTime">Hora inicio</label>

                                                        <div class="input-group bootstrap-timepicker">
                                                            <input id="startTime"
                                                                   name="startTime"
                                                                   ng-disabled="minute.isFinished==true"
                                                                   ng-model="minute.startTime"
                                                                   readonly
                                                                   type="text" class="form-control umeca-time-picker"
                                                                   data-val="true"
                                                                   data-val-required="Hora inicio es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                                            <br/>
                                                        </div>
                                        <span class="field-validation-valid" data-valmsg-for="startTime"
                                              data-valmsg-replace="true"></span>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <label for="endTime">Hora fin</label>

                                                        <div class="input-group bootstrap-timepicker">
                                                            <input id="endTime"
                                                                   name="endTime"
                                                                   ng-disabled="minute.isFinished==true"
                                                                   ng-model="minute.endTime"
                                                                   readonly
                                                                   type="text" class="form-control umeca-time-picker"
                                                                   data-val="true"
                                                                   data-val-required="Hora fin es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                                            <br/>
                                                        </div>
                                        <span class="field-validation-valid" data-valmsg-for="endTime"
                                              data-valmsg-replace="true"></span>
                                                    </div>
                                                    <div class="col-xs-4">
                                                        <label>Lugar</label>
                                                        <br/>
                                        <textarea class="input-xxlarge form-control limited" name="place"
                                                  ng-model="minute.place"
                                                  ng-disabled="minute.isFinished==true"
                                                  maxlength="740" data-val="true"
                                                  data-val-required="Lugar es un campo requerido">
                                        </textarea>
                                        <span class="field-validation-valid" data-valmsg-for="place"
                                              data-valmsg-replace="true"></span>
                                                        <br/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-xs-12">
                                                    <div class="col-xs-6">
                                                        <label>Buscar encargado</label>
                                                        <br/>
                                                        <input id="attendantStr" name="attendantStr" type="text"
                                                               class="input-xxlarge"
                                                               ng-model="minute.attendant"
                                                               ng-disabled="minute.isFinished==true"
                                                               typeahead="att as att.name for att in getAttendant($viewValue)| filter:$viewValue"
                                                               placeholder='Nombre de empleado ej. "Sandra Col&iacute;n"'
                                                               typeahead-on-select="selAttendant($item)"
                                                               typeahead-editable="false" typeahead-loading="loadingAtt"
                                                               typeahead-wait-ms="500"
                                                               data-val="true"
                                                               data-val-required="Encargado es un campo requerido"/>
                                                        <br/>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="attendantStr"
                                                              data-valmsg-replace="true"></span>
                                                        <i ng-show="loadingAtt" class="glyphicon glyphicon-refresh"></i>
                                                        <input type="hidden" name="attendantId"
                                                               value="{{minute.attendant.id}}"/>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label>Buscar participante</label>
                                                        <br/>
                                                        <input type="text" class="input-xxlarge"
                                                               ng-disabled="minute.isFinished==true"
                                                               ng-model="assistantSel"
                                                               typeahead="assis as assis.name for assis in getAssistant($viewValue)| filter:$viewValue"
                                                               placeholder='Nombre de empleado ej. "Bianca Solares"'
                                                               typeahead-on-select="selAssistant($item)"
                                                               typeahead-editable="false"
                                                               typeahead-loading="loadingAssis"
                                                               typeahead-wait-ms="500"/>
                                                        <i ng-show="loadingAssis"
                                                           class="glyphicon glyphicon-refresh"></i>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-xs-2"></div>
                                                <div class="col-xs-8">
                                                    <br/>

                                                    <div ng-show="MsgErrorAssis&&MsgErrorAssis!=''"
                                                         class="alert alert-danger element-center error-font">
                                                        <span ng-bind-html="MsgErrorAssis"></span>
                                                        <input type="hidden" name="assistants"
                                                               value="{{lstAssistantSel}}">
                                                    </div>
                                                    <div class="widget-box">
                                                        <div class="widget-header">Asistentes</div>
                                                        <div class="widget-body">
                                                            <div class="widget-main no-padding">
                                                                <table class="table table-striped table-bordered table-hover">
                                                                    <thead class="thin-border-bottom">
                                                                    <tr>
                                                                        <th class="element-center">Participante</th>
                                                                        <th class="element-center">Quitar</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                    <div>
                                                                        <tr ng-repeat="assis in lstAssistantSel track by $index">
                                                                            <td class="element-center">{{assis.name}}
                                                                            </td>
                                                                            <td class="element-center"><a
                                                                                    href="javascript:;"
                                                                                    style="display:inline-block;"
                                                                                    title="Quitar de la lista"
                                                                                    ng-click="removeAssistant($index)"><span
                                                                                    class="glyphicon glyphicon-minus blue"></span></a>
                                                                            </td>
                                                                        </tr>
                                                                    </div>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-2"></div>
                                            </div>

                                            <div class="row" ng-show="isRH==true&&minute.isFinished==false">
                                                <br/>

                                                <div class="col-xs-12 element-right">
                                                    <div class="col-xs-12 modal-footer">
                                <span class="btn btn-default btn-sm btn-primary"
                                      ng-click="submitMinute('#FormMinute','<c:url value="/shared/minute/doUpsertMinute.json"/>')">
                                Guardar
                                </span>
                                                    </div>
                                                    <br/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                            </div>

                        </form>
                    </div>

                    <div class="space"></div>

                    <div id="addAgreement" ng-if="isNew==true" class="alert alert-success element-center success-font">
                        <span>Ahora puede agregar acuerdos a la minuta</span>
                    </div>


                    <div class="row" ng-if="minute.id>0">
                        <div class="widget-box">
                            <div class="widget-header">Acuerdos
                                <div class="widget-toolbar">
                                    <a data-action="collapse" href="#"><i
                                            class="icon-chevron-up"></i></a>
                                </div>
                            </div>
                            <div class="widget-body">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div id="msgObs" class="alert alert-success element-center success-font"
                                             ng-if="successObs==true">
                                            <span>La observaci&oacute;n fue agregada con &eacute;xito</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12">
                                        <%@ include
                                                file="/WEB-INF/jsp/shared/minute/agreement/agreement.jsp" %>
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
    <div class="row">
        <div class="col-xs-12">
            <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
            <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
        </div>
    </div>
</div>
</body>
</html>
