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


  <script src="${pageContext.request.contextPath}/assets/scripts/app/reportExcelCtrl.js"></script>

  <title>Reporte excel</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content col-xs-12 col-xs-offset-1">


  <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

  <h2 class="element-center"><i class="icon icon-file"></i>&nbsp;&nbsp;Reporte Excel
  </h2>



  <form id="FormRepExcel" name="FormRepExcel" class="form-horizontal"
        role="form" ng-controller="reportExcelController" method="post" ng-cloak>

    <input type="hidden" id="hdLstStatusCases" name="lstStatusCaseStr" value="{{lstStatusCase}}">

    <input type="hidden" id="hdLstStatusMeet" name="lstStatusMeetingStr" value="{{lstStatusMeeting}}">
    <input type="hidden" id="hdLstStatusVer" name="lstStatusVerificationStr" value="{{lstStatusVerification}}">

    <input type="hidden" id="hdLstGenderB" name="lstGenderBoolStr" value="{{lstGender}}">

    <input type="hidden" id="hdLstMaritalSt" name="lstMaritalStStr" value="{{lstMaritalSt}}">
    <input type="hidden" id="hdLstAcademicLvl" name="lstAcademicLvlStr" value="{{lstAcademicLvl}}">
    <input type="hidden" id="hdLstDrugs" name="lstDrugsStr" value="{{lstDrugs}}">
    <input type="hidden" id="hdLstLvlRisk" name="lstLvlRiskStr" value="{{lstLvlRisk}}">
    <input type="hidden" id="hdLstHearingType" name="lstHearingTypeStr" value="{{lstHearingType}}">
    <input type="hidden" name="idLoc" value="{{locSel.id}}">

    <input type="hidden" id="hdLstCrimesSel" name="lstCrimeStr" value="{{lstCrimesSel}}">
    <input type="hidden" id="hdLstArrangementSel" name="lstArrangementStr" value="{{lstArrangementSel}}">
    <input type="hidden" id="hdLstActivitiesSel" name="lstActivitiesStr" value="{{lstActivitiesSel}}">

    <input type="hidden"
           ng-init="urlGetMun= '<c:url value="/director/excelReport/getMunBySt.json"/>'; urlGetLoc= '<c:url value="/director/excelReport/getLocationsByMun.json"/>'">

    <input type="hidden" id="urlDowload" value="'<c:url value="/catalogs/address/locationsByZipCode.json"/>'">


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
                                                              data-valmsg-replace="true"></span>
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
                </div>
              </div>
            </div>
            <br/>

            <div class="row">
              <div class="col-xs-11 element-center">
<span class="btn btn-default btn-primary btn-sm"
      ng-click="findHumanResourceExcel('#FormRepExcel','<c:url value='/humanResources/excelReport/jxls.html'/>');">
                      Descargar Excel
                </span>
              </div>

            </div>
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

<%--<script type="text/javascript">--%>

  <%--var startDate = new Date('01/01/2012');--%>
  <%--var FromEndDate = new Date();--%>
  <%--var ToEndDate = new Date();--%>

  <%--jQuery(function ($) {--%>

    <%--$('#initDate').datepicker({--%>
      <%--minViewMode: 1,--%>
      <%--autoclose: true,--%>
      <%--endDate: new Date()}).next().on(ace.click_event, function () {--%>
      <%--$(this).prev().focus();--%>
    <%--})--%>


    <%--$('#initDate').datepicker()--%>
            <%--.on('changeDate', function(e) {--%>
              <%--startDate = new Date(e.date.getFullYear(), (e.date.getMonth()+1));--%>
              <%--$('#endDate').datepicker('setStartDate', startDate);--%>
              <%--$('#endDate').datepicker('setEndDate', new Date(startDate.getFullYear(),11,31));--%>

            <%--});--%>

    <%--$('#endDate').datepicker({--%>
      <%--minViewMode: 1,--%>
      <%--autoclose: true--%>
    <%--}).next().on(ace.click_event, function () {--%>
      <%--$(this).prev().focus();--%>
    <%--});--%>

  <%--});--%>
<%--</script>--%>


<script type="text/javascript">

    jQuery(function ($) {
        $('#initDate').datepicker({autoclose: true}).next().on(ace.click_event, function () {
         $(this).prev().focus();
         });

        $('#initDate').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $('#endDate').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

    });
</script>
