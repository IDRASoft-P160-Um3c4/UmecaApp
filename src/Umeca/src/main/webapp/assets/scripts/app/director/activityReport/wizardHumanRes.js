app.controller('wizardHumanResController', function ($scope, $rootScope) {
    $scope.m = {};
    $scope.m.lstAct = {};

    $scope.search = function(gridId){
        $(gridId).jqGrid('clearGridData');
        $(gridId).jqGrid('setGridParam', {postData:{startDate: $scope.m.startDate, endDate: $scope.m.endDate}}).trigger('reloadGrid');
        $scope.m.lstAct = {};
    };

    $scope.change = function(check, val){
        $scope.send();
    };

    $scope.send = function(){
        $rootScope.$broadcast("onChange", {key: "minute", value: $scope.m.lstAct});
    };

    $scope.end = function(){
        $rootScope.$broadcast("onFinish");
    };

    $scope.selectAll = function(){
        $rootScope.$broadcast('selectAllHumanResAct', $scope.m.selectAll);
    };

    $scope.next = function (tabName) {
        $scope.$emit("onNextTab", tabName);
    }

    $scope.$on("selectAllHumanResAct", function(ev, data){
        var obj = $scope.m.lstAct;
        for(var key in obj){
            var attrName = key;
            obj[key] = $scope.m.selectAll;
        }
        $scope.send();
    });
});