    <!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/technicalReviewCtrl.js"></script>

    <title>Opini&oacute;n t&eacute;cnica</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <script>

        addTechnicalReview = function (id) {
            var goTo = "<c:url value='/reviewer/technicalReview/technicalReview.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };

        generateFile = function (id) {
            var goTo = "<c:url value='/reviewer/technicalReview/generateFile.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };

        var generateFileAllSources = function (id) {
            var goTo = "<c:url value='/reviewer/technicalReview/generateFileAllSources.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };

        $(document).ready(function () {
            jQuery("#GridId").jqGrid({
                url: '<c:url value='/reviewer/technicalReview/list.json' />',
                autoencode:true,
                datatype: "json",
                mtype: 'POST',
//                colNames: ['ID', 'SHOW', 'No. Carpeta', 'No. M.P.', 'Imputado', 'Estatus', 'Acci&oacute;n'],
                colNames: ['ID', 'SHOW', 'No. Carpeta', 'Imputado', 'Estatus', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'status', index: 'status', hidden: true },
                    { name: 'idFolder', index: 'idFolder', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
//                    { name: 'idMP', index: 'idMP', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'fullName', index: 'fullName', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'statusName', index: 'statusName', width: 200, align: "center", sortable: false, search: false },
                    { name: 'Action', width: 70, align: "center", sortable: false, search: false, formatter:window.actionFormatter}
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'idFolder',
                height: 450,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "desc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    var status = $(this).jqGrid('getCol', 'status', false);


                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var row = $(this).getRowData(cl);
                        var enabled = row.enabled;
                        var be = "";


                        switch (status[i]) {

                            case "ST_CASE_VERIFICATION_COMPLETE":
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Agregar instrumento de evaluaci&oacute;n de riesgos\" onclick=\"addTechnicalReview('" + cl + "');\"><span class=\"glyphicon glyphicon-plus\"></span></a>";
                                break;
                            case "ST_CASE_TECHNICAL_REVIEW_COMPLETE":
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar instrumento de evaluaci&oacute;n de riesgos\" onclick=\"addTechnicalReview('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open dark\"></span></a>";
                                be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Generar formato de opini&oacute;n\" onclick=\"generateFile('" + cl + "');\"><span class=\"glyphicon glyphicon-file\"></span></a>";
                                be += "  <a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Descargar entrevistas\" onclick=\"generateFileAllSources('" + cl + "');\"><i class=\" icon-group purple\"></i></a>";
                                break;
                            case "ST_CASE_EDIT_TEC_REV":
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar instrumento de evaluaci&oacute;n de riesgos\" onclick=\"addTechnicalReview('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil dark\"></span></a>";
                                break;
                            case "ST_CASE_TECHNICAL_REVIEW_INCOMPLETE":
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Continuar instrumento de evaluaci&oacute;n de riesgos\" onclick=\"addTechnicalReview('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil dark\"></span></a>";
                                break;
                            default:
                                be = "<a style=\"display:inline-block;\" title=\"A&uacute;n no cuenta con la verificaci&oacute;n completa\" href=\"#\"\"><span class=\"glyphicon glyphicon-ban-circle\"></span></a>";
                                break;
                        }

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

            jQuery("#GridId").jqGrid('navSeparatorAdd', '#GridPager');
            jQuery("#GridId").jqGrid('navButtonAdd', "#GridPager",
                    {
                        caption: "",
                        title: "Exportar a excel",
                        buttonicon: 'icon-download-alt blue',

                        onClickButton: function () {
                            try {
                                $("#GridId").jqGrid('toExcelFile', {nombre: "datosXls", formato: "excel"});
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

    <h2 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Instrumento de evaluaci&oacute;n de
        riesgos</h2>

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