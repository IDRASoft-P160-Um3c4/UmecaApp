<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<style>
    .ui-jqgrid tr.jqgrow td {
        white-space: normal !important;
    }
</style>
<script>
    window.upsertAddress = function(id) {
        window.showUpsertWithIdCase(id, "#angJsjqGridIdAddress", "<c:url value='/reviewer/meeting/address/upsert.html'/>", "#GridIdAddress",undefined, ${m.caseDetention.id});
    };

    window.deleteAddress = function (id) {
        window.showObsolete(id, "#angJsjqGridIdAddress", "<c:url value='/reviewer/meeting/address/delete.json'/>", "#GridIdAddress");
    };

    $(document).ready(function() {

        jQuery("#GridIdAddress").jqGrid({
            url: '<c:url value='/reviewer/meeting/listAddress.json?idCase=${m.caseDetention.id}'/>',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Direccion','Tipo de domicilio','Tiempo de recidencia','Tipo de propiedad', 'Acción'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'domicile', index: 'domicile', width: 350, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'timeLive', index: 'timeLive', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'registerTypeString', index: 'registerTypeString', width: 160, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'belongString', index: 'belongString', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerAddress',
            sortname: 'registerTypeString',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar domicilio\" onclick=\"window.upsertAddress('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar domicilio\" onclick=\"window.deleteAddress('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridIdAddress").jqGrid('navGrid', '#GridPagerAddress', {
            edit: false, editicon : 'icon-pencil blue',
            add: true, addfunc: window.upsertAddress, addicon : 'icon-plus-sign purple',
            refresh: true, refreshicon : 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridIdAddress").jqGrid('filterToolbar', {
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
        <h2> <i class="green  icon-home  bigger-100"></i>&nbsp;Domicilios</h2>
        <br/>
        <div id="angJsjqGridIdAddress" ng-controller="modalDlgController">
            <table id="GridIdAddress" class="element-center" style="margin: auto"></table>
            <div id="GridPagerAddress"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>
    </div>
</div>
