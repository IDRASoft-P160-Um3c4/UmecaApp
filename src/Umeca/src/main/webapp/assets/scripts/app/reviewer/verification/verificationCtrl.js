app.controller('verificationController', function($scope, $timeout, $q) {
    $scope.def= $q.defer();
    $scope.verification=true;
    $scope.init = function(){
    };

    $timeout(function() {
        $scope.init();
    }, 0);

});