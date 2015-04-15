<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
    $(document).ready(function () {

        var minuteId = $("#hidMinuteId").val();
        var finishedMinute = $("#hidFinishedMinuteId").val();
        var isRH = $("#hidIsRHId").val();
        var isDir = $("#hidIsDirId").val();

        upsertAgreement = function (id) {
            if (finishedMinute == 'false' && isRH == 'true')
                window.showUpsertWithIdMinute(id, "#angJsjqGridIdAgreement", "<c:url value='/shared/agreement/upsertAgreement.html'/>", "#GridIdAgreement", undefined, minuteId);
        };

        upsertObservation = function (id) {
            if (finishedMinute == 'false')
                window.showUpsert(id, "#angJsjqGridIdAgreement", "<c:url value='/shared/observation/upsertObservation.html'/>", "#GridIdAgreement");
        };

        showObsHistory = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToNewUrl("<c:url value='/shared/observation/obsHistory.html?id=idParam' />", params);
        };

        showFinishRequest = function (id) {
            if (finishedMinute == 'false' && isRH == 'true')
                window.showUpsert(id, "#angJsjqGridIdAgreement", "<c:url value='/shared/agreement/finishRequestAgreement.html'/>", "#GridIdAgreement");
        };

        autRejFinishRequestAgreement = function (id) {
            if (finishedMinute == 'false' && isDir == 'true')
                window.showUpsert(id, "#angJsjqGridIdAgreement", "<c:url value='/shared/agreement/authRejFinishRequest.html'/>", "#GridIdAgreement");
        };

        showResponseRequest = function (id) {
            if (finishedMinute == 'false' && isRH == 'true')
                window.showUpsert(id, "#angJsjqGridIdAgreement", "<c:url value='/shared/agreement/responseFinishRequest.html'/>", "#GridIdAgreement");
        };

        showAgreementFiles = function (id) {
            <%--if (finishedMinute == 'false' && isRH == 'true')--%>
            <%--window.showUpsert(id, "#angJsjqGridIdAgreement", "<c:url value='/shared/agreement/responseFinishRequest.html'/>", "#GridIdAgreement");--%>
            alert("Funcionalidad en desarrollo");
        };

        jQuery("#GridIdAgreement").jqGrid({
            url: '<c:url value="/shared/agreement/list.json?id="/>' + minuteId,
            autoencode: true,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'isFinished', 'stCode', 'Acuerdo', 'Estado', 'Concluido', 'Acci&oacute;n'],
            colModel: [
                {name: 'id', index: 'id', hidden: true},
                {name: 'isFinished', index: 'isFinished', hidden: true},
                {name: 'stCode', index: 'isFinished', hidden: true},
                {
                    name: 'title',
                    index: 'title',
                    sorttype: 'string',
                    width: 400,
                    align: "center",
                    searchoptions: {sopt: ['bw']}
                },
                {
                    name: 'isDoneStr',
                    index: 'isDoneStr',
                    width: 150,
                    align: "center",
                    sortable: false,
                    search: false
                },
                {
                    name: 'isFinishedStr',
                    index: 'isFinishedStr',
                    width: 150,
                    align: "center",
                    sortable: false,
                    search: false
                },
                {
                    name: 'Action',
                    width: 150,
                    align: "center",
                    sortable: false,
                    search: false,
                    formatter: window.actionFormatter
                }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerAgreement',
            sortname: 'id',
            height: 200,
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
                        be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Agregar observaci&oacute;n\" onclick=\"upsertObservation('" + cl + "');\"><span class=\"glyphicon glyphicon-comment\"></span></a>";
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
                    be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Archivos del acuerdo\" onclick=\"showAgreementFiles('" + cl + "');\"><span class=\"glyphicon glyphicon-upload\"></span></a>";
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

        jQuery("#GridIdAgreement").jqGrid('navGrid', '#GridPagerAgreement', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertAgreement, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false
        });

        jQuery("#GridIdAgreement").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    })
    ;
</script>

<div class="row element-center">
    <div class="col-xs-12">
        <br/>

        <div id="angJsjqGridIdAgreement" ng-controller="modalDlgController">
            <table id="GridIdAgreement" class="element-center" style="margin: auto"></table>
            <div id="GridPagerAgreement"></div>
        </div>
    </div>
</div>

