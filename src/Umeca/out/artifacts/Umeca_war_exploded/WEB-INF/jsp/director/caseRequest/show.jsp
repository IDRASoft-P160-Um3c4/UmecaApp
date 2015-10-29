<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <title>Entrevistas</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.showDetailRequest = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToUrlMvcUrl("<c:url value='/director/caseRequest/showDetail.html?id=idParam'/>", params);

        };

        window.updwFiles = function(id){
            var params= [];
            params["idParam"]=id;
            window.goToUrlMvcUrl("<c:url value='/shared/uploadFile/index.html?id=idParam'/>",params);
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/director/caseRequest/list.json' />',
                datatype: "json",
                autoencode:true,
                mtype: 'POST',
                colNames: ['ID', 'Carpeta de Investigaci&oacute;n', 'Nombre', 'Estatus', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullname', index: 'fullname', width: 400, align: "center", searchoptions: { sopt: ['bw'] } },
                    { name: 'statusString', index: 'statusString', hidden: false, width: 300, align:"center", searchoptions: { sopt: ['bw'] } },
                    { name: 'Action', width: 130, align: "center", sortable: false, search: false,formatter:window.actionFormatter }
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
                    var verifs = $(this).jqGrid('getCol', 'idVerif', false);
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var row = $(this).getRowData(cl);
                        var status = parseInt(row.status);
                        var be = "";
                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Ver detalles de solicitudes realizadas\" onclick=\"window.showDetailRequest('" + cl + "');\"><i class=\"glyphicon icon-eye-open\"></i></a>  ";
                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Subir / Descargar archivos del caso\" onclick=\"window.updwFiles('" + cl + "');\"><i class=\"glyphicon glyphicon-cloud-download\"></i></a>  ";

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
                edit: false, editicon: 'icon-pencil blue',
                add: true, addfunc: window.newMeeting, addicon: 'icon-plus-sign purple',
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

    <h2 class="element-center"><i class="icon icon-envelope"></i>&nbsp;&nbsp;Historial de solicitudes</h2>

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