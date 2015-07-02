<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/minute/requestFinishCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/minute/authRejRequestFinishCtrl.js"></script>
    <title>Personal UMECA</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        $(document).ready(function () {

            var isRH = ${isRH};
            var isDir = ${isDir};

            upsertMinute = function () {
                if (isRH == true) {
                    var url = "<c:url value='/shared/minute/upsertMinute.html'/>";
                    window.goToUrlMvcUrl(url);
                }
            };

            editMinute = function (id) {
                var url = "<c:url value='/shared/minute/upsertMinute.html?id='/>" + id;
                window.goToUrlMvcUrl(url);
            };

            summaryMinute = function (id) {
                if (isRH == true || isDir == true) {
                    var url = "<c:url value='/shared/minute/summaryMinute.html?id='/>" + id;
                    window.goToUrlMvcUrl(url);
                }
            };

            showFinishRequest = function (id) {
                if (isRH == true)
                    window.showUpsert(id, "#angJsjqGridId", "<c:url value='/shared/minute/finishRequestMinute.html'/>", "#GridMinuteId");
            };

            showFinishResponse = function (id) {
                if (isRH == true)
                    window.showUpsert(id, "#angJsjqGridId", "<c:url value='/shared/minute/responseFinishRequest.html'/>", "#GridMinuteId");
            };

            autRejFinishRequestMinute = function (id) {
                if (isDir == true)
                    window.showUpsert(id, "#angJsjqGridId", "<c:url value='/shared/minute/authRejFinishRequest.html'/>", "#GridMinuteId");
            };

            jQuery("#GridMinuteId").jqGrid({
                url: '<c:url value='/shared/minute/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'isFinished', 'stCode', 'Fecha', 'Hora', 'Lugar', 'Encargado', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'isFinished', index: 'isFinished', hidden: true},
                    {name: 'stCode', index: 'stCode', hidden: true},
                    {
                        name: 'minuteDate',
                        index: 'minuteDate',
                        width: 120,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'startTime',
                        index: 'startTime',
                        width: 120,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'place',
                        index: 'place',
                        align: "center",
                        width: 300,
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'attendant',
                        index: 'attendant',
                        align: "center",
                        width: 220,
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'Action',
                        index: 'Action',
                        width: 110,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
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
                    var finished = $(this).jqGrid('getCol', 'isFinished', false);
                    var status = $(this).jqGrid('getCol', 'stCode', false);
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var be = "";
                        if (finished[i] == 'false') {
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar minuta\" onclick=\"editMinute(" + cl + ");\"><span class=\"glyphicon glyphicon-list\"></span></a>";
                            if (isRH) {
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Cerrar minuta\" onclick=\"showFinishRequest('" + cl + "');\"><span class=\"glyphicon glyphicon-lock\"></span></a>";

                                if (status[i] == 'FINISH_REJECT')
                                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Ver respuesta de solicitud de conclusi&oacute;n\" onclick=\"showFinishResponse('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                            }

                            if (isDir == true && status[i] == 'PENDENT_FINISH_REQUEST') {
                                be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Autorizar/Rechazar concluir minuta\" onclick=\"autRejFinishRequestMinute('" + cl + "');\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>";
                            }
                        } else {
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Consultar minuta\" onclick=\"editMinute(" + cl + ");\"><span class=\"glyphicon glyphicon-search\"></span></a>";
                        }
                        if (isRH == true || isDir == true)
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar documento de minuta\" onclick=\"summaryMinute('" + cl + "');\"><span class=\"glyphicon glyphicon-file\"></span></a>";
                        $(this).jqGrid('setRowData', ids[i], {Action: be});
                    }
                },
                subGridOptions: {
                    plusicon: "glyphicon glyphicon-chevron-down position-relative",
                    minusicon: "glyphicon glyphicon-chevron-right position-relative",
                    reloadOnExpand: false,
                    selectOnExpand: true
                },
                subGrid: true,
                loadComplete: function () {
                    var table = this;
                    setTimeout(function () {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },
                subGridRowExpanded: function (subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid({
                        url: '<c:url value='/shared/agreement/list.json?id=' />' + row_id,
                        autoencode: true,
                        datatype: "json",
                        mtype: 'POST',
                        colNames: ['Acuerdo', 'Estado', 'Concluido'],
                        colModel: [
                            {
                                name: 'title',
                                index: 'title',
                                sorttype: 'string',
                                width: 400,
                                align: "center",
                                searchoptions: {sopt: ['bw']}
                            },
                            {
                                name: 'isDoneStr',
                                index: 'isDoneStr',
                                width: 150,
                                align: "center",
                                sortable: false,
                                search: false
                            },
                            {
                                name: 'isFinishedStr',
                                index: 'isFinishedStr',
                                width: 150,
                                align: "center",
                                sortable: false,
                                search: false
                            }
                        ],
                        rowNum: 20,
                        pager: pager_id,
                        sortname: 'id',
                        sortorder: "asc",
                        height: '100%',
                        loadComplete: function () {
                            var table = this;
                            setTimeout(function () {
                                updatePagerIcons(table);
                                enableTooltips(table);
                            }, 0);
                        }
                    });
                    $("#" + subgrid_table_id).jqGrid('navGrid', "#" + pager_id, {edit: false, add: false, del: false})
                }
            });

            jQuery("#GridMinuteId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: true, addfunc: upsertMinute, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridMinuteId").jqGrid('navSeparatorAdd', '#GridPager');
            jQuery("#GridMinuteId").jqGrid('navButtonAdd', "#GridPager",
                    {
                        caption: "",
                        title: "Exportar a excel",
                        buttonicon: 'icon-download-alt blue',

                        onClickButton: function () {
                            try {
                                $("#GridMinuteId").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                            } catch (e) {
                            }
                        }
                    });

            jQuery("#GridMinuteId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        })
        ;

    </script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Minutas abiertas
    </h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridMinuteId" class="element-center" style="margin: auto"></table>
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