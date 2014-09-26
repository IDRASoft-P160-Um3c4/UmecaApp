<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormAddressId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="addressFMController" ng-cloak>

        <div class="modal-dialog" style="width:800px" ng-init='fa=${addObj}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Domicilio</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormAddressId" name="FormHousemateId" class="form-horizontal" role="form">
                        <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
                        <br/>

                        <div class="row">
                            <div class="col-xs-8">
                                <label>Referencias del domicilio</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited" name="addressRef"
                                          ng-model="fa.addressRef"
                                          maxlength="980" data-val="true"
                                          data-val-required="Referencias del domicilio es un campo requerido">
                                </textarea>
        <span class="field-validation-valid" data-valmsg-for="addressRef"
              data-valmsg-replace="true"></span>
                                <br/>
                            </div>
                        </div>
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
                          ng-click="submitIdCaseParam('#FormAddressId', '<c:url value="/supervisor/framingMeeting/address/doAddressUpsert.json?idCase="/>',fa.idCase);">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

