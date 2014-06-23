<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1">

        <div class="row element-center">
            <h2><i class="purple glyphicon glyphicon-user bigger-100 element-center"></i> &nbsp;Datos personales</h2>
        </div>

        <div class="row">
            <div ng-show="msgExito" class="col-xs-12 alert alert-success element-center success-font">
                {{successMsg}}
            </div>
        </div>

        <div class="row">

            <form id="FormPersonalData" name="FormPersonalData" ng-submit="submit('#FormPersonalData')"
                  class="form-horizontal"
                  role="form">


                <div class="row">

                    <div class="col-xs-6">
                        <div class="widget-box">
                            <div class="widget-header">Género</div>
                            <div class="widget-body">
                                <div class="control-group">
                                    <div class="radio">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="gender"
                                                       ng-model="m.objView.personalData.gender" value="true"/>
                                                <span class="lbl">&nbsp;&nbsp;Femenino</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="radio">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="gender"
                                                       ng-model="m.objView.personalData.gender" value="false"/>
                                                <span class="lbl">&nbsp;&nbsp;Masculino</span>
                                            </label>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-6">
                        <div class="widget-box">
                            <div class="widget-header">Estado civil</div>
                            <div class="widget-body">
                                <div class="control-group">
                                    <div class="radio">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="maritalStatus"
                                                       ng-model="m.objView.personalData.maritalStatus" value="1"/>
                                                <span class="lbl">&nbsp;&nbsp;Soltero</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="radio">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="maritalStatus"
                                                       ng-model="m.objView.personalData.maritalStatus" value="2"/>
                                                <span class="lbl">&nbsp;&nbsp;Casado</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="radio">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="maritalStatus"
                                                       ng-model="m.objView.personalData.maritalStatus" value="3"/>
                                                <span class="lbl">&nbsp;&nbsp;Divorciado</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="radio">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="maritalStatus"
                                                       ng-model="m.objView.personalData.maritalStatus" value="4"/>
                                                <span class="lbl">&nbsp;&nbsp;Union libre</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="radio"
                                         ng-show="m.objView.personalData.maritalStatus==2 || m.objView.personalData.maritalStatus==4">
                                        <div class="col-xs-offset-1">
                                            <label>Años</label><br>
                                            <input type="text" class="ace" name="maritalStatusYears"
                                                   ng-model="m.objView.personalData.maritalStatusYears"/>
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
                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header">Lugar y fecha de nacimiento</div>
                            <div class="widget-body">
                                <br/>

                                <div class="row">
                                    <div class="col-xs-4 col-xs-offset-1">
                                        <label>Pais</label>
                                        <select class="form-control element-center" ng-model="country"
                                                ng-options="e.name for e in lstCountry"
                                                url-request="/catalogs/getStatesByCountry.json"
                                                ng-change="countryId = country.id;"
                                                ng-init='lstCountry = ${lstCountry};'></select>
                                    </div>

                                    <div class="col-xs-3">
                                        <label>Estado</label>
                                        <input type="text" class="ace" name="state"
                                               ng-model="m.objView.personalData.state">
                                    </div>

                                    <div class="col-xs-3">
                                        <label>Fecha de nacimiento:</label>

                                        <div class="input-group">
                                            <input class="form-control date-picker"
                                                   type="text" data-date-format="dd/mm/yyyy"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                                        </div>

                                        <br/>
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
                            <div class="widget-header">Padecimientos</div>
                            <div class="widget-body">
                                <br/>

                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <label>¿Padece alguna enfermedad o condición física?</label>
                                        <br/>

                                        <div class="widget-main">
                                            <select class="form-control" id="form-field-select-1">
                                                <option value="">&nbsp;</option>
                                                <option value="AL">Alabama</option>
                                                <option value="AK">Alaska</option>
                                                <option value="AZ">Arizona</option>
                                                <option value="AR">Arkansas</option>
                                                <option value="CA">California</option>
                                                <option value="CO">Colorado</option>
                                                <option value="CT">Connecticut</option>
                                                <option value="DE">Delaware</option>
                                                <option value="FL">Florida</option>
                                                <option value="GA">Georgia</option>
                                                <option value="HI">Hawaii</option>
                                                <option value="ID">Idaho</option>
                                                <option value="IL">Illinois</option>
                                                <option value="IN">Indiana</option>
                                                <option value="IA">Iowa</option>
                                                <option value="KS">Kansas</option>
                                                <option value="KY">Kentucky</option>
                                                <option value="LA">Louisiana</option>
                                                <option value="ME">Maine</option>
                                                <option value="MD">Maryland</option>
                                                <option value="MA">Massachusetts</option>
                                                <option value="MI">Michigan</option>
                                                <option value="MN">Minnesota</option>
                                                <option value="MS">Mississippi</option>
                                                <option value="MO">Missouri</option>
                                                <option value="MT">Montana</option>
                                                <option value="NE">Nebraska</option>
                                                <option value="NV">Nevada</option>
                                                <option value="NH">New Hampshire</option>
                                                <option value="NJ">New Jersey</option>
                                                <option value="NM">New Mexico</option>
                                                <option value="NY">New York</option>
                                                <option value="NC">North Carolina</option>
                                                <option value="ND">North Dakota</option>
                                                <option value="OH">Ohio</option>
                                                <option value="OK">Oklahoma</option>
                                                <option value="OR">Oregon</option>
                                                <option value="PA">Pennsylvania</option>
                                                <option value="RI">Rhode Island</option>
                                                <option value="SC">South Carolina</option>
                                                <option value="SD">South Dakota</option>
                                                <option value="TN">Tennessee</option>
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
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <br/>

        <div ng-show="msgError" class="alert alert-danger element-center error-font">
            {{errorMsg}}
        </div>

    </div>

    <div class="col-xs-12">
        <div class="modal-footer">
        <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
              ng-click="submit('#FormPersonalData', '<c:url value="/reviewer/meeting/upsertPersonalData.json"/>');">
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
    $('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function () {
        $(this).next().focus();
    });
</script>