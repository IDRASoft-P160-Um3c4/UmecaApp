app.controller('uploadActivityReportController', function($scope, $timeout, $sce) {

    $scope.setOutError = function(msg){
        $scope.$apply(function(){
            $scope.MsgError = $sce.trustAsHtml(msg);
            $timeout(function(){
                $scope.MsgError = $sce.trustAsHtml("");
            }, 5000)
        });
    };

    $scope.setSuccess = function(result){
        $scope.$apply(function(){

            $scope.MsgSuccess = $sce.trustAsHtml(result.message);
            $scope.m.fileUploadGenericId = result.returnData;

        });
    };

    $scope.save = function(urlToPost){
        $scope.setRes = true;
        var data = $scope.m;
        $.ajax({
            url: urlToPost,
            type: "POST",
            data: JSON.stringify(data),
            success: $scope.handleSuccess,
            error: $scope.handleError,
            dataType: "json",
            contentType: "application/json"
        });
    };

});