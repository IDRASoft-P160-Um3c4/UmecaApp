<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormJobId");
    });
</script>



<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="jobController" ng-cloak>
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-briefcase "></i>&nbsp;&nbsp;Empleo</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormJobId" name="FormJobId" class="form-horizontal" role="form">
                        <br />
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/job/content.jsp"%>
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
                          ng-click="submit('#FormJobId', '/reviewer/meeting/job/doUpsert.json');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });
</script>

