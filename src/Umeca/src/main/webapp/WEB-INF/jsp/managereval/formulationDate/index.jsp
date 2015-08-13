<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
</style>
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
    <script src="${pageContext.request.contextPath}/assets/scripts/app/managereval/formulationDate/formulationDateCtrl.js"></script>
    <title>Entrevistas</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>


<sec:authorize access="hasRole('ROLE_EVALUATION_MANAGER')">
<div class="container body-content">

    <script>
        window.upsert = function (id) {
            window.showUpsert(id, "#angJsjqGridId", '<c:url value='/managereval/formulationDate/upsert.html' />', "#GridId");
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/managereval/formulation/list.json' />',
                datatype: "json",
                autoencode: true,
                mtype: 'POST',
                colNames: ['ID', 'Fecha registro formulaci&oacute;n', 'Oficio', 'C&eacute;dula de notificaci&oacute;n', 'Datos imputado', 'Datos evaluador', 'Fecha audiencia', 'Fecha entrevista Umeca', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'registrationFormulationDateStr',
                        index: 'registrationFormulationDateStr',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'document',
                        index: 'document',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'certificateNotification',
                        index: 'certificateNotification',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'imputedFullname',
                        index: 'imputedFullname',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'reviewerFullname',
                        index: 'reviewerFullname',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'hearingDateStr',
                        index: 'registrationFormulationDateStr',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
                    {
                        name: 'umecaInterviewDateStr',
                        index: 'registrationFormulationDateStr',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        search: false
                    },
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
                        var row = $(this).getRowData(cl);
                        var status = row.status + "";
                        var be = "";
                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Responder solicitud\" onclick=\"window.upsert('" + cl + "');\"><i class=\" icon-pencil\"></i></a>";
                        $(this).jqGrid('setRowData', ids[i], {Action: be});
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
                add: true, addfunc: window.upsert, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridId").jqGrid('navGrid', '#GridPager', {
                edit: false, editicon: 'icon-pencil blue',
                add: false,
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });
        });

    </script>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_REVIEWER')">
    <div class="container body-content">

        <script>

            window.showConfirmPresence = function(id){

                window.showUpsert(id, "#angJsjqGridId", '<c:url value='/reviewer/meeting/newMeeting.html'/>', "#GridId");
             //   window.showAction(id, "#angJsjqGridId", '', "#GridId","Registrar Asistencia/inasistencia","&iquest;El imputado asisti&oacute; a la cita de entrevista de riesgo?","warning");
            };

            window.showReportAbsence = function(id){
                window.showAction(id, "#angJsjqGridId", '', "#GridId","Registrar Asistencia/inasistencia","&iquest;El imputado asisti&oacute; a la cita de entrevista de riesgo?","warning");
            };

            window.showConfirmInformationDelivery = function(id){
                window.showAction(id, "#angJsjqGridId", '<c:url value='/reviewer/formulationDate/confirmInformation.json'/>', "#GridId","Registrar entrega de informaci&oacute;n","&iquest;Realiz&oacute; la entrega de la informaci&oacute;n de la entrevista de formulaci&oacute;n?","warning");
            };
            $(document).ready(function () {
                jQuery("#GridId").jqGrid({
                    url: '<c:url value='/reviewer/formulation/list.json' />',
                    datatype: "json",
                    autoencode: true,
                    mtype: 'POST',
                    colNames: ['ID', 'Fecha registro formulaci&oacute;n', 'Oficio', 'C&eacute;dula de notificaci&oacute;n', 'Datos imputado', 'Datos evaluador', 'Fecha audiencia', 'Fecha entrevista Umeca', 'Acci&oacute;n'],
                    colModel: [
                        {name: 'id', index: 'id', hidden: true},
                        {
                            name: 'registrationFormulationDateStr',
                            index: 'registrationFormulationDateStr',
                            width: 200,
                            align: "center",
                            sorttype: 'string',
                            search: false
                        },
                        {
                            name: 'document',
                            index: 'document',
                            width: 200,
                            align: "center",
                            sorttype: 'string',
                            searchoptions: {sopt: ['bw']}
                        },
                        {
                            name: 'certificateNotification',
                            index: 'certificateNotification',
                            width: 200,
                            align: "center",
                            sorttype: 'string',
                            searchoptions: {sopt: ['bw']}
                        },
                        {
                            name: 'imputedFullname',
                            index: 'imputedFullname',
                            width: 200,
                            align: "center",
                            sorttype: 'string',
                            searchoptions: {sopt: ['bw']}
                        },
                        {
                            name: 'reviewerFullname',
                            index: 'reviewerFullname',
                            width: 200,
                            align: "center",
                            sorttype: 'string',
                            searchoptions: {sopt: ['bw']}
                        },
                        {
                            name: 'hearingDateStr',
                            index: 'registrationFormulationDateStr',
                            width: 200,
                            align: "center",
                            sorttype: 'string',
                            search: false
                        },
                        {
                            name: 'umecaInterviewDateStr',
                            index: 'registrationFormulationDateStr',
                            width: 200,
                            align: "center",
                            sorttype: 'string',
                            search: false
                        },
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
                            var row = $(this).getRowData(cl);
                            var status = row.status + "";
                            var be = "";
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Registrar asistencia/inasistencia\" onclick=\"window.showConfirmPresence('" + cl + "');\"><i class=\" icon-ok\"></i></a>";
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar reporte de inasistencia\" onclick=\"window.showReportAbsence('" + cl + "');\"><i class=\" icon-file\"></i></a>";
                            be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Entrega de informaci&oacute;n\" onclick=\"window.showConfirmInformationDelivery('" + cl + "');\"><i class=\" icon-list-alt\"></i></a>";
                            $(this).jqGrid('setRowData', ids[i], {Action: be});
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
                    search: false
                });
            });

        </script>

        </sec:authorize>

        <h2 class="element-center"><i class="icon icon-calendar"></i>&nbsp;&nbsp;Cita de formulaci&oacute;n</h2>

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
</body>
</html>
