app.controller('upsertActivityEventController', function ($scope, $timeout, $q, $sce, sharedSvc) {
    var th = this;
    var dlgMsgBox = $('#UpsertActivityEventDlgId');

    $scope.Title = "";
    $scope.cfg = {};
    $scope.m = {};

    $scope.config = function (cfg) {
        $scope.cfg = cfg;
    };

    $scope.hideMsg = function () {
        dlgMsgBox.modal('hide');
    };

    $scope.showMsg = function (data) {
        sharedSvc.showMsg(
            {
                title: data.title,
                message: data.msg,
                type: data.type
            })
    };

    $scope.showDlg = function (params) {
        $scope.msgError = "";
        $scope.Title = params.title;
        $scope.startDt = new Date(params.start);
        $scope.endDt = new Date(params.end);
        $scope.isReadOnly = params.isReadOnly;
        $scope.isNew = params.isNew;
        $scope.setRes = false;

        $scope.canSetRes = params.event !== undefined && params.event.activityId !== -1;
        $scope.m = {};

        if(params.isNew === false){
            $scope.eventSel = params.event;
            $scope.actIsDone = params.event.actIsDone;
            $scope.m.place = params.event.m.place;
            $scope.m.description = params.event.m.description;
            $scope.m.comments = params.event.m.comments;
            $scope.m.priority = window.idToObject(params.event.m.priorityId, $scope.cfg.lstPriorities);
            $scope.m.priorityId = $scope.m.priority.id;

            if($scope.actIsDone)
                $scope.isReadOnly = true;
        }
        else{
            $scope.m.priority = $scope.cfg.lstPriorities[0];
            $scope.m.priorityId = $scope.m.priority.id;
        }

        var startTime = window.getTimeFormat($scope.startDt, false);
        var endTime = window.getTimeFormat($scope.endDt, false);
        $($scope.cfg.startTimeId).timepicker('setTime', startTime);
        $($scope.cfg.endTimeId).timepicker('setTime', endTime);

        var def = $q.defer();

        $timeout(function () {
            dlgMsgBox.modal('show');
            dlgMsgBox.on('hidden.bs.modal', function () {
                def.resolve({activity: $scope.activity, option: $scope.option, isOk: $scope.IsOk});
            });
        }, 1);

        return def.promise;
    };


    $scope.validateDates = function (d) {
        d.dateInit = $scope.startDt;
        d.dateEnd = $scope.endDt;

        d.timeInit = window.formatTime($($scope.cfg.startTimeId).data("timepicker").getTime());
        d.timeEnd = window.formatTime($($scope.cfg.endTimeId).data("timepicker").getTime());

        d.dateInit.setHours(d.timeInit.hours, d.timeInit.minutes, 0, 0);
        d.dateEnd.setHours(d.timeEnd.hours, d.timeEnd.minutes, 0, 0);

        if (d.dateEnd < d.dateInit) {
            $scope.msgError = "La hora final no puede ser menor a la hora inicial";
            return false;
        }

        return true;
    };

    $scope.add = function () {
        var d = {};

        if ($scope.validateDates(d) == false)
            return false;

        $scope.activity = {
            title: "",
            doTitle: function () {
                this.title = (this.isModified === true ? "* " : "") + this.m.description + ' / ' + this.m.place
            },
            activityId: -1,
            start: d.dateInit,
            end: d.dateEnd,
            m: $scope.m,
            isModified : true,
            className: 'label-act-evt-' + $scope.m.priority.id,
            allDay: false,
            hasTo: 1 //1-insert, 2-update, 3-delete
        };

        $scope.activity.doTitle();

        $scope.IsOk = true;
        $scope.hideMsg();
    };

    $scope.save = function () {

        var today = new Date();
        today.setHours(0, 0, 0, 0);

        var d = {};
        if ($scope.validateDates(d) == false)
            return false;

        if (d.dateInit < today) {
            $scope.msgError = "No es posible modificar la actividad ya que la fecha de inicio está definida antes de la fecha actual";
            return false;
        }

        var dateInit = new Date(d.dateInit);
        var dateEnd = new Date(d.dateEnd);
        dateInit.setHours(d.timeInit.hours, d.timeInit.minutes, 0, 0);
        dateEnd.setHours(d.timeEnd.hours, d.timeEnd.minutes, 0, 0);

        $scope.eventSel.start = dateInit;
        $scope.eventSel.end = dateEnd;
        $scope.eventSel.isModified = true;

        $scope.eventSel.className = window.changeByStatus('', $scope.m.priority.id);

        $scope.eventSel.m = $scope.m;
        $scope.eventSel.doTitle(true);

        $scope.IsOk = true;
        $scope.option = "UPDATE";
        $scope.hideMsg();
    };

    $scope.delete = function () {
        $scope.IsOk = true;
        $scope.option = "REMOVE";
        $scope.hideMsg();
    };


    $scope.cancel = function () {
        $scope.IsOk = false;
        $scope.hideMsg();
    };

    $scope.doCancel = function () {
        if($scope.setRes){
            $scope.actProcessIsDone = false;
            $scope.setRes = false;
        }
        else{
            $scope.cancel();
        }
    };

    $scope.endActivity = function(urlToPost){
        $scope.setRes = true;
        var endData = {activityId: $scope.eventSel.activityId, isDone: $scope.isDone === '1', comments: $scope.m.comments};
        $.ajax({
            url: urlToPost,
            type: "POST",
            data: JSON.stringify(endData),
            success: $scope.handleEndActSuccess,
            error: $scope.handleEndActError,
            dataType: "json",
            contentType: "application/json"
        });
    };

    $scope.handleEndActSuccess = function (resp) {
        try {
            $scope.waitFor = false;
            if (resp.hasError === undefined || resp.hasError === true) {
                $scope.msgError = resp.message;
                $scope.$apply();
                return;
            }
            else if (resp.hasError === false) {
                $scope.actIsDone = true;
                $scope.isReadOnly = true;
                $scope.actProcessIsDone = true;
                $scope.eventSel.className = window.changeByStatus('REALIZADA', $scope.m.priority.id);
                $scope.eventSel.actIsDone = true;
                $scope.option = "UPDATE";
            }
            $scope.$apply();
        } catch (e) {
            $scope.msgError = "Error inesperado de datos. Por favor intente más tarde.";
            $scope.$apply();
        }
    };

    $scope.handleEndActError = function () {
        $scope.waitFor = false;
        $scope.msgError = "Error de red. Por favor intente más tarde.";
        $scope.$apply();

    };

    $scope.formatHtml = function(sHtml){
        return $sce.trustAsHtml(sHtml);
    };

});