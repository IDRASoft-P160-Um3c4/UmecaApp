<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
</style>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/managereval/formulation/formulationCtrl.js"></script>
    <title>Entrevistas</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<div class="container body-content">
    <script>


        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value = "/reviewer/onlyMeeting/list.json" />',
                datatype: "json",
                autoencode: true,
                mtype: 'POST',
                colNames: ['ID', 'Carpeta de investigaci&oacute;n', 'Nombre', 'Status', 'Evaluador'/*,'Acci&oacute;n'*/],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'idFolder',
                        index: 'idFolder',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'fullname',
                        index: 'fullname',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'description',
                        index: 'description',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'reviewerName',
                        index: 'reviewerName',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                   /* {
                        name: 'Action',
                        width: 70,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
                    }*/
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
                        var presenceStr = row.presenceStr + "";
                        var be = "";
                        if (presenceStr === "Pendiente") {
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Registrar asistencia/inasistencia\" onclick=\"window.showConfirmPresence('" + cl + "');\"><i class=\" icon-ok\"></i></a>";
                        }
                        if (presenceStr === "No") {
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar reporte de inasistencia\" onclick=\"window.printDocument('" + cl + "');\"><i class=\" icon-file\"></i></a>";
                        }
                        if (presenceStr === "Si") {
//                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Entrevistas de riesgos\" onclick=\"window.showInterview('" + cl + "');\"><i class=\" icon-comments-alt\"></i></a>";
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Entrega de informaci&oacute;n\" onclick=\"window.showConfirmInformationDelivery('" + cl + "');\"><i class=\" icon-list-alt\"></i></a>";
                        }
                        $(this).jqGrid('setRowData', ids[i], {Action: be});
                        if (row.attended === "false" && row.presenceStr === "Pendiente") {
                            $("#" + cl).css("background-color", "#FF3617");
                        }
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
                search: false
            });
        });

    </script>
</div>


<h2 class="element-center"><i class="icon icon-list-alt"></i>&nbsp;&nbsp;Casos s&oacute;lo entrevista</h2>

<div id="angJsjqGridId" ng-controller="modalDlgController">
    <table id="GridId" class="element-center" style="margin: auto"></table>
    <div id="GridPager"></div>
    <div class="blocker" ng-show="working">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>
</div>
</div>


<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</body>
</html>

