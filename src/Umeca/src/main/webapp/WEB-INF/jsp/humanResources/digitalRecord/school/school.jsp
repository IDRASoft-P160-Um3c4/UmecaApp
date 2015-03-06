<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
    $(document).ready(function () {

        var urlGrid = $('#urlGridSchool').attr("value");
        var idCase = $('#hidIdCase').attr("value");

        upsertSchool = function (id) {
            //if (canTerminate == 'true')
            window.showUpsertWithIdCase(id, "#angJsjqGridSchool", "<c:url value='/supervisor/framingMeeting/school/upsert.html'/>", "#GridSchool", undefined, idCase);
        };

        deleteSchool = function (id) {
            //if (canTerminate == 'true')
            window.showObsolete(id, "#angJsjqGridSchool", "<c:url value='/supervisor/framingMeeting/school/delete.json'/>", "#GridSchool");
        };

        $(document).ready(function () {
            jQuery("#GridSchool").jqGrid({
                autoencode: true,
//                url: urlGridSchool,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Nombre', 'Lugar', 'Documento', 'Acci&oacute;n'],
                colModel: [
                    {name: 'id', index: 'id', hidden: true},
                    {
                        name: 'name',
                        index: 'name',
                        width: 200,
                        align: "center",
                        sorttype: 'string',
                        searchoptions: {sopt: ['bw']}
                    },
                    {name: 'place', index: 'place', width: 200, align: "center", sorttype: 'string', search: false},
                    {name: 'docName', index: 'docName', width: 200, align: "center", search: false},
                    {
                        name: 'Action',
                        width: 65,
                        align: "center",
                        sortable: false,
                        search: false,
                        formatter: window.actionFormatter
                    }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPagerSchool',
                sortname: 'id',
                height: 200,
                viewrecords: true,
                shrinkToFit: false,
                sortorder: "asc",
                caption: "&nbsp;",
                altRows: true,
                gridComplete: function () {
                    var ids = $(this).jqGrid('getDataIDs');
                    for (var i = 0; i < ids.length; i++) {
                        var cl = ids[i];
                        var row = $(this).getRowData(cl);
                        var enabled = row.enabled;
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar usuario\" onclick=\"window.upsertSchool('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Deshabilitar usuario\" onclick=\"window.deleteSchool('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

            jQuery("#GridSchool").jqGrid('navGrid', '#GridPagerSchool', {
                edit: false, editicon: 'icon-pencil blue',
                add: true, addfunc: window.upsertSchool, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false
            });

            jQuery("#GridSchool").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });

        });
    });

</script>
<div class="col-xs-12">
    <div class="row">
        <div class="col-xs-10 col-xs-offset-1">
            <input type="hidden" id="hidIdCase" value="{{fm.objView.idCase}}"/>
            <input type="hidden" id="urlGridSchool" value="listSchool.json?idCase={{fm.objView.idCase}}"/>

            <div class="element-center">
                <h2><i class="blue icon-book  bigger-100">&nbsp;</i>Historia escolar</h2>
                <br/>
            </div>

            <div class="col-xs-12">

                <div class="row">
                    <%--ng-controller="emplyeeSchoolController">--%>
                    <form id="FormLastGrade" name="FormLastGrade" class="form-horizontal" role="form">
                        <div class="widget-box">
                            <div class="widget-header element-left">Ultimo grado de estudios</div>
                            <div class="widget-body">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <br/>

                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="col-xs-12">
                                                    <label for="name">Escuela</label>
                                                    <br/>
                                                    <input id="name" ng-model="lg.name" name="name"
                                                           type="text" class="input-xxlarge" data-val="true"
                                                           data-val-required="Escuela es un campo requerido"/>
                                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="name"
                              data-valmsg-replace="true"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>

                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label>Nivel</label>
                                                    <br/>
                                                    <select id="acLevel" class="form-control element-center"
                                                            ng-model="lg.acLevel"
                                                            ng-options="e.name for e in lstAcLevel"
                                                    <%--ng-init='lstAcLevel = ${lstAcLevel};'--%>
                                                            ></select>
                                                    <input type="hidden" name="acLevelId" value="{{lg.acLevel.id}}"/>
                                                </div>
                                                <div class="col-xs-6">
                                                    <label>Grado</label>
                                                    <br/>
                                                    <select id="degree" class="form-control element-center"
                                                            ng-model="lg.degree"
                                                            ng-options="e.name for e in lstDegree"
                                                    <%--ng-init='lstDegree = ${lstDegree};'--%>
                                                            ></select>
                                                    <input type="hidden" name="degreeId" value="{{lg.degree.id}}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>

                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label>Documento obtenido</label>
                                                    <br/>
                                                    <select id="document" class="form-control element-center"
                                                            ng-model="lg.document"
                                                            ng-options="e.name for e in lstDocs"
                                                    <%--ng-init='lstDocs = ${lstDocs};'--%>
                                                            ></select>
                                                    <input type="hidden" name="documentId" value="{{lg.document.id}}"/>
                                                </div>
                                                <div class="col-xs-6" ng-show="lg.document.specification==true">
                                                    <label for="docSpec">Especificaci&oacute;n</label>
                                                    <br/>
                                                    <input id="docSpec" ng-model="lg.docSpec"
                                                           name="docSpec" type="text"
                                                           class="input-xxlarge" data-val="true"
                                                           data-val-required="Especificaci&oacute;n es un campo requerido"/>
                                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="docSpec"
                              data-valmsg-replace="true"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>

                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class=" modal-footer element-right">
                                    <span class="btn btn-default btn-sm btn-primary"
                                          ng-click="submitLastGrade('#FormLastGrade','<c:url value='/supervisor/hearingFormat/indexFormats.html'/>'+'?id='+m.idCase)">
                                    Guardar
                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="row">
                    <div class="widget-box">
                        <div class="widget-header">Cursos/Logros</div>
                        <div class="widget-body">
                            <div class="row">
                                <div class="col-xs-12" style="padding: 2%;">
                                    <div id="angJsjqGridSchool" ng-controller="modalDlgController">
                                        <table id="GridSchool" class="element-center" style="margin: auto"></table>
                                        <div id="GridPagerSchool"></div>
                                        <div class="blocker" ng-show="working">
                                            <div>
                                                Cargando...<img
                                                    src="<c:url value='/assets/content/images/ajax_loader.gif' />"
                                                    alt=""/>
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
