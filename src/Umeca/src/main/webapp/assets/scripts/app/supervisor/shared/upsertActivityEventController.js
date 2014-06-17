app.controller('upsertActivityEventController', function ($scope, $timeout, $q, sharedSvc) {
    $scope.Title = "";
    var th = this;
    var dlgMsgBox = $('#UpsertActivityEventDlgId');
    $scope.cfg = {};
    $scope.m = {};


    $scope.config = function(cfg){
        $scope.cfg = cfg;
        //startDateId: "#id-date-picker-start", endDateId: "#id-date-picker-end",
        //startTimeId: "#id-date-picker-start", endTimeId: "#id-date-picker-end"
    };

    $scope.hideMsg = function (rMsg) {
        th.respMsg = rMsg;
        dlgMsgBox.modal('hide');
    };

    $scope.showMsg = function(data){
        sharedSvc.showMsg(
            {
                title: data.title,
                message: data.msg,
                type: data.type
            })
    };

    $scope.showDlg = function (params) {
        $scope.msgError = "";
        $scope.clearDaysOfWeek();
        $scope.Title = params.title;
        $scope.startDt = params.start;
        $scope.endDt = params.end;
        $scope.isNew = params.isNew;

        $($scope.cfg.startDateId).datepicker('update', $scope.startDt);
        $($scope.cfg.endDateId).datepicker('update', $scope.endDt);

        $($scope.cfg.startTimeId).timepicker('setTime', window.getTimeFormat($scope.startDt, false));
        $($scope.cfg.endTimeId).timepicker('setTime', window.getTimeFormat($scope.endDt, false));


        $scope.m.daysOfWeek[$scope.startDt.getDay()] = true;

        var def = $q.defer();

        $timeout(function () {
            dlgMsgBox.modal('show');
            dlgMsgBox.on('hidden.bs.modal', function () {
                if ($scope.IsOk === true) {
                    def.resolve($scope.activities);
                }
                else {
                    def.reject();
                }
            });
        }, 1);

        return def.promise;
    };

    $scope.valid = function(){
        var isValid = false;
        $scope.msgError = "";

        for(var i=0; i<$scope.m.daysOfWeek.length; i++){
            if($scope.m.daysOfWeek[i]=== true){
                isValid = true;
                break;
            }
        }

        if(isValid === false){
            $scope.msgError = "Al menos debe seleccionar un día de la semana para añadir la actividad";
            return false;
        }

        return true;
    };

    $scope.generateActivities = function(){
        var dateInit = $($scope.cfg.startDateId).data("datepicker").getDate();
        var dateEnd = $($scope.cfg.endDateId).data("datepicker").getDate();

        dateInit.setHours(0,0,0,0);
        dateEnd.setHours(23,59,59,999);

        if(dateEnd < dateInit){
            $scope.msgError = "La fecha final no puede ser menor a la fecha inicial";
            return false;
        }

        var timeInit = window.formatTime($($scope.cfg.startTimeId).data("timepicker").getTime());
        var timeEnd = window.formatTime($($scope.cfg.endTimeId).data("timepicker").getTime());

        if(timeEnd.totSecs <= timeInit.totSecs){
            $scope.msgError = "El tiempo final no puede ser igual o menor al tiempo inicial";
            return false;
        }

        $scope.activities = [];

        var dateStepInit = new Date(dateInit);
        var dateStepEnd = new Date(dateInit);
        dateStepInit.setHours(timeInit.hours, timeInit.minutes, 0, 0);
        dateStepEnd.setHours(timeEnd.hours, timeEnd.minutes, 0, 0);

        var iCount = 0;
        while(dateStepInit < dateEnd){

            if($scope.m.daysOfWeek[dateStepInit.getDay()] === true){
                $scope.activities.push(
                    {
                        title: "Caso " + $scope.cfg.caseInfo.caseId + "  (" + $scope.cfg.caseInfo.folderId + ") Imputado: "
                            + $scope.cfg.caseInfo.personName + " " + $scope.m.arrangement.name,
                        start: dateStepInit,
                        end: dateStepEnd,
                        allDay: false,
                        className: 'label-success',
                        infoActivity:{
                            arrangement: $scope.m.arrangement,
                            activity: $scope.m.activity,
                            goal: $scope.m.goal,
                            source: $scope.m.source,
                            caseInfo: $scope.cfg.caseInfo
                        }
                    }
                );

                if(++iCount > 100){
                    $scope.msgError = "No puede añadir más de 100 actividades";
                    return false;
                }
            }

            dateStepInit = new Date(dateStepInit.getFullYear(), dateStepInit.getMonth(), dateStepInit.getDate() + 1);
            dateStepEnd = new Date(dateStepEnd.getFullYear(), dateStepEnd.getMonth(), dateStepEnd.getDate() + 1);
            dateStepInit.setHours(timeInit.hours, timeInit.minutes, 0, 0);
            dateStepEnd.setHours(timeEnd.hours, timeEnd.minutes, 0, 0);
        }

        if(iCount === 0){
            $scope.msgError = "Al menos debe seleccionar un día de la semana dentro del intervalo de tiempo que eligió para crear al menos una actividad";
            return false;
        }

        return true;
    };

    $scope.save = function () {

        if($scope.valid() === false)
            return false;

        if($scope.generateActivities() === false)
            return false;

        $scope.IsOk = true;
        $scope.hideMsg($scope);
    };


    $scope.cancel = function () {
        $scope.IsOk = false;
        $scope.hideMsg($scope);
    };

    $scope.onBusinessWeek = function(){

        if($scope.m.chkBusinessWeek){
            $scope.m.daysOfWeek = [false, true, true, true, true, true, false];
            $scope.m.chkWeek = false;
        }
        else{
            $scope.clearDaysOfWeek();
        }

    };

    $scope.onWeek = function(){
        if($scope.m.chkWeek){
            $scope.m.daysOfWeek = [true, true, true, true, true, true, true];
            $scope.m.chkBusinessWeek = false;
        }
        else{
            $scope.clearDaysOfWeek();
        }
    };

    $scope.clearDaysOfWeek = function(){
        $scope.m.daysOfWeek = [false, false, false, false, false, false, false];
    };

    $scope.clearDaysOfWeek();
});