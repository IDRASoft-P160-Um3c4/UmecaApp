<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
    $(document).ready(function () {

        upsertVacation = function (id) {
            window.showUpsertWithIdEmployee(id, "#angJsjqGridVacation", "<c:url value='/humanResources/digitalRecord/upsertVacation.html'/>", "#GridVacation", undefined, ${idEmployee});
        };

        deleteVacation = function (id) {
            window.showObsolete(id, "#angJsjqGridVacation", "<c:url value='/humanResources/digitalRecord/deleteVacation.json'/>", "#GridVacation");
        };

        $(document).ready(function () {
            jQuery("#GridVacation").jqGrid({
                autoencode: true,
                url: '<c:url value="/humanResources/digitalRecord/listVacation.json?id="/>' +${idEmployee},
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Nombre', 'Fecha incio', 'Fecha fin', 'Comentarios', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'name',
                        index: 'name',
                        width: 220,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {name: 'start', index: 'start', width: 110, align: "center", sorttype: 'string', search: false},
                    {name: 'end', index: 'end', width: 110, align: "center", sorttype: 'string', search: false},
                    {name: 'comments', index: 'comments', width: 200, align: "center", sortable: false, search: false},
                    {
                        name: 'Action',
                        width: 65,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
                    }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPagerVacation',
                sortname: 'id',
                height: 200,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "asc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var row = $(this).getRowData(cl);
                        var enabled = row.enabled;
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar periodo vacacional\" onclick=\"window.upsertVacation('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar periodo vacacional\" onclick=\"window.deleteVacation('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
                        $(this).jqGrid('setRowData', ids[i], {Action: be});
                    }
                }

                ,
                loadComplete: function () {
                    var table = this;
                    setTimeout(function () {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                }
            });

            jQuery("#GridVacation").jqGrid('navGrid', '#GridPagerVacation', {
                edit: false, editicon: 'icon-pencil blue',
                add: true, addfunc: window.upsertVacation, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridVacation").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });

        })
        ;
    })
    ;

</script>

<div class="row element-center">

    <input type="hidden" id="hidIdCase" value="{{fm.objView.idCase}}"/>
    <input type="hidden" id="urlGridVacation" value="listVacation.json?idCase={{fm.objView.idCase}}"/>

    <div class="col-xs-12">
        <h2><i class="purple icon-camera bigger-100">&nbsp;</i>Vacaciones</h2>
        <br/>

        <div id="angJsjqGridVacation" ng-controller="modalDlgController">
            <table id="GridVacation" class="element-center" style="margin: auto"></table>
            <div id="GridPagerVacation"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>

    </div>
</div>
