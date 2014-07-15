<div  ng-init="idCase = ${idCase}; idSource=${idSource};">
    <div id="dlgUpModalIdSchedule" class="modal fade" ng-controller="upsertScheduleVerifController" ng-cloak data-backdrop="static">
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-danger ">
                        <h4 class="element-center">&nbsp;&nbsp;Dato proporcionado por la fuente</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatIdSchedule" name="FormCatIdSchedule" ng-submit="submit('#FormCatIdSchedule')" class="form-horizontal"
                          role="form">
                           <h6> Disponibilidad</h6>
                        <div class="row" ng-controller="scheduleVerifController">
                            <div class="col-xs-10 col-xs-offset-1">

                                <%@ include file="/WEB-INF/jsp/reviewer/verification/shared/schedule.jsp" %>
                                <input type="hidden" ng-update-hidden ng-model="schString" name='sch'>
                                <div class="col-xs-9 col-xs-offset-1" ng-show ="listSchedule.length > 0">
                                    <div class="row center">
                                        <div class="col-xs-5">
                                            <h5 class="smaller lighter blue">Día</h5>
                                            <div class="hr hr-2"></div>
                                        </div>
                                        <div class="col-xs-2">
                                            <h5 class="smaller lighter blue">Inicio</h5>
                                            <div class="hr hr-2"></div>
                                        </div>
                                        <div class="col-xs-2">
                                            <h5 class="smaller lighter blue">Fin</h5>
                                            <div class="hr hr-2"></div>
                                        </div>
                                        <div class="col-xs-2">
                                            <h5 class="smaller lighter blue">Acciones</h5>
                                            <div class="hr hr-2"></div>
                                        </div>
                                    </div>
                                    <div class="row center" ng-repeat ="sch in listSchedule">
                                        <div class="col-xs-5">
                                            {{sch.day}}
                                        </div>
                                        <div class="col-xs-2">
                                            {{sch.start}}
                                        </div>
                                        <div class="col-xs-2">
                                            {{sch.end}}
                                        </div>
                                        <div class="col-xs-2">
                                            <i class="icon-trash red" style="cursor:pointer;" ng-click="deleteSchedule($index)"></i>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                            </div>
                        </div>
                        <br/>

                    </form>

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
                    <input type="hidden" ng-init="urlToGoSaveSchedule = '<c:url value="/reviewer/verification/saveScheduleVerification.json"/>'" ng-model="urlToGoSaveSchedule">
                    <span class="btn btn-danger btn-danger btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormCatIdSchedule')">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>


