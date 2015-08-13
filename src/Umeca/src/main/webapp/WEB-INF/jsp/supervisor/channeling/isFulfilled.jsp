<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormUpId");
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:700px">
            <div class="modal-content"  ng-controller="channelingController">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h5 class="element-center"><i class="glyphicon glyphicon-saved"></i>&nbsp;&nbsp;Resultado de la imposici&oacute;n</h5>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormUpId" name="FormUpId" class="form-horizontal"
                          role="form">
                        <br/>
                        <input type="hidden" value="${channelingId}" name="channelingId" id="channelingId">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h6><i class="glyphicon glyphicon-play-circle"></i>&nbsp;&nbsp;Debe elegir el resultado de la imposici&oacute;n</h6>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row" ng-init="m.isFulfilled = ${isFulfilled};">
                                            <div class="col-xs-10 col-xs-offset-1">
                                                <input type="radio" name="isFulfilled" ng-model="m.isFulfilled" ng-value="true">&nbsp;&nbsp;&nbsp;SI cumpli&oacute; el imputado con la imposici&oacute;n
                                            </div>
                                            <div class="col-xs-10 col-xs-offset-1">
                                                <input type="radio" name="isFulfilled" ng-model="m.isFulfilled" ng-value="false">&nbsp;&nbsp;&nbsp;NO cumpli&oacute; el imputado con la imposici&oacute;n
                                            </div>
                                        </div>
                                        <br/>
                                        <div class="row" ng-show="MsgError">
                                            <br/>
                                            <div class="col-xs-12">
                                                <div class="alert alert-danger element-center"  ng-bind-html="MsgError">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </button>
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                            ng-click="submit('#FormUpId', '<c:url value='/supervisor/channeling/doIsFulfilled.json' />', undefined, FormUpId.$valid)">
                        Aceptar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

