<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>

    <style>
        .ui-jqgrid tr.jqgrow td {
            white-space: normal !important;
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/scheduleHearings/scheduleHearingsIndexCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/scheduleHearings/scheduleHearingsCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/enterKeyDrct.js"></script>

    <script>
        window.scheduleNewHearing = function(lstIds) {
            var sLst = "" + lstIds[0];
            for(var i= 1, len = lstIds.length; i<len; i++){
                sLst = sLst + ", " + lstIds[i];
            }
            window.showUpsert(sLst, "#angJsjqGridIdReference", "<c:url value='/supervisor/scheduleHearings/scheduleNewHearing.html'/>", "#GridIdReference");
        };

        $(document).ready(function() {
            jQuery("#GridIdReference").jqGrid({
                url: '<c:url value='/supervisor/scheduleHearings/list.json' />',
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', '', 'Estado plan de supervisi&oacute;n','Carpeta judicial', 'Nombre del imputado', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'selOpt', width: 50, align: "center", sortable: false, search: false },
                    { name: 'status', index: 'status', width: 300, align: "center", sorttype: 'string' },
                    { name: 'idMP', index: 'idFolder', width: 300, align: "center", sorttype: 'string' },
                    { name: 'fullName', index: 'fullName', width: 400, align: "center", sorttype: 'string'},
                    { name: 'Action', width: 100, align: "center", sortable: false, search: false }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPagerReference',
                sortname: 'fullName',
                height: 500,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "desc",
                caption: "&nbsp;",
                altRows: true,
                subGridOptions: {
                    plusicon: "glyphicon glyphicon-chevron-down position-relative",
                    minusicon: "glyphicon glyphicon-chevron-right position-relative",
                    // openicon  : "ui-icon-arrowreturn-1-e",
                    reloadOnExpand: false,
                    selectOnExpand: true
                },
                subGrid: true,
                subGridRowExpanded: function (subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid({
                        url: '<c:url value='/supervisor/scheduleHearings/sublist.json?monPlanId=' />' + row_id,
                        datatype: "json",
                        mtype: 'POST',
                        colNames: ['ID', 'Sala', 'Fecha audiencia','Fecha creación','Fecha recordatorio','Generado por', 'Tipo audiencia'],
                        colModel: [
                            { name: 'id', hidden: true },
                            { name: 'room', width: 150, align: "center", sorttype: 'string'},
                            { name: 'hearingDateSt', width: 200, align: "center", sorttype: 'string' },
                            { name: 'hearingCreationDateSt', width: 200, align: "center", sorttype: 'string'},
                            { name: 'hearingReminderDateSt', width: 200, align: "center", sorttype: 'string'},
                            { name: 'usernameCreate', width: 150, align: "center", sorttype: 'string'},
                            { name: 'hearingTypeName', width: 150, align: "center", sorttype: 'string'}
                        ],
                        rowNum: 20,
                        pager: pager_id,
                        sortname: 'id',
                        sortorder: "asc",
                        height: '100%',
                        loadComplete: function () {
                            var table = this;
                            setTimeout(function () {
                                updatePagerIcons(table);
                                enableTooltips(table);
                            }, 0);
                        }
                    });
                    $("#" + subgrid_table_id).jqGrid('navGrid', "#" + pager_id, {edit: false, add: false, del: false})
                },
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var row = $(this).getRowData(cl);
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Agendar nueva audiencia\" onclick=\"window.scheduleNewHearing([" + cl + "]);\"><span class=\"icon-legal\"></span></a>";
                        $(this).jqGrid('setRowData', ids[i], { Action: be });
                        be = '<input class="form-control-static" type="checkbox" id="chkCaseToSel' + cl + '" value="' + cl + '"/>'
                        $(this).jqGrid('setRowData', ids[i], { selOpt: be });
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

            jQuery("#GridIdReference").jqGrid('navGrid', '#GridPagerReference', {
                edit: false, editicon : 'icon-pencil blue',
                add: false,
                refresh: true, refreshicon : 'icon-refresh green',
                del: false,
                search: false});
        });

    </script>


    <title>Agenda de audiencias</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" ng-controller="scheduleHearingsIndexController">
    <h2 class="element-center"><i class=" icon-folder-close"></i>&nbsp;&nbsp;Agenda de audiencias
    </h2>
    <br>
    <br>
    <div class="row">
        <div class="col-xs-6 col-xs-offset-3">
            <div class="row">
                <div class="col-xs-12 element-center">
                    <div class="form-inline" role="form">
                        <input type="text" class="form-control" ng-enter-key for-element-id="btn-search"
                               ng-model="m.searchField" style="width: 220px !important;" placeholder="Ingresa la carpeta judicial">
                        <button id="btn-search" class="btn btn-primary btn-sm" ng-click="onSearchField('#GridIdReference');">
                            <i class="white icon icon-search"></i>
                            Buscar
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" ng-show="MsgError">
        <br>
        <div class="col-xs-12">
            <div class="alert alert-danger element-center" ng-bind-html="MsgError">
            </div>
        </div>
    </div>
    <br/>
    <br/>
    <br/>
    <div class="row element-center" ng-show="m.isSearchDone">
        <div class="col-xs-12">
            <button class="btn btn-primary btn-sm" ng-click="onScheduleHearings('#GridIdReference', 'chkCaseToSel');">
                <i class="white icon icon-legal"></i>
                *Agendar nueva audiencia
            </button>
        </div>
        <div>
            <small>*Usted puede seleccionar más de una casilla, si desea agendar la misma cita para todos los imputados seleccionados</small>
        </div>
        <div class="col-xs-12">
            <div id="angJsjqGridIdReference" ng-controller="modalDlgController">
                <table id="GridIdReference" class="element-center" style="margin: auto"></table>
                <div id="GridPagerReference"></div>
                <div class="blocker" ng-show="working">
                    <div>
                        Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>