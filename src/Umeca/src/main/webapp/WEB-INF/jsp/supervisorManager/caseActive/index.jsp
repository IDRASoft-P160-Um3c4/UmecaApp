<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/authorizeMonitoringPlan/authRejectCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/enterKeyDrct.js"></script>
    <title>Casos activos</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>

        window.authCloseCase = function(id){
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/caseActive/authClose.html' />', "#GridId");
        };

        window.rejectCloseCase = function(id){
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/caseActive/rejectClose.html' />', "#GridId");
        };

        window.authObsoleteCase = function(id){
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/caseActive/authClose.html?authObs=1' />', "#GridId");
        };

        window.rejectObsoleteCase = function(id){
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/caseActive/rejectClose.html?authObs=1' />', "#GridId");
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/supervisorManager/caseActive/listB.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'idStatus', 'Carpeta Judicial', 'Nombre completo', 'Fecha de nacimiento', 'Estatus', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'codeStatus', index: 'codeStatus', hidden: true },
                    { name: 'idMP', index: 'idMP', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['lk'] } },
                    { name: 'fullName', index: 'fullName', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['lk'] } },
                    { name: 'brthDateTxt', index: 'brthDateTxt', width: 160, align: "center", sorttype: 'string', searchoptions: { sopt: ['lk'] } },
                    { name: 'descStatus', index: 'descStatus', width: 250, align: "center", sortable: false, search: false },
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'idMP',
                height: 450,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "desc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    var status = $(this).jqGrid('getCol', 'codeStatus', false);
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var be = "";
                        switch (status[i]) {
                            case 'ST_CASE_REQUEST_SUPERVISION':
                                be = "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Autorizar la eliminaci&oacute;n del caso\" onclick=\"window.authObsoleteCase('" + cl + "');\"><span class=\"glyphicon glyphicon-check\"></span></a>";
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Rechazar la eliminaci&oacute;n del caso\" onclick=\"window.rejectObsoleteCase('" + cl + "');\"><span class=\"glyphicon glyphicon-remove color-danger\"></span></a>";
                                break
                            case 'ST_CASE_CLOSE_REQUEST':
                                be = "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Autorizar el cierre del caso\" onclick=\"window.authCloseCase('" + cl + "');\"><span class=\"glyphicon glyphicon-check\"></span></a>";
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Rechazar el cierre del caso\" onclick=\"window.rejectCloseCase('" + cl + "');\"><span class=\"glyphicon glyphicon-remove color-danger\"></span></a>";
                                break;
                        }
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

            jQuery("#GridId").jqGrid('navSeparatorAdd', '#GridPager');
            jQuery("#GridId").jqGrid('navButtonAdd', "#GridPager",
                    {
                        caption: "",
                        title: "Exportar a excel",
                        buttonicon: 'icon-download-alt blue',

                        onClickButton: function () {
                            try {
                                $("#GridId").jqGrid('toExcelFile',{nombre:"datosXls",formato:"excel"});
                            } catch (e) {
                            }
                        }});

            jQuery("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Casos activos
    </h2>

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