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


  <script src="${pageContext.request.contextPath}/assets/scripts/app/managereval/statisticReportCtrl.js"></script>

  <title>Reporte estad&iacute;stico</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content col-xs-12 col-xs-offset-1">

  <script>


  </script>
  <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

  <h2 class="element-center"><i class="icon icon-file"></i>&nbsp;&nbsp;Reporte Estad&iacute;stico
  </h2>

  <form id="FormStatisRep" name="FormStatisRep" class="form-horizontal"
        role="form" ng-controller="statisticReportController" method="post" ng-init='entities=${lstFilter}' ng-cloak>


    <input type="hidden" id="filterSelected" name="filterSelected" value="{{m.filterSelected}}">


    <div class="row">
      <div class="col-xs-10 col-xs-offset-1">
        <div class="widget-box">
          <div class="widget-header">Filtros de b&uacute;squeda</div>
          <div class="widget-body">
            <div class="row">
              <div class="col-xs-10 col-xs-offset-1">
                <div class="row">
                  <br/>

                  <div class="widget-box">
                    <div class="widget-header">Rango de fechas</div>
                    <div class="widget-body">
                      <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                          <br/>

                          <div class="row">

                            <div class="col-xs-3 col-xs-offset-2">
                              <label for="initDate">Fecha inicio</label>
                              <br/>
                              <small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/01)</small>
                              <div class="row">
                                <div class="input-group">
                                  <input id="initDate" name="initDate"
                                         ng-model="initDate"
                                         class="form-control date-picker"
                                         type="text"
                                         data-date-format="yyyy/mm/dd" data-val="true"

                                         data-val-required="Fecha de inicio es un campo requerido"/>
                                                                    <span class="input-group-addon">
                                                                        <i class="icon-calendar bigger-110"></i>
                                                                    </span>
                                </div>
                                                                <span class="field-validation-valid"
                                                                      data-valmsg-for="initDate"
                                                                      data-valmsg-replace="true">
                                                                </span>
                              </div>
                            </div>

                            <div class="col-xs-3 col-xs-offset-1">
                              <label for="endDate">Fecha fin</label>
                              <br/>
                              <small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/30)</small>
                              <div class="row">
                                <div class="input-group">
                                  <input id="endDate" name="endDate"
                                         class="form-control date-picker" type="text"
                                         data-date-format="yyyy/mm/dd" data-val="true"
                                         ng-model="endDate"
                                         data-val-required="Fecha de fin es un campo requerido"/>
                                                                    <span class="input-group-addon">
                                                                        <i class="icon-calendar bigger-110"></i>
                                                                    </span>
                                </div>
                                                                <span class="field-validation-valid"
                                                                      data-valmsg-for="endDate"
                                                                      data-valmsg-replace="true">
                                                                </span>
                              </div>
                            </div>
                          </div>
                          <br/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <br/>
                </div>


                <div class="row">
                  <div ng-repeat="filter in entities">
                    <div class="checkbox">
                      <label>
                        <input class="ace" type="radio" ng-model="m.filterSelected"
                               ng-value="filter.name" ng-required="!m.filterSelected">
                        <span class="lbl col-xs-10">&nbsp;&nbsp;{{filter.description}}</span>
                      </label>
                    </div>
                    <br/>
                  </div>
                  <br/>

                </div>
                <br/>
                <div class="row" ng-show="msgError">
                  <div class="col-xs-8 col-xs-offset-2 alert alert-danger element-center">
                    <span class="control-label element-center">{{msgError}}</span>
                  </div>
                </div>

              </div>
            </div>
            <br/>

            <div class="row">
              <div class="col-xs-11 element-right">
                                <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                                      ng-click="findReport('#FormStatisRep','<c:url value='/supervisorManager/statisticReport/showReport.html'/>');">
                                    Realizar b&uacute;squeda
                                </span>
              </div>

            </div>

            <br/>
            <%--<div class="row">--%>
            <%--<div class="col-xs-11 element-right">--%>
            <%--<a href="<c:url value='/managereval/statisticReport/testd3.html' />">--%>
            <%--<span class="btn btn-default btn-primary btn-sm">--%>
            <%--D3--%>
            <%--</span>--%>
            <%--</a>--%>
            <%--</div>--%>

            <%--</div>--%>
            <br/>
            <br/>
          </div>
        </div>
      </div>
    </div>
    <br/>
  </form>

  <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
  <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>
<script type="text/javascript">

  jQuery(function ($) {
    /*$('#initDate').datepicker({autoclose: true}).next().on(ace.click_event, function () {
     $(this).prev().focus();
     });*/

    $('#initDate').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
      $(this).prev().focus();
    });

    $('#endDate').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
      $(this).prev().focus();
    });

  });
</script>