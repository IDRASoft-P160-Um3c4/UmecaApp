<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

                upsertReference = function (id) {
                    window.showUpsertWithIdEmployee(id, "#angJsjqGridIdReference", "<c:url value='/humanResources/digitalRecord/upsertReference.html'/>", "#GridIdReference", undefined, ${idEmployee});
                };

                deleteReference = function (id) {
                    window.showObsolete(id, "#angJsjqGridIdReference", "<c:url value='/humanResources/digitalRecord/deleteReference.json'/>", "#GridIdReference");
                };

                $(document).ready(function () {
                    jQuery("#GridIdReference").jqGrid({
                        autoencode: true,
                        url: '<c:url value="/humanResources/digitalRecord/listReferences.json?id="/>' +${idEmployee},
                        datatype: "json",
                        mtype: 'POST',
                        colNames: ['ID', 'Nombre', 'Relaci&oacute;n', 'Edad', 'Tel&eacute;fono', 'Tiempo de <br/> conocerlo', 'Acci&oacute;n'],
                        colModel: [
                            {name: 'id', index: 'id', hidden: true},
                            {
                                name: 'name',
                                index: 'name',
                                width: 180,
                                align: "center",
                                sorttype: 'string',
                                searchoptions: {sopt: ['bw']}
                            },
                            {
                                name: 'relationship',
                                index: 'relationship',
                                width: 150,
                                align: "center",
                                sorttype: 'string',
                                searchoptions: {sopt: ['bw']}
                            },
                            {
                                name: 'age',
                                index: 'age',
                                width: 70,
                                align: "center",
                                sorttype: 'string',
                                search: false
                            },
                            {
                                name: 'phone',
                                index: 'phone',
                                width: 80,
                                align: "center",
                                sorttype: 'string',
                                search: false
                            },
                            {
                                name: 'timeAgo',
                                index: 'timeAgo',
                                width: 120,
                                align: "center",
                                sorttype: 'string',
                                search: false
                            },
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
                        pager: '#GridPagerReference',
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
                                var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar referencia\" onclick=\"window.upsertReference('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar referencia\" onclick=\"window.deleteReference('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

                    jQuery("#GridIdReference").jqGrid('navGrid', '#GridPagerReference', {
                        edit: false, editicon: 'icon-pencil blue',
                        add: true, addfunc: upsertReference, addicon: 'icon-plus-sign purple',
                        refresh: true, refreshicon: 'icon-refresh green',
                        del: false,
                        search: false
                    });

                    jQuery("#GridIdReference").jqGrid('filterToolbar', {
                        stringResult: true,
                        searchOperators: true,
                        searchOnEnter: true,
                        multipleSearch: true,
                        ignoreCase: true
                    });

                });
            }
    );

</script>

<div class="row element-center">

    <div class="col-xs-12">
        <div id="angJsjqGridIdReference" ng-controller="modalDlgController">
            <table id="GridIdReference" class="element-center" style="margin: auto"></table>
            <div id="GridPagerReference"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>