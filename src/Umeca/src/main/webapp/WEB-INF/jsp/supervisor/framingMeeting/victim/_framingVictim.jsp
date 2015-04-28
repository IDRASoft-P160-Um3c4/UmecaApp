<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        var urlGridVictim = $('#urlGridVictim').attr("value");
        var idCase = $('#hidIdCase').attr("value");

        var canTerminate = $('#canTerminateVictim').attr("value");

        upsertVictim = function (id) {
            window.showUpsertWithIdCase(id, "#angJsjqGridIdVictim", "<c:url value='/supervisor/framingMeeting/victim/upsert.html'/>", "#GridVictim", undefined, idCase);
        };

        deleteVictim = function (id) {
            window.showObsolete(id, "#angJsjqGridIdVictim", "<c:url value='/supervisor/framingMeeting/reference/delete.json'/>", "#GridVictim");
        };

        jQuery("#GridVictim").jqGrid({
            url: urlGridVictim,
            autoencode:true,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre', 'Edad', 'Relaci&oacute;n', 'T&eacute;lefono', 'Direcci&oacute;n', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'name', index: 'name', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'age', index: 'age', width: 60, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'relationshipName', index: 'relationshipName', width: 120, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'phone', index: 'phone', width: 110, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'address', index: 'address', width: 250, align: "center", search: false },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false, formatter:window.actionFormatter}
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerVictim',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar referencia\" onclick=\"window.upsertVictim('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar referencia\" onclick=\"window.deleteVictim('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridVictim").jqGrid('navGrid', '#GridPagerVictim', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertVictim, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridVictim").jqGrid('filterToolbar', {
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
    <input type="hidden" id="urlGridVictim" value="listVictim.json?idCase={{fm.objView.idCase}}"/>
    <input type="hidden" id="canTerminateVictim" value="{{fm.objView.canTerminate}}"/>

    <div class="col-xs-12">
        <div ng-show="victimSuccessMsg&&victimSuccessMsg!=''"
             class="col-xs-12 alert alert-success element-center success-font" ng-bind-html="victimSuccessMsg">
        </div>
        <div ng-show="victimErrorMsg&&victimErrorMsg!=''" class="alert alert-danger element-center error-font"
             ng-bind-html="victimErrorMsg">
        </div>
    </div>
    <br/>
    <br/>

    <div class="col-xs-12">
        <h2><i class="blue icon-group bigger-100">&nbsp;</i>Victimas y testigos</h2>
        <br/>

        <div id="angJsjqGridIdVictim" ng-controller="modalDlgController">
            <table id="GridVictim" class="element-center" style="margin: auto"></table>
            <div id="GridPagerVictim"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
        <br/>
        <br/>

        <div class="element-left">
            <form id="FormCommentVictim" class="form-horizontal" role="form">
                <div class="col-xs-10 col-xs-offset-1">
                    <div class="col-xs-8">
                        <label for="victimComments">Observaciones</label>
                        <br/>
                        <textarea ng-model="fm.objView.victimComments"
                                  id="victimComments"
                                  name="victimComments"
                                  type="text" class="input-xxlarge"
                                  data-val="true"
                                  data-val-required="Observaciones es un campo requerido">
                        </textarea>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="victimComments"
                  data-valmsg-replace="true"></span>
                    </div>
                </div>
            </form>
            <div class="col-xs-12">
                <div class="modal-footer">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitComments('#FormCommentVictim', '<c:url value="/supervisor/framingMeeting/upsertVictimComments.json?idCase="/>',fm.objView.idCase);">
                    <span class="glyphicon glyphicon-cloud-upload"></span>
                    Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
