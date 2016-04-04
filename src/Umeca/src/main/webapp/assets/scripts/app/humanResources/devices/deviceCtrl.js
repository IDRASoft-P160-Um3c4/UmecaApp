app.controller('upsertDeviceController', function ($scope, $timeout, $sce, $rootScope) {

    $scope.dv = {};

    $scope.init = function (value) {
        if(value === undefined){
            $scope.dv = {};
            return;
        }
        $scope.dv = value;
    };


    $scope.initCatalog = function () {
        $scope.DeviceUses = window.initCatalog($scope.lstDeviceUse, $scope.dv.deviceUse);
        $scope.dv.deviceUse = $scope.DeviceUses.Id;

    };


    $scope.fillSelect = function (obj, prop, list, model) {
        if ($scope[list] === undefined || $scope[list].length <= 0)
            return;
        if ($scope[model] === undefined)
            $scope[prop] = $scope[list][0];

    };

    $scope.submitUpsertDevice = function(formId, urlToPost) {
        $scope.WaitFor = true;
        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            $scope.MsgError = $sce.trustAsHtml("Debe proporcionar toda la informaci&oacute;n requerida.");
            $scope.WaitFor = false;
            return false;
        }

        $.post(urlToPost, $(formId).serialize())
            .success($scope.handleSuccess)
            .error($scope.handleError);
        return true;
    }

    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }

            if (resp.hasError == true) {
                $scope.MsgError = $sce.trustAsHtml(resp.message);
                $scope.$apply();
            } else {
                if (resp.urlToGo == null)
                    location.reload();
                else
                    window.goToUrlMvcUrl(resp.urlToGo);
            }
        } catch (e) {
            $scope.MsgError = $sce.trustAsHtml("Error inesperado de datos. Por favor intente m&aacute;s tarde.");
            $scope.$apply();
        }

    };

    $scope.handleError= function () {
        $scope.WaitFor = false;
        $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente m&aacute;s tarde.");
        $scope.$apply();
    };
});