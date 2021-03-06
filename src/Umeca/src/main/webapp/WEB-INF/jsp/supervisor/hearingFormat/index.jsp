<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>

    <title>Casos para entrevista de encuadre</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>


        showHearingFormats = function (idCase) {
            var goTo = "<c:url value='/supervisor/hearingFormat/indexFormats.html'/>" + "?id=" + idCase;
            window.goToUrlMvcUrl(goTo);
        };

        newCaseConditionalReprieve = function () {
            window.showUpsert(null, "#angJsjqGridId", "<c:url value='/supervisor/hearingFormat/newConditionalReprieve.html'/>", "#GridCasesId");
        };

        window.obsoleteCase = function (id) {
            window.showObsolete(id, "#angJsjqGridId", "<c:url value='/supervisor/hearingFormat/obsoleteCase.json'/>", "#GridCasesId");
        };
        $(document).ready(function () {
            jQuery("#GridCasesId").jqGrid({
                url: '<c:url value='/supervisor/hearingFormat/listCases.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'idStatus', 'Carpeta <br/>de Investigaci&oacute;n', 'Carpeta Judicial', 'Nombre completo', 'Estatus', 'Acci&oacute;n','framingMeetingId','idTR'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'status', index: 'status', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'idMP', index: 'idMP', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullName', index: 'fullName', width: 250, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'statusDesc', index: 'statusDesc', width: 350, align: "center", search: false},
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false, formatter:window.actionFormatter },
                    { name: 'framingMeetingId', index: 'framingMeetingId', hidden: true },
                    { name: 'idTR', index: 'idTR', hidden: true }
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
                    var status = $(this).jqGrid('getCol', 'status', false);

                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var be = "";
                        switch (status[i]) {

                            case 'ST_CASE_TECHNICAL_REVIEW_COMPLETE':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Gestionar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-plus\"></span></a>";
                                break;
                            case 'ST_CASE_NOT_PROSECUTE_OPEN':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Gestionar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-plus\"></span></a>";
                                break;
                            case 'ST_CASE_CONDITIONAL_REPRIEVE':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Gestionar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-plus\"></span></a>";
                                break;
                            case 'ST_CASE_HEARING_FORMAT_INCOMPLETE':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                                break;
                            case 'ST_CASE_HEARING_FORMAT_END':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                                break;
                            case 'ST_CASE_PRE_CLOSED':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                                break;
                            case 'ST_CASE_FRAMING_MEETING_INCOMPLETE':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                                break;
                            case 'ST_CASE_FRAMING_MEETING_COMPLETE':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                                break;
                        }
                        var row = $(this).getRowData(cl);
                        var framingMeetingId = row.framingMeetingId;
                        var idTR = row.idTR;
                        if(framingMeetingId == '' && idTR == ''){
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar caso\" onclick=\"obsoleteCase('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

            jQuery("#GridCasesId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: true, addfunc: newCaseConditionalReprieve, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false});

            jQuery("#GridCasesId").jqGrid('navSeparatorAdd', '#GridPager');
            jQuery("#GridCasesId").jqGrid('navButtonAdd', "#GridPager",
                    {
                        caption: "",
                        title: "Exportar a excel",
                        buttonicon: 'icon-download-alt blue',

                        onClickButton: function () {
                            try {
                                $("#GridCasesId").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                            } catch (e) {
                            }
                        }});

            jQuery("#GridCasesId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Casos para formato de audiencia
    </h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridCasesId" class="element-center" style="margin: auto"></table>
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