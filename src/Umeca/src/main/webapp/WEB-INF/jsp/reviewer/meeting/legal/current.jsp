<style>
    .width-100{
        max-width: none !important;
        width: 100% !important;
    }
</style>
<form id="FormCurrentLegalId" name="FormCurrentLegalId" class="form-horizontal" role="form">
<div class="row">
    <div class="col-xs-10  col-xs-offset-1">
        <div class="row element-center">
            <h2><i class="green  icon-legal  bigger-150"></i> &nbsp;Proceso actual</h2>
        </div>
        <br/>
        <div class="row">
            <div ng-show="msgExitoCurrent" class="alert alert-success element-center success-font">
           <span ng-bind-html="msgExitoCurrent"></span>
            </div>
        </div>
        <br/>

                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/crime.jsp" %>


                              <br/>
        <div class="widget-box">
            <div class="widget-header">
                <input type="hidden" ng-update-hidden ng-model="haveCoDependant" ng-init="haveCoDependant = ${haveCoDependant==null? false: haveCoDependant};" name='haveCoDependant'>
                <h5>&nbsp;<input type="checkbox" ng-model="haveCoDependant">&nbsp;Detenci&oacute;n de coimputados</h5>
            </div>
            <div class="widget-body" ng-show="haveCoDependant == true">
                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <br/>
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/coDependant.jsp" %>

                    </div>
                </div>
            </div>
        </div>
        <br/>
        <div class="widget-box">
            <div class="widget-header">
                <h5>&nbsp;Informaci&oacute;n de la v&iacute;ctima</h5>
            </div>
            <div class="widget-body">
                <div class="row">
                    <div class="row">
                        <%@ include file="/WEB-INF/jsp/victim/index.jsp" %>
                    </div>
                    <br/>
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                            Observaciones <br/>
                                <br/>
                            <textarea class="width-100" ng-model="m.additionalInfo" id="additionalInfo" name="additionalInfo"
                                      data-val="true" ng-init='m.additionalInfo="${additionalInfo==null?'':additionalInfo}";'
                                      data-val-length-max="300" data-val-length-min="3" data-val-length ="Debe tener entre 1 y m&aacute;ximo 1000 caracteres." >
                            </textarea>
                                <span class="field-validation-valid" data-valmsg-for="additionalInfo" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                </div>
                <br/>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-xs-3 element-left">Comportamiento antes de detenci&oacute;n:<br/>
                <label class="info-example">(resistencia, persecuci&oacute;n, violencia, etc.)</label>
            </div>
            <div class="col-xs-9">
                <textarea class="form-control" name="behaviorDetention" id="behaviorDetention" ng-model="m.behaviorDetention"
                          data-val="true"  data-val-required="El comportamiento es un campo requerido"
                          ng-init='m.behaviorDetention="${behaviorDetention==null?'':behaviorDetention}";'
                          data-val-length-max="500" data-val-length-min="5" data-val-length ="Debe tener al menos 5 y m&aacute;ximo 500 caracteres."></textarea>
                <span class="field-validation-valid" data-valmsg-for="behaviorDetention" data-valmsg-replace="true"></span>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-xs-3 element-left">Lugar de detenci&oacute;n:</div>
            <div class="col-xs-9">
                <textarea class="form-control" name="placeDetention"  ng-model="m.placeDetention" id="placeDetention"
                          ng-init='m.placeDetention="${placeDetention==null?'':placeDetention}";'
                          data-val="true"  data-val-required="El lugar de detenci&oacute;n es un campo requerido"
                          data-val-length-max="255" data-val-length-min="1" data-val-length ="Debe tener al menos 1 y m&aacute;ximo 255 caracteres."></textarea>
                <span class="field-validation-valid" data-valmsg-for="placeDetention" data-valmsg-replace="true"></span>
            </div>
        </div>



</div>
</div>
    <div class="modal-footer" ng-show="managereval == false">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormCurrentLegalId', '<c:url value="/reviewer/meeting/savePartialCurrent.json?idCase=${idCase}"/>');">
                        <span class="glyphicon glyphicon-cloud-upload"></span>
                          Guardar
                    </span>
    </div>
</form>
