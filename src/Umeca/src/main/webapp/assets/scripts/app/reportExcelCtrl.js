app.controller('reportExcelController', function ($scope, $timeout, $http) {

        $scope.lstStatusCase = [];
        $scope.lstStatusMeeting = [];
        $scope.lstStatusVerification = [];
        $scope.lstGender = [];
        $scope.lstMaritalSt = [];
        $scope.lstJob = [];
        $scope.lstAcademicLvl = [];
        $scope.lstDrugs = [];
        $scope.lstLvlRisk = [];
        $scope.lstHearingType = [];

        $scope.m = {};
        $scope.m.filtersModel = {};

        $scope.init = function () {

        };

        $timeout(function () {
            $scope.init();
        }, 0);


        $scope.addIdToList = function (listName, id) {

            var idx = $scope.findIdInList(listName, id);

            if (idx != null) {
                $scope[listName].splice(idx, 1);
            } else {
                $scope[listName].push(id);
            }

        }
        ;

        $scope.findIdInList = function (listName, id) {

            if ($scope[listName] && $scope[listName] != undefined) {
                for (var i = 0; i < $scope[listName].length; i++) {
                    if ($scope[listName][i] == id)
                        return i;
                }
            }

            return null;
        };

        $scope.submitFindCases = function (formId, urlToPost) {

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

                        $scope.fillFiltersModel();

                        window.reloadExcelGrid(resp.message, $scope.m.filtersModel);


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

        $scope.fillFiltersModel = function () {

            $scope.m.filtersModel["iDt"] = $scope.initDate;
            $scope.m.filtersModel["eDt"] = $scope.endDate;
            $scope.m.filtersModel["mP"] = $scope.hasMonP;
            $scope.m.filtersModel["hJ"] = $scope.hasJob;

            $scope.m.filtersModel["l1"] = $scope.lstStatusCase;
            $scope.m.filtersModel["l2"] = $scope.lstStatusMeeting;
            $scope.m.filtersModel["l3"] = $scope.lstStatusVerification;
            $scope.m.filtersModel["l4"] = $scope.lstGender;
            $scope.m.filtersModel["l5"] = $scope.lstMaritalSt;
            $scope.m.filtersModel["l6"] = $scope.lstJob;
            $scope.m.filtersModel["l7"] = $scope.lstAcademicLvl;
            $scope.m.filtersModel["l8"] = $scope.lstDrugs;
            $scope.m.filtersModel["l9"] = $scope.lstLvlRisk;
            $scope.m.filtersModel["l10"] = $scope.lstHearingType;

        };

    }
)
;