<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row">

    <div class="col-xs-10 col-xs-offset-1">

        <div class="row element-center">
            <h2><i class="glyphicon glyphicon-edit bigger-100 element-center"></i> &nbsp;Enrolamiento</h2>
        </div>
        <br/>

        <div class="row">
            <div ng-show="pdSuccessMsg&&pdSuccessMsg!=''"
                 class="col-xs-12 alert alert-success element-center success-font">
                {{pdSuccessMsg}}
            </div>
            <div ng-show="pdErrorMsg&&pdErrorMsg!=''" class="alert alert-danger element-center error-font">
                {{pdErrorMsg}}
            </div>
        </div>

        <div class="row">

            <form id="FormFingerPrinting" name="FormFingerPrinting" class="form-horizontal" role="form">

            </form>
        </div>
        <br/>


    </div>

    <div class="col-xs-12">
        <div class="modal-footer">
        <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-show="{{fm.objView.canTerminate==true}}"
              ng-click="tuAccion();">
            <span class="glyphicon glyphicon-cloud-upload"></span>
              Guardar
        </span>
        </div>
    </div>

</div>
