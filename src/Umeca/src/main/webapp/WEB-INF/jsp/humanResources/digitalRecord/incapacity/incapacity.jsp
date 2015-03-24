<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
    $(document).ready(function () {

        upsertIncapacity = function (id) {
            window.showUpsertWithIdEmployee(id, "#angJsjqGridIncapacity", "<c:url value='/humanResources/digitalRecord/upsertIncapacity.html'/>", "#GridIncapacity", undefined, ${idEmployee});
        };

        deleteIncapacity = function (id) {
            window.showObsolete(id, "#angJsjqGridIncapacity", "<c:url value='/humanResources/digitalRecord/deleteIncapacity.json'/>", "#GridIncapacity");
        };

        $(document).ready(function () {
            jQuery("#GridIncapacity").jqGrid({
                autoencode: true,
                url: '<c:url value="/humanResources/digitalRecord/listIncapacity.json?id="/>' +${idEmployee},
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Descripci&oacute;n', 'Fecha incio', 'Fecha fin', 'Comentarios', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'description',
                        index: 'description',
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
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar incapacidad\" onclick=\"window.upsertIncapacity('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar incapacidad\" onclick=\"window.deleteIncapacity('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        })
        ;
    })
    ;

</script>

<div class="row element-center">

    <input type="hidden" id="hidIdCase" value="{{fm.objView.idCase}}"/>
    <input type="hidden" id="urlGridIncapacity" value="listIncapacity.json?idCase={{fm.objView.idCase}}"/>

    <div class="col-xs-12">
        <h2><i class="purple icon-camera bigger-100">&nbsp;</i>Incapacidades</h2>
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
