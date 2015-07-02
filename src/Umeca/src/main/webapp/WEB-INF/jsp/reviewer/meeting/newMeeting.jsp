<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:500px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Agregar Entrevista</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
                        <br />
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
                                       data-val-length-max="50" data-val-length-min="3" ng-init="name=''"
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
                                       data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres" ng-init="lastNameP=''"
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
                                       data-val-required="El apellido materno es un campo requerido" ng-init="lastNameM=''"
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
                                <br/><small>(A&ntilde;o/Mes/D&iacute;a) Ej. (1978/08/30)</small>
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
                    </form>
                    <br />
                    <div class="row"  ng-show="MsgError">
                        <div class="col-xs-12">
                            <div class="alert alert-danger element-center"  ng-bind-html="MsgError">
                                <%--{{MsgError}}--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true || m.isAccepted == false"
                          ng-click="submitRedirect('#FormCatId','<c:url value="/reviewer/meeting/doNewMeeting.json"/>');">
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
