<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>
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
    <script src="${pageContext.request.contextPath}/assets/scripts/app/detentionRecordManager/detentionRecordManagerCtrl.js"></script>

    <title>Casos para entrevista de encuadre</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>

        var listIds = "[]";

        function reloadDetainedGrid(returnIds) {
            listIds = returnIds;
            $("#GridIdDetained").setGridParam({postData: {ids: listIds}});
            $("#GridIdDetained").trigger("reloadGrid");
        }



        $(document).ready(function () {

            var rol = "${rol}";

            var _48hrsMil = 172800000;
            var _1hourMil = 3600000;
            var _1minMil = 60000;

            calcTerm = function (dateMil, timeMil, now, obj, id, prosecute) {

                var totDue = "";
                var totLeft = "";
                if (prosecute == 'false') {
                    var milNow = parseInt(now, 10);
                    var milDate = parseInt(dateMil, 10);
                    var milTime = parseInt(timeMil, 10);

                    var dt = new Date(milTime);
                    var dtA = new Date(milDate);

                    var lowDt = new Date(dtA.getFullYear(), dtA.getMonth(), dtA.getDate(), dt.getHours(), dt.getMinutes(), dt.getSeconds());
                    var lowL = lowDt.getTime();
                    var upL = lowL + _48hrsMil;


                    if (milNow <= upL) {

                        var dueMil = milNow - lowL;
                        var hrsDue = Math.floor(dueMil / _1hourMil);
                        var minDue = dueMil % _1hourMil;
                        minDue = Math.floor(minDue / _1minMil);

                        var leftMil = upL - milNow;
                        var hrsLeft = Math.floor(leftMil / _1hourMil);
                        var minLeft = leftMil % _1hourMil;
                        minLeft = Math.floor(minLeft / _1minMil);

                        if (hrsDue > -1 && minDue > -1)
                            totDue = hrsDue + " hrs. " + minDue + " min.";
                        if (hrsLeft > -1 && minLeft > -1)
                            totLeft = hrsLeft + " hrs. " + minLeft + " min.";
                    }
                    else {
                        totDue = "El plazo ha vencido.";
                        totLeft = "El plazo ha vencido.";
                    }
                }
                else {
                    totDue = "Judicializado.";
                    totLeft = "Judicializado.";
                }

                $(obj).jqGrid('setRowData', id, {dueTime: totDue});
                $(obj).jqGrid('setRowData', id, {timeLeft: totLeft});

            };



            jQuery("#GridIdDetained").jqGrid({
                url: '<c:url value="/detentionRecordManager/detainedSheet/list.json"/>',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                postData: {
                    ids: listIds
                },
                colNames: ['ID', 'isProsecute', 'nowL', 'initDateL', 'initTimeL', 'Imputado', 'Edad', 'Carpeta de <br/> Investigaci&oacute;n', 'Fecha inicio', 'Hora inicio', 'Unidad de <br/>Investigaci&oacute;n',
                    'Presentado por', 'Distrito', 'T&eacute;rmino', 'Tiempo para <br/> cumplir t&eacute;rmino'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'isProsecute', index: 'isProsecute', hidden: true},
                    {name: 'nowL', index: 'nowL', hidden: true},
                    {name: 'initDateL', index: 'initDateL', hidden: true},
                    {name: 'initTimeL', index: 'initTimeL', hidden: true},
                    {
                        name: 'fullName',
                        index: 'fullName',
                        sorttype: 'string',
                        width: 200,
                        align: "center",
                        search: false
                    },
                    {
                        name: 'age',
                        index: 'age',
                        sortable: false,
                        width: 80,
                        align: "center",
                        search: false
                    },
                    {
                        name: 'idFolder',
                        index: 'idFolder',
                        sorttype: 'string',
                        width: 120,
                        align: "center",
                        search: false
                    },
                    {
                        name: 'initDateStr',
                        index: 'initTimestamp',
                        width: 90,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'initTimeStr',
                        index: 'initTimeStr',
                        width: 90,
                        align: "center",
                        sortable: false,
                        search: false
                    },
                    {
                        name: 'investigationUnit',
                        index: 'investigationUnit',
                        width: 120,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'crime',
                        index: 'crime',
                        width: 120,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'district',
                        index: 'district',
                        width: 110,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'dueTime',
                        index: 'dueTime',
                        width: 125,
                        align: "center",
                        sortable: false,
                        search: false
                    },
                    {
                        name: 'timeLeft',
                        index: 'initTimestamp',
                        width: 125,
                        align: "center",
                        sortable: true,
                        search: false
                    },
                ],
                rowNum: 100000,
                rowList: [],
                pager: '#GridPager',
                sortname: 'id',
                height: "100%",
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "desc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');

                    var date = $(this).jqGrid('getCol', 'initDateL', false);
                    var time = $(this).jqGrid('getCol', 'initTimeL', false);
                    var now = $(this).jqGrid('getCol', 'nowL', false);
                    var isPro = $(this).jqGrid('getCol', 'isProsecute', false);

                    for (var i = 0; i < ids.length; i++) {
                        calcTerm(date[i], time[i], now[i], this, ids[i], isPro[i]);
                    }
                }
                ,
                loadComplete: function () {
                    var table = this;
                    setTimeout(function () {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                }
            });

            jQuery("#GridIdDetained").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: false,
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridIdDetained").jqGrid('navButtonAdd', "#GridPager", {
                caption: "",
                title: "Descargar Excel",
                buttonicon: 'icon-download-alt red',

                onClickButton: function () {
                    try {
                        $("#GridIdDetained").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                    } catch (e) {
                    }
                }
            });

            jQuery("#GridIdDetained").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });


        });
    </script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i> S&aacute;bana de detenidos
    </h2>




    <form id="FormRepExcel" name="FormRepExcel" class="form-horizontal"
          role="form" ng-controller="detentionRecordManagerController" method="post" ng-cloak>

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
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-click="submitFindRecords('#FormRepExcel','<c:url value='/detentionRecordManager/detainedSheet/findRecords.json'/>');">
                      Realizar b&uacute;squeda
                    </span>
                    </div>
                </div>
                <br/>
            </div>
        </div>

    </form>


    <div id="angJsjqGridIdDetained" ng-controller="modalDlgController">


        <table id="GridIdDetained" class="element-center" style="margin: auto"></table>
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