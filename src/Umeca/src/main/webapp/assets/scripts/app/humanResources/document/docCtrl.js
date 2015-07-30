app.controller('documentController', function ($scope, $timeout, $sce, $rootScope) {

    $scope.WaitFor = false;
    $scope.MsgError="";
    $scope.Invalid = false;

    $scope.submitDocument = function (formId, urlToGo) {

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.");
            return false;
        }

        $scope.WaitFor = true;

        $timeout(function () {
            $.post(urlToGo, $(formId).serialize())
                .success(function (resp) {
                    $scope.successDocument(resp);
                })
                .error(function () {
                    $scope.handleError();
                });
        }, 1);
        return true;
    };

    $scope.successDocument = function (resp) {
        $scope.WaitFor = false;

        if (resp.hasError === undefined) {
            resp = resp.responseMessage;
        }

        if (resp.hasError == true) {
            $scope.MsgError = $sce.trustAsHtml(resp.message);
        } else if (resp.hasError == false) {
            $scope.MsgError = $sce.trustAsHtml("");
            scope = angular.element('#dlgUpModalId').scope();
            scope.Model.dlg.modal('hide');
            scope.Model.def.resolve({isCancel: false});
            $("#GridDocument").trigger("reloadGrid");
        }
        $scope.$apply();
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
        $scope.$apply();
    };

    $scope.init = function () {
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});