app.controller('rolSupervisionController', function ($scope, sharedSvc) {

    $scope.lstActivityDelIds = [];
    $scope.msgError = undefined;
    $scope.waitFor = false;


    $scope.addActivityToDelete = function(id){
        if(id === -1)
            return;
        $scope.lstActivityDelIds.push(id);
    }

    $scope.saveRolActivities = function(urlToPost){
        $scope.msgError = undefined;
        $scope.waitFor = true;

        try{
            var lstEvents = $scope.m.calendar.fullCalendar('clientEvents');

            var lstActivities = [];

            for(var i=0; i<lstEvents.length; i++){
                var event = lstEvents[i];

                if(event.isModified !== true)
                    continue;

                var infoAct = event.infoActivity;

                var start = window.formatDateTime(event.start);
                var end = window.formatDateTime(event.end);

                lstActivities.push({
                    rolActivityId: event.idActivity,
                    eventId : event._id,
                    end: end,
                    start: start,
                    supervisorId: infoAct.supervisor.id
                });
            }

            if(lstActivities.length === 0 && $scope.lstActivityDelIds.length === 0){
                sharedSvc.showMsg({title: "Rol de supervisión",message: "No existen actividades para agregar, actualizar o eliminar",type: "info"});
                $scope.waitFor = false;
                return false;
            }

            var activityUpsert = {lstActivitiesUpsert:lstActivities, lstActivitiesDel: $scope.lstActivityDelIds};

            $.ajax({
                url: urlToPost,
                type: "POST",
                data: JSON.stringify(activityUpsert),
                success: $scope.handleSuccess,
                error: $scope.handleError,
                dataType: "json",
                contentType: "application/json"
            });
        }catch(e){
            $scope.waitFor = false;
        }


    }

    $scope.handleSuccess = function(resp){
        try{
            $scope.waitFor = false;

            if (resp.hasError === undefined || resp.hasError === true) {
                $scope.msgError = resp.message;
                $scope.$apply();
                return;
            }
            else if(resp.hasError === false){
                $scope.lstActivityDelIds = [];
                try{
                    var lstEvents = JSON.parse(resp.returnData);
                    for(var i=0; i<lstEvents.length; i++){
                        var eventInfo = lstEvents[i];
                        var event = $scope.m.calendar.fullCalendar('clientEvents', eventInfo.eventId);

                        if(event !== undefined && event.length > 0){
                            var fstEvent = event[0];
                            fstEvent.idActivity = eventInfo.rolActivityId;
                            fstEvent.doTitle(false);
                            fstEvent.isModified = false;
                            $scope.m.calendar.fullCalendar('updateEvent', event);
                        }
                    }

                }catch(eIn){}
                sharedSvc.showMsg({title: "Rol de supervisión",message: resp.message,type: "success"}).then();
            }
            $scope.$apply();
        } catch (e) {
            $scope.msgError = "Error inesperado de datos. Por favor intente más tarde.";
            $scope.$apply();
        }
    };


    $scope.handleError = function(){
        $scope.waitFor = false;
        $scope.msgError = "Error de red. Por favor intente más tarde.";
        $scope.$apply();

    };
});