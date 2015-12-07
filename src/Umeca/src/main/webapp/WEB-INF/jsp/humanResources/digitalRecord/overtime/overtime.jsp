<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>

<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>

<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>

<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>


<script src="${pageContext.request.contextPath}/assets/scripts/app/managereval/statisticReportCtrl.js"></script>

<script>
    $(document).ready(function () {

        showOvertime = function (initDate, endDate, idEmployee) {
            window.showUpsertParams({initDate: initDate, endDate: endDate, idEmployee: idEmployee}, "#angJsjqGridVacation", "<c:url value='/humanResources/digitalRecord/getOverTime.html'/>", undefined, undefined);
        };
    });

</script>

<div class="row element-center">



    <div class="col-xs-12">
        <h2><i class="pink icon-briefcase bigger-100">&nbsp;</i>Tiempo extra</h2>
        <br/>


        <div class="container body-content col-xs-12 col-xs-offset-1">

            <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

            <form id="FormStatisRep" name="FormStatisRep" class="form-horizontal"
                  role="form" ng-controller="statisticReportController" method="post"
                  ng-cloak>

                <input type="hidden" id="filterSelected" name="filterSelected" value="{{m.filterSelected}}">

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

                                                <div class="col-xs-3 col-xs-offset-1">
                                                    <label for="initDate">Fecha inicio</label>
                                                    <br/>
                                                    <small>(A&ntilde;o/Mes) Ej. (2015/01)</small>
                                                    <div class="row">
                                                        <div class="input-group">
                                                            <input id="initDate" name="initDate"
                                                                   ng-model="initDate"
                                                                   class="form-control date-picker"
                                                                   type="text"
                                                                   data-date-format="yyyy/mm"
                                                                   data-val="true"
                                                                   ng-change="resetDate();"
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
                                                    <small>(A&ntilde;o/Mes) Ej. (2015/01)</small>
                                                    <div class="row">
                                                        <div class="input-group">
                                                            <input id="endDate" name="endDate"
                                                                   class="form-control date-picker"
                                                                   type="text"
                                                                   data-date-format="yyyy/mm"
                                                                   data-val="true"
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

                                                <div class="col-xs-3 col-xs-offset-1">
                                                    <br/>
                                                    <br/>
                                                    <div class="col-xs-11 element-center">
                                                        <span class="btn btn-default btn-primary btn-sm"  ng-click="findHumanResourceOvertimeReport('#FormStatisRep','<c:url value='/humanResources/digitalRecord/getOverTime.html'/>',  ${idEmployee});">
                                                            Realizar b&uacute;squeda
                                                        </span>
                                                    </div>

                                                </div>
                                                <br/>
                                            </div>
                                            <br/>
                                        </div>
                                    </div>

                                    <div class="row" ng-show="msgError">
                                        <div class="col-xs-8 col-xs-offset-2 alert alert-danger element-center">
                                            <span class="control-label element-center">{{msgError}}</span>
                                        </div>
                                    </div>
                                    <br/>


                                </div>
                            </div>
                            <br/>
                        </div>


                    </div>
                </div>
                <br/>
            </form>

        </div>

        <div id="angJsjqGridVacation" ng-controller="modalDlgController">
            <table id="GridVacation" class="element-center" style="margin: auto"></table>
            <div id="GridPagerVacation"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript">

    var startDate = new Date('01/01/2012');
    var FromEndDate = new Date();
    var ToEndDate = new Date();

    jQuery(function ($) {

        $('#initDate').datepicker({
            minViewMode: 1,
            autoclose: true,
            endDate: new Date()
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        })


        $('#initDate').datepicker()
                .on('changeDate', function (e) {
                    startDate = new Date(e.date.getFullYear(), (e.date.getMonth() + 1));
                    $('#endDate').datepicker('setStartDate', startDate);
                    $('#endDate').datepicker('setEndDate', new Date(startDate.getFullYear(), 11, 31));

                });

        $('#endDate').datepicker({
            minViewMode: 1,
            autoclose: true
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

    });
</script>