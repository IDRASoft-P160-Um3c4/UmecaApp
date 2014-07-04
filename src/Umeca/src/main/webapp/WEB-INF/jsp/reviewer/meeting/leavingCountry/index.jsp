<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="upsertMeetingController">
    <div class="col-xs-10 element-center col-xs-offset-1">

        <form id="FormLeaveCountry" name="FormLeaveCountry"   class="form-horizontal"  role="form">
            <%@ include file="/WEB-INF/jsp/reviewer/meeting/leavingCountry/content.jsp"%>
        </form>
        <br/>

        <div ng-show="msgError" class="alert alert-danger element-center error-font">
            {{msgError}}
        </div>
    </div>
    <div class="col-xs-12">
        <div class="modal-footer">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormLeaveCountry', '<c:url value="/reviewer/meeting/upsertLeaveCountry.json"/>');">
                        <span class="glyphicon glyphicon-cloud-upload"></span>
                          Guardar
                    </span>
        </div>
    </div>
</div>