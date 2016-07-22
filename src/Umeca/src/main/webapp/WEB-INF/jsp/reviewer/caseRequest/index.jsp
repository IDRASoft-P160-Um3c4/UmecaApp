<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css" />
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
        window.makeRequest = function(id,code) {
            window.showUpsertWithIdCase(id, "#angJsjqGridId", "<c:url value='/reviewer/caseRequest/makeRequest.html'/>", "#GridId",undefined, code);
        };

        $(document).ready(function() {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/reviewer/caseRequest/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID','Carpeta de Investigaci&oacute;n','Nombre','Estatus','Id estatus','Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullName', index: 'fullName', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'description', index: 'description', width: 300, align: "center", sortable: false, search: false, formatter:window.actionFormatter},
                    { name: 'status', index: 'status', hidden: true },
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false ,formatter:window.actionFormatter}
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
                        var status = row.status+"";
                        var be="";

                        if ( status.indexOf(".E.") != -1 ) {
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar caso\" onclick=\"window.makeRequest('CASE_OBSOLETE','" + cl + "');\"><i class=\"icon-trash\"></i></a>";
                        }
                        if(status.indexOf(".A.")!= -1){
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Modificar entrevista de riesgos procesales\" onclick=\"window.makeRequest('EDIT_MEETING','" + cl + "');\"><span class=\"glyphicon icon-comments-alt\"></span></a>";
                        }if(status.indexOf(".B.")!= -1){
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"...\" title=\"Modificar informaci&oacute;n legal\" onclick=\"window.makeRequest('EDIT_LEGAL_INFORMATION','" + cl + "');\"><span class=\"icon-legal\"></span></a>";
                        }if(status.indexOf(".C.")!= -1){
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"...\" title=\"Cambiar el estado de las fuentes de verificaci&oacute;n\" onclick=\"window.makeRequest('CHANGE_STATUS_SOURCE','" + cl + "');\"><span class=\"icon-group\"></span></a>";
                        }if(status.indexOf(".D.")!=-1){
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"...\" title=\"Modificar Instrumento de evaluaci&oacute;n de riesgos\" onclick=\"window.makeRequest('EDIT_TECHNICAL_REVIEW','" + cl + "');\"><span class=\"glyphicon glyphicon-user\"></span></a>";
                        }

                        if(status.indexOf(".I.")!=-1){
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"...\" title=\"El imputado alcanz&oacute; su libertad\" onclick=\"window.makeRequest('GET_FREEDOM','" + cl + "');\"><span class=\"icon-folder-close\"></span></a>";
                        }else if(status.indexOf(".G.")!=-1){
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"...\" title=\"El imputado alcanz&oacute; su libertad\" onclick=\"window.makeRequest('GET_FREEDOM','" + cl + "');\"><span class=\"icon-folder-close\"></span></a>";
                        }
//                        else if(status.indexOf(".H.")!=-1){
//                            be += "";
//                        }

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
                add: false,
                refresh: true, refreshicon : 'icon-refresh green',
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
                                $("#GridId").jqGrid('toExcelFile',{nombre:"datosXls",formato:"excel"});
                            } catch (e) {
                            }
                        }});

            jQuery("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>

    <h2 class="element-center"><i class="icon icon-envelope"></i>&nbsp;&nbsp;Solicitudes a Coordinador</h2>
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