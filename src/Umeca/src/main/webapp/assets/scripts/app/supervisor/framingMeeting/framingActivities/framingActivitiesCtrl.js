app.controller('framingActivitiesController', function ($scope, $timeout, $http) {

    $scope.act = {};

    $scope.actErrorMsg ="";
    $scope.actSuccessMsg="";

    $scope.init = function () {
        $scope.loadFramingActivities();
    };

    $timeout(function () {
        $scope.init();
    }, 0);

    $scope.WaitFor = false;
    $scope.Model = {};

    $scope.loadFramingActivities = function () {

        var currentTimeout = null;
        var ajaxConf;

        var url = $('#hidUrlAct').attr("value");
        var idCase = $('#hidIdCaseAct').attr("value");

        ajaxConf = {
            method: "POST",
            params: {idCase: idCase}/*,
             dataType: "json",
             contentType: "application/json"*/
        };

        ajaxConf.url = url;

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (data) {
                    $scope.fillActivities(data);
                });
        }, 200);
    };


    $scope.fillActivities = function (data) {
        $scope.act.occName=data.occName;
        $scope.act.occPlace=data.occPlace;
        $scope.act.occPhone=data.occPhone;
        $scope.act.activities=data.activities;
    };


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