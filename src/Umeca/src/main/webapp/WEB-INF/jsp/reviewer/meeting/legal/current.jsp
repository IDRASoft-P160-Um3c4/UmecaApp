<form id="FormCurrentLegalId" name="FormCurrentLegalId" class="form-horizontal" role="form">
<div class="row">
    <div class="col-xs-10  col-xs-offset-1">
        <div class="row element-center">
            <h2><i class="green  icon-legal  bigger-150"></i> &nbsp;Proceso actual</h2>
        </div>
        <br/>

                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/crime.jsp" %>


                              <br/>
        <div class="widget-box">
            <div class="widget-header">

                <input type="hidden" ng-update-hidden ng-model="haveCoDependant" name='haveCoDEfendant'>
                <h5>&nbsp;<input type="checkbox" ng-model="haveCoDependant" ng-init="haveCoDependant = false">&nbsp;Detenci&oacute;n de coimputados</h5>
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
                    <div class="col-xs-10 col-xs-offset-1">
                       <br/>
                        <div class="col-xs-8 element-left">
                            Nombre completo<br/>
                            <input type="text" class="form-control" ng-model="m.fullName" id="nameVictim" name="nameVictim"
                                   data-val="true"  data-val-required="El nombre de la v&iacute;ctima es un campo requerido"
                                   data-val-length-max="300" data-val-length-min="3" data-val-length ="Debe tener al menos 3 y m&aacute;ximo 300 caracteres."/>
                            <span class="field-validation-valid" data-valmsg-for="nameVictim" data-valmsg-replace="true"></span>
                        </div>
                        <div class="col-xs-4 element-left">
                            Relaci&oacute;n <br/>
                            <input type="hidden" ng-model="m.relId" ng-update-hidden name="relVictimId">
                            <select class="form-control element-center" ng-model="m.rel"
                                    ng-options="e.name for e in lstRelationship"
                                    ng-change="m.relId = m.rel.id"
                                    ng-init='lstRelationship = ${listRelationship};'></select>
                            <br/>
                        </div>
                        <div class="col-xs-12">
                            <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-xs-3 element-left">Comportamiento antes de detenci&oacute;n:</div>
            <div class="col-xs-9">
                <textarea class="form-control" name="behaviorDetention" id="behaviorDetention" ng-model="m.behaviorDetention"
                          data-val="true"  data-val-required="El comportamiento es un campo requerido"
                          data-val-length-max="500" data-val-length-min="5" data-val-length ="Debe tener al menos 5 y m&aacute;ximo 500 caracteres."></textarea>
                <span class="field-validation-valid" data-valmsg-for="behaviorDetention" data-valmsg-replace="true"></span>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-xs-3 element-left">Lugar de detenci&oacute;n:</div>
            <div class="col-xs-9">
                <textarea class="form-control" name="placeDetention"  ng-model="m.placeDetention" id="placeDetention"
                          data-val="true"  data-val-required="El lugar de detenci&oacute;n es un campo requerido"
                          data-val-length-max="255" data-val-length-min="5" data-val-length ="Debe tener al menos 5 y m&aacute;ximo 255 caracteres."></textarea>
                <span class="field-validation-valid" data-valmsg-for="placeDetention" data-valmsg-replace="true"></span>
            </div>
        </div>



</div>
</div>
</form>
