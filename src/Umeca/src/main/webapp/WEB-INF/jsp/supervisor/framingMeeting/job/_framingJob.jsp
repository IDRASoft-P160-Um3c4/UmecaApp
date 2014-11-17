<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        var urlGridJob = $('#urlGridJob').attr("value");
        var idCase = $('#hidIdCase').attr("value");

        var canTerminate = $('#canTerminateJob').attr("value");

        upsertJob = function (id) {
            if (canTerminate == 'true')
                window.showUpsertWithIdCase(id, "#angJsjqGridIdJob", "<c:url value='/supervisor/framingMeeting/job/upsert.html'/>", "#GridIdJob", undefined, idCase);
        };

        deleteJob = function (id) {
            if (canTerminate == 'true')
                window.showObsolete(id, "#angJsjqGridIdJob", "<c:url value='/supervisor/framingMeeting/job/delete.json'/>", "#GridIdJob");
        };

        $(document).ready(function () {
            jQuery("#GridIdJob").jqGrid({
                url: urlGridJob,
                datatype: "json",
                mtype: 'POST',
                colNames: ['ID', 'Empresa', 'Puesto', 'Patr&oacute;n', 'Tel&eacute;fono', 'Tipo', 'TipoId', 'Acci&oacute;n'],
                colModel: [
                    { name: 'id', index: 'id', hidden: true },
                    { name: 'company', index: 'company', width: 170, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'post', index: 'post', width: 110, align: "center", sorttype: 'string', search: false },
                    { name: 'nameHead', index: 'nameHead', width: 120, align: "center", search: false },
                    { name: 'phone', index: 'phone', width: 120, align: "center", search: false },
                    { name: 'registerTypeString', index: 'registerTypeString', width: 120, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                    { name: 'registerTypeId', index: 'registerTypeId', hidden: true},
                    { name: 'Action', width: 65, align: "center", sortable: false, search: false }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#GridPagerJob',
                sortname: 'registerTypeId',
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
                        var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar usuario\" onclick=\"window.upsertJob('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Deshabilitar usuario\" onclick=\"window.deleteJob('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

            jQuery("#GridIdJob").jqGrid('navGrid', '#GridPagerJob', {
                edit: false, editicon: 'icon-pencil blue',
                add: true, addfunc: window.upsertJob, addicon: 'icon-plus-sign purple',
                refresh: true, refreshicon: 'icon-refresh green',
                del: false,
                search: false});

            jQuery("#GridIdJob").jqGrid('filterToolbar', {
                stringResult: true,
                searchOperators: true,
                searchOnEnter: true,
                multipleSearch: true,
                ignoreCase: true
            });

        });
    });

</script>

<div class="row element-center">

    <input type="hidden" id="hidIdCase" value="{{fm.objView.idCase}}"/>
    <input type="hidden" id="urlGridJob" value="listJob.json?idCase={{fm.objView.idCase}}"/>
    <input type="hidden" id="canTerminateJob" value="{{fm.objView.canTerminate}}"/>

    <div class="col-xs-12">
        <div ng-show="jobSuccessMsg&&jobSuccessMsg!=''"
             class="col-xs-12 alert alert-success element-center success-font" ng-bind-html="jobSuccessMsg">
        </div>
        <div ng-show="jobErrorMsg&&jobErrorMsg!=''" class="alert alert-danger element-center error-font"
             ng-bind-html="jobErrorMsg">
        </div>
    </div>
    <br/>
    <br/>

    <div class="col-xs-12">
        <h2><i class="blue icon-group bigger-100">&nbsp;</i>Historia laboral</h2>
        <br/>

        <div id="angJsjqGridIdJob" ng-controller="modalDlgController">
            <table id="GridIdJob" class="element-center" style="margin: auto"></table>
            <div id="GridPagerJob"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
        <br/>
        <br/>

        <div class="element-left">
            <form id="FormCommentJob" class="form-horizontal" role="form">
                <div class="col-xs-10 col-xs-offset-1">
                    <div class="col-xs-8">
                        <label for="jobComments">Observaciones</label>
                        <br/>
                        <textarea ng-model="fm.objView.jobComments"
                                  id="jobComments"
                                  name="jobComments"
                                  type="text" class="input-xxlarge"
                                  data-val="true"
                                  data-val-required="Observaciones es un campo requerido">
                        </textarea>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="jobComments"
                  data-valmsg-replace="true"></span>
                    </div>
                </div>
            </form>
            <div class="col-xs-12">
                <div class="modal-footer" ng-show="fm.objView.canTerminate==true">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitComments('#FormCommentJob', '<c:url value="/supervisor/framingMeeting/upsertJobComments.json?idCase="/>',fm.objView.idCase);">
                    <span class="glyphicon glyphicon-cloud-upload"></span>
                    Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
