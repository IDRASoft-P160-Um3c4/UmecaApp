<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <title>Bitácoras de supervisión y cumplimiento</title>
</head>
<body scroll="no" ng-app="ptlUmc">
    <%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

    <div class="container body-content">

        <script>
            window.accomplishmentLog = function(id) {
                var params= [];
                params["idParam"]=id;
                window.goToNewUrl("<c:url value='/supervisor/log/accomplishmentLog.html?id=idParam' />",params);
            };

            window.requestAccomplishmentLog = function(id) {
                window.showConfirmFull(id, "#angJsjqGridId", "<c:url value='/supervisor/log/requestAccomplishmentLog.json' />", "#GridId",
                "Plan de seguimiento", "¿Está seguro de que desea solicitar la autorización del reporte de incumplimiento?", "warning");
            };

            window.showRejectAuthAccomplishmentMsg = function(id){
                window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisor/manageMonitoringPlan/showRejectAuthAccomplishmentMsg.html' />', "#GridId");
            }

            window.supervisionLog = function(id) {
                var params= [];
                params["idParam"]=id;
                window.goToNewUrl("<c:url value='/supervisor/log/supervisionLog.html?id=idParam' />",params);
            };

            $(document).ready(function() {
                jQuery("#GridId").jqGrid({
                    url: '<c:url value='/supervisor/log/list.json' />',
                    datatype: "json",
                    mtype: 'POST',
                    colNames: ['ID', 'Caso', 'Carpeta judicial','Imputado', 'Fecha asignación', 'Fecha generación', 'Fecha autorización', 'Estatus', 'Asignado a', "Estatus bitácora", 'Acción'],
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
                        { name: 'statusLog', hidden: true },
                        { name: 'Action', width: 90, align: "center", sortable: false, search: false }
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
                            var statusLog = undefined;
                            try{
                                if(row.statusLog !== undefined && row.statusLog !== null && row.statusLog !== "")
                                    statusLog = jQuery.parseJSON(row.statusLog);
                            }catch(ex){}

                            var be = "";

                            be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Reporte de incumplimiento\" onclick=\"window.accomplishmentLog('" + cl + "');\"><span class=\"glyphicon glyphicon-saved\"></span></a>";

                            if(statusLog !== undefined && statusLog.action === "RECHAZADO REPORTE INCUMPLIMIENTO"){
                                be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Revisar mensaje de rechazo del reporte de incumplimiento\" onclick=\"window.showRejectAuthAccomplishmentMsg('" + cl + "');\"><span class=\"glyphicon glyphicon-comment green\"></span></a>";
                            }

                            if(statusLog === undefined || (statusLog.action !== "SOLICITUD AUTORIZAR REPORTE INCUMPLIMIENTO" && statusLog.action !== "AUTORIZAR REPORTE INCUMPLIMIENTO")){
                                be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Solicitar autorización del reporte de incumplimiento\" onclick=\"window.requestAccomplishmentLog('" + cl + "');\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>";
                            }

                            be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Bitácora de supervisión\" onclick=\"window.supervisionLog('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";

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

                jQuery("#GridId").jqGrid('filterToolbar', {
                    stringResult: true,
                    searchOperators: true,
                    searchOnEnter: true,
                    multipleSearch: true,
                    ignoreCase: true
                });
            });

        </script>

        <h2 class="element-center"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;Listado de los planes de seguimiento</h2>

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