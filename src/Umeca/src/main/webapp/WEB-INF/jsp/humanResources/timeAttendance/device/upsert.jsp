<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
  $(document).ready(function () {
    window.showModalFormDlg("#dlgUpModalId", "#FormAgreementId");
  });
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div>
  <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
    <div class="modal-dialog" style="width:500px" ng-controller="upsertDeviceController">
      <div class="modal-content">
        <div class="modal-header">
          <div class="alert alert-info ">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Dispositivo Biom&eacute;trico</h4>
          </div>
        </div>
        <div class="modal-body">
          <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
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
                       id="name" name="name" ng-model="name"
                       type="text"/>
                                <span class="field-validation-valid" data-valmsg-for="name"
                                      data-valmsg-replace="true"></span>
              </div>
            </div>
            <br/>

            <div class="row">
              <div class="col-xs-4 element-left">
                Apellido paterno:
              </div>
              <div class="col-xs-8">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres"
                       data-val-length-max="50" data-val-length-min="3"
                       data-val-required="El apellido paterno es un campo requerido"
                       id="lastNameP" name="lastNameP"
                       type="text" ng-model="lastNameP"/>
                                <span class="field-validation-valid" data-valmsg-for="lastNameP"
                                      data-valmsg-replace="true"></span>
              </div>
            </div>

            <br/>

            <div class="row">
              <div class="col-xs-4 element-left">
                Apellido materno:
              </div>
              <div class="col-xs-8">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres"
                       data-val-length-max="50" data-val-length-min="3"
                       data-val-required="El apellido marterno es un campo requerido"
                       id="lastNameM" name="lastNameM"
                       type="text" ng-model="lastNameM"/>
                                                                <span class="field-validation-valid"
                                                                      data-valmsg-for="lastNameM"
                                                                      data-valmsg-replace="true"></span>

              </div>
            </div>
            <br/>

            <div class="row">
              <div class="col-xs-4 element-left">
                Fecha de nacimiento:
              </div>
              <div class="col-xs-8">
                <div class="input-group">
                  <input class="form-control date-picker" readonly="readonly" type="text"
                         data-date-format="yyyy/mm/dd"
                         data-val="true"
                         data-val-required="La fecha de nacimiento es un campo requerido"
                         id="birthDate" name="birthDate" ng-model="birthDate"/>
											<span class="input-group-addon">
														<i class="icon-calendar bigger-110"></i>
											</span>
                </div>
                                <span class="field-validation-valid" data-valmsg-for="birthDate"
                                      data-valmsg-replace="true"></span>
              </div>
            </div>
            <br/>

            <div class="row">
              <div class="col-xs-5 element-left">
                G&eacute;nero:
              </div>
              <div class="col-xs-7">
                <div class="row" ng-init="gen=false">
                  <div class="col-xs-6">
                    <div class="radio">
                      <label>
                        <input class="ace" type="radio" ng-checked="gen==true" name="gender"
                               data-val-required="El g&eacute;nero es un campo requerido"
                               value="true"
                               ng-model="gen">
                        <span class="lbl">Femenino</span>
                      </label>
                    </div>
                  </div>
                  <div class="col-xs-6">
                    <div class="radio">
                      <label>
                        <input class="ace" type="radio" value="false" ng-model="gen"
                               ng-checked="gen==false"
                               name="gender">
                        <span class="lbl">Masculino</span>
                      </label>
                    </div>

                  </div>
                </div>
              </div>
            </div>
            <br/>

            <div class="row">
              <div class="col-xs-4 element-left">
                Distrito:
              </div>
              <div class="col-xs-8">
                <select class="form-control element-center"
                        ng-model="m.district"
                        ng-options="e.name for e in lstDistrict"
                        ng-init='lstDistrict = ${lstDistrict};'></select>
                <input type="hidden" name="districtId" value="{{m.district.id}}"/>
              </div>
            </div>
          </form>
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
                          ng-click="submitUpsertEmployee('#FormCatId','<c:url value="/humanResources/employees/doUpsertEmployee.json"/>');">
                          Guardar
                    </span>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  var date = new Date();
  date.setFullYear(date.getFullYear() - 18);
  $('.date-picker').datepicker({autoclose: true, endDate: date}).next().on(ace.click_event, function () {
    $(this).prev().focus();
  });
</script>