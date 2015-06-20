<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <%--<script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/channeling/channelingCtrl.js"></script>--%>
    <title>Seguimiento a canalizaci&oacute;n</title>
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
                url: '<c:url value='/supervisor/channelingTrack/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Carpeta Judicial', 'Imputado', 'Resoluci&oacute;n', 'Fecha inasistencia', 'Canalizaci&oacute;n', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idMP', index: 'idMP', width: 180, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'imputed', index: 'imputed', width: 310, align: "center", sorttype: 'string', search: false },
                    { name: 'determination', index: 'determination', width: 150, align: "center", sorttype: 'string', search: false },
                    { name: 'absenceDate', index: 'absenceDate', width: 180, align: "center", sorttype: 'string', search: false },
                    { name: 'channeling', index: 'channeling', width: 180, align: "center", sorttype: 'string', search: false },
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

                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Justificar inasistenca\" onclick=\"window.upsert('" + cl + ");\"><span class=\"glyphicon glyphicon-check\"></span></a>";
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

    <h2 class="element-center"><i class="glyphicon glyphicon-hand-right"></i>
        &nbsp;&nbsp;Seguimiento a canalizaci&oacute;n</h2>

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