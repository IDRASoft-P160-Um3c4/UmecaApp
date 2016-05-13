app.controller('statisticReportController', function ($scope, $timeout, $http) {

        $scope.m = {};
        $scope.entities = [];
        $scope.ChannelingType = null;
        $scope.InstitutionName = null;

        $scope.init = function () {

            if($scope.lstChannelingType&&$scope.lstChannelingType.length > 0) {
                if ($scope.idParameter > 0) {
                    for (var i = 0; $scope.lstChannelingType.length; i++) {
                        if ($scope.lstChannelingType[i].id == $scope.idParameter) {
                            $scope.ChannelingType = $scope.lstChannelingType[i];
                            break;
                        }
                    }
                } else {
                    $scope.ChannelingType = $scope.lstChannelingType[0];
                }
            }

            if($scope.lstChannelingInstitutionName&&$scope.lstChannelingInstitutionName.length > 0 ){
                if($scope.idParameter>0) {
                    for (var i = 0; $scope.lstChannelingInstitutionName.length; i++) {
                        if ($scope.lstChannelingInstitutionName[i].id == $scope.idParameter) {
                            $scope.InstitutionName = $scope.lstChannelingInstitutionName[i];
                            break;
                        }
                    }
                }
                else{
                    $scope.InstitutionName = $scope.lstChannelingInstitutionName[0];
                }
            }

        };

        $timeout(function () {
            $scope.init();
        }, 500);

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

        $scope.findSupervisorReport = function (formId, urlToPost) {
            $scope.msgError = "";

            if ($scope.m.filterSelected === null || $scope.m.filterSelected === '' || $scope.m.filterSelected === undefined) {
                $scope.msgError = "Debes seleccionar un filtro de busqueda";
            }

            if ($(formId).valid() == false)
                return;

            $scope.WaitFor = true;


            var url = urlToPost + "?initDate=" + $scope.initDate + "&endDate=" + $scope.endDate + "&filterSelected=" + $scope.m.filterSelected + "&idReportType=" + $scope.idReportType + "&idDistrict="  + $scope.idDistrict;
            window.goToUrlMvcUrl(url);

            return true;
        };


        $scope.findLargeSupervisorReport = function(formId, urlToPost){
            $scope.msgError = "";
            if ($(formId).valid() == false)
                return;

            $scope.WaitFor = true;

            var url = urlToPost + "?filterSelected=" + $scope.filterSelected + "&initDate=" + $scope.initDate + "&endDate=" + $scope.endDate + "&idDistrict=" + $scope.idDistrict + "&idSupervisor=" + $scope.idSupervisor;
            window.goToUrlMvcUrl(url);

            return true;


        };

        $scope.findChannelingTypeReport = function(formId, urlToPost){
            $scope.msgError = "";

            if ($(formId).valid() == false)
                return

            $scope.WaitFor = true;

            var url = urlToPost + "?initDate=" + $scope.initDate + "&endDate=" + $scope.endDate + "&idDistrict=" + $scope.idDistrict + "&idReportType=" + $scope.idReportType + "&idParameter=" + $scope.idParameter;

          //  var url = urlToPost + "&idChannelingType=" + $scope.idChannelingType;
            window.goToUrlMvcUrl(url);
            return true;
        };


        $scope.findHumanResourceReport = function (formId, urlToPost) {
            $scope.msgError = "";

            if ($scope.m.filterSelected === null || $scope.m.filterSelected === '' || $scope.m.filterSelected === undefined) {
                $scope.msgError = "Debes seleccionar un filtro de busqueda";
            }

            if ($(formId).valid() == false)
                return;

            $scope.WaitFor = true;


            var url = urlToPost + "?initDate=" + $scope.initDate + "&endDate=" + $scope.endDate + "&filterSelected=" + $scope.m.filterSelected + "&idReportType=" + $scope.idReportType + "&idDistrict="  + $scope.idDistrict + "&idEmployee="  + $scope.idEmployee;
            window.goToUrlMvcUrl(url);

            return true;
        };


        $scope.resetDate = function(){
            $scope.endDate = null;
        };



        $scope.findHumanResourceOvertimeReport = function (formId, urlToPost,  idEmployee) {
            $scope.msgError = "";


            if ($(formId).valid() == false)
                return;

            $scope.WaitFor = true;

            var url = urlToPost + "?initDate=" + $scope.initDate + "&endDate=" + $scope.endDate + "&idEmployee="  + idEmployee;
            window.goToNewUrl(url);
            //window.showOvertime($scope.initDate, $scope.endDate, idEmployee);

            return true;
        };

    }
)
;