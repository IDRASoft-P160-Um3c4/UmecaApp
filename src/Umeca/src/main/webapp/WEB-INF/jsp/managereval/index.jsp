<!DOCTYPE html>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/uniqueDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <title>Aprobación de fuentes a verificar</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">
    <script>

        var verifySourceMethods = ${verifySources};

        function verifySources(idx) {
            angular.element($("#ConfirmBoxDialog")).scope().prompt(idx);
        }

        function autorizar(e, s) {
            if ($(e).is(":checked")) {
                if ($(s).val() == "") {
                    t.alert("Validaciones de fuentes", "Para poder autorizar una verificaci&oacute;n, hay que asignar el m&eacute;todo de verificaci&oacute;n.", "danger");
                    return false;
                }
                $(s).attr("disabled", "disabled");
            }
            else {
                $(s).removeAttr("disabled");
            }
        }

        var t = {};

        t.alert = function(title, message, icon) {
            var scope = angular.element($("#MessageBoxDlgId")).scope();
            scope.alert(title, message, icon);
            scope.$apply();
            $("#MessageBoxDlgId").modal('show');
        }

        $(document).ready(function() {
            $("#GridId").jqGrid({
                url: '<c:url value='/managereval/list.json' />',
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID','Carpeta de Investigaci&oacute;n','Nombre completo', 'Crimen', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', hidden: true },
                    { name: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullname', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'crime', width: 300, align: "center", sortable: false, search: false },
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'id',
                height: 450,
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
                        var status = row.id;
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Autorizar verificaci&oacute;n de fuentes\" onclick=\"verifySources('" + cl + "');\"><i class=\"icon-thumbs-up\"></i></a>";
                        $(this).jqGrid('setRowData', ids[i], { Action: be });
                    }
                },
                loadComplete : function() {
                    var table = this;
                    setTimeout(function(){
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },
                subGridOptions: {
                    plusicon  : "ui-icon-triangle-1-e",
                    minusicon : "ui-icon-triangle-1-s",
                    openicon  : "ui-icon-arrowreturn-1-e",
                    reloadOnExpand : false,
                    selectOnExpand : true
                },
                subGrid: true,
                subGridRowExpanded: function(subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id+"_t";
                    pager_id = "p_"+subgrid_table_id;
                    $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
                    $("#"+subgrid_table_id).jqGrid({
                        url: '<c:url value='/managereval/listVerification.json?idCase=' />' + row_id,
                        datatype: "json",
                        mtype: 'POST',
                        colNames: ['ID', 'Nombre','Relaci&oacute;n','Tel&eacute;fono', 'Direcci&oacute;n', 'Tipo de Verificaci&oacute;n', 'Autorizado'],
                        colModel: [
                            { name: 'id', hidden: true },
                            { name: 'fullName', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                            { name: 'relationshipString', width: 100, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                            { name: 'phone', width: 100, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                            { name: 'address', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                            { name: "idVerificationMethod", width: 100, align: "center", sortable: false },
                            { name: "authorized", width: 30, align: "center", sortable: false }
                        ],
                        rowNum:20,
                        pager: pager_id,
                        sortname: 'id',
                        sortorder: "asc",
                        height: '100%',
                        gridComplete: function () {
                            var ids = $(this).jqGrid('getDataIDs');
                            for (var i = 0; i < ids.length; i++) {
                                var cl = ids[i];
                                var row = $(this).getRowData(cl);
                                var vm = row.idVerificationMethod;
                                var beN = subgrid_table_id + "_" + row.id;
                                var be = "<select id=\"" + beN + "\"" + (row.authorized == "true" ? " disabled=\"disabled\"" : "") + "><option></option>";
                                $.each(verifySourceMethods, function(idx, item) {
                                    if (!item.isObsolete) be += "<option value=\"" + item.id + "\"" + (item.id == vm ? " selected" : "") + ">" + item.name + "</option>";
                                });
                                be += "</select>";
                                var bc = "<input type=\"checkbox\" " + (row.authorized == "true" ? "  disabled=\"disabled\" checked=\"checked\"" : "") + " onclick=\"return autorizar(this, " + beN + ");\"/>";
                                $(this).jqGrid('setRowData', ids[i], { idVerificationMethod: be, authorized: bc });
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
                    $("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false})
                }
            });

            $("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: false,
                refresh: true, refreshicon : 'icon-refresh green',
                del: false,
                search: false});

            $("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

        app.controller('managerEvalController', function ($scope, $sce) {

            $scope.Title = $sce.trustAsHtml("Aprobaci&oacute;n de fuentes");
            $scope.Message = $sce.trustAsHtml("Se har&aacute; la autorizaci&oacute;n de fuentes a verificar del caso seleccionado<br>&iquest;Desea continuar? ");
            $scope.Type = "warning";

            $scope.no = function () {
                $("#ConfirmBoxDialog").modal("hide");
            };

            $scope.yes = function() {
                $.ajax({
                    url: "<c:url value="/managereval/save.json?c=" />" + $scope.idx,
                    type: "POST",
                    data: $scope.toSave,
                    success: function(r) {
                        $("#ConfirmBoxDialog").modal("hide");
                        t.alert($scope.Title, "Se han actualizado los datos correctamente. ", "info");
                        $($scope.subGrid).trigger("reloadGrid");
                    },
                    error: function(e) {
                        $("#ConfirmBoxDialog").modal("hide");
                        alert(JSON.stringify(e));
                    },
                    dataType: "json",
                    contentType: "application/json"
                });
            };

            $scope.prompt = function(idx) {
                $scope.subGrid = "#GridId_" + idx + "_t";
                var list = $($scope.subGrid + ">tbody>tr[id!='']");
                var vS = [];
                $.each(list, function(idx, item){
                    var chk = $(item).find("td>input");
                    if (chk.is(":checked"))
                        vS.push({
                            id: $(item).attr("id"),
                            ref: $(item).find("select").val()
                        });
                });
                $scope.idx = idx;
                $scope.toSave = JSON.stringify(vS);
                $("#ConfirmBoxDialog").modal("show");
            };
        });
    </script>

    <h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Validar fuentes</h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridId" class="element-center" style="margin: auto"></table>
        <div id="GridPager"></div>
        <div class="blocker" ng-show="working">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
            </div>
        </div>
    </div>

    <div ng-controller="managerEvalController" data-backdrop="static" ng-cloak>
        <div id="ConfirmBoxDialog" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="alert alert-{{Type}}">
                            <button type="button" class="close" ng-click="no()">&times;</button>
                            <h4 class="modal-title element-center" ng-bind-html="Title"></h4>
                        </div>
                    </div>
                    <div class="modal-body">
                        <div class="element-center" ng-bind-html="Message"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default btn-{{Type}}" ng-click="yes()">S&iacute;</button>
                        <button type="button" class="btn btn-default btn-secondary" ng-click="no()">No</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>
</body>
</html>
