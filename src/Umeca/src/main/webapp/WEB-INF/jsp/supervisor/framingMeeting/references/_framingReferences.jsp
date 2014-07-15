<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>

    $(document).ready(function() {

        var urlGridReferences = $('#urlGridReferences').attr("value");
        var idCase = $('#hidIdCase').attr("value");

        upsertReference = function(id) {
            window.showUpsertWithIdCase(id, "#angJsjqGridIdReferences", "<c:url value='/supervisor/framingMeeting/references/upsert.html'/>", "#GridReferences",undefined, idCase);
        };

        deleteReference = function (id) {
            window.showObsolete(id, "#angJsjqGridIdReferences", "<c:url value='/supervisor/framingMeeting/reference/delete.json'/>", "#GridReferences");
        };

        jQuery("#GridReferences").jqGrid({
            url: urlGridReferences,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre','Tel�fono','Parentesco','Direcci�n','Acci�n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'name', index: 'name', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'phone', index: 'phone', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'relationshipName', index: 'relationshipName', width: 160, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'address', index: 'address', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerReferences',
            sortname: 'name',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar usuario\" onclick=\"upsertReference('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Deshabilitar usuario\" onclick=\"deleteReference('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridReferences").jqGrid('navGrid', '#GridPagerReferences', {
            edit: false, editicon : 'icon-pencil blue',
            add: true, addfunc: upsertReference, addicon : 'icon-plus-sign purple',
            refresh: true, refreshicon : 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridReferences").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    });

</script>

<div class="row element-center">

    <input type="hidden" id="hidIdCase" value="{{fm.objView.idCase}}"/>
    <input type="hidden" id="urlGridReferences" value="listReferences.json?idCase={{fm.objView.idCase}}"/>

    <div class="col-xs-12">
        <h2> <i class="blue icon-group bigger-100">&nbsp;</i>Referencias personales</h2>
        <br/>
        <div id="angJsjqGridIdReferences" ng-controller="modalDlgController">
            <table id="GridReferences" class="element-center" style="margin: auto"></table>
            <div id="GridPagerReferences"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>
    </div>
</div>
