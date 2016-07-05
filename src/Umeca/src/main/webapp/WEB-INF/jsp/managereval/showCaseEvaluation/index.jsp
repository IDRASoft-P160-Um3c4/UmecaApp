<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>

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
        window.showMeeting = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/verificationBySource.html?idCase=idParam'/>", params);

        };

        window.showVerification = function (id) {
            var params = [];
            params["idParam"] = id;
            params["readParam"] = 1;
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/choiceInformation.html?idCase=idParam&&read=readParam'/>", params);

        };

        window.technicalReview = function (id) {
            var params = [];
            params["idParam"] = id;
            params["returnParam"] = 1;
            window.goToUrlMvcUrl("<c:url value='/reviewer/technicalReview/technicalReview.html?id=idParam&&returnId=returnParam'/>", params);

        };

        window.legal = function (id) {
            var params = [];
            params["idParam"] = id;
            params["showCaseParam"] = 1;
            window.goToUrlMvcUrl("<c:url value='/reviewer/meeting/legal/index.html?id=idParam&&showCase=showCaseParam'/>", params);

        };

        window.updwFiles = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToUrlMvcUrl("<c:url value='/shared/uploadFile/index.html?id=idParam'/>", params);
        };

        window.generateFileAllSources = function (id) {
            var goTo = "<c:url value='/reviewer/technicalReview/generateFileAllSources.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };

        window.generateFile = function (id) {
            var goTo = "<c:url value='/reviewer/technicalReview/generateFile.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/managereval/showCaseEvaluation/listB.json' />',
                datatype: "json",
                autoencode:true,
                mtype: 'POST',
                colNames: ['ID', 'IDVER', 'Carpeta de Investigaci&oacute;n', 'Nombre', 'Estatus', 'Evaluador', 'Id estatus', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idVerif', index: 'idVerif', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['lk'] } },
                    { name: 'fullname', index: 'fullname', width: 350, align: "center", sorttype: 'string', searchoptions: { sopt: ['lk'] } },
                    { name: 'statusString', index: 'statusString', width: 300, align: "center", sortable: true, search: false, formatter:window.actionFormatter},
                    { name: 'userName', index: 'userName', width: 150, align: "center", sortable: true, search: false},
                    { name: 'status', index: 'status', hidden: true},
                    { name: 'Action', width: 180, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
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
                    var verifs = $(this).jqGrid('getCol', 'idVerif', false);
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var _id_verif = verifs[i];
                        var row = $(this).getRowData(cl);
                        var status = parseInt(row.status);
                        var be = "";
                        if (status == 0) {
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\"></i></a>";
                        } else {
                            if (status >= 1) {
                                be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Entevista de riesgos procesales\" onclick=\"window.showMeeting('" + cl + "');\"><i class=\"glyphicon icon-comments-alt\"></i></a>  ";
                            }
                            if (status >= 2) {
                                be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Informaci&oacute;n legal\" onclick=\"window.legal('" + cl + "');\"><span class=\"green  icon-legal\"></span></a>  ";
                            }
                            if (status >= 3) {
                                be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Verificaci&oacute;n\" onclick=\"window.showVerification('" + cl + "');\"><span class=\"purple icon-list\"></span></a>";
                                be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar entrevistas\" onclick=\"generateFileAllSources('" + _id_verif + "');\"><i class=\" icon-group purple\"></i></a> ";
                            }
                            if (status >= 4) {
                                be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Instrumento de evaluaci&oacute;n de riesgos \" onclick=\"window.technicalReview('" + _id_verif + "');\"><span class=\"warning icon-archive\"></span></a>";
                                be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar formato de opini&oacute;n\" onclick=\"generateFile('" + _id_verif + "');\"><span class=\"glyphicon glyphicon-file\"></span></a> ";
                            }

                        }
                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Subir / Descargar archivos del caso\" onclick=\"window.updwFiles('" + cl + "');\"><i class=\"glyphicon glyphicon-cloud-download\"></i></a>  ";

                        $(this).jqGrid('setRowData', ids[i], { Action: be });
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

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Consulta de casos en proceso de
        evaluaci&oacute;n</h2>

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