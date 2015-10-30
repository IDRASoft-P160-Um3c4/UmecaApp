<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  $(document).ready(function () {
    window.showModalFormDlg("#dlgUpModalId", "#FormAgreementId");
  });
</script>
<div>
  <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
    <div class="modal-dialog" style="width:500px" ng-controller="upsertDeviceController" ng-init='init(${AttendanceLog});'>
      <div class="modal-content">
        <div class="modal-header">
          <div class="alert alert-info ">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Justificar retardo</h4>
          </div>
        </div>
        <div class="modal-body">
          <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
            <input type="hidden" name="id" ng-value="dv.id" />
            <br/>
            <div class="row">
              <div class="col-xs-4 element-left">
                Nombre:
              </div>
              <div class="col-xs-8">
                <input class="form-control"
                       ng-model="dv.name"
                       type="text" readonly="readonly"/>
              </div>
            </div>
            <br/>
            <div class="row">
              <div class="col-xs-4 element-left">
                  Fecha de registro:
              </div>
              <div class="col-xs-8">
                  <input class="form-control" name="date"
                         type="text" ng-model="dv.date" readonly="readonly"/>
              </div>
          </div>
            <br/>
            <div class="row">
                  <div class="col-xs-4 element-left">
                      Hora de registro:
                  </div>
                  <div class="col-xs-8">
                      <input class="form-control" name="time"
                             type="text" ng-model="dv.time" readonly="readonly"/>
                  </div>
              </div>
            <br/>
            <div class="row">
              <div class="col-xs-4 element-left">
                  &iquest;Desea justificar el retardo?:
              </div>
              <div class="col-xs-8">
                  <label class="radio-inline">
                    <input type="radio" name="justified" ng-model="dv.justified" ng-value="true"/>S&iacute;
                  </label>
                  <label class="radio-inline">
                    <input type="radio" name="justified" ng-model="dv.justified" ng-value="false"/>No
                  </label>
              </div>
            </div>
            <br/>
            <div class="row">
                  <div class="col-xs-4 element-left">
                      Contrase&ntilde;a:
                  </div>
                  <div class="col-xs-8">
                      <input class="form-control" data-val="true"
                             data-val-length="Debe tener al menos 8 y m&aacute;ximo 15 caracteres"
                             data-val-length-max="15" data-val-length-min="8"
                             data-val-required="Para continaur con el proceso, es necesario introduzca su contraseña"
                             type="password" ng-model="dv.password" name="password"/>
                                <span class="field-validation-valid" data-valmsg-for="ip"
                                      data-valmsg-replace="true"></span>
                  </div>
              </div>
            <br/>
            <div class="row">
                  <div class="col-xs-4 element-left">
                      Observaciones:
                  </div>
                  <div class="col-xs-8">
                      <input class="form-control" data-val="true"
                             data-val-length="Debe tener al menos 8 y m&aacute;ximo 200 caracteres"
                             data-val-length-max="200" data-val-length-min="8"
                             data-val-required="Es requerido un comentario para la justificación del retardo."
                             name="comment"
                             type="text" ng-model="dv.comment"/>
                                <span class="field-validation-valid" data-valmsg-for="comment"
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
                          ng-click="submitUpsertAssistence('#FormCatId','<c:url value="/humanResources/assistence/doUpsertJustify.json"/>');">
                          Aceptar
                    </span>
        </div>
      </div>
    </div>
  </div>
</div>