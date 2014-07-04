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
                        <%@ include file="/WEB-INF/jsp/reviewer/verification/socialNetwork/content.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>