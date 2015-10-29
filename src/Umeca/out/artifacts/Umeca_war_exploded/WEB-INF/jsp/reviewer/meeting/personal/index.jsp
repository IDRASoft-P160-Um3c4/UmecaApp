<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="upsertMeetingController">
    <div class="blocker" ng-show="WaitFor==true">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>

    <div class="col-xs-10 element-center col-xs-offset-1">
        <h2><i class="purple glyphicon glyphicon-user bigger-100"></i> &nbsp;Datos personales y entorno social</h2>
        <div ng-show="msgExito" class="alert alert-success element-center success-font">
          <span ng-bind-html="msgExito"></span>
        </div>
        <form id="FormPersonalData" name="FormPersonalData" ng-submit="submit('#FormPersonalData')"
              class="form-horizontal"
              role="form">
        <input type="hidden" value="${m.caseDetention.id}" name="caseDetention.id">

            <%@ include file="/WEB-INF/jsp/reviewer/meeting/personal/content.jsp"%>
        </form>
        <br/>
        <div ng-show="msgError" class="alert alert-danger element-center error-font">
            <span ng-bind-html="msgError"></span>
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

