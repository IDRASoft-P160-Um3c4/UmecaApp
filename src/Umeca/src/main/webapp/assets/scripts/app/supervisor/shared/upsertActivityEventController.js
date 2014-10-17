app.controller('upsertActivityEventController', function ($scope, $timeout, $q, sharedSvc, $sce) {
    $scope.Title = "";
    var th = this;
    var dlgMsgBox = $('#UpsertActivityEventDlgId');
    $scope.cfg = {};
    $scope.m = {};
    $scope.m.lstArrangements = {};

    $scope.config = function (cfg, lstArrangements, lstActivities, lstGoals, lstSources) {
        $scope.cfg = cfg;
        $scope.lstArrangements = lstArrangements;
        $scope.lstActivities = lstActivities;
        $scope.lstGoals = lstGoals;
        $scope.lstSources = lstSources;

        if ($scope.lstActivities.length > 0) $scope.m.activity = $scope.lstActivities[0];
        if ($scope.lstGoals.length > 0) $scope.m.goal = $scope.lstGoals[0];
        if ($scope.lstSources.length > 0) $scope.m.source = $scope.lstSources[0];
    };

    $scope.hideMsg = function () {
        dlgMsgBox.modal('hide');
    };

    $scope.showMsg = function (data) {
        sharedSvc.showMsg(
            {
                title: data.title,
                message: $sce.trustAsHtml(data.msg),
                type: data.type
            })
    };

    $scope.fillFields = function (event) {
        $scope.m.event = event;
        $scope.m.lstArrangements = event.infoActivity.lstArrangements;
        $scope.m.activity = event.infoActivity.activity;
        $scope.m.goal = event.infoActivity.goal;
        $scope.m.source = event.infoActivity.source;

    };

    $scope.showDlg = function (params) {
        $scope.msgError = "";
        $scope.clearDaysOfWeek();
        $scope.Title = params.title;
        $scope.startDt = params.start;
        $scope.endDt = params.end;
        $scope.isNew = params.isNew;
        $scope.isReadOnly = params.isReadOnly;
        $scope.activities = [];
        $scope.m.lstArrangements = {};
        $scope.m.chkBusinessWeek = false;
        $scope.m.chkWeek = false;
        $scope.lstPeriodicity = [
            {id: 1, name: 'Semana'},
            {id: 2, name: 'Quincena'},
            {id: 3, name: 'Mes'},
            {id: 4, name: 'Bimestre'},
            {id: 5, name: 'Trimestre'},
            {id: 6, name: 'Semestre'}
        ];
        $scope.m.periodicity = $scope.lstPeriodicity[0];
        $scope.m.periodicityId = $scope.m.periodicity.id;
        $scope.m.daysOfMonth = [];
        for (var i = 0; i < 31; i++) {
            $scope.m.daysOfMonth.push(false);
        }

        if (params.isNew === false) {
            $scope.fillFields(params.event);
        }
        else {
            $scope.m.event = undefined;
        }

        var startTime = window.getTimeFormat($scope.startDt, false);
        var endTime = window.getTimeFormat($scope.endDt, false);

        if (startTime === endTime)
            endTime = "23:59:59";


        var dateInit = new Date($scope.startDt);
        var dateEnd = new Date($scope.endDt);
        dateInit.setHours(0, 0, 0, 0);
        dateEnd.setHours(0, 0, 0, 0);

        $($scope.cfg.startDateId).datepicker('update', dateInit);
        $($scope.cfg.endDateId).datepicker('update', dateEnd);

        $($scope.cfg.startTimeId).timepicker('setTime', startTime);
        $($scope.cfg.endTimeId).timepicker('setTime', endTime);

        $scope.m.daysOfWeek[$scope.startDt.getDay()] = true;

        var def = $q.defer();

        $timeout(function () {
            dlgMsgBox.modal('show');
            dlgMsgBox.on('hidden.bs.modal', function () {
                if ($scope.IsOk === true) {
                    def.resolve({activities: $scope.activities, option: $scope.option, event: $scope.m.event});
                }
                else {
                    def.reject();
                }
            });
        }, 1);

        return def.promise;
    };

    $scope.valid = function () {
        var isValid = false;
        $scope.msgError = "";

        for (var key in $scope.m.lstArrangements) {
            if ($scope.m.lstArrangements[key] === true) {
                isValid = true;
                break;
            }
        }

        if (isValid === false) {
            $scope.msgError = "Al menos debe seleccionar una obligación procesal";
            return false;
        }

        isValid = false;

        for (var i = 0; i < $scope.m.daysOfWeek.length; i++) {
            if ($scope.m.daysOfWeek[i] === true) {
                isValid = true;
                break;
            }
        }

        if (isValid === false) {
            $scope.msgError = "Al menos debe seleccionar un día de la semana para añadir la actividad";
            return false;
        }

        return true;
    };

    $scope.validateDates = function (d) {
        d.dateInit = $($scope.cfg.startDateId).data("datepicker").getDate();
        d.dateEnd = $($scope.cfg.endDateId).data("datepicker").getDate();

        d.timeInit = window.formatTime($($scope.cfg.startTimeId).data("timepicker").getTime());
        d.timeEnd = window.formatTime($($scope.cfg.endTimeId).data("timepicker").getTime());

        d.dateInit.setHours(d.timeInit.hours, d.timeInit.minutes, 0, 0);
        d.dateEnd.setHours(d.timeEnd.hours, d.timeEnd.minutes, 0, 0);

        if (d.dateEnd < d.dateInit) {
            $scope.msgError = "La fecha final no puede ser menor a la fecha inicial";
            return false;
        }
        /*
         if(d.timeEnd.totSecs <= d.timeInit.totSecs){
         $scope.msgError = "El tiempo final no puede ser igual o menor al tiempo inicial";
         return false;
         } */
        return true;
    };

    $scope.generateActivities = function () {

        var d = {};
        if ($scope.validateDates(d) === false)
            return false;

        var today = new Date();
        today.setHours(0, 0, 0, 0);

        $scope.activities = [];

        var dateStepInit = new Date(d.dateInit);
        var dateStepEnd = new Date(d.dateInit);
        dateStepInit.setHours(d.timeInit.hours, d.timeInit.minutes, 0, 0);
        dateStepEnd.setHours(d.timeEnd.hours, d.timeEnd.minutes, 0, 0);

        var iCount = 0;
        var hasEvent, stepMonth, itMonth, isNextBusinessDay = false, group = window.generateUuid();
        var iWkStep = $scope.m.periodicityId;

        if ($scope.m.periodicityId > 2) {
            switch ($scope.m.periodicityId) {
                case 4:
                    stepMonth = 2;
                    break;
                case 5:
                    stepMonth = 3;
                    break;
                case 6:
                    stepMonth = 6;
                    break;
                default:
                    stepMonth = 1;
                    break;
            }
        }

        itMonth = 0;
        var iWkCount = 0, iFirstDayWk = undefined;

        while (dateStepInit < d.dateEnd || isNextBusinessDay) {
            hasEvent = false;
            if ($scope.m.periodicityId < 3) {
                var dayWk = dateStepInit.getDay();
                if ($scope.m.daysOfWeek[dayWk] === true) {
                    if(iFirstDayWk === undefined)
                        iFirstDayWk = dayWk;

                    if(iWkCount % iWkStep === 0)
                        hasEvent = true;

                    if(dayWk === iFirstDayWk)
                        iWkCount++;
                }
            } else {
                if (isNextBusinessDay || ((itMonth % stepMonth === 0) && $scope.m.daysOfMonth[dateStepInit.getDate() - 1] === true)) {
                    isNextBusinessDay = false;
                    if ($scope.m.sundayNoBusiness === true && dateStepInit.getDay() === 0) {
                        isNextBusinessDay = true;
                    }
                    else {
                        hasEvent = true;
                    }
                }
            }

            if (hasEvent === true && dateStepInit < today) {
                $scope.msgError = "No es posible añadir actividades antes de la fecha actual";
                return false;
            }

            if (hasEvent) {
                var eventAct = {
                    title: "",
                    doTitle: function (isModified) {
                        this.title = (isModified === true ? "*" : "") + "Caso "
                            + this.infoActivity.caseInfo.caseId + "  (" + this.infoActivity.caseInfo.mpId + ") Imputado: "
                            + this.infoActivity.caseInfo.personName + " / " + this.infoActivity.activity.name + " / " + this.infoActivity.goal.name;
                    },
                    idActivity: -1,
                    start: dateStepInit,
                    end: dateStepEnd,
                    allDay: false,
                    isModified: true,
                    className: ($scope.cfg.caseInfo.isInAuthorizeReady ? 'label-pre-new' : 'label-info'),
                    infoActivity: {
                        lstArrangements: $scope.m.lstArrangements,
                        activity: $scope.m.activity,
                        goal: $scope.m.goal,
                        source: $scope.m.source,
                        caseInfo: $scope.cfg.caseInfo
                    },
                    groupEvt: group
                };

                eventAct.doTitle(true);
                $scope.activities.push(eventAct);

                if (++iCount > 100) {
                    $scope.msgError = "No puede añadir más de 100 actividades";
                    return false;
                }
            }

            var oldMonth = dateStepInit.getMonth();
            dateStepInit = new Date(dateStepInit.getFullYear(), dateStepInit.getMonth(), dateStepInit.getDate() + 1);
            dateStepEnd = new Date(dateStepEnd.getFullYear(), dateStepEnd.getMonth(), dateStepEnd.getDate() + 1);
            dateStepInit.setHours(d.timeInit.hours, d.timeInit.minutes, 0, 0);
            dateStepEnd.setHours(d.timeEnd.hours, d.timeEnd.minutes, 0, 0);
            var newMonth = dateStepInit.getMonth();

            if (oldMonth !== newMonth)
                itMonth++;
        }

        if (iCount === 0) {
            $scope.msgError = "Al menos debe seleccionar un día de la semana dentro del intervalo de tiempo que eligió para crear al menos una actividad";
            return false;
        }

        return true;
    };

    $scope.add = function () {

        if ($scope.valid() === false)
            return false;

        if ($scope.generateActivities() === false)
            return false;

        $scope.IsOk = true;
        $scope.hideMsg();
    };

    $scope.save = function () {
        var today = new Date();
        today.setHours(0, 0, 0, 0);

        var d = {};
        if ($scope.validateDates(d) === false)
            return false;

        if (d.dateInit < today) {
            $scope.msgError = "No es posible modificar la actividad ya que la fecha de inicio está definida antes de la fecha actual";
            return false;
        }

        var dateInit = new Date(d.dateInit);
        var dateEnd = new Date(d.dateEnd);
        dateInit.setHours(d.timeInit.hours, d.timeInit.minutes, 0, 0);
        dateEnd.setHours(d.timeEnd.hours, d.timeEnd.minutes, 0, 0);

        $scope.m.event.start = dateInit;
        $scope.m.event.end = dateEnd;
        $scope.m.event.isModified = true;
        $scope.m.event.className = $scope.cfg.caseInfo.isInAuthorizeReady ? ($scope.m.event.className == 'label-pre-new' ? 'label-pre-new' : 'label-pre-update') : 'label-info';

        $scope.m.event.infoActivity = {
            lstArrangements: $scope.m.lstArrangements,
            activity: $scope.m.activity,
            goal: $scope.m.goal,
            source: $scope.m.source,
            caseInfo: $scope.cfg.caseInfo
        };

        $scope.m.event.doTitle(true);

        $scope.IsOk = true;
        $scope.option = "UPDATE";
        $scope.hideMsg();
    };

    $scope.delete = function () {
        $scope.IsOk = true;
        $scope.option = (($scope.cfg.caseInfo.isInAuthorizeReady && $scope.m.event.className != 'label-pre-new') ? "PRE_REMOVE" : "REMOVE");
        $scope.hideMsg();
    };

    $scope.deleteGroup = function () {
        $scope.IsOk = true;
        $scope.option = (($scope.cfg.caseInfo.isInAuthorizeReady && $scope.m.event.className != 'label-pre-new') ? "PRE_REMOVE_GROUP" : "REMOVE_GROUP");
        $scope.hideMsg();
    };

    $scope.cancel = function () {
        $scope.IsOk = false;
        $scope.hideMsg();
    };

    $scope.onBusinessWeek = function () {

        if ($scope.m.chkBusinessWeek) {
            $scope.m.daysOfWeek = [false, true, true, true, true, true, false];
            $scope.m.chkWeek = false;
        }
        else {
            $scope.clearDaysOfWeek();
        }

    };

    $scope.onWeek = function () {
        if ($scope.m.chkWeek) {
            $scope.m.daysOfWeek = [true, true, true, true, true, true, true];
            $scope.m.chkBusinessWeek = false;
        }
        else {
            $scope.clearDaysOfWeek();
        }
    };

    $scope.clearDaysOfWeek = function () {
        $scope.m.daysOfWeek = [false, false, false, false, false, false, false];
    };

    $scope.range = function (first, last, step) {
        if (step === undefined) step = 1;
        var out = [];
        for (var i = first; i < last; i += step) {
            out.push(i);
        }
        return out;
    };

    $scope.onAllDays = function () {
        var i;
        if ($scope.m.chkAllDays) {
            for (i = 0; i < $scope.m.daysOfMonth.length; i++) {
                $scope.m.daysOfMonth[i] = true;
            }
        }
        else {
            for (i = 0; i < $scope.m.daysOfMonth.length; i++) {
                $scope.m.daysOfMonth[i] = false;
            }
        }
    };

    $scope.clearDaysOfWeek();

});