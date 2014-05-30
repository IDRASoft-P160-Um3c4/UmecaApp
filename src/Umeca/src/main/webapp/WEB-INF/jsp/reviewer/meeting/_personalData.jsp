<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row">
<div class="col-xs-10 element-center col-xs-offset-1">
<h2><i class="purple glyphicon glyphicon-user bigger-100"></i> &nbsp;Datos personales y entorno social</h2>
<br/>
<form id="FormPersonalData" name="FormPersonalData" ng-submit="submit('#FormPersonalData')" class="form-horizontal"
      role="form">

<div class="row">
    <div class="col-xs-6">
        <div class="row">
        <div class="col-xs-3 element-left">
            <br/>Género:
        </div>
        <div class="col-xs-9">
            <div class="row" ng-init="gen=${m.imputed.gender}">
                <div class="col-xs-6">
                    <div class="radio">
                        <label>
                            <input class="ace" type="radio" ng-checked="gen==true" name="imputed.gender"
                                   data-val-required="El género es un campo requerido" id="genero" value="true" ng-model="gen">
                            <span class="lbl">Femenino</span>
                        </label>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="radio">
                        <label>
                            <input class="ace" type="radio" value="false" ng-model="gen" ng-checked="gen==false" name="imputed.gender">
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
                <input class="form-control" name="imputed.dateBirth" readonly="readonly" value="${m.imputed.dateBirth}">
            </div>
        </div>
       </div>
        <br/>
        <div class="row">
        <div class="col-xs-3 element-left">
            Celular:
        </div>
        <div class="col-xs-9">
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener 10 caracteres" ng-init="m.lastNameP=${m.imputed.celPhone}"
                   data-val-length-max="10" data-val-length-min="10"
                   id="celPhone" name="celPhone"
                   type="text" ng-model="celPhone"/>
        </div>
    </div>
        </div>
    <div class="col-xs-6">
        <div class="widget-box">
            <div class="widget-header" ng-init="maritalStatus = ${m.imputed.maritalStatus.id}">
                <h5>Estado civil</h5>
            </div>
            <div class="widget-body">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="control-group" >
                            <div class="radio element-left">
                                <label>
                                    <input name="form-field-radio" type="radio" class="ace" value="1"
                                           ng-model="maritalStatus"/>
                                    <span class="lbl">Soltero</span>
                                </label>
                            </div>

                            <div class="radio element-left">
                                <label>
                                    <input name="form-field-radio" type="radio" class="ace" ng-model="maritalStatus"
                                           value="2"/>
                                    <span class="lbl">Casado</span>

                                </label>
                            </div>
                        </div>

                        <div class="radio element-left">
                            <label>
                                <input name="form-field-radio" type="radio" class="ace" value="3"
                                       ng-model="maritalStatus"/>
                                <span class="lbl">Divorciado</span>
                            </label>
                        </div>

                        <div class="radio element-left">
                            <label>
                                <input name="form-field-radio" type="radio" class="ace" ng-model="maritalStatus"
                                       value="4"/>
                                <span class="lbl">Unión libre</span>
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-5" ng-show="maritalStatus ==2 || maritalStatus == 4">
                        <div class="space-10"></div>
                        <div class="widget-main">
                            <input type="text" class="input-mini" id="spinnder1" value="${m.imputed.yearsMaritalStatus}" name="imputed.yearsMaritalStatus"/> Años
                        </div>
                    </div>
                </div>
                <br/>
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
                        <input type="text" class="form-control"/>
                    </div>
                    <div class="col-xs-4 align-left">
                        Dependientes económicos:
                    </div>
                    <div class="col-xs-2">
                        <input type="text" class="form-contro1"/>
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
                            País:
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
<div class="row">
  <div class="col-xs-3 element-left">¿Padece alguna enfermedad o condición física?:{{physical_condition}}</div>
    <div class="col-xs-9">
            <div class="widget-main">
                <select class="form-control chosen-select" ng-model="physicalCondition"
                        ng-options="e.name for e in lstRoles"
                        ng-change="m.roleId = m.role.id"
                        ng-init='lstRoles = ${lstRoles};'></select>
                <select multiple="" class="width-90 chosen-select"data-placeholder="...." ng-model="physical_condition">
                    <option value="">&nbsp;</option>
                    <option value="TX">Texas</option>
                    <option value="UT">Utah</option>
                    <option value="VT">Vermont</option>
                    <option value="VA">Virginia</option>
                    <option value="WA">Washington</option>
                    <option value="WV">West Virginia</option>
                    <option value="WI">Wisconsin</option>
                    <option value="WY">Wyoming</option>
                </select>
            </div>
    </div>
    </div>
<br/>
<div class="row">
    <div class="col-xs-3 element-left">¿Qué actividades realiza?:</div>
    <div class="col-xs-9">
        <div class="widget-main">
            <select multiple="" class="width-90 chosen-select"  data-placeholder="...">
                <option value="">&nbsp;</option>
                <option value="TX">Texas</option>
                <option value="UT">Utah</option>
                <option value="VT">Vermont</option>
                <option value="VA">Virginia</option>
                <option value="WA">Washington</option>
                <option value="WV">West Virginia</option>
                <option value="WI">Wisconsin</option>
                <option value="WY">Wyoming</option>
            </select>
        </div>
    </div>
</div>
<br/>
<div class="row">
   <div class="col-xs-3 element-left">Comentarios:</div>
    <div class="col-xs-9">
        <textarea id="form-field-11" class="form-control"></textarea>
    </div>
</div>
</div>
</div>
       </div>
</form>
<div class="modal-footer">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormPersonalData', '/reviewer/meeting/upsertPersonalData.json');">
                        <span class="glyphicon glyphicon-cloud-upload"></span>
                          Guardar
                    </span>

</div>
<script>
    $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });
    $(".chosen-select").chosen();
</script>