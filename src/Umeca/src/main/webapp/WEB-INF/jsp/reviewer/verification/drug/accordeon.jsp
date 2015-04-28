<div class="row">
    <div class="col-xs-10 col-xs-offset-1 element-left">
        <label class="text-primary">Observaciones: <b>${m.commentDrug}</b></label>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-sm-12">
        <div id="accordionDrug" class="accordion-style1 panel-group"
             ng-init='listDrug =${(listDrug == null) ? '[]': listDrug};'>
            <div class="panel panel-default" ng-repeat="d in listDrug">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionDrug"
                           href="#collapseDrug{{$index}}">
                            <i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down"
                               data-icon-show="icon-angle-right"></i>
                            Sustancia {{$index+1}}
                        </a>
                    </h4>
                </div>

                <div class="panel-collapse collapse in" id="collapseDrug{{$index}}">
                    <div class="panel-body" ng-controller="drugController">
                        <div class="row"  ng-show="readOnly == false">
                            <div class="col-xs-12">
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <b>  Establecer toda la informaci&oacute;n de el consumo de sustancias
                                    {{$index+1}} con:</b>
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 text-info"  style="padding-top: 8px;">
                                <i class="purple glyphicon glyphicon-user bigger-160"
                                   ng-click="showChoicesSection(7,d.id,1,'Sustancia',$index+1)"  style="cursor: pointer;"></i>
                                &nbsp;&nbsp;&nbsp;Informaci&oacute;n que proporcion&oacute; el imputado.
                            </div>
                            <div class="col-xs-10 col-xs-offset-1 text-info" style="padding-top: 8px;">
                                <i class="blue icon-question-sign  icon-only bigger-160" style="cursor: pointer;"
                                   ng-click="showChoicesSection(7,d.id,-1,'Sustancia',$index+1)"></i>
                                &nbsp;&nbsp;&nbsp;No se pudo verificar
                            </div>
                        </div>
                        <div class="row"  ng-show="readOnly == false">
                            <div col-xs-12>
                                <div class="hr hr-8"></div>
                            </div>
                        </div>
                        <%@ include file="/WEB-INF/jsp/reviewer/verification/drug/content.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>