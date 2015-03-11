app.controller('umecaJobController', function ($scope, $timeout, $sce) {
        $scope.umecaJob = {};
        $scope.WaitFor = false;
        $scope.MsgError;
        $scope.MsgSuccess;

        $scope.init = function () {
        };

        $scope.submitUmecaJob = function (formId, urlToGo) {

            if ($(formId).valid() == false) {
                $scope.Invalid = true;
                $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.");
                return false;
            }

            $scope.WaitFor = true;

            $timeout(function () {
                $.post(urlToGo, $(formId).serialize())
                    .success(function (resp) {
                        $scope.successUmecaJob(resp);
                    })
                    .error(function () {
                        $scope.errorUmecaJob();
                    });
            }, 1);

            return true;
        };

        $scope.successUmecaJob = function (resp) {

            $scope.WaitFor = false;

            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }

            if (resp.hasError == true) {
                $scope.MsgError = $sce.trustAsHtml(resp.message);
                $scope.MsgSuccess = $sce.trustAsHtml("");
            } else if (resp.hasError == false) {
                $scope.MsgSuccess = $sce.trustAsHtml(resp.message);
                $scope.MsgError = $sce.trustAsHtml("");
                scope = angular.element('#dlgUpModalId').scope()
                scope.Model.dlg.modal('hide');
                scope.Model.def.resolve({isCancel: false});
            }

            $scope.$apply();
        };

        $scope.errorUmecaJob = function (resp) {
            $scope.WaitFor = false;
            $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
            $scope.$apply();
        };

        $timeout(function () {
            $scope.init();
        }, 0);

    }
)
;