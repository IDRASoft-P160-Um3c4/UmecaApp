<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });


</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:600px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-ok-circle"></i>&nbsp;&nbsp;Asistencia
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
                          role="form">
                        <input type="hidden" name="id" value="${idCP}"/>

                        <h5 class="element-center">&iquest;El imputado asisti&oacute; a la entrevista de encuadre?</h5>

                        <div class="col-md-3 col-md-offset-4">
                            <div class="radio">
                                <label><input type="radio" data-val-required="Es un campo requerido" value="true" name="attendance" checked>Asisti&oacute;</label>
                            </div>
                            <div class="radio">
                                <label><input type="radio" name="attendance" value="false" >No asisti&oacute;</label>
                            </div>
                        </div>

                    </form>
                    <br/>

                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError&&MsgError!=''" class="alert alert-danger element-center"
                                 ng-bind-html="MsgError">
                                <%--{{MsgError}}--%>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer" id="btn-act-footer">
                    <button class="btn btn-default btn-sm" ng-click="cancel()">Cancelar</button>
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                            id="btn-def-ck"
                            ng-click="submit('#FormCatId', '<c:url value='/supervisor/framingMeeting/attendanceFramingMeeting.json' />')">
                        Guardar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

