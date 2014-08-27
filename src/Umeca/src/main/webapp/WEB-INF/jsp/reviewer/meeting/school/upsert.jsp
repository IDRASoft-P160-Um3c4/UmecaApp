<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
<div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
<div class="modal-dialog" style="width:800px">
<div class="modal-content">
<div class="modal-header">
    <div class="alert alert-info ">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="element-center"><i class="glyphicon glyphicon-briefcase "></i>&nbsp;&nbsp;Empleo</h4>
    </div>
</div>
<div class="modal-body">
    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
        <br />
        <%@ include file="/WEB-INF/jsp/reviewer/meeting/school/content.jsp"%>
    </form>
    <br />
    <div class="row">
        <div class="col-xs-12">
            <div ng-show="MsgError" class="alert alert-danger element-center"  ng-bind-html="MsgError">
                <%--{{MsgError}}--%>
            </div>
        </div>
    </div>
</div>
<div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormCatId', '<c:url value="/reviewer/meeting/doNewMeeting.json"/>');">
                          Continuar
                    </span>
</div>
</div>
</div>
</div>
</div>

<script>
    $('.date-picker').datepicker({autoclose:true, endDate:new Date()}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });
</script>

