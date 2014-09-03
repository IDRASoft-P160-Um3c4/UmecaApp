app.controller('reportExcelController', function ($scope, $timeout, $http) {

        $scope.lstStatusCase = [];
        $scope.lstStatusMeeting = [];
        $scope.lstStatusVerification = [];
        $scope.lstGender = [];
        $scope.lstMaritalSt = [];
        $scope.lstJob = [];
        $scope.lstAcademicLvl = [];
        $scope.lstDrugs = [];
        $scope.lstLvlRisk = [];
        $scope.lstHearingType = [];

        $scope.init = function () {

        };

        $timeout(function () {
            $scope.init();
        }, 0);


        $scope.addIdToList = function (listName, id) {

            var idx = $scope.findIdInList(listName, id);

            if (idx != null) {
                $scope[listName].splice(idx, 1);
            } else {
                $scope[listName].push(id);
            }

        }
        ;

        $scope.findIdInList = function (listName, id) {

            if ($scope[listName] && $scope[listName] != undefined) {
                for (var i = 0; i < $scope[listName].length; i++) {
                    if ($scope[listName][i] == id)
                        return i;
                }
            }

            return null;
        };

        $scope.submitFindCases = function (formId, urlToPost) {

            if ($(formId).valid() == false)
                return;

            $scope.WaitFor = true;

            $.post(urlToPost, $(formId).serialize())
                .success(function (resp) {

                    $scope.WaitFor = false;
                    $scope.$apply();

                    resp = resp.responseMessage;

                    window.mycosa(resp.message);

                    if (resp.hasError == true) {
                        $scope.MsgError = resp.message;
                        $scope.$apply();
                    }

                })
                .error(function () {
                    $scope.WaitFor = false;
                    $scope.MsgError = "Error de red. Por favor intente más tarde.";
                    $scope.$apply();
                });

            return true;
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