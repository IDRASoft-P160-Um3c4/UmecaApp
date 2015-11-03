<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });

</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/meetingCtrl.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:500px">
            <div class="modal-content">
                <div class="modal-header" ng-hide="m.isNegation">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Agregar Entrevista</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">

                        <div ng-hide="m.isNegation">
                            <div class="row">
                                <div class="col-xs-12">
                                    <label>Ingrese la informaci&oacute;n requerida para poder generar un nuevo n&uacute;mero de expediente:</label>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-5 element-left">
                                    Nombre:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" data-val="true"
                                           data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres"
                                           data-val-required="El nombre es un campo requerido"
                                           data-val-length-max="50" data-val-length-min="3" ng-init='name="${(f.firstName == null) ? '' : f.firstName}"'
                                           id="name" name="name" ng-model="name"
                                           type="text"/>
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-5 element-left">
                                    Apellido paterno:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" data-val="true"
                                           data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres" ng-init='lastNameP="${(f.lastNameP == null) ? '' : f.lastNameP}"'
                                           data-val-length-max="50" data-val-length-min="3"
                                           data-val-required="El apellido paterno es un campo requerido"
                                           id="lastNameP" name="lastNameP"
                                           type="text" ng-model="lastNameP"/>
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="lastNameP" data-valmsg-replace="true"></span>
                                </div>
                            </div>

                            <br/>
                            <div class="row">
                                <div class="col-xs-5 element-left">
                                    Apellido materno:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" data-val="true"
                                           data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres"
                                           data-val-length-max="50" data-val-length-min="3"
                                           data-val-required="El apellido materno es un campo requerido" ng-init='lastNameM="${(f.lastNameM == null) ? '' : f.lastNameM }"'
                                           id="lastNameM" name="lastNameM"
                                           type="text" ng-model="lastNameM"/>
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="lastNameM" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-5 element-left">
                                    Fecha de nacimiento:
                                    <br/><small>(A&ntilde;o/Mes/D&iacute;a) Ej. (1980/08/30)</small>
                                </div>
                                <div class="col-xs-7">
                                    <div class="input-group">
                                        <input class="form-control date-picker" type="text" data-date-format="yyyy/mm/dd"
                                               data-val="true" data-val-required="La fecha de nacimiento es un campo requerido" ng-init="birthDate=''"
                                               id="birthDate" name="birthDate" ng-model="birthDate"/>
											<span class="input-group-addon">
														<i class="icon-calendar bigger-110" ></i>
											</span>
                                    </div>
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="birthDate" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-5 element-left">
                                    G&eacute;nero:
                                </div>
                                <div class="col-xs-7">
                                    <div class="row" ng-init="gen=false">
                                        <div class="col-xs-6">
                                            <div class="radio">
                                                <label>
                                                    <input class="ace" type="radio" ng-checked="gen==true" name="gender"
                                                           data-val-required="El g?nero es un campo requerido" id="genero" value="true"
                                                           ng-model="gen">
                                                    <span class="lbl">Femenino</span>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="radio">
                                                <label>
                                                    <input class="ace" type="radio" value="false" ng-model="gen" ng-checked="gen==false"
                                                           name="gender">
                                                    <span class="lbl">Masculino</span>
                                                </label>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-5 element-left">
                                    Carpeta de investigaci&oacute;n:
                                </div>
                                <div class="col-xs-7">
                                    <input class="form-control" type="text"    data-val-length="Debe tener al menos 1 y m&aacute;ximo 15 caracteres"
                                           data-val-length-max="15" data-val-length-min="1"
                                           data-val="true" data-val-required="La carpeta de investigaci&oacute;n es un campo requerido" ng-init="m.idFolder=''"
                                           id="meeting.caseDetention.idFolder" name="meeting.caseDetention.idFolder" ng-model="m.idFolder"/>
                                </div>
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="meeting.caseDetention.idFolder" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>
                            <div class="row" ng-controller="newMeetController" ng-init='lstDistrict = ${lstDistrict}; init();'>
                                <div class="col-xs-5 element-left">
                                    Distrito judicial
                                </div>
                                <div class="col-xs-7">
                                    <select id="district"
                                            ng-model="m.district"
                                            ng-options="e.name for e in lstDistrict"></select>
                                </div>
                                <input type="hidden" name="meeting.district.id" value="{{m.district.id}}"/>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-12 text-danger">
                                    <i class="icon-warning-sign icon-animated-wrench bigger-120"></i>&nbsp;
                                    La carpeta de investigaci&oacute;n no podr&aacute; ser modificada durante la entrevista.
                                </div>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-xs-12 element-center">
                                    <input type="checkbox" ng-model="m.isAccepted" id="isAccepted"
                                           ng-init="m.isAccepted= false">
                                    <label class="info-note" for="isAccepted">&iquest;El imputado acepta que se realice la entrevista de riesgos procesales?</label>
                                </div>
                            </div>
                            <input type="hidden" ng-update-hidden ng-model="isFromFormulation" name="isFromFormulation" ng-init='isFromFormulation = "${(isFromFormulation == null) ? false : isFromFormulation}"'>
                            <div class="row">
                                <div class="col-xs-12 element-center">
                                    <input type="checkbox" ng-model="m.isFromFormulation" id="isFormulation"
                                           ng-change = "isFromFormulation = m.isFromFormulation"
                                           ng-init="m.isFromFormulation= ${isFromFormulation ==null ? false: isFromFormulation}">
                                    <label class="info-note" for="isFormulation">&iquest;Se trata de una entrevista de formulaci&oacute;n?</label>
                                </div>
                            </div>
                            <br/>
                            <br/>
                        </div>




                        <div class="panel panel-info" ng-show="m.isNegation" style="margin-bottom: auto;">
                            <div class="panel-heading element-center">
                                <div class="panel-title">Negaci&oacute;n de entrevista</div>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-12 element-center">
                                        <p>&iquest;Est&aacute; seguro que desea terminar la entrevista de riesgos procesales?</p>
                                        <br/>
                                        <p>Raz&oacute;n de negaci&oacute;n</p>
                                        <textarea rows="4" cols="50" data-val="true"
                                                  data-val-length="Debe tener al menos 3 y m&aacute;ximo 500 caracteres"
                                                  data-val-required="Es necesario escribir la raz&oacute;n"
                                                  data-val-length-max="500" data-val-length-min="3"ng-init="reason=''"
                                                  id="reason" name="meeting.declineReason" ng-model="reason">

                                        </textarea>
                                    </div>
                                    <div class="col-xs-11 col-xs-offset-1">
                                        <span class="field-validation-valid" data-valmsg-for="meeting.declineReason" data-valmsg-replace="true"></span>
                                    </div>

                                </div>
                            </div>
                            <div class="panel-footer element-right">
                                <span class="btn btn-default btn-sm" ng-click="cancel()">
                                    Cancelar
                                </span>
                                <span class="btn btn-primary btn-sm" ng-disabled="checked" ng-click="checked = true; submitRedirect('#FormCatId','<c:url value="/reviewer/meeting/doNewMeeting.json"/>');">
                                    Terminar
                                </span>
                            </div>
                        </div>

                    </form>
                    <br ng-show="MsgError" />
                    <div class="row" ng-show="MsgError">
                        <div class="col-xs-12">
                            <div class="alert alert-danger element-center"  ng-bind-html="MsgError">
                                <%--{{MsgError}}--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" ng-hide="m.isNegation">
                    <span class="btn btn-danger btn-sm" ng-disabled="WaitFor==true || m.isAccepted == true"
                          ng-click="submitNegation('#FormCatId');">
                          El imputado niega la entrevista
                    </span>
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-primary btn-sm" ng-disabled="WaitFor==true || m.isAccepted == false"
                          ng-show = "m.isFromFormulation == false"
                          ng-click="submitRedirect('#FormCatId','<c:url value="/reviewer/meeting/doNewMeeting.json"/>');">
                          Continuar
                    </span>
                     <span class="btn btn-primary btn-sm" ng-disabled="WaitFor==true || m.isAccepted == false"
                           ng-show = "m.isFromFormulation == true"
                           ng-click="submitRedirect('#FormCatId','<c:url value="/reviewer/formulation/doNewMeeting.json"/>');">
                          Continuar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var date=new Date();
    date.setFullYear(date.getFullYear()-18);
    $('.date-picker').datepicker({autoclose:true, endDate:date}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });
</script>
