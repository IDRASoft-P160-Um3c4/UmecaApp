<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <link href="${pageContext.request.contextPath}/assets/content/upload/jquery.fileupload.css" rel="stylesheet" type="text/css">


    <script src="${pageContext.request.contextPath}/assets/scripts/upload/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.iframe-transport.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.fileupload.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upload/uploadFileCtrl.js"></script>

    <title>Subir / Descargar archivos</title>
</head>
<body scroll="no" ng-app="ptlUmc">
    <%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>


    <div class="container body-content">

        <script>
            window.uploadFile = function() {
                id = ${caseId};
                window.showUpsert(id, "#angJsjqGridId", '<c:url value='/shared/uploadFile/uploadFile.html' />', "#GridId");
            };

            window.download = function(id) {
                var params= [];
                params["idParam"]=id;
                window.goToUrlMvcUrl("<c:url value='/shared/uploadFile/downloadFile.html?id=idParam' />",params);
            };

            window.downloadAll = function() {
                id = ${caseId};
                var params= [];
                params["idParam"]=id;
                window.goToNewUrl("<c:url value='/shared/uploadFile/downloadFileByCase.html?id=idParam' />", params, {opts:"fullscreen=no, top=0, left=0, width=500, height=300"});
            };

            window.delete = function(id) {
                id = ${caseId} + "|" + id;
                window.showAction(id, "#angJsjqGridId", '<c:url value='/shared/uploadFile/deleteFile.json' />', "#GridId", "Eliminar archivo", "&iquest;Desea eliminar el archivo elegido?", "danger");
            };

            $(document).ready(function() {
                jQuery("#GridId").jqGrid({
                    url: '<c:url value='/shared/uploadFile/list.json?caseId=' />' + ${caseId},
                    autoencode:true,
                    datatype: "json",
                    mtype: 'POST',
                    colNames: ['ID', 'Archivo', 'Descripci&oacute;n','Tipo', 'Tama&ntilde;o (bytes)', 'Usuario', 'Fecha creaci&oacute;n', 'Caso', 'Acci&oacute;n'],
                    colModel: [
                        { name: 'id', index: 'id', hidden: true },
                        { name: 'filename', index: 'filename', width: 280, align: "center", sortable: false, sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                        { name: 'description', index: 'description', width: 220, align: "center", sortable: false, search: false },
                        { name: 'typeName', index: 'typeName', width: 220, align: "center", sortable: false, search: false },
                        { name: 'size', index: 'size', width: 150, align: "center", sortable: true, search: false },
                        { name: 'fullname', index: 'fullname', width: 130, align: "center", sortable: true, search: false },
                        { name: 'stCreationTime', index: 'stCreationTime', width: 140, align: "center", sortable: true, search: false },
                        { name: 'caseId', index: 'caseId', hidden: true},
                        { name: 'Action', width: 70, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
                    ],
                    rowNum: 10,
                    rowList: [10, 20, 30],
                    pager: '#GridPager',
                    sortname: 'creationTime',
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
                            var status = row.status;
                            var be = "";

                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar\" onclick=\"window.download('" + cl + "');\"><span class=\"glyphicon glyphicon-cloud-download\"></span></a>";

                            if("${readOnly}" !== "1" )
                                be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar\" onclick=\"window.delete('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
                            /*if (status === "NUEVO") {
                                be += "&nbsp;&nb00sp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar plan de supervisi�n\" onclick=\"window.generate('" + cl + "');\"><span class=\"glyphicon glyphicon-plus-sign\"></span></a>";
                            }*/
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
                    edit: false, editicon : 'icon-pencil blue',
                    add: ("${readOnly}" !== "1") ? true : false, addfunc: window.uploadFile, addicon: 'icon-plus-sign purple',
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

        <div class="page-header">
            <div class="row">
                <div class="col-xs-12">
                    <h3 class="element-center"><i class="glyphicon glyphicon-upload"></i>&nbsp;Subir / <i class="glyphicon glyphicon-upload"></i>&nbsp;Descargar archivos del caso
                        <br/>Imputado: ${fullname}</h3>
                </div>
                <div class="row" ng-controller="uploadFileController">
                    <div class="col-xs-12 element-center">
                    <br/>
                        <span class="btn btn-primary element-center" ng-click="downloadAll()">
                            <i class="glyphicon glyphicon-save"></i> &nbsp; Descargar expediente
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <div id="angJsjqGridId" ng-controller="modalDlgController">
            <table id="GridId" class="element-center" style="margin: auto"></table>
            <div id="GridPager"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>

        <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
        <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
    </div>

</body>
</html>