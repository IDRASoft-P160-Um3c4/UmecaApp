<div class="row">
    <div class="col-sm-12">
        <div id="accordionImputedHome" class="accordion-style1 panel-group"
             ng-init='listImputedHome =${(listImputedHome == null) ? '[]': listImputedHome};'>
            <div class="panel panel-default" ng-repeat="a in listImputedHome">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionImputedHome"
                           href="#collapseImputedHome{{$index}}">
                            <i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down"
                               data-icon-show="icon-angle-right"></i>
                            Domicilio {{$index+1}}
                        </a>
                    </h4>
                </div>

                <div class="panel-collapse collapse in" id="collapseImputedHome{{$index}}">
                    <div class="panel-body" ng-controller="addressController">
                        <%@ include file="/WEB-INF/jsp/reviewer/verification/imputedHome/content.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /span -->
</div>