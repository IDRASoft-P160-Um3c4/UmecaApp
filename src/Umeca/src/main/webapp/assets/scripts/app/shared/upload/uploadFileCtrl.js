app.controller('uploadFileController', function($scope, $timeout, $sce) {
    $scope.m = {};

    $scope.setOutError = function(msg){
        $scope.$apply(function(){
            $scope.MsgError = $sce.trustAsHtml(msg);
            $timeout(function(){
                $scope.MsgError = $sce.trustAsHtml("");
            }, 10000)
        });
    }

    $scope.setSuccess = function(msg){
        $scope.$apply(function(){
            $scope.MsgSuccess = $sce.trustAsHtml(msg);
            $timeout(function(){
                $scope.MsgSuccess = $sce.trustAsHtml("");
            }, 10000)
        });
    }

    $scope.downloadAll = function(){
        window.downloadAll();
    }
});