<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row" ng-controller="employeeGeneralDataController">
    <div class="blocker" ng-show="WaitFor==true">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>

    <div class="col-xs-10 col-xs-offset-1" ng-init='gd=${generalData};'>

        <div class="row element-center">
            <h2><i class="purple glyphicon glyphicon-user bigger-100 element-center"></i> &nbsp;Datos generales</h2>
        </div>
        <br/>

        <div class="row">
            <div ng-show="MsgSuccess&&MsgSuccess!=''" class="alert alert-success element-center success-font">
                <span ng-bind-html="MsgSuccess"></span>
            </div>

            <div ng-show="MsgError&&MsgError!=''" class="alert alert-danger element-center error-font">
                <span ng-bind-html="MsgError"></span>
            </div>
        </div>

        <div class="row">
            <form id="FormGeneralData" name="FormGeneralData" class="form-horizontal" role="form">
                <input type="hidden" name="idEmployee" value="{{gd.idEmployee}}"/>

                <%--<div class="col-xs-12">--%>

                <div class="widget-box">
                    <div class="widget-header">Informaci&oacute;n personal</div>
                    <div class="widget-body">
                        <div class="row">
                            <div class="space"></div>
                            <div class="col-xs-12">
                                <div class="col-xs-4">
                                    <label for="name">Nombre(s)</label>
                                    <br/>
                                    <input id="name" ng-model="gd.name" name="name"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           ng_change="changeName();"
                                           data-val-required="Nombre(s) es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="name"
                              data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-4">
                                    <label for="lastNameP">Apellido paterno</label>
                                    <br/>
                                    <input id="lastNameP" ng-model="gd.lastNameP" name="lastNameP"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           ng_change="changeName();"
                                           data-val-required="Apellido paterno es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="lastNameP"
                              data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-4">
                                    <label for="lastNameM">Apellido materno</label>
                                    <br/>
                                    <input id="lastNameM" ng-model="gd.lastNameM" name="lastNameM"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           ng_change="changeName();"
                                           data-val-required="Apellido materno es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="lastNameM"
                              data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="space"></div>
                            <div class="col-xs-12">
                                <div class="col-xs-4">
                                    <div><label>G&eacute;nero</label><br/>
                    <span class="field-validation-valid" data-valmsg-for="gender"
                          data-valmsg-replace="true"></span></div>
                                    <div class="radio element-center">
                                        <label>
                                            <input name="gender" class="ace" type="radio" ng-value="true"
                                                   ng-model="gd.gender"
                                                   ng-checked="gd.gender==true" data-val="true"
                                                   data-val-required="Debe seleccionar un valor">
                                            <span class="lbl">&nbsp;&nbsp;Femenino</span>
                                        </label>
                                        <br/>
                                        <label>
                                            <input name="gender" class="ace" type="radio"
                                                   ng-value="false"
                                                   ng-model="gd.gender"
                                                   ng-checked="gd.gender==false" data-val="true"
                                                   data-val-required="Debe seleccionar un valor">
                                            <span class="lbl">&nbsp;&nbsp;Masculino</span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <label for="birthDate">Fecha de nacimiento</label>

                                    <div class="input-group">
                                        <input class="form-control date-picker"
                                               id="birthDate"
                                               name="birthDate" type="text"
                                               data-date-format="yyyy/mm/dd"
                                               readonly ng-model="gd.birthDate" data-val="true"
                                               data-val-required="Fecha de nacimiento es un campo requerido"/>
                    <span class="input-group-addon">
                    <i class="icon-calendar bigger-110"></i>
                    </span>
                                    </div>
                    <span class="field-validation-valid" data-valmsg-for="birthDate"
                          data-valmsg-replace="true"></span>
                                </div>

                                <div class="col-xs-4">
                                    <label>Estado civil</label>
                                    <br/>
                                    <select class="form-control element-center" ng-model="gd.maritalStatus"
                                            ng-options="e.name for e in lstMaritalSt"
                                            ng-init='lstMaritalSt = ${lstMaritalSt};'
                                            ></select>
                                    <input type="hidden" name="maritalStatusId"
                                           value="{{gd.maritalStatus.id}}"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="space"></div>
                            <div class="col-xs-12">
                                <div class="col-xs-4">
                                    <label>Identificaci&oacute;n</label>
                                    <br/>
                                    <select class="form-control element-center" ng-model="gd.document"
                                            ng-options="e.name for e in lstDocType"
                                            ng-init='lstDocType= ${lstDocType};'
                                            ></select>
                                    <input type="hidden" name="documentId" value="{{gd.document.id}}"/>
                                    <br/>
                                    <label for="documentDesc">Descripci&oacute;n de identificaci&oacute;n</label>
                                    <br/>
                                    <input id="documentDesc" ng-model="gd.documentDesc" name="documentDesc"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           data-val-required="Descripci&oacute;n de indetificaci&oacute;n es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="documentDesc"
                              data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-4" style="padding-top: 45px;">
                                    <label for="email">Correo electr&oacute;nico</label>
                                    <br/>
                                    <input id="email" ng-model="gd.email" name="email"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           data-val-required="Correo electr&oacute;nico es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="email"
                              data-valmsg-replace="true"></span>
                                </div>

                                <div class="col-xs-4" style="padding-top: 45px;">
                                    <label for="phone">Tel&eacute;fono</label>
                                    <br/>
                                    <input id="phone" ng-model="gd.phone" name="phone"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           data-val-required="Tel&eacute;fono es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="phone"
                              data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="space"></div>
                            <div class="col-xs-12">
                                <div class="col-xs-4">
                                    <label for="certificate">C&eacute;dula profesional</label>
                                    <br/>
                                    <input id="certificate" ng-model="gd.certificate" name="certificate"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           data-val-required="C&eacute;dula profesional es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="certificate"
                              data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-4">
                                    <label for="dependents">Dependientes econ&oacute;micos</label>
                                    <br/>
                        <textarea id="dependents" class="form-control limited" name="dependents"
                                  ng-model="gd.dependents" maxlength="980"
                                  data-val="true"
                                  data-val-required="Dependientes econ&oacute;micos es un campo requerido">
                        </textarea>
                        <span class="field-validation-valid" data-valmsg-for="dependents"
                              data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>

                <%--<br/>--%>

                <div class="widget-box">
                    <div class="widget-header">Informaci&oacute;n laboral UMECA</div>
                    <div class="widget-body">
                        <div class="row">
                            <div class="space"></div>
                            <div class="col-xs-12">
                                <div class="col-xs-4">
                                    <label for="noEmployee">N&uacute;mero de empleado</label>
                                    <br/>
                                    <input id="noEmployee" ng-model="gd.noEmployee" name="noEmployee"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           data-val-required="N&uacute;mero de empleado es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="noEmployee"
                              data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-4">
                                    <label for="datePublicServ">Fecha de inicio servidor p&uacute;blico</label>

                                    <div class="input-group">
                                        <input class="form-control date-picker"
                                               id="datePublicServ"
                                               name="datePublicServ" type="text"
                                               data-date-format="yyyy/mm/dd"
                                               readonly ng-model="gd.datePublicServ" data-val="true"
                                               data-val-required="Fecha de inicio servidor p&uacute;blico es un campo requerido"/>
                        <span class="input-group-addon">
                        <i class="icon-calendar bigger-110"></i>
                        </span>
                                    </div>
                        <span class="field-validation-valid" data-valmsg-for="datePublicServ"
                              data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-4">
                                    <label for="dateEntryUmeca">Fecha de ingreso UMECA</label>

                                    <div class="input-group">
                                        <input class="form-control date-picker"
                                               id="dateEntryUmeca"
                                               name="dateEntryUmeca" type="text"
                                               data-date-format="yyyy/mm/dd"
                                               readonly ng-model="gd.dateEntryUmeca" data-val="true"
                                               data-val-required="Fecha de ingreso UMECA es un campo requerido"/>
                        <span class="input-group-addon">
                        <i class="icon-calendar bigger-110"></i>
                        </span>
                                    </div>
                        <span class="field-validation-valid" data-valmsg-for="dateEntryUmeca"
                              data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="space"></div>
                            <div class="col-xs-12">
                                <div class="col-xs-4">
                                    <div><label>Comisionado</label><br/>
                        <span class="field-validation-valid" data-valmsg-for="commissioner"
                              data-valmsg-replace="true"></span></div>
                                    <div class="radio element-center">
                                        <label>
                                            <input name="commissioner" class="ace" type="radio" ng-value="true"
                                                   ng-model="gd.commissioner"
                                                   ng-checked="gd.commissioner==true" data-val="true"
                                                   data-val-required="Debe seleccionar un valor">
                                            <span class="lbl">&nbsp;&nbsp;S&iacute;</span>
                                        </label>
                                        <br/>
                                        <label>
                                            <input name="commissioner" class="ace" type="radio"
                                                   ng-value="false"
                                                   ng-model="gd.commissioner"
                                                   ng-checked="gd.commissioner==false" data-val="true"
                                                   data-val-required="Debe seleccionar un valor">
                                            <span class="lbl">&nbsp;&nbsp;No</span>
                                        </label>
                                    </div>
                                </div>
                                <div class="col-xs-4">
                                    <label for="noImss">N&uacute;mero IMSS</label>
                                    <br/>
                                    <input id="noImss" ng-model="gd.noImss" name="noImss"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           data-val-required="N&uacute;mero IMSS es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="noImss"
                              data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-4">
                                    <label for="appointment">Nombramiento</label>
                                    <br/>
                                    <input id="appointment" ng-model="gd.appointment" name="appointment"
                                           type="text"
                                           class="input-xxlarge"
                                           data-val="true"
                                           data-val-required="Nombramiento es un campo requerido"/>
                                    <br/>
                        <span class="field-validation-valid" data-valmsg-for="appointment"
                              data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>
                <%--<br/>--%>

                <div class="widget-box">
                    <div class="widget-header">Direcci&oacute;n</div>
                    <div class="widget-body">
                        <div class="row" style="padding: 5%">
                            <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
                        </div>
                    </div>
                </div>

                <%--</div>--%>

                <div class="col-xs-12 modal-footer element-right">
                <span class="btn btn-default btn-sm btn-primary"
                      ng-click="submitGeneralData('#FormGeneralData','<c:url value="/humanResources/digitalRecord/doUpsertGeneralData.json"/>')">
                Guardar
                </span>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    var date = new Date();
    date.setFullYear(date.getFullYear() - 18);
    $('#birthDate').datepicker({autoclose: true, endDate: date}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>


