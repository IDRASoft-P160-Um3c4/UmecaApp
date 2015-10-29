
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormForumulationDateId");
        $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });
</script>


<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="formulationDateCtrl" ng-cloak>
        <div class="modal-dialog" style="width:820px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-calendar "></i>&nbsp;&nbsp;Cita de formulaci&oacute;n</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormForumulationDateId" name="FormForumulationDateId" class="form-horizontal" role="form">
                        <br />
                        <%@ include file="/WEB-INF/jsp/managereval/formulation/content.jsp"%>
                    </form>
                    <br />
                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError" class="alert alert-danger element-center">
                                {{MsgError}}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormForumulationDateId', '<c:url value="/managereval/formulation/doupsert.json"/>');">
                          Guardar
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
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>