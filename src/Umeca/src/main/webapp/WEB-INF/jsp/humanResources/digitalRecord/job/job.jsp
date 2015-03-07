<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        upsertJob = function (id) {
            window.showUpsert(id, "#angJsjqGridIdJob", "<c:url value='/humanResources/digitalRecord/upsertJob.html'/>", "#GridIdJob");
        };

        deleteJob = function (id) {
            window.showObsolete(id, "#angJsjqGridIdJob", "<c:url value='/humanResources/digitalRecord/doDeleteJob.json'/>", "#GridIdJob");
        };

        $(document).ready(function () {
            jQuery("#GridIdJob").jqGrid({
                autoencode: true,
                url: '<c:url value="/humanResources/digitalRecord/listJob.json?id="/>' +${idEmployee},
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Empresa', 'Puesto', 'Fecha de inicio', 'Fecha de fin', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'company',
                        index: 'company',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {name: 'post', index: 'post', width: 200, align: "center", sorttype: 'string', search: false},
                    {name: 'iDate', index: 'iDate', width: 150, align: "center", search: false},
                    {name: 'eDate', index: 'eDate', width: 150, align: "center", search: false},
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
                pager: '#GridPagerJob',
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
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar trabajo\" onclick=\"window.upsertJob('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar trabajo\" onclick=\"window.deleteJob('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

            jQuery("#GridIdJob").jqGrid('navGrid', '#GridPagerJob', {
                edit: false, editicon: 'icon-pencil blue',
                add: true, addfunc: window.upsertJob, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridIdJob").jqGrid('filterToolbar', {
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

    <div class="col-xs-12">
        <h2><i class="green icon-briefcase bigger-100">&nbsp;</i>Historia laboral</h2>
        <br/>

        <div id="angJsjqGridIdJob" ng-controller="modalDlgController">
            <table id="GridIdJob" class="element-center" style="margin: auto"></table>
            <div id="GridPagerJob"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
        <br/>

    </div>
</div>
