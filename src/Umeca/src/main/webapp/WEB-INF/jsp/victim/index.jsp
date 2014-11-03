<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<script>
    window.upsertReference = function(id) {
        window.showUpsertWithIdCase(id, "#angJsjqGridIdReference", "<c:url value='/reviewer/meeting/reference/upsert.html'/>", "#GridIdReference",undefined, ${m.caseDetention.id});
    };

    window.deleteReference = function (id) {
        window.showObsolete(id, "#angJsjqGridIdReference", "<c:url value='/reviewer/meeting/reference/delete.json'/>", "#GridIdReference");
    };

    $(document).ready(function() {
        jQuery("#GridIdReference").jqGrid({
            url: '<c:url value='/reviewer/meeting/listReference.json?idCase=${m.caseDetention.id}' />',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre','Relaci&oacute;n','Edad','Tel&eacute;fono','Acompa&ntilde;a al imputado <br/> durante el proceso', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'fullName', index: 'fullName', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'relName', index: 'relName', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'age', index: 'age', width: 120, align: "center", sorttype: 'string', search: false },
                { name: 'phone', index: 'phone', width: 150, align: "center", search: false  },
                { name: 'accompanimentString', index: 'accompanimentString', width: 200, align: "center",  search: false  },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerReference',
            sortname: 'fullName',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar referencia\" onclick=\"window.upsertReference('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar referencia\" onclick=\"window.deleteReference('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
                          $(this).jqGrid('setRowData', ids[i], { Action: be });
                }
            },
            loadComplete : function() {
                var table = this;
                setTimeout(function(){
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            }
        });

        jQuery("#GridIdReference").jqGrid('navGrid', '#GridPagerReference', {
            edit: false, editicon : 'icon-pencil blue',
            add: true, addfunc: window.upsertReference, addicon : 'icon-plus-sign purple',
            refresh: true, refreshicon : 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridIdReference").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    });

</script>

<div class="row element-center">
    <div class="col-xs-12">
        <h2><i class="red icon-list bigger-100"></i> &nbsp;Referencias personales</h2>
        <br/>
        <div id="angJsjqGridIdReference" ng-controller="modalDlgController">
            <table id="GridIdReference" class="element-center" style="margin: auto"></table>
            <div id="GridPagerReference"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
                </div>
            </div>
        </div>
    </div>
</div>
    <br/><br/>
<div class="row" ng-controller="scController">
    <div class="col-xs-10 col-xs-offset-1">
        <div ng-show="msgSuccess" class="alert alert-success element-center success-font">
            <span ng-bind-html="msgSuccess"></span>
        </div>
    </div>
    <form id="FormCommentReferenceId" name="FormCommentReferenceId" class="form-horizontal" role="form">
        <div class="col-xs-3 element-right">Observaciones:<br/>
            <label class="info-example">(no tiene donde vivir, existe violencia, etc.)</label></div>
        <div class="col-xs-8">
            <textarea class="width-100"
                      ng-model = "comment" ng-init='comment = "${m.commentReference == null ? '' : m.commentReference}";'
                      data-val-required="Las observaciones es un campo requerido"
                      data-val="true"
                      data-val-required="Las observaciones es un campo requerido"
                      data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                      data-val-length-max="500"
                      data-val-length-min="1"
                      name="commentReference">${m.socialNetwork.comment}</textarea>
                <span class="field-validation-valid" data-valmsg-for="comment"
                      data-valmsg-replace="true"></span>
        </div>
    </form>
    <br/>
    <div class="col-xs-10 col-xs-offset-1">
        <div ng-show="msgError" class="alert alert-danger element-center error-font">
            <span ng-bind-html="msgError"></span>
        </div>
    </div>
    <div class="col-xs-12">
        <div class="modal-footer">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="upsertComment(${idCase}, '<c:url value="/reviewer/meeting/upsertComment.json"/>',4);">
                        <span class="glyphicon glyphicon-cloud-upload"></span>
                          Guardar
                    </span>
        </div>
    </div>
</div>
