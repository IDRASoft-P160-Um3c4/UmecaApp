<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/imputedMissedAttendance/imputedMissedAttendanceCtrl.js"></script>

    <title>Imputados que se presentaron sin cita</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>

        var listIds = "[]";

        function reloadDetainedGrid(returnIds) {
            listIds = returnIds;
            $("#GridId").setGridParam({postData: {ids: listIds}});
            $("#GridId").trigger("reloadGrid");
        }


        $(document).ready(function () {

            jQuery("#GridId").jqGrid({
                url: '<c:url value="/supervisor/imputedMissedAttendance/list.json"/>',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                postData: {
                    ids: listIds
                },
                colNames: ['Id', 'Nombre', 'Apellido paterno', 'Apellido materno', 'Fecha'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'name',
                        index: 'name',
                        sorttype: 'string',
                        width: 150,
                        align: "center",
                        search: false
                    },
                    {
                        name: 'lastNameP',
                        index: 'lastNameP',
                        sorttype: 'string',
                        width: 150,
                        align: "center",
                        search: false
                    },
                    {
                        name: 'lastNameM',
                        index: 'lastNameM',
                        sorttype: 'string',
                        width: 150,
                        align: "center",
                        search: false
                    },
                    {
                        name: 'dateStr',
                        index: 'dateStr',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'id',
                height: 450,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "desc",
                caption: "&nbsp;",
                altRows: true,
                loadComplete: function () {
                    var table = this;
                    setTimeout(function () {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                }
            });

            jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: false,
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridId").jqGrid('navButtonAdd', "#GridPager", {
                caption: "",
                title: "Exportar a excel",
                buttonicon: 'icon-download-alt red',

                onClickButton: function () {
                    try {
                        $("#GridId").jqGrid('toExcelFile',{nombre:"datosXls",formato:"excel"});
                    } catch (e) {
                    }
                }
            });

            jQuery("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });


        });
    </script>

    <h2 class="element-center"><i class="glyphicon glyphicon-user "></i> Imputados que se presentaron sin cita
    </h2>


    <form id="FormRepExcel" name="FormRepExcel" class="form-horizontal"
          role="form" ng-controller="imputedMissedAttendanceController" method="post" ng-cloak>

        <div class="row">
            <div class="col-xs-10 col-xs-offset-1">

                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-2 col-xs-offset-3">
                                <label for="initDate">Fecha inicial</label>
                                <br/>
                                <small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/01)</small>
                                <div class="row">
                                    <div class="input-group">
                                        <input id="initDate" name="initDateStr"
                                               ng-model="initDate"
                                               class="form-control date-picker"
                                               type="text"
                                               data-date-format="yyyy/mm/dd" data-val="true"
                                               data-val-required="La fecha inicial es un campo requerido"/>
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                    </div>
                                <span class="field-validation-valid"
                                      data-valmsg-for="initDateStr"
                                      data-valmsg-replace="true">
                                </span>
                                </div>
                            </div>

                            <div class="col-xs-2 col-xs-offset-2">
                                <label for="endDate">Fecha final</label>
                                <br/>
                                <small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/30)</small>
                                <div class="row">
                                    <div class="input-group">
                                        <input id="endDate" name="endDateStr"
                                               class="form-control date-picker" type="text"
                                               data-date-format="yyyy/mm/dd" data-val="true"
                                               ng-model="endDate"
                                               data-val-required="La fecha final es un campo requerido"/>
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                    </div>
                                <span class="field-validation-valid"
                                      data-valmsg-for="endDateStr"
                                      data-valmsg-replace="true">
                                </span>
                                </div>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 element-center">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-click="submitFindRecords('#FormRepExcel','<c:url value='/supervisor/imputedMissedAttendance/findRecords.json'/>');">
                      Realizar b&uacute;squeda
                    </span>
                    </div>
                </div>
                <br/>
            </div>
        </div>

    </form>


    <div id="angJsjqGridIdDetained" ng-controller="modalDlgController">


        <table id="GridId" class="element-center" style="margin: auto"></table>
        <div id="GridPager"></div>
        <div class="blocker" ng-show="working">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>

<script type="text/javascript">

    jQuery(function ($) {

        $('#initDate').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $('#endDate').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

    });
</script>