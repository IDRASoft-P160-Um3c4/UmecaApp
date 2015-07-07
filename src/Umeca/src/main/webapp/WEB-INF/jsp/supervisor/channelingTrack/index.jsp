<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/channeling/channelingTrackCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>

    <title>Seguimiento a canalizaci&oacute;n</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.justify = function (id) {
            window.showUpsertParams({id: id}, "#angJsjqGridId", '<c:url value='/supervisor/channelingTrack/justify.html' />', "#GridId");
        };

        window.reschedule = function (id) {
            window.showUpsertParams({id: id}, "#angJsjqGridId", '<c:url value='/supervisor/channelingTrack/reschedule.html' />', "#GridId");
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/supervisor/channelingTrack/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Carpeta Judicial', 'Imputado', 'Resoluci&oacute;n', 'Fecha inasistencia', 'Canalizaci&oacute;n', 'Tipo de canalizaci&oacute;n', '¿Justificado?', 'Reagendar', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idMP', index: 'idMP', width: 130, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'imputed', index: 'imputed', width: 280, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'resolutionTxt', index: 'resolutionTxt', width: 100, align: "center", sorttype: 'string', search: false },
                    { name: 'absenceDate', index: 'absenceDate', width: 180, align: "center", sorttype: 'string', search: false},
                    { name: 'channelingName', index: 'channelingName', width: 160, align: "center", sorttype: 'string', search: false },
                    { name: 'channelingTypeName', index: 'channelingTypeName', width: 160, align: "center", sorttype: 'string', search: false },
                    { name: 'isJustified', index: 'isJustified', hidden: true },
                    { name: 'rescheduleAppointmentId', index: 'rescheduleAppointmentId', hidden: true },
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
                        var be = "";

                        if(row.isJustified === undefined || row.isJustified === null || row.isJustified === "")
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Justificar inasistencia\" onclick=\"window.justify('" + cl + "');\"><span class=\"glyphicon glyphicon-check\"></span></a>";
                        else
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Mostrar justificante\" onclick=\"window.justify('" + cl + "');\"><span class=\"glyphicon glyphicon-paperclip\"></span></a>";

                        if(row.rescheduleAppointmentId === undefined || row.rescheduleAppointmentId === null || row.rescheduleAppointmentId === "")
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Reagendar cita\" onclick=\"window.reschedule('" + cl + "');\"><span class=\"glyphicon glyphicon-calendar\"></span></a>";
                        else if(row.rescheduleAppointmentId > 0)
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Ver cita reagendada\" onclick=\"window.reschedule('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";

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