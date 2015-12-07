<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
    window.upsertJob = function (id) {
        window.showUpsertWithIdCase(id, "#angJsjqGridIdJob", "<c:url value='/reviewer/meeting/job/upsert.html'/>", "#GridIdJob", undefined, ${m.caseDetention.id});
    };

    window.deleteJob = function (id) {
        window.showObsolete(id, "#angJsjqGridIdJob", "<c:url value='/reviewer/meeting/job/delete.json'/>", "#GridIdJob");
    };

    $(document).ready(function () {
        jQuery("#GridIdJob").jqGrid({
            url: '<c:url value='/reviewer/meeting/listJob.json?idCase=${m.caseDetention.id}' />',
            autoencode:true,
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Empresa', 'Puesto', 'Patr&oacute;n', 'Tel&eacute;fono', 'Tipo', 'TipoId', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'company', index: 'company', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'post', index: 'post', width: 150, align: "center", sorttype: 'string', search: false },
                { name: 'nameHead', index: 'nameHead', width: 160, align: "center", search: false },
                { name: 'phone', index: 'phone', width: 150, align: "center", search: false },
                { name: 'registerTypeString', index: 'registerTypeString', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'registerTypeId', index: 'registerTypeId', hidden: true},
                { name: 'Action', width: 70, align: "center", sortable: false, search: false, formatter: window.actionFormatter }
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar registro\" onclick=\"window.upsertJob('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Eliminar registro\" onclick=\"window.deleteJob('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

</script>

<div class="row element-center">
    <div class="col-xs-12">
        <h2><i class="pink icon-briefcase  bigger-100">&nbsp;</i>Historia Laboral</h2>
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
    </div>
</div>
<br/>
<br/>

<div class="row" ng-controller="scController">
    <div class="blocker" ng-show="WaitFor==true">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>
    <div class="col-xs-10 col-xs-offset-1">
        <div ng-show="msgSuccess" class="alert alert-success element-center success-font">
            <span ng-bind-html="msgSuccess"></span>
        </div>
    </div>
    <form id="FormCommentJobId" name="FormCommentJobId" class="form-horizontal" role="form">
        <div class="col-xs-3 element-right">Observaciones:<br/>
            <label class="info-example">(Este campo no es verificable)</label></div>
        <div class="col-xs-8">
            <textarea class="width-100"
                      data-val="true" ng-model="comment"
                      ng-init='comment = "${m.commentJob == null ? '' : m.commentJob}";'
                      data-val-required="Las observaciones es un campo requerido"
                      data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                      data-val-length-max="500"
                      data-val-length-min="1"
                      name="commentJob"></textarea>
                <span class="field-validation-valid" data-valmsg-for="commentJob"
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
                          ng-click="upsertComment(${idCase}, '<c:url value="/reviewer/meeting/upsertComment.json"/>',5);">
                        <span class="glyphicon glyphicon-cloud-upload"></span>
                          Guardar
                    </span>
        </div>
    </div>
</div>
