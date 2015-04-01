<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <title>Personal UMECA</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>


        upsertMinute = function () {
            var canAdd = ${canAdd};
            if (canAdd == true) {
                var url = "<c:url value='/shared/minute/upsertMinute.html'/>";
                window.goToUrlMvcUrl(url);
            }
        };

        editMinute = function (id) {
            var url = "<c:url value='/shared/minute/upsertMinute.html?id='/>" + id;
            window.goToUrlMvcUrl(url);
        };

        $(document).ready(function () {
            jQuery("#GridMinuteId").jqGrid({
                url: '<c:url value='/shared/minute/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'isObsolete', 'Fecha', 'Hora', 'Lugar', 'Encargado', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'isFinished', index: 'isObsolete', hidden: true},
                    {
                        name: 'minuteDate',
                        index: 'minuteDate',
                        width: 120,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'startTime',
                        index: 'startTime',
                        width: 120,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'place',
                        index: 'place',
                        align: "center",
                        width: 300,
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'attendant',
                        index: 'attendant',
                        align: "center",
                        width: 220,
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'Action',
                        index: 'Action',
                        width: 110,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
                    },
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'id',
                height: 350,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "desc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    var obsolete = $(this).jqGrid('getCol', 'isFinished', false);
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var be = "";

                        if (obsolete[i] == 'false') {
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar minuta\" onclick=\"editMinute(" + cl + ");\"><span class=\"glyphicon glyphicon-list\"></span></a>";
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Cerrar minuta\" onclick=\"closeMinute('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
                        }

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar documento de minuta\" onclick=\"downloadMinute('" + cl + "');\"><span class=\"glyphicon glyphicon-file\"></span></a>";
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

            jQuery("#GridMinuteId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: true, addfunc: upsertMinute, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridMinuteId").jqGrid('navSeparatorAdd', '#GridPager');
            jQuery("#GridMinuteId").jqGrid('navButtonAdd', "#GridPager",
                    {
                        caption: "",
                        title: "Exportar a excel",
                        buttonicon: 'icon-download-alt blue',

                        onClickButton: function () {
                            try {
                                $("#GridMinuteId").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                            } catch (e) {
                            }
                        }
                    });

            jQuery("#GridMinuteId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        })
        ;

    </script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Minutas de trabajo
    </h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridMinuteId" class="element-center" style="margin: auto"></table>
        <div id="GridPager"></div>
        <div class="blocker" ng-show="working">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>