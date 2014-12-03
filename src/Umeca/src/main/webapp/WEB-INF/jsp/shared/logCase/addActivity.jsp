<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertLogCaseController" ng-cloak>
        <div class="modal-dialog" style="width:700px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-asterisk "></i>&nbsp;&nbsp;Agregar actividad espont&aacute;nea</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal" role="form">
                       <br/>
                        <div class="row">
                            <div class="col-xs-3">
                               T&iacute;tulo:
                            </div>
                            <div class="col-xs-7">
                                <input class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m&aacute;ximo 500 caracteres"
                                       data-val-length-max="500" data-val-length-min="1" data-val-required="El t&iacute;tulo es un campo requerido"
                                       type="text" value="" ng-model="m.title" id="title" name="title">
                                <br/>
                                <span class="field-validation-valid" data-valmsg-for="title" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-3">
                                Detalles:
                            </div>
                            <div class="col-xs-7">
                                <textarea class="form-control" data-val="true" data-val-length="Debe tener al menos 1 y m&aacute;ximo 1500 caracteres"
                                       data-val-length-max="1500" data-val-length-min="1" data-val-required="El detalle es un campo requerido"
                                       ng-model="m.resume" id="resume" name="resume"></textarea>
                                <br/>
                                <span class="field-validation-valid" data-valmsg-for="resume" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                    </form>
                    <br />
                    <div class="row">
                        <div class="col-xs-12">
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
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormCatId', '<c:url value="/shared/logCase/doNewLogSpontaneous.json?id=${caseId}"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>



