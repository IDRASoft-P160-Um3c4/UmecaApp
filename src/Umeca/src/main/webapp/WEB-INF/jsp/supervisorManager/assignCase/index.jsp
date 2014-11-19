<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/uniqueDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <title>Asignaci&oacute;n de casos</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">
<script>
    var assignCase = function (id, cbo) {
        angular.element($("#AssignCaseDialog")).scope().promptAssignCase(id, cbo);
    };

    $(function () {
        /*
         Carpeta Judicial
         Nombre Imputado
         Estatus (Entrevista de encuadre finalizada)
         Consulta
         */
        var users = ${users};

        $("#GridId").jqGrid({
            url: '<c:url value='/supervisorManager/assignCase/list.json' />',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Carpeta de Investigaci&oacute;n', 'Nombre completo', 'Estado', 'Asignado a', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', hidden: true },
                { name: 'idFolder', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'fullname', width: 250, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'status', width: 280, align: "center", sortable: false, search: false },
                { name: 'supervisor', width: 280, align: "center", sortable: false, search: false },
                { name: 'action', width: 50, align: "center", sortable: false, search: false }
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
                    var cbo = "cbo_" + row.id;
                    var vm = row.supervisor;
                    if (vm == "") {
                        var be = "<select id=\"" + cbo + "\"" + (row.supervisor != "" ? " disabled=\"disabled\"" : "") + "><option></option>";
                        $.each(users, function (idx, item) {
                            be += "<option value=\"" + item.id + "\"" + (item.id == vm ? " selected" : "") + ">" + item.description + "</option>";
                        });
                        var ba = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Asignar el caso\" onclick=\"assignCase(" + row.id + ", '#" + cbo + "');\"><i class=\"icon-share\"></i></a>";
                        $(this).jqGrid('setRowData', ids[i], { supervisor: be, action: ba});
                    }
                    else {
                        var be = "";
                        $.each(users, function (_, user) {
                            if (user.id == vm) {
                                be = user.description;
                                return false;
                            }
                        });
                        $(this).jqGrid('setRowData', ids[i], { supervisor: be});
                    }
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

        $("#GridId").jqGrid('navGrid', '#GridPager', {
            edit: false,
            add: false,
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridId").jqGrid('navSeparatorAdd', '#GridPager');
        jQuery("#GridId").jqGrid('navButtonAdd', "#GridPager",
                {
                    caption: "",
                    title: "Exportar a excel",
                    buttonicon: 'icon-download-alt blue',

                    onClickButton: function () {
                        try {
                            $("#GridId").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
                        } catch (e) {
                        }
                    }});

        $("#GridId").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });

    });

    app.controller('assignCaseController', function ($scope, $sce) {

        $scope.Title = $sce.trustAsHtml("Asignaci&oacute;n de Casos");
        $scope.Message = $sce.trustAsHtml("Se har&aacute; la asignaci&oacute;n del caso al supervisor seleccionado<br>&iquest;Desea continuar? ");

        $scope.cancel = function () {
            $("#AssignCaseDialog").modal("hide");
        };

        $scope.ok = function () {

            $.ajax({
                url: "<c:url value="/supervisorManager/assignCase/save.json" />",
                type: "POST",
                data: JSON.stringify({
                    caseId: $scope.caseId,
                    supervisorId: $scope.supervisorId,
                    comments: $scope.comments
                }),
                success: function (r) {
                    $("#AssignCaseDialog").modal("hide");
                    $("#GridId").trigger("reloadGrid");
                    $scope.MsgBoxMsg = $sce.trustAsHtml("La asignaci&oacute;n del caso se ha efecturado correctamente.");
                    $scope.Type = "info";
                    $scope.$apply();
                    $("#MessageBoxDialog").modal("show");
                },
                error: function (e) {
                    $scope.MsgBoxMsg = $sce.trustAsHtml("Se ha presentado un error.<br>" + e.statusText);
                    $scope.Type = "danger";
                    $scope.$apply();
                    $("#MessageBoxDialog").modal("show");
                },
                dataType: "json",
                contentType: "application/json"
            });
        };

        $scope.promptAssignCase = function (id, cbo) {
            if ($(cbo).val() == "") {
                $scope.MsgBoxMsg = $sce.trustAsHtml("Para hacer la asignaci&oacute;n de un caso, es necesario seleccionar un <strong>supervisor</strong>.");
                $scope.Type = "danger";
                $scope.$apply();
                $("#MessageBoxDialog").modal("show");
                return;
            }
            $("#AssignCaseDialog").modal("show");
            $scope.caseId = id;
            $scope.supervisorId = $(cbo).val();
        };

        $scope.MsgBoxOK = function () {
            $("#MessageBoxDialog").modal("hide");
        };
    });
</script>

<h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Asignaci&oacute;n de casos</h2>

<div id="angJsjqGridId" ng-controller="modalDlgController">
    <table id="GridId" class="element-center" style="margin: auto"></table>
    <div id="GridPager"></div>
    <div class="blocker" ng-show="working">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>
</div>

<div ng-controller="assignCaseController" data-backdrop="static" ng-cloak>
    <div id="AssignCaseDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info">
                        <button type="button" class="close" ng-click="cancel()">&times;</button>
                        <h4 class="modal-title element-center" ng-bind-html="Title"></h4>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="element-center" ng-bind-html="Message"></div>
                    <label for="id_txt_comments" class="control-label">Comentarios:</label>
                    <textarea id="id_txt_comments" style="width:540px;" ng-model="comments"></textarea>

                    <div ng-show="msgError" class="alert-danger element-center">
                        {{msgError}}
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-primary" ng-click="ok()">Asignar</button>
                    <button type="button" class="btn btn-default btn-secondary" ng-click="cancel()">Cancel</button>
                </div>
            </div>
        </div>
    </div>
    <div id="MessageBoxDialog" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-{{Type}}">
                        <button type="button" class="close" ng-click="MsgBoxOK()">&times;</button>
                        <h4 class="modal-title element-center" ng-bind-html="Title"></h4>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="element-center" ng-bind-html="MsgBoxMsg"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-{{Type}}" ng-click="MsgBoxOK()">Aceptar</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>