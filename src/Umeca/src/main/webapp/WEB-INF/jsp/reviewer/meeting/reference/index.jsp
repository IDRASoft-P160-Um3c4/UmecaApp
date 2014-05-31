<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<script>
    window.upsertReference = function(id) {
        window.showUpsertWithIdCase(id, "#angJsjqGridIdReference", "/reviewer/meeting/reference/upsert.html", "#GridIdReference",undefined, ${m.caseDetention.id});
    };

    window.delete = function (id) {
        window.showObsolete(id, "#angJsjqGridIdReference", "/reviewer/meeting/reference/delete.json", "#GridIdReference");
    };

    $(document).ready(function() {
        jQuery("#GridIdReference").jqGrid({
            url: '<c:url value='/reviewer/meeting/listReference.json?idCase=${m.caseDetention.id}' />',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre','Relación','Edad','Teléfono', 'Acción'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'fullName', index: 'fulName', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'relName', index: 'relName', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'age', index: 'age', width: 160, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'phone', index: 'phone', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerReference',
            sortname: 'fullName',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar referencia\" onclick=\"window.upsertReference('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar referencia\" onclick=\"window.delete('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridIdReference").jqGrid('navGrid', '#GridPagerReference', {
            edit: false, editicon : 'icon-pencil blue',
            add: true, addfunc: window.upsertReference, addicon : 'icon-plus-sign purple',
            refresh: true, refreshicon : 'icon-refresh green',
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
        <h2><i class="red icon-list bigger-100"></i> &nbsp;Referencias personales</h2>
        <br/>
        <div id="angJsjqGridIdReference" ng-controller="modalDlgController">
            <table id="GridIdReference" class="element-center" style="margin: auto"></table>
            <div id="GridPagerReference"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>
    </div>
</div>
