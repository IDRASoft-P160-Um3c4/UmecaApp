<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>

    <link href="${pageContext.request.contextPath}/assets/content/upload/jquery.fileupload.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.iframe-transport.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.fileupload.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/project/projectCtrl.js"></script>
    <title>Proyectos</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.upsert = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/director/project/upsert.html' />', "#GridId");
        };

        window.endProject = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/director/project/endProject.html' />', "#GridId");
        };

        window.upsertActivity = function(projectId, id, gridId){
            window.showUpsertParams({projectId: projectId, id: id }, "#angJsjqGridId", '<c:url value='/director/project/upsertActivity.html' />', gridId);
        };

        window.obsolete = function (id) {
            window.showObsolete(id, "#angJsjqGridId", '<c:url value='/director/project/doObsolete.json' />', "#GridId");
        };

        window.obsoleteActivity = function (id, gridId) {
            window.showObsolete(id, "#angJsjqGridId", '<c:url value='/director/project/doObsoleteActivity.json' />', gridId);
        };

        window.download = function (id) {
            var goTo = "<c:url value='/shared/uploadFileGeneric/downloadFile.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };


        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/director/project/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Proyecto', 'Fecha de creaci&oacute;n', 'Estatus', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'name', index: 'name', width: 450, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'stCreationDate', index: 'stCreationDate', width: 150, align: "center", sorttype: 'string', search: false },
                    { name: 'status', index: 'status', width: 150, align: "center", sorttype: 'string', search: false },
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

                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar proyecto\" onclick=\"window.upsert('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        if(row.status !== "Finalizado")
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Finalizar proyecto\" onclick=\"window.endProject('" + cl + "');\"><span class=\"glyphicon glyphicon-log-out\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar proyecto\" onclick=\"window.obsolete('" + cl + "');\"><span class=\"glyphicon glyphicon-remove\"></span></a>";

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
                        url: '<c:url value='/director/project/listActivities.json?id=' />' + row_id,
                        autoencode:true,
                        datatype: "json",
                        mtype: 'POST',
                        colNames: ['ID', 'Actividad', 'Descripci&oacute;n', 'Fecha de creaci&oacute;n', 'ID Archivo', 'Acci&oacute;n'],
                        colModel: [
                            { name: 'id', index: 'id', hidden: true },
                            { name: 'name', index: 'name', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                            { name: 'description', index: 'description', width: 300, align: "center", sorttype: 'string', search: false },
                            { name: 'stCreationDate', index: 'stCreationDate', width: 150, align: "center", sorttype: 'string', search: false },
                            { name: 'fileId', index: 'fileId', hidden: true },
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
                                var row = $(this).getRowData(cl);

                                var be = "";

                                if(row.fileId > 0)
                                    be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar archivo de la actividad\" onclick=\"window.download('" + row.fileId + "');\"><i class=\"glyphicon glyphicon-cloud-download\"></i></a>  ";

                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar actividad del proyecto\" onclick=\"window.obsoleteActivity('" + cl + "', '#" + subgrid_table_id + "');\"><span class=\"glyphicon glyphicon-remove\"></span></a>";
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
                        add: true, addfunc:
                                function(id) {
                                    window.upsertActivity(row_id, id, "#" + subgrid_table_id);
                                },
                        addicon: 'icon-plus-sign purple',
                        refresh: true, refreshicon: 'icon-refresh green',
                        del: false,
                        search: false
                    });
                }
            });

            jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: true, addfunc: window.upsert, addicon: 'icon-plus-sign purple',
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

    <h2 class="element-center"><i class="glyphicon glyphicon-tags"></i>
        &nbsp;&nbsp;Proyectos</h2>

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