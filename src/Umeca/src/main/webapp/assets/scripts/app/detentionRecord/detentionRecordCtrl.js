app.controller('detentionRecordController', function ($scope, $timeout, $sce, $rootScope) {

    $scope.detained = {};
    $scope.WaitFor = false;
    $scope.MsgError;
    $scope.MsgSuccess;

    $scope.submitDetained = function (formId, urlToGo) {
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
                    $scope.handleSuccessDetained(resp);
                })
                .error(function () {
                    $scope.handleErrorDetained();
                });
        }, 1);

        return true;
    };

    $scope.handleSuccessDetained = function (resp) {
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
                scope = angular.element('#dlgUpModalId').scope()
                scope.Model.dlg.modal('hide');
                scope.Model.def.resolve({isCancel: false});
            }
        });

        $scope.WaitFor = false;
        $scope.$apply();
    };

    $scope.handleErrorDetained = function () {
        $scope.$apply(function () {
            $scope.WaitFor = false;
            $scope.MsgSuccess = $sce.trustAsHtml("");
            $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
        });
    };

    $scope.fillSelect = function (obj, prop, list, model) {

        if ($scope[list] === undefined || $scope[list].length <= 0)
            return;
        if ($scope[obj][model] === undefined)
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

    $scope.submitProsecute = function(formId,urlToGo){
        $scope.WaitFor = true;

        $timeout(function () {
            $.post(urlToGo)
                .success(function (resp) {
                    $scope.handleSuccessDetained(resp);
                })
                .error(function () {
                    $scope.handleErrorDetained();
                });
        }, 1);

    };

    $scope.init = function () {
        $scope.fillSelect("detained", "district", "lstDistrict", "districtId");
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});