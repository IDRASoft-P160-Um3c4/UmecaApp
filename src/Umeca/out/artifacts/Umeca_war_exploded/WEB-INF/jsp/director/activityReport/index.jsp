<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <link href="${pageContext.request.contextPath}/assets/content/upload/jquery.fileupload.css" rel="stylesheet" type="text/css">

    <script src="${pageContext.request.contextPath}/assets/scripts/upload/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.iframe-transport.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.fileupload.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/activityReport/uploadActivityReport.js"></script>

    <title>Informes globales de actividades</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.upsert = function(id) {
            var params= [];
            params["idParam"]=id;
            window.goToUrlMvcUrl("<c:url value='/director/activityReport/wizardUpsert.html?id=idParam'/>",params);
        };

        window.downloadFiles = function(id) {
            var params= [];
            params["idParam"]=id;
            window.goToNewUrl("<c:url value='/director/activityReport/downloadFiles.html?id=idParam'/>",params);
        };

        window.delete = function (id) {
            window.showObsolete(id, "#angJsjqGridId", '<c:url value='/director/activityReport/doObsolete.json' />', "#GridId");
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/director/activityReport/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Fecha del reporte', 'Nombre', 'Descripci&oacute;n', 'Fecha de creaci&oacute;n', 'Usuario', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'stReportDate', index: 'stReportDate', width: 150, align: "center", sorttype: 'string', search: false },
                    { name: 'reportName', index: 'reportName', width: 250, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'reportDescription', index: 'reportDescription', width: 400, align: "center", sorttype: 'string', search: false },
                    { name: 'stCreationDate', index: 'stCreationDate', width: 150, align: "center", sorttype: 'string', search: false },
                    { name: 'creatorUserName', index: 'creatorUserName', width: 150, align: "center", sorttype: 'string', search: false },
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
                        var status = parseInt(row.status);
                        var be = "";

                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar el informe de actividades\" onclick=\"window.downloadFiles('" + cl + "');\"><i class=\"glyphicon glyphicon-cloud-download\"></i></a>  ";
                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar el informe de actividades\" onclick=\"window.delete('" + cl + "');\"><i class=\"icon icon-trash\"></i></a>  ";

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

            jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false, editicon: 'icon-pencil blue',
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

    <h2 class="element-center"><i class="glyphicon glyphicon-stats"></i>
        &nbsp;&nbsp;Informes globales de actividades</h2>

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