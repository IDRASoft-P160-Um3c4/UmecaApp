<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        upsertUmecaJob = function (id) {
            window.showUpsertWithIdEmployee(id, "#angJsjqGridIdUmecaJob", "<c:url value='/humanResources/digitalRecord/upsertUmecaJob.html'/>", "#GridIdUmecaJob", undefined, ${idEmployee});
        };

        deleteUmecaJob = function (id) {
            window.showObsolete(id, "#angJsjqGridIdUmecaJob", "<c:url value='/humanResources/digitalRecord/doDeleteUmecaJob.json'/>", "#GridIdUmecaJob");
        };

        $(document).ready(function () {
            jQuery("#GridIdUmecaJob").jqGrid({
                autoencode: true,
                url: '<c:url value="/humanResources/digitalRecord/listUmecaJob.json?id="/>' +${idEmployee},
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Puesto', 'Jefe inmediato', 'Distrito', 'Salario', 'Fecha de inicio', 'Fecha de fin', 'Tipo', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'umecaPost',
                        index: 'umecaPost',
                        width: 120,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'nameHead',
                        index: 'nameHead',
                        width: 120,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'district',
                        index: 'district',
                        width: 100,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'salary',
                        index: 'salary',
                        width: 100,
                        align: "center",
                        search: false,
                        sortable: false
                    },
                    {
                        name: 'startDate',
                        index: 'startDate',
                        width: 100,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {name: 'endDate', index: 'endDate', width: 100, align: "center", sorttype: 'string', search: false},
                    {
                        name: 'registerType',
                        index: 'registerType',
                        width: 100,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
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
                pager: '#GridPagerUmecaJob',
                sortname: 'id',
                height: 300,
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
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar trabajo\" onclick=\"window.upsertUmecaJob('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar trabajo\" onclick=\"window.deleteUmecaJob('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

            jQuery("#GridIdUmecaJob").jqGrid('navGrid', '#GridPagerUmecaJob', {
                edit: false, editicon: 'icon-pencil blue',
                add: true, addfunc: window.upsertUmecaJob, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridIdUmecaJob").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });

        });
    })
    ;

</script>

<div class="row element-center">

    <div class="col-xs-12">
        <h2><i class="green icon-briefcase bigger-100">&nbsp;</i>Puestos ocupados en UMECA</h2>
        <br/>

        <div id="angJsjqGridIdUmecaJob" ng-controller="modalDlgController">
            <table id="GridIdUmecaJob" class="element-center" style="margin: auto"></table>
            <div id="GridPagerUmecaJob"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
        <br/>

    </div>
</div>
