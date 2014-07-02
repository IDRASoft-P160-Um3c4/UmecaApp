<div ng-controller="personalDataController">
    <div class="row">
    <div class="col-xs-6">
        <div class="row">
            <div class="col-xs-3 element-left">
                <br/> <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('imputed.gender')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="imputed.gender"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('imputed.gender')"></i>
                <i class="icon-list-alt icon-only bigger-120"  ng-click="doSelectSource('imputed.gender')" ng-show="selectSource"></i>
                Género:
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

        <div class="row">
            <div class="col-xs-5 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('imputed.birthDate')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="imputed.birthDate"></i>
                <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('imputed.birthDate')"></i>
                <i class="icon-list-alt icon-only bigger-120"  ng-click="doSelectSource('imputed.birthDate')" ng-show="selectSource"></i>
                Fecha de nacimiento:
            </div>
            <div class="col-xs-7">
                <input class="form-control" id="birthDate" readonly="readonly" value="${m.imputed.birthDate}">
            </div>
        </div>
        <br/>

        <div class="row">
            <div class="col-xs-5 element-left">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('imputed.phone')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"></i>
                <i class="icon-ban-circle gray icon-only bigger-120"  ng-show="verification"  ng-click="doConfirmVerifNotKnow('imputed.phone')"></i>
                <i class="icon-list-alt icon-only bigger-120"  ng-click="doSelectSource('imputed.phone')" ng-show="selectSource"></i>
                Celular:
            </div>
            <div class="col-xs-7">
                    <input class="form-control" data-val="true"
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
                 <div class="col-xs-2" ng-show="verification">
                     <i class="icon-ok-circle green  icon-only bigger-120"  ng-click="doConfirmVerifEqual('imputed.maritalStatus')"></i>
                     <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4" code="imputed.maritalStatus"></i>
                     <i class="icon-ban-circle inverse icon-only bigger-120" ng-click="doConfirmVerifNotKnow('imputed.maritalStatus')"></i>
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
                                <span class="lbl">Unión libre</span>
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
                                   data-val-length="Debe tener mínimo 1 y máximo 3 caracteres"
                                   data-val-length-max="3" data-val-length-min="1"
                                   data-val-required="El número de años es un campo requerido"
                                   data-val-regex="La cantidad de años sólo pueden ser números"/> Años
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
            <div class="col-xs-1"  ng-show="verification">
                <i class="icon-ok-circle green  icon-only bigger-120" ng-click="doConfirmVerifEqual('imputed.boys')"></i>
                <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" code="imputed.boys"></i>
                <i class="icon-ban-circle gray icon-only bigger-120"   ng-click="doConfirmVerifNotKnow('imputed.boys')"></i>
            </div>
            <div class="col-xs-11 element-left">
            <h5>Hijos</h5>
                </div>
        </div>
        <div class="widget-body">
            <br/>

            <div class="row">
                <div class="col-xs-10 col-xs-offset-1">
                    <div class="col-xs-1 align-left">
                        Total:
                    </div>
                    <div class="col-xs-3">
                        <input type="text" class="form-control" name="imputed.boys" value="${m.imputed.boys}"
                               data-val-regex-pattern="([0-9]+)"
                               data-val-regex="Sólo puede guardar números" data-val="true" data-val="true"
                               id="imputed.boys"/>
                    </div>
                    <div class="col-xs-4 align-left">
                        Dependientes económicos:
                    </div>
                    <div class="col-xs-2">
                        <input type="text" class="form-contro1" name="imputed.dependentBoys"
                               data-val-regex-pattern="([0-9]+)"
                               data-val-regex="S?lo puede guardar números" id="imputed.dependentBoys" data-val="true"
                               value="${m.imputed.dependentBoys == null ? '': m.imputed.dependentBoys}"/>

                    </div>
                </div>
                <div class="col-xs-10 col-xs-offset-1">
                    <div class="col-xs-4">
                        <span class="field-validation-valid" data-valmsg-for="imputed.boys"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-4 col-xs-offset-2">
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
                <div class="col-xs-1" ng-show="verification">
                    <i class="icon-ok-circle green  icon-only bigger-120" ng-click="doConfirmVerifEqual('imputed.birthPlace')"></i>
                    <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="4" code="imputed.birthPlace"></i>
                    <i class="icon-ban-circle gray icon-only bigger-120"  ng-click="doConfirmVerifNotKnow('imputed.birthPlace')"></i>
                </div>
                <div class="col-xs-1" ng-show="selectSource == true">
                    <i class="icon-list-alt icon-only bigger-120"  ng-click="doConfirmVerifNotKnow('imputed.birthPlace')"></i>
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
                        <input type="hidden" ng-update-hidden ng-model="countryId" name="imputed.birthCountry.id"
                               id="country"
                               ng-init='l.countryId = ${(m.imputed.birthCountry.id == null)? 'undefined':m.imputed.birthCountry.id}'>
                        <select class="form-control element-center" ng-model="country"
                                ng-options="e.name for e in listCountry"
                                url-request="/catalogs/getStatesByCountry.json"
                                ng-change="countryId = country.id;" ng-init='listCountry = ${lstCountry};'></select>
                    </div>
                    <div class="col-xs-2 element-right">
                        Estado:
                    </div>
                    <div class="col-xs-3">
                        <input class="form-control"
                               type="text" ng-model="state" id="imputed.birthState"
                               name="imputed.birthState"
                               ng-init='state = "${(m.imputed.birthState ==  null) ? "" : m.imputed.birthState}";'>
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
                               name="imputed.birthMunicipality"
                               ng-init='birthMunicipality = "${(m.imputed.birthMunicipality ==  null) ? "" : m.imputed.birthMunicipality}";'>
                    </div>
                    <div class="col-xs-2 element-right">
                        Localidad/Ciudad:
                    </div>
                    <div class="col-xs-3">
                        <input class="form-control"
                               type="text" ng-model="location" id="imputed.birthLocation"
                               name="imputed.birthLocation"
                               ng-init='location = "${(m.imputed.birthLocation ==  null) ? "" : m.imputed.birthLocation}";'>
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
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialEnvironment.physicalCondition')"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialEnvironment.physicalCondition"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialEnvironment.physicalCondition')"></i>
        ¿Padece alguna enfermedad o condición física?:</div>
    <div class="col-xs-9">
        <textarea class="form-control"
                  name="socialEnvironment.physicalCondition">${m.socialEnvironment.physicalCondition}</textarea>
    </div>
</div>
<br/>
<div>
<div class="row">
    <div class="col-xs-3 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialEnvironment.activities')"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialEnvironment.activities"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialEnvironment.acitivies')"></i>
        ¿Qué actividades realiza?:</div>
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
                Especifíque activiades {{activity.name}}:
            </div>
            <div class="col-xs-9">
                <input class="form-control" data-val="true"
                       data-val-length="Debe tener al menos 3 y máximo 255 caracteres"
                       data-val-length-max="255" data-val-length-min="3"
                       data-val-required="La especificación de actividades {{activity.name}} es un campo requerido"
                       type="text" value="" ng-model="specification[activity.name]"
                       id="specification{{activity.name}}" name="specification{{activity.name}}"
                       ng-change="matchActivities()">
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
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('socialEnvironment.comment')"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="socialEnvironment.comment"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('socialEnvironment.comment')"></i>
        Comentarios:</div>
    <div class="col-xs-9">
        <textarea class="form-control" name="socialEnvironment.comment">${m.socialEnvironment.comment}</textarea>
    </div>
</div>
</div>
<script>
    $('.date-picker').datepicker({autoclose: true, endDate:new Date()}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>