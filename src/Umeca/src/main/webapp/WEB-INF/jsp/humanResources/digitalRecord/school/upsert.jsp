<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCourseId");
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px" ng-controller="courseController"
             ng-init='course=${course}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Agregar curso</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCourseId" name="FormCourseId" class="form-horizontal" role="form">
                        <input type="hidden" name="idEmployee" value="{{course.idEmployee}}">
                        <input type="hidden" name="id" value="{{course.id}}">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header">Agregar curso</div>
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

                                                <div id="divCourse">
                                                    <div class="col-xs-12">
                                                        <div class="col-xs-6">
                                                            <label>Nombre</label>
                                                            <br/>
                                                            <input id="name" ng-model="course.name"
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
                                                            <label>Lugar</label>
                                                            <br/>
                                                            <input id="place" ng-model="course.place"
                                                                   name="place"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Lugar es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="place"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6">
                                                            <label>Tipo</label>
                                                            <br/>
                                                            <select id="idCourseType"
                                                                    class="form-control element-center"
                                                                    ng-model="course.courseType"
                                                                    ng-init='lstCourseType = ${lstCourseType}'
                                                                    ng-change="cleanSpecs();"
                                                                    ng-options="e.name for e in lstCourseType"></select>
                                                            <input type="hidden" name="idCourseType"
                                                                   value="{{course.courseType.id}}"/>
                                                        </div>

                                                        <div class="col-xs-6">
                                                            <label>Documento obtenido</label>
                                                            <br/>
                                                            <select id="idDocType" class="form-control element-center"
                                                                    ng-model="course.docType"
                                                                    ng-init='lstDocs= ${lstSchoolDocType}'
                                                                    ng-change="cleanSpecs();"
                                                                    ng-options="e.name for e in lstDocs"></select>
                                                            <input type="hidden" name="idDocType"
                                                                   value="{{course.docType.id}}"/>
                                                        </div>

                                                    </div>
                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6"
                                                             ng-show="course.courseType.specification==true">
                                                            <label>Especifique tipo</label>
                                                            <br/>
                                                            <input id="specCourseType" ng-model="course.specCourseType"
                                                                   name="specCourseType"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Especifique tipo es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="specCourseType"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                        <div class="col-xs-6"
                                                             ng-show="course.docType.specification==true">
                                                            <label>Especifique documento</label>
                                                            <br/>
                                                            <input id="specDocType" ng-model="course.specDocType"
                                                                   name="specDocType"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Especifique documento es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="specDocType"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6">
                                                            <label>Fecha de inicio</label>
                                                            <br/>

                                                            <div class="input-group">
                                                                <input class="form-control date-picker"
                                                                       name="start" type="text"
                                                                       data-date-format="yyyy/mm/dd"
                                                                       readonly
                                                                       ng-model="course.start" data-val="true"
                                                                       data-val-required="Fecha de inicio es un campo requerido"/>
                                                                    <span class="input-group-addon">
                                                                        <i class="icon-calendar bigger-110"></i>
                                                                    </span>
                                                            </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="start"
                                                              data-valmsg-replace="true"></span>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <label>Fecha de fin</label>
                                                            <br/>

                                                            <div class="input-group">
                                                                <input class="form-control date-picker"
                                                                       name="end" type="text"
                                                                       data-date-format="yyyy/mm/dd"
                                                                       readonly
                                                                       ng-model="course.end" data-val="true"
                                                                       data-val-required="Fecha de fin es un campo requerido"/>
                                                                    <span class="input-group-addon">
                                                                        <i class="icon-calendar bigger-110"></i>
                                                                    </span>
                                                            </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="end"
                                                              data-valmsg-replace="true"></span>
                                                            <br/>
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

                    </form>
                    <br/>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitCourse('#FormCourseId', '<c:url value="/humanResources/digitalRecord/doUpsertCourse.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>