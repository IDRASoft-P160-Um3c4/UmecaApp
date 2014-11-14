<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>

    $(document).ready(function () {

        var urlGridReferences = $('#urlGridReferences').attr("value");
        var idCase = $('#hidIdCase').attr("value");
        var canTerminate = $('#canTerminateRef').attr("value");

        upsertReference = function (id) {
            if (canTerminate == 'true')
                window.showUpsertWithIdCase(id, "#angJsjqGridIdReferences", "<c:url value='/supervisor/framingMeeting/references/upsert.html'/>", "#GridReferences", undefined, idCase);
        };

        deleteReference = function (id) {
            if (canTerminate == 'true')
                window.showObsolete(id, "#angJsjqGridIdReferences", "<c:url value='/supervisor/framingMeeting/reference/delete.json'/>", "#GridReferences");
        };

        jQuery("#GridReferences").jqGrid({
            url: urlGridReferences,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre', 'Tel&eacute;fono', 'Parentesco', 'Direcci&oacute;n', 'Acompa&ntilde;ar&aacute<br/>en el proceso', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'name', index: 'name', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'phone', index: 'phone', width: 140, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'relationshipName', index: 'relationshipName', width: 110, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'address', index: 'address', width: 230, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'isAccompanimentString', index: 'isAccompanimentString', width: 100, align: "center", search: false },
                { name: 'Action', width: 50, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerReferences',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar usuario\" onclick=\"upsertReference('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Deshabilitar usuario\" onclick=\"deleteReference('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridReferences").jqGrid('navGrid', '#GridPagerReferences', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertReference, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridReferences").jqGrid('filterToolbar', {
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
    <input type="hidden" id="urlGridReferences" value="listReferences.json?idCase={{fm.objView.idCase}}"/>
    <input type="hidden" id="canTerminateRef" value="{{fm.objView.canTerminate}}"/>

    <div class="col-xs-12">
        <div ng-show="referencesSuccessMsg&&referencesSuccessMsg!=''"
             class="col-xs-12 alert alert-success element-center success-font" ng-bind-html="referencesSuccessMsg">
        </div>
        <div ng-show="referencesErrorMsg&&referencesErrorMsg!=''" class="alert alert-danger element-center error-font"
             ng-bind-html="referencesErrorMsg">
        </div>
    </div>
    <br/>
    <br/>

    <div class="col-xs-12">
        <h2><i class="blue icon-group bigger-100">&nbsp;</i>Referencias personales</h2>
        <br/>

        <div id="angJsjqGridIdReferences" ng-controller="modalDlgController">
            <table id="GridReferences" class="element-center" style="margin: auto"></table>
            <div id="GridPagerReferences"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
        <br/>
        <br/>

        <div class="element-left">
            <form id="FormCommentReference" class="form-horizontal" role="form">
                <div class="col-xs-10 col-xs-offset-1">
                    <div class="col-xs-8">
                        <label for="referencesComments">Observaciones</label>
                        <br/>
                        <textarea ng-model="fm.objView.referencesComments"
                                  id="referencesComments"
                                  name="referencesComments"
                                  type="text" class="input-xxlarge"
                                  data-val="true"
                                  data-val-required="Observaciones es un campo requerido">
                        </textarea>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="referencesComments"
                  data-valmsg-replace="true"></span>
                    </div>
                </div>
            </form>
            <div class="col-xs-12">
                <div class="modal-footer" ng-show="fm.objView.canTerminate==true">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitComments('#FormCommentReference', '<c:url value="/supervisor/framingMeeting/upsertReferencesComments.json?idCase="/>',fm.objView.idCase);">
                    <span class="glyphicon glyphicon-cloud-upload"></span>
                    Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
