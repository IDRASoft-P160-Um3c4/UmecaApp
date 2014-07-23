<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    window.upsertJob = function(id) {
        window.showUpsertWithIdCase(id, "#angJsjqGridIdJob", "<c:url value='/reviewer/meeting/job/upsert.html'/>", "#GridIdJob",undefined, ${m.caseDetention.id});
    };

    window.deleteJob = function (id) {
        window.showObsolete(id, "#angJsjqGridIdJob", "<c:url value='/reviewer/meeting/job/delete.json'/>", "#GridIdJob");
    };

    $(document).ready(function() {
        jQuery("#GridIdJob").jqGrid({
            url: '<c:url value='/reviewer/meeting/listJob.json?idCase=${m.caseDetention.id}' />',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Empresa','Puesto','Patrón','Teléfono','Tipo','TipoId','Acción'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'company', index: 'company', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'post', index: 'post', width: 150, align: "center", sorttype: 'string',search: false },
                { name: 'nameHead', index: 'nameHead', width: 160, align: "center", search: false },
                { name: 'phone', index: 'phone', width: 150, align: "center", search: false },
                { name: 'registerTypeString', index: 'registerTypeString', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'registerTypeId', index: 'registerTypeId', hidden: true},
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerJob',
            sortname: 'registerTypeId',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar usuario\" onclick=\"window.upsertJob('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Deshabilitar usuario\" onclick=\"window.deleteJob('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
                          $(this).jqGrid('setRowData', ids[i], { Action: be });
                }
            },
            loadComplete : function() {
                var table = this;
                setTimeout(function(){
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            }
        });

        jQuery("#GridIdJob").jqGrid('navGrid', '#GridPagerJob', {
            edit: false, editicon : 'icon-pencil blue',
            add: true, addfunc: window.upsertJob, addicon : 'icon-plus-sign purple',
            refresh: true, refreshicon : 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridIdJob").jqGrid('filterToolbar', {
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
        <h2> <i class="pink icon-briefcase  bigger-100">&nbsp;</i>Historia Laboral</h2>
        <br/>
        <div id="angJsjqGridIdJob" ng-controller="modalDlgController">
            <table id="GridIdJob" class="element-center" style="margin: auto"></table>
            <div id="GridPagerJob"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>
    </div>
</div>
