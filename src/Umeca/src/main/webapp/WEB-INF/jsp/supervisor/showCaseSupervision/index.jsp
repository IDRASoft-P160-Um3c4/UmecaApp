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
        var hideCol = false;

        <sec:authorize access="hasRole('ROLE_SUPERVISOR')">
        hideCol = true;
        </sec:authorize>

        window.technicalReview = function (id) {
            var params = [];
            params["idParam"] = id;
            params["returnParam"] = 2;
            window.goToUrlMvcUrl("<c:url value='/reviewer/technicalReview/technicalReview.html?id=idParam&returnId=returnParam'/>", params);
        };

        window.hearingFormat = function (id) {
            var params = [];
            params["idParam"] = id;
            params["returnParam"] = 1;
            window.goToUrlMvcUrl("<c:url value='/supervisor/hearingFormat/indexFormats.html?id=idParam&returnId=returnParam'/>", params);
        };

        window.framingMeeting = function (id) {
            var params = [];
            params["idParam"] = id;
            params["returnParam"] = 1;
            window.goToUrlMvcUrl("<c:url value='/supervisor/framingMeeting/framingMeeting.html?id=idParam&returnId=returnParam'/>", params);
        };

        window.accomplishmentLog = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToNewUrl("<c:url value='/supervisor/log/accomplishmentLog.html?id=idParam' />", params);
        };

        window.supervisionLog = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToNewUrl("<c:url value='/supervisor/log/supervisionLog.html?id=idParam' />", params);
        };


        window.showLogCase = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToUrlMvcUrl("<c:url value='/shared/logCase/index.html?id=idParam' />", params);
        };


        window.updwFiles = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToUrlMvcUrl("<c:url value='/shared/uploadFile/index.html?id=idParam'/>", params);
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/supervisor/showCaseEvaluation/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'IDFM', 'IDHF', 'IDMONP', 'IDVER', 'FMTERM', 'Carpeta Judicial', 'Nombre', 'Supervisor', 'Resoluci&oacute;n', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'idFM', index: 'idFM', hidden: true },
                    { name: 'idHF', index: 'idHF', hidden: true },
                    { name: 'idMonP', index: 'idMonP', hidden: true },
                    { name: 'idTec', index: 'idTec', hidden: true },
                    { name: 'fmTerminated', index: 'fmTerminated', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullname', index: 'fullname', search: false, width: 400, align: "center"},
                    { name: 'userName', index: 'userName', search: false, width: 400, align: "center", hidden: hideCol},
                    { name: 'resolutionStr', index: 'resolutionStr', width: 100, align: "center", sorttype: 'string', search: false},
                    { name: 'Action', width: 130, align: "center", sortable: false, search: false,formatter:window.actionFormatter}
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
                    var hfs = $(this).jqGrid('getCol', 'idHF', false);
                    var fms = $(this).jqGrid('getCol', 'idFM', false);
                    var fmTerm = $(this).jqGrid('getCol', 'fmTerminated', false);
                    var monPs = $(this).jqGrid('getCol', 'idMonP', false);
                    var tecs = $(this).jqGrid('getCol', 'idTec', false);

                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var iHF = hfs[i];
                        var iFM = fms[i];
                        var iMonP = monPs[i];
                        var iTec = tecs[i];
                        var isTerm = fmTerm[i];

                        var row = $(this).getRowData(cl);
                        var status = parseInt(row.status);
                        var be = "";

                        if (iTec != null && iTec != undefined && iTec.trim() != "")
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar opini&oacute;n t&eacute;cnica\" onclick=\"technicalReview('" + iTec + "');\"><span class=\"glyphicon glyphicon-user\"></span></a>  ";

                        if (iHF != null && iHF != undefined && iHF.trim() != "")
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar formatos de audiencia\" onclick=\"hearingFormat('" + cl + "');\"><span class=\"glyphicon glyphicon-file\"></span></a>  ";

                        if (iFM != null && iFM != undefined && iFM.trim() != "" && isTerm == 'true')
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar entrevista de encuadre\" onclick=\"framingMeeting('" + cl + "');\"><span class=\"glyphicon glyphicon-bullhorn\"></span></a>  ";

                        if (iMonP != null && iMonP != undefined && iMonP.trim() != "") {
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar bitacora de plan de monitoreo\" onclick=\"supervisionLog('" + iMonP + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>  ";
                            be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar reporte de incumplimiento\" onclick=\"accomplishmentLog('" + iMonP + "');\"><span class=\"glyphicon glyphicon-saved\"></span></a>  ";
                        }

                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Subir / Descargar archivos del caso\" onclick=\"window.updwFiles('" + cl + "');\"><i class=\"glyphicon glyphicon-cloud-download\"></i></a>  ";

                        be += "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Bit&aacute;cora del caso\" onclick=\"window.showLogCase('" + cl + "');\"><i class=\"icon icon-book\"></i></a>  ";

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
        supervisi&oacute;n</h2>

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