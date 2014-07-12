<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div  ng-init="idCase = ${idCase}; idSource=${idSource};">
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertVerificationController" ng-cloak
         data-backdrop="static">
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-danger ">
                        <h4 class="element-center">&nbsp;&nbsp;Dato proporcionado por la fuente</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="row" ng-init="generalComponent = true" ng-controller="innerVerificationController" id="divContentVerifId">
                        <div class="col-xs-10 col-xs-offset-1">
                            <form id="FormVerifUpsertId" name="FormVerifUpsertId" ng-submit="submit('#FormVerifUpsertId')"
                                  class="form-horizontal"
                                  role="form">
                                <div id="divElementVerif">
                                </div>
                                <br/>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <br/>
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
                    <input type="hidden" ng-init="urlToGoSave = '<c:url value="/reviewer/verification/saveFieldVerification.json"/>'" ng-model="urlToGoSave">
                    <span class="btn btn-danger btn-danger btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormVerifUpsertId',${idCase},${idSource});"> Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>



