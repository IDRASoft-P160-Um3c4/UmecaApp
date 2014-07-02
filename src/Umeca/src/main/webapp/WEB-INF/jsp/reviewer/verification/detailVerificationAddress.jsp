<div>
    <div id="dlgUpModalIdAddress" class="modal fade" ng-controller="upsertVerificationController" ng-cloak>
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-warning ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center">&nbsp;&nbsp;Dato proporcionado por la fuente</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatIdAddress" name="FormCatIdAddress" ng-submit="submit('#FormCatIdAddress')" class="form-horizontal"
                          role="form">

                        <div id="divElementVerif" class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
                            </div>
                        </div>
                        <br/>

                    </form>

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
                          ng-click="submit('#FormCatIdAddress', '/reviewer/meeting/doNewMeeting.json');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>


