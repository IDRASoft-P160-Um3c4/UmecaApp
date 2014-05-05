app.controller('userController', function($scope, $timeout) {
    $scope.m = {};

    $scope.lstRoles = [];
    $scope.m.role = 0;

    $scope.init = function(){
        if($scope.lstRoles === undefined || $scope.lstRoles.length <= 0)
            return;

        $scope.m.role = $scope.lstRoles[0];
    };


    $timeout(function() {
        $scope.init();
    }, 0);
});