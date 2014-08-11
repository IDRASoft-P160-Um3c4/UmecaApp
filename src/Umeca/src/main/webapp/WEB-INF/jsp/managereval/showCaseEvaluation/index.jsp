<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>
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
        window.showMeeting=function(id){
            var params= [];
            params["idParam"]=id;
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/verificationBySource.html?idCase=idParam'/>",params);

        };

        window.showVerification=function(id){
            var params= [];
            params["idParam"]=id;
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/choiceInformation.html?idCase=idParam'/>",params);

        };

        window.technicalReview=function(id){
            var params= [];
            params["idParam"]=id;
            window.goToUrlMvcUrl("<c:url value='/reviewer/technicalReview/technicalReview.html?id=idParam'/>",params);

        };

        window.legal=function(id){
            var params= [];
            params["idParam"]=id;
            window.goToUrlMvcUrl("<c:url value='/reviewer/meeting/legal/index.html?id=idParam'/>",params);

        };

        $(document).ready(function() {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/managereval/showCaseEvaluation/list.json' />',
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID','Carpeta de Investigaci&oacute;n','Nombre','Estatus','Id estatus','Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullname', index: 'fullname', search : false, width:400, align:"center"},
                    { name: 'statusString', index: 'statusString', width: 300, align: "center", sortable: true, search: false},
                    { name: 'status', index: 'status', hidden:true},
                    { name: 'Action', width: 130, align: "center", sortable: false, search: false }
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
                        var status = parseInt(row.status);
                        var be="";
                        if(status == 0){
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\"     ></i></a>";
                        }else{
                            if ( status >= 1) {
                                be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Entevista de riesgos procesales\" onclick=\"window.showMeeting('" + cl + "');\"><i class=\"glyphicon icon-comments-alt\"></i></a>";
                            }
                            if(status >= 2){
                                be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Informaci&oacute;n legal\" onclick=\"window.legal('" + cl + "');\"><span class=\"green  icon-legal\"></span></a>";
                            }
                            if(status >=3){
                                be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Verificaci&oacute;n\" onclick=\"window.showVerification('" + cl + "');\"><span class=\"purple icon-list\"></span></a>";

                            }
                            if(status >= 4){
                                be += "&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Opini&oacute;n t&eacute;cnica\" onclick=\"window.technicalReview('" + cl + "');\"><span class=\"warning icon-archive\"></span></a>";
                            }
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
                add: true, addfunc: window.newMeeting, addicon : 'icon-plus-sign purple',
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

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Entrevistas de evaluaci&oacute;n de riesgos procesales</h2>
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