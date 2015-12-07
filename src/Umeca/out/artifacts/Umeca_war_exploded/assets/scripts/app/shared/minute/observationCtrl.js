app.controller('obsController', function ($scope, $timeout, $sce, $rootScope) {

        $scope.observation = {};
        $scope.WaitFor = false;
        $scope.MsgSuccess;
        $scope.MsgError;

        $scope.init = function () {

        };

        $scope.submitObs = function (formId, urlToGo) {
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
                        $scope.handleSuccessObs(resp);
                    })
                    .error(function () {
                        $scope.handleErrorObs();
                    });
            }, 1);

            return true;
        };

        $scope.handleSuccessObs = function (resp) {
            $scope.$apply(function () {

                if (resp.hasError === undefined) {
                    resp = resp.responseMessage;
                }

                if (resp.hasError == true) {
                    $scope.MsgSuccess = $sce.trustAsHtml("");
                    $scope.MsgError = $sce.trustAsHtml(resp.message);
                } else {
                    $rootScope.$broadcast('successObs');
                    $scope.MsgError = $sce.trustAsHtml("");
                    $scope.MsgSuccess = $sce.trustAsHtml(resp.message);
                    scope = angular.element('#dlgUpModalId').scope()
                    scope.Model.dlg.modal('hide');
                    scope.Model.def.resolve({isCancel: false});
                }
            });

            $scope.WaitFor = false;
            $scope.$apply();
        };

        $scope.handleErrorObs = function () {
            $scope.$apply(function () {
                $scope.WaitFor = false;
                $scope.MsgSuccess = $sce.trustAsHtml("");
                $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
            });
        };

        $timeout(function () {
            $scope.init();
        }, 0);

    }
)
;

