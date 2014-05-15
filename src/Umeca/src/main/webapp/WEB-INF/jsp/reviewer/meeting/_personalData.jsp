<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script>
    $(".chosen-select").chosen();
</script>
<div class="row">
<div class="col-xs-10 element-center col-xs-offset-1">
<h2><i class="purple glyphicon glyphicon-user bigger-100"></i> &nbsp;Datos personales y entorno social</h2>
<br/>

<div class="row">
<div class="row">
    <div class="col-xs-6">
        <div class="col-xs-3 element-left">
            Apodo:
        </div>
        <div class="col-xs-9">
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener al menos 3 y máximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="3"
                   data-val-required="El Apodo es un campo requerido" id="name"
                   type="text" value="" ng-model="m.apodo">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
        </div>
        <br/>
        <div class="col-xs-2 element-left">
            Género:
        </div>
        <div class="col-xs-9">
            <div class="row">
                <div class="col-xs-6">
                    <div class="radio">
                        <label>
                            <input class="ace" type="radio" name="genero"
                                   data-val-required="El g?nero es un campo requerido" id="genero">
                            <span class="lbl">Femenino</span>
                        </label>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="radio">
                        <label>
                            <input class="ace" type="radio" name="genero">
                            <span class="lbl">Masculino</span>
                        </label>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="genero" data-valmsg-replace="true"></span>
        </div>
        <br/>
        <div class="col-xs-3 element-left">
            Fecha de nacimiento:
        </div>
        <div class="col-xs-9">
            <div class="input-group">
                <input id="id-date-picker-1" class="form-control date-picker" type="text"
                       data-date-format="dd-mm-yyyy">
                <span class="input-group-addon">
                <i class="icon-calendar bigger-110"></i>
                </span>
            </div>
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
        </div>
        <br/>       <br/>
        <div class="col-xs-3 element-left">
            Celular:
        </div>
        <div class="col-xs-9">
            <input class="form-control" data-val="true"
                   data-val-length="Debe tener 10"
                   data-val-length-max="10" data-val-length-min="10" id="cel"
                   type="text" value="" ng-model="m.apodo">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="widget-box">
            <div class="widget-header">
                <h4>Estado civil</h4>
            </div>

            <div class="widget-body">
                <div class="row">
                    <div class="col-xs-6">
                        <div class="control-group">
                            <div class="radio element-left">
                                <label>
                                    <input name="form-field-radio" type="radio" class="ace" value="soltero"
                                           ng-model="estadoCivil"/>
                                    <span class="lbl">Soltero</span>
                                </label>
                            </div>

                            <div class="radio element-left">
                                <label>
                                    <input name="form-field-radio" type="radio" class="ace" ng-model="estadoCivil"
                                           value="casado"/>
                                    <span class="lbl">Casado</span>

                                </label>
                            </div>
                        </div>

                        <div class="radio element-left">
                            <label>
                                <input name="form-field-radio" type="radio" class="ace" value="divorciado"
                                       ng-model="estadoCivil"/>
                                <span class="lbl">Divorciado</span>
                            </label>
                        </div>

                        <div class="radio element-left">
                            <label>
                                <input name="form-field-radio" type="radio" class="ace" ng-model="estadoCivil"
                                       value="unionLibre"/>
                                <span class="lbl">Unión libre</span>
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-5" ng-show="estadoCivil==='casado' || estadoCivil==='unionLibre'">
                        <div class="space-10"></div>
                        <div class="widget-main">
                            <input type="text" class="input-mini" id="spinnder1"/> Años
                        </div>
                    </div>
                </div>
            </div>
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
  <div class="col-xs-3 element-left">¿Padece alguna enfermedad o condición física?:</div>
    <div class="col-xs-3">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y máximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
               type="text" value="" ng-model="m.name">
    </div>
    <div class="col-xs-3 element-left">¿Qué actividades realiza?:</div>
    <div class="col-xs-3">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y máximo 200 caracteres"
               data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
               type="text" value="" ng-model="m.name">
    </div>
</div>
<div class="row">
   <div class="col-xs-3 element-left">Comentarios:</div>
    <div class="col-xs-9">
        <textarea id="form-field-11" class="form-control"></textarea>
    </div>
</div>
</div>
</div>
</div>
