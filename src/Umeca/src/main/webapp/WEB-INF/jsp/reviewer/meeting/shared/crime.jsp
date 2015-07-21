<script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/crimeCtrl.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/chosen.min.css"/>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/chosen.jquery.min.js"></script>

<style>
    .chosen-container-single .chosen-search:after {
        content: "" !important;
    }
</style>

<div class="row element-center" ng-controller="crimeController">
    <div class="row element-left" ng-init="readonlyBand = ${readonlyBand == null? false: readonlyBand};">
        <b>Delitos:{{c.crime}}</b>
    </div>
    <input type="hidden" ng-update-hidden ng-init='listCrime = ${(listCrime == null) ? '[]': listCrime};'>
    <input type="hidden" ng-update-hidden ng-model="crimeString" name='listCrime'>
    <div class="col-xs-12">

        <div class="row"  ng-show="readonlyBand == false">
    <div class="col-xs-5 element-center">
       Delito<br/><br/>
        <select class="width-95 chosen-select" ng-model="c.crime"
                id="selectCrime"
                ng-options="e.name for e in optionsCrime"
                ng-change="c.crimeId = c.crime.id"
                ng-init='optionsCrime = ${optionsCrime};'>
                <option value="">Selecciona un delito</option>
        </select>
    </div>
    <div class="col-xs-1 element-center">
       Art&iacute;culo<br/><br/>
            <input type="text" class="form-control" ng-model="c.article"/>
    </div>
    <div class="col-xs-2 element-center">
        Federal<br/> <label class="info-example">(Asociado con el local)</label> <br/>
        <select class="form-control element-center" ng-model="c.federal"
                ng-options="e.name for e in listElection"
                ng-change="c.federalId = c.federal.id"
                ng-init='listElection = ${listElection};'></select>
    </div>
     <div class="col-xs-3">
         Observaciones<br/><br/>
        <textarea class="width-100" ng-model="c.comment"></textarea>
     </div>
    <div class="col-xs-1 element-center">
        Acciones<br/><br/><div class="space-5"></div>
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
        <div class="col-xs-12" ng-show ="listCrime.length > 0">
         <div class="row center">
             <div class="col-xs-5">
                 <h5 class="smaller lighter blue">Delito</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-1">
                 <h5 class="smaller lighter blue">Art&iacute;culo</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-2">
                 <h5 class="smaller lighter blue">Federal</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-3">
                 <h5 class="smaller lighter blue">Observaciones</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-1" ng-show="readonlyBand == false">
                 <h5 class="smaller lighter blue">Acciones</h5>
                 <div class="hr hr-2"></div>
             </div>
         </div>
            <div class="row center" ng-repeat ="crime in listCrime">
                <div class="col-xs-5">
                    {{crime.crime.name}}
                </div>
                <div class="col-xs-1">
                     {{crime.article}}
                </div>
                <div class="col-xs-2">
                    {{crime.federal.name}}
                </div>
                <div class="col-xs-3">
                    {{crime.comment}}
                </div>
                <div class="col-xs-1" ng-show="readonlyBand == false">
                    <i class="icon-trash red" style="cursor:pointer;" ng-click="deleteCrime($index)"></i>
                </div>
            </div>
         </div>

    </div>
    <br/>
</div>