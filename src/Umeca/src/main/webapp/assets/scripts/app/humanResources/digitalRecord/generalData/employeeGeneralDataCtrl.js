app.controller('employeeGeneralDataController', function ($scope, $timeout, $sce, $rootScope) {
        $scope.gd = {};
        $scope.WaitFor = false;
        $scope.MsgError;
        $scope.MsgSuccess;

        $scope.changeName = function () {
            var data = $scope.gd.name + " " + $scope.gd.lastNameP + " " + $scope.gd.lastNameM;
            $rootScope.$broadcast("changeEmployeeName", data);
        };

        $scope.changeRole = function () {
            var data = $scope.gd.post.name;
            $rootScope.$broadcast("changeEmployeeRole", data);
        };

        $scope.init = function () {
            $scope.fillSelect("gd", "maritalStatus", "lstMaritalSt", "maritalStatusId");
            $scope.fillSelect("gd", "document", "lstDocType", "documentId");
            $scope.fillSelect("gd", "post", "lstRole", "roleId");
        };

        $scope.submitGeneralData = function (formId, urlToGo) {

            if ($(formId).valid() == false) {
                $scope.Invalid = true;
                $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.");
                return false;
            }

            $scope.WaitFor = true;

            $timeout(function () {
                $.post(urlToGo, $(formId).serialize())
                    .success(function (resp) {
                        $scope.successGrlData(resp);
                    })
                    .error(function () {
                        $scope.errorGrlData();
                    });
            }, 1);

            return true;
        };

        $scope.successGrlData = function (resp) {

            $scope.WaitFor = false;

            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }

            if (resp.hasError == true) {
                $scope.MsgError = $sce.trustAsHtml(resp.message);
            } else if (resp.hasError == false) {
                $scope.MsgSuccess = $sce.trustAsHtml(resp.message);
            }

            $scope.$apply();
        };

        $scope.errorGrlData = function (resp) {
            $scope.WaitFor = false;
            $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
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

    }
)
;