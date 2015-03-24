app.controller('minuteController', function ($scope, $timeout, $sce, sharedSvc) {

    $scope.minute = {};
    $scope.WaitFor = false;
    $scope.MsgSuccess;
    $scope.MsgError;

    $scope.init = function () {

    };

    $timeout(function () {
        $scope.init();
    }, 0);

});

