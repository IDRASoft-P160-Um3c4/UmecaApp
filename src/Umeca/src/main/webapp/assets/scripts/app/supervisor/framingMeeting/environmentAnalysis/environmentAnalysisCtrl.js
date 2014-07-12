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
        $scope.loadEnvironmentAnalysis();
    });

    $scope.fillEnvironmentAnalysis=function(data){

        //debugger;
        $scope.lstSources = $.parseJSON(data.lstSources);
        $scope.lstArrangement=$.parseJSON(data.lstArrangement);
        $scope.lstRisk=$.parseJSON(data.lstRisk);
        $scope.lstThreat=$.parseJSON(data.lstThreat);

        $scope.lstSelectedSources= $.parseJSON(data.lstSelectedSources);/*
        $scope.lstSelectedArrangement=data.lstSelectedArrangement;
        $scope.lstSelectedRisk=data.lstSelectedRisk;
        $scope.lstSelectedThreat=data.lstSelectedThreat;*/

        //$scope.checkOptions();
    };

    $scope.verifyCheck=function(id){
        alert($scope.lstSelectedSources.indexOf(id)>=0);
    };

    $scope.checkOptions= function(){

        for(var i=0; i<$scope.lstSelectedSources.length; i++){

        alert('#chkSource_'+$scope.lstSelectedSources[i]);

            $('#chkSource_'+$scope.lstSelectedSources[i]).prop('checked', 'checked');
        }
    };

    $scope.loadEnvironmentAnalysis= function () {
        var currentTimeout = null;
        var urlType = $('#loadEnvironmentAnalysis').attr("value");

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
                    $scope.fillEnvironmentAnalysis(data);

                });
        }, 200);
    };

    $scope.init = function () {
        $scope.loadEnvironmentAnalysis();
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