app.controller('showInfoVerifiedImputedHomesController', function ($scope, $timeout, $rootScope) {
    $scope.siv = {};

    $scope.initialize = function(){

        if($scope.listType && $scope.listType.length > 0)
        {
            if($scope.siv.typeId === undefined){
                $scope.siv.type = $scope.listType[0];
                $scope.siv.typeId = $scope.siv.type.id;
            }
            else{
                for(var i=0; i < $scope.listType.length; i++){
                    var type = $scope.listType[i];

                    if(type.id === $scope.siv.typeId){
                        $scope.siv.type =type;
                        break;
                    }
                }
            }
        }


        if($scope.lstHomeType && $scope.lstHomeType.length > 0)
        {
            if($scope.siv.homeTypeId === undefined){
                $scope.siv.homeType = $scope.lstHomeType[0];
                $scope.siv.homeTypeId = $scope.siv.homeType.id;
            }
            else{
                for(var i=0; i < $scope.lstHomeType.length; i++){
                    var bel = $scope.lstHomeType[i];

                    if(bel.id === $scope.siv.homeTypeId){
                        $scope.siv.homeType = bel;
                        break;
                    }
                }
            }
        }
    };

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