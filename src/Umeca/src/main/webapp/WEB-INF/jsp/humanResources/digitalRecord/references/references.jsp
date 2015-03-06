<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        var urlGridReferences = $('#urlGridReferences').attr("value");
        var idCase = $('#hidIdCase').attr("value");

        upsertReferences = function (id) {
            //if (canTerminate == 'true')
            window.showUpsertWithIdCase(id, "#angJsjqGridIdReferences", "<c:url value='/supervisor/framingMeeting/References/upsert.html'/>", "#GridReferences", undefined, idCase);
        };

        deleteReferences = function (id) {
            //if (canTerminate == 'true')
            window.showObsolete(id, "#angJsjqGridIdReferences", "<c:url value='/supervisor/framingMeeting/reference/delete.json'/>", "#GridReferences");
        };

        jQuery("#GridReferences").jqGrid({
            autoencode: true,
//            url: urlGridReferences,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre', 'Relaci&oacute;n', 'Edad', 'Tel&eacute;fono', 'Tiempo de relaci&oacute;n', 'Acci&oacute;n'],
            colModel: [
                {name: 'id', index: 'id', hidden: true},
                {
                    name: 'name',
                    index: 'name',
                    width: 190,
                    align: "center",
                    sorttype: 'string',
                    searchoptions: {sopt: ['bw']}
                },
                {
                    name: 'relName',
                    index: 'relName',
                    width: 190,
                    align: "center",
                    sorttype: 'string',
                    searchoptions: {sopt: ['bw']}
                },
                {
                    name: 'age',
                    index: 'age',
                    width: 80,
                    align: "center",
                    sortable: false,
                    search: false
                },
                {
                    name: 'phone',
                    index: 'phone',
                    width: 100,
                    align: "center",
                    sortable: false,
                    search: false
                },
                {
                    name: 'relTime',
                    index: 'relTime',
                    width: 100,
                    align: "center",
                    search: false
                },
                {
                    name: 'Action',
                    width: 60,
                    align: "center",
                    sortable: false,
                    search: false,
                    formatter: window.actionFormatter
                }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerReferences',
            sortname: 'id',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar referencia\" onclick=\"window.upsertReferences('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar referencia\" onclick=\"window.deleteReferences('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridReferences").jqGrid('navGrid', '#GridPagerReferences', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertReferences, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false
        });

        jQuery("#GridReferences").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    });

</script>

<div class="row element-center">

    <input type="hidden" id="hidIdCase" value="{{fm.objView.idCase}}"/>
    <input type="hidden" id="urlGridReferences" value="listReferences.json?idCase={{fm.objView.idCase}}"/>

    <div class="col-xs-12">
        <div ng-show="ReferencesSuccessMsg&&ReferencesSuccessMsg!=''"
             class="col-xs-12 alert alert-success element-center success-font" ng-bind-html="ReferencesSuccessMsg">
        </div>
        <div ng-show="ReferencesErrorMsg&&ReferencesErrorMsg!=''" class="alert alert-danger element-center error-font"
             ng-bind-html="ReferencesErrorMsg">
        </div>
    </div>
    <br/>
    <br/>

    <div class="col-xs-12">
        <h2><i class="red icon-group bigger-100">&nbsp;</i>Referencias personales</h2>
        <br/>
        <div id="angJsjqGridIdReferences" ng-controller="modalDlgController">
            <table id="GridReferences" class="element-center" style="margin: auto"></table>
            <div id="GridPagerReferences"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
        <br/>
        <br/>

    </div>
</div>
