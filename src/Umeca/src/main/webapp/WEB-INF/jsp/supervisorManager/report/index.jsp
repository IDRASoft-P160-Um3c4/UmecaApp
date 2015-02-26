<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/report/managerSupReportCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
    <script>
        jQuery(function ($) {
            $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });
            $('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function () {
                $(this).next().focus();
            });
        });
    </script>

    <title>Reportes para coordinaci&oacute;n de supervisi&oacute;n</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" ng-controller="managerSupReportController">
    <div class="col-xs-12">
        <div class="row">
            <h2 class="element-center"><i class="icon icon-file"></i>&nbsp;&nbsp;Reportes para coordinaci&oacute;n de
                supervisi&oacute;n
            </h2>
        </div>
        <div class="row">
            <input type="hidden" id="urlMun" value="<c:url value='/supervisorManager/report/getMun.json'/>"/>
            <input type="hidden" id="urlLoc" value="<c:url value='/supervisorManager/report/getLoc.json'/>"/>

            <form id="FormRepExcel" name="FormRepExcel" class="form-horizontal"
                  role="form" method="post" ng-cloak>

                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="widget-box">
                            <div class="widget-header">Filtros de b&uacute;squeda</div>
                            <div class="widget-body">
                                <div class="row">
                                    <br/>

                                    <div class="col-xs-10 col-xs-offset-1">
                                        <div class="widget-box">
                                            <div class="widget-header">Rango de fechas</div>
                                            <div class="widget-body">
                                                <div class="row">
                                                    <div class="col-xs-10 col-xs-offset-1">
                                                        <br/>

                                                        <div class="row">

                                                            <div class="col-xs-3 col-xs-offset-2">
                                                                <label for="initDate">Fecha inicio</label>

                                                                <div class="row">
                                                                    <div class="input-group">
                                                                        <input id="initDate" name="initDate"
                                                                               ng-model="initDate"
                                                                               class="form-control date-picker"
                                                                               type="text"
                                                                               data-date-format="yyyy/mm/dd"
                                                                               data-val="true"
                                                                               readonly
                                                                               data-val-required="Fecha de inicio es un campo requerido"/>
                                                            <span class="input-group-addon">
                                                                <i class="icon-calendar bigger-110"></i>
                                                            </span>
                                                                    </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="initDate"
                                                              data-valmsg-replace="true"></span>
                                                                </div>
                                                            </div>

                                                            <div class="col-xs-3 col-xs-offset-1">
                                                                <label for="endDate">Fecha fin</label>

                                                                <div class="row">
                                                                    <div class="input-group">
                                                                        <input id="endDate" name="endDate"
                                                                               class="form-control date-picker"
                                                                               type="text"
                                                                               data-date-format="yyyy/mm/dd"
                                                                               data-val="true"
                                                                               readonly
                                                                               ng-model="endDate"
                                                                               data-val-required="Fecha de fin es un campo requerido"/>
                                                            <span class="input-group-addon">
                                                                <i class="icon-calendar bigger-110"></i>
                                                            </span>
                                                                    </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="endDate"
                                                              data-valmsg-replace="true"></span>
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

                                <div class="row" ng-init='lstOpts=${lstOpts}'>
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <div class="widget-box">
                                            <div class="widget-header">Indicadores</div>
                                            <div class="widget-body">
                                                <div class="row">
                                                    <br/>

                                                    <div class="col-xs-12">
                                                        <div class="col-xs-3">
                                                            <div class="checkbox"
                                                                 style="padding-left: 32px !important;">
                                                                <label>
                                                                    <input class="ace"
                                                                           ng-model="byDistrict"
                                                                           type="checkbox">
                                                                    <span class="lbl">&nbsp;&nbsp;Seleccionar distrito </span>
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <select class="form-control element-center col-xs-6"
                                                                    ng-disabled="byDistrict!=true"
                                                                    ng-model="m.district"
                                                                    ng-options="di.name for di in lstDistrict"
                                                                    ng-init='lstDistrict= ${lstDistrict};'></select>
                                                            <input type="hidden" name="districtId"
                                                                   value="{{m.district.id}}"
                                                                   ng-disabled="byDistrict!=true"/>
                                                        </div>
                                                        <br/>
                                                    </div>

                                                    <div class="col-xs-10 col-xs-offset-1">
                                                        <div class="space"></div>
                                                        <div ng-show="MsgErrorSel&&MsgErrorSel!=''"
                                                             class="alert alert-danger element-center">
                                                            <span ng-bind-html="MsgErrorSel"></span>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <div class="col-xs-6" ng-repeat="e in lstOpts">
                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           ng-model="opts[e.name]"
                                                                           type="checkbox"
                                                                           name="{{e.name}}">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;{{e.description}}</span>
                                                                </label>
                                                                <br/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-12">
                                                        <div class="space"></div>
                                                        <div class="col-xs-3">
                                                            <div style="padding-left: 35px; padding-right: 10px;">
                                                                <label>Estado</label>
                                                                <br/>
                                                                <select class="form-control element-center"
                                                                        ng-disabled="opts['countDetPlace']!=true"
                                                                        ng-model="m.state"
                                                                        ng-options="e.name for e in lstStates"
                                                                        ng-init='lstStates = ${lstStates}'
                                                                        ng-change="getMun();"></select>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-4">
                                                            <div>
                                                                <label>Municipio</label>
                                                                <br/>
                                                                <select class="form-control element-center"
                                                                        ng-disabled="opts['countDetPlace']!=true"
                                                                        ng-model="m.municipality"
                                                                        ng-options="mu.name for mu in lstMun"
                                                                        ng-change="getLoc();"></select>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <div style="padding-left: 10px; padding-right: 35px;">
                                                                <label>Localidad</label>
                                                                <br/>
                                                                <select class="form-control element-center"
                                                                        ng-disabled="opts['countDetPlace']!=true"
                                                                        ng-model="m.location"
                                                                        ng-options="l.name for l in lstLocation">
                                                                </select>
                                                                <input type="hidden" name="locationId"
                                                                       value="{{m.location.id}}"
                                                                       ng-disabled="opts['countDetPlace']!=true"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-11 element-right">
                                        <br/>
                                        <span
                                                class="btn btn-default btn-primary btn-sm"
                                                ng-disabled="WaitFor==true"
                                                ng-click="submitReport('#FormRepExcel','<c:url value='/supervisorManager/report/doReport.html'/>', validateSel);"
                                                >Reporte</span>
                                        <br/>
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
    </form>
</div>
<div class="row">
    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridCasesId" class="element-center" style="margin: auto"></table>
        <div id="GridPager"></div>
        <div class="blocker" ng-show="working">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>
</div>
</body>
</html>