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
                        <%@ include file="/WEB-INF/jsp/reviewer/verification/drug/content.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /span -->
</div>