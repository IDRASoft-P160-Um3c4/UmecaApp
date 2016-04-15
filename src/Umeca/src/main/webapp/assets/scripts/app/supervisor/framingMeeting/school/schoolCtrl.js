app.controller('framingSchoolController', function ($scope, $timeout, $rootScope, $sce) {

        $scope.school = {};
        $scope.day = "";
        $scope.start = "";
        $scope.end = "";
        $scope.MsgSuccessSchool = "";
        $scope.MsgErrorSchool = "";
        $scope.MsgErrorSchedule = "";

        $scope.init = function () {
            $scope.loadSchool();
        };

        $timeout(function () {
            $scope.init();

        }, 0);

        $scope.WaitFor = false;
        $scope.Model = {};

        $scope.loadSchool = function () {

            var url = $("#urlSchool").val();
            var idCase = $("#schoolIdCase").val();

            url += idCase;

            $.post(url)
                .success(function (resp) {
                    $scope.fillSchool(resp);
                })
                .error(function () {
                    $scope.MsgErrorSchool = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                });
        };

        $scope.fillSchool = function (data) {

            var msg = {};
            if (data.hasError === undefined) {
                msg = JSON.parse(data.responseMessage.message);
            }

            $scope.school.id = msg.id;
            $scope.school.name = msg.name;
            $scope.school.phone = msg.phone;
            $scope.school.address = msg.address;
            $scope.school.academicLvlId = msg.academicLvlId;
            $scope.school.degreeId = msg.degreeId;
            $scope.school.comments = msg.commentSchool;
            $scope.school.specification = msg.specification;

            $scope.hasActualSchool = msg.hasActualSchool;
            if ($scope.hasActualSchool == undefined || $scope.hasActualSchool == null)
                $scope.hasActualSchool = true;


            if (msg.schedule != undefined){
                $scope.school.schedule = JSON.parse(msg.schedule);
                for (var schedule in $scope.school.schedule) {
                    if(($scope.school.schedule[schedule].start === "00:00") && ($scope.school.schedule[schedule].end === "00:00")){
                        $scope.school.schedule[schedule].isRandomTime = true;
                    }
                }
            }
            else
                $scope.school.schedule = [];

            $scope.$apply();

            $scope.initDisable($scope.hasActualSchool);
            $scope.getAcademicLvl();
        };

        $scope.submitSchool = function (formId, urlToPost, id) {

            $scope.Invalid = false;

            if ($scope.validateSchedule() == false) {
                $scope.Invalid = true;
            }

            if ($(formId).valid() == false) {
                $scope.Invalid = true;
            }

            if ($scope.Invalid == true)
                return false;

            $scope.WaitFor = true;

            var url = urlToPost;

            $.post(url, $(formId).serialize())
                .success($scope.handleSuccessSchool)
                .error($scope.handleErrorSchool);

            return true;
        };

        $scope.handleSuccessSchool = function (resp) {
            $scope.WaitFor = false;

            try {
                if (resp.hasError === undefined) {
                    resp = resp.responseMessage;
                }
                if (resp.hasError === false) {
                    $scope.MsgSuccessSchool = $sce.trustAsHtml(resp.message);
                    $scope.MsgErrorSchool = $sce.trustAsHtml("");
                } else {
                    $scope.MsgErrorSchool = $sce.trustAsHtml(resp.message);
                    $scope.MsgSuccessSchool = $sce.trustAsHtml("");
                }
                $scope.$apply();
            } catch (e) {
                $scope.MsgErrorSchool = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
            }
        };

        $scope.handleErrorSchool = function () {
            $scope.WaitFor = false;
            $scope.MsgErrorSchool = $sce.trustAsHtml("Error de red. Por favor intente más tarde.");
        };

        $scope.randomTime = function () {
            if ($scope.isRandomTime === true) {
                $scope.start = "00:00";
                $scope.end = "00:00";
            } else {
                $scope.start = "12:00";
                $scope.end = "12:00";
            }
        }

        $scope.addSchedule = function () {
            if ($scope.day == "" || $scope.start == "" || $scope.timeEnd == "") {
                $scope.MsgErrorSchedule = "Debe proporcionar todos los campos para poder agregar una disponibilidad.";
                return;
            }

            $scope.MsgErrorSchedule = "";

            var newObj = {"day": $scope.day, "start": $scope.start, "end": $scope.end, "isRandomTime": $scope.isRandomTime}
            $scope.school.schedule.push(newObj);

            $scope.day = "";
            $scope.start = "";
            $scope.end = "";
            $scope.isRandomTime = false;

        };

        $scope.removeSchedule = function (idx) {
            $scope.school.schedule.splice(idx, 1);
        };


        $scope.validateSchedule = function () {
            if ($scope.hasActualSchool == true) {
                if ($scope.school.schedule == null || $scope.school.schedule == undefined || !$scope.school.schedule.length > 0) {
                    $scope.MsgErrorSchedule = "Debe agregar al menos una disponibilidad para la escuela.";
                    $scope.hasError = true;
                    return false;
                }
            }
            $scope.MsgErrorSchedule = "";
            return true;
        };

        $scope.fillSelAcademicLvl = function () {
            if ($scope.lstAcademicLvl === undefined || $scope.lstAcademicLvl.length <= 0)
                return;

            if ($scope.school.academicLvlId === undefined) {
                $scope.academicLvl = $scope.lstAcademicLvl[0];
                $scope.school.academicLvlId = $scope.academicLvl.id;
            }
            else {
                for (var i = 0; i < $scope.lstAcademicLvl.length; i++) {
                    var act = $scope.lstAcademicLvl[i];
                    if (act.id === $scope.school.academicLvlId) {
                        $scope.academicLvl = act;
                        break;
                    }
                }
            }
            $scope.getDegrees($scope.school.academicLvlId);
        };

        $scope.getDegrees = function (id) {
            var url = $("#getDegree").val();
            url = url + id;
            $.post(url)
                .success(function (resp) {
                    if (resp.hasError === undefined) {
                        resp = resp.responseMessage;
                    }
                    if (resp.hasError === false) {
                        $scope.lstDegree = JSON.parse(resp.message);
                    } else if (resp.hasError === true) {
                        $scope.MsgErrorSchool = $sce.trustAsHtml("Error de red. Intente m&aacute;s tarde");
                    }
                    $scope.$apply();
                    $scope.fillSelDegree();
                })
                .error($scope.handleErrorSchool);
        };

        $scope.getAcademicLvl = function () {
            var url = $("#getAcademicLvl").val();
            $.post(url)
                .success(function (resp) {
                    if (resp.hasError === undefined) {
                        resp = resp.responseMessage;
                    }
                    if (resp.hasError === false) {
                        $scope.lstAcademicLvl = JSON.parse(resp.message);
                    } else if (resp.hasError === true) {
                        $scope.MsgErrorSchool = $sce.trustAsHtml("Error de red. Intente m&aacute;s tarde");
                    }
                    $scope.$apply();
                    $scope.fillSelAcademicLvl();
                })
                .error($scope.handleErrorSchool);
        };

        $scope.fillSelDegree = function () {

            $scope.degree = $scope.lstDegree[0];
            $scope.$apply();

            for (var i = 0; i < $scope.lstDegree.length; i++) {
                var act = $scope.lstDegree[i];
                if (act.id === $scope.school.degreeId) {
                    $scope.degree = act;
                    $scope.$apply();
                    break;
                }
            }

        };

        $scope.fillSelects = function () {
            $scope.fillSelAcademicLvl();
            $scope.getDegrees($scope.academicLvl.id);

        };



        $scope.initDisable = function (value) {
            if (value == false) {
                $("#idSchool :input").attr("disabled", true);
                $("#divHiddenSchool :input").attr("disabled", false);
            } else {
                $("#idSchool :input").attr("disabled", false);
                $("#divHiddenSchool :input").attr("disabled", true);
            }
        };

        $scope.disableView = function (value) {

            if (value == false) {
                $scope.school.name = "NO ESTUIDA";
                $scope.school.phone = "NO ESTUIDA";
                $scope.school.address = "NO ESTUIDA";

                $("#idSchool :input").attr("disabled", true);
                $("#divHiddenSchool :input").attr("disabled", false);
            } else {

                $scope.school.name = "";
                $scope.school.phone = "";
                $scope.school.address = "";

                $("#idSchool :input").attr("disabled", false);
                $("#divHiddenSchool :input").attr("disabled", true);
            }

        };

    }
)
;