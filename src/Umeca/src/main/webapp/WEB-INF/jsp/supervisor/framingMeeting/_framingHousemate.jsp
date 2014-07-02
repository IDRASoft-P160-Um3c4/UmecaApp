<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    /*window.upsertJob = function(id) {
        window.showUpsertWithIdCase(id, "#angJsjqGridIdJob", "<c:url value='/reviewer/meeting/job/upsert.html'/>", "#GridIdJob",undefined, ${m.caseDetention.id});
    };

    window.deleteJob = function (id) {
        window.showObsolete(id, "#angJsjqGridIdJob", "<c:url value='/reviewer/meeting/job/delete.json'/>", "#GridIdJob");
    };*/

    $(document).ready(function() {
        jQuery("#GridHouseMate").jqGrid({
           // url: '<c:url value='/reviewer/meeting/listJob.json?idCase=${m.caseDetention.id}' />',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Empresa','Puesto','Patr�n','Tel�fono','Tipo','TipoId','Acci�n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'company', index: 'company', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'post', index: 'post', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'nameHead', index: 'nameHead', width: 160, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'phone', index: 'phone', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'registerTypeString', index: 'registerTypeString', hidden: true, width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'registerTypeId', index: 'registerTypeId', hidden: true},
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerHouseMate',
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

        jQuery("#GridHouseMate").jqGrid('navGrid', '#GridPagerHouseMate', {
            edit: false, editicon : 'icon-pencil blue',
            add: true, addfunc: window.upsertJob, addicon : 'icon-plus-sign purple',
            refresh: true, refreshicon : 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridHouseMate").jqGrid('filterToolbar', {
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
        <h2> <i class="blue icon-group bigger-100">&nbsp;</i>Personas con las que vive</h2>
        <br/>
        <div id="angJsjqGridIdHouseMate" ng-controller="modalDlgController">
            <table id="GridHouseMate" class="element-center" style="margin: auto"></table>
            <div id="GridPagerHouseMate"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>
    </div>
</div>
