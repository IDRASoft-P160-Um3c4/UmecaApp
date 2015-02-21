app.controller('upsertActivityEventController', function ($scope, $timeout, $q, sharedSvc) {
        $scope.Title = "";
        var th = this;
        var dlgMsgBox = $('#UpsertActivityEventDlgId');
        $scope.cfg = {};

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
            $scope.startDt = params.start;
            $scope.endDt = params.end;
            $scope.isNew = params.isNew;
            $scope.m = {};
            $scope.m.priority = $scope.cfg.lstPriorities[0];
            $scope.m.priorityId = $scope.m.priority.id;

            var startTime = window.getTimeFormat($scope.startDt, false);
            var endTime = window.getTimeFormat($scope.endDt, false);
            $($scope.cfg.startTimeId).timepicker('setTime', startTime);
            $($scope.cfg.endTimeId).timepicker('setTime', endTime);

            var def = $q.defer();

            $timeout(function () {
                dlgMsgBox.modal('show');
                dlgMsgBox.on('hidden.bs.modal', function () {
                    if ($scope.IsOk === true) {
                        def.resolve({activity: $scope.activity, option: $scope.option});
                    }
                    else {
                        def.reject();
                    }
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
                $scope.msgError = "La hora final no puede ser menor a la fecha inicial";
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
                idActivity: -1,
                start: d.dateInit,
                end: d.dateEnd,
                m: $scope.m,
                isModified : true,
                className: 'label-act-evt-' + $scope.m.priority.id,
                allDay: false
            };

            $scope.activity.doTitle();

            $scope.IsOk = true;
            $scope.hideMsg();
        };

        $scope.save = function () {
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

    }
)
;