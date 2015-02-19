app.controller('managerSupReportController', function ($scope, $timeout, $http, $q, $sce, $rootScope) {

        $scope.m = {};
        $scope.MsgError = "";
        $scope.MsgErrorSel = "";

        $scope.submitReport = function (formId, urlToPost, validate) {

            $scope.Invalid = false;

            if ($(formId).valid() == false) {
                $scope.Invalid = true;
            }

            if (validate && validate() == false) {
                $scope.Invalid = true;
            }

            if ($scope.Invalid == true) {
                return false;
            }

            window.goToUrlMvcUrl(urlToPost, $(formId).serialize());

            return true;
        };

        $scope.validateSel = function () {
            var result = false;
            result = result || $scope.m.casesMC;
            result = result || $scope.m.casesSCPP;
            result = result || $scope.m.imputedDrugs;
            result = result || $scope.m.casesDone;
            result = result || $scope.m.casesPrison;
            result = result || $scope.m.casesNonFulfillment;
            result = result || $scope.m.casesSubtracted;
            result = result || $scope.m.homeVisit;
            result = result || $scope.m.civilOrg;
            result = result || $scope.m.initDate;
            result = result || $scope.m.endDate;

            if (result == false || result == undefined) {
                result = false;
                $scope.MsgErrorSel = $sce.trustAsHtml("Debe seleccionar al menos una opci&oacute;n.");
            } else {
                $scope.MsgErrorSel = $sce.trustAsHtml("");
            }

            return result;
        }

        $scope.returnMidLength = function (array) {
            /*if(array!=undefined&&array.length>1){
                if(array.length%2!=0)
                    alert(array.length/2);
            }
            return -1;*/
            alert($scope.lstOpts.length/2);
        };

        $scope.init = function () {
        };

        $timeout(function () {
            $scope.init();
        }, 0);

    }
)
;