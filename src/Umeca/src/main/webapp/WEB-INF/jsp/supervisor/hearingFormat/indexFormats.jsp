<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
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

        addHearingFormat = function () {
            var goTo = "<c:url value='/supervisor/hearingFormat/newHearingFormat.html'/>" + "?idCase=" + ${idCase};
            window.goToUrlMvcUrl(goTo);
        };

        viewHearingFormat = function (id) {
            var goTo = "<c:url value='/supervisor/hearingFormat/viewHearingFormat.html'/>" + "?idFormat=" + id;
            window.goToUrlMvcUrl(goTo);
        };

        $(document).ready(function () {


            jQuery("#GridId").jqGrid({
                url: '<c:url value='/supervisor/hearingFormat/listFormats.json' />' + '?id=' +${idCase},
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Carpeta <br/>de Investigación' , 'Carpeta Judicial', 'Nombre completo', 'Audiencia', 'Ampliación <br/>de plazo', 'Vinculación <br/>a proceso', 'Acción'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'idMP', index: 'idMP', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullName', index: 'fullName', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'hearingType', index: 'hearingType', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'extension', index: 'extension', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'processVinc', index: 'processVinc', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false }
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
                        var be;

                        be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar formato de audiencia\" onclick=\"viewHearingFormat(   '" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";

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
                add: true, addfunc: addHearingFormat, addicon: 'icon-plus-sign purple',
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

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Formatos de audiencia del caso
    </h2>

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