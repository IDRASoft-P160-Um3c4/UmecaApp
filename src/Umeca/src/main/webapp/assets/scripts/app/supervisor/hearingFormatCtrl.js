app.controller('hearingFormatController', function ($scope, $timeout) {

    $scope.m = {};

    $scope.validateSave = function () {

        if (hayError == true)
            return false;

        return true;
    };

    $scope.init = function () {

    };


    $timeout(function () {
        $scope.init();
    }, 0);


});