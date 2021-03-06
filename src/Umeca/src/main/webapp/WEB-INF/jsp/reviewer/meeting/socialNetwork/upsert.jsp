<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormSocialNetworkId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="socialNetworkController" ng-cloak>
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Persona de su red social</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormSocialNetworkId" name="FormSocialNetworkId" ng-submit="submit('#FormSocialNetworkId')" class="form-horizontal" role="form">
                        <br />
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/socialNetwork/content.jsp"%>
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
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormSocialNetworkId', '<c:url value="/reviewer/meeting/socialNetwork/doUpsert.json?idCase=${idCase}"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

