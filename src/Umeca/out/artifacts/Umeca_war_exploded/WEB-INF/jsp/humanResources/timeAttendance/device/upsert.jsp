<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  $(document).ready(function () {
    window.showModalFormDlg("#dlgUpModalId", "#FormAgreementId");
  });
</script>
<div>
  <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
    <div class="modal-dialog" style="width:500px" ng-controller="upsertDeviceController" ng-init='init(${device});'>
      <div class="modal-content">
        <div class="modal-header">
          <div class="alert alert-info ">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Dispositivo Biom&eacute;trico</h4>
          </div>
        </div>
        <div class="modal-body">
          <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
              <input type="hidden" name="id" ng-value="dv.id" />
              <input type="hidden" name="isObsolete" ng-value="dv.isObsolete" />
            <br/>
            <div class="row">
              <div class="col-xs-4 element-left">
                Nombre:
              </div>
              <div class="col-xs-8">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres"
                       data-val-required="El nombre es un campo requerido"
                       data-val-length-max="50" data-val-length-min="3"
                       id="name" name="name" ng-model="dv.name"
                       type="text"/>
                                <span class="field-validation-valid" data-valmsg-for="name"
                                      data-valmsg-replace="true"></span>
              </div>
            </div>
            <br/>

            <div class="row">
              <div class="col-xs-4 element-left">
                Direcci&oacute;n IP:
              </div>
              <div class="col-xs-8">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 8 y m&aacute;ximo 15 caracteres"
                       data-val-length-max="15" data-val-length-min="8"
                       data-val-required="La dirección IP es un campo requerido"
                       id="ip" name="ip"
                       type="text" ng-model="dv.ip"/>
                                <span class="field-validation-valid" data-valmsg-for="ip"
                                      data-valmsg-replace="true"></span>
              </div>
            </div>
    
            <br/>

            <div class="row">
              <div class="col-xs-4 element-left">
                Puerto:
              </div>
              <div class="col-xs-8">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 1 y m&aacute;ximo 50 caracteres"
                       data-val-length-max="50" data-val-length-min="3"
                       data-val-required="El puerto es un campo requerido"
                       id="port" name="port"
                       type="text" ng-model="dv.port"/>
                                                                <span class="field-validation-valid"
                                                                      data-valmsg-for="port"
                                                                      data-valmsg-replace="true"></span>

              </div>
            </div>
            <br/>

          <div class="row">
            <div class="col-xs-12">
              <div ng-show="MsgError" class="alert alert-danger element-center" ng-bind-html="MsgError">

              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitUpsertDevice('#FormCatId','<c:url value="/humanResources/device/doUpsertDevice.json"/>');">
                          Guardar
                    </span>
        </div>
      </div>
    </div>
  </div>
</div>