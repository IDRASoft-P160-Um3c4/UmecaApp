<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>
<style>

    .profile-info-name{
           width: 200px !important;
    }
    .profile-info-value{
        margin-left: 200px !important;
    }
</style>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-folder-open "></i>&nbsp;&nbsp;Abrir caso no judicializados</h4>
                    </div>
                </div>
                <div class="modal-body" >
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
                        <br />
                            <%@ include file="/WEB-INF/jsp/supervisor/caseNotProsecute/content.jsp"%>
                    </form>
                    <br />
                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError" class="alert alert-danger element-center">
                                {{MsgError}}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled='WaitFor==true ||( comment== "" && password == "")'
                          ng-click="submit('#FormCatId', '<c:url value="/supervisor/caseNotProsecute/doOpen.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

