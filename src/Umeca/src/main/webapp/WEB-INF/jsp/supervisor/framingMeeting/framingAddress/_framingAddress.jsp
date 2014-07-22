<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>

    /*
     window.deleteJob = function (id) {
     window.showObsolete(id, "#angJsjqGridIdJob", "<c:url value='/reviewer/meeting/job/delete.json'/>", "#GridIdJob");
     };*/

    $(document).ready(function () {

        var urlGridAddress = $('#urlGridAddress').attr("value");
        var idCase = $('#hidIdCaseAdd').attr("value");
        var canTerminte = $('#canTerminateAddress').attr("value");

        upsertAddress = function (id) {
            if (canTerminte == true)
                window.showUpsertWithIdCase(id, "#angJsjqGridIdAddress", "<c:url value='/supervisor/framingMeeting/address/upsert.html'/>", "#GridAddress", undefined, idCase);
        };


        deleteAddress = function (id) {
            if (canTerminte == true)
                window.showObsolete(id, "#angJsjqGridIdAddress", "<c:url value='/supervisor/framingMeeting/address/delete.json'/>", "#GridAddress");
        };


        jQuery("#GridAddress").jqGrid({
            url: urlGridAddress,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Direcci�n', 'Acci�n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'fullAddress', index: 'fullAddress', width: 600, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerAddress',
            sortname: 'fullAddress',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar domicilio\" onclick=\"upsertAddress('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar domicilio\" onclick=\"deleteAddress('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridAddress").jqGrid('navGrid', '#GridPagerAddress', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertAddress, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridAddress").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    });

</script>

<div class="row element-center">

    <input type="hidden" id="hidIdCaseAdd" value="{{fm.objView.idCase}}"/>
    <input type="hidden" id="urlGridAddress" value="listAddress.json?idCase={{fm.objView.idCase}}"/>
    <input type="hidden" id="canTerminateAddress" value="{{fm.objView.canTerminate}}"/>

    <div class="col-xs-12">
        <h2><i class="blue icon-group bigger-100">&nbsp;</i>Domicilios</h2>
        <br/>

        <div id="angJsjqGridIdAddress" ng-controller="modalDlgController">
            <table id="GridAddress" class="element-center" style="margin: auto"></table>
            <div id="GridPagerAddress"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>