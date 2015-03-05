app.controller('employeeGeneralDataController', function ($scope, $timeout, $sce, $http) {
    $scope.m = {};
    $scope.WaitFor = false;
    $scope.MsgError;

    $scope.init = function () {
        //$scope.fillSelect("m", "district", "lstDistrict");
    };

    $scope.submitGeneralData = function (formId, urlToGo) {

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.");
            return false;
        }

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