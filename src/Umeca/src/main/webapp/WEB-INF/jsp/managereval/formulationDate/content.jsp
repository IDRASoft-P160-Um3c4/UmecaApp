<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4">
            Fecha de registro de formulaci&oacute;n:
        </div>

        <div class="col-xs-8 input-group">
            <input class="form-control date-picker" id="timestamp" type="text"
                   data-date-format="yyyy/mm/dd" value=""
                   data-val="true" data-val-required="La fecha de registro de formulaci&oacute;n es un campo requerido"
                   name="timestamp"/>
            <span class="input-group-addon col-xs-offset-1"><i class="icon-calendar bigger-110"></i></span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="timestamp" data-valmsg-replace="true"></span>
    </div>
</div>

<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4 element-left">
            Oficio:
        </div>
        <div class="col-xs-8">
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 3 y m&aacute;ximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="3"
                   data-val-required="El oficio es un campo requerido"
                   type="text" value="" ng-model="j.documentNumber"
                   ng-init='j.documentNumber = "${(j.documentNumber == null) ? '' : j.documentNumber}"'
                   id="documentNumber"
                   name="documentNumber">
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="documentNumber" data-valmsg-replace="true"></span>
    </div>
</div>

<br/>


<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4 element-left">
            Nombre del imputado:
        </div>
        <div class="col-xs-8">
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y m&aacute;ximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="1"
                   data-val-required="El nombre del imputado es un campo requerido"
                   type="text" value="" ng-model="j.FirstName"
                   ng-init='j.FirstName = "${(j.FirstName == null) ? '' : j.FirstName}"' id="FirstName"
                   name="FirstName">
        </div>

    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="FirstName" data-valmsg-replace="true"></span>
    </div>
</div>


<br/>

<div class="row">
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            Apellido paterno:
        </div>

        <div class="col-xs-8">
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y m&aacute;ximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="1"
                   data-val-required="El apellido paterno es un campo requerido"
                   type="text" value="" ng-model="j.lastName"
                   ng-init='j.lastName = "${(j.company == null) ? '' : j.company}"' id="lastName"
                   name="lastName">
        </div>
    </div>

    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            Apellido materno:
        </div>

        <div class="col-xs-8">
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 1 y m&aacute;ximo 150 caracteres"
                   data-val-required="El apellido materno es un campo requerido"
                   data-val-length-max="150" data-val-length-min="1"
                   type="text" value="" ng-model="j.thirdname"
                   ng-init='j.thirdname = "${(j.thirdname == null) ? '' : j.company}"' id="thirdname"
                   name="thirdname">
        </div>
    </div>

</div>

<div class="row">
    <div class="col-xs-6 element-right">
        <span class="field-validation-valid" data-valmsg-for="lastName" data-valmsg-replace="true"></span>

    </div>

    <div class="col-xs-6 element-right">
        <span class="field-validation-valid" data-valmsg-for="thirdname" data-valmsg-replace="true"></span>
    </div>


</div>


<br/>


<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4 element-left">
            Evaluador que realizar&aacute; la entrevista:
        </div>

        <div class="col-xs-8">
            <select class="form-control element-center" ng-model="j.evaluator"
                    ng-options="e.name for e in lstRegisterType" ng-init='lstRegisterType = ${lstRegisterType};'
                    ng-change="j.registerTypeId = j.registerType.id;"></select>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="evaluator" data-valmsg-replace="true"></span>
    </div>
</div>

<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4 element-left">
            Fecha de entrevista UMECA:
        </div>
        <div class="col-xs-8">
            <div class="input-group">
                <input class="form-control date-picker" id="umecaInterviewDate" type="text"
                       data-date-format="yyyy/mm/dd" value=""
                       data-val="true" data-val-required="La fecha de entrevista UMECA es un campo requerido"
                       name="umecaInterviewDate"/>
                <span class="input-group-addon col-xs-offset-1"><i class="icon-calendar bigger-110"></i></span>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="umecaInterviewDate" data-valmsg-replace="true"></span>
    </div>
</div>

<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4 element-left">
            Fecha de audiencia:
        </div>
        <div class="col-xs-8">
            <div class="input-group">
                <input class="form-control date-picker" id="hearingInterviewDate" type="text"
                       data-date-format="yyyy/mm/dd" value=""
                       data-val="true" data-val-required="La fecha de entrevista audiencia es un campo requerido"
                       name="hearingInterviewDate"/>
                <span class="input-group-addon col-xs-offset-1"><i class="icon-calendar bigger-110"></i></span>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="hearingInterviewDate" data-valmsg-replace="true"></span>
    </div>
</div>

<br/>


<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4 element-left">
            Observaciones:
        </div>
        <div class="col-xs-8">
            <div class="input-group">
                <textarea></textarea>
            </div>
        </div>
    </div>
</div>
