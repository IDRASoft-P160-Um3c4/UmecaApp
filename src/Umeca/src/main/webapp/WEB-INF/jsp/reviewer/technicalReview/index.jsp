<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
* Project: Umeca
* User: Israel
* Date: 4/30/14
* Time: 9:53 AM
-->

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/technicalReviewCtrl.js"></script>

    <title>Opinión técnica</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.upsert = function(id) {
            window.showUpsert(id, "#angJsjqGridId", "/reviewer/technicalReview/technicalReview.html", "#GridId");
        };

        /*window.obsolete = function (id) {
            window.showObsolete(id, "#angJsjqGridId", "/management/user/obsolete.json", "#GridId");
        };*/

        var data_grid_reviews = [{id:"1",idCarpeta:"C-001",idMp:"MP-001",fullname:"Rolandael Vite Padilla"},
                                {id:"2",idCarpeta:"C-002",idMp:"MP-002",fullname:"Isrolando Gómez Silva"}
        ];

        $(document).ready(function() {
            jQuery("#GridTecRevId").jqGrid({
                //data:data_grid_reviews,
                url: '<c:url value='/reviewer/technicalReview/list.json' />',
                datatype: "local",
                mtype: 'POST',
                colNames: ['Id','Id Carpeta', 'Id M.P.','Imputado', 'Acción'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idCarpeta', index: 'idCarpeta', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'idMp', index: 'idMp', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullname', index: 'fullname', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPagerTecRev',
                sortname: 'fullname',
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
                        var enabled = row.enabled;
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Calcular riesgo\" onclick=\"window.upsert('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
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

            jQuery("#GridTecRevId").jqGrid('navGrid', '#GridPagerTecRev', {
                edit: false, editicon : 'icon-pencil blue',
                add: true, addfunc: window.upsert, addicon : 'icon-plus-sign purple',
                refresh: true, refreshicon : 'icon-refresh green',
                del: false,
                search: false});

            jQuery("#GridTecRevId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>

    <h2 class="element-center"><i class="icon-archive"></i>&nbsp;&nbsp;Opinión técnica</h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridTecRevId" class="element-center" style="margin: auto"></table>
        <div id="GridPagerTecRev"></div>
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