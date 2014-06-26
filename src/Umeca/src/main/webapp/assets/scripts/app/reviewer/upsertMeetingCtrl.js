app.controller('upsertMeetingController', function ($scope, $timeout) {
    $scope.verification=false;
    $scope.submit = function (formId, urlToPost) {

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;
        $.post(urlToPost, $(formId).serialize())
            .success($scope.handleSuccess)
            .error($scope.handleError);
        return true;

    };

    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                $scope.msgExito = resp.message;
                $scope.$apply();
                return;
            }
            $scope.msgError = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.msgError = "Error inesperado de datos. Por favor intente más tarde.";
        }
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.MsgError = "Error de red. Por favor intente más tarde.";
        $scope.$apply();
    };

});