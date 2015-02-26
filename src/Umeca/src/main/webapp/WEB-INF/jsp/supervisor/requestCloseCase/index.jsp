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

    <title>Cerrar casos</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>

        showRequestCloseCase = function (id) {
            window.showUpsert(id, "#angJsjqGridId", "<c:url value='/supervisor/requestCloseCase/showRequest.html'/>", "#GridCasesId");
        };

        $(document).ready(function () {
            jQuery("#GridCasesId").jqGrid({
                url: '<c:url value='/supervisor/caseActive/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'stCode','Carpeta Judicial', 'Nombre completo', 'Estatus', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'codeStatus', index: 'codeStatus', hidden: true},
                    {
                        name: 'idMP',
                        index: 'idMP',
                        width: 150,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'fullName',
                        index: 'fullName',
                        width: 250,
                        align: "center"
                    },
                    {name: 'descStatus', index: 'descStatus', width: 350, align: "center", search: false},
                    {
                        name: 'Action',
                        width: 70,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
                    },
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
                    var status = $(this).jqGrid('getCol', 'codeStatus', false);

                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var stat = status[i];
                        var be = "";

                        if (stat !== 'ST_CASE_PRISON_CLOSED' && stat !== 'ST_CASE_OBSOLETE_SUPERVISION' &&
                                stat !== 'ST_CASE_REQUEST_SUPERVISION' && stat !== 'ST_CASE_CLOSE_REQUEST' &&
                                stat !== 'ST_CASE_PRE_CLOSED' && stat !== 'ST_CASE_CLOSED')
                            be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Solicitar cierre de caso\" onclick=\"showRequestCloseCase('" + cl + "');\"><span class=\"glyphicon glyphicon-remove color-danger\"></span></a>";

                        $(this).jqGrid('setRowData', ids[i], {Action: be});
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
                add: false,
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

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
                        }
                    });

            jQuery("#GridCasesId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Cerrar casos
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