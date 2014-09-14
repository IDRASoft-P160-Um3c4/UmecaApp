<div class="row">
    <div>
        <div class="col-xs-2 element-right">
            <i class="icon-ok-circle green  icon-only bigger-120"  ng-show="verification"
               ng-click="doConfirmVerifEqual('socialNetwork.comment')"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification"
               code="socialNetwork.comment"></i>
            <i class="icon-ban-circle inverse icon-only bigger-1 20"  ng-show="verification"
               ng-click="doConfirmVerifNotKnow('socialNetwork.comment')"></i>
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" onclick="window.showChoices('socialNetwork.comment')"></i>
            Observaciones:
            <label class="info-example">(no tiene donde vivir, existe violencia, etc.)</label>
            <br/>
        </div>
        <div class="col-xs-10">
            <textarea class="form-control width-100"
                      data-val-required="Las observaciones es un campo requerido"
                      name="socialNetwork.comment">${m.socialNetwork.comment}</textarea>
                <span class="field-validation-valid" data-valmsg-for="socialNetwork.comment"
                      data-valmsg-replace="true"></span>
        </div>

    </div>
</div>
<br/>
<div class="row">
    <div class="col-sm-12">
        <div id="accordionSocialNetwork" class="accordion-style1 panel-group"
             ng-init='listSocialNetwork =${(listSocialNetwork == null) ? '[]': listSocialNetwork};'>
            <div class="panel panel-default" ng-repeat="p in listSocialNetwork">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionSocialNetwork"
                           href="#collapseSocialNetwork{{$index}}">
                            <i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down"
                               data-icon-show="icon-angle-right"></i>
                            Red social (persona {{$index+1}})
                        </a>
                    </h4>
                </div>

                <div class="panel-collapse collapse in" id="collapseSocialNetwork{{$index}}">
                    <div class="panel-body" ng-controller="socialNetworkController">
                        <div class="row"  ng-show="readOnly == false">
                            <div class="col-xs-12">
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <b>  Establecer toda la informaci&oacute;n de la Red Social persona
                                    {{$index+1}} con:</b>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 text-info"  style="padding-top: 8px;">
                                <i class="purple glyphicon glyphicon-user bigger-160"
                                   ng-click="showChoicesSection(3,p.id,1,'Red social, persona',$index+1)"  style="cursor: pointer;"></i>
                                &nbsp;&nbsp;&nbsp;Informaci&oacute;n que proporcion&oacute; el imputado.
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 text-info" style="padding-top: 8px;">
                                <i class="blue icon-question-sign  icon-only bigger-160" style="cursor: pointer;"
                                   ng-click="showChoicesSection(3,p.id,-1,'Red social, persona',$index+1)"></i>
                                &nbsp;&nbsp;&nbsp;No se pudo verificar
                            </div>
                        </div>
                        <div class="row"  ng-show="readOnly == false">
                            <div col-xs-12>
                                <div class="hr hr-8"></div>
                            </div>
                        </div>
                        <%@ include file="/WEB-INF/jsp/reviewer/verification/socialNetwork/content.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>