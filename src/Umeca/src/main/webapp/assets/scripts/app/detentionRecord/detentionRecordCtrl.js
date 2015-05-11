app.controller('detentionRecordController', function ($scope, $timeout, $sce, $rootScope) {

    $scope.detained = {};
    $scope.WaitFor = false;
    $scope.MsgError;
    $scope.MsgSuccess;

    $scope.init = function () {
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});