<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/management/userCtrl.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>


    <script src="${pageContext.request.contextPath}/assets/scripts/app/reportExcelCtrl.js"></script>

    <title>Reporte excel</title>
</head>

<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content col-xs-12 col-xs-offset-1">

    <script>

        var listIds = "[]";
        var selectedFilters = {};

        function reloadExcelGrid(returnIds, selectedFiltersObj) {
            listIds = returnIds;
            selectedFilters = selectedFiltersObj;
            $("#GridCasesId").setGridParam({postData: {ids: listIds}});
            $("#GridCasesId").trigger("reloadGrid");
        }

        $(document).ready(function () {

            jQuery("#GridCasesId").jqGrid({
                url: '<c:url value='/director/excelReport/listCases.json' />',
                autoencode: true,
                datatype: "json",
                mtype: 'POST',
                postData: {
                    ids: listIds
                },
                colNames: ['ID', 'idStatus', 'Carpeta <br/>de Investigaci&oacute;n', 'Carpeta Judicial', 'Nombre completo', 'Estatus'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {name: 'status', index: 'status', hidden: true},
                    {
                        name: 'idFolder',
                        index: 'idFolder',
                        width: 150,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'idMP',
                        index: 'idMP',
                        width: 150,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {
                        name: 'fullName',
                        index: 'fullName',
                        width: 250,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {name: 'statusDesc', index: 'statusDesc', width: 400, align: "center", search: false}
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPager',
                sortname: 'id',
                height: 250,
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
                        var be = "";

                        switch (status[i]) {

                            case 'ST_CASE_VERIFICATION_COMPLETE':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Gestionar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-plus\"></span></a>";
                                break;
                            case 'ST_CASE_CONDITIONAL_REPRIEVE':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Gestionar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-plus\"></span></a>";
                                break;
                            case 'ST_CASE_HEARING_FORMAT_END':
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                                break;
                            default : //eliminar
                                be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Visualizar formatos de audiencia\" onclick=\"showHearingFormats('" + cl + "');\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
                                break;
                        }

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

            jQuery("#GridCasesId").jqGrid('navGrid', '#GridPager', {
                edit: false,
                add: false,
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridCasesId").jqGrid('navSeparatorAdd', '#GridPager');

            jQuery("#GridCasesId").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });

            jQuery("#GridCasesId").jqGrid('navButtonAdd', "#GridPager",
                    {
                        caption: "",
                        title: "Descargar información de casos en excel",
                        buttonicon: 'icon-download-alt red',

                        onClickButton: function () {
                            try {
                                var params = [];
                                params["idParam"] = listIds;
                                params["filters"] = JSON.stringify(selectedFilters);

                                window.goToUrlMvcUrl("<c:url value='/director/excelReport/jxls.html?ids=idParam&filt=filters'/>", params);

                            } catch (e) {

                            }
                        }
                    });
        });

    </script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

    <h2 class="element-center"><i class="icon icon-file"></i>&nbsp;&nbsp;Reporte Excel
    </h2>

    <form id="FormRepExcel" name="FormRepExcel" class="form-horizontal"
          role="form" ng-controller="reportExcelController" method="post" ng-cloak>

        <input type="hidden" id="hdLstStatusCases" name="lstStatusCaseStr" value="{{lstStatusCase}}">

        <input type="hidden" id="hdLstStatusMeet" name="lstStatusMeetingStr" value="{{lstStatusMeeting}}">
        <input type="hidden" id="hdLstStatusVer" name="lstStatusVerificationStr" value="{{lstStatusVerification}}">

        <input type="hidden" id="hdLstGenderB" name="lstGenderBoolStr" value="{{lstGender}}">

        <input type="hidden" id="hdLstMaritalSt" name="lstMaritalStStr" value="{{lstMaritalSt}}">
        <input type="hidden" id="hdLstAcademicLvl" name="lstAcademicLvlStr" value="{{lstAcademicLvl}}">
        <input type="hidden" id="hdLstDrugs" name="lstDrugsStr" value="{{lstDrugs}}">
        <input type="hidden" id="hdLstLvlRisk" name="lstLvlRiskStr" value="{{lstLvlRisk}}">
        <input type="hidden" id="hdLstHearingType" name="lstHearingTypeStr" value="{{lstHearingType}}">
        <input type="hidden" name="idLoc" value="{{locSel.id}}">

        <input type="hidden" id="hdLstCrimesSel" name="lstCrimeStr" value="{{lstCrimesSel}}">
        <input type="hidden" id="hdLstArrangementSel" name="lstArrangementStr" value="{{lstArrangementSel}}">
        <input type="hidden" id="hdLstActivitiesSel" name="lstActivitiesStr" value="{{lstActivitiesSel}}">

        <input type="hidden"
               ng-init="urlGetMun= '<c:url value="/director/excelReport/getMunBySt.json"/>'; urlGetLoc= '<c:url value="/director/excelReport/getLocationsByMun.json"/>'">

        <input type="hidden" id="urlDowload" value="'<c:url value="/catalogs/address/locationsByZipCode.json"/>'">


        <div class="row">
            <div class="col-xs-10 col-xs-offset-1">
                <div class="widget-box">
                    <div class="widget-header">Filtros de b&uacute;squeda</div>
                    <div class="widget-body">
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="row">
                                    <br/>

                                    <div class="widget-box">
                                        <div class="widget-header">Rango de fechas</div>
                                        <div class="widget-body">
                                            <div class="row">
                                                <div class="col-xs-10 col-xs-offset-1">
                                                    <br/>

                                                    <div class="row">

                                                        <div class="col-xs-3 col-xs-offset-2">
                                                            <label for="initDate">Fecha inicio</label>
                                                            <br/>
                                                            <small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/01)</small>
                                                            <div class="row">
                                                                <div class="input-group">
                                                                    <input id="initDate" name="initDate"
                                                                           ng-model="initDate"
                                                                           class="form-control date-picker"
                                                                           type="text"
                                                                           data-date-format="yyyy/mm/dd" data-val="true"

                                                                           data-val-required="Fecha de inicio es un campo requerido"/>
                                                            <span class="input-group-addon">
                                                                <i class="icon-calendar bigger-110"></i>
                                                            </span>
                                                                </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="initDate"
                                                              data-valmsg-replace="true"></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-3 col-xs-offset-1">
                                                            <label for="endDate">Fecha fin</label>
                                                            <br/>
                                                            <small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/30)</small>
                                                            <div class="row">
                                                                <div class="input-group">
                                                                    <input id="endDate" name="endDate"
                                                                           class="form-control date-picker" type="text"
                                                                           data-date-format="yyyy/mm/dd" data-val="true"

                                                                           ng-model="endDate"
                                                                           data-val-required="Fecha de fin es un campo requerido"/>
                                                            <span class="input-group-addon">
                                                                <i class="icon-calendar bigger-110"></i>
                                                            </span>
                                                                </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="endDate"
                                                              data-valmsg-replace="true"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <br/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                </div>

                                <div class="row">
                                    <div class="widget-box">
                                        <div class="widget-header">Datos de imputado</div>
                                        <div class="widget-body">
                                            <div class="row">
                                                <div class="col-xs-10 col-xs-offset-1">
                                                    <br>

                                                    <div class="row">
                                                        <div class="col-xs-6">
                                                            <div class="widget-box">
                                                                <div class="widget-header">G&eacute;nero</div>
                                                                <div class="widget-body">
                                                                    <div class="row">
                                                                        <div class="col-xs-10 col-xs-offset-1">
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstGender",true); addIdToList("lstGenderStr","-Femenino");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Femenino</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstGender",false); addIdToList("lstGenderStr","-Masculino");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Masculino</span>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="widget-box">
                                                                <div class="widget-header">Empleo</div>
                                                                <div class="widget-body">
                                                                    <div class="row">
                                                                        <div class="col-xs-10 col-xs-offset-1">
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           name="hasJob"
                                                                                           ng-model="hasJob">
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Con empleo actual</span>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-6">
                                                            <div class="widget-box">
                                                                <div class="widget-header">Estado civil</div>
                                                                <div class="widget-body">
                                                                    <div class="row">
                                                                        <div class="col-xs-10 col-xs-offset-1">
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstMaritalSt",1); addIdToList("lstMarStStr","-Soltero");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Soltero</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstMaritalSt",2); addIdToList("lstMarStStr","-Casado");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Casado</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstMaritalSt",3); addIdToList("lstMarStStr","-Divorciado");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Divorciado</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstMaritalSt",4); addIdToList("lstMarStStr","-Unión libre");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Uni&oacute;n libre</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstMaritalSt",5) ; addIdToList("lstMarStStr","-Viudo");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Viudo</span>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <br/>

                                                    <div class="row">
                                                        <div class="col-xs-6">
                                                            <div class="widget-box">
                                                                <div class="widget-header">Sustancias</div>
                                                                <div class="widget-body">
                                                                    <div class="row">
                                                                        <div class="col-xs-10 col-xs-offset-1">
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",1); addIdToList("lstDrugsStr","-Alcohol");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Alcohol</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",2); addIdToList("lstDrugsStr","-Marihuana");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Marihuana</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",3); addIdToList("lstDrugsStr","-Cocaína");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Cocaca&iacute;na</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",4); addIdToList("lstDrugsStr","-Heroína");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Hero&iacute;na</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",5); addIdToList("lstDrugsStr","-Opiáceos");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Opi&aacute;ceos</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",6); addIdToList("lstDrugsStr","-PBC");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;PBC(Pasta b&aacute;sica de coca&iacute;na)</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",7); addIdToList("lstDrugsStr","-Solventes");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Solventes</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",8); addIdToList("lstDrugsStr","-Cemento");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Cemento</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",9); addIdToList("lstDrugsStr","-LSD");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;LSD</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",10); addIdToList("lstDrugsStr","-Anfetaminas");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Anfetaminas</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",11); addIdToList("lstDrugsStr","-Metanfetaminas");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Metanfetaminas</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",12); addIdToList("lstDrugsStr","-Extasis");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Extasis</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",13); addIdToList("lstDrugsStr","-Hongos");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Hongos</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstDrugs",14); addIdToList("lstDrugsStr","-Otro");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Otro</span>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <div class="widget-box">
                                                                <div class="widget-header">Nivel acad&eacute;mico</div>
                                                                <div class="widget-body">
                                                                    <div class="row">
                                                                        <div class="col-xs-10 col-xs-offset-1">
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstAcademicLvl",1); addIdToList("lstAcLvlStr","-Sin instrucción académica");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Sin instrucci&oacute;n acad&eacute;mica</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstAcademicLvl",2); addIdToList("lstAcLvlStr","-Primaria");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Primaria</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstAcademicLvl",3); addIdToList("lstAcLvlStr","-Secundaria");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Secundaria</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstAcademicLvl",4); addIdToList("lstAcLvlStr","-Bachillerato");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Bachillerato</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstAcademicLvl",5); addIdToList("lstAcLvlStr","-Licenciatura");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Licenciatura</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstAcademicLvl",6); addIdToList("lstAcLvlStr","-Postgrado");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Postgrado</span>
                                                                                </label>
                                                                            </div>
                                                                            <div class="checkbox">
                                                                                <label>
                                                                                    <input class="ace" type="checkbox"
                                                                                           ng-click='addIdToList("lstAcademicLvl",7); addIdToList("lstAcLvlStr","-Otro");'>
                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;Otro</span>
                                                                                </label>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <br/>

                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <div class="widget-box">
                                                                <div class="widget-header">Lugar de residencia</div>
                                                                <div class="widget-body">
                                                                    <div class="row">
                                                                        <div class="checkbox col-xs-5">
                                                                            <label>
                                                                                <input class="ace" type="checkbox"
                                                                                       ng-model="homePlace"
                                                                                       name="homePlace">
                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Agregar filtro por lugar de residencia</span>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                    <br/>

                                                                    <div class="row">
                                                                        <br/>

                                                                        <div class="col-xs-3 col-xs-offset-1">
                                                                            <label>&nbsp;&nbsp;Estado:</label>
                                                                            <br/>
                                                                            <select class="form-control element-center"
                                                                                    ng-model="stateSel"
                                                                                    ng-options="e.name for e in lstStates"
                                                                                    ng-init='lstStates = ${lstStates}'
                                                                                    ng-change="changeState(stateSel);"
                                                                                    ng-disabled="homePlace==false"></select>
                                                                            <br/>
                                                                        </div>

                                                                        <div class="col-xs-3">
                                                                            <label>Municipio:</label>
                                                                            <br/>
                                                                            <select class="form-control element-center"
                                                                                    ng-model="munSel"
                                                                                    ng-options="m.name for m in lstMuns"
                                                                                    ng-change="changeMun(munSel);"
                                                                                    ng-disabled="homePlace==false"></select>
                                                                            <br/>
                                                                        </div>

                                                                        <div class="col-xs-4">
                                                                            <label>Localidad:</label>
                                                                            <br/>
                                                                            <select id="locSel"
                                                                                    class="form-control element-center"
                                                                                    ng-model="locSel"
                                                                                    ng-options="e.name for e in lstLoca"
                                                                                    ng-disabled="homePlace==false"></select>
                                                                            <br/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <br/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br/>

                                    <div class="widget-box">
                                        <div class="widget-header">En proceso de</div>
                                        <div class="widget-body">
                                            <div class="row">
                                                <div class="col-xs-10 col-xs-offset-1">
                                                    <br/>

                                                    <div class="row">

                                                        <div class="widget-box">
                                                            <div class="widget-header">Evaluaci&oacute;n</div>
                                                            <div class="widget-body">
                                                                <div class="row">
                                                                    <br/>

                                                                    <div class="col-xs-10 col-xs-offset-1">

                                                                        <div class="col-xs-6">
                                                                            <div class="widget-box">
                                                                                <div class="widget-header">Estatus</div>
                                                                                <div class="widget-body">
                                                                                    <div class="row">

                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstStatusMeeting",2); addIdToList("lstStCaseStr","-Entrevista de riesgos procesales completa");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Entrevista de riesgos procesales completa</span>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstStatusMeeting",3); addIdToList("lstStCaseStr","-Información legal completa");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Informaci&oacute;n legal completa</span>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstStatusVerification",4); addIdToList("lstStCaseStr","-Verificación completa");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Verificaci&oacute;n completa</span>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstStatusCase",2); addIdToList("lstStCaseStr","-Opinión técnica");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Opini&oacute;n t&eacute;cnica</span>
                                                                                            </label>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                        <div class="col-xs-6">
                                                                            <div class="widget-box">
                                                                                <div class="widget-header">Nivel de
                                                                                    riesgo
                                                                                </div>
                                                                                <div class="widget-body">
                                                                                    <div class="row">
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstLvlRisk",1); addIdToList("lstLvlRkStr","-Mínimo");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;M&iacute;nimo</span>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstLvlRisk",2); addIdToList("lstLvlRkStr","-Bajo");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Bajo</span>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstLvlRisk",3); addIdToList("lstLvlRkStr","-Medio");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Medio</span>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstLvlRisk",4); addIdToList("lstLvlRkStr","-Alto");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Alto</span>
                                                                                            </label>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                    </div>
                                                                </div>
                                                                <br/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <sec:authorize access="hasRole('ROLE_DIRECTOR')">
                                                    <div class="row">
                                                        <div class="widget-box">
                                                            <div class="widget-header">Supervisi&oacute;n</div>
                                                            <div class="widget-body">
                                                                <div class="row">
                                                                    <br/>

                                                                    <div class="col-xs-10 col-xs-offset-1">

                                                                        <div class="col-xs-6">
                                                                            <div class="widget-box">
                                                                                <div class="widget-header">Estatus</div>
                                                                                <div class="widget-body">
                                                                                    <div class="row">
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstStatusCase",4); addIdToList("lstStCaseStr","-Formato de audiencia completo");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Formato de audiencia completo</span>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstStatusCase",7); addIdToList("lstStCaseStr","-Entrevista de encuadre completa");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Entrevista de encuadre completa</span>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       id="hasMonP"
                                                                                                       name="hasMonP"
                                                                                                       ng-model="hasMonP"
                                                                                                       ng-ckick='addIdToList("lstStCaseStr","-Plan de monitorieo");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Plan de monitoreo</span>
                                                                                            </label>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-xs-6">
                                                                            <div class="widget-box">
                                                                                <%--<div class="widget-header">Audiencia</div>--%>
                                                                                <div class="widget-header">Situaci&oacute;n
                                                                                    legal
                                                                                </div>
                                                                                <div class="widget-body">
                                                                                    <div class="row">
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstHearingType",2); addIdToList("lstHearingTpStr","-MC");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;MC</span>
                                                                                            </label>
                                                                                        </div>
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstHearingType",1); addIdToList("lstHearingTpStr","-SCPP");'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;SCPP</span>
                                                                                            </label>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-xs-10 col-xs-offset-1">

                                                                            <div class="col-xs-6"
                                                                                 ng-init='lstActivities=${lstActivities}'>
                                                                                <div class="widget-box">
                                                                                    <div class="widget-header">
                                                                                        Actividades de supervisi&oacute;n
                                                                                    </div>
                                                                                    <div class="widget-body">
                                                                                        <div class="row"
                                                                                             ng-repeat="activity in lstActivities">
                                                                                            <div class="checkbox">
                                                                                                <label>
                                                                                                    <input class="ace"
                                                                                                           type="checkbox"
                                                                                                           ng-click='addIdToList("lstActivitiesSel",activity.id); addIdToList("lstActivitiesStr","-" + activity.name);'>
                                                                                                    <span class="lbl col-xs-10">&nbsp;&nbsp;{{activity.name}}</span>
                                                                                                </label>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                        <div class="col-xs-6"
                                                                             ng-init='lstCrimes=${lstCrimes}'>
                                                                            <div class="widget-box">
                                                                                <div class="widget-header">Delitos</div>
                                                                                <div class="widget-body">
                                                                                    <div class="row"
                                                                                         ng-repeat="crime in lstCrimes">
                                                                                        <div class="checkbox">
                                                                                            <label>
                                                                                                <input class="ace"
                                                                                                       type="checkbox"
                                                                                                       ng-click='addIdToList("lstCrimesSel",crime.id); addIdToList("lstCrimesStr","-" + crime.name);'>
                                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;{{crime.name}}</span>
                                                                                            </label>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-xs-10 col-xs-offset-1"
                                                                         ng-init='lstArrangement=${lstArrangement}'>
                                                                        <div class="widget-box">
                                                                            <div class="widget-header">Obligaciones
                                                                                procesales
                                                                            </div>
                                                                            <div class="widget-body">
                                                                                <div class="row">
                                                                                    <div class="col-xs-12">
                                                                                        <div class="widget-box">
                                                                                            <div class="widget-header">
                                                                                                Local
                                                                                            </div>
                                                                                            <div class="widget-body">
                                                                                                <div class="row">
                                                                                                    <div class="col-xs-10 col-xs-offset-1">
                                                                                                        <div class="widget-box collapsed">
                                                                                                            <div class="widget-header">
                                                                                                                MC
                                                                                                                <div class="widget-toolbar">
                                                                                                                    <a data-action="collapse"
                                                                                                                       href="#"><i
                                                                                                                            class="icon-chevron-down"></i></a>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <div class="widget-body">
                                                                                                                <div class="row"
                                                                                                                     ng-repeat="arrMCL in lstArrangement">
                                                                                                                    <div class="checkbox"
                                                                                                                         ng-if="arrMCL.isNational==false&&arrMCL.type==2">
                                                                                                                        <label>
                                                                                                                            <input class="ace"
                                                                                                                                   type="checkbox"
                                                                                                                                   ng-click='addIdToList("lstArrangementSel",arrMCL.id); addIdToList("lstArrangementStr","-"+arrMCL.description);'>
                                                                                                                            <span class="lbl col-xs-10">&nbsp;&nbsp;{{arrMCL.description}}</span>
                                                                                                                        </label>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                    <div class="col-xs-10 col-xs-offset-1">
                                                                                                        <div class="widget-box collapsed">
                                                                                                            <div class="widget-header">
                                                                                                                SCPP
                                                                                                                <div class="widget-toolbar">
                                                                                                                    <a data-action="collapse"
                                                                                                                       href="#"><i
                                                                                                                            class="icon-chevron-down"></i></a>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <div class="widget-body">
                                                                                                                <div class="row"
                                                                                                                     ng-repeat="arrSCPPL in lstArrangement">
                                                                                                                    <div class="checkbox"
                                                                                                                         ng-if="arrSCPPL.isNational==false&&arrSCPPL.type==1">
                                                                                                                        <label>
                                                                                                                            <input class="ace"
                                                                                                                                   type="checkbox"
                                                                                                                                   ng-click='addIdToList("lstArrangementSel",arrSCPPL.id); addIdToList("lstArrangementStr","-"+arrSCPPL.description);'>
                                                                                                                            <span class="lbl col-xs-10">&nbsp;&nbsp;{{arrSCPPL.description}}</span>
                                                                                                                        </label>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-xs-12">
                                                                                        <div class="widget-box">
                                                                                            <div class="widget-header">
                                                                                                Nacional
                                                                                            </div>
                                                                                            <div class="widget-body">
                                                                                                <div class="row">
                                                                                                    <div class="col-xs-10 col-xs-offset-1">
                                                                                                        <div class="widget-box collapsed">
                                                                                                            <div class="widget-header">
                                                                                                                MC
                                                                                                                <div class="widget-toolbar">
                                                                                                                    <a data-action="collapse"
                                                                                                                       href="#"><i
                                                                                                                            class="icon-chevron-down"></i></a>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <div class="widget-body">
                                                                                                                <div class="row"
                                                                                                                     ng-repeat="arrMCN in lstArrangement">
                                                                                                                    <div class="checkbox"
                                                                                                                         ng-if="arrMCN.isNational==true&&arrMCN.type==2">
                                                                                                                        <label>
                                                                                                                            <input class="ace"
                                                                                                                                   type="checkbox"
                                                                                                                                   ng-click='addIdToList("lstArrangementSel",arrMCN.id); addIdToList("lstArrangementStr","-"+arrMCN.description);'>
                                                                                                                            <span class="lbl col-xs-10">&nbsp;&nbsp;{{arrMCN.description}}</span>
                                                                                                                        </label>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                    <div class="col-xs-10 col-xs-offset-1">
                                                                                                        <div class="widget-box collapsed">
                                                                                                            <div class="widget-header">
                                                                                                                SCPP
                                                                                                                <div class="widget-toolbar">
                                                                                                                    <a data-action="collapse"
                                                                                                                       href="#"><i
                                                                                                                            class="icon-chevron-down"></i></a>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                            <div class="widget-body">
                                                                                                                <div class="row"
                                                                                                                     ng-repeat="arrSCPPN in lstArrangement">
                                                                                                                    <div class="checkbox"
                                                                                                                         ng-if="arrSCPPN.isNational==true&&arrSCPPN.type==1">
                                                                                                                        <label>
                                                                                                                            <input class="ace"
                                                                                                                                   type="checkbox"
                                                                                                                                   ng-click='addIdToList("lstArrangementSel",arrSCPPN.id); addIdToList("lstArrangementStr","-"+arrSCPPN.description);'>
                                                                                                                            <span class="lbl col-xs-10">&nbsp;&nbsp;{{arrSCPPN.description}}</span>
                                                                                                                        </label>
                                                                                                                    </div>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <br/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </sec:authorize>
                                                    <br/>
                                                </div>
                                                <br/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-11 element-right">
<span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
      ng-click="submitFindCases('#FormRepExcel','<c:url value='/director/excelReport/findCases.json'/>');">
                      Realizar b&uacute;squeda
                </span>
                            </div>

                        </div>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
        <br/>
    </form>
    <div class="row">
        <div id="angJsjqGridId" ng-controller="modalDlgController">
            <table id="GridCasesId" class="element-center" style="margin: auto"></table>
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
</div>

</body>
</html>

<script type="text/javascript">

    jQuery(function ($) {
        /*$('#initDate').datepicker({autoclose: true}).next().on(ace.click_event, function () {
         $(this).prev().focus();
         });*/

        $('#initDate').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $('#endDate').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

    });
</script>