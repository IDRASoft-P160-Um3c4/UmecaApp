app.controller('formulationDateCtrl', function ($scope, $timeout) {
    $scope.m = {};
    $scope.lstReviewers = [];
    $scope.m.idReviewer = 0;


    $scope.init = function () {




        var registrationFormulationDate = $("#registrationFormulationDate").val();
        if (registrationFormulationDate !== '') {
            registrationFormulationDate = registrationFormulationDate.replace(/-/g, "/");
            $("#registrationFormulationDate").val(registrationFormulationDate.replace("00:00:00.0", ""));
        }


        var umecaInterviewDate = $("#umecaInterviewDate").val();
        if (umecaInterviewDate !== '') {
            umecaInterviewDate = umecaInterviewDate.replace(/-/g, "/");
            $("#umecaInterviewDate").val(umecaInterviewDate.replace("00:00:00.0", ""));
        }

        var hearingDate = $("#hearingDate").val();
        if (hearingDate !== '') {
            hearingDate = hearingDate.replace(/-/g, "/");
            $("#hearingDate").val(hearingDate.replace("00:00:00.0", ""));
        }


        if ($scope.lstReviewers === undefined || $scope.lstReviewers.length <= 0)
            return;
        if ($scope.m.reviewerId === undefined || $scope.m.reviewerId === 0) {
            $scope.m.reviewer = $scope.lstReviewers[0];
            $scope.m.reviewerId = $scope.m.reviewer.id;
        }
        else {
            for (var i = 0; i < $scope.lstReviewers.length; i++) {
                var reviewer = $scope.lstReviewers[i];
                if (reviewer.id === $scope.m.reviewerId) {
                    $scope.m.reviewer = reviewer;
                    break;
                }
            }
        }
    };

    $timeout(function () {
        $scope.init();
    }, 0);


    $timeout(function () {
        $scope.init();
    }, 0);
    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};

    $scope.submit = function (formId, urlToPost, hasReturnId) {

        $(formId).validate();

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;

        if (hasReturnId === true) {
            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccessWithId)
                .error($scope.handleError);
        }
        else {
            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccess)
                .error($scope.handleError);
        }
        return true;
    };

    $scope.handleSuccessWithId = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                $rootScope.$broadcast("onLastId", resp.Id);
                $scope.Model.dlg.modal('hide');
                $scope.Model.def.resolve({isCancel: false});
                return;
            }

            $scope.MsgError = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.MsgError = "Error inesperado de datos. Por favor intente más tarde.";
        }
    };


    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                $scope.Model.dlg.modal('hide');
                $scope.Model.def.resolve({isCancel: false});
                return;
            }

            $scope.MsgError = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.MsgError = "Error inesperado de datos. Por favor intente más tarde.";
        }
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.MsgError = "Error de red. Por favor intente más tarde.";
        $scope.$apply();
    };

    $scope.cancel = function () {
        $scope.Model.dlg.modal('hide');
        $scope.Model.def.reject({isCancel: true});
    };

    $scope.setDlg = function (dlg, urlToSubmit) {
        $scope.Model.dlg = dlg;
        $scope.Model.url = urlToSubmit;

        dlg.on('hidden.bs.modal', function () {
            dlg.data('modal', null);
            dlg.replaceWith("");
        });
    };


});