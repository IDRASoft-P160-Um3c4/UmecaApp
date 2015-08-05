<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/channeling/authRejChannelingDropCtrl.js"></script>
    <title>Proyectos</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.authRejChannelingDrop = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/channelingInfo/authRejChannelingDrop.html' />', "#GridId");
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/supervisorManager/channelingInfo/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Causa penal / Carpeta Judicial', 'Imputado', 'Distrito', 'Fecha solicitud', 'Canalizaci&oacute;n', 'Tipo canalizaci&oacute;n', 'Supervisor', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idMp', index: 'idMp', width: 230, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'imputed', index: 'imputed', width: 200, align: "center", sorttype: 'string', search: false },
                    { name: 'district', index: 'district', width: 120, align: "center", sorttype: 'string', search: false },
                    { name: 'creationDateTx', index: 'creationDateTx', width: 140, align: "center", sorttype: 'string', search: false },
                    { name: 'channelingName', index: 'channelingName', width: 120, align: "center", sorttype: 'string', search: false },
                    { name: 'channelingTypeName', index: 'channelingTypeName', width: 150, align: "center", sorttype: 'string', search: false },
                    { name: 'supervisor', index: 'supervisor', width: 150, align: "center", sorttype: 'string', search: false },
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

                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Revisar solicitud de baja de canalizaci&oacute;n\" onclick=\"window.authRejChannelingDrop('" + cl + "');\"><span class=\"glyphicon glyphicon-list-alt\"></span></a>";
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

    <h2 class="element-center"><i class="glyphicon glyphicon-check"></i>
        &nbsp;&nbsp;Lista de canalizaciones con solicitud de baja</h2>

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