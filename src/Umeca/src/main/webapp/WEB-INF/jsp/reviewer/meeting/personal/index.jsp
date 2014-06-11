
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="personalDataController">

    <%@ include file="/WEB-INF/jsp/reviewer/meeting/personal/_personalData.jsp"%>

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
    $('.date-picker').datepicker({autoclose: true, endDate:new Date()}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>

