<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
* Project: Umeca
* User: Israel
* Date: 4/30/14
* Time: 9:53 AM
-->

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/sourceCtrl.js"></script>
    <title>Fuentes de verificaci&oacute;n</title>
    <style>
        .ui-jqgrid tr.jqgrow td {
            white-space: normal !important;
        }
    </style>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>

        window.upsertSource = function(id) {
            window.showUpsertWithIdCase(id, "#angJsjqGridId", "<c:url value='/reviewer/verification/source/upsert.html'/>", "#GridId",undefined, ${idCase});
        };

        window.cancelShowSource = function(){
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/sources.html?id=${idCase}'/>");
        }

        window.terminateSuccess = function(){
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/index.html'/>");
        }

        $(document).ready(function() {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/reviewer/verification/listSourceAdd.json?id=${idCase}' />',
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Nombre','Edad', 'Parentesco', 'Direcci&oacute;n', 'Tel&eacute;fono','Estatus','Complete','IdCase', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'fullName', index: 'fullName', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'age', index: 'age', width: 80, align: "center", sorttype: 'string', search: false },
                    { name: 'relationshipString', index: 'relationshipString', width: 100, align: "center", search: false },
                    { name: 'address', index: 'address', width: 300, align: "center", sorttype: 'string', search: false },
                    { name: 'phone', index: 'phone', width: 150, align: "center", sorttype: 'string', search: false },
                    { name: 'statusString', index: 'statusString', width: 200, align: "center", sorttype: 'string', search: false },
                    { name: 'dateComplete', index: 'dateComplete',hidden:true},
                    { name: 'idCase', index: 'idCase', hidden:true},
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'fullName',
                height: 300,
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
                        var dateComplete = row.dateComplete;
                        var be="";
                        if (dateComplete == "") {
                            be = "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar fuente\" onclick=\"window.upsertSource('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></a>";
                        }
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

            jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false, editicon : 'icon-notes blue',
                add: true, addfunc: window.upsertSource, addicon : 'icon-plus-sign purple',
                refresh: true, refreshicon : 'icon-refresh green',
                del: false,
                search: false});

            jQuery("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>

    <h2 class="element-center"><i class=" icon-group"></i>&nbsp;&nbsp;Fuentes de verificaci&oacute;n</h2>
    <%@ include file="/WEB-INF/jsp/reviewer/meeting/imputedName.jsp" %>
    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridId" class="element-center" style="margin: auto"></table>
        <div id="GridPager"></div>
        <div class="blocker" ng-show="working">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
            </div>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-12">
            <div ng-show="MsgError" class="alert alert-danger element-center">
                {{MsgError}}
            </div>
        </div>
    </div>
    <br/>
    <div class="row" ng-controller="addSourcesController">
        <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelShowSource()">
                        Regresar
                    </span>
                    <span class="btn btn-primary btn-sm" ng-click="terminateAddSource('<c:url value="/reviewer/verification/addSource/terminate.json?idCase=${idCase}"/>');" ng-disabled="WaitFor == true">
                        <span class="icon-group align-top- bigger-125"></span> &nbsp;&nbsp;Finalizar agregado de fuentes
                    </span>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>