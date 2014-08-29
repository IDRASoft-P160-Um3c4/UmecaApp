<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
* Project: Umeca
* Date: 4/30/14
* Time: 9:53 AM
-->

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <%--<script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/trackMonitoringPlan/trackListMonPlanCtrl.js"></script>--%>
    <title></title>
</head>
<body scroll="no" ng-app="ptlUmc">
    <%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>


    <div class="container body-content">

        <script>
            window.uploadFile = function(id) {
                alert(id);
            };

            $(document).ready(function() {
                jQuery("#GridId").jqGrid({
                    url: '<c:url value='/shared/uploadFile/list.json?idCase=' />' + ${caseId},
                    datatype: "json",
                    mtype: 'POST',
                    colNames: ['ID', 'Archivo', 'Descripción', 'Tamaño', 'Usuario', 'Fecha creaci&oacute;n', 'Caso'],
                    colModel: [
                        { name: 'id', index: 'id', hidden: true },
                        { name: 'filename', index: 'filename', width: 140, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                        { name: 'description', index: 'description', width: 220, align: "center", sortable: false, search: false },
                        { name: 'stCreationTime', index: 'stCreationTime', width: 130, align: "center", sortable: true, search: false },
                        { name: 'stGenerationTime', index: 'stGenerationTime', width: 130, align: "center", sortable: true, search: false },
                        { name: 'stAuthorizationTime', index: 'stAuthorizationTime', width: 140, align: "center", sortable: true, search: false },
                        { name: 'status', index: 'status', width: 180, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                        { name: 'supervisor', index: 'supervisor', width: 130, align: "center", sortable: false, search: false },
                        { name: 'caseId', index: 'caseId', hidden: true},
                        { name: 'Action', width: 70, align: "center", sortable: false, search: false }
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

                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Seguimiento al plan\" onclick=\"window.track('" + cl + "');\"><span class=\"glyphicon glyphicon-calendar\"></span></a>";
                            /*if (status === "NUEVO") {
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar plan de supervisiï¿½n\" onclick=\"window.generate('" + cl + "');\"><span class=\"glyphicon glyphicon-plus-sign\"></span></a>";
                            }*/
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

                jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                    edit: false, editicon : 'icon-pencil blue',
                    add: false,
                    refresh: true, refreshicon : 'icon-refresh green',
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
                                    $("#GridId").jqGrid('toExcelFile',{nombre:"datosXls",formato:"excel"});
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

        <div class="page-header">
            <div class="row">
                <div class="col-xs-12">
                    <h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Planes de seguimiento</h2>
                </div>
            </div>
            <div class="row" ng-controller="trackListMonPlanCtrl">
                <div class="col-xs-3 col-xs-offset-5">
                    <div class="btn btn-success element-center" ng-click="trackAll()"><i class="glyphicon glyphicon-calendar"></i> &nbsp; Calendario completo</div>
                </div>
            </div>
        </div>

        <div id="angJsjqGridId" ng-controller="modalDlgController">
            <table id="GridId" class="element-center" style="margin: auto"></table>
            <div id="GridPager"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>

        <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
        <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
    </div>

</body>
</html>