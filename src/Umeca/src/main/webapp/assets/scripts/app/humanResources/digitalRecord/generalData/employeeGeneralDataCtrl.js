window.ptlUmcDependencies.push('ui.bootstrap');
app.controller('employeeGeneralDataController', function ($scope, $timeout, $sce, $rootScope, $http) {
        $scope.gd = {};
        $scope.WaitFor = false;
        $scope.MsgError;
        $scope.MsgSuccess;
        $scope.MsgErrorUsr;
        $scope.userSel = "";
        $scope.lstUsers = [];
        $scope.MsgErrorSchedule = "";

        $scope.changeName = function () {
            var data = $scope.gd.name + " " + $scope.gd.lastNameP + " " + $scope.gd.lastNameM;
            $rootScope.$broadcast("changeEmployeeName", data);
        };

        $scope.changeRole = function () {
            var data = $scope.gd.post.name;
            $rootScope.$broadcast("changeEmployeeRole", data);
        };

        $scope.getUsrs = function (val) {
            var url = "getUsers.json";
            return $http.get(url, {params: {str: val}})
                .then(function (response) {

                    for (var x = response.data.length - 1; x > -1; x--) {
                        if ($scope.findAssignedUsr(response.data[x]) == true) {
                            response.data.splice(x, 1);
                        }
                    }

                    return response.data.map(function (item) {
                        item.desc = item.name + " (" + item.description + ") ";
                        return item;

                    });
                });
        };

        $scope.findAssignedUsr = function (obj) {
            var ret = false;
            for (var x = 0; x < $scope.lstAssignedUsr.length; x++) {
                if (obj.id === $scope.lstAssignedUsr[x].id) {
                    ret = true;
                    break;
                }
            }
            return ret;
        }

        $scope.selUser = function (obj) {

            $scope.MsgErrorUsr = $sce.trustAsHtml("");

            if ($scope.findAssignedUsr(obj) == true) {
                $scope.MsgErrorUsr = $sce.trustAsHtml("El usuario ya se encuentra asociado.");
            } else {
                $scope.lstAssignedUsr.push(obj);
            }

            $scope.userSel = undefined;
        };

        $scope.removeUsr = function (idx) {
            $scope.lstAssignedUsr.splice(idx, 1);
        };

        $scope.init = function () {
            $scope.fillSelect("gd", "maritalStatus", "lstMaritalSt", "maritalStatusId");
            $scope.fillSelect("gd", "document", "lstDocType", "documentId");
            $scope.fillSelect("gd", "empSchedule", "lstEmployeeSchedule", "empSchId");
        };

        $scope.validateUsrs = function () {
            if ($scope.lstAssignedUsr == undefined || !($scope.lstAssignedUsr.length > 0)) {
                return false;
            }
            return true;
        };

        $scope.validateUsrs = function () {
            if ($scope.lstAssignedUsr == undefined || !($scope.lstAssignedUsr.length > 0)) {
                $scope.MsgErrorUsr = $sce.trustAsHtml("Debe asociar al menos un usuario al empleado.");
                return false;
            }
            return true;
        };

        $scope.validateEmpSchedule = function () {
            if ($scope.empSchedule == undefined) {
                $scope.MsgErrorSchedule = $sce.trustAsHtml("Horario es un campo requerido");
                return false;
            }
            return true
        };


        $scope.validateGrlData = function () {
            var r = $scope.validateUsrs() || $scope.validateEmpSchedule();
            return r;
        }

        $scope.submitGeneralData = function (formId, urlToGo) {

            $scope.Invalid = false;

            $scope.MsgErrorUsr = $sce.trustAsHtml("");
            $scope.MsgErrorSchedule = $sce.trustAsHtml("");
            $scope.MsgError = $sce.trustAsHtml("");


            if ($scope.validateGrlData() == false) {
                $scope.Invalid = true;
            }

            if ($(formId).valid() == false) {
                $scope.Invalid = true;
                $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.");
            }

            if ($scope.Invalid == true) {
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

            $scope.MsgError = $sce.trustAsHtml("");
            $scope.MsgSuccess = $sce.trustAsHtml("");

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