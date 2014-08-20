<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>
<style>
    textarea {
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
                        <h4 class="element-center"><i class="icon icon-envelope"></i>&nbsp;&nbsp;Realizar solicitud</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
                          role="form">
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="col-xs-3 text-right"><b>Solicitud:</b></div>
                                <div class="col-xs-9">${requestTypeDes}</div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="col-xs-3 text-right"><b>No. de Carpeta:</b></div>
                                <div class="col-xs-9">${caseInfo.folderId}</div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="col-xs-3 text-right"><b>Imputado:</b></div>
                                <div class="col-xs-9">${caseInfo.personName}</div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="col-xs-3 text-right"><b>Esatus:</b></div>
                                <div class="col-xs-9">${caseInfo.status}</div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div ng-init='requestType = "${requestType}"; listSources = ${sources};'
                                     ng-show="requestType == 'CHANGE_STATUS_SOURCE'">

                                    <div class="widget-box" ng-show="listSources.length > 0">
                                        <div class="widget-header widget-header-small header-color-blue3">
                                            <h6 class="smaller">Elige las fuentes que solicitas cambiar</h6>
                                        </div>
                                        <table class=" widget-body table table-striped table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th>
                                                </th>
                                                <th>
                                                    Nombre
                                                </th>
                                                <th>
                                                    Parentesco
                                                </th>
                                                <th>
                                                    Edad
                                                </th>
                                                <th>
                                                    Tel&eacute;fono
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr ng-repeat="source in listSources">
                                                <td style="width: 20px;">
                                                    <input class="" type="checkbox" ng-value="source.id" name="sourcesId" >
                                                </td>
                                                <td>
                                                    {{source.fullName}}
                                                </td>
                                                <td>
                                                    {{source.relationship}}
                                                </td>
                                                <td>
                                                    {{source.age}}
                                                </td>
                                                <td>
                                                    {{source.phone}}
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div ng-show="listSources.length == 0">
                                        <span class="label label-lg label-info arrowed-right"> No tienes fuentes con entrevista terminada para realizar esta solicitud.</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="widget-header widget-header-small">
                                    <h6>Raz&oacute;n</h6>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main padding-10">
                                        <input type="hidden" value="${requestType}" name="requestType">
                                        <input type="hidden" value="${caseInfo.caseId}" name="caseId">
                                        <textarea id="comment" name="reason" ng-model="comment"
                                                  class="form-control" rows="5"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <br/>
                    <div class="row" ng-show="MsgError">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="alert alert-danger element-center">
                                {{MsgError}}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm"
                          ng-disabled="WaitFor==true || comment=='' || comment == undefined || listSources.length == 0"
                          ng-click="submit('#FormCatId', '<c:url value="/reviewer/caseRequest/doMakeRequest.json"/>');">
                          Enviar Solicitud
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
