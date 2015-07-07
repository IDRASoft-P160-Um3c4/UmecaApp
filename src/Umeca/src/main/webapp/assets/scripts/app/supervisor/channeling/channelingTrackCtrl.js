app.controller('channelingTrackController', function($scope, $sce) {
    $scope.m = {};

    $scope.initReschedule = function(){
    };

    $scope.submitRescheduleIfValid = function(formId, urlToPost, hasReturnId, isValid, submit){
        $scope.MsgError = $sce.trustAsHtml("");
        if($scope.validateDates() == false)
            return;

        submit(formId, urlToPost, hasReturnId, isValid);
    };

    $scope.validateDates = function(){
        try{
            var dateSplit = $scope.m.dateStart.split("-");
            var dateStart = new Date(parseInt(dateSplit[2]), parseInt(dateSplit[1]) - 1, parseInt(dateSplit[0]), 0, 0, 0, 0);
            var dateEnd = new Date(dateStart);

            var today = new Date();
            today.setHours(0, 0, 0, 0);

            if(dateStart < today){
                $scope.MsgError = $sce.trustAsHtml("La fecha final no puede ser menor a la fecha actual");
                return false;
            }

            var dateSplit = $scope.m.timeStart.split(":");
            dateStart.setHours(dateSplit[0], dateSplit[1], 0, 0);

            dateSplit = $scope.m.timeEnd.split(":");
            dateEnd.setHours(dateSplit[0], dateSplit[1], 0, 0);

            if (dateEnd <= dateStart) {
                $scope.MsgError = $sce.trustAsHtml("La fecha final no puede ser menor o igual a la fecha inicial");
                return false;
            }

            return true;

        }catch(e){
            return false;
        }
    };

});