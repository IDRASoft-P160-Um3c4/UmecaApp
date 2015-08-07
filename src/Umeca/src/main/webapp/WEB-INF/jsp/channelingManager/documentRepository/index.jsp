<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <link href="${pageContext.request.contextPath}/assets/content/upload/jquery.fileupload.css" rel="stylesheet"
          type="text/css">

    <script src="${pageContext.request.contextPath}/assets/scripts/upload/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.iframe-transport.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.fileupload.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/activityReport/uploadActivityReport.js"></script>
    <title>Repositorio de documentos y plantillas para canalizaciones</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.upsert = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/channelingManager/documentRepository/upsert.html' />', "#GridId");
        };

        window.download = function (id) {
            var goTo = "<c:url value='/shared/uploadFileGeneric/downloadFile.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };

        window.delete = function (id) {
            window.showAction(id, "#angJsjqGridId", '<c:url value='/shared/activityReport/delete.json' />', "#GridId", "Eliminar documento", "&iquest;Desea eliminar el documento elegido?", "danger");
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/channelingManager/documentRepository/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'fileId', 'Nombre', 'Descripci&oacute;n', 'Fecha de subida', 'Archivo', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'fileId', index: 'fileId', hidden: true},
                    {
                        name: 'reportName',
                        index: 'reportName',
                        width: 280,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'description',
                        index: 'description',
                        width: 300,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'stCreationDate',
                        index: 'stCreationDate',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'fileName',
                        index: 'fileName',
                        width: 220,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'Action',
                        width: 100,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
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
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    var fId = $(this).jqGrid('getCol', 'fileId', false);
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var f_id = fId[i];
                        var be = "";
                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar documento\" onclick=\"window.download('" + f_id + "');\"><i class=\"glyphicon glyphicon-cloud-download\"></i></a>  ";
                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar documento\" onclick=\"window.delete('" + cl + "');\"><i class=\"icon icon-trash\"></i></a>  ";

                        $(this).jqGrid('setRowData', ids[i], {Action: be});
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

            jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false, editicon: 'icon-pencil blue',
                add: true, addfunc: window.upsert, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
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

    <h2 class="element-center"><i class="glyphicon glyphicon-th-list"></i>
        &nbsp;&nbsp;Repositorio de documentos y plantillas para canalizaciones</h2>

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