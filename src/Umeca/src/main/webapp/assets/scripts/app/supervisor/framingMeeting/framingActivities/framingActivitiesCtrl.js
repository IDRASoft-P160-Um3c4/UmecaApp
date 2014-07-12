app.controller('framingActivitiesController', function ($scope, $timeout, $http) {

    $scope.act = {};

    $scope.actErrorMsg ="";
    $scope.actSuccessMsg="";

    $scope.init = function () {

    };

    $timeout(function () {
        $scope.init();
    }, 0);

    $scope.WaitFor = false;
    $scope.Model = {};

    $scope.submitIdCaseParam = function (formId, urlToPost, id) {

        $(formId).validate();

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;

        var url = urlToPost + id;

        $.post(url, $(formId).serialize())
            .success($scope.handleSuccess)
            .error($scope.handleError);

        return true;
    };

    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                $scope.actSuccessMsg = resp.message;
                $scope.$apply();
                return;
            }

            $scope.actErrorMsg = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.actErrorMsg = "Error inesperado de datos. Por favor intente más tarde.";
        }
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.actErrorMsg = "Error de red. Por favor intente más tarde.";
        $scope.$apply();
    };

    $scope.cancel = function () {
        $scope.Model.dlg.modal('hide');
        $scope.Model.def.reject({ isCancel: true });
    };

    $scope.setDlg = function (dlg, urlToSubmit) {
        $scope.Model.dlg = dlg;
        $scope.Model.url = urlToSubmit;

        dlg.on('hidden.bs.modal', function () {
            dlg.data('modal', null);
            dlg.replaceWith("");
        });
    };
});