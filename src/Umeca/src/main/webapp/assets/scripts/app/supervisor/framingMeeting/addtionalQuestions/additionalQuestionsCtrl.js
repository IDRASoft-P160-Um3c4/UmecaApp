app.controller('additionalQuestionsController', function ($scope, $timeout, $http, $rootScope) {

    $scope.aq = {};
    $scope.aqErrorMsg ="";
    $scope.aqSuccessMsg="";

    $scope.lstRelativeAbroad=[];

    $scope.loadRelativeAbroad= function () {

        var currentTimeout = null;
        var url = $('#loadRelativeAbroad').attr("value");
        var idCase = $('#hidIdCaseAQ').attr("value");
        var ajaxConf;

        ajaxConf = {
            method: "GET",
            params: {idCase: idCase}
        };

        ajaxConf.url = url;

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (data) {
                    $scope.lstRelativeAbroad= data;
                });
        }, 200);
    };

    $scope.loadArrangements= function () {

        var currentTimeout = null;
        var url = $('#loadArrangements').attr("value");
        var idCase = $('#hidIdCaseAQ').attr("value");
        var ajaxConf;

        ajaxConf = {
            method: "GET",
            params: {idCase: idCase}
        };

        ajaxConf.url = url;

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (data) {
                    $scope.lstRelativeAbroad= data;
                });
        }, 200);
    };

    $scope.init = function () {
        $scope.loadRelativeAbroad();
        //$scope.loadArrangements();
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
                $scope.aqSuccessMsg = resp.message;
                $scope.$apply();
                return;
            }

            $scope.aqErrorMsg = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.aqErrorMsg = "Error inesperado de datos. Por favor intente más tarde.";
        }
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.aqErrorMsg = "Error de red. Por favor intente más tarde.";
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