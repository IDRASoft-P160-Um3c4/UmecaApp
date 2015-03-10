app.controller('historySchoolController', function ($scope, $timeout, $sce, $http) {
    $scope.hs = {};
    $scope.WaitFor = false;
    $scope.MsgError;
    $scope.MsgSuccess;

    $scope.init = function () {
        $scope.fillSelect("hs", "acLevel", "lstAcLevel", "acLvlId");
        $timeout(function () {
            $scope.getDegree();
        }, 0);
        $scope.fillSelect("hs", "document", "lstDocs", "documentId");
    };

    $scope.cleanDocSpec=function(){
        if($scope.hs.document.specification==false)
            $scope.hs.docSpec="";

    };

    $scope.getDegree = function () {
        if ($scope.hs.acLevel === undefined)
            return;

        var urlDegree = $("#urlGetDegree").val();
        var currentTimeout = null;
        var ajaxConf = {
            method: 'POST',
            url: urlDegree
        };

        ajaxConf.params = {acLvlId: $scope.hs.acLevel.id};

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (result) {
                    $scope.lstDegree = result;
                    if (currentTimeout) {
                        $timeout.cancel(currentTimeout);
                    }
                    $timeout(function () {
                        $scope.fillSelect("hs", "degree", "lstDegree", "degreeId");
                    }, 0);
                });
        }, 0);
    };

    $scope.submitSchool = function (formId, urlToGo) {

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.");
            return false;
        }

        $scope.WaitFor = true;

        $timeout(function () {
            $.post(urlToGo, $(formId).serialize())
                .success(function (resp) {
                    $scope.successSchool(resp);
                })
                .error(function () {
                    $scope.errorSchool();
                });
        }, 1);

        return true;
    };


    $scope.successSchool = function (resp) {

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
        }

        $scope.$apply();
    };

    $scope.errorSchool = function (resp) {
        $scope.WaitFor = false;
        $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente m&aacute;s tarde.");
        $scope.$apply();
    };

    $scope.fillSelect = function (obj, prop, list, model) {
        if ($scope[list] === undefined || $scope[list].length <= 0)
            return;

        if ($scope[obj][model] === undefined || $scope[obj][model] === -1)
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

    $timeout(function () {
        $scope.init();
    }, 0);

});