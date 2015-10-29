app.controller('wizardReportController', function ($scope) {
    $scope.m = {};

    $scope.next = function (tabName) {
        $scope.$emit("onNextTab", tabName);
    };

    $scope.change = function(){
        $scope.$emit("onChange", {key: 'report', value: $scope.m});
    };

});