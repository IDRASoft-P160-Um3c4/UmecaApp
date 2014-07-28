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
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/uniqueDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <title>Usuarios</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

<script>
    window.upsert = function (id) {
        window.showUpsert(id, "#angJsjqGridId", '<c:url value='/management/user/upsert.html' />', "#GridId");
    };

    window.disable = function (id) {
        window.showAction(id, "#angJsjqGridId", '<c:url value='/management/user/disable.json' />', "#GridId", "Deshabilitar usuario", "&iquest;Desea deshabilitar al usuario?", "warning");
    };


    window.enable = function (id) {
        window.showAction(id, "#angJsjqGridId", '<c:url value='/management/user/enable.json' />', "#GridId", "Habilitar usuario", "&iquest;Desea habilitar al usuario?", "warning");
    };

    $(document).ready(function () {
        jQuery("#GridId").jqGrid({
            url: '<c:url value='/management/user/list.json' />',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Usuario', 'Nombre completo', 'Correo electr&oacute;nico', 'Perfil', 'Habilitado', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'username', index: 'username', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'fullname', index: 'fullname', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'email', index: 'email', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'role', index: 'role', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'enabled', index: 'enabled', hidden: true },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPager',
            sortname: 'username',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar usuario\" onclick=\"window.upsert('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                    if (enabled == "true") {
                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Deshabilitar usuario\" onclick=\"window.disable('" + cl + "');\"><span class=\"glyphicon glyphicon-remove\"></span></a>";
                    } else {
                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Habilitar usuario\" onclick=\"window.enable('" + cl + "');\"><span class=\"glyphicon glyphicon-ok\"></span></a>";
                    }
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
            add: true, addfunc: window.upsert, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
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
                                            $("#GridId").jqGrid('exportarExcelCliente',{nombre:"HOJATEST",formato:"excel"});
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

<h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Usuarios</h2>

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