app.controller('wizardActivityUpsertController', function ($scope) {

    $scope.tabItem = "activity";

    $scope.$on("onNextTab", function(ev, data){
        $scope.tabItem = data;
    });

});