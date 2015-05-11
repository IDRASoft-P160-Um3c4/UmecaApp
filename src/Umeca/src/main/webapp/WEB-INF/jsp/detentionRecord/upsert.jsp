<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormDetained");
    });
</script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width: 40%" ng-controller="detentionRecordController">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Registrar
                            puesta a disposici&oacute;n
                        </h4>
                    </div>
                </div>
                <div class="modal-body">

                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1">
                            <div class="row">
                                <div class="col-xs-12">
                                    <form id="FormDetained" name="FormDetained" class="form-horizontal" role="form">
                                        <div class="row">
                                            <div ng-show="MsgError&&MsgError!=''"
                                                 class="alert alert-danger element-center"
                                                 ng-bind-html="MsgError">
                                            </div>
                                        </div>
                                        <input type="hidden" name="agreementId"/>
                                        <%--value="${agreementId}"/>--%>

                                        <div class="row">
                                            <div class="col-xs-12">

                                                <div class="col-xs-12">
                                                    <label>Nombre</label>
                                                    <input ng-model="detained.name"
                                                           name="name" type="text"
                                                           class="input-xxlarge" data-val="true"
                                                           maxlength="50"
                                                           data-val-length-max="50" data-val-length-min="5"
                                                           data-val-length="Debe tener al menos 5 y m&aacute;ximo 50 caracteres."
                                                           data-val-required="Nombre es un campo requerido"/>
                                                    <br/>
                                                    <span class="field-validation-valid" data-valmsg-for="idJudicial"
                                                          data-valmsg-replace="true"></span>
                                                    <br/>
                                                </div>
                                                <div class="col-xs-12">
                                                    <label>Apellido paterno</label>
                                                    <br/>
                                                    <input ng-model="detained.lastNameP"
                                                           maxlength="50"
                                                           name="lastNameP"
                                                           type="text"
                                                           class="input-xxlarge"/>
                                                </div>
                                                <div class="col-xs-12">
                                                    <label>Apellido materno</label>
                                                    <input ng-model="detained.lastNameM"
                                                           maxlength="50"
                                                           name="lastNameM"
                                                           type="text"
                                                           class="input-xxlarge"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-12">

                                                <div class="col-xs-4">
                                                    <label>Edad</label>
                                                    <br/>
                                                    <input ng-model="detained.age"
                                                           name="age" type="text"
                                                           class="input-large"
                                                           maxlength="2"
                                                           data-val-regex-pattern="([0-9])"
                                                           data-val-regex="Edad s&oacute;lo puede contener n&uacute;meros"/>
                                                    <br/>
                                                    <span class="field-validation-valid" data-valmsg-for="age"
                                                          data-valmsg-replace="true"></span>
                                                </div>
                                                <div class="col-xs-8">
                                                    <label>Carpeta de invvestigaci&oacute;n</label>
                                                    <br/>
                                                    <input ng-model="detained.idFolder"
                                                           maxlength="50"
                                                           name="idFolder"
                                                           type="text"
                                                           class="input-xxlarge"/>
                                                </div>

                                            </div>
                                        </div>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="col-xs-6">
                                        <label>Fecha de incio</label>

                                        <div class="input-group input-larger">
                                            <input class="form-control date-picker"
                                                   id="initDate" data-date-format="yyyy/mm/dd"
                                                   name="linkageDateStr" ng-model="detained.initDate"
                                                   type="text" readonly data-val="true"
                                                   data-val-required="Fecha de incio es un campo requerido">
                <span class="input-group-addon">
                    <i class="icon-calendar bigger-110"></i>
                </span>
                                        </div>
                         <span class="field-validation-valid" data-valmsg-for="initDate"
                               data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="col-xs-6">
                                        <label>Hora de inicio</label>

                                        <div class="input-group bootstrap-timepicker">
                                            <input id="initTime" name="initTime"
                                                   ng-model="detained.initTime"
                                                   readonly type="text"
                                                   class="form-control umeca-time-picker"
                                                   data-val="true"
                                                   data-val-required="Hora de inicio es un campo requerido"/>
                                                        <span class="input-group-addon"><i
                                                                class="icon-time bigger-110"></i></span>
                                            <br/>
                                        </div>
                        <span class="field-validation-valid" data-valmsg-for="initTime"
                              data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="col-xs-12">
                                        <label>Distrito</label>
                                        <br/>
                                        <select class="form-control element-center"
                                                ng-model="detained.district"
                                                ng-options="e.description for e in lstSupervisor"
                                                ng-init='lstDistrict= ${lstDistrict};'></select>
                                        <input type="hidden" name="districtId" value="detained.district"/>
                                    </div>
                                    <div class="col-xs-12">
                                        <label>Unidad de investigaci&oacute;n</label>
                                        <br/>
                                        <input ng-model="detained.investigationUnit"
                                               maxlength="50"
                                               name="investigationUnit"
                                               type="text"
                                               class="input-xxlarge"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-12">

                                    <div class="col-xs-12">
                                        <label>Presentado por</label>
                                        <input ng-model="detained.crime"
                                               name="crime" type="text"
                                               class="input-xxlarge"
                                               maxlength="150"/>
                                    </div>
                                    <div class="col-xs-12">
                                        <label>Comentarios</label>
                                        <br/>
                                            <textarea class="form-control limited" name="comments"
                                                      ng-model="detained.comments"
                                                      maxlength="500">
                                            </textarea>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <br/>
                        </form>
                        <br/>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" id="btn-def-ck" ng-disabled="WaitFor==true"
                          ng-click="submitDetained('#FormDetained','<c:url value="/detentionRecord/upsertDetention.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>


    </div>
</div>
</div>
