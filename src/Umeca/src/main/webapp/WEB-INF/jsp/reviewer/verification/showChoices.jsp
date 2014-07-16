<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormChoices");
    });
</script>

    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak data-backdrop="static">
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
               <!-- <div class="modal-header">
                    <div class="alert alert-danger ">
                        <h4 class="element-center">&nbsp;&nbsp;Informaci�n recolectada</h4>
                    </div>
                </div>    -->
                <div class="modal-body">
                    <form id="FormChoices" name="FormChoices" ng-submit="submit('#FormChoices')" class="form-horizontal"
                          role="form">
                        <div class="row">
                            <div class="col-xs-12 widget-container-span">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small header-color-blue3" ng-init='codeField = ${code}; idList = ${idList};'>
                                        <input type="hidden" name="code" value="${code}"/>
                                        <input type="hidden" name="idList" value="${idList}"/>
                                        <h6 class="smaller">Elije la informaci�n final para el campo o secci�n</h6>
                                    </div>
                                            <table class=" widget-body table table-striped table-bordered table-hover" ng-init='listChoice = ${listChoice};'>
                                                <tbody>
                                                    <tr ng-repeat ="opc in listChoice">
                                                        <td style="width: 20px;"><input class="" type="radio"  ng-model="Model.rdoField" ng-value="opc.id"
                                                                     name="idFieldMeeting" ng-disabled="opc.status=='NOT_FOUND'||opce.status=='DONT_KNOW'" ng-checked="opc.isFinal">
                                                        </td>
                                                        <td style="width: 15px;">
                                                            <i class="icon-ok green  icon-only bigger-120" ng-show="opc.status == 'EQUALS' "></i>
                                                            <i class="icon-eye-close default icon-only bigger-120" ng-show="opc.status == 'NOT_FOUND' "></i>
                                                            <i class="icon-remove red  icon-only bigger-120" ng-show="opc.status == 'NO_EQUALS' "></i>
                                                            <i class="icon-ban-circle grey  icon-only bigger-120" ng-show="opc.status == 'DONT_KNOW' "></i>
                                                            <i class="purple glyphicon glyphicon-user bigger-120" ng-show="opc.status == 'IS_IMPUTED' "></i>
                                                            <i class="blue icon-question-sign  icon-only bigger-120" ng-show="opc.status == 'UNABLE_VERIFICATION' "></i>
                                                        </td>
                                                        <td>
                                                         <b> {{opc.nameSource}}</b>
                                                        </td>
                                                        <td>
                                                            <div ng-repeat="v in opc.values">
                                                                <h6><small>{{v}}</small></h6>
                                                            </div>

                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-xs-3 element-left">
                                        Raz�n por la que se elije este campo o secci�n:
                                    </div>
                                    <div class="col-xs-9">
                                        <textarea class="form-control" name="reason"  data-val="true"
                                         data-val-required="La raz�n por la que se elije el campo o secci�n es un campo requerido"></textarea>
                                         <span class="field-validation-valid" data-valmsg-for="reason" data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                            </div>
                        </div>

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
                    <span class="btn btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormChoices','<c:url value="/reviewer/verification/saveSelectChoice.json?idCase=${idCase}"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>


