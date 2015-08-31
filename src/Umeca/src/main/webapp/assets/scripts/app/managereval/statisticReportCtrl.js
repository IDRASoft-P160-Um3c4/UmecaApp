app.controller('statisticReportController', function ($scope, $timeout, $http) {

        $scope.m = {};
        $scope.entities = []

        $scope.findReport = function (formId, urlToPost) {
            $scope.msgError = "";

            if ($scope.m.filterSelected === null || $scope.m.filterSelected === '' || $scope.m.filterSelected === undefined) {
                $scope.msgError = "Debes seleccionar un filtro de busqueda";
            }

            if ($(formId).valid() == false)
                return;

            $scope.WaitFor = true;


            var url = urlToPost + "?initDate=" + $scope.initDate + "&endDate=" + $scope.endDate + "&filterSelected=" + $scope.m.filterSelected;
            window.goToUrlMvcUrl(url);

            return true;
        };

    }
)
;