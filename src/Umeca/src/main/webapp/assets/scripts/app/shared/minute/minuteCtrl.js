app.controller('minuteController', function ($scope, $timeout, $sce, $rootScope) {

        $scope.minute = {};
        $scope.WaitFor = false;
        $scope.MsgSuccess;
        $scope.MsgError;
        $scope.MsgErrorAssis;
        $scope.lstAssistantSel = [];
        $scope.isNew = false;
        $scope.successObs = false;

        $scope.init = function () {
            $scope.fillSelect("minute", "attendant", "lstEmployee", "attendantId");
            if ($scope.minute.assistantsIds != undefined)
                $scope.lstAssistantSel = JSON.parse($scope.minute.assistantsIds);

            if ($scope.isRH == false) {
                $("#divMinute :input").attr("disabled", true);
            }
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

        $scope.validateAssistant = function () {
            if (!($scope.lstAssistantSel.length > 0)) {
                $scope.MsgErrorAssis = $sce.trustAsHtml("Debe seleccionar al menos un asistente.");
                return false;
            }
            $scope.MsgErrorAssis = $sce.trustAsHtml("");
            return true;
        };

        $scope.selectAssistant = function (idAsisstant) {
            var idx = $scope.lstAssistantSel.indexOf(idAsisstant);
            if (idx > -1) {
                $scope.lstAssistantSel.splice(idx, 1);
            } else {
                $scope.lstAssistantSel.push(idAsisstant);
            }
        };

        $scope.minuteBottom = function () {
            var h = $("body").height();
            $("body").animate({scrollTop: h});
            if ($scope.isNew == true) {
                $timeout(function () {
                    $("#addAgreement").animate({opacity: 0});
                    $scope.isNew = false;
                }, 3000);
            }
        };

        $rootScope.$on('successObs', function () {

            $timeout(function () {
                $scope.successObs = true;
            }, 0);

            $timeout(function () {
                $("#msgObs").animate({opacity: 0});
                $scope.successObs = false;
            }, 3000);

        });

        $scope.submitMinute = function (formId, urlToGo) {
            $scope.invalid = false;

            if ($scope.validateAssistant() == false) {
                $scope.invalid = true;
            }

            if ($(formId).valid() == false) {
                $scope.invalid = true;
                $scope.MsgError = $sce.trustAsHtml("No es posible guardar. Debe proporcionar toda la informaci&oacute;n requerida.")
            } else {
                $scope.MsgError = $sce.trustAsHtml("");
            }

            if ($scope.invalid == true) {
                return;
            }

            $scope.WaitFor = true;

            $timeout(function () {
                var datos = $(formId).serializeArray();
                $.post(urlToGo, datos)
                    .success(function (resp) {
                        $scope.handleSuccessMinute(resp);
                    })
                    .error(function () {
                        $scope.handleErrorMinute();
                    });
            }, 1);

            return true;
        };

        $scope.handleSuccessMinute = function (resp) {
            $scope.$apply(function () {

                if (resp.hasError === undefined) {
                    resp = resp.responseMessage;
                }

                if (resp.hasError == true) {
                    $scope.MsgSuccess = $sce.trustAsHtml("");
                    $scope.MsgError = $sce.trustAsHtml(resp.message);
                } else {
                    $scope.MsgError = $sce.trustAsHtml("");
                    $scope.MsgSuccess = $sce.trustAsHtml(resp.message);
                    $scope.minute.id = resp.returnData.id;
                    $scope.isNew = resp.returnData.specification;
                }
            });

            $scope.WaitFor = false;
            $scope.$apply();
            if ($scope.isNew == true)
                $scope.minuteBottom();
        };

        $scope.handleErrorMinute = function () {
            $scope.$apply(function (resp) {
                $scope.WaitFor = false;
                $scope.MsgSuccess = $sce.trustAsHtml("");
                $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
            });
        };

        $timeout(function () {
            $scope.init();
        }, 0);

    }
)
;

