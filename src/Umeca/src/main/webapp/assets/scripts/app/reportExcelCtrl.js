app.controller('reportExcelController', function ($scope, $timeout, $http) {

        $scope.init = function () {
            $scope.disableView();
        };

        $timeout(function () {
            $scope.init();
        }, 0);

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