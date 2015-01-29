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

    <title>Casos no judicializados</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <style>
        .ui-jqgrid tr.jqgrow td {
            white-space: normal !important;
        }
    </style>
    <script>
        window.openNotProsecute = function(id) {
            window.showUpsertWithIdCase(id, "#angJsjqGridIdReference", "<c:url value='/supervisor/caseNotProsecute/openNotProsecute.html'/>", "#GridIdReference",undefined);
        };
        $(document).ready(function() {
            jQuery("#GridIdReference").jqGrid({
                url: '<c:url value='/supervisor/caseNotProsecute/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Carpeta de investigaci&oacute;n', 'Nombre', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullName', index: 'fullName', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false, formatter:window.actionFormatter }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPagerReference',
                sortname: 'fullName',
                height: 200,
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
                        var enabled = row.enabled;
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Reabrir caso no judicializado\" onclick=\"window.openNotProsecute('" + cl + "');\"><span class=\"icon-folder-open\"></span></a>";
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

            jQuery("#GridIdReference").jqGrid('navGrid', '#GridPagerReference', {
                edit: false, editicon : 'icon-pencil blue',
                add: false,
                refresh: true, refreshicon : 'icon-refresh green',
                del: false,
                search: false});

            jQuery("#GridIdReference").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>
    <h2 class="element-center"><i class=" icon-folder-close"></i>&nbsp;&nbsp;Casos no judicializados
    </h2>
    <div class="row element-center">
        <div class="col-xs-12">
            <br/>
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