<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
                        <div class="panel panel-info" style="margin-bottom: auto;">
                            <div class="panel-heading element-center">
                                <div class="panel-title">Generar informe</div>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-12 element-center">
                                        <p>&iquest;Est&aacute; seguro que desea terminar la verificaci&oacute;n y generar el documento de informe?</p>
                                        <br/>
                                        <p>Raz&oacute;n</p>
                                        <textarea rows="4" cols="50" name="meeting.declineReason" ng-model="m.reason">

                                        </textarea>
                                    </div>

                                </div>
                            </div>
                            <div class="panel-footer element-right">
                                <span class="btn btn-default btn-sm" ng-click="cancel()">
                                    Cancelar
                                </span>
                                <span class="btn btn-primary btn-sm" >
                                    Terminar
                                </span>
                            </div>
                        </div>

                    </form>
                    <br ng-show="MsgError" />
                    <div class="row" ng-show="MsgError">
                        <div class="col-xs-12">
                            <div class="alert alert-danger element-center"  ng-bind-html="MsgError">
                                <%--{{MsgError}}--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
