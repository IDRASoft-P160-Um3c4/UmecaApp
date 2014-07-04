app.controller('monActivityController', function($scope, $location, $anchorScroll, $timeout){
    $scope.m = {};
    $scope.m.do = false;
    $scope.m.notDo = false;
    $scope.isReadOnly = true;

    $scope.do = function(){
        $scope.m.do = true;
        $scope.m.notDo = !($scope.m.do);
        $scope.m.commentsFail = "";
        $timeout(function(){
            $location.hash("btn-act-footer");
            $anchorScroll();
        },1);
    };

    $scope.notDo = function(){
        $scope.m.notDo = true;
        $scope.m.do = !($scope.m.notDo);
        $scope.m.commentsOk = "";
        $timeout(function(){
            $location.hash("btn-act-footer");
            $anchorScroll();
        },1);
    };

});