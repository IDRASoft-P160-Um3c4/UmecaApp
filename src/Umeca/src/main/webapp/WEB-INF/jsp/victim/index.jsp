<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script src="${pageContext.request.contextPath}/assets/scripts/app/victim/victimCtrl.js"></script>
<script>
    window.upsertVictim = function (id) {
        window.showUpsertWithIdCase(id, "#angJsjqGridIdReference", "<c:url value='/victim/upsert.html'/>", "#GridIdReference", undefined, ${idCase});
    };

    window.deleteVictim = function (id) {
        window.showObsolete(id, "#angJsjqGridIdReference", "<c:url value='/victim/delete.json'/>", "#GridIdReference");
    };

    $(document).ready(function () {
        jQuery("#GridIdReference").jqGrid({
            url: '<c:url value='/shared/victim/listVictim.json?idCase=${idCase}' />',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre', 'Relaci&oacute;n', 'Edad', 'Tel&eacute;fono', 'Direcci&oacute;n', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'fullname', index: 'fullname', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'relName', index: 'relName', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'age', index: 'age', width: 60, align: "center", sorttype: 'string', search: false },
                { name: 'phone', index: 'phone', width: 100, align: "center", search: false  },
                { name: 'addressString', index: 'addressString', width: 200, align: "center", search: false  },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerReference',
            sortname: 'fullname',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar v&iacute;ctima\" onclick=\"window.upsertVictim('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar v&iacute;ctima\" onclick=\"window.deleteVictim('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridIdReference").jqGrid('navGrid', '#GridPagerReference', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: window.upsertVictim, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
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

<div class="row element-center">
    <div class="col-xs-12">
        <br/>

        <div id="angJsjqGridIdReference" ng-controller="modalDlgController">
            <table id="GridIdReference" class="element-center" style="margin: auto"></table>
            <div id="GridPagerReference"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>
