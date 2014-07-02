<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1">

        <div class="row element-center">
            <h2><i class="purple glyphicon glyphicon-user bigger-100 element-center"></i> &nbsp;Domicilios</h2>
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