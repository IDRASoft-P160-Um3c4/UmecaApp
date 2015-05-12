<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormDetainedId");

        var today = new Date();

        $('.date-picker').datepicker({
            autoclose: true,
            endDate: today
        }).on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $('#initTime').timepicker({
            minuteStep: 1,
            showSeconds: true,
            showMeridian: false
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

    })
    ;
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:40%" ng-controller="detentionRecordController">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Registrar puesta a disposici&oacute;n
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormDetainedId" name="FormDetainedId" class="form-horizontal" role="form">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header">Datos generales</div>
                                    <div class="widget-body">
                                        <div class="row">
                                            <div class="col-xs-12">

                                                <div class="col-xs-12">
                                                    <br/>

                                                    <div ng-show="MsgError!=''"
                                                         class="alert alert-danger element-center"
                                                         ng-bind-html="MsgError">
                                                    </div>
                                                </div>
                                                <br/>

                                                <div id="divDetained">
                                                    <div class="col-xs-12">
                                                        <div class="col-xs-12">
                                                            <label>Nombre</label>
                                                            <br/>
                                                            <input id="name" ng-model="detained.name"
                                                                   name="name"
                                                                   maxlength="150"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Nombre es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="name"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6">
                                                            <label>Apellido paterno</label>
                                                            <br/>
                                                            <input id="lastNameP" ng-model="detained.lastNameP"
                                                                   name="lastNameP"
                                                                   type="text"
                                                                   maxlength="150"
                                                                   class="input-xxlarge"/>
                                                        </div>

                                                        <div class="col-xs-6">
                                                            <label>Apellido materno</label>
                                                            <br/>
                                                            <input id="lastNameM" ng-model="detained.lastNameM"
                                                                   name="lastNameM"
                                                                   type="text"
                                                                   maxlength="150"
                                                                   class="input-xxlarge"/>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6">
                                                            <label>Edad</label>
                                                            <br/>
                                                            <input id="age" ng-model="detained.age"
                                                                   name="age"
                                                                   type="text"
                                                                   maxlength="2"
                                                                   class="input-large"
                                                                   data-val-regex-pattern="([0-9]{2})"
                                                                   data-val-regex="S&oacute;lo puede contener n&uacute;meros y un punto"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="age"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <label>Carpeta de investigaci&oacute;n:</label>
                                                            <input id="idFolder" ng-model="detained.idFolder"
                                                                   name="idFolder"
                                                                   type="text"
                                                                   maxlength="15"
                                                                   class="input-large"/>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6">
                                                            <label>Fecha de inicio</label>
                                                            <br/>

                                                            <div class="input-group">
                                                                <input id="initDate" class="form-control date-picker"
                                                                       name="initDate" type="text"
                                                                       data-date-format="yyyy/mm/dd"
                                                                       readonly
                                                                       ng-model="detained.initDate" data-val="true"
                                                                       data-val-required="Fecha de inicio es un campo requerido"/>
                                                                    <span class="input-group-addon">
                                                                        <i class="icon-calendar bigger-110"></i>
                                                                    </span>
                                                            </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="initDate"
                                                              data-valmsg-replace="true"></span>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <label>Hora de inicio</label>

                                                            <div class="input-group bootstrap-timepicker">
                                                                <input id="initTime" name="initTime"
                                                                       ng-model="detained.initTime"
                                                                       readonly
                                                                       type="text"
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

                                                    <div class="col-xs-12">
                                                        <div class="col-xs-6">
                                                            <label>Distrito</label>
                                                            <br/>
                                                            <select id="district" class="form-control element-center"
                                                                    ng-model="detained.district"
                                                                    ng-options="e.name for e in lstDistrict"
                                                                    ng-init='lstDistrict = ${lstDistrict};'></select>
                                                            <input type="hidden" name="districtId"
                                                                   value="{{detained.district.id}}"/>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <label>Unidad de investigaci&oacute;n</label>
                                                            <br/>
                                                            <input id="investigationUnit"
                                                                   ng-model="detained.investigationUnit"
                                                                   name="investigationUnit"
                                                                   type="text"
                                                                   maxlength="150"
                                                                   class="input-xxlarge"/>
                                                            <br/>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <div class="col-xs-12">
                                                            <label>Presentado por</label>
                                                            <br/>
                                                            <input id="crime"
                                                                   ng-model="detained.crime"
                                                                   name="crime"
                                                                   type="text"
                                                                   maxlength="250"
                                                                   class="input-xxlarge"/>
                                                        </div>

                                                    </div>

                                                    <div class="col-xs-12">
                                                        <div class="col-xs-12">
                                                            <label>Comentarios</label>
                                                            <br/>
                                                            <textarea class="input-xxlarge form-control limited"
                                                                      name="comments"
                                                                      ng-model="detained.comments"
                                                                      maxlength="500">
                                                            </textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitDetained('#FormDetainedId', '<c:url value="/detentionRecord/doUpsertDetention.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>