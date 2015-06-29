<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<style>
    textarea{
        max-width: none;
    }
</style>
<script>
    window.upsertSocialNetwork = function(id) {

        window.showUpsertWithIdCase(id, "#angJsjqGridIdSocialNetwork", "<c:url value='/reviewer/meeting/socialNetwork/upsert.html'/>", "#GridIdSocialNetwork",undefined, ${m.caseDetention.id});
    };

    window.deleteSocialNetwork = function (id) {
        window.showObsolete(id, "#angJsjqGridIdSocialNetwork", "<c:url value='/reviewer/meeting/socialNetwork/delete.json'/>", "#GridIdSocialNetwork");
    };

    $(document).ready(function() {
        jQuery("#GridIdSocialNetwork").jqGrid({
            autoencode:true,
            url: '<c:url value='/reviewer/meeting/listSocialNetwork.json?idCase=${m.caseDetention.id}' />',
            datatype: "json",
            mtype: 'POST',
            colNames: ['ID', 'Nombre','Relaci&oacute;n','Edad','Tel&eacute;fono','Acompa&ntilde;a al imputado <br/> durante el proceso','Dependiente<br/>econ&oacute;mico', 'Acci&oacute;n'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'name', index: 'name', width: 200, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'relName', index: 'relName', width: 150, align: "center", sorttype: 'string', searchoptions: { sopt: ['bw'] } },
                { name: 'age', index: 'age', width: 70, align: "center",  search: false },
                { name: 'phone', index: 'phone', width: 150, align: "center",  search: false  },
                { name: 'isAccompanimentString', index: 'isAccompanimentString', width: 180, align: "center",  search: false, sortable:false  },
                { name: 'dependentString', index: 'dependentString', width: 120, align: "center",  search: false, sortable:false  },
                { name: 'Action', width: 70, align: "center", sortable: false, search: false,formatter:window.actionFormatter }
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#GridPagerSocialNetwork',
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
                    var be = "<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Editar persona de red social\" onclick=\"window.upsertSocialNetwork('" + cl + "');\"><span class=\"glyphicon glyphicon-pencil\"></span></a>";

                        be += "&nbsp;&nbsp;<a href=\"javascript:;\" style=\"display:inline-block;\" title=\"Borrar persona de red social\" onclick=\"window.deleteSocialNetwork('" + cl + "');\"><span class=\"glyphicon glyphicon-trash\"></span></a>";
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

        jQuery("#GridIdSocialNetwork").jqGrid('navGrid', '#GridPagerSocialNetwork', {
            edit: false, editicon : 'icon-pencil blue',
            add: true, addfunc: window.upsertSocialNetwork, addicon : 'icon-plus-sign purple',
            refresh: true, refreshicon : 'icon-refresh green',
            del: false,
            search: false});

        jQuery("#GridIdSocialNetwork").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    });

</script>

<div class="row element-center">
        <h2> <i class="blue icon-group bigger-100"></i> &nbsp;Red social</h2>
        <br/>
        <div id="angJsjqGridIdSocialNetwork" ng-controller="modalDlgController">
            <table id="GridIdSocialNetwork" class="element-center" style="margin: auto"></table>
            <div id="GridPagerSocialNetwork"></div>
            <div class="blocker" ng-show="working">
                <div>
                    Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
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
    <form id="FormSocialNetworkIndexId" name="FormSocialNetworkIndexId" class="form-horizontal" role="form">
                <div class="col-xs-3 element-right">Observaciones:<br/>
                    <label class="info-example">(no tiene donde vivir, existe violencia, etc.)</label></div>
                <div class="col-xs-8">
                    <textarea class="width-100"     ng-model = "comment" ng-init='comment = "${m.socialNetwork.comment == null ? '' : m.socialNetwork.comment}";'
                              data-val-required="Las observaciones es un campo requerido"
                              data-val="true"
                              data-val-required="Las observaciones es un campo requerido"
                              data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                              data-val-length-max="500"
                              data-val-length-min="1"
                              name="socialNetwork.comment"></textarea>
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
                          ng-click="upsertComment(${idCase}, '<c:url value="/reviewer/meeting/upsertComment.json"/>',3);">
                        <span class="glyphicon glyphicon-cloud-upload"></span>
                          Guardar
                    </span>
        </div>
    </div>
</div>