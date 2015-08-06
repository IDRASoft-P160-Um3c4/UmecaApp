<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

<div clas="row">
  <div class="col-xs-12">
    <div class = "col-xs-2 element-left">
      Fecha de registro de formulaci&oacute;n
    </div>
    <div class="col-xs-10">
      <div class="input-group">
        <input class="form-control date-picker" id="jdpStart" type="text"
               data-date-format="yyyy/mm/dd" value="${j.startPrev}"
               data-val="true" data-val-required="La fecha de inicio es un campo requerido"
               name="startPrev"/>
        <span class="input-group-addon"><i class="icon-calendar bigger-110"></i></span>
      </div>
    </div>
</div>

<div class="row">
  <div clas="col-xs-12">
    <div class = "col-xs-2 element-left">
      Oficio:
    </div>
    <div class="col-xs-10">
      <input class="form-control" data-val="true"
             data-val-length="Debe tener al menos 3 y m&aacute;ximo 150 caracteres"
             data-val-length-max="150" data-val-length-min="3"
             data-val-required="La empresa es un campo requerido" ng-readonly="!blockJ"
             type="text" value="" ng-model="j.company"
             ng-init='j.company = "${(j.company == null) ? '' : j.company}"' id="company" name="company">
    </div>
  </div>


  <div class="row">
    <div class="col-xs-12 element-left">
      <div class = "col-xs-3">
        <div class="col-xs-6">
          Nombre del imputado:
        </div>
        <div class="col-xs-6">
          <input type="text">
        </div>

      </div>

      <div class = "col-xs-3">
        <div class="col-xs-6">
          Apellido paterno:
        </div>

        <div class="col-xs-6">
          <input type="text">
        </div>
      </div>

      <div class = "col-xs-3">
        <div class="col-xs-6">
          Apellido materno
        </div>
        <div class="col-xs-6">
          <input type="text">
        </div>
      </div>
    </div>
  </div>

  <div class ="row">
    <div class="col-xs-12">
      <div clas="col-xs-6">
        Evaluador que realizar&aacute; la entrevista
      </div>

      <div class="coll-xs-6">

        <select></select>
      </div>
    </div>

  </div>

  <div class="row">
    <div class="col-xs-12">
      <div class="col-xs-6">Fecha de entrevista UMECA</div>
    </div>
  </div>

  <div class="row">
    <div class="col-xs-12">
      <div class="col-xs-6">Fecha de audiencia  </div>

    </div>
  </div>



  <div class="col-xs-12">
    <div class="col-xs-6">Observaciones  </div>

  </div>
</div>

</div>
