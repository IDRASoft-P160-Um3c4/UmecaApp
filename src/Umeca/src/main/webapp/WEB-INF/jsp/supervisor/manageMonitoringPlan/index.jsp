<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <title></title>
</head>
<body scroll="no" ng-app="ptlUmc">
    <%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>


    <div class="container body-content">

        <script>

            window.reqEndPlan = function(id){
                window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisor/manageMonitoringPlan/reqEndPlan.html' />', "#GridId");
            }

            window.showRejectAuthMsg = function(id){
                window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisor/manageMonitoringPlan/showRejectAuthMsg.html' />', "#GridId");
            }

            window.showRejectAuthEndMsg = function(id){
                window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisor/manageMonitoringPlan/showRejectAuthEndMsg.html' />', "#GridId");
            }

            window.track = function(id) {
                var params= [];
                params["idParam"]=id;
                params["redirectParam"] = 1;
                window.goToUrlMvcUrl("<c:url value='/supervisor/trackMonitoringPlan/trackCalendar.html?id=idParam&&redirect=redirectParam' />",params);
            };



            window.supervisionLog = function(id) {
                var params= [];
                params["idParam"]=id;
                window.goToNewUrl("<c:url value='/supervisorManager/log/supervisionLog.html?id=idParam' />",params);
            };

            $(document).ready(function() {
                jQuery("#GridId").jqGrid({
                    url: '<c:url value='/supervisor/manageMonitoringPlan/list.json' />',
                    datatype: "json",
                    mtype: 'POST',
                    colNames: ['ID', 'Caso', 'Carpeta judicial','Imputado', 'Fecha asignaci&oacute;n', 'Fecha generaci&oacute;n', 'Fecha autorizaci&oacute;n', 'Estatus', 'Asignado a', 'Suspendido', 'Acci&oacute;n'],
                    colModel: [
                        { name: 'id', index: 'id', hidden: true },
                        { name: 'caseId', index: 'caseId', hidden: true},
                        { name: 'idMP', index: 'idMP', width: 140, align: "center", sortable: false, search: false },
                        { name: 'fullName', index: 'fullName', width: 220, align: "center", sortable: false, search: false },
                        { name: 'stCreationTime', index: 'stCreationTime', width: 130, align: "center", sortable: true, search: false },
                        { name: 'stGenerationTime', index: 'stGenerationTime', width: 130, align: "center", sortable: true, search: false },
                        { name: 'stAuthorizationTime', index: 'stAuthorizationTime', width: 140, align: "center", sortable: true, search: false },
                        { name: 'status', index: 'status', width: 180, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                        { name: 'supervisor', index: 'supervisor', width: 130, align: "center", sortable: false, search: false },
                        { name: 'isMonPlanSuspended', index: 'isMonPlanSuspended', hidden:true},
                        { name: 'Action', width: 110, align: "center", sortable: false, search: false }
                    ],
                    rowNum: 10,
                    rowList: [10, 20, 30],
                    pager: '#GridPager',
                    sortname: 'creationTime',
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
                            var status = row.status;
                            var isMonPlanSuspended = row.isMonPlanSuspended;
                            var be = "";

                            if(isMonPlanSuspended === 'true'){
                                be += "<span style='display:inline-block;' class='glyphicon glyphicon-fire red' title='El plan se encuentra suspendido, no se podr&aacute;n realizar acciones hasta que el coordinador lo autorice.'></span>";
                            }
                            else{
                                if (status === "RECHAZADO AUTORIZAR"){
                                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Revisar mensaje de rechazo de autorizaci&oacute;n\" onclick=\"window.showRejectAuthMsg('" + cl + "');\"><span class=\"glyphicon glyphicon-comment color-warning\"></span></a>";
                                }else if(status === "RECHAZADO TERMINAR"){
                                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Revisar mensaje de rechazo de finalizaci&oacute;n\" onclick=\"window.showRejectAuthEndMsg('" + cl + "');\"><span class=\"glyphicon glyphicon-comment color-danger\"></span></a>";
                                }

                                if (status !== "RECHAZADO AUTORIZAR" && status !== "RECHAZADO TERMINAR" && status !== "EN PROCESO DE AUTORIZAR"  && status !== "EN PROCESO DE TERMINAR") {
                                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Solicitar finalizaci&oacute;n del plan de seguimiento\" onclick=\"window.reqEndPlan('" + cl + "');\"><span class=\"glyphicon glyphicon-off\"></span></a>";
                                    be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Ver plan de supervisi&oacute;n\" onclick=\"window.track('" + cl + "');\"><span class=\"glyphicon glyphicon-calendar\"></span></a>";
                                    be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Bit&aacute;cora de supervisi&oacute;n\" onclick=\"window.supervisionLog('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                                }
                            }

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

        <div class="page-header">
            <div class="row">
                <div class="col-xs-12">
                    <h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Casos y planes de seguimiento</h2>
                </div>
            </div>
        </div>

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