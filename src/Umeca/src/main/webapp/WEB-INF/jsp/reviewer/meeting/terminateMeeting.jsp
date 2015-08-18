<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="meetingController" ng-cloak>
        <div class="modal-dialog" style="width:600px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"></i>&nbsp;&nbsp;Terminar entrevista de riesgos</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
                        <br/>

                        <div class="row">
                            <div class="col-xs-12 element-left">
                                <label>
                                    &iquest;Est&aacute; seguro que desea terminar la entrevista de riesgos procesales?
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 element-left">

                                <div class="checkbox">
                                    <label>
                                        <input class="ace"
                                               type="checkbox"
                                               id="cancelMeeting"
                                               ng-model="cancelMeeting"
                                               name="cancelMeeting">
                                        <span class="lbl col-xs-10">&nbsp;&iquest;El imputado se niega a terminar la entrevista?</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row"  ng-if="cancelMeeting==true">
                            <div class="col-xs-12 element-center">
                                <br/>
                                <p>Raz&oacute;n de negaci&oacute;n</p>
                                        <textarea rows="4" cols="50" data-val="true"
                                                  data-val-length="Debe tener al menos 3 y m&aacute;ximo 500 caracteres"
                                                  data-val-required="Es necesario escribir la raz&oacute;n"
                                                  data-val-length-max="500" data-val-length-min="3"ng-init="reason=''"
                                                  id="reason" name="declineReason" ng-model="reason">
                                        </textarea>
                            </div>
                            <div class="col-xs-11 col-xs-offset-1">
                                <span class="field-validation-valid" data-valmsg-for="meeting.declineReason" data-valmsg-replace="true"></span>
                            </div>

                        </div>
                    </form>
                    <br/>

                    <div class="row" ng-show="MsgError">
                        <div class="col-xs-12">
                            <div class="alert alert-danger element-center" ng-bind-html="MsgError">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span ng-if="cancelMeeting!=true" class="btn btn-default btn-primary btn-sm"
                          ng-disabled="WaitFor==true || m.isAccepted == false"
                          ng-click="submit('#FormCatId, #FormSchool , #FormPersonalData, #FormLeaveCountry, #FormCommentHomeId, #FormCommentReferenceId, #FormCommentJobId, #FormSocialNetworkIndexId, #FormCommentDrugId','<c:url value="/reviewer/meeting/terminateMeeting.json"/>');">
                          Continuar
                    </span>

                    <span ng-if="cancelMeeting==true" class="btn btn-default btn-primary btn-sm"
                          ng-disabled="WaitFor==true || m.isAccepted == false"
                          ng-click="submit('#FormCatId, #FormSchool , #FormPersonalData, #FormLeaveCountry, #FormCommentHomeId, #FormCommentReferenceId, #FormCommentJobId, #FormSocialNetworkIndexId, #FormCommentDrugId','<c:url value="/reviewer/meeting/terminateMeeting.json"/>');">
                          Terminar entrevista incompleta
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>