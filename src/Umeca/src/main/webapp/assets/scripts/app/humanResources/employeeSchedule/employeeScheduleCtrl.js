app.controller('employeeScheduleController', function ($scope, $timeout, $sce) {
    $scope.empSche = {};
    $scope.WaitFor = false;
    $scope.MsgError;

    $scope.init = function () {

    };

    $scope.submitUpsertEmployeeSch = function (formId, urlToPost) {

        $scope.WaitFor = true;

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            $scope.MsgError = $sce.trustAsHtml("Debe proporcionar toda la informaci&oacute;n requerida.");
            $scope.WaitFor = false;
            return false;
        }

        $.post(urlToPost, $(formId).serialize())
            .success($scope.handleSuccessEmployeeSch)
            .error($scope.handleErrorEmployeeSch);
        return true;
    };

    $scope.handleSuccessEmployeeSch = function (resp) {
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

    $scope.handleErrorEmployeeSch = function () {
        $scope.WaitFor = false;
        $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente m&aacute;s tarde.");
        $scope.$apply();
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});