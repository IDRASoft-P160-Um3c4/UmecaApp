app.controller('calendarTaskDiaryController', function ($scope, $timeout, $sce, sharedSvc) {
    $scope.m = {};
    $scope.waitFor = false;
    $scope.lstActivityDelIds = [];
    $scope.lstFullActivities = [];

    $scope.initActivitySelect = function(){
        $scope.lstPriorities.unshift({id:-1, name:'-- Todas las prioridades --'});
        $timeout(function(){
            $scope.m.priority = $scope.lstPriorities[0];
        }, 1);
    };

    $scope.formatHtml = function(sHtml){
        return $sce.trustAsHtml(sHtml);
    };

    $scope.saveActivities = function (urlToPost) {
        $scope.msgError = undefined;
        $scope.waitFor = true;

        try {
            var lstEvents = $scope.lstFullActivities;

            var lstActivities = [];

            for (var i = 0; i < lstEvents.length; i++) {
                var event = lstEvents[i];

                if (event.isModified !== true)
                    continue;

                var start = window.formatDateTime(event.start);
                var end = window.formatDateTime(event.end);

                lstActivities.push({
                    activityId: event.activityId,
                    eventId: event._id,
                    end: end,
                    start: start,
                    place: event.m.place,
                    description: event.m.description,
                    priorityId: event.m.priorityId
                });
            }

            if (lstActivities.length === 0 && $scope.lstActivityDelIds.length === 0) {
                sharedSvc.showMsg({title: "Agenda de actividades", message: "No existen actividades para agregar, actualizar o eliminar", type: "warning"});
                $scope.waitFor = false;
                return false;
            }

            var activityUpsert = {lstActivitiesUpsert: lstActivities, lstActivitiesDel: $scope.lstActivityDelIds};

            $.ajax({
                url: urlToPost,
                type: "POST",
                data: JSON.stringify(activityUpsert),
                success: $scope.handleSuccess,
                error: $scope.handleError,
                dataType: "json",
                contentType: "application/json"
            });
        } catch (e) {
            $scope.waitFor = false;
        }
    };

    $scope.addActivityToDelete = function (event) {
        if (event.activityId === -1){
            $scope.lstFullActivities = $scope.lstFullActivities.filter(function(e){ if(e._id !== event._id) return true; });
            return;
        }
        $scope.lstActivityDelIds.push(id);
    };

    $scope.handleSuccess = function (resp) {
        try {
            $scope.waitFor = false;

            if (resp.hasError === undefined || resp.hasError === true) {
                $scope.msgError = resp.message;
                $scope.$apply();
                return;
            }
            else if (resp.hasError === false) {

                try {
                    $scope.lstActivityDelIds = [];
                    var lstEvents = JSON.parse(resp.returnData);
                    for (var i = 0; i < lstEvents.length; i++) {
                        var eventInfo = lstEvents[i];
                        var event = $scope.m.calendar.fullCalendar('clientEvents', eventInfo.eventId);

                        if (event !== undefined && event.length > 0) {
                            var fstEvent = event[0];
                            fstEvent.activityId = eventInfo.activityId;
                            fstEvent.isModified = false;
                            fstEvent.doTitle();
                            fstEvent.hasTo = 0; //0-nothing, 1-insert, 2-update, 3-delete

                            $scope.m.calendar.fullCalendar('updateEvent', event);
                        }
                    }

                } catch (eIn) {
                }
                sharedSvc.showMsg({title: "Agenda de actividades", message: resp.message, type: "success"}).then();
            }
            $scope.$apply();
        } catch (e) {
            $scope.msgError = "Error inesperado de datos. Por favor intente más tarde.";
            $scope.$apply();
        }
    };


    $scope.handleError = function () {
        $scope.waitFor = false;
        $scope.msgError = "Error de red. Por favor intente más tarde.";
        $scope.$apply();

    };

    $scope.loadActivities = function(dateStart, dateEnd, urlToPost){

        var yearStart = dateStart.getFullYear();
        var monthStart = dateStart.getMonth();
        var yearEnd = dateEnd.getFullYear();
        var monthEnd = dateEnd.getMonth();

        if(yearStart === yearEnd && monthStart === monthEnd){
            if($scope.yearStart === yearStart && $scope.monthStart === monthStart ||
                $scope.yearEnd === yearEnd && $scope.monthEnd === monthEnd){
                return;
            }
        }
        else{
            if($scope.yearStart === yearStart && $scope.monthStart === monthStart &&
                $scope.yearEnd === yearEnd && $scope.monthEnd === monthEnd){
                return;
            }
        }

        $scope.yearStart = dateStart.getFullYear();
        $scope.monthStart = dateStart.getMonth();
        $scope.yearEnd = dateEnd.getFullYear();
        $scope.monthEnd = dateEnd.getMonth();
        $scope.workingTrack = true;
        $scope.$apply();

        $.ajax({
            url: urlToPost,
            type: "POST",
            data: JSON.stringify({yearStart: $scope.yearStart, monthStart: ($scope.monthStart+1),
                yearEnd: $scope.yearEnd, monthEnd: ($scope.monthEnd+1)}),
            success: $scope.handleLoadSuccess,
            error: $scope.handleLoadError,
            dataType: "json",
            contentType: "application/json"
        });
    };


    $scope.handleLoadSuccess = function(data){
        try{
            $scope.workingTrack = false;
            $scope.$apply();
            if(data.hasError === true){
                sharedSvc.showMsg({title: "Agenda de actividades",message: data.message,type: "danger"});
                return;
            }

            var lstActivities = $scope.processActivities(data);

            $scope.mergeAndFilterActivities(lstActivities);

        }catch(ex){
            sharedSvc.showMsg({
                title: "Error de red",
                message: "<strong>No fue posible conectarse al servidor</strong> <br/><br/>Por favor intente más tarde",
                type: "danger"
            });
        }
    };

    $scope.handleLoadError = function(data){
        $scope.workingTrack = false;
        sharedSvc.showMsg({
            title: "Error de red",
            message: "<strong>No fue posible conectarse al servidor</strong> <br/><br/>Por favor intente más tarde",
            type: "danger"
        });
    };

    $scope.idToName = function(id, lstCat){
        for(var i=0; i<lstCat.length; i++){
            var dat = lstCat[i];
            if(id === dat.id){
                return dat.name;
            }
        }
        return "";
    };

    $scope.mergeAndFilterActivities = function(lstActivities){

        //merge
        var hasToAdd;
        for(var j=0; j<lstActivities.length; j++){
            var loadAct = lstActivities[j];
            hasToAdd = true;
            for(var i=0; i<$scope.lstFullActivities.length; i++){
                var curAct = $scope.lstFullActivities[i];
                if((loadAct.activityId !== -1 && loadAct.activityId === curAct.activityId)
                    || (loadAct.activityId === -1 && loadAct.eventId !== undefined && loadAct.eventId === curAct.eventId)){
                    hasToAdd = false;
                    break;
                }
            }

            if(hasToAdd === true)
                $scope.lstFullActivities.push(loadAct);

        }

        //filter
        for(i=0; i<$scope.lstFullActivities.length; i++){
            var curAct = $scope.lstFullActivities[i];


            if($scope.m.priority.id === -1 || $scope.m.priority.id === curAct.m.priority.id){
                switch(curAct.hasTo){
                    case 1:
                        $scope.m.calendar.fullCalendar('renderEvent', curAct, true);
                        break;
                    case 2:
                        var event = $scope.m.calendar.fullCalendar('clientEvents', curAct.eventId);
                        if (event !== undefined && event.length > 0) {
                            var fstEvent = event[0];
                            fstEvent.doTitle();
                            $scope.m.calendar.fullCalendar('updateEvent', event);
                        }
                        break;
                }
                curAct.hasTo = 0;
            }
        }

        $scope.m.calendar.fullCalendar('unselect');

    };

    $scope.onChangePriority = function(){
        $scope.m.calendar.fullCalendar('removeEvents');
        for(i=0; i<$scope.lstFullActivities.length; i++) {
            $scope.lstFullActivities[i].hasTo = 1;
            if($scope.lstFullActivities[i]['_id'] !== undefined){
                $scope.lstFullActivities[i].className = $scope.lstFullActivities[i].className[0];
                delete $scope.lstFullActivities[i]['_id'];
                delete $scope.lstFullActivities[i]['_end'];
                delete $scope.lstFullActivities[i]['_start'];
                delete $scope.lstFullActivities[i]['source'];
            }
        }
        $scope.mergeAndFilterActivities([]);
    };

    $scope.processActivities = function(data){
        var lstPriorities = $scope.lstPriorities;
        var lstAgendaActivities = data.lstAgendaActivities;
        var lstEvents = [];
        var today = window.stringToDate(data.today);
        today.setHours(0,0,0,0);

        for(var i=0; i<lstAgendaActivities.length; i++){
            var act = lstAgendaActivities[i];
            var end = window.stringToDate(act.end);

            var className = window.changeByStatus(act.status, act.priorityId);

            var event = {
                title: "",
                doTitle: function () {
                    this.title = (this.isModified === true ? "* " : "") + this.m.description + ' / ' + this.m.place
                },
                activityId: act.activityId,
                start: window.stringToDate(act.start),
                end: window.stringToDate(act.end),
                m: {
                    priorityId : act.priorityId,
                    place: act.place,
                    description: act.description,
                    priority: window.idToObject(act.priorityId, $scope.lstPriorities),
                    comments : act.comments
                },
                isModified : false,
                className: className,
                allDay: false,
                actIsDone: (act.status === 'REALIZADA' ? true : false),
                hasTo: 1 //1-insert, 2-update, 3-delete
            };

            event.doTitle();
            lstEvents.push(event);
        }
        return lstEvents;
    };
});

