<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1">

        <div class="row element-center">
            <h2><i class="pink icon-briefcase  bigger-100"></i> &nbsp;Actividades que reliza
                el imputado</h2>
        </div>

        <div class="row">
            <div ng-show="msgExito" class="col-xs-12 alert alert-success element-center success-font">
                {{successMsg}}
            </div>
        </div>

        <div class="row">
        </div>

        <div class="row">

            <form id="FormPersonalData" name="FormPersonalData" ng-submit="submit('#FormPersonalData')"
                  class="form-horizontal"
                  role="form">


                <div class="widget-box">
                    <div class="widget-header">Ocupación</div>
                    <div class="widget-body">
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="row">
                                    <br/>

                                    <div class="col-xs-4">
                                        <label for="accompOccupation">Ocupación</label>
                                        <br/>
                                        <input id="accompOccupation" ng-model="m.accompOccupation"
                                               name="accompOccupation"
                                               type="text" class="input-xxlarge"
                                               data-val="true"
                                               data-val-required="Ocupación es un campo requerido"/>
                                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompOccupation"
                  data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="col-xs-4">
                                        <label for="accompOccupationPlace">Lugar de ocupación</label>
                                        <br/>
                                        <input id="accompOccupationPlace" ng-model="m.accompOccupationPlace"
                                               name="accompOccupationPlace"
                                               type="text" class="input-xxlarge"
                                               data-val="true"
                                               data-val-required="Lugar de ocupación es un campo requerido"/>
                                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompOccupationPlace"
                  data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="col-xs-4">
                                        <label for="accompOccupationPhone">Teléfono</label>
                                        <br/>
                                        <input id="accompOccupationPhone" ng-model="m.accompOccupationPhone"
                                               name="accompOccupationPhone"
                                               type="text" class="input-xxlarge"
                                               data-val="true"
                                               data-val-required="Teléfono es un campo requerido"/>
                                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompOccupationPhone"
                  data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>
                <br/>

                <div class="widget-box">
                    <div class="widget-header">Actividades</div>
                    <div class="widget-body">
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="row">
                                    <div>
                                        <label>Actividades que reliza el imputado</label>
                                        <br/>
                                        <textarea class="input-xxlarge form-control limited" name="crimes"
                                                  ng-model="m.crimes"
                                                  maxlength="980" data-val="true"
                                                  data-val-required="Institución es un campo requerido">
                                        </textarea>
                                            <span class="field-validation-valid" data-valmsg-for="crimes"
                                                  data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>
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