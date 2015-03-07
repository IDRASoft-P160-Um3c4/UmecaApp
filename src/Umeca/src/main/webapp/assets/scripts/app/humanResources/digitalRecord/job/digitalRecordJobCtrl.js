app.controller('digitalRecordJobController', function ($scope, $timeout, $sce) {
        $scope.gd = {};
        $scope.WaitFor = false;
        $scope.MsgError;
        $scope.MsgSuccess;

        $scope.init = function () {
        };

        $scope.submitJob = function (formId, urlToGo) {

            if ($(formId).valid() == false) {
                $scope.Invalid = true;
                $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.");
                return false;
            }

            $scope.WaitFor = true;

            $timeout(function () {
                $.post(urlToGo, $(formId).serialize())
                    .success(function (resp) {
                        $scope.successJob(resp);
                    })
                    .error(function () {
                        $scope.errorJob();
                    });
            }, 1);

            return true;
        };

        $scope.successJob = function (resp) {

            $scope.WaitFor = false;

            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }

            if (resp.hasError == true) {
                $scope.MsgError = $sce.trustAsHtml(resp.message);
            } else if (resp.hasError == false) {
                $scope.MsgSuccess = $sce.trustAsHtml(resp.message);
            }

            $scope.$apply();
        };

        $scope.errorJob = function (resp) {
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