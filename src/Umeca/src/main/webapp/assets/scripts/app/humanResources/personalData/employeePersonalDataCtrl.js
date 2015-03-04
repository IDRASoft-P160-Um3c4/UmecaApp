app.controller('employeePersonalDataController', function ($scope, $timeout, $sce, $http) {
    $scope.m = {};
    $scope.WaitFor = false;
    $scope.MsgError;

    $scope.init = function () {
        //$scope.fillSelect("m", "district", "lstDistrict");
    };

    $scope.fillSelect = function (obj, prop, list, model) {
        if ($scope[list] === undefined || $scope[list].length <= 0)
            return;
        if ($scope[model] === undefined)
            $scope[obj][prop] = $scope[list][0];
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});