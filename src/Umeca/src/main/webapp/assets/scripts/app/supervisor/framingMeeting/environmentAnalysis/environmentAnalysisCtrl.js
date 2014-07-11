app.controller('environmentAnalysisController', function ($scope, $timeout, $http, $rootScope) {

    $scope.envir = {};

    $scope.lstSources = {};

    $scope.lstSelectedSources = [];

    $scope.errorMsg ="";

    $scope.successMsg="";

    $scope.selectSource = function (id) {

        var idx = $scope.findSource(id);

        if (idx != null) {
            $scope.lstSelectedSources.splice(idx, 1);
        } else {
            $scope.lstSelectedSources.push(id);
        }

    };

    $scope.findSource = function (id) {
        for (var i = 0; i < $scope.lstSelectedSources.length; i++) {
            if ($scope.lstSelectedSources[i] == id)
                return i;
        }
        return null;
    }

    $rootScope.$on('reloadEnvironment', function() {
        $scope.loadSources();
    });


    $scope.loadSources = function () {
        //alert("loadSource");
        var currentTimeout = null;
        var urlType = $('#loadSources').attr("value");

        var ajaxConf;

        var idCase = $('#envIdCase').attr("value");
        ajaxConf = {
            method: "POST",
            params: {idCase: idCase},
            dataType: "json",
            contentType: "application/json"
        };

        ajaxConf.url = urlType;

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (data) {
                    $scope.lstSources = data;
                });
        }, 200);
    };

    $scope.init = function () {
        $scope.loadSources();
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
                $scope.successMsg = resp.message;
                $scope.$apply();
                return;
            }

            $scope.errorMsg = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.errorMsg = "Error inesperado de datos. Por favor intente más tarde.";
        }
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.errorMsg = "Error de red. Por favor intente más tarde.";
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