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

            <form id="FormRepExcel" name="FormRepExcel" class="form-horizontal"
                  role="form" method="post" ng-cloak>


                <%--<input type="hidden" id="hdLstStatusCases" name="lstStatusCaseStr" value="{{lstStatusCase}}">--%>

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

                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <div class="widget-box">
                                            <div class="widget-header">Reportes</div>
                                            <div class="widget-body">
                                                <div class="row">
                                                    <br/>

                                                    <div class="col-xs-10 col-xs-offset-1">
                                                        <div ng-show="MsgErrorSel&&MsgErrorSel!=''"
                                                             class="alert alert-danger element-center">
                                                            <span ng-bind-html="MsgErrorSel"></span>
                                                        </div>
                                                    </div>
                                                    <br/>

                                                    <div class="col-xs-12">

                                                        <div class="col-xs-6">
                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           type="checkbox"
                                                                           ng-model="m.casesMC" name="casesMC">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;N&uacute;mero de casos en MC</span>
                                                                </label>
                                                                <br/>
                                                            </div>

                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           type="checkbox"
                                                                           ng-model="m.casesSCPP" name="casesSCPP">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;N&uacute;mero de casos en SCPP</span>
                                                                </label>
                                                                <br/>
                                                            </div>

                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           type="checkbox"
                                                                           ng-model="m.imputedDrugs"
                                                                           name="imputedDrugs">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;N&uacute;mero de imputados que consumen sustancias por tipo</span>
                                                                </label>
                                                                <br/>
                                                            </div>

                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           type="checkbox"
                                                                           ng-model="m.casesDone" name="casesDone">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;N&uacute;mero de casos concluidos</span>
                                                                </label>
                                                                <br/>
                                                            </div>

                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           type="checkbox"
                                                                           ng-model="m.casesPrison" name="casesPrison">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;N&uacute;mero de casos con imposici&oacute;n de prisi&oacute;n preventiva</span>
                                                                </label>
                                                                <br/>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-6">
                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           type="checkbox"
                                                                           ng-model="m.casesNonFulfillment"
                                                                           name="casesNonFulfillment">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;N&uacute;mero de casos con reporte de incumplimiento</span>
                                                                </label>
                                                                <br/>
                                                            </div>

                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           type="checkbox"
                                                                           ng-model="m.casesSubtracted"
                                                                           name="casesSubtracted">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;N&uacute;mero de imputados declarados sustra&iacute;dos</span>
                                                                </label>
                                                                <br/>
                                                            </div>

                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           type="checkbox"
                                                                           ng-model="m.homeVisit" name="homeVisit">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;N&uacute;mero de visitas a domicilio para la supervisi&oacute;n</span>
                                                                </label>
                                                                <br/>
                                                            </div>

                                                            <div class="checkbox">
                                                                <label>
                                                                    <input class="ace"
                                                                           type="checkbox"
                                                                           ng-model="m.civilOrg" name="civilOrg">
                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;N&uacute;mero de canalizaciones a organizaciones de la sociedad civil (total y por tipo)</span>
                                                                </label>
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

                                <div class="row">

                                </div>

                                <br/>

                                <div class="row">
                                    <div class="col-xs-11 element-right">
<span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
      ng-click="submitReport('#FormRepExcel','<c:url value='/supervisorManager/report/doReport.json'/>', validateSel);">
                      Reporte
                </span>
                                    </div>

                                </div>

                                <br/>
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