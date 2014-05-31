app.controller('personalDataController', function($scope, $timeout) {
    $scope.m = {};
    $scope.lstActivity = [];
    $scope.activityModel = [];
    $scope.activityList = [];
    $scope.phyModel = [];
    $scope.lstPhysicalCondition = [];
    $scope.pCSelected = [];



    $scope.init = function(){
            $(".chosen-select").chosen();
            var dateBirth=$("#dateBirth").val();
             $("#dateBirth").val(dateBirth.replace("00:00:00.0",""));
    };
    $timeout(function() {
        $scope.init();
    }, 0)

    $scope.selectedActivities = function(lstActivity,activityList){

        for(var i = 0 ; i <   activityList.length; i++){
            for(var j=0;j < lstActivity.length; j++){
                if(lstActivity[j].id=== activityList[i]){
                    $scope.activityModel.push(lstActivity[j]);
                }
            }
        }

    };

    $scope.selectedPhysicalCondition = function(lstPhysicalCondition,pCSelected){

        for(var i = 0 ; i <   pCSelected.length; i++){
            for(var j=0;j < lstPhysicalCondition.length; j++){
                if(lstPhysicalCondition[j].id=== pCSelected[i]){
                    $scope.phyModel.push(lstPhysicalCondition[j]);
                }
            }
        }

    }


    $scope.submit = function (formId, urlToPost, hasReturnId) {

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;
            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccess)
                .error($scope.handleError);
        return true;
    };

    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        try {
            if(resp.hasError===undefined){
                resp=resp.responseMessage;}
            if (resp.hasError === false) {
                $scope.msgExito = resp.message;
                $scope.$apply();
                return;
            }
            $scope.msgError = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.msgError = "Error inesperado de datos. Por favor intente más tarde.";
        }
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.MsgError = "Error de red. Por favor intente más tarde.";
        $scope.$apply();
    };
});