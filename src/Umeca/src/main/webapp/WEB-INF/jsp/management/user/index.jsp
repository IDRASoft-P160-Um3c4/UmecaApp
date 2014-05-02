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
    <%@ include file="/WEB-INF/jsp/shared/headGrid.jsp"%>
<title>Roles</title>
</head>
<body scroll="no" ng-app="ptlUmc">
    <%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

    <div class="container body-content">

        <script>
            $(document).ready(function() {
                jQuery("#GridId").jqGrid({
                    url: '<c:url value='/management/user/list.json' />',
                    datatype: "json",
                    mtype: 'POST',
                    colNames: ['ID', 'Usuario', 'Acción'],
                    colModel: [
                        { name: 'id', index: 'id', hidden: true },
                        { name: 'username', index: 'username', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                        { name: 'enabled', index: 'enabled', width: 500, align: "center", search: false }
                    ],
                    rowNum: 10,
                    rowList: [10, 20, 30],
                    pager: '#GridPager',
                    sortname: 'username',
                    height: 450,
                    viewrecords: true,
                    shrinkToFit: false,
                    sortorder: "desc",
                    caption: "&nbsp;"
                });

                jQuery("#GridId").jqGrid('navGrid', '#GridPager', { edit: false, add: false, del: false, search: false});

                jQuery("#GridId").jqGrid('filterToolbar', {
                    stringResult: true,
                    searchOperators: true,
                    searchOnEnter: true,
                    multipleSearch: true,
                    ignoreCase: true
                });
            });

        </script>

        <h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Usuarios</h2>

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