<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        var urlGridActivities = $('#urlGridActivities').attr("value");
        var idCase = $('#hidIdCase').attr("value");
        var canTerminate = $('#canTerminateActivities').attr("value");

        upsertActivity = function (id) {
            window.showUpsertWithIdCase(id, "#angJsjqGridIdActivities", "<c:url value='/supervisor/framingMeeting/activities/upsert.html'/>", "#GridActivities", undefined, idCase);
        };

        deleteActivity = function (id) {
            window.showObsolete(id, "#angJsjqGridIdActivities", "<c:url value='/supervisor/framingMeeting/activities/delete.json'/>", "#GridActivities");
        };

        jQuery("#GridActivities").jqGrid({
            autoencode:true,
            url: urlGridActivities,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre', 'Descripci&oacute;n', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'name', index: 'name', width: 310, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'description', index: 'description', align: "center", width: 310, sortable: false},
                { name: 'Action', width: 100, align: "center", sortable: false, search: false,formatter:window.actionFormatter }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerActivities',
            sortname: 'name',
            height: 200,
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
                    var enabled = row.enabled;
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar actividad\" onclick=\"window.upsertActivity('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";
                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar actividad\" onclick=\"window.deleteActivity('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridActivities").jqGrid('navGrid', '#GridPagerActivities', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertActivity, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridActivities").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    });

</script>

<div class="row element-center">

    <input type="hidden" id="hidIdCase" value="{{fm.objView.idCase}}"/>
    <input type="hidden" id="urlGridActivities" value="listActivities.json?idCase={{fm.objView.idCase}}"/>
    <input type="hidden" id="canTerminateActivities" value="{{fm.objView.canTerminate}}"/>

    <div class="col-xs-12">
        <div ng-show="activitiesSuccessMsg&&activitiesSuccessMsg!=''"
             class="col-xs-12 alert alert-success element-center success-font" ng-bind-html="activitiesSuccessMsg">
        </div>
        <div ng-show="activitiesErrorMsg&&activitiesErrorMsg!=''" class="alert alert-danger element-center error-font"
             ng-bind-html="activitiesErrorMsg">
        </div>
    </div>
    <br/>
    <br/>

    <div class="col-xs-12">
        <h2><i class="blue icon-group bigger-100">&nbsp;</i>Actividades que realiza el imputado</h2>
        <br/>

        <div id="angJsjqGridIdActivities" ng-controller="modalDlgController">
            <table id="GridActivities" class="element-center" style="margin: auto"></table>
            <div id="GridPagerActivities"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
        <br/>
        <br/>

        <div class="element-left">
            <form id="FormCommentActivities" class="form-horizontal" role="form">
                <div class="col-xs-10 col-xs-offset-1">
                    <div class="col-xs-8">
                        <label for="activitiesComments">Observaciones</label>
                        <br/>
                        <textarea ng-model="fm.objView.activitiesComments"
                                  id="activitiesComments"
                                  name="activitiesComments"
                                  type="text" class="input-xxlarge"
                                  data-val="true"
                                  data-val-required="Observaciones es un campo requerido">
                        </textarea>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="housemateComments"
                  data-valmsg-replace="true"></span>
                    </div>
                </div>
            </form>
            <div class="col-xs-12">
                <div class="modal-footer">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitComments('#FormCommentActivities', '<c:url value="/supervisor/framingMeeting/upsertActivitiesComments.json?idCase="/>',fm.objView.idCase);">
                    <span class="glyphicon glyphicon-cloud-upload"></span>
                    Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
