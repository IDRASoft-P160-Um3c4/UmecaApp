app.controller('victimController', function ($scope, $timeout, $rootScope) {
    $scope.v = {};
    $scope.lstRel = [];
    $scope.v.rel = 0;
    $scope.nameAddress = "address."
    $scope.a = {};
    $scope.v.hasInfoAddress;

    $scope.init = function () {
        if ($scope.lstRel === undefined || $scope.lstRel.length <= 0)
            return;

        if ($scope.v.relId === undefined) {
            $scope.v.rel = $scope.lstRel[0];
            $scope.v.relId = $scope.v.rel.id;
        }
        else {
            for (var i = 0; i < $scope.lstRel.length; i++) {
                var rel = $scope.lstRel[i];

                if (rel.id === $scope.v.relId) {
                    $scope.v.rel = rel;
                    break;
                }
            }
        }

        $scope.initInfoAvail();
    };

    $scope.initInfoAvail = function () {
        if ($scope.lstInfoAvail === undefined || $scope.lstInfoAvail.length <= 0)
            return;

        if ($scope.v.infoAddressId === undefined) {
            $scope.v.infoAddress = $scope.lstInfoAvail[0];
            $scope.v.infoAddressId = $scope.v.infoAddress.id;
        }
        else {
            for (var i = 0; i < $scope.lstInfoAvail.length; i++) {
                var infoAddress = $scope.lstInfoAvail[i];

                if (infoAddress.id === $scope.v.infoAddressId) {
                    $scope.v.infoAddress = infoAddress;
                    break;
                }
            }
        }

        $scope.changeInfoAvail();
    };


    $scope.changeInfoAvail = function () {
        if ($scope.v.infoAddress.specification == true) {
            $scope.a.isHomeless = false;
            $scope.v.hasInfoAddress = true;
            $rootScope.$broadcast('setNotKnowValues', true);
        }else {
            $scope.a.isHomeless = true;
            $scope.v.hasInfoAddress = false;
            $rootScope.$broadcast('setNotKnowValues', false);
        }
    };

    $timeout(function () {
        $scope.init();
    }, 0);
});