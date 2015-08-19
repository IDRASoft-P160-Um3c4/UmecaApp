app.controller('showInfoVerifiedImputedHomesController', function ($scope, $timeout, $rootScope) {

    //$scope.init = function(){
    //};

    //$timeout(function() {
    //    $scope.init();
    //}, 0);
    $scope.lstSourceInfoHomes = {};

    $rootScope.$on('showAnswered', function (event, lstFieldInfo) {
        for (var a = 0; a < lstFieldInfo.length; a++) {
            if (lstFieldInfo[a].sectionCode == 2) {
                $scope.lstSourceInfoHomes[lstFieldInfo[a].code + "." + lstFieldInfo[a].idFieldList] = true;
            }
        }
        $scope.$apply();
    });

    $scope.lstFinalInfoHomes = {};
    $rootScope.$on('showFinalAnswered', function (event, lstFieldInfo) {
        for (var a = 0; a < lstFieldInfo.length; a++) {
            if (lstFieldInfo[a].sectionCode == 2) {
                $scope.lstFinalInfoHomes[lstFieldInfo[a].code + "." + lstFieldInfo[a].idFieldList] = true;
            }
        }
        $scope.$apply();
    });

});