<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/channeling/channelingCtrl.js"></script>
    <title>Proyectos</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>

        window.reportAttendance = function (channelingId) {
            var goTo = "<c:url value='/channelingManager/queryChanneling/reportAttendance.html'/>" + "?id=" + channelingId;
            window.goToUrlMvcUrl(goTo);
        };

        window.queryChannelingFiles = function (caseId) {
            var goTo = "<c:url value='/channelingManager/queryChanneling/queryChannelingFiles.html'/>" + "?id=" + caseId;
            window.goToUrlMvcUrl(goTo);
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/channelingManager/queryChanneling/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Causa penal / Carpeta Judicial', 'Imputado', 'Distrito', 'Supervisor', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idMP', index: 'idMP', width: 240, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'imputed', index: 'imputed', width: 330, align: "center", sorttype: 'string', search: false },
                    { name: 'district', index: 'district', width: 240, align: "center", sorttype: 'string', search: false },
                    { name: 'supervisor', index: 'supervisor', width: 220, align: "center", sorttype: 'string', search: false },
                    { name: 'Action', width: 100, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
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
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var row = $(this).getRowData(cl);
                        var be = "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Consultar archivos del caso\" onclick=\"window.queryChannelingFiles('" + cl + "');\"><span class=\"glyphicon glyphicon-cloud-download\"></span></a>";
                        $(this).jqGrid('setRowData', ids[i], { Action: be });
                    }
                },
                loadComplete: function () {
                    var table = this;
                    setTimeout(function () {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },
                subGridOptions: {
                    plusicon: "glyphicon glyphicon-chevron-down position-relative",
                    minusicon: "glyphicon glyphicon-chevron-right position-relative",
                    // openicon  : "ui-icon-arrowreturn-1-e",
                    reloadOnExpand: false,
                    selectOnExpand: true
                },
                subGrid: true,
                subGridRowExpanded: function (subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid({
                        url: '<c:url value='/channelingManager/queryChanneling/listChannelings.json?id=' />' + row_id,
                        autoencode:true,
                        datatype: "json",
                        mtype: 'POST',
                        colNames: ['ID','Puede dar baja', '#', 'Tipo de canalizaci&oacute;n', 'Nombre de canalizaci&oacute;n', 'Tipo de instituci&oacute;n', 'Nombre de instituci&oacute;n', 'Imposici&oacute;n cumplida', 'Acci&oacute;n'],
                        colModel: [
                            { name: 'id', index: 'id', hidden: true },
                            { name: 'canDrop', index: 'canDrop', hidden: true },
                            { name: 'consecutiveTx', index: 'consecutiveTx', width: 90, align: "center", sorttype: 'string', search: false },
                            { name: 'channelingType', index: 'channelingType', width: 170, align: "center", sorttype: 'string', search: false },
                            { name: 'name', index: 'name', width: 180, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                            { name: 'institutionType', index: 'institutionType', width: 165, align: "center", sorttype: 'string', search: false },
                            { name: 'institutionName', index: 'institutionName', width: 165, align: "center", sorttype: 'string', search: false },
                            { name: 'isFulfilledTx', index: 'isFulfilledTx', width: 150, align: "center", sorttype: 'string', search: false },
                            { name: 'Action', width: 100, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
                        ],
                        rowNum: 20,
                        pager: pager_id,
                        sortname: 'id',
                        sortorder: "asc",
                        height: '100%',
                        gridComplete: function () {
                            var ids = $(this).jqGrid('getDataIDs');
                            for (var i = 0; i < ids.length; i++) {
                                var cl = ids[i];
                                var row = $(this).getRowData(cl);
                                var be = "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Consultar actividades de la canalizaci&oacute;n\" onclick=\"window.reportAttendance('" + cl + "');\"><span class=\"glyphicon glyphicon-list-alt\"></span></a>";
                                $(this).jqGrid('setRowData', ids[i], { Action: be });
                            }
                        },
                        loadComplete: function () {
                            var table = this;
                            setTimeout(function () {
                                updatePagerIcons(table);
                                enableTooltips(table);
                            }, 0);
                        }
                    });
                    $("#" + subgrid_table_id).jqGrid('navGrid', "#" + pager_id, {
                        edit: false,
                        add: false,
                        refresh: true, refreshicon: 'icon-refresh green',
                        del: false,
                        search: false
                    });
                }
            });

            jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: false,
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false});

            jQuery("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>

    <h2 class="element-center"><i class="glyphicon glyphicon-search"></i>
        &nbsp;&nbsp;Consulta de las canalizaciones</h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
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