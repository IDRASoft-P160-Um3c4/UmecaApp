app.controller("logCaseController", function($scope, $timeout,$sce){

    $scope.init = function () {

    };

    $timeout(function () {
        $scope.init();
    }, 0);

    $scope.formatHtml = function(sHtml){
        return $sce.trustAsHtml(sHtml);
    };
});