<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<div class="row element-center" ng-controller="upsertMeetingController">
    <div class="col-xs-12">
        <h2> <i class="orange icon-book  bigger-100">&nbsp;</i>Historia Escolar</h2>
        <br/>
        <div class="row" >
            <div class="col-xs-10 col-xs-offset-1">
                <div ng-show="msgExito" class="alert alert-success element-center success-font">
                    <span ng-bind-html="msgExito"></span>
                </div>
                <form id="FormSchool" name="FormSchool" ng-submit="submit('#FormSchool')" class="form-horizontal"
                      role="form">
                    <input type="hidden" value="${m.caseDetention.id}" name="caseDetention.id">

                <%@ include file="/WEB-INF/jsp/reviewer/meeting/school/content.jsp"%>
                </form>
                <div ng-show="msgError" class="alert alert-danger element-center error-font">
                    <span ng-bind-html="msgError"></span>
                </div>

            </div>
        </div>
        <div class="row">
            <div class="modal-footer">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormSchool', '<c:url value="/reviewer/meeting/school/doUpsert.json"/>');">
                        <span class="glyphicon glyphicon-cloud-upload"></span>
                           Guardar
                    </span>
            </div>
        </div>
    </div>
    <div class="blocker" ng-show="WaitFor==true">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>
</div>
