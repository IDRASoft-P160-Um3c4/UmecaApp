<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        upsertCourse = function (id) {
            window.showUpsertWithIdEmployee(id, "#angJsjqGridIdCourse", "<c:url value='/humanResources/digitalRecord/upsertCourse.html'/>", "#GridIdCourse", undefined, ${idEmployee});
        };

        deleteCourse = function (id) {
            window.showObsolete(id, "#angJsjqGridIdCourse", "<c:url value='/humanResources/digitalRecord/deleteCourse.json'/>", "#GridIdCourse");
        };

        jQuery("#GridIdCourse").jqGrid({
            autoencode: true,
            url: '<c:url value="/humanResources/digitalRecord/listSchoolCourses.json?id="/>' +${idEmployee},
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Tipo', 'Nombre', 'Lugar', 'Documento', 'Acci&oacute;n'],
            colModel: [
                {name: 'id', index: 'id', hidden: true},
                {
                    name: 'courseType',
                    index: 'courseType',
                    width: 120,
                    align: "center",
                    sorttype: 'string',
                    searchoptions: {sopt: ['bw']}
                },
                {
                    name: 'name',
                    index: 'courseType',
                    width: 120,
                    align: "center",
                    sorttype: 'string',
                    searchoptions: {sopt: ['bw']}
                },
                {
                    name: 'place',
                    index: 'courseType',
                    width: 120,
                    align: "center",
                    sorttype: 'string',
                    search: false
                },
                {
                    name: 'documentType',
                    index: 'courseType',
                    width: 120,
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
            pager: '#GridPagerCourse',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar curso\" onclick=\"window.upsertCourse('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar curso\" onclick=\"window.deleteCourse('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridIdCourse").jqGrid('navGrid', '#GridPagerCourse', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertCourse, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false
        });

        jQuery("#GridIdCourse").jqGrid('filterToolbar', {
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
        <div id="angJsjqGridIdCourse" ng-controller="modalDlgController">
            <table id="GridIdCourse" class="element-center" style="margin: auto"></table>
            <div id="GridPagerCourse"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
    </div>
</div>