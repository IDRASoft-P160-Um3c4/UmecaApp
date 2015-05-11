<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/detentionRecord/detentionRecordCtrl.js"></script>

    <title>Casos para entrevista de encuadre</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

<script>
    $(document).ready(function () {

        refreshList = function(){
//        $(jqGridToUse).trigger("reloadGrid");
        };

        upsertDetained = function (id) {
                window.showUpsert(null, "#angJsjqGridIdDetained", "<c:url value='/detentionRecord/upsertDetention.html'/>", "#GridIdDetained");
        };

        jQuery("#GridIdDetained").jqGrid({
            url: '<c:url value="/detentionRecord/detainedSheet/list.json"/>',
            autoencode: true,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Imputado', 'Carpeta de <br/> Investigaci&oacute;n', 'Fecha inicio', 'Hora inicio', 'Unidad de <br/>Investigaci&oacute;n',
                'Presentado por','Distrito', 'T&eacute;rmino', 'Tiempo para <br/> cumplir t&eacute;rmino'],
            colModel: [
                {name: 'id', index: 'id', hidden: true},
                {
                    name: 'fullName',
                    index: 'fullName',
                    sorttype: 'string',
                    width: 200,
                    align: "center",
                    search: false
                },
                {
                    name: 'idFolder',
                    index: 'idFolder',
                    sorttype: 'string',
                    width: 200,
                    align: "center",
                    search: false
                },
                {
                    name: 'initDateStr',
                    index: 'initDateStr',
                    sorttype: 'string',
                    width: 150,
                    align: "center",
                    search: false
                },
                {
                    name: 'initTimeStr',
                    index: 'initTimeStr',
                    width: 150,
                    align: "center",
                    sorttype: 'string',
                    search: false
                },
                {
                    name: 'investigationUnit',
                    index: 'investigationUnit',
                    width: 200,
                    align: "center",
                    sorttype: 'string',
                    search: false
                },
                {
                    name: 'crime',
                    index: 'crime',
                    width: 200,
                    align: "center",
                    sorttype: 'string',
                    search: false
                },
                {
                    name: 'district',
                    index: 'district',
                    width: 200,
                    align: "center",
                    sorttype: 'string',
                    search: false
                },
                {
                    name: 'timeLeft',
                    index: 'timeLeft',
                    width: 200,
                    align: "center",
                    sortable: false,
                    search: false
                },
                {
                    name: 'dueTime',
                    index: 'dueTime',
                    width: 200,
                    align: "center",
                    sortable: false,
                    search: false
                }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPager',
            sortname: 'id',
            height: 350,
            viewrecords: true,
            shrinkToFit: false,
            sortorder: "desc",
            caption: "&nbsp;",
            altRows: true,
            gridComplete: function () {
                var ids = $(this).jqGrid('getDataIDs');
                var finished = $(this).jqGrid('getCol', 'isFinished', false);
                var stCode = $(this).jqGrid('getCol', 'stCode', false);
                for (var i = 0; i < ids.length; i++) {
                    var cl = ids[i];
                    var be = "";
                    if (finished[i] == 'false') {
                        be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Consultar acuerdo\" onclick=\"window.upsertAgreement('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                        be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Agregar observaci&oacute;n\" onclick=\"upsertObservation('" + cl + "');\"><span class=\"glyphicon glyphicon-comment\"></span></a>";
                        be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Agregar archivo al acuerdo\" onclick=\"upsertAgreementFile('" + cl + "');\"><span class=\"glyphicon glyphicon-upload\"></span></a>";
                        if (isRH == 'true') {
                            be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Solicitar concluir acuerdo\" onclick=\"showFinishRequest('" + cl + "');\"><span class=\"glyphicon glyphicon-lock\"></span></a>";
                            if (stCode[i] === 'FINISHED_AGREEMENT' || stCode[i] === 'FINISH_REJECT')
                                be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Ver respuesta de solicitud de conclusi&oacute;n\" onclick=\"showResponseRequest('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                        }

                        if (isDir == 'true') {
                            if (stCode[i] === 'PENDENT_FINISH_REQUEST')
                                be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Autorizar/Rechazar concluir acuerdo\" onclick=\"autRejFinishRequestAgreement('" + cl + "');\"><span class=\"glyphicon glyphicon-thumbs-up\"></span></a>";
                        }
                    }

                    be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Historial de observaciones\" onclick=\"showObsHistory('" + cl + "');\"><span class=\"glyphicon glyphicon-dashboard\"></span></a>";
                    be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar archivos del acuerdo\" onclick=\"downloadAgreementFiles('" + cl + "');\"><span class=\"glyphicon glyphicon-download\"></span></a>";
                    $(this).jqGrid('setRowData', ids[i], {Action: be});
                }
            }
            ,
            loadComplete: function () {
                var table = this;
                setTimeout(function () {
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            }
        });

        jQuery("#GridIdDetained").jqGrid('navGrid', '#GridPager', {
            edit: false,
            add: true, addfunc: upsertDetained, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false
        });

        jQuery("#GridIdDetained").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    });
</script>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i> S&aacute;bana de detenidos
    </h2>

    <div id="angJsjqGridIdDetained" ng-controller="modalDlgController">
        <table id="GridIdDetained" class="element-center" style="margin: auto"></table>
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