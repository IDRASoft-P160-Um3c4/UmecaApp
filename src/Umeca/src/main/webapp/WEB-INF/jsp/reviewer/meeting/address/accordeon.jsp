<div id="accordion" class="accordion-style1 panel-group">

 <!--   <div class="panel panel-default" ng-repeat="panel in listPanels" ng-init="listPanels = ${listDomicile}">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                    <i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down" data-icon-show="icon-angle-right"></i>
                    &nbsp;  {{ panel.direccion }}
                </a>
            </h4>
        </div>

        <div class="panel-collapse collapse in" id="collapseOne">
            <div class="panel-body">
                <%@ include file="/WEB-INF/jsp/reviewer/meeting/address/content.jsp"%>
            </div>
        </div>
    </div>-->

    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                    <i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down" data-icon-show="icon-angle-right"></i>
                    &nbsp;<span></span>  Calle 1 no 211 col. qqqq Delegación zzzz
                </a>
            </h4>
        </div>

        <div class="panel-collapse collapse in" id="collapseOne">
            <div class="panel-body">
                <%@ include file="/WEB-INF/jsp/reviewer/meeting/address/content.jsp"%>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                    <i class="icon-angle-right bigger-110" data-icon-hide="icon-angle-down" data-icon-show="icon-angle-right"></i>
                    &nbsp;Calle 2 no 323, col. zzzzz Delegación qwerty
                </a>
            </h4>
        </div>

        <div class="panel-collapse collapse" id="collapseTwo">
            <div class="panel-body">
                <%@ include file="/WEB-INF/jsp/reviewer/meeting/address/content.jsp"%>
            </div>
        </div>
</div>
</div>