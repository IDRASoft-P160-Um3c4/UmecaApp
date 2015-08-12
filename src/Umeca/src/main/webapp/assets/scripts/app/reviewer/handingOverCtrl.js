app.controller('handingOverController', function ($scope, $timeout, $http, $rootScope, $interval, dateFilter) {
        $scope.ho = {};
        $scope.WaitFor = false;
        $scope.MsgErrorInfoDet = "";
        var _1Mil = 1000;
        var _1minMil = 60 * (_1Mil);
        var _1hourMil = 60 * (_1minMil);
        var _48hrsMil = 48 * (_1hourMil);

        $scope.submitDetInfo = function (formId, urlToPost) {
            if ($(formId).valid() == false) {
                $scope.Invalid = true;
                return false;
            }

            $scope.WaitFor = true;

            $.post(urlToPost, $(formId).serialize())
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

                if (resp.hasError === true) {
                    $scope.WaitFor = false;
                    $scope.MsgErrorInfoDet = resp.message;
                } else {
                    $scope.WaitFor = false;
                    $scope.MsgErrorInfoDet = "";
                    $scope.hasHandingOverInfo = true;
                    $scope.handingOverMil = resp.returnData;
                }
                $scope.$apply();

            } catch (e) {
                $scope.MsgErrorInfoDet = "Error inesperado de datos. Por favor intente más tarde.";
            }
        };

        $scope.handleError = function () {
            $scope.WaitFor = false;
            $scope.MsgErrorInfoDet = "Error de red. Por favor intente más tarde.";
            $scope.$apply();
        };

        $scope.init = function () {
            $scope.showHandingOverInfo();
        };

        $scope.showHandingOverInfo = function () {
            if ($scope.handingOverMil > 0) {
                $scope.handingOverTimeStr = dateFilter($scope.handingOverMil, "HH:mm:ss");
                $scope.handingOverDateStr = dateFilter($scope.handingOverMil, "yyyy/MM/dd");
            }
        };

        $scope.updateTime = function () {

            $interval(function () {
            var now = new Date();
            $scope.currentDate = dateFilter(now, "yyyy/MM/dd");
            $scope.currentTime = dateFilter(now, "HH:mm:ss");
            if ($scope.hasHandingOverInfo == true) {

                var diff = now.getTime() - $scope.handingOverMil;

                if(diff > 0) {
                    if (diff < _48hrsMil) {
                        $scope.setLeftTime(diff);
                    } else {
                        $scope.leftTime = "El plazo a vencido.";
                    }
                }else{
                    $scope.leftTime = "La fecha proporcionada es mayor a la actual.";
                }
            }
            });

        };

        $scope.setLeftTime = function (diff) {
            var left = _48hrsMil - diff;
            var hrs = parseInt(left / _1hourMil);
            var left = left - (hrs * _1hourMil);
            var mins = parseInt(left / _1minMil);
            var left = left - (mins * _1minMil);
            var sec = parseInt(left / _1Mil);

            if (parseInt(hrs) < 10)
                hrs = "0" + hrs;
            if (parseInt(mins) < 10)
                mins = "0" + mins;
            if (parseInt(sec) < 10)
                sec = "0" + sec;

            $scope.leftTime = hrs + ":" + mins + ":" + sec;
        };

        $timeout(function () {
            $scope.init();
            $scope.updateTime();
        }, 0);

    }
)
;
