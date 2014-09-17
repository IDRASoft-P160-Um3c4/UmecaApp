<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        var urlGridHousemate = $('#urlGridHousemate').attr("value");
        var idCase = $('#hidIdCase').attr("value");

        var canTerminate = $('#canTerminateHousemate').attr("value");

        upsertHousemate = function (id) {
            if (canTerminate == 'true')
                window.showUpsertWithIdCase(id, "#angJsjqGridIdHouseMate", "<c:url value='/supervisor/framingMeeting/housemate/upsert.html'/>", "#GridHouseMate", undefined, idCase);
        };

        deleteHousemate = function (id) {
            if (canTerminate == 'true')
                window.showObsolete(id, "#angJsjqGridIdHouseMate", "<c:url value='/supervisor/framingMeeting/reference/delete.json'/>", "#GridHouseMate");
        };

        jQuery("#GridHouseMate").jqGrid({
            url: urlGridHousemate,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre', 'Edad', 'Parentesco', 'Ocupaci&oacute;n', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'name', index: 'name', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'age', index: 'age', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'relationshipName', index: 'relationshipName', width: 160, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'occupation', index: 'occupation', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerHouseMate',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar referencia\" onclick=\"window.upsertHousemate('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar referencia\" onclick=\"window.deleteHousemate('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridHouseMate").jqGrid('navGrid', '#GridPagerHouseMate', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertHousemate, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridHouseMate").jqGrid('filterToolbar', {
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
    <input type="hidden" id="urlGridHousemate" value="listHousemate.json?idCase={{fm.objView.idCase}}"/>
    <input type="hidden" id="canTerminateHousemate" value="{{fm.objView.canTerminate}}"/>

    <div class="col-xs-12">
        <h2><i class="blue icon-group bigger-100">&nbsp;</i>Personas con las que vive</h2>
        <br/>

        <div id="angJsjqGridIdHouseMate" ng-controller="modalDlgController">
            <table id="GridHouseMate" class="element-center" style="margin: auto"></table>
            <div id="GridPagerHouseMate"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>
