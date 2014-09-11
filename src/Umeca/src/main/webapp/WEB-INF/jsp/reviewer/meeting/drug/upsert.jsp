<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="drugController" ng-cloak>
        <div class="modal-dialog" style="width:700px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-warning-sign "></i>&nbsp;&nbsp;Consumo de sustancias</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
                       <br/>
                        <div class="row">
                            <div class="col-xs-12 element-center" ng-init="blockD=${d.block == null ? true: d.block};">
                                <div class="col-xs-6 element-right">
                                    ¿El imputado consume sustancias actualmente?
                                </div>
                                <div class="col-xs-2">
                                    <input type="radio"  name="block"
                                           id="blockYes" ng-value="true" ng-model="blockD" ng-change="fillModel()">
                                    <label for="blockYes">Si</label>   &nbsp;&nbsp;&nbsp;
                                    <input type="radio"  name="block"
                                           id="blockNo" ng-value="false" ng-model="blockD" ng-change="fillModel()">
                                    <label for="blockNo">No</label>
                                </div>
                            </div>
                        </div>
                        <br />
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/drug/content.jsp"%>
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
                          ng-click="submit('#FormCatId', '<c:url value="/reviewer/meeting/drug/doUpsert.json?idCase=${idCase}"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>



