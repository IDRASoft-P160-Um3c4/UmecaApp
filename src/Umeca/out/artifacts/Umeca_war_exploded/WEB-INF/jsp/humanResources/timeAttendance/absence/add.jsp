<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  $(document).ready(function () {
    window.showModalFormDlg("#dlgUpModalId", "#FormAgreementId");
  });
</script>
<div>
  <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
    <div class="modal-dialog" style="width:500px" ng-controller="upsertAbsenceController" ng-init='initAdd(${Employees});'>
      <div class="modal-content">
        <div class="modal-header">
          <div class="alert alert-info ">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Registrar falta</h4>
          </div>
        </div>
        <div class="modal-body">
          <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">

            <br/>
            <div class="row">
              <div class="col-xs-4 element-left">
                Nombre:<br/>{{dv.IdEmployee}}
              </div>
              <div class="col-xs-8">
                <select class="form-control" name="IdEmployee"
                       ng-model="dv.IdEmployee" ng-options="employee.id as employee.name for employee in employees"
                       >
                  </select>
              </div>
            </div>
            <br/>
            <div class="row">
              <div class="col-xs-4 element-left">
                Fecha de registro:
                <br/><small>(A&ntilde;o/Mes/D&iacute;a) Ej. (1980/08/06)</small>
              </div>
              <div class="col-xs-8">
                <div class="input-group">
                <input class="form-control date-picker"
                       id="date"
                       name="date" type="text"
                       data-date-format="yyyy/mm/dd"
                       ng-model="dv.date" data-val="true"
                       data-val-required="Fecha de la falta"/>
                    <span class="input-group-addon">
                    <i class="icon-calendar bigger-110"></i>
                    </span></div>
                <span class="field-validation-valid" data-valmsg-for="date"
                      data-valmsg-replace="true"></span>
              </div>
            </div>

            <br/>
            <div class="row">
              <div class="col-xs-4 element-left">
                Observaciones:
              </div>
              <div class="col-xs-8">
                <textarea class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 8 y m&aacute;ximo 200 caracteres"
                       data-val-length-max="200" data-val-length-min="8"
                       data-val-required="Es requerido un comentario para la justificación del retardo."
                       name="comment"
                       ng-model="dv.comment"/>
                                <span class="field-validation-valid" data-valmsg-for="comment"
                                      data-valmsg-replace="true"></span>
              </div>
            </div>
            <br/>
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
                          ng-click="submitAbsence('#FormCatId','<c:url value="/humanResources/absence/doAddAbsence.json"/>');">
                          Aceptar
                    </span>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  var date = new Date();
  $('.date-picker').datepicker({autoclose: true, endDate: date}).next().on(ace.click_event, function () {
    $(this).prev().focus();
  });
</script>