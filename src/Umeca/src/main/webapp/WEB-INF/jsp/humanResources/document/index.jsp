<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/document/docCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <title>Oficios</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        $(document).ready(function () {

            upsertDocument = function (id) {
                var params = {"id":id};
                window.showUpsertParams(params, "#angJsjqGridId", "<c:url value='/humanResources/document/upsertDocument.html'/>", "#GridDocument");
            };

            deleteDocument = function (id) {
                window.showObsolete(id, "#angJsjqGridId", "<c:url value='/humanResources/document/documentDoObsolete.json'/>", "#GridDocument");
            };

            jQuery("#GridDocument").jqGrid({
                url: '<c:url value='/humanResources/document/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Fecha', 'N&uacute;mero<br/>de oficio', 'Remitente', 'Destinatario', 'Causa penal', 'Asunto', 'Turnado a', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'documentDate',
                        index: 'documentDate',
                        width: 100,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'numberDocument',
                        index: 'numberDocument',
                        width: 150,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'sender',
                        index: 'sender',
                        width: 150,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'receiver',
                        index: 'receiver',
                        align: "center",
                        width: 150,
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'criminalCause',
                        index: 'criminalCause',
                        align: "center",
                        width: 150,
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'subject',
                        index: 'subject',
                        align: "center",
                        width: 180,
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'turnedOver',
                        index: 'turnedOver',
                        align: "center",
                        width: 150,
                        sorttype: 'string',
                        search: false
                    },
                       {
                        name: 'Action',
                        index: 'Action',
                        width: 75,
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
                height: 350,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "desc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');

                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var be = "";

                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar documento\" onclick=\"upsertDocument(" + cl + ");\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar documento\" onclick=\"deleteDocument('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Adjuntar archivo\" onclick=\"summaryMinute('" + cl + "');\"><span class=\"glyphicon glyphicon-file\"></span></a>";

                        $(this).jqGrid('setRowData', ids[i], {Action: be});
                    }
                },
                <%--subGridOptions: {--%>
                    <%--plusicon: "glyphicon glyphicon-chevron-down position-relative",--%>
                    <%--minusicon: "glyphicon glyphicon-chevron-right position-relative",--%>
                    <%--reloadOnExpand: false,--%>
                    <%--selectOnExpand: true--%>
                <%--},--%>
                <%--subGrid: true,--%>
                <%--loadComplete: function () {--%>
                    <%--var table = this;--%>
                    <%--setTimeout(function () {--%>
                        <%--updatePagerIcons(table);--%>
                        <%--enableTooltips(table);--%>
                    <%--}, 0);--%>
                <%--},--%>
                <%--subGridRowExpanded: function (subgrid_id, row_id) {--%>
                    <%--var subgrid_table_id, pager_id;--%>
                    <%--subgrid_table_id = subgrid_id + "_t";--%>
                    <%--pager_id = "p_" + subgrid_table_id;--%>
                    <%--$("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");--%>
                    <%--$("#" + subgrid_table_id).jqGrid({--%>
                        <%--url: '<c:url value='/shared/agreement/list.json?id=' />' + row_id,--%>
                        <%--autoencode: true,--%>
                        <%--datatype: "json",--%>
                        <%--mtype: 'POST',--%>
                        <%--colNames: ['Acuerdo', 'Estado', 'Concluido'],--%>
                        <%--colModel: [--%>
                            <%--{--%>
                                <%--name: 'title',--%>
                                <%--index: 'title',--%>
                                <%--sorttype: 'string',--%>
                                <%--width: 400,--%>
                                <%--align: "center",--%>
                                <%--searchoptions: {sopt: ['bw']}--%>
                            <%--},--%>
                            <%--{--%>
                                <%--name: 'isDoneStr',--%>
                                <%--index: 'isDoneStr',--%>
                                <%--width: 150,--%>
                                <%--align: "center",--%>
                                <%--sortable: false,--%>
                                <%--search: false--%>
                            <%--},--%>
                            <%--{--%>
                                <%--name: 'isFinishedStr',--%>
                                <%--index: 'isFinishedStr',--%>
                                <%--width: 150,--%>
                                <%--align: "center",--%>
                                <%--sortable: false,--%>
                                <%--search: false--%>
                            <%--}--%>
                        <%--],--%>
                        <%--rowNum: 20,--%>
                        <%--pager: pager_id,--%>
                        <%--sortname: 'id',--%>
                        <%--sortorder: "asc",--%>
                        <%--height: '100%',--%>
                        <%--loadComplete: function () {--%>
                            <%--var table = this;--%>
                            <%--setTimeout(function () {--%>
                                <%--updatePagerIcons(table);--%>
                                <%--enableTooltips(table);--%>
                            <%--}, 0);--%>
                        <%--}--%>
                    <%--});--%>
                    <%--$("#" + subgrid_table_id).jqGrid('navGrid', "#" + pager_id, {edit: false, add: false, del: false})--%>
                <%--}--%>
            });

            jQuery("#GridDocument").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: true, addfunc: upsertDocument, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridDocument").jqGrid('navSeparatorAdd', '#GridPager');
            jQuery("#GridDocument").jqGrid('navButtonAdd', "#GridPager",
                    {
                        caption: "",
                        title: "Exportar a excel",
                        buttonicon: 'icon-download-alt blue',

                        onClickButton: function () {
                            try {
                                $("#GridDocument").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                            } catch (e) {
                            }
                        }
                    });

            jQuery("#GridDocument").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        })
        ;

    </script>

    <h2 class="element-center"><i class="glyphicon icon-file "></i>&nbsp;&nbsp;Oficios
    </h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridDocument" class="element-center" style="margin: auto"></table>
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