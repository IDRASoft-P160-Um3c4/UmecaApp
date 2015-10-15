<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/rfcDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/meetingCtrl.js"></script>
    <title>Entrevistas</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>

        var lstReviewer = ${lstReviewer};
        var allSources = new Array();

        var saveAssignment=function (id){
            var sendSources = new Array();
            var noSources=false;

            for(var a=0;a<allSources.length;a++){
                x = allSources[a];

                var revId  = $("#id_cmb_"+ x.idCase+"_"+ x.idSource).val();

                if(x.idCase==id){
                    if(revId>0)
                        sendSources.push({id:revId,aux: x.idSource})
                }
            }

            if(!sendSources.length>0){
                alert("Debe asignar al menos una fuente a un evaluador.");
                return;
            }else{
                var params = {idCase: id, type:"VERIFICATION", sources:JSON.stringify(sendSources)};
                window.showActionParams(params, "#angJsjqGridId", "<c:url value='/shared/upload_info/saveAssignedCase.json'/>", "#GridId", "Asignar caso a tableta", "Se asignar&aacute;(n) la(s) fuente(s) a el(los) evaluador(es) indicado(s) &iquest;Desea continuar?", "info");
            }

        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/shared/upload_info/verification/list.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Carpeta de Investigaci&oacute;n', 'Nombre completo', 'Fecha de nacimiento', 'G&eacute;nero', 'Estatus', 'Id estatus', 'Status case', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'idFolder',
                        index: 'idFolder',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'fullname',
                        index: 'fullname',
                        width: 300,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'dateBirthString',
                        index: 'dateBirthString',
                        width: 160,
                        align: "center",
                        sortable: false,
                        search: false
                    },
                    {
                        name: 'genderString',
                        index: 'genderString',
                        width: 150,
                        align: "center",
                        sortable: false,
                        search: false
                    },
                    {
                        name: 'description',
                        index: 'description',
                        width: 250,
                        align: "center",
                        sortable: false,
                        search: false
                    },
                    {name: 'statusCode', index: 'statusCode', hidden: true},
                    {name: 'reviewerId', index: 'reviewerId', hidden: true},
                    {
                        name: 'Action',
                        width: 70,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
                    }
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
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Guardar asignaci&oacute;n\" onclick=\"saveAssignment('" + cl + "');\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>";
                        $(this).jqGrid('setRowData', ids[i], {Action: be});
                    }
                },
                loadComplete: function () {
                    var table = this;
                    setTimeout(function () {
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },
                subGridOptions: {
                    plusicon: "glyphicon glyphicon-chevron-down position-relative",
                    minusicon: "glyphicon glyphicon-chevron-right position-relative",
                    reloadOnExpand: false,
                    selectOnExpand: true
                },
                subGrid: true,
                subGridRowExpanded: function (subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html("<table id='" + subgrid_table_id + "' class='scroll'></table><div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid({
                        url: '<c:url value='/shared/upload_info/verification/sources/list.json?idCase=' />' + row_id,
                        autoencode:true,
                        datatype: "json",
                        mtype: 'POST',
                        colNames: ['ID', 'Nombre', 'Relaci&oacute;n', 'Direcci&oacute;n', 'Asignado a'],
                        colModel: [
                            { name: 'id', hidden: true },
                            { name: 'fullName', width: 200, align: "center", sortable : false, search: false},
                            { name: 'relationship', width: 150, align: "center", sortable : false, search: false},
                            { name: 'address', width: 250, align: "center", sortable : false, search: false},
                            { name: "assigned", width: 150, align: "center", sortable: false,formatter:window.actionFormatter},
                        ],
                        rowNum: 20,
                        pager: pager_id,
                        sortname: 'id',
                        sortorder: "asc",
                        height: '100%',
                        gridComplete: function () {
                            var ids = $(this).jqGrid('getDataIDs');
                            var prefix = "id_cmb_"+row_id+"_";
                            for (var i = 0; i < ids.length; i++) {

                                var cl = ids[i];

                                allSources.push({idCase:row_id,idSource:cl})

                                var cmb = "<select style=\"width:90%;\" id=\"" + prefix + cl + "\"><option value=\"-1\" selected>Sin asignar</option>";
                                $.each(lstReviewer, function (idx, item) {
                                    cmb += "<option value=\"" + item.id + "\">" + item.name + "</option>";
                                });
                                cmb += "</select>";
                                $(this).jqGrid('setRowData', ids[i], {assigned: cmb});
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
                    $("#" + subgrid_table_id).jqGrid('navGrid', "#" + pager_id, {edit: false, add: false, del: false})
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

            jQuery("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

        app.controller('mobileMeetingController', function ($scope, $timeout, $http, $rootScope, $sce) {

            $scope.WaitFor = false;
            $scope.errorMsg = "";
            $scope.successMsg = "";

            $scope.doSave = function () {

                $scope.WaitFor = true;
                $scope.errorMsg = "";
                $scope.successMsg = "";

                if (!arrSelectedCases.length > 0) {
                    $scope.errorMsg = $sce.trustAsHtml("Debe seleccionar al menos un caso.");
                    $scope.WaitFor = false;
                    return;
                }

                var currentTimeout = null;
                var url = "../saveAssignedCases.json";

                var ajaxConf;

                ajaxConf = {
                    method: "POST",
                    params: {param: JSON.stringify(arrSelectedCases), type: "VERIFICATION"}
                };

                ajaxConf.url = url;

                if (currentTimeout) {
                    $timeout.cancel(currentTimeout);
                }

                currentTimeout = $timeout(function () {
                    $http(ajaxConf)
                            .success(function (data) {
                                $scope.WaitFor = false;

                                if (data.hasError == undefined) {
                                    data = data.responseMesage;
                                }

                                if (data.hasError == true) {
                                    $scope.successMsg = "";
                                    $scope.errorMsg = $sce.trustAsHtml(data.message);
                                }
                                else {
                                    $scope.errorMsg = "";
                                    $scope.successMsg = $sce.trustAsHtml(data.message);
                                    $("#GridId").trigger("reloadGrid");
                                }
                            })
                            .error(function () {
                                $scope.WaitFor = false;
                                $scope.errorMsg = $sce.trustAsHtml("Error de red, intente m&aacute;s tarde.");
                            });
                }, 200);
            };

        });

    </script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Descargar entrevistas de
        verificaci&oacute;n
        a tableta</h2>

    <div class="row" ng-controller="mobileMeetingController">
        <%--<div id="finishConfirm" align="right">--%>
            <%--<span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-confirm-action--%>
                  <%--confirm-message="&iquest;Est&aacute; seguro que desea asignar a tableta los casos seleccionados?"--%>
                  <%--confirm-title="Guardar asignaci&oacute;n de casos" confirm-type="info"--%>
                  <%--confirmed-click-action="doSave();">--%>
                  <%--Guardar--%>
            <%--</span>--%>
        <%--</div>--%>

        <div ng-show="successMsg&&successMsg!=''" class="alert alert-success element-center success-font">
            <span ng-bind-html="successMsg"></span>
        </div>

        <div ng-show="errorMsg&&errorMsg!=''" class="alert alert-danger element-center error-font">
            <span ng-bind-html="errorMsg"></span>
        </div>

        <div id="angJsjqGridId" ng-controller="modalDlgController">
            <table id="GridId" class="element-center" style="margin: auto"></table>
            <div id="GridPager"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>