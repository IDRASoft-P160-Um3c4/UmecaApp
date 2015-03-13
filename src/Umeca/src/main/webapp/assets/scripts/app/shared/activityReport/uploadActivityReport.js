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

    $scope.handleEndActSuccess = function (resp) {
        try {
            $scope.waitFor = false;
            if (resp.hasError === undefined || resp.hasError === true) {
                $scope.msgError = resp.message;
                $scope.$apply();
                return;
            }
            else if (resp.hasError === false) {
                $scope.actIsDone = true;
                $scope.isReadOnly = true;
                $scope.actProcessIsDone = true;
                $scope.eventSel.className = window.changeByStatus('REALIZADA', $scope.m.priority.id);
                $scope.eventSel.actIsDone = true;
                $scope.option = "UPDATE";
            }
            $scope.$apply();
        } catch (e) {
            $scope.msgError = "Error inesperado de datos. Por favor intente más tarde.";
            $scope.$apply();
        }
    };

    $scope.handleEndActError = function () {
        $scope.waitFor = false;
        $scope.msgError = "Error de red. Por favor intente más tarde.";
        $scope.$apply();

    };

});