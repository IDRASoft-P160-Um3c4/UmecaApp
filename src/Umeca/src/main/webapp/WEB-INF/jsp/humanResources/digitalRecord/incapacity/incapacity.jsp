<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
    $(document).ready(function () {

        var urlGrid = $('#urlGridIncapacity').attr("value");
        var idCase = $('#hidIdCase').attr("value");

        upsertIncapacity = function (id) {
            //if (canTerminate == 'true')
            window.showUpsertWithIdCase(id, "#angJsjqGridIncapacity", "<c:url value='/supervisor/framingMeeting/incapacity/upsert.html'/>", "#GridIncapacity", undefined, idCase);
        };

        deleteIncapacity = function (id) {
            //if (canTerminate == 'true')
            window.showObsolete(id, "#angJsjqGridIncapacity", "<c:url value='/supervisor/framingMeeting/incapacity/delete.json'/>", "#GridIncapacity");
        };

        $(document).ready(function () {
            jQuery("#GridIncapacity").jqGrid({
                autoencode: true,
                url: urlGridIncapacity,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Empresa', 'Puesto', 'Patr&oacute;n', 'Tel&eacute;fono', 'Tipo', 'TipoId', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'company',
                        index: 'company',
                        width: 170,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {name: 'post', index: 'post', width: 110, align: "center", sorttype: 'string', search: false},
                    {name: 'nameHead', index: 'nameHead', width: 120, align: "center", search: false},
                    {name: 'phone', index: 'phone', width: 120, align: "center", search: false},
                    {
                        name: 'registerTypeString',
                        index: 'registerTypeString',
                        width: 120,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {name: 'registerTypeId', index: 'registerTypeId', hidden: true},
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
                pager: '#GridPagerIncapacity',
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
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar usuario\" onclick=\"window.upsertIncapacity('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Deshabilitar usuario\" onclick=\"window.deleteIncapacity('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

            jQuery("#GridIncapacity").jqGrid('navGrid', '#GridPagerIncapacity', {
                edit: false, editicon: 'icon-pencil blue',
                add: true, addfunc: window.upsertIncapacity, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridIncapacity").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });

        });
    });

</script>

<div class="row element-center">

    <input type="hidden" id="hidIdCase" value="{{fm.objView.idCase}}"/>
    <input type="hidden" id="urlGridIncapacity" value="listIncapacity.json?idCase={{fm.objView.idCase}}"/>

    <div class="col-xs-12">
        <h2><i class="gray icon-male bigger-100">&nbsp;</i>Incapacidad</h2>
        <br/>

        <div id="angJsjqGridIncapacity" ng-controller="modalDlgController">
            <table id="GridIncapacity" class="element-center" style="margin: auto"></table>
            <div id="GridPagerIncapacity"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>

    </div>
</div>
