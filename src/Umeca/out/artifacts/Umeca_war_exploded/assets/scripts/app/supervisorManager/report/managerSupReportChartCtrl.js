app.controller('managerSupReportChartController', function ($scope, $timeout, $http, $q, $sce, $rootScope) {

    $scope.m = {};
    $scope.MsgError = "";
    $scope.MsgErrorSel = "";
    $scope.opts = {};

    $scope.submitReport = function (formId, urlToPost, validate) {
        $scope.Invalid = false;

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
        }

        if (validate && validate() == false) {
            $scope.Invalid = true;
        }

        if ($scope.Invalid == true) {
            return false;
        }

        var formSer = $(formId).serialize();
        window.goToUrlMvcUrl(urlToPost + "?" + formSer);
        return true;
    };

    $scope.validateSel = function () {
        var result = false;

        if ($scope.opts !== undefined) {
            for (obj in $scope.opts) {
                result = result || $scope.opts[obj];
            }
        } else {
            result = false;
        }

        if (result == false || result == undefined) {
            result = false;
            $scope.MsgErrorSel = $sce.trustAsHtml("Debe seleccionar al menos una opci&oacute;n.");
        } else {
            $scope.MsgErrorSel = $sce.trustAsHtml("");
        }

        return result;
    };

    $scope.fillSelect = function (obj, prop, list) {
        if ($scope[list] === undefined || $scope[list].length <= 0)
            return;
        $scope[obj][prop] = $scope[list][0];
    };

    $scope.init = function () {
        $scope.m.filter = 1;
        $scope.fillSelect("m", "district", "lstDistrict");
        $scope.fillSelect("m", "supervisor", "lstSupervisor");
    };

    $timeout(function () {
        $scope.init();
    }, 1);

})
;