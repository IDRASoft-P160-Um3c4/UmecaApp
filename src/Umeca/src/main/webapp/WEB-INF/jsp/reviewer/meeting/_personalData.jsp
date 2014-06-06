<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="personalDataController">
    <div class="col-xs-10 element-center col-xs-offset-1">
        <h2><i class="purple glyphicon glyphicon-user bigger-100"></i> &nbsp;Datos personales y entorno social</h2>
        <div ng-show="msgExito" class="alert alert-success element-center success-font">
            {{msgExito}}
        </div>
        <form id="FormPersonalData" name="FormPersonalData" ng-submit="submit('#FormPersonalData')"
              class="form-horizontal"
              role="form">
        <input type="hidden" value="${m.caseDetention.id}" name="caseDetention.id">

        <div class="row">
            <div class="col-xs-6">
                <div class="row">
                    <div class="col-xs-3 element-left">
                        <br/>G�nero:
                    </div>
                    <div class="col-xs-9">
                        <div class="row" ng-init="gen=${(m.imputed.gender == null) ? false: m.imputed.gender}">
                            <div class="col-xs-6">
                                <div class="radio">
                                    <label>
                                        <input class="ace" type="radio" ng-checked="gen==true" name="imputed.gender"
                                               data-val-required="El g�nero es un campo requerido" id="genero" value="true"
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
                    <div class="col-xs-6 element-left">
                        Fecha de nacimiento:
                    </div>
                    <div class="col-xs-6">
                        <div class="input-group">
                            <input class="form-control" id="dateBirth" readonly="readonly" value="${m.imputed.dateBirth}">
                        </div>
                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-3 element-left">
                        Celular:
                    </div>
                    <div class="col-xs-9">
                        <div class="col-xs-10">
                            <input class="form-control" data-val="true" data-val-length="Debe tener 10 caracteres"
                                   data-val-length-max="10" data-val-length-min="10"
                                   type="text" ng-model="celPhone"  id="celPhone" ng-init="celPhone= '${(m.imputed.celPhone == null) ? '': m.imputed.celPhone}'" name="imputed.celPhone">
                            <span class="field-validation-valid" data-valmsg-for="imputed.celPhone" data-valmsg-replace="true"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="widget-box">
                    <div class="widget-header" ng-init="maritalStatus = ${m.imputed.maritalStatus.id == null ? 1: m.imputed.maritalStatus.id}">
                        <h5>Estado civil</h5>
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
                                            <input name="imputed.maritalStatus.id" type="radio" class="ace" ng-model="maritalStatus"
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
                            <div class="col-xs-5" ng-show="maritalStatus ==2 || maritalStatus == 4">
                                <div class="space-10"></div>
                                <div class="widget-main">
                                    <input type="text" class="input-mini" id="spinnder1" value="${m.imputed.yearsMaritalStatus ==null ? '': m.imputed.yearsMaritalStatus}"
                                           name="imputed.yearsMaritalStatus"/> A�os
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
                        <h5>Hijos</h5>
                    </div>
                    <div class="widget-body">
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="col-xs-1 align-left">
                                    Total:
                                </div>
                                <div class="col-xs-3">
                                    <input type="text" class="form-control" name="imputed.boys" value="${m.imputed.boys}"/>
                                </div>
                                <div class="col-xs-4 align-left">
                                    Dependientes econ�micos:
                                </div>
                                <div class="col-xs-2">
                                    <input type="text" class="form-contro1" name="imputed.dependentBoys"
                                           value="${m.imputed.dependentBoys == null ? '': m.imputed.dependentBoys}"/>
                                </div>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>
            </div>

            <br/>

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4>Lugar de nacimiento</h4>
                        </div>

                        <div class="widget-body">
                            <br/>

                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="col-xs-5">
                                        Pa�s:
                                    </div>
                                    <div class="col-xs-7">
                                        <div>
                                            <select class="form-control">
                                                <option value="00" selected="selected">--Selecciona--</option>
                                                <option value="NV">Nevada</option>
                                                <option value="NH">New Hampshire</option>
                                                <option value="NJ">New Jersey</option>
                                                <option value="NM">New Mexico</option>
                                                <option value="NY">New York</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-5">
                                        Estado:
                                    </div>
                                    <div class="col-xs-7">
                                        <select class="form-control">
                                            <option value="00" selected="selected">--Selecciona--</option>
                                            <option value="NV">Nevada</option>
                                            <option value="NH">New Hampshire</option>
                                            <option value="NJ">New Jersey</option>
                                            <option value="NM">New Mexico</option>
                                            <option value="NY">New York</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="col-xs-5">
                                        Municipio:
                                    </div>
                                    <div class="col-xs-7">
                                        <select class="form-control">
                                            <option value="00" selected="selected">--Selecciona--</option>
                                            <option value="NV">Nevada</option>
                                            <option value="NH">New Hampshire</option>
                                            <option value="NJ">New Jersey</option>
                                            <option value="NM">New Mexico</option>
                                            <option value="NY">New York</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-5">
                                        Localidad:
                                    </div>
                                    <div class="col-xs-7">
                                        <select class="form-control">
                                            <option value="00" selected="selected">--Selecciona--</option>
                                            <option value="NV">Nevada</option>
                                            <option value="NH">New Hampshire</option>
                                            <option value="NJ">New Jersey</option>
                                            <option value="NM">New Mexico</option>
                                            <option value="NY">New York</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <br/>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
       <!-- <div class="row">
            <div class="col-xs-3 element-left">�Padece alguna enfermedad o condici�n f�sica?:</div>
            <div class="col-xs-9">
                <div class="widget-main">
                    <select multiple="" class="width-90 chosen-select" ng-model="phyModel" data-placeholder="..."
                            ng-init="lstPhysicalCondition = ${lstPhysicalCondition}; pCSelected = '${(physicalCondition == null) ? "    " : physicalCondition}'; selectedPhysicalCondition(lstPhysicalCondition,pCSelected);"
                            name="physicalCondition" ng-options="phy as phy.name for phy in lstPhysicalCondition">
                    </select>
                </div>
            </div>
        </div>
        <br/>

        <div class="row">
            <div class="col-xs-3 element-left">�Qu� actividades realiza?:</div>
            <div class="col-xs-9">
                <div class="widget-main">
                    <select multiple="" class="width-90 chosen-select" ng-model="activityModel" data-placeholder="..."
                            ng-init='lstActivity = ${lstActivity}; activityList = "${(activity == null) ? '' : activity}"; selectedActivities(lstActivity,activityList);'
                            name="activity" id="slctActivity"
                            ng-options="ac as ac.name for ac in lstActivity">

                    </select>
                </div>
            </div>
        </div>        -->
        <br/>

            <div class="row">
                <div class="col-xs-3 element-left">Comentarios:</div>
                <div class="col-xs-9">
                    <textarea class="form-control" name="socialEnvironment.comment">${m.socialEnvironment.comment}</textarea>
                </div>
            </div>
        </form>
        <br/>
        <div ng-show="msgError" class="alert alert-danger element-center error-font">
            {{msgError}}
        </div>
    </div>
    <div class="col-xs-12">
        <div class="modal-footer">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormPersonalData', '/reviewer/meeting/upsertPersonalData.json');">
                        <span class="glyphicon glyphicon-cloud-upload"></span>
                          Guardar
                    </span>
        </div>
    </div>
</div>
<script>
    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>
