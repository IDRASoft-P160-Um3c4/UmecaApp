<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <title>Fuentes de verificaci&oacute;n autorizadas</title>
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
        window.cancelShowSource = function(){
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/index.html'/>");
        };

        window.addSources = function(){
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/addSources/index.html?id=${idCase}'/>");
        };

        window.electionInformation = function(){
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/choiceInformation.html?idCase=${idCase}'/>");
        };

        window.newReport = function(){
            window.showUpsert(null, "#angJsjqGridId", "<c:url value='/reviewer/verification/newReport.html?id=${idCase}'/>");
        };

        window.meetingSource = function(id) {
            var params= [];
            params["idSourceParam"]=id;
            params["idCaseParam"]=${idCase};
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/verificationBySource.html?idCase=idCaseParam&&idSource=idSourceParam'/>",params);
        };

        $(document).ready(function() {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/reviewer/verification/listSource.json?id=${idCase}' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Nombre','Edad', 'Relaci&oacute;n', 'Direcci&oacute;n', 'Tel&eacute;fono','Estatus','Complete','IdCase', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'fullName', index: 'fullName', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'age', index: 'age', width: 80, align: "center", search:false},
                    { name: 'relationshipString', index: 'relationshipString', width: 100, align: "center", search:false},
                    { name: 'address', index: 'address', width: 300, align: "center", search:false},
                    { name: 'phone', index: 'phone', width: 150, align: "center", search:false},
                    { name: 'statusString', index: 'statusString', width: 200, align: "center", search:false, formatter:window.actionFormatter},
                    { name: 'dateComplete', index: 'dateComplete',hidden:true},
                    { name: 'idCase', index: 'idCase', hidden:true},
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false ,formatter:window.actionFormatter}
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
                            be = "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Continuar entrevista\" onclick=\"window.meetingSource('" + cl + "');\"><span class=\"icon-edit blue\"></span></a>";
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
                add: false,
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
    <div class="row">
        <div class="modal-footer">
            <span class="btn btn-default btn-sm" onclick="window.cancelShowSource()">
                Regresar
            </span>
            <span class="btn btn-primary btn-sm" ng-disabled = "${sourceAvailable}" onclick="window.addSources()">
                <span class="icon-group align-top bigger-125"></span> &nbsp;&nbsp;Agregar fuentes
            </span>
            <span class="btn btn-purple btn-primary btn-sm" ng-disabled="${sourceAvailable}" onclick="window.electionInformation()" >
                <span class="icon-list align-top bigger-125"></span>&nbsp;&nbsp;Iniciar elecci&oacute;n de informaci&oacute;n
            </span>
            <span class="btn btn-success btn-sm" ng-disabled="${sourceAvailable}" onclick="window.newReport()" >
                <span class="icon-file align-top bigger-125"></span>&nbsp;&nbsp;Generar informe
            </span>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>