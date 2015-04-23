<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {

        window.showModalFormDlg("#dlgUpModalId", "#FormEmpSchId");

        $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });

        $('.umeca-time-picker').timepicker({
            minuteStep: 1,
            showSeconds: true,
            showMeridian: false
        }).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px" ng-controller="employeeScheduleController"
             ng-init='empSch=${empSch}; lstDays=${lstDays}; lstSelDays=${lstSelDays};'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Agregar horario de trabajo
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="widget-box">
                                <div class="widget-header">Agregar horario de trabajo</div>
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

                                            <div id="divEmpSch">
                                                <form id="FormEmpSchId" class="form-horizontal" role="form">
                                                    <input type="hidden" name="id" value="{{empSch.id}}">
                                                    <input type="hidden" name="scheduleDays" value="{{lstDays}}"/>

                                                    <div class="col-xs-12">
                                                        <div class="col-xs-6">
                                                            <label>Nombre</label>
                                                            <br/>
                                                            <input id="name" ng-model="empSch.name"
                                                                   name="name"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Nombre es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="name"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <label>Descripcio&oacute;n</label>
                                                            <br/>
                                                            <textarea class="input-xxlarge form-control limited"
                                                                      name="description"
                                                                      ng-model="empSch.description"
                                                                      maxlength="255" data-val="true"
                                                                      data-val-required="Descripci&oacute;n es un campo requerido">
                                                            </textarea>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="description"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                </form>
                                                <div class="col-xs-12">
                                                    <div class="widget-box">
                                                        <div class="widget-header">D&iacute;as y horario</div>
                                                        <div class="widget-body">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <br/>

                                                                    <div ng-show="MsgErrorDay!=undefined&&MsgErrorDay!=''"
                                                                         class="alert alert-danger element-center"
                                                                         ng-bind-html="MsgErrorDay">
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12 element-center">
                                                                    <div class="col-xs-4">
                                                                        <label><b>D&iacute;a</b></label>

                                                                        <div class="checkbox">
                                                                            <label>
                                                                                <input class="ace"
                                                                                       type="checkbox"
                                                                                       ng-model="isAllDaySelected"
                                                                                       ng-change="selAllDays();">
                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Toda la semana</span>
                                                                            </label>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-xs-4">
                                                                        <label><b>Entrada</b></label>
                                                                        <br/>

                                                                        <div class="input-group bootstrap-timepicker">
                                                                            <input readonly
                                                                                   ng-model="grlStart"
                                                                                   type="text"
                                                                                   class="form-control umeca-time-picker"/>
                                                                                        <span class="input-group-addon"><i
                                                                                                class="icon-time bigger-110"></i></span>
                                                                        </div>

                                                                        <div class="checkbox">
                                                                            <label>
                                                                                <input class="ace"
                                                                                       ng-model="isAllStartSelected"
                                                                                       ng-change="setAllStart();"
                                                                                       type="checkbox">
                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Aplicar a todo</span>
                                                                            </label>
                                                                        </div>
                                                                    </div>


                                                                    <div class="col-xs-4">
                                                                        <label><b>Salida</b></label>
                                                                        <br/>

                                                                        <div class="input-group bootstrap-timepicker">
                                                                            <input readonly
                                                                                   ng-model="grlEnd"
                                                                                   type="text"
                                                                                   class="form-control umeca-time-picker"/>
                                                                                        <span class="input-group-addon"><i
                                                                                                class="icon-time bigger-110"></i></span>
                                                                        </div>

                                                                        <div class="checkbox">
                                                                            <label>
                                                                                <input class="ace"
                                                                                       ng-model="isAllEndSelected"
                                                                                       ng-change="setAllEnd();"
                                                                                       type="checkbox">
                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;Aplicar a todo</span>
                                                                            </label>
                                                                        </div>
                                                                        <br/>
                                                                        <br/>
                                                                    </div>

                                                                </div>

                                                                <div class="col-xs-12" ng-repeat="day in lstDays">
                                                                    <div class="col-xs-4">
                                                                        <div class="checkbox">
                                                                            <label>
                                                                                <input class="ace"
                                                                                       ng-model="lstDays[$index].isSel"
                                                                                       ng-checked="lstDays[$index].isSel==true"
                                                                                       type="checkbox"
                                                                                       ng-change="updateStEd($index);">
                                                                                <span class="lbl col-xs-10">&nbsp;&nbsp;{{day.name}}</span>
                                                                            </label>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-xs-4">
                                                                        <div class="input-group bootstrap-timepicker">
                                                                            <input ng-model="lstDays[$index].start"
                                                                                   readonly
                                                                                   type="text"
                                                                                   class="form-control umeca-time-picker"/>
                                                                                        <span class="input-group-addon"><i
                                                                                                class="icon-time bigger-110"></i></span>
                                                                            <br/>
                                                                        </div>
                                                                    </div>

                                                                    <div class="col-xs-4">
                                                                        <div class="input-group bootstrap-timepicker">
                                                                            <input ng-model="lstDays[$index].end"
                                                                                   readonly
                                                                                   type="text"
                                                                                   class="form-control umeca-time-picker"
                                                                                   data-val="true"/>
                                                                                        <span class="input-group-addon"><i
                                                                                                class="icon-time bigger-110"></i></span>
                                                                            <br/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
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
                    </div>
                    <br/>
                    <%--</form>--%>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitUpsertEmployeeSch('#FormEmpSchId', '<c:url value="/humanResources/employeeSchedule/doUpsertEmployeeSchedule.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>