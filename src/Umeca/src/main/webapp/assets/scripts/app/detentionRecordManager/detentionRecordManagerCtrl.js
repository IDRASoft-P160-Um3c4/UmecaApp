app.controller('detentionRecordManagerController', function ($scope) {

    $scope.submitFindRecords = function (formId, urlToPost) {

        if ($(formId).valid() == false)
            return;

        $scope.WaitFor = true;

        $.get(urlToPost, $(formId).serialize()).success(
            function (resp) {
                $scope.WaitFor = false;
                $scope.$apply();

                if (resp.hasError == true) {
                    $scope.MsgError = resp.message;
                    $scope.$apply();
                } else {
                    resp = resp.responseMessage;
                    window.reloadDetainedGrid(resp.message);
                }
            }
        ).error(
            function () {
                $scope.WaitFor = false;
                $scope.MsgError = "Error de red. Por favor intente más tarde.";
                $scope.$apply();
            });

        return true;
    };

});