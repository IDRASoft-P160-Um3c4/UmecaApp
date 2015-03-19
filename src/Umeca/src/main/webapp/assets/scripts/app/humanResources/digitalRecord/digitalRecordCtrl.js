app.controller('digitalRecordController', function ($scope, $timeout, $sce) {

    $scope.dr = {};
    $scope.WaitFor = false;
    $scope.MsgError;
    $scope.MsgSuccess;

    $scope.setPhotoError = function (msg) {
        $scope.$apply(function () {
            $scope.MsgError = $sce.trustAsHtml(msg);
            $timeout(function () {
                $scope.MsgError = $sce.trustAsHtml("");
            }, 5000)
        });
    };

    $scope.setPhotoSuccess = function (result) {
        $scope.$apply(function () {
            debugger;
            $scope.MsgSuccess = $sce.trustAsHtml(result.message);
            $scope.canSave = true;
            $scope.attachment.fileId = result.returnData;
        });
    };

    $scope.init = function () {
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});