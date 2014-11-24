<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
    $("input").keypress(function (event) {
        if (event.which == 13) {
            event.preventDefault();
            $("#btnMakeRequest").trigger("click");
        }
    });
</script>
<style>
    input, textarea {
        max-width: none !important;
    }
</style>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon icon-envelope"></i>&nbsp;&nbsp;Responder solicitud
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
                          role="form">

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small header-color-dark">
                                        <h6 class="smaller">Detalles del caso</h6>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="row">
                                                <div class="col-xs-4 smaller lighter blue  text-right">
                                                    <input type="hidden" value="${idRequest}" name="idRequest">
                                                    Imputado:
                                                </div>
                                                <div class="col-xs-7">
                                                    ${caseInfo.personName}
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-4 smaller lighter blue  text-right">
                                                    Carpeta de investigaci&oacute;n:
                                                </div>
                                                <div class="col-xs-7">
                                                    ${caseInfo.folderId}
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-4 smaller lighter blue text-right">
                                                    Estatus:
                                                </div>
                                                <div class="col-xs-7">
                                                    ${statusCase}
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row" ng-init='sources = ${sources};'>
                            <div class="col-xs-10 col-xs-offset-1">
                                <h4 class="smaller lighter blue">
                                    <small>Tipo de solicitud:</small>
                                    &nbsp;${requestTypeDes}</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <h5 class="smaller lighter blue">
                                    <small> Realizada por:
                                    </small>
                                    &nbsp; ${user}</h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <h5 class="smaller lighter blue">
                                    <small> Raz&oacute;n:
                                    </small>
                                    &nbsp; ${reason}</h5>
                            </div>
                        </div>
                        <div class="row" ng-show="sources.length > 0">
                            <div class="col-xs-1 col-xs-offset-1">
                                <h5 class="smaller lighter blue">
                                    <small> Fuente(s):
                                    </small>
                                </h5>
                            </div>
                            <div class="col-xs-9">
                                <h5 class="smaller lighter blue">
                                    <ul>
                                        <li ng-repeat="s in sources">
                                            {{s.fullName}}. Edad: {{s.age}}. Relaci&oacute;n: {{s.relationship}} <br/>
                                        </li>
                                    </ul>
                                </h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <h5 class="smaller lighter blue">
                                    <small> Respuesta de la solitud:
                                    </small>
                                    &nbsp;&nbsp;
                                    <input type="radio" name="response" checked="true" value="ACCEPTED">&nbsp;Aceptar
                                    solicitud
                                    &nbsp; &nbsp; &nbsp; <input type="radio" name="response" value="REJECTED">&nbsp;Rechazar
                                    solicitud
                                </h5>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small header-color-blue2">
                                        <h6 class="smaller">Raz&oacute;n por la que autoriza o rechaza la solicitud</h6>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <input type="hidden" value="${requestType}" name="requestType">
                                            <input type="hidden" value="${caseInfo.caseId}" name="caseId">
                                            <textarea id="comment" name="reason" ng-model="comment"
                                                      class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small header-color-blue2">
                                        <h6 class="smaller">Ingrese su contrase&ntilde;a para confirmar</h6>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <input type="password" id="password" name="password" ng-model="password"
                                                   class="form-control" rows="5"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <br/>

                    <div class="row" ng-show="MsgError">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="alert alert-danger element-center" ng-bind-html="MsgError">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" id="btnMakeRequest"
                          ng-disabled="WaitFor==true || comment=='' || comment == undefined"
                          ng-click="submit('#FormCatId', '<c:url value="/managereval/authorizeRequest/doResponseRequest.json"/>');">
                          Enviar Solicitud
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
