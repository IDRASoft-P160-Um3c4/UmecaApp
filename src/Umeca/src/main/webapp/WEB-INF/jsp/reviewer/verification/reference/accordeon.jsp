<div class="row">
    <div class="col-sm-12">
        <div id="accordionReference" class="accordion-style1 panel-group"
             ng-init='listReference =${(listReference == null) ? '[]': listReference};'>
            <div class="panel panel-default" ng-repeat="r in listReference">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionReference"
                           href="#collapseReference{{$index}}">
                            <i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down"
                               data-icon-show="icon-angle-right"></i>
                            Referencia personal {{$index+1}}
                        </a>
                    </h4>
                </div>

                <div class="panel-collapse collapse in" id="collapseReference{{$index}}">
                    <div class="panel-body" ng-controller="referenceController">
                        <%@ include file="/WEB-INF/jsp/reviewer/verification/reference/content.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /span -->
</div>