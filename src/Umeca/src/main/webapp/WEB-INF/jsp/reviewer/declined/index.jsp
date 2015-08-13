<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>

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

        window.printDocument = function(id){
            var goTo = "<c:url value='/reviewer/meeting/declined/printSheet.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);


        };

        $(document).ready(function() {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/reviewer/meeting/listDeclined.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID','Carpeta de Investigaci&oacute;n','Nombre completo','Fecha de nacimiento','G&eacute;nero','Estatus','Evaluador','Id estatus','Status case','Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullname', index: 'fullname', width: 300, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'dateBirthString', index: 'dateBirthString', hidden: true },
                    { name: 'genderString', index: 'genderString', hidden: true},
                    { name: 'description', index: 'description', width: 250, align: "center", sortable: false, search: false},
                    { name: 'reviewerName', index: 'reviewerName', width: 150, align: "center", sortable: false, search: false },
                    { name: 'statusCode', index: 'statusCode', hidden: true },
                    { name: 'reviewerId', index: 'reviewerId', hidden: true },
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
                        var status = row.statusCode;
                        var be="";
                        if ( status == 'DECLINE') {
                            be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar informe de negaci&oacute;n\" onclick=\"window.printDocument('" + cl + "');\"><i class=\"icon-file\"></i></a>";
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
                edit: false, editicon : 'icon-pencil blue',
                add: false, addfunc: window.newMeeting, addicon : 'icon-plus-sign purple',
                refresh: true, refreshicon : 'icon-refresh green',
                del: false,
                search: false});

//            jQuery("#GridId").jqGrid('navSeparatorAdd', '#GridPager');
//            jQuery("#GridId").jqGrid('navButtonAdd', "#GridPager",
//                    {
//                        caption: "",
//                        title: "Exportar a excel",
//                        buttonicon: 'icon-download-alt blue',
//
//                        onClickButton: function () {
//                            try {
//                                $("#GridId").jqGrid('toExcelFile',{nombre:"datosXls",formato:"excel"});
//                            } catch (e) {
//                            }
//                        }});

            jQuery("#GridId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });
        });

    </script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Entrevistas de evaluaci&oacute;n con negaci&oacute;n</h2>
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