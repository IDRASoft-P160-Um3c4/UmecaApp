<script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/crimeCtrl.js"></script>
<div class="row element-center" ng-controller="crimeController">
    <div class="row element-left">
        <b>Delitos:</b>
    </div>
    <input type="hidden" ng-update-hidden ng-model="crimeString" name='listCrime'>
    <div class="col-xs-12">

        <div class="row">
    <div class="col-xs-5 element-center">
       Delito<br/>
        <input type="text" class="form-control" ng-model="c.name"/>
    </div>
    <div class="col-xs-4 element-center">
       Art&iacute;culo<br/>
            <input type="text" class="form-control" ng-model="c.article"/>
    </div>
    <div class="col-xs-2 element-center">
        Federal <br/>
        <select class="form-control element-center" ng-model="c.federal"
                ng-options="e.name for e in listElection"
                ng-change="c.federalId = c.federal.id"
                ng-init='listElection = ${listElection};'></select>
    </div>
    <div class="col-xs-1 element-center">
        Acciones<br/><div class="space-5"></div>
        <i class="icon-plus-sign orange" style="cursor:pointer;" ng-click="addCrime()"></i>
    </div>
    </div>
        <div class="row" >
        <div class="hr hr-6"></div>
        </div>
        <div ng-show="listMsgError.length > 0" class="alert alert-danger element-center error-font">
            <div  ng-repeat ="msg in listMsgError">
                {{msg}}
                <br/>
            </div>
        </div>
        <div class="col-xs-10 col-xs-offset-1" ng-show ="listCrime.length > 0">
         <div class="row center">
             <div class="col-xs-5">
                 <h5 class="smaller lighter blue">Delito</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-2">
                 <h5 class="smaller lighter blue">Art&iacute;culo</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-2">
                 <h5 class="smaller lighter blue">Federal</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-3">
                 <h5 class="smaller lighter blue">Acciones</h5>
                 <div class="hr hr-2"></div>
             </div>
         </div>
            <div class="row center" ng-repeat ="crime in listCrime">
                <div class="col-xs-5">
                    {{crime.name}}
                </div>
                <div class="col-xs-2">
                     {{crime.article}}
                </div>
                <div class="col-xs-2">
                    {{crime.federal.name}}
                </div>
                <div class="col-xs-3">
                    <i class="icon-trash red" style="cursor:pointer;" ng-click="deleteCrime($index)"></i>
                </div>
            </div>
         </div>

    </div>
    <br/>
</div>