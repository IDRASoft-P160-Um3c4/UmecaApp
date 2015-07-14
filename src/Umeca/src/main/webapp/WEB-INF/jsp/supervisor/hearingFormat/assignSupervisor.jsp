<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:500px" ng-controller="hearingFormatController">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Asignar caso
                            a supervisor</h4>
                    </div>
                </div>

                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
                        <br/>

                        <input type="hidden" name="idCase" value="${idCase}"/>

                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-2">
                                <label>Elige el nuevo supervisor para el caso</label>
                                <br/>
                                <select class="form-control element-center"
                                        ng-model="m.umecaSupervisor"
                                        ng-options="e.description for e in lstSupervisor"
                                        ng-init='lstSupervisor = ${lstSupervisor};'></select>
                            </div>
                        </div>
                        <input type="hidden" name="idUser" value="{{m.umecaSupervisor.id}}"/>

                        <br/>
                    </form>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-show="showLabels!='true'"
                          ng-click="submitAssignSupervisor('#FormCatId','<c:url value="/supervisor/hearingFormat/doAssignSupervisor.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>