<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>

    upsertAgreement = function (id) {
        window.showUpsertWithIdEmployee(id, "#angJsjqGridIdAgreement", "<c:url value='/humanResources/digitalRecord/upsertAgreement.html'/>", "#GridIdAgreement", undefined, ${idEmployee});
    };

    deleteAgreement = function (id) {

    };

    $(document).ready(function () {
        jQuery("#GridIdAgreement").jqGrid({
            url: '#',
            autoencode: true,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID','isObsolete','statusId', 'Acuerdo', 'Estado', 'Acci&oacute;n'],
            colModel: [
                {name: 'id', index: 'id', hidden: true},
                {name: 'isObsolete', index: 'isObsolete', hidden: true},
                {name: 'statusId', index: 'statusId', hidden: true},
                {
                    name: 'title',
                    index: 'title',
                    width: 350,
                    align: "center",
                    sorttype: 'string',
                    searchoptions: {sopt: ['bw']}
                },
                {
                    name: 'statusName',
                    index: 'statusName',
                    width: 150,
                    align: "center",
                    sorttype: 'string',
                    searchoptions: {sopt: ['bw']}
                },
                {
                    name: 'Action',
                    width: 70,
                    align: "center",
                    sortable: false,
                    search: false,
                    formatter: window.actionFormatter
                }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerAgreement',
            sortname: 'id',
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
                    $(this).jqGrid('setRowData', ids[i], {Action: be});
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

        jQuery("#GridIdAgreement").jqGrid('navGrid', '#GridPagerAgreement', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: window.upsertVictim, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false
        });

        jQuery("#GridIdAgreement").jqGrid('filterToolbar', {
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

        <div id="angJsjqGridIdAgreement" ng-controller="modalDlgController">
            <table id="GridIdAgreement" class="element-center" style="margin: auto"></table>
            <div id="GridPagerAgreement"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>