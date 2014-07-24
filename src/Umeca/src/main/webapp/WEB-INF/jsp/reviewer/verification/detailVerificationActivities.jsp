<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalIdActivities", "#FormVerifUpsertIdActivities");
    });
</script>
<style>
    .chosen-container {
        width: 100%;!important

    }
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/chosen.min.css"/>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/chosen.jquery.min.js"></script>

<div  ng-init="idCase = ${idCase}; idSource=${idSource};">
    <div id="dlgUpModalIdActivities" class="modal fade" ng-controller="upsertVerificationController" ng-cloak
         data-backdrop="static">
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-danger ">
                        <h4 class="element-center">&nbsp;&nbsp;Dato proporcionado por la fuente</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="row" ng-controller="innerActivitiesController">
                        <div class="col-xs-10 col-xs-offset-1">
                            <form id="FormVerifUpsertIdActivities" name="FormVerifUpsertIdActivities" ng-submit="submit('#FormVerifUpsertIdActivities')"
                                  class="form-horizontal"
                                  role="form">
                                <div class="row">
                                    <div class="col-xs-3 element-left">
                                        &iquest;Qu&eacute; actividades realiza?:
                                    </div>
                                    <div class="col-xs-9 element-left">
                                        <input name="activities" ng-model="activities" ng-update-hidden type="hidden">
                                        <select multiple="" class="width-100 chosen-select" ng-model="activityModel" data-placeholder="..."
                                                ng-init='lstActivity = ${lstActivity};'
                                                id="slctActivityV" ng-change="matchActivities()"
                                                ng-options="ac as ac.name for ac in lstActivity">
                                        </select>
                                    </div>
                                </div>
                                <br/>

                                <div class="row">
                                    <div ng-repeat="activity in activityModel">
                                        <div ng-show="activity.specification==true">
                                            <div class="col-xs-3">
                                                Especif&iacute;que activiades {{activity.name}}:
                                            </div>
                                            <div class="col-xs-9">
                                                <input class="form-control" data-val="true"
                                                       data-val-length="Debe tener al menos 3 y m&aacute;ximo 255 caracteres"
                                                       data-val-length-max="255" data-val-length-min="3"
                                                       data-val-required="La especificaci&oacute;n de actividades {{activity.name}} es un campo requerido"
                                                       type="text" value="" ng-model="specification[activity.name]"
                                                       id="specification{{activity.name}}" name="specification{{activity.name}}"
                                                       ng-change="matchActivities()"><br/>
                <span class="field-validation-valid" data-valmsg-for="specification{{activity.name}}"
                      data-valmsg-replace="true"></span>
                                                <br/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br/>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <br/>
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
                    <input type="hidden" ng-init="urlToGoSave = '<c:url value="/reviewer/verification/saveFieldVerification.json"/>'" ng-model="urlToGoSave">
                    <span class="btn btn-danger btn-danger btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormVerifUpsertId');"> Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>



