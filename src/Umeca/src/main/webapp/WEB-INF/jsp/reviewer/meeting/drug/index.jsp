<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<script>
    window.upsertDrug = function(id) {
        window.showUpsertWithIdCase(id, "#angJsjqGridIdDrug", "<c:url value='/reviewer/meeting/drug/upsert.html'/>", "#GridIdDrug",undefined, ${m.caseDetention.id});
    };

    window.delete = function (id) {
        window.showObsolete(id, "#angJsjqGridIdDrug", "<c:url value='/reviewer/meeting/drug/delete.json'/>", "#GridIdDrug");
    };


    $(document).ready(function() {
        jQuery("#GridIdDrug").jqGrid({
            url: '<c:url value='/reviewer/meeting/listDrug.json?idCase=${m.caseDetention.id}' />',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Sustancia','Periodicidad','Cantidad','�ltimo consumo', 'Acci�n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'drugName', index: 'drugName', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'perName', index: 'perName', width: 150, align: "center",search: false},
                { name: 'quantity', index: 'quantity', width: 160, align: "center",search: false},
                { name: 'lastUseFormat', index: 'lastUseFormat', width: 150, align: "center",search: false},
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerDrug',
            sortname: 'drugName',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar sustancia\" onclick=\"window.upsertDrug('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Elimnar sustancia\" onclick=\"window.delete('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridIdDrug").jqGrid('navGrid', '#GridPagerDrug', {
            edit: false, editicon : 'icon-pencil blue',
            add: true, addfunc: window.upsertDrug, addicon : 'icon-plus-sign purple',
            refresh: true, refreshicon : 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridIdDrug").jqGrid('filterToolbar', {
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
        <h2> <i class="green icon-warning-sign  bigger-100">&nbsp;</i>Consumo de sustancias</h2>
        <br/>
        <div id="angJsjqGridIdDrug" ng-controller="modalDlgController">
            <table id="GridIdDrug" class="element-center" style="margin: auto"></table>
            <div id="GridPagerDrug"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>
    </div>
</div>
