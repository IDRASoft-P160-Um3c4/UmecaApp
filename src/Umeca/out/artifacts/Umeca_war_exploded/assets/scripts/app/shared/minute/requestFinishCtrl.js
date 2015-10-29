app.controller('requestFinishController', function ($scope, $timeout, $sce, $rootScope) {

    $scope.WaitFor = false;
    $scope.MsgSuccess;
    $scope.MsgError;
    $scope.savedRequest = false;

    $scope.init = function () {

    };

    $scope.submitRequest = function (formId, urlToGo) {
        $scope.invalid = false;

        if ($(formId).valid() == false) {
            $scope.invalid = true;
            $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.")
        } else {
            $scope.MsgError = $sce.trustAsHtml("");
        }

        if ($scope.invalid == true) {
            return;
        }

        $scope.WaitFor = true;

        $timeout(function () {
            var datos = $(formId).serialize();
            $.post(urlToGo, datos)
                .success(function (resp) {
                    $scope.handleSuccessRequest(resp);
                })
                .error(function () {
                    $scope.handleErrorRequest();
                });
        }, 1);

        return true;
    };

    $scope.handleSuccessRequest = function (resp) {
        $scope.$apply(function () {

            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }

            if (resp.hasError == true) {
                $scope.MsgSuccess = $sce.trustAsHtml("");
                $scope.MsgError = $sce.trustAsHtml(resp.message);
            } else {
                $scope.MsgError = $sce.trustAsHtml("");
                $scope.MsgSuccess = $sce.trustAsHtml(resp.message);
                $scope.savedRequest = true;
            }
        });

        $scope.WaitFor = false;
        $scope.$apply();
    };

    $scope.cancel = function () {
        $scope.closeModal(angular.element('#dlgUpModalId').scope());
    }

    $scope.closeModal = function (scope) {
        scope.Model.dlg.modal('hide');
        scope.Model.def.resolve({isCancel: false});
    }

    $scope.handleErrorRequest = function () {
        $scope.$apply(function () {
            $scope.WaitFor = false;
            $scope.MsgSuccess = $sce.trustAsHtml("");
            $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
        });
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});

