<div id="accordion" class="accordion-style1 panel-group">
    <div class="panel panel-default" ng-repeat="panel in listPanels" ng-init="listPanels = ${listImputedHome}">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                    <i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down"
                       data-icon-show="icon-angle-right"></i>
                    &nbsp; {{ panel.address.addressString }}
                </a>
            </h4>
        </div>

        <div class="panel-collapse collapse in" id="collapseOne">
            <div class="panel-body">
                <%@ include file="/WEB-INF/jsp/reviewer/verification/imputedHome/content.jsp" %>
            </div>
        </div>
    </div>
</div>
