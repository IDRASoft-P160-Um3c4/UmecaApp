<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/minute/minuteCtrl.js"></script>
    <title>Agregar minuta</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<div class="container body-content" ng-controller="minuteController">
    <div class="row">
        <div class="blocker" ng-show="WaitFor==true">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
            </div>
        </div>

        <div class="col-xs-10 col-xs-offset-1" ng-init='minute=${minute};'>

            <div class="row element-center">
                <h2><i class="purple glyphicon glyphicon-user bigger-100 element-center"></i> &nbsp;Agregar minuta</h2>
            </div>
            <br/>

            <div class="row">
                <div ng-show="MsgSuccess&&MsgSuccess!=''" class="alert alert-success element-center success-font">
                    <span ng-bind-html="MsgSuccess"></span>
                </div>

                <div ng-show="MsgError&&MsgError!=''" class="alert alert-danger element-center error-font">
                    <span ng-bind-html="MsgError"></span>
                </div>
            </div>

            <div class="row">
                <form id="FormMinute" name="FormMinute" class="form-horizontal" role="form">
                    <%--<input type="hidden" name="idEmployee" value="{{minute.idEmployee}}"/>--%>

                    <div class="widget-box">
                        <div class="widget-header">Datos de la reuni&oacute;n</div>
                        <div class="widget-body">

                            <div class="row">
                                <div class="space"></div>
                                <div class="col-xs-12">
                                    <div class="col-xs-6">
                                        <label for="title">T&iacute;tulo</label>
                                        <br/>
                                        <input id="title" ng-model="minute.title" name="title"
                                               type="text"
                                               class="input-xxlarge"
                                               data-val="true"
                                               data-val-required="T&iacute;tulo es un campo requerido"/>
                                        <br/>
                                        <span class="field-validation-valid" data-valmsg-for="title"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="col-xs-6">
                                        <label>Orden del d&iacute;a</label>
                                        <br/>
                                        <textarea class="input-xxlarge form-control limited" name="agenda"
                                                  ng-model="minute.agenda"
                                                  maxlength="980" data-val="true"
                                                  data-val-required="Orden del d&iacute;a es un campo requerido">
                                        </textarea>
                                        <span class="field-validation-valid" data-valmsg-for="agenda"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="space"></div>
                                <div class="col-xs-12">
                                    <div class="col-xs-4">
                                        <label for="minuteDate">Fecha</label>

                                        <div class="input-group">
                                            <input class="form-control date-picker"
                                                   id="minuteDate" name="minuteDate"
                                                   ng-model="minute.minuteDate"
                                                   data-date-format="yyyy/mm/dd" type="text" readonly
                                                   data-val="true"
                                                   data-val-required="Fecha es un campo requerido">
                                            <span class="input-group-addon">
                                                <i class="icon-calendar bigger-110"></i>
                                            </span>
                                        </div>
                                        <span class="field-validation-valid" data-valmsg-for="minuteDate"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="col-xs-4">
                                        <label for="startTime">Hora inicio</label>

                                        <div class="input-group bootstrap-timepicker">
                                            <input id="startTime" name="startTime" ng-model="minute.startTime"
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
                                            <input id="endTime" name="endTime" ng-model="minute.endTime"
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
                                </div>
                            </div>

                            <div class="row">
                                <div class="space"></div>
                                <div class="col-xs-12">
                                    <div class="col-xs-6">
                                        <label>Encargado</label>
                                        <br/>
                                        <select class="form-control element-center"
                                                ng-model="minute.attendant"
                                                ng-options="e.name for e in lstAttendant"
                                                ng-init='lstAttendant = ${lstAttendant};'></select>
                                        <input type="hidden" name="idEmployee" value="{{minute.attendant.id}}"/>
                                    </div>
                                    <div class="col-xs-6">
                                        <label>Lugar</label>
                                        <br/>
                                        <textarea class="input-xxlarge form-control limited" name="place"
                                                  ng-model="minute.place"
                                                  maxlength="980" data-val="true"
                                                  data-val-required="Lugar es un campo requerido">
                                        </textarea>
                                        <span class="field-validation-valid" data-valmsg-for="place"
                                              data-valmsg-replace="true"></span>
                                        <br/>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="space"></div>

                    <div class="widget-box">
                        <div class="widget-header">Asistentes</div>
                        <div class="widget-body">
                            <div class="row">
                                <div class="space"></div>
                                <div class="col-xs-12">

                                    <div class="col-xs-4">

                                        <div class="widget-box">
                                            <div class="widget-header">Jojutla</div>
                                            <div class="widget-body">
                                                <div class="row">
                                                    <div class="space"></div>
                                                    <div class="col-xs-12">
                                                        lista de sujetos distrito A
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xs-4">

                                        <div class="widget-box">
                                            <div class="widget-header">Cuautla</div>
                                            <div class="widget-body">
                                                <div class="row">
                                                    <div class="space"></div>
                                                    <div class="col-xs-12">
                                                        lista de sujetos distrito A
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xs-4">

                                        <div class="widget-box">
                                            <div class="widget-header">Cuernavaca</div>
                                            <div class="widget-body">
                                                <div class="row">
                                                    <div class="space"></div>
                                                    <div class="col-xs-12">
                                                        lista de sujetos distrito A
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="col-xs-12 modal-footer element-right">
                    <span class="btn btn-default btn-sm btn-primary"
                          ng-click="submitMinute('#FormMinute','<c:url value="/humanResources/minute/doUpsertMinute.json"/>')">
                    Guardar
                    </span>
                    </div>

                </form>
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
<script>
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
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>


