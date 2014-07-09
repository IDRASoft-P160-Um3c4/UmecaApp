app.controller('personalDataFMController', function ($scope, $timeout, $http, $q, sharedSvc) {

        $scope.pd = {}

        $scope.lstPhysicalCondition={};

        $scope.init = function () {
            //$scope.fillFormat($scope.m);
            //$scope.disableView($scope.m.disableAll);
        };

        $timeout(function () {
            $scope.init();
        }, 0);

    }
)
;