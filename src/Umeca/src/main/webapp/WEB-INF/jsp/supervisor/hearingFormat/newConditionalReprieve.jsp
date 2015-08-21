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
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Agregar caso
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
                        <br/>

                        <div class="row">
                            <div class="col-xs-12">
                                <label>Ingrese la informaci&oacute;n requerida para generar un nuevo caso.</label>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Nombre:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres"
                                       data-val-required="El nombre es un campo requerido"
                                       data-val-length-max="50" data-val-length-min="3"
                                       id="name" name="name" ng-model="name"
                                       type="text"/>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="name"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Apellido paterno:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres"
                                       data-val-length-max="50" data-val-length-min="3"
                                       data-val-required="El apellido paterno es un campo requerido"
                                       id="lastNameP" name="lastNameP"
                                       type="text" ng-model="lastNameP"/>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="lastNameP"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Apellido materno:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres"
                                       data-val-length-max="50" data-val-length-min="3"
                                       data-val-required="El apellido materno es un campo requerido"
                                       id="lastNameM" name="lastNameM"
                                       type="text" ng-model="lastNameM"/>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="lastNameM"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-5 element-left">
                                Fecha de nacimiento:
                                <br/><small>(A&ntilde;o/Mes/D&iacute;a) Ej. (1980/11/30)</small>
                            </div>
                            <div class="col-xs-7">
                                <div class="input-group">
                                    <input class="form-control date-picker" type="text"
                                           data-date-format="yyyy/mm/dd"
                                           data-val="true"
                                           data-val-required="La fecha de nacimiento es un campo requerido"
                                           id="birthDate" name="birthDate" ng-model="birthDate"/>
											<span class="input-group-addon">
														<i class="icon-calendar bigger-110"></i>
											</span>
                                </div>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="birthDate"
                                      data-valmsg-replace="true"></span>
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
                                                       data-val-required="El g?nero es un campo requerido" id="genero"
                                                       value="true"
                                                       ng-model="gen">
                                                <span class="lbl">Femenino</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="radio">
                                            <label>
                                                <input class="ace" type="radio" value="false" ng-model="gen"
                                                       ng-checked="gen==false"
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
                                Carpeta judicial:
                            </div>
                            <div class="col-xs-7">
                                <input class="form-control" type="text"
                                       data-val-length="Debe tener al menos 1 y m&aacute;ximo 15 caracteres"
                                       data-val-length-max="15" data-val-length-min="1"
                                       data-val="true" data-val-required="Carpeta judicial es un campo requerido"
                                       id="idJudicial" name="idJudicial" ng-model="m.idJudicial"/>
                            </div>
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="idJudicial"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                    </form>
                    <br/>

                    <div class="row element-center" ng-show="showLabels=='true'">
                        <div class="col-xs-6 col-xs-12 pricing-box ">
                            <div class="widget-box">
                                <div class="widget-header header-color-red">

                                    <h5 class="bigger lighter"><i
                                            class="icon-warning-sign icon-animated-wrench bigger-120"></i>&nbsp;
                                        El imputado ya se encuentra registrado en la <strong>UMECA</strong></h5>
                                </div>

                                <div class="widget-body">
                                    <div class="widget-main">
                                        <ul class="list-unstyled spaced2">
                                            <li>
                                                <i class="icon-circle-blank green"></i>
                                                Carpeta de investiaci&oacute;n : <strong>{{folderShow}}</strong>
                                                <br/>
                                                <i class="icon-circle-blank green"></i>
                                                Carpeta judicial : <strong>{{mpShow}}</strong>
                                            </li>
                                            <li class="text-danger">
                                                <i class="icon-warning-sign icon-animated-wrench bigger-120"></i>
                                                Debe registrar la audiencia de revocaci&oacute;n y seleccionar la
                                                obligaci&oacute;n
                                                prisi&oacute;n preventiva.
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError" class="alert alert-danger element-center" ng-bind-html="MsgError">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-show="showLabels!='true'"
                          ng-click="submitNewCase('#FormCatId','<c:url value="/supervisor/hearingFormat/doNewCase.json"/>');">
                          Guardar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-show="showLabels=='true'"
                          ng-click="goToFormatsCase('<c:url value="/supervisor/hearingFormat/indexFormats.html"/>');">
                          Registrar formato de audiencia
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var date = new Date();
    date.setFullYear(date.getFullYear() - 18);
    $('.date-picker').datepicker({autoclose: true, endDate: date}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>