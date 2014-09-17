app.controller("authRejActMonPlanController", function($scope){
    $scope.m = {};
    $scope.m.lstAutRejActMon = [];

    $scope.createLabel = function(status){
        return 'label ' + window.colorActMonPlan(status);
    };

    $scope.init = function(lstActivities){
        for(act in lstActivities){
            $scope.m.lstAutRejActMon.push({id:act.activityMonId, value: 0});
        };
    };

});
