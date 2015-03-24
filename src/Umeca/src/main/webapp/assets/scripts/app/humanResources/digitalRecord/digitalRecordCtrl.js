app.controller('digitalRecordController', function ($scope, $timeout, $sce, $rootScope) {

    $scope.dr = {};
    $scope.WaitFor = false;
    $scope.MsgError;
    $scope.MsgSuccess;
    $scope.MsgErrorPhoto;
    $scope.MsgSuccessPhoto;

    $scope.setPhotoError = function (msg) {
        $scope.$apply(function () {
            $scope.MsgErrorPhoto = $sce.trustAsHtml(msg);
            $timeout(function () {
                $scope.MsgErrorPhoto = $sce.trustAsHtml("");
            }, 5000)
        });
    };

    $scope.setPhotoSuccess = function (result, context) {
        $scope.$apply(function () {
            $("#photoEmployee").attr("src", context + result.returnData);
            $("#idLinkPhotoEmployee").attr("href", context + result.returnData);
        });
    };

    $rootScope.$on('changeEmployeeName', function (ev, data) {
        $scope.dr.employeeName = data;
    });

    $rootScope.$on('changeEmployeeRole', function (ev, data) {
        $scope.dr.employeePost = data;
    });

    $scope.init = function () {
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});