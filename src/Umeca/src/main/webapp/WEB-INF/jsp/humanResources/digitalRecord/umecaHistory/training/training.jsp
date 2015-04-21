<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        upsertTraining = function (id) {
            window.showUpsertWithIdEmployee(id, "#angJsjqGridIdTraining", "<c:url value='/humanResources/digitalRecord/upsertTraining.html'/>", "#GridIdTraining", undefined, ${idEmployee});
        };

        deleteTraining = function (id) {
            window.showObsolete(id, "#angJsjqGridIdTraining", "<c:url value='/humanResources/digitalRecord/deleteTraining.json'/>", "#GridIdTraining");
        };

        jQuery("#GridIdTraining").jqGrid({
            autoencode: true,
            url: '<c:url value="/humanResources/digitalRecord/listTraining.json?id="/>' +${idEmployee},
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'fileID', 'Nombre', 'Lugar', 'Duraci&oacute;n', 'Fecha inicio', 'Fecha fin', 'Acci&oacute;n'],
            colModel: [
                {name: 'id', index: 'id', hidden: true},
                {name: 'fileId', index: 'fileId', hidden: true},
                {
                    name: 'name',
                    index: 'name',
                    width: 110,
                    align: "center",
                    sorttype: 'string',
                    search: false
                },
                {
                    name: 'place',
                    index: 'place',
                    width: 110,
                    align: "center",
                    sorttype: 'string',
                    search: false
                },
                {
                    name: 'duration',
                    index: 'duration',
                    width: 110,
                    align: "center",
                    sortable: false,
                    search: false
                },
                {
                    name: 'start',
                    index: 'start',
                    width: 100,
                    align: "center",
                    sorttype: 'string',
                    search: false
                },
                {
                    name: 'end',
                    index: 'end',
                    width: 100,
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
                },

            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerTraining',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar capacitaci&oacute;n\" onclick=\"window.upsertTraining('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                    be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar capacitaci&oacute;n\" onclick=\"window.deleteTraining('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
                    be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar archivo del curso\" onclick=\"window.download('" + _f + "');\"><i class=\"glyphicon glyphicon-cloud-download\"></i></a>  ";
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

        jQuery("#GridIdTraining").jqGrid('navGrid', '#GridPagerTraining', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertTraining, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false
        });

        jQuery("#GridIdTraining").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });

    });

</script>

<div class="row element-center">

    <h2><i class="green icon-briefcase bigger-100">&nbsp;</i>Cursos / Capacitaciones</h2>
    <br/>

    <div class="col-xs-12">
        <div id="angJsjqGridIdTraining" ng-controller="modalDlgController">
            <table id="GridIdTraining" class="element-center" style="margin: auto"></table>
            <div id="GridPagerTraining"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>