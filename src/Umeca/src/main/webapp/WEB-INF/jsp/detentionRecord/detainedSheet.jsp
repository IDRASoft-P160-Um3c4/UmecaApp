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
    <script src="${pageContext.request.contextPath}/assets/scripts/app/detentionRecord/detentionRecordCtrl.js"></script>

    <title>Casos para entrevista de encuadre</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        $(document).ready(function () {

            var showAction =${showAction};
            var showProsecute =${showProsecute};
            var _48hrsMil = 172800000;
            var _1hourMil = 3600000;
            var _1minMil = 60000;

            //

            //milisegundos en 48 horas
//            calcTerm(date[i], time[i], now[i], this,ids[i],isPro[i]);

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

                        totDue = hrsDue + " hrs. " + minDue + " min.";
                        totLeft = hrsLeft + " hrs. " + minLeft + " min.";
                    } else {
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

            if (showAction) {
                alert("aa");
                setInterval(function () {
                    $("#GridIdDetained").trigger("reloadGrid");
//                }, 180000);
                }, 5000);
            }

            upsertDetained = function (id) {
                window.showUpsert(null, "#angJsjqGridIdDetained", "<c:url value='/detentionRecord/upsertDetention.html'/>", "#GridIdDetained");
            };

            doProsecute = function (id) {
                window.showUpsert(id, "#angJsjqGridIdDetained", "<c:url value='/detentionRecord/upsertProsecute.html'/>", "#GridIdDetained");
            };

            jQuery("#GridIdDetained").jqGrid({
                url: '<c:url value="/detentionRecord/detainedSheet/list.json"/>',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'isProsecute', 'nowL', 'initDateL', 'initTimeL', 'Imputado', 'Carpeta de <br/> Investigaci&oacute;n', 'Fecha inicio', 'Hora inicio', 'Unidad de <br/>Investigaci&oacute;n',
                    'Presentado por', 'Distrito', 'T&eacute;rmino', 'Tiempo para <br/> cumplir t&eacute;rmino', 'Judicializado', 'Accion'],
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
                        name: 'idFolder',
                        index: 'idFolder',
                        sorttype: 'string',
                        width: 120,
                        align: "center",
                        search: false
                    },
                    {
                        name: 'initDateStr',
                        index: 'initDateStr',
                        sorttype: 'string',
                        width: 90,
                        align: "center",
                        search: false
                    },
                    {
                        name: 'initTimeStr',
                        index: 'initTimeStr',
                        width: 90,
                        align: "center",
                        sorttype: 'string',
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
                        width: 120,
                        align: "center",
                        sortable: false,
                        search: false
                    },
                    {
                        name: 'timeLeft',
                        index: 'timeLeft',
                        width: 100,
                        align: "center",
                        sortable: false,
                        search: false
                    },

                    {
                        name: 'prosecute',
                        index: 'prosecute',
                        width: 90,
                        align: "center",
                        sortable: false,
                        hidden: !showProsecute
                    },
                    {
                        name: 'Action',
                        width: 70,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter,
                        hidden: !showAction
                    },
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'id',
                height: 350,
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
                        var cl = ids[i];
                        var be = "";
                        be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Indicar judicializaci&oacute;n\" onclick=\"doProsecute('" + cl + "');\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>";
                        $(this).jqGrid('setRowData', ids[i], {Action: be});
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
                add: !showAction, addfunc: upsertDetained, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
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