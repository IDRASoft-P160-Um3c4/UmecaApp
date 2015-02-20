app.controller('managerSupReportController', function ($scope, $timeout, $http, $q, $sce, $rootScope) {

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

    $scope.getMun = function () {

        if ($scope.m.state === undefined)
            return;

        var urlMun = $("#urlMun").val();
        var currentTimeout = null;
        var ajaxConf = {
            method: 'POST',
            url: urlMun
        };

        ajaxConf.params = {idState: $scope.m.state.id};

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (result) {
                    $scope.lstMun = result;
                    if (currentTimeout) {
                        $timeout.cancel(currentTimeout);
                    }
                    $scope.fillSelect("m", "municipality", "lstMun");
                    $timeout(function () {
                        if ($scope.m.municipality !== undefined)
                            $scope.getLoc();
                    }, 0);
                });
        }, 0);
    };

    $scope.getLoc = function () {
        if ($scope.m.municipality === undefined)
            return;

        var urlLoc = $("#urlLoc").val();
        var currentTimeout = null;
        var ajaxConf = {
            method: 'POST',
            url: urlLoc
        };

        ajaxConf.params = {idMun: $scope.m.municipality.id};

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (result) {
                    $scope.lstLocation = result;
                    if (currentTimeout) {
                        $timeout.cancel(currentTimeout);
                    }
                    $timeout(function () {
                        $scope.fillSelect("m", "location", "lstLocation");
                    }, 0);
                });
        }, 0);
    };

    $scope.fillSelect = function (obj, prop, list) {
        if ($scope[list] === undefined || $scope[list].length <= 0)
            return;
        $scope[obj][prop] = $scope[list][0];
    };

    $scope.init = function () {
        $scope.fillSelect("m", "district", "lstDistrict");
        $scope.fillSelect("m", "state", "lstStates");

        $timeout(function () {
            if ($scope.m.state !== undefined)
                $scope.getMun();
        }, 0);


    };

    $timeout(function () {
        $scope.init();
    }, 1);

})
;