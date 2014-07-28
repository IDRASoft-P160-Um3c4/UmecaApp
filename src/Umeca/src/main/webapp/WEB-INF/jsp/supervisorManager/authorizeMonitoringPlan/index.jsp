<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/authorizeMonitoringPlan/authRejectCtrl.js"></script>
    <title>Planes de seguimiento</title>
</head>
<body scroll="no" ng-app="ptlUmc">
    <%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

    <div class="container body-content">

        <script>
            window.showCalendar = function(id) {
                var params= [];
                params["idParam"]=id;
                window.goToUrlMvcUrl("<c:url value='/supervisorManager/authorizeMonitoringPlan/showCalendar.html?id=idParam' />",params);
            };

            window.authorize = function(id){
                window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/authorizeMonitoringPlan/authorize.html' />', "#GridId");
            };

            window.reject = function(id){
                window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/authorizeMonitoringPlan/reject.html' />', "#GridId");
            };

            $(document).ready(function() {
                jQuery("#GridId").jqGrid({
                    url: '<c:url value='/supervisorManager/authorizeMonitoringPlan/list.json' />',
                    datatype: "json",
                    mtype: 'POST',
                    colNames: ['ID', 'Caso', 'Carpeta judicial','Imputado', 'Fecha asignaci&oacute;n', 'Fecha generaci&oacute;n', 'Fecha autorizaci&oacute;n', 'Estatus', 'Asignado a', 'Acci&oacute;n'],
                    colModel: [
                        { name: 'id', index: 'id', hidden: true },
                        { name: 'caseId', width: 65, align: "center", sortable: true, search: false },
                        { name: 'idMP', width: 140, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] }},
                        { name: 'fullName', width: 220, align: "center", sortable: false, search: false },
                        { name: 'stCreationTime', width: 130, align: "center", sortable: true, search: false },
                        { name: 'stGenerationTime', width: 130, align: "center", sortable: true, search: false },
                        { name: 'stAuthorizationTime', width: 140, align: "center", sortable: true, search: false },
                        { name: 'status', width: 180, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                        { name: 'supervisor', width: 130, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] }},
                        { name: 'Action', width: 70, align: "center", sortable: false, search: false }
                    ],
                    rowNum: 10,
                    rowList: [10, 20, 30],
                    pager: '#GridPager',
                    sortname: 'creationTime',
                    height: 450,
                    viewrecords: true,
                    shrinkToFit: false,
                    sortorder: "asc",
                    caption: "&nbsp;",
                    altRows: true,
                    gridComplete: function () {
                        var ids = $(this).jqGrid('getDataIDs');
                        for (var i = 0; i < ids.length; i++) {
                            var cl = ids[i];
                            var row = $(this).getRowData(cl);
                            var be = "";

                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Ver plan de supervisi&oacute;n\" onclick=\"window.showCalendar('" + cl + "');\"><span class=\"glyphicon glyphicon-calendar\"></span></a>";
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Rechazar plan de supervisi&oacute;n\" onclick=\"window.reject('" + cl + "');\"><span class=\"glyphicon glyphicon-remove\"></span></a>";
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Autorizar plan de supervisi&oacute;n\" onclick=\"window.authorize('" + cl + "');\"><span class=\"glyphicon glyphicon-saved\"></span></a>";

                            $(this).jqGrid('setRowData', ids[i], { Action: be });
                        }
                    },
                    loadComplete : function() {
                        var table = this;
                        setTimeout(function(){
                            updatePagerIcons(table);
                            enableTooltips(table);
                        }, 0);
                    }
                });

                jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                    edit: false, editicon : 'icon-pencil blue',
                    add: false,
                    refresh: true, refreshicon : 'icon-refresh green',
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

        <h2 class="element-center"><i class="glyphicon glyphicon-thumbs-up"></i>&nbsp;&nbsp;Planes de seguimiento en espera de autorizaci&oacute;n</h2>

        <div id="angJsjqGridId" ng-controller="modalDlgController">
            <table id="GridId" class="element-center" style="margin: auto"></table>
            <div id="GridPager"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>

        <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
        <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
    </div>

</body>
</html>