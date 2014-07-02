<div class="row">
    <div class="col-sm-12">
        <div id="accordionJob" class="accordion-style1 panel-group"
             ng-init='listJob =${(listJob == null) ? '[]': listJob};'>
            <div class="panel panel-default" ng-repeat="j in listJob">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionJob"
                           href="#collapseJob{{$index}}">
                            <i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down"
                               data-icon-show="icon-angle-right"></i>
                            Trabajo {{$index+1}}
                        </a>
                    </h4>
                </div>

                <div class="panel-collapse collapse in" id="collapseJob{{$index}}">
                    <div class="panel-body" ng-controller="jobController">
                        <%@ include file="/WEB-INF/jsp/reviewer/verification/job/content.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /span -->
</div>