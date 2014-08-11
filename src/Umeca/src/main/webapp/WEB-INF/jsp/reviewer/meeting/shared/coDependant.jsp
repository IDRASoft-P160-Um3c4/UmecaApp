<script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/coDependantCtrl.js"></script>
<div class="row element-center" ng-controller="coDependantController">
    <input type="hidden" ng-update-hidden ng-init='listCoDependant = ${(listCoDependant == null) ? '[]': listCoDependant};'>
    <input type="hidden" ng-update-hidden ng-model="coDependantString" name='listCoDefendant'>
    <div class="col-xs-12   ">

        <div class="row">
    <div class="col-xs-7 element-center">
       Nombre Completo<br/>
        <input type="text" class="form-control" ng-model="c.fullName"/>
    </div>
    <div class="col-xs-4 element-center">
        Relaci&oacute;n <br/>
        <select class="form-control element-center" ng-model="c.rel"
                ng-options="e.name for e in listRelationship"
                ng-change="c.relId = c.rel.id"
                ng-init='listRelationship = ${listRelationship};'></select>
    </div>
    <div class="col-xs-1 element-center">
        Acciones<br/><div class="space-5"></div>
        <i class="icon-plus-sign orange" style="cursor:pointer;" ng-click="addCoDependant()"></i>
    </div>
    </div>

        <div class="row" >
        <div class="hr hr-6"></div>
        </div>
        <div ng-show="msgError" class="alert alert-danger element-center error-font">
            {{msgError}}
        </div>
        <div class="col-xs-10 col-xs-offset-1" ng-show ="listCoDependant.length > 0">
         <div class="row center">
             <div class="col-xs-6">
                 <h5 class="smaller lighter blue">Nombre Completo</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-3">
                 <h5 class="smaller lighter blue">Relaci&oacute;n</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-3">
                 <h5 class="smaller lighter blue">Acciones</h5>
                 <div class="hr hr-2"></div>
             </div>
         </div>
            <div class="row center" ng-repeat ="c in listCoDependant">
                <div class="col-xs-6">
                    {{c.fullName}}
                </div>
                <div class="col-xs-3">
                    {{c.relationship.name}}
                </div>
                <div class="col-xs-3">
                    <i class="icon-trash red" style="cursor:pointer;" ng-click="deleteCoDependant($index)"></i>
                </div>
            </div>
         </div>
    </div>
    <br/>
</div>