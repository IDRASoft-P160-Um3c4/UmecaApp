<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <title></title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.generate = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToUrlMvcUrl("<c:url value='/supervisor/generateMonitoringPlan/generate.html?id=idParam' />", params);
        };

        window.preAuthorize = function (id) {
            window.showConfirmFull(id, "#angJsjqGridId", "<c:url value='/supervisor/generateMonitoringPlan/preAuthorize.json' />", "#GridId",
                    "Plan de seguimiento", "&iquest;Est&aacute; seguro de que desea solicitar la autorizaci&oacute;n del plan de seguimiento para este caso?", "warning");
        };

        window.showRejectAuthMsg = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisor/manageMonitoringPlan/showRejectAuthMsg.html' />', "#GridId");
        }

        window.showRejectAuthEndMsg = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisor/manageMonitoringPlan/showRejectAuthEndMsg.html' />', "#GridId");
        }

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/supervisor/generateMonitoringPlan/listB.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Caso', 'Carpeta judicial', 'Imputado', 'Fecha asignaci&oacute;n', 'Fecha generaci&oacute;n', 'Fecha autorizaci&oacute;n', 'Estatus', 'Asignado a', 'Suspendido', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'caseId', index: 'caseId', hidden: true },
                    { name: 'idMP', index: 'idMP', width: 140, align: "center", sorttype: 'string', searchoptions: { sopt: ['lk'] } },
                    { name: 'fullName', index: 'fullName', width: 220, align: "center", sorttype: 'string', searchoptions: { sopt: ['lk'] } },
                    { name: 'stCreationTime', index: 'stCreationTime', width: 130, align: "center", sortable: false, searchoptions: { sopt: ['lk'] } },
                    { name: 'stGenerationTime', index: 'stGenerationTime', width: 130, align: "center", sorttype: 'string', searchoptions: { sopt: ['lk'] } },
                    { name: 'stAuthorizationTime', index: 'stAuthorizationTime', width: 140, align: "center", sortable: false, searchoptions: { sopt: ['lk'] } },
                    { name: 'status', index: 'status', width: 180, align: "center", sorttype: 'string', searchoptions: { sopt: ['lk'] } },
                    { name: 'supervisor', index: 'supervisor', width: 130, align: "center", sortable: false, search: false },
                    { name: 'isMonPlanSuspended', index: 'isMonPlanSuspended', hidden: true},
                    { name: 'Action', width: 110, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
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
                        var be = "";
                        var isMonPlanSuspended = row.isMonPlanSuspended;
                        if (isMonPlanSuspended === 'true') {
                            be += "<span style='display:inline-block;' class='glyphicon glyphicon-fire red' title='El plan se encuentra suspendido, no se podr&aacute;n realizar acciones hasta que el coordinador lo autorice.'></span>";
                        }
                        else {
                            if (status === "NUEVO") {
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar plan de seguimiento\" onclick=\"window.generate('" + cl + "');\"><span class=\"glyphicon glyphicon-plus-sign\"></span></a>";
                            } else if (status === "EN PROCESO DE GENERAR") {
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Solicitar autorizaci&oacute;n del plan de seguimiento\" onclick=\"window.preAuthorize('" + cl + "');\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>";
                            }
                            else if (status === "RECHAZADO AUTORIZAR") {
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Revisar mensaje de rechazo de autorizaci&oacute;n\" onclick=\"window.showRejectAuthMsg('" + cl + "');\"><span class=\"glyphicon glyphicon-comment color-warning\"></span></a>";
                            }
                            else if (status === "RECHAZADO TERMINAR") {
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Revisar mensaje de rechazo de finalizaci&oacute;n\" onclick=\"window.showRejectAuthEndMsg('" + cl + "');\"><span class=\"glyphicon glyphicon-comment color-danger\"></span></a>";
                            }

                            if (status === "EN PROCESO DE GENERAR" || status === "AUTORIZADO" || status === "RECHAZADO AUTORIZAR" || status === "EN SEGUIMIENTO" || status === "RECHAZADO TERMINAR") {
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Modificar plan de seguimiento\" onclick=\"window.generate('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                            }
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

    <h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Casos asignados</h2>

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