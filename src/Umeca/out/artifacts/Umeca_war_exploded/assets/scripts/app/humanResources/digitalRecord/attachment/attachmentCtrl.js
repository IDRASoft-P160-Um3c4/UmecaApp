app.controller('attachmentController', function ($scope, $timeout, $sce) {
    $scope.attachment = {};
    $scope.WaitFor = false;
    $scope.MsgError;
    $scope.MsgSuccess;
    $scope.canSave = false;

    $scope.setOutError = function (msg) {
        $scope.$apply(function () {
            $scope.MsgError = $sce.trustAsHtml(msg);
            $timeout(function () {
                $scope.MsgError = $sce.trustAsHtml("");
            }, 5000)
        });
    };

    $scope.setSuccess = function (result) {
        $scope.$apply(function () {
            $scope.MsgSuccess = $sce.trustAsHtml(result.message);
            $scope.canSave = true;
            $scope.attachment.fileId = result.returnData;
        });
    };

    $scope.init = function () {
    };

    $scope.submitAttachment = function (formId, urlToGo) {

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.");
            return false;
        }

        $scope.WaitFor = true;

        $timeout(function () {
            $.post(urlToGo, $(formId).serialize())
                .success(function (resp) {
                    $scope.successAttachment(resp);
                })
                .error(function () {
                    $scope.errorAttachment();
                });
        }, 1);

        return true;
    };


    $scope.successAttachment = function (resp) {

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
            $scope.MsgSuccess = $sce.trustAsHtml(resp.message);
            scope = angular.element('#dlgUpModalId').scope()
            scope.Model.dlg.modal('hide');
            scope.Model.def.resolve({isCancel: false});
        }

        $scope.$apply();
    };

    $scope.errorAttachment = function (resp) {
        $scope.WaitFor = false;
        $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
        $scope.$apply();
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});