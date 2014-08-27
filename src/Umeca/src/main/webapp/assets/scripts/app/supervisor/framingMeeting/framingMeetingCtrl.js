app.controller('framingMeetingController', function ($scope, $timeout, $http, $rootScope) {

        $scope.fm = {}

        $scope.FMsuccessMsg = "";
        $scope.FMerrorMsg = "";

        $scope.FMerrorMsgLst = "";

        $scope.disableView = function () {

            if ($scope.fm.objView.canTerminate == false) {
                $("#divFM :input").attr("disabled", true);
            }
            else {
                $("#divFM :input").attr("disabled", false);
            }

        };

        $scope.returnFM = function () {

            if ($scope.returnId && $scope.returnId != null && $scope.returnId != undefined && $scope.returnId > 0) {
                window.goToUrlMvcUrl($scope.urlManagerSup);
            }
            else
                window.goToUrlMvcUrl($scope.urlIndex);
        };

        $scope.init = function () {
            $scope.disableView();
        };

        $timeout(function () {
            $scope.init();
        }, 0);


        $scope.resizeMap = function () {
            $timeout(function () {
                $rootScope.$broadcast("resizeMap");
            }, 10);

        };

        $scope.doTerminate = function () {
            var currentTimeout = null;
            var url = "doTerminate.json";
            var idCase = $scope.fm.objView.idCase;
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

                        var resp = data.responseMesage;

                        if (data.hasError == true) {
                            //$scope.FMerrorMsg = data.message;
                            $scope.FMerrorMsgLst = data.message.split('|');
                        }
                        else
                            $scope.returnIdx();
                    });
            }, 200);
        };


    }
)
;