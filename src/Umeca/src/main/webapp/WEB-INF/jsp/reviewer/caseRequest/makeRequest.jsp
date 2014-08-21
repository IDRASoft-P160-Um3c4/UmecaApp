<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
    $("input").keypress(function(event) {
        if (event.which == 13) {
            event.preventDefault();
            $( "#btnMakeRequest" ).trigger( "click" );
        }
    });
</script>
<style>
    input,textarea {
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
                        <div class="row">
                                       <div class="col-xs-10 col-xs-offset-1">
                                           <h4 class="header smaller lighter blue"><small>Tipo de solicitud:  </small>
                                           &nbsp;${requestTypeDes}</h4>
                                       </div>
                        </div>
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
                                                    ${caseInfo.status}
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div ng-init='requestType = "${requestType}"; listSources = ${sources};'
                                     ng-show=" requestType == 'CHANGE_STATUS_SOURCE' ">

                                    <div class="widget-box" ng-show="listSources.length > 0">
                                        <div class="widget-header widget-header-small header-color-blue2">
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
                                                    <input class="" type="checkbox" ng-value="source.id"
                                                           name="sourcesId">
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
                        <div class="row" ng-show="requestType != 'CHANGE_STATUS_SOURCE' || (requestType == 'CHANGE_STATUS_SOURCE' && listSources.length > 0)">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small header-color-blue2">
                                        <h6 class="smaller">Raz&oacute;n por la que realiza la solicitud</h6>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <input type="hidden" value="${requestType}" name="requestType">
                                            <input type="hidden" value="${caseInfo.caseId}" name="caseId">
                                            <textarea id="comment" name="reason" ng-model="comment"
                                                      class="form-control" ></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row"  ng-show="requestType != 'CHANGE_STATUS_SOURCE' || (requestType == 'CHANGE_STATUS_SOURCE' && listSources.length > 0)">
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
                            <div class="alert alert-danger element-center" ng-bind-html="MsgError" >
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm"   id="btnMakeRequest"
                          ng-disabled="WaitFor==true || comment=='' || comment == undefined || (listSources.length == 0 && requestType == 'CHANGE_STATUS_SOURCE')"
                          ng-click="submit('#FormCatId', '<c:url value="/reviewer/caseRequest/doMakeRequest.json"/>');">
                          Enviar Solicitud
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
