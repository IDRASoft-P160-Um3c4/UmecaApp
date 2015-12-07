<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        upsertIncident = function (id) {
            window.showUpsertWithIdEmployee(id, "#angJsjqGridIdIncident", "<c:url value='/humanResources/digitalRecord/upsertIncident.html'/>", "#GridIdIncident", undefined, ${idEmployee});
        };

        deleteIncident = function (id) {
            window.showObsolete(id, "#angJsjqGridIdIncident", "<c:url value='/humanResources/digitalRecord/deleteIncident.json'/>", "#GridIdIncident");
        };

        jQuery("#GridIdIncident").jqGrid({
            autoencode: true,
            url: '<c:url value="/humanResources/digitalRecord/listIncident.json?id="/>' +${idEmployee},
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'fileId', 'Tipo', 'Raz&oacute;n', 'Fecha', 'Comentarios', 'Acci&oacute;n'],
            colModel: [
                {name: 'id', index: 'id', hidden: true},
                {name: 'fileId', index: 'fileId', hidden: true},
                {
                    name: 'incidentType',
                    index: 'incidentType',
                    width: 170,
                    align: "center",
                    sorttype: 'string',
                    searchoptions: {sopt: ['bw']}
                },
                {name: 'reason', index: 'reason', width: 110, align: "center", sorttype: 'string', search: false},
                {
                    name: 'incidentDate',
                    index: 'incidentDate',
                    width: 120,
                    align: "center",
                    sorttype: 'string',
                    search: false
                },
                {
                    name: 'comments',
                    index: 'comments',
                    width: 120,
                    align: "center",
                    sortable: false,
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
            pager: '#GridPagerIncident',
            sortname: 'id',
            height: 200,
            viewrecords: true,
            shrinkToFit: false,
            sortorder: "asc",
            caption: "&nbsp;",
            altRows: true,
            gridComplete: function () {
                var ids = $(this).jqGrid('getDataIDs');
                var fId = $(this).jqGrid('getCol', 'fileId', false);
                for (var i = 0; i < ids.length; i++) {
                    var cl = ids[i];
                    var _f = fId[i];
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar incidencia\" onclick=\"window.upsertIncident('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                    be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar incidencia\" onclick=\"window.deleteIncident('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
                    be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar archivo del incidente\" onclick=\"window.download('" + _f + "');\"><i class=\"glyphicon glyphicon-cloud-download\"></i></a>  ";
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

        jQuery("#GridIdIncident").jqGrid('navGrid', '#GridPagerIncident', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: window.upsertIncident, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false
        });

        jQuery("#GridIdIncident").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });

    });

</script>

<div class="row element-center">
    <h2><i class="green icon-briefcase bigger-100">&nbsp;</i>Incidentes</h2>
    <br/>

    <div class="col-xs-12">
        <div id="angJsjqGridIdIncident" ng-controller="modalDlgController">
            <table id="GridIdIncident" class="element-center" style="margin: auto"></table>
            <div id="GridPagerIncident"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>
