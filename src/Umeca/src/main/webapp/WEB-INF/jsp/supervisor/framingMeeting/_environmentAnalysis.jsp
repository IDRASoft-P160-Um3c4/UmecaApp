<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1">

        <div class="row element-center">
            <h2><i class="blue icon-eye-open bigger-100"></i> &nbsp;Analisis del entorno
            </h2>
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
                    <br/>

                    <div class="col-xs-6">
                        <div class="widget-box">
                            <div class="widget-header">Vinculos</div>
                            <div class="widget-body">
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="widget-box">
                            <div class="widget-header">Medidas cautelares</div>
                            <div class="widget-body">
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <br/>

                    <div class="col-xs-6">
                        <div class="widget-box">
                            <div class="widget-header">Riesgos</div>
                            <div class="widget-body">
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">

                        <div class="widget-box">
                            <div class="widget-header">Amenazas</div>
                            <div class="widget-body">
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>
                                    </div>
                                </div>
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