<div id="divPersonalController" ng-controller="personalDataController">
<div class="row">
    <div class="col-xs-6">
        <div class="row">
            <div class="col-xs-3 element-left">
                <br/> <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                         ng-click="doConfirmVerifEqual('imputed.gender')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2"
                   ng-show="verification" code="imputed.gender"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('imputed.gender')"></i>
                <i class="purple icon-list icon-only bigger-120" onclick="window.showChoices('imputed.gender')"></i>
                G�nero:
            </div>
            <div class="col-xs-9">
                <div class="row" ng-init="gen=${(m.imputed.gender == null) ? false: m.imputed.gender}">
                    <div class="col-xs-6">
                        <div class="radio">
                            <label>
                                <input class="ace" type="radio" ng-checked="gen==true" name="imputed.gender"
                                       data-val-required="El g?nero es un campo requerido" id="genero" value="true"
                                       ng-model="gen">
                                <span class="lbl">Femenino</span>
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="radio">
                            <label>
                                <input class="ace" type="radio" value="false" ng-model="gen" ng-checked="gen==false"
                                                                      name="imputed.gender">
                                <span class="lbl">Masculino</span>
                            </label>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <br/>

        <div class="row" ng-show="verification==false">
            <div class="col-xs-5 element-left">
                Fecha de nacimiento:
            </div>
            <div class="col-xs-7">
                <input class="form-control" id="birthDate" readonly="readonly" value="${m.imputed.birthDate}" disabled="disabled">
            </div>
        </div>
        <div class="row" ng-show="verification == true">
            <div class="col-xs-5 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('imputed.birthDate')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2"
                   ng-show="verification" code="imputed.birthDate"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('imputed.birthDate')"></i>
                <i class="purple icon-list icon-only bigger-120" onclick="window.showChoices('imputed.birthDate')"
                        ng-show="selectSource"></i>
                Fecha de nacimiento:
            </div>
            <div class="col-xs-7">
                <div class="input-group">
                    <input class="form-control date-picker" id="dateBirth"  type="text"  disabled="disabled"
                           data-date-format="yyyy/mm/dd" value="${m.imputed.birthDate}" readonly="readonly"
                           name="imputed.birthDate" data-val-required="La fecha de nacimiento es un campo requerido"/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                </div>
                 <span class="field-validation-valid" data-valmsg-for="imputed.yearsMaritalStatus"
                       data-valmsg-replace="true"></span>
            </div>
        </div>
        <br/>

        <div class="row">
            <div class="col-xs-5 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('imputed.celPhone')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2"
                   ng-show="verification"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('imputed.celPhone')"></i>
                <i class="purple icon-list icon-only bigger-120" ng-click="showChoices('imputed.celPhone')"
                   ng-show="selectSource"></i>
                Celular:
            </div>
            <div class="col-xs-7">
                <input class="form-control" data-val-required="El celular es un campo requerido"
                       data-val-length="Debe tener m?nimo 8 y m?ximo 20 caracteres"
                       data-val-length-max="20" data-val-length-min="8"
                       type="text" ng-model="celPhone" id="celPhone"
                       ng-init="celPhone= '${(m.imputed.celPhone == null) ? '': m.imputed.celPhone}'"
                       name="imputed.celPhone">
                    <span class="field-validation-valid" data-valmsg-for="imputed.celPhone"
                          data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="widget-box">
            <div class="widget-header"
                 ng-init="maritalStatus = ${m.imputed.maritalStatus.id == null ? 1: m.imputed.maritalStatus.id}">
                <div class="col-xs-2">
                    <i class="icon-ok-circle green  icon-only bigger-120"  ng-show="verification"
                       ng-click="doConfirmVerifEqual('imputed.maritalStatus')"></i>
                    <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4" ng-show="verification"
                       code="imputed.maritalStatus"></i>
                    <i class="icon-ban-circle inverse icon-only bigger-1 20"  ng-show="verification"
                       ng-click="doConfirmVerifNotKnow('imputed.maritalStatus')"></i>
                    <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" onclick="window.showChoices('imputed.maritalStatus.id')"></i>
                </div>

                <div class="col-xs-9 element-left">
                    <h5>Estado civil</h5>
                </div>

            </div>
            <div class="widget-body">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="control-group">
                            <div class="radio element-left">
                                <label>
                                    <input name="imputed.maritalStatus.id" type="radio" class="ace" value="1"
                                           ng-model="maritalStatus"/>
                                    <span class="lbl">Soltero</span>
                                </label>
                            </div>

                            <div class="radio element-left">
                                <label>
                                    <input name="imputed.maritalStatus.id" type="radio" class="ace"
                                           ng-model="maritalStatus"
                                           value="2"/>
                                    <span class="lbl">Casado</span>

                                </label>
                            </div>
                        </div>

                        <div class="radio element-left">
                            <label>
                                <input name="imputed.maritalStatus.id" type="radio" class="ace" value="3"
                                       ng-model="maritalStatus"/>
                                <span class="lbl">Divorciado</span>
                            </label>
                        </div>

                        <div class="radio element-left">
                            <label>
                                <input name="imputed.maritalStatus.id" type="radio" class="ace" ng-model="maritalStatus"
                                       value="4"/>
                                <span class="lbl">Uni�n libre</span>
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-5" ng-show="maritalStatus ==2 || maritalStatus == 4" ng-model="verifElem">
                        <div class="space-10"></div>
                        <div class="widget-main">
                            <input type="text" class="input-mini" id="spinnder1"
                                   value="${m.imputed.yearsMaritalStatus ==null ? '': m.imputed.yearsMaritalStatus}"
                                   name="imputed.yearsMaritalStatus" id="imputed.yearsMaritalStatus" data-val="true"
                                   data-val-regex-pattern="([0-9]+)"
                                   data-val-length="Debe tener m�nimo 1 y m�ximo 3 caracteres"
                                   data-val-length-max="3" data-val-length-min="1"
                                   data-val-required="El n�mero de a�os es un campo requerido"
                                   data-val-regex="La cantidad de a�os s�lo pueden ser n�meros"/> A�os
                            <br/>
                            <span class="field-validation-valid" data-valmsg-for="imputed.yearsMaritalStatus"
                                  data-valmsg-replace="true"></span>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>
</div>
<br/>

<div class="col-xs-12">
    <div class="widget-box">
        <div class="widget-header">
            <div class="col-xs-1">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifEqual('imputed.boys')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification"
                   code="imputed.boys"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                   ng-click="doConfirmVerifNotKnow('imputed.boys')"></i>
                <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" onclick="window.showChoices('imputed.boys')"></i>
            </div>

            <div class="col-xs-11 element-left">
                <h5>Hijos</h5>
            </div>
        </div>
        <div class="widget-body">
            <br/>

            <div class="row">
                <div class="col-xs-12">
                    <div class="col-xs-1 align-left">
                        Total:
                    </div>
                    <div class="col-xs-3">
                        <input type="text" class="form-control" name="imputed.boys" value="${m.imputed.boys}"
                               data-val-regex-pattern="([0-9]+)"
                               data-val-required="El total de hijos es un campo requerido"
                               data-val-regex="S�lo puede guardar n�meros" data-val="true" data-val="true"
                               id="imputed.boys"/>
                        <span class="field-validation-valid" data-valmsg-for="imputed.boys"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-3 align-right">
                        Dependientes econ�micos:
                    </div>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="imputed.dependentBoys"
                               data-val-regex-pattern="([0-9]+)"
                               data-val-required="El total de depentdientes econ�micos es un campo requerido"
                               data-val-regex="S?lo puede guardar n�meros" id="imputed.dependentBoys" data-val="true"
                               value="${m.imputed.dependentBoys == null ? '': m.imputed.dependentBoys}"/>
                        <span class="field-validation-valid" data-valmsg-for="imputed.dependentBoys"
                              data-valmsg-replace="true"></span>
                    </div>
                </div>
            </div>
            <br/>
        </div>
    </div>
</div>

<br/>

<div class="row">

</div>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header">
                <div class="col-xs-1" >
                    <i class="icon-ok-circle green  icon-only bigger-120"ng-show="verification"
                       ng-click="doConfirmVerifEqual('imputed.birtCountry')"></i>
                    <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4"ng-show="verification"
                       code="imputed.birthCountry"></i>
                    <i class="icon-ban-circle gray icon-only bigger-120"ng-show="verification"
                       ng-click="doConfirmVerifNotKnow('imputed.birthCountry')"></i>
                    <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('imputed.birthCountry.id')"></i>
                </div>
                <div class="col-xs-10 element-left">
                    <h4>Lugar de nacimiento</h4>
                </div>
            </div>

            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-2 element-left col-xs-offset-1">
                        Pais:
                    </div>
                    <div class="col-xs-3">
                        <input type="hidden" ng-update-hidden ng-model="m.countryId" name="imputed.birthCountry.id"
                               id="country"
                               ng-init='m.countryId = ${(m.imputed.birthCountry.id == null)? 'undefined':m.imputed.birthCountry.id}'>
                        <select class="form-control element-center" ng-model="m.country"
                                ng-options="e.name for e in listCountry"
                                url-request="/catalogs/getStatesByCountry.json"
                                ng-change="m.countryId = m.country.id;" ng-init='listCountry = ${lstCountry};'></select>
                    </div>
                    <div class="col-xs-2 element-right">
                        Estado:
                    </div>
                    <div class="col-xs-3">
                        <input class="form-control"
                               type="text" ng-model="state" id="imputed.birthState"
                               name="imputed.birthState" data-val-required="El estado es un campo requerido"
                               ng-init='state = "${(m.imputed.birthState ==  null) ? "" : m.imputed.birthState}";'>
                         <span class="field-validation-valid" data-valmsg-for="imputed.birthState"
                               data-valmsg-replace="true"></span>
                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-2 element-left col-xs-offset-1">
                        Municipio:
                    </div>
                    <div class="col-xs-3">
                        <input class="form-control"
                               type="text" ng-model="birthMunicipality" id="imputed.birthMunicipality"
                               name="imputed.birthMunicipality" data-val-required="El municipio es un campo requerido"
                               ng-init='birthMunicipality = "${(m.imputed.birthMunicipality ==  null) ? "" : m.imputed.birthMunicipality}";'>
                         <span class="field-validation-valid" data-valmsg-for="imputed.birthMunicipality"
                               data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-2 element-right">
                        Localidad/Ciudad:
                    </div>
                    <div class="col-xs-3">
                        <input class="form-control"
                               type="text" ng-model="location" id="imputed.birthLocation"
                               name="imputed.birthLocation" data-val-required="La localidad es un campo requerido"
                               ng-init='location = "${(m.imputed.birthLocation ==  null) ? "" : m.imputed.birthLocation}";'>
                         <span class="field-validation-valid" data-valmsg-for="imputed.birthLocation"
                               data-valmsg-replace="true"></span>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-3 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('socialEnvironment.physicalCondition')"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"
           code="socialEnvironment.physicalCondition"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('socialEnvironment.physicalCondition')"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('socialEnvironment.physicalCondition')"></i>
        �Padece alguna enfermedad o condici�n f�sica?:
    </div>
    <div class="col-xs-9">
        <textarea class="form-control"
                  data-val-required="Si padece alguna enfermedad o condici�n f�sica es un campo requerido"
                  name="socialEnvironment.physicalCondition">${m.socialEnvironment.physicalCondition}</textarea>
                <span class="field-validation-valid" data-valmsg-for="socialEnvironment.physicalCondition"
              data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div>
    <div class="row">
        <div class="col-xs-3 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('socialEnvironment.activities')"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"
               code="socialEnvironment.activities"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('socialEnvironment.acitivies')"></i>
            �Qu� actividades realiza?:
        </div>
        <div class="col-xs-9 element-left">
            <input name="activities" ng-model="activities" ng-update-hidden type="hidden">
            <select multiple="" class="form-control chosen-select" ng-model="activityModel" data-placeholder="..."
                    ng-init='lstActivity = ${lstActivity}; lstActivitySelec = ${(activity == null) ? '[]' : activity}; selectedActivities(lstActivity,lstActivitySelec);'
                    id="slctActivity" ng-change="matchActivities()"
                    ng-options="ac as ac.name for ac in lstActivity">
            </select>
        </div>
    </div>
    <br/>

    <div class="row">
        <div ng-repeat="activity in activityModel">
            <div ng-show="activity.specification==true">
                <div class="col-xs-3">
                    Especif�que activiades {{activity.name}}:
                </div>
                <div class="col-xs-9">
                    <input class="form-control" data-val="true"
                           data-val-length="Debe tener al menos 3 y m�ximo 255 caracteres"
                           data-val-length-max="255" data-val-length-min="3"
                           data-val-required="La especificaci�n de actividades {{activity.name}} es un campo requerido"
                           type="text" value="" ng-model="specification[activity.name]"
                           id="specification{{activity.name}}" name="specification{{activity.name}}"
                           ng-change="matchActivities()"><br/>
                <span class="field-validation-valid" data-valmsg-for="specification{{activity.name}}"
                      data-valmsg-replace="true"></span>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-3 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('socialEnvironment.comment')"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"
           code="socialEnvironment.comment"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('socialEnvironment.comment')"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('socialEnvironment.comment')"></i>
        Comentarios:
    </div>
    <div class="col-xs-9">
        <textarea class="form-control" name="socialEnvironment.comment"
                  data-val-required="Los comentarios es un campo requerido">${m.socialEnvironment.comment}</textarea>
         <span class="field-validation-valid" data-valmsg-for="socialEnvironment.comment"
               data-valmsg-replace="true"></span>
    </div>
</div>
</div>
<script>
    $('.date-picker').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>