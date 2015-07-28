<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <%--<title>Bit&aacute;coras de supervisi&oacute;n y cumplimiento</title>--%>
    <title>Historial de supervisi&oacute;n y cumplimiento</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>
        window.accomplishmentLog = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToNewUrl("<c:url value='/supervisor/log/accomplishmentLog.html?id=idParam' />", params);
        };

        <%--window.v = function(id) {--%>
        <%--window.showConfirmFull(id, "#angJsjqGridId", "<c:url value='/supervisor/log/requestAccomplishmentLog.json' />", "#GridId",--%>
        <%--"Plan de seguimiento", "&iquest;Est&aacute; seguro de que desea solicitar la autorizaci&oacute;n del reporte de incumplimiento?",--%>
        <%--"warning", {title:"Elige el tipo de reporte", lstItems:${lstFulfillmentReport},--%>
        <%--prepareData:function(data, res){--%>
        <%--return {id:data.id, fulfillmentReportId:res.m.choiceA.id};--%>
        <%--}});--%>
        <%--};--%>

        window.showRequestAccomplishmentLog = function (id) {
            window.showUpsert(id, "#angJsjqGridId", "<c:url value='/supervisor/log/requestAuthAccomplishment.html'/>", "#GridCasesId");
        };


        window.showRejectAuthAccomplishmentMsg = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/supervisor/manageMonitoringPlan/showRejectAuthAccomplishmentMsg.html' />', "#GridId");
        }

        window.supervisionLog = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToNewUrl("<c:url value='/supervisor/log/supervisionLog.html?id=idParam' />", params);
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/supervisor/log/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Caso', 'Carpeta judicial', 'Imputado', 'Fecha asignaci&oacute;n', 'Fecha generaci&oacute;n', 'Fecha autorizaci&oacute;n', 'Estatus', 'Asignado a', "Estatus bit&aacute;cora", 'Suspender', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'caseId', width: 65, hidden: true},
                    {
                        name: 'idMP',
                        width: 140,
                        align: "center",
                        sortable: false,
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {name: 'fullName', width: 220, align: "center", sortable: false, search: false},
                    {name: 'stCreationTime', width: 130, align: "center", sortable: true, search: false},
                    {name: 'stGenerationTime', width: 130, align: "center", sortable: true, search: false},
                    {name: 'stAuthorizationTime', width: 140, align: "center", sortable: true, search: false},
                    {
                        name: 'status',
                        width: 180,
                        align: "center",
                        sortable: false,
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'supervisor',
                        width: 130,
                        align: "center",
                        sortable: false,
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {name: 'statusLog', hidden: true},
                    {name: 'isMonPlanSuspended', index: 'isMonPlanSuspended', hidden: true},
                    {
                        name: 'Action',
                        width: 110,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
                    }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'creationTime',
                height: 450,
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
                        var statusLog = undefined;
                        var isMonPlanSuspended = row.isMonPlanSuspended;

                        try {
                            if (row.statusLog !== undefined && row.statusLog !== null && row.statusLog !== "")
                                statusLog = jQuery.parseJSON(row.statusLog);
                        } catch (ex) {
                        }

                        var be = "";

                        if (isMonPlanSuspended === 'true') {
                            be += "<span style='display:inline-block;' class='glyphicon glyphicon-fire red' title='El plan se encuentra suspendido, no se podr&aacute;n realizar acciones hasta que el coordinador lo autorice.'></span>";
                        }
                        else {
                            if (statusLog === undefined || (statusLog.action !== "SOLICITUD AUTORIZAR REPORTE INCUMPLIMIENTO" && statusLog.action !== "AUTORIZAR REPORTE INCUMPLIMIENTO")) {
                                be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Solicitar autorizaci&oacute;n del reporte de incumplimiento\" onclick=\"window.showRequestAccomplishmentLog('" + cl + "');\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>";
                            }
                        }

                        be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Reporte de incumplimiento\" onclick=\"window.accomplishmentLog('" + cl + "');\"><span class=\"glyphicon glyphicon-saved\"></span></a>";

                        if (statusLog !== undefined && statusLog.action === "RECHAZADO REPORTE INCUMPLIMIENTO") {
                            be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Revisar mensaje de rechazo del reporte de incumplimiento\" onclick=\"window.showRejectAuthAccomplishmentMsg('" + cl + "');\"><span class=\"glyphicon glyphicon-comment green\"></span></a>";
                        }

                        be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Bit&aacute;cora de supervisi&oacute;n\" onclick=\"window.supervisionLog('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";

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

            jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false, editicon: 'icon-pencil blue',
                add: false,
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

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
                        }
                    });

            jQuery("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });


        app.controller('requestFulfillmentCtrl', function ($scope, $timeout, $sce) {

            $scope.MsgError="";
            $scope.MsgErrorArrangementSel="";
            $scope.Model ={};

            $scope.init = function () {
                $scope.initFulfillmentType();
                $scope.initArrangementLst();
            };

            $scope.initArrangementLst = function () {
                for (var i = 0; i < $scope.lstArrangements.length; i++) {
                    $scope.lstArrangements[i].block = false;
                }
            };

            $scope.initFulfillmentType = function () {
                $scope.fulfillmentType = $scope.lstFulfillmentReport[0];
            };

            $scope.submit = function (formId, urlToPost) {
                $scope.Invalid = false;
                $scope.WaitFor = true;
                $scope.MsgError="";
                $scope.MsgErrorArrangementSel ="";

                if ($(formId).valid() == false) {
                    $scope.Invalid = true;
                    $scope.MsgError = $sce.trustAsHtml("Debe proporcionar toda la informaci&oacute;n para guardar");
                }

                if($scope.validateSelectedArrangements()==false){
                    $scope.MsgErrorArrangementSel = $sce.trustAsHtml("Debe seleccionar al menos una obligaci&oacute;n procesal");
                    $scope.Invalid = true
                }

                if($scope.Invalid == true){
                    $scope.WaitFor = false;
                    return false;
                }

                $timeout(function () {
                    $.post(urlToPost, $(formId).serialize())
                            .success(function (resp) {
                                if (resp.hasError === undefined) {
                                    resp = resp.responseMessage;
                                }

                                if (resp.hasError == true) {
                                    $scope.MsgError = $sce.trustAsHtml(resp.message);
                                    $scope.WaitFor = false;
                                    $scope.$apply();
                                }else{
                                    scope = angular.element('#dlgUpModalId').scope()
                                    scope.Model.dlg.modal('hide');
                                    scope.Model.def.resolve({isCancel: false});
                                }
                            })
                            .error(function () {
                                $scope.WaitFor = false;
                                $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
                                $scope.$apply();
                            });
                }, 1);

                return true;
            };

            $scope.validateSelectedArrangements = function () {
                for (var i = 0; i < $scope.lstArrangements.length; i++) {
                    if ($scope.lstArrangements[i].lock == true) {
                        return true;
                    }
                }
                return false;
            };

            $timeout(function () {
                $scope.init();
            }, 0);

        });

    </script>

    <h2 class="element-center"><i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;Listado de los planes de
        seguimiento</h2>

    <div id="angJsjqGridId" ng-controller="modalDlgController">
        <table id="GridId" class="element-center" style="margin: auto"></table>
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