<div  ng-init="idCase = ${idCase}; idSource=${idSource};">
    <div id="dlgUpModalIdAddress" class="modal fade" ng-controller="verificationAddressController" ng-cloak data-backdrop="static">
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-danger ">
                        <h4 class="element-center">&nbsp;&nbsp;Dato proporcionado por la fuente</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatIdAddress" name="FormCatIdAddress" ng-submit="submit('#FormCatIdAddress')" class="form-horizontal"
                          role="form">

                        <div id="divElementVerifAddress" class="row">
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
                    <input type="hidden" ng-init="urlToGoSaveAddress = '<c:url value="/reviewer/verification/saveAddressVerification.json"/>'" ng-model="urlToGoSaveAddress">
                    <span class="btn btn-danger btn-danger btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormCatIdAddress', ${idCase}, ${idSource});">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

