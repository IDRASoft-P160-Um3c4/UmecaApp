app.controller('statisticReportController', function ($scope, $timeout, $http) {

        $scope.m = {};
        $scope.m.filtersModel = {};
        $scope.entities = []

        $scope.submitFindCases = function (formId, urlToPost) {
            $scope.msgError = "";

            if($scope.m.filterSelected === null || $scope.m.filterSelected === '' || $scope.m.filterSelected === undefined){
                $scope.msgError = "Debes seleccionar un filtro de busqueda";
            }

            if ($(formId).valid() == false)
                return;

            $scope.WaitFor = true;

            $.post(urlToPost, $(formId).serialize())
                .success(function (resp) {

                    $scope.WaitFor = false;
                    $scope.$apply();

                    if (resp.hasError == true) {
                        $scope.MsgError = resp.message;
                        $scope.$apply();
                    } else {
                        resp = resp.responseMessage;


                    }

                }
            )
                .
                error(function () {
                    $scope.WaitFor = false;
                    $scope.MsgError = "Error de red. Por favor intente más tarde.";
                    $scope.$apply();
                });

            return true;
        };


    }
)
;