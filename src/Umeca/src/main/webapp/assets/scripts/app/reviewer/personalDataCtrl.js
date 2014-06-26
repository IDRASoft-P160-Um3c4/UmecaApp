app.controller('personalDataController', function($scope, $timeout) {
    $scope.m = {};
    $scope.specification = {};
    $scope.lstActivity = [];
    $scope.activityModel = [];
    $scope.activityList = [];
    $scope.phyModel = [];
    $scope.listCountry = [];
    $scope.country=0;
    $scope.lstPhysicalCondition = [];
    $scope.pCSelected = [];
    $scope.relActivities = [];



    $scope.init = function(){
            $(".chosen-select").chosen();
            var birthDate=$("#birthDate").val();
             $("#birthDate").val(birthDate.replace("00:00:00.0",""));
        if($scope.listCountry === undefined || $scope.listCountry.length <= 0)
            return;

        if($scope.countryId === undefined){
            $scope.country = $scope.listCountry[0];
            $scope.countryId = $scope.country.id;
        }
        else{
            for(var i=0; i < $scope.listCountry.length; i++){
                var country = $scope.listCountry[i];

                if(country.id === $scope.countryId){
                    $scope.country = country;
                    break;
                }
            }
        }
    };
    $timeout(function() {
        $scope.init();
    }, 0)

    $scope.selectedActivities = function(lstActivity,lstActivitySelect){

        for(var i = 0 ; i <   lstActivitySelect.length; i++){
            for(var j=0;j < lstActivity.length; j++){
                if(lstActivity[j].id === lstActivitySelect[i].id){
                    $scope.activityModel.push(lstActivity[j]);
                    if(lstActivity[j].specification){
                        $scope.specification[lstActivity[j].name]=lstActivitySelect[i].specification;
                    }
                }
            }
        }

    };

    $scope.matchActivities = function(){
        $scope.relActivities=[];
      for(var i = 0 ; i< $scope.activityModel.length; i++){
          var model = {};
          model.activity= {};
          model.activity.id =  $scope.activityModel[i].id;
          if($scope.specification[$scope.activityModel[i].name] != undefined){
              model.specification  =$scope.specification[$scope.activityModel[i].name];
          }else{
              model.specification = "";
          }
          $scope.relActivities.push(model);
      }
        $scope.activities =  JSON.stringify($scope.relActivities);
        return true;
    };
});