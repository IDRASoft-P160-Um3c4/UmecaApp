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
        window.upsert = function (id, channelingId, gridId) {
            window.showUpsertParams({id: id, channelingId: channelingId}, "#angJsjqGridId", '<c:url value='/supervisor/channeling/upsert.html' />', gridId);
        };

        window.obsolete = function (id, channelingId, gridId) {
            window.showObsoleteParams({id: id, channelingId: channelingId}, "#angJsjqGridId", '<c:url value='/supervisor/channeling/doObsolete.json' />', gridId);
        };

        window.printSheet = function (channelingId) {
            var goTo = "<c:url value='/supervisor/channeling/printSheet.html'/>" + "?id=" + channelingId;
            window.goToUrlMvcUrl(goTo);
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/supervisor/channeling/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Causa penal / Carpeta Judicial', 'Imputado', 'Distrito', 'Supervisor', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idMP', index: 'idMP', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'imputed', index: 'imputed', width: 320, align: "center", sorttype: 'string', search: false },
                    { name: 'district', index: 'district', width: 250, align: "center", sorttype: 'string', search: false },
                    { name: 'supervisor', index: 'supervisor', width: 180, align: "center", sorttype: 'string', search: false },
                    { name: 'Action', width: 110, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
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

                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Registrar canalizaci&oacute;n\" onclick=\"window.upsert('" + cl + "', undefined, '#GridId');\"><span class=\"glyphicon glyphicon-plus\"></span></a>";
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
                        url: '<c:url value='/supervisor/channeling/listChannelings.json?id=' />' + row_id,
                        autoencode:true,
                        datatype: "json",
                        mtype: 'POST',
                        colNames: ['ID', '#', 'Tipo de canalizaci&oacute;n', 'Nombre de canalizaci&oacute;n', 'Tipo de instituci&oacute;n', 'Nombre de instituci&oacute;n', 'Acci&oacute;n'],
                        colModel: [
                            { name: 'id', index: 'id', hidden: true },
                            { name: 'consecutiveTx', index: 'consecutiveTx', width: 100, align: "center", sorttype: 'string', search: false },
                            { name: 'channelingType', index: 'channelingType', width: 200, align: "center", sorttype: 'string', search: false },
                            { name: 'name', index: 'name', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                            { name: 'institutionType', index: 'institutionType', width: 200, align: "center", sorttype: 'string', search: false },
                            { name: 'institutionName', index: 'institutionName', width: 200, align: "center", sorttype: 'string', search: false },
                            { name: 'Action', width: 110, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
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
                                var be = "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar/consultar canalizaci&oacute;n\" onclick=\"window.upsert('" + row_id + "', '" + cl + "', '" + "#" + subgrid_table_id + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar oficio\" onclick=\"window.printSheet('" + cl + "');\"><span class=\"glyphicon glyphicon-download\"></span></a>";
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar canalizaci&oacute;n\" onclick=\"window.obsolete('" + row_id + "', '" + cl + "', '" + "#" + subgrid_table_id + "');\"><span class=\"glyphicon glyphicon-remove\"></span></a>";
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

    <h2 class="element-center"><i class="glyphicon glyphicon-screenshot"></i>
        &nbsp;&nbsp;Proceso para canalizaciones</h2>

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