<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormDrugId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="drugsFMController" ng-cloak>
        <div class="modal-dialog" style="width:700px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-warning-sign "></i>&nbsp;&nbsp;Consumo
                            de sustancias</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormDrugId" name="FormDrugId" class="form-horizontal" role="form" ng-init="block=true">
                        <div class="row">
                            <div class="col-xs-12 element-center"
                                 ng-init="blockD=${d.block == null ? true: d.block}; d.consumeDrug=1;">
                                <div class="col-xs-6 element-right">
                                    &iquest;El imputado consume sustancias actualmente?
                                </div>
                                <div class="col-xs-2">
                                    <input type="radio" value="1" ng-model="d.consumeDrug" ng-click="fillDrug(1)">
                                    <label>Si</label> &nbsp;&nbsp;&nbsp;
                                    <input type="radio" value="2" ng-model="d.consumeDrug" ng-click="fillDrug(2)">
                                    <label>No</label>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/drugs/content.jsp" %>
                    </form>
                    <br/>

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
                          ng-click="submitIdCaseParam('#FormDrugId', '<c:url value="/supervisor/framingMeeting/drugs/doUpsert.json?idCase="/>',${idCase});">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>



