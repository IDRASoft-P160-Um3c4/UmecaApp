app.controller('upsertEmployeeController', function ($scope, $timeout, $sce, $http) {
    $scope.m = {};
    $scope.WaitFor = false;
    $scope.MsgError;

    $scope.init = function () {
        $scope.fillSelect("m", "district", "lstDistrict");
    };

    $scope.submitUpsertEmployee = function (formId, urlToPost) {

        $scope.WaitFor = true;

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            $scope.MsgError = $sce.trustAsHtml("Debe proporcionar toda la informaci&oacute;n requerida.");
            $scope.WaitFor = false;
            return false;
        }

        $.post(urlToPost, $(formId).serialize())
            .success($scope.handleSuccessEmployee)
            .error($scope.handleErrorEmployee);
        return true;
    };

    $scope.handleSuccessEmployee = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }

            if (resp.hasError == true) {
                $scope.MsgError = $sce.trustAsHtml(resp.message);
                $scope.$apply();
            } else {
                window.goToUrlMvcUrl(resp.urlToGo);
            }
        } catch (e) {
            $scope.MsgError = $sce.trustAsHtml("Error inesperado de datos. Por favor intente m&aacute;s tarde.");
            $scope.$apply();
        }

    };

    $scope.handleErrorEmployee = function () {
        $scope.WaitFor = false;
        $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente m&aacute;s tarde.");
        $scope.$apply();
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