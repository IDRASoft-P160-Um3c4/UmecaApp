<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/commonActMonPlan.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/activeMonitoringPlan/changeSupervisorCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/authorizeMonitoringPlan/authRejectCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/activeMonitoringPlan/autRejActMonPlanCtrl.js"></script>
    <title>Planes de seguimiento</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<div class="container body-content">

    <script>
        window.showCalendar = function (id) {
            var params = [];
            params["idParam"] = id;
            params["retParam"] = 1;
            window.goToUrlMvcUrl("<c:url value='/supervisorManager/authorizeMonitoringPlan/showCalendar.html?id=idParam&ret=retParam' />", params);
        };

        window.authorizeEnd = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/activeMonitoringPlan/authorizeEnd.html' />', "#GridId");
        };

        window.rejectEnd = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/activeMonitoringPlan/rejectEnd.html' />', "#GridId");
        };

        window.authorizeAccomplishment = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/activeMonitoringPlan/authorizeAccomplishment.html' />', "#GridId");
        };

        window.rejectAccomplishment = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/activeMonitoringPlan/rejectAccomplishment.html' />', "#GridId");
        };

        window.changeSupervisor = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/activeMonitoringPlan/changeSupervisor.html' />', "#GridId");
        };

        window.accomplishmentLog = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToNewUrl("<c:url value='/supervisorManager/log/accomplishmentLog.html?id=idParam' />", params);
        };

        window.requestAccomplishmentLog = function (id) {
            window.showConfirmFull(id, "#angJsjqGridId", "<c:url value='/supervisor/log/requestAccomplishmentLog.json' />", "#GridId",
                    "Plan de seguimiento", "&iquest;Est&aacute; seguro de que desea solicitar la autorizaci&oacute;n del reporte de incumplimiento?", "warning");
        };

        window.supervisionLog = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToNewUrl("<c:url value='/supervisorManager/log/supervisionLog.html?id=idParam' />", params);
        };

        window.authRejActMonPlan = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisorManager/activeMonitoringPlan/authRejActMonPlan.html' />', "#GridId");
        }

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/supervisorManager/activeMonitoringPlan/list.json' />',
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Caso', 'Carpeta judicial', 'Imputado', 'Fecha asignaci&oacute;n', 'Fecha generaci&oacute;n', 'Fecha autorizaci&oacute;n', 'Estatus', 'Asignado a', "Estatus bit&aacute;cora", "PreAuth", 'Suspender', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'caseId', hidden: true },
                    { name: 'idMP', width: 140, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] }},
                    { name: 'fullName', width: 220, align: "center", sortable: false, search: false },
                    { name: 'stCreationTime', width: 130, align: "center", sortable: true, search: false },
                    { name: 'stGenerationTime', width: 130, align: "center", sortable: true, search: false },
                    { name: 'stAuthorizationTime', width: 140, align: "center", sortable: true, search: false },
                    { name: 'status', width: 180, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'supervisor', width: 130, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] }},
                    { name: 'statusLog', hidden: true },
                    { name: 'hasActPreAuth', hidden: true },
                    { name: 'isMonPlanSuspended', index: 'isMonPlanSuspended', hidden: true},
                    { name: 'Action', width: 150, align: "center", sortable: false, search: false }
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
                        var status = row.status;
                        var statusLog = undefined;
                        var hasActPreAuth = row.hasActPreAuth;

                        try {
                            statusLog = jQuery.parseJSON(row.statusLog);
                        } catch (ex) {
                        }

                        var be = "";
                        var isMonPlanSuspended = row.isMonPlanSuspended;

                        if (isMonPlanSuspended === 'true') {
                            be += "<span style='display:inline-block;' class='glyphicon glyphicon-fire red' title='El plan se encuentra suspendido, no se podr&aacute;n realizar acciones hasta que el coordinador lo autorice.'></span>";
                        }

                        be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Cambiar supervisor del caso\" onclick=\"window.changeSupervisor('" + cl + "');\"><span class=\"glyphicon glyphicon-user\"></span></a>";
                        be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Ver plan de supervisi&oacute;n\" onclick=\"window.showCalendar('" + cl + "');\"><span class=\"glyphicon glyphicon-calendar\"></span></a>";

                        be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Reporte de incumplimiento\" onclick=\"window.accomplishmentLog('" + cl + "');\"><span class=\"glyphicon glyphicon-saved\"></span></a>";

                        if (statusLog !== undefined && statusLog.action === "SOLICITUD AUTORIZAR REPORTE INCUMPLIMIENTO") {
                            be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Rechazar reporte de incumplimiento\" onclick=\"window.rejectAccomplishment('" + cl + "');\"><span class=\"glyphicon glyphicon-remove-circle\"></span></a>";
                            be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Autorizar reporte de incumplimiento\" onclick=\"window.authorizeAccomplishment('" + cl + "');\"><span class=\"glyphicon glyphicon-ok-circle\"></span></a>";
                        }

                        be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Bit&aacute;cora de supervisi&oacute;n\" onclick=\"window.supervisionLog('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";

                        if (status === "EN PROCESO DE TERMINAR") {
                            be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Rechazar termino del plan de supervisi&oacute;n\" onclick=\"window.rejectEnd('" + cl + "');\"><span class=\"glyphicon glyphicon-remove-circle red\"></span></a>";
                            be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Autorizar termino del plan de supervisi&oacute;n\" onclick=\"window.authorizeEnd('" + cl + "');\"><span class=\"glyphicon glyphicon-ok-circle green\"></span></a>";
                        }

                        if (hasActPreAuth === 'true') {
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Autorizar o rechazar creaciones, modificaciones o eliminaciones de las actividades del plan de seguimiento\" onclick=\"window.authRejActMonPlan('" + cl + "');\"><span class=\"glyphicon glyphicon-transfer red\"></span></a>";
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
                edit: false, editicon: 'icon-pencil blue',
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
                                $("#GridId").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
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

    <h2 class="element-center"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;Listado de los planes de
        seguimiento</h2>

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