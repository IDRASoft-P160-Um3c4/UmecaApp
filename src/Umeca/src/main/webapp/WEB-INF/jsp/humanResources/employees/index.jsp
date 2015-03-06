<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/employees/upsertEmployeeCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>

    <title>Personal UMECA</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>

        showUpsertEmployee = function () {
            window.showUpsert(null, "#angJsjqGridId", "<c:url value='/humanResources/employees/upsertEmployee.html'/>", "#GridEmployeeId");
        };

        showDigitalRecord = function (id) {
            window.goToUrlMvcUrl("<c:url value='/humanResources/digitalRecord/index.html?id='/>" + id);
        };

        $(document).ready(function () {
            jQuery("#GridEmployeeId").jqGrid({
                url: '<c:url value='/humanResources/employees/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'isObsolete', 'Nombre', 'Distrito', 'Puesto', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'isObsolete', index: 'isObsolete', hidden: true},
                    {
                        name: 'fullName',
                        index: 'fullName',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'district',
                        index: 'district',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'post',
                        index: 'post',
                        align: "center",
                        width: 200,
                        sortable: false,
                        search: false
                    },
                    {
                        name: 'Action',
                        index: 'Action',
                        width: 200,
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
                height: 280,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "desc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    var obsolete = $(this).jqGrid('getCol', 'isObsolete', false);

                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var be = "";

                        if (obsolete[i] == 'false') {
                            be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Gestionar formatos de audiencia\" onclick=\"showDigitalRecord(" + cl + ");\"><span class=\"glyphicon glyphicon-list\"></span></a>";
                        } else {
                            be = "";
                        }
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

            jQuery("#GridEmployeeId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: true, addfunc: showUpsertEmployee, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridEmployeeId").jqGrid('navSeparatorAdd', '#GridPager');
            jQuery("#GridEmployeeId").jqGrid('navButtonAdd', "#GridPager",
                    {
                        caption: "",
                        title: "Exportar a excel",
                        buttonicon: 'icon-download-alt blue',

                        onClickButton: function () {
                            try {
                                $("#GridEmployeeId").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                            } catch (e) {
                            }
                        }
                    });

            jQuery("#GridEmployeeId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        })
        ;

    </script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Personal UMECA
    </h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridEmployeeId" class="element-center" style="margin: auto"></table>
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