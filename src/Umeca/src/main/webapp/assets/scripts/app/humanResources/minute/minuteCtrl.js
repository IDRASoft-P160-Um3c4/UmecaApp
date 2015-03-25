app.controller('minuteController', function ($scope, $timeout, $sce, sharedSvc) {

    $scope.minute = {};
    $scope.WaitFor = false;
    $scope.MsgSuccess;
    $scope.MsgError;

    $scope.init = function () {
        $scope.fillSelect("minute", "attendant", "lstAttendant", "attendantId");
    };

    $scope.fillSelect = function (obj, prop, list, model) {
        if ($scope[list] === undefined || $scope[list].length <= 0)
            return;
        if ($scope[obj][model] === undefined || $scope[obj][model] === -1)
            $scope[obj][prop] = $scope[list][0];
        else {
            for (var i = 0; i < $scope[list].length; i++) {
                var rel = $scope[list][i];
                if (rel.id === $scope[obj][model]) {
                    $scope[obj][prop] = rel;
                    break;
                }
            }
        }
    };

    $scope.submitMinute = function (formId, urlToPost) {

        if ($(formId).valid() == false || stVal == false) {
            $scope.Invalid = true;
            $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.");
            return false;
        }

        $scope.WaitFor = true;

        $timeout(function () {
            $.post(urlToPost, $(formId).serialize())
                .success(function (resp) {
                    if (resp.hasError === undefined) {
                        resp = resp.responseMessage;
                    }

                    if (resp.hasError == true) {
                        $scope.MsgError = $sce.trustAsHtml(resp.message);
                        $scope.$apply();
                    }
                })
                .error(function () {
                    $scope.WaitFor = false;
                    $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
                    $scope.$apply();
                });
        }, 1);

        return true;
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});

