app.controller('employeeScheduleController', function ($scope, $timeout, $sce) {
        $scope.empSche = {};
        $scope.WaitFor = false;
        $scope.MsgError;
        $scope.MsgErrorDay;

        $scope.isAllDaySelected = false;
        $scope.isAllStartSelected = false;
        $scope.isAllEndSelected = false;

        $scope.init = function () {
            $scope.initLstDays();
        };

        $scope.validateSelDay = function () {
            var band = 0;
            var st = false;

            for (var i = 0; i < $scope.lstDays.length; i++) { //valida al menos un seleccionado
                st = st || $scope.lstDays[i].isSel == true;
            }

            if (st == false) {
                band = 1;
                return band;
            }

            for (var i = 0; i < $scope.lstDays.length; i++) {//valida que todos los selecccionados tengan sus datos
                if ($scope.lstDays[i].isSel == true) {
                    if ($scope.lstDays[i].start == undefined || $scope.lstDays[i].start == ""
                        && $scope.lstDays[i].end == undefined || $scope.lstDays[i].end == "") {
                        band = 2;
                        break;
                    }
                }
            }

            return band;
        };

        $scope.updateStEd = function (idx) {

            if ($scope.lstDays[idx].isSel == false) {
                $scope.lstDays[idx].start = "";
                $scope.lstDays[idx].end = "";
            }

            if ($scope.isAllDaySelected == true)
                $scope.isAllDaySelected = $scope.chkDaysSelected();
            if ($scope.isAllStartSelected == true)
                $scope.isAllStartSelected = $scope.chkAllStart();
            if ($scope.isAllEndSelected == true)
                $scope.isAllEndSelected = $scope.chkAllEnd();

        };

        $scope.chkDaysSelected = function () {
            var band = true;
            for (var i = 0; i < $scope.lstDays.length; i++) {
                if ($scope.lstDays[i].isSel == false) {
                    band = false;
                    break;
                }
            }

            return band;
        };

        $scope.chkAllStart = function () {
            var band = true;

            for (var i = 0; i < $scope.lstDays.length; i++) {
                if ($scope.lstDays[i].start == undefined || $scope.lstDays[i].start != "") {
                    band = false;
                    break;
                }
            }

            return band;
        };

        $scope.chkAllEnd = function () {
            var band = true;

            for (var i = 0; i < $scope.lstDays.length; i++) {
                if ($scope.lstDays[i].end == undefined || $scope.lstDays[i].end != "") {
                    band = false;
                    break;
                }
            }

            return band;
        };

        $scope.findIdxByDayId = function (dayId) {
            var idx = -1;
            for (var x = 0; x < $scope.lstSelDays.length; x++) {
                if ($scope.lstSelDays[x].dayId == dayId) {
                    idx = x;
                    break;
                }
            }
            return idx;
        };

        $scope.initLstDays = function () {

            for (var a = 0; a < $scope.lstDays.length; a++) {
                var idx = $scope.findIdxByDayId($scope.lstDays[a].id);

                if (idx > -1) {
                    $scope.lstDays[a].start = $scope.lstSelDays[idx].start;
                    $scope.lstDays[a].end = $scope.lstSelDays[idx].end;
                    $scope.lstDays[a].isSel = true;
                } else {
                    $scope.lstDays[a].start = "";
                    $scope.lstDays[a].end = "";
                    $scope.lstDays[a].isSel = "";
                }
            }

        };

        $scope.selAllDays = function () {
            for (var i = 0; i < $scope.lstDays.length; i++) {
                $scope.lstDays[i].isSel = $scope.isAllDaySelected;
            }
        };

        $scope.setAllStart = function () {
            var val = "";
            if ($scope.isAllStartSelected == true) {
                val = $scope.grlStart;
            }

            for (var i = 0; i < $scope.lstDays.length; i++) {
                $scope.lstDays[i].start = val;
            }
        };

        $scope.setAllEnd = function () {
            var val = "";
            if ($scope.isAllEndSelected == true) {
                val = $scope.grlEnd;
            }
            for (var i = 0; i < $scope.lstDays.length; i++) {
                $scope.lstDays[i].end = val;
            }
        };


        $scope.submitUpsertEmployeeSch = function (formId, urlToPost) {

            $scope.WaitFor = true;
            $scope.Invalid = false;
            $scope.MsgError = "";
            $scope.MsgErrorDay = "";

            if ($scope.validateSelDay() == 1) {
                $scope.MsgErrorDay = $sce.trustAsHtml("Debe seleccionar al menos un d&iacute;a de la semana.");
                $scope.Invalid = true;
            } else if ($scope.validateSelDay() == 2) {
                $scope.MsgErrorDay = $sce.trustAsHtml("Debe indicar la hora de entrada y la hora de salida para cada d&iacute;a seleccionado.");
                $scope.Invalid = true;
            }

            if ($(formId).valid() == false) {
                $scope.MsgError = $sce.trustAsHtml("Debe proporcionar toda la informaci&oacute;n requerida.");
                $scope.Invalid = true;
            }

            if ($scope.Invalid == true) {
                $scope.WaitFor = false;
                return false;
            }

            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccessEmployeeSch)
                .error($scope.handleErrorEmployeeSch);
            return true;
        };

        $scope.handleSuccessEmployeeSch = function (resp) {
            $scope.WaitFor = false;

            try {
                if (resp.hasError === undefined) {
                    resp = resp.responseMessage;
                }

                if (resp.hasError == true) {
                    $scope.MsgError = $sce.trustAsHtml(resp.message);
                } else {
                    scope = angular.element('#dlgUpModalId').scope()
                    scope.Model.dlg.modal('hide');
                    scope.Model.def.resolve({isCancel: false});
                }

                $scope.$apply();
            }
            catch (e) {
                $scope.MsgError = $sce.trustAsHtml("Error inesperado de datos. Por favor intente m&aacute;s tarde.");
                $scope.$apply();
            }
        };

        $scope.handleErrorEmployeeSch = function () {
            $scope.WaitFor = false;
            $scope.MsgError = $sce.trustAsHtml("Error de red. Por favor intente m&aacute;s tarde.");
            $scope.$apply();
        };

        $timeout(function () {
            $scope.init();
        }, 0);

    }
)
;