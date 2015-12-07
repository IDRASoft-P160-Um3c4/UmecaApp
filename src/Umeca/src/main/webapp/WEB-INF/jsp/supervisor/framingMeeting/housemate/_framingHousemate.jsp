<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(document).ready(function () {

        var urlGridHousemate = $('#urlGridHousemate').attr("value");
        var idCase = $('#hidIdCase').attr("value");

        var canTerminate = $('#canTerminateHousemate').attr("value");

        upsertHousemate = function (id) {
            //if (canTerminate == 'true')
            window.showUpsertWithIdCase(id, "#angJsjqGridIdHouseMate", "<c:url value='/supervisor/framingMeeting/housemate/upsert.html'/>", "#GridHouseMate", undefined, idCase);
        };

        deleteHousemate = function (id) {
            //if (canTerminate == 'true')
            window.showObsolete(id, "#angJsjqGridIdHouseMate", "<c:url value='/supervisor/framingMeeting/reference/delete.json'/>", "#GridHouseMate");
        };

        jQuery("#GridHouseMate").jqGrid({
            autoencode:true,
            url: urlGridHousemate,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre', 'Edad', 'Relaci&oacute;n', 'Ocupaci&oacute;n', 'Acompa&ntilde;ar&aacute<br/>en el proceso', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'name', index: 'name', width: 190, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'age', index: 'age', width: 80, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'relationshipName', index: 'relationshipName', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'occupation', index: 'occupation', width: 140, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'isAccompanimentString', index: 'isAccompanimentString', width: 100, align: "center", search: false },
                { name: 'Action', width: 60, align: "center", sortable: false, search: false, formatter:window.actionFormatter}
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerHouseMate',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar referencia\" onclick=\"window.upsertHousemate('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                    be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar referencia\" onclick=\"window.deleteHousemate('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridHouseMate").jqGrid('navGrid', '#GridPagerHouseMate', {
            edit: false, editicon: 'icon-pencil blue',
            add: true, addfunc: upsertHousemate, addicon: 'icon-plus-sign purple',
            refresh: true, refreshicon: 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridHouseMate").jqGrid('filterToolbar', {
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
    <input type="hidden" id="urlGridHousemate" value="listHousemate.json?idCase={{fm.objView.idCase}}"/>
    <input type="hidden" id="canTerminateHousemate" value="{{fm.objView.canTerminate}}"/>

    <div class="col-xs-12">
        <div ng-show="housemateSuccessMsg&&housemateSuccessMsg!=''"
             class="col-xs-12 alert alert-success element-center success-font" ng-bind-html="housemateSuccessMsg">
        </div>
        <div ng-show="housemateErrorMsg&&housemateErrorMsg!=''" class="alert alert-danger element-center error-font"
             ng-bind-html="housemateErrorMsg">
        </div>
    </div>
    <br/>
    <br/>

    <div class="col-xs-12">
        <h2><i class="blue icon-group bigger-100">&nbsp;</i>Personas con las que vive el imputado</h2>
        <br/>

        <div id="angJsjqGridIdHouseMate" ng-controller="modalDlgController">
            <table id="GridHouseMate" class="element-center" style="margin: auto"></table>
            <div id="GridPagerHouseMate"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                </div>
            </div>
        </div>
        <br/>
        <br/>

        <div class="element-left">
            <form id="FormCommentHousemate" class="form-horizontal" role="form">
                <div class="col-xs-10 col-xs-offset-1">
                    <div class="col-xs-8">
                        <label for="housemateComments">Observaciones</label>
                        <br/>
                        <textarea ng-model="fm.objView.housemateComments"
                                  id="housemateComments"
                                  name="housemateComments"
                                  type="text" class="input-xxlarge"
                                  data-val="true"
                                  data-val-required="Observaciones es un campo requerido"
                                  data-val-length="Debe tener m&aacute;ximo 1000 caracteres"
                                  data-val-length-max="1000">
                        </textarea>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="housemateComments"
                  data-valmsg-replace="true"></span>
                    </div>
                </div>
            </form>
            <div class="col-xs-12">
                <%--<div class="modal-footer" ng-show="fm.objView.canTerminate==true">--%>
                <div class="modal-footer">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitComments('#FormCommentHousemate', '<c:url value="/supervisor/framingMeeting/upsertHousemateComments.json?idCase="/>',fm.objView.idCase);">
                    <span class="glyphicon glyphicon-cloud-upload"></span>
                    Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
