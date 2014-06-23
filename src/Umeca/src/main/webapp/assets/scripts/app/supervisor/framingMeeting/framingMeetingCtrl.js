app.controller('framingMeetingController', function ($scope, $timeout, $http) {

        $scope.m = {}

        $scope.successMsg;
        $scope.errorMsg = {}

        $scope.returnIdx = function () {
            window.goToUrlMvcUrl("<c:url value='/supervisor/framingFormat/index.html'/>");
        }

        /*personalData*/
        $scope.selectedPhysicalCondition = function (lstPhysicalCondition, pCSelected) {

            for (var i = 0; i < pCSelected.length; i++) {
                for (var j = 0; j < lstPhysicalCondition.length; j++) {
                    if (lstPhysicalCondition[j].id === pCSelected[i]) {
                        $scope.phyModel.push(lstPhysicalCondition[j]);
                    }
                }
            }

        }
        /*personalData*/


        $scope.init = function () {

        };

        $timeout(function () {
            $scope.init();
        }, 0);

    }
)
;