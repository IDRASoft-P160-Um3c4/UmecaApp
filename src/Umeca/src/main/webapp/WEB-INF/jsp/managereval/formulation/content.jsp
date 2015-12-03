<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

<input type="hidden" ng-update-hidden ng-model="m.id" name="id" id="id" ng-init='m.id = "${(m.id == null) ? 0 : m.id}"'>
<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4">
            Fecha de registro de formulaci&oacute;n:
        </div>

        <div class="col-xs-8 input-group">
                <input class="form-control date-picker" id="registrationFormulationDate" type="text"
                   data-date-format="yyyy/mm/dd" value=""
                   data-val="true" data-val-required="La fecha de registro de formulaci&oacute;n es un campo requerido"
                   ng-model = "m.registrationFormulationDate"
                   name="registrationFormulationDate"
                   ng-init='m.registrationFormulationDate = "${(m.registrationFormulationDate == null) ? '' : m.registrationFormulationDate}"'/>

            <span class="input-group-addon col-xs-offset-1"><i class="icon-calendar bigger-110"></i></span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="registrationFormulationDate" data-valmsg-replace="true"></span>
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
                   type="text" value="" ng-model="m.document"
                   ng-init='m.document = "${(m.document == null) ? '' : m.document}"'
                   id="document"
                   name="document">
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="document" data-valmsg-replace="true"></span>
    </div>
</div>

<br/>



<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4 element-left">
            C&eacute;dula de notificaci&oacute;n:
        </div>
        <div class="col-xs-8">
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 2 y m&aacute;ximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="2"
                   data-val-required="El oficio es un campo requerido"
                   type="text" value="" ng-model="m.certificateNotification"
                   ng-init='m.certificateNotification = "${(m.certificateNotification == null) ? '' : m.certificateNotification}"'
                   id="certificateNotification"
                   name="certificateNotification">
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="certificateNotification" data-valmsg-replace="true"></span>
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
                   type="text" value="" ng-model="m.firstName"
                   ng-init='m.firstName = "${(m.firstName == null) ? '' : m.firstName}"' id="firstName"
                   name="firstName">
        </div>

    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="firstName" data-valmsg-replace="true"></span>
    </div>
</div>


<br/>
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
                   type="text" value="" ng-model="m.lastNameP"
                   ng-init='m.lastNameP = "${(m.lastNameP == null) ? '' : m.lastNameP}"' id="lastNameP"
                   name="lastNameP">
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
                   type="text" value="" ng-model="m.lastNameM"
                   ng-init='m.lastNameM = "${(m.lastNameM == null) ? '' : m.lastNameM}"' id="lastNameM"
                   name="lastNameM">
        </div>
    </div>

</div>

<div class="row">
    <div class="col-xs-6 element-right">
        <span class="field-validation-valid" data-valmsg-for="lastNameP" data-valmsg-replace="true"></span>

    </div>

    <div class="col-xs-6 element-right">
        <span class="field-validation-valid" data-valmsg-for="lastNameM" data-valmsg-replace="true"></span>
    </div>


</div>
<br/>


<input type="hidden" ng-update-hidden ng-model="m.reviewerId" name="reviewerId" id="reviewerId"
       ng-init="m.reviewerId = ${(reviewerId == null) ? 0 : reviewerId};">
<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-4 element-left">
            Evaluador que realizar&aacute; la entrevista:
        </div>
        <div class="col-xs-8">
            <select class="form-control element-center" id="reviewer"
                    ng-model="m.reviewer"
                    ng-init = 'lstReviewers = ${lstReviewers}'
                    ng-options="(e.name + ' (' + e.description + ')') for e in lstReviewers"
                    ng-change="m.reviewerId = m.reviewer.id"
                    ng-disabled="isReadOnly">
            </select>
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
                       name="umecaInterviewDate"
                       ng-model="m.umecaInterviewDate"
                       ng-init='m.umecaInterviewDate = "${(m.umecaInterviewDate == null) ? '' : m.umecaInterviewDate}"'/>

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
                <input class="form-control date-picker" id="hearingDate" type="text"
                       data-date-format="yyyy/mm/dd" value=""
                       data-val="true" data-val-required="La fecha de entrevista audiencia es un campo requerido"
                       name="hearingDate"
                       ng-model="m.hearingDate"
                       ng-init='m.hearingDate = "${(m.hearingDate == null) ? '' : m.hearingDate}"'/>
                <span class="input-group-addon col-xs-offset-1"><i class="icon-calendar bigger-110"></i></span>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="hearingDate" data-valmsg-replace="true"></span>
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
                <textarea rows="4" cols="50"
                          name = "comments"
                          data-val="true"
                          data-val-length="Debe tener m&aacute;ximo 300 caracteres"
                          data-val-length-max="300"
                          ng-model="m.comments" data-val-length-max="300"
                          ng-init='m.comments = "${(m.comments == null) ? '' : m.comments}"' ></textarea>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-offset-4 element-center">
        <span class="field-validation-valid" data-valmsg-for="comments" data-valmsg-replace="true"></span>
    </div>
</div>