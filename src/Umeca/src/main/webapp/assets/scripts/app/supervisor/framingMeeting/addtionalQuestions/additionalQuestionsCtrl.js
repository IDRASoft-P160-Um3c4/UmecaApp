app.controller('additionalQuestionsController', function ($scope, $timeout, $http, $rootScope) {

    $scope.aq = {};
    $scope.aqErrorMsg = "";
    $scope.aqSuccessMsg = "";

    $scope.lstRelativeAbroad = [];

    $scope.errorSelAddAcq = "";
    $scope.errorSelRelAbroad = "";
    $scope.errorSelObligIssues = "";


    $scope.loadAdditionalQuestion = function () {

        var currentTimeout = null;
        var url = $('#loadAdditionalQuestion').attr("value");
        var idCase = $('#hidIdCaseAQ').attr("value");
        var ajaxConf;

        ajaxConf = {
            method: "GET",
            params: {idCase: idCase}
        };

        ajaxConf.url = url;

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (data) {
                    $scope.fillAdditionalQuestion(data);
                });
        }, 200);
    };

    $scope.fillAdditionalQuestion = function (data) {

        $scope.aq.observations = data.observations;
        $scope.aq.addictionTreatment = data.addictionTreatment;
        $scope.aq.addictionTreatmentInstitute = data.addictionTreatmentInstitute;
        $scope.aq.addictionTreatmentDate = $scope.myFormatDate(data.addictionTreatmentDate);
        $scope.aq.addictedAcquaintance = data.addictedAcquaintance;
        $scope.aq.relativeAbroad = data.relativeAbroad;
        $scope.aq.obligationIssue = data.obligationIssue;

        $scope.selectedAddictedAcquaintances = $.parseJSON(data.selectedAddictedAcquaintances);
        $scope.selectedObligationIssues = $.parseJSON(data.selectedObligationIssues);
        $scope.selectedRelativesAbroad = $.parseJSON(data.selectedRelativesAbroad);
    }

    $scope.validateRelAbroad = function () {

        var noSel = 0;
        var noDesc = 0;

        if ($scope.aq.relativeAbroad) {
            for (var i = 0; i < $scope.selectedRelativesAbroad.length; i++) {

                if ($scope.selectedRelativesAbroad[i].selVal == true) {
                    noSel++;
                    if ($scope.selectedRelativesAbroad[i].description != "" && $scope.selectedRelativesAbroad[i].description != undefined) {
                        noDesc++;
                    }
                } else {
                    $scope.selectedRelativesAbroad[i].description = "";
                }
            }

            if (noSel < 1) {
                $scope.errorSelRelAbroad = "Debe seleccionar al menos una opción.";
                return false;
            } else if (noSel > noDesc) {
                $scope.errorSelRelAbroad = "Debe indicar una dirección para cada opción seleccionada.";
                return false;
            } else {
                $scope.errorSelRelAbroad = "";
                return true;
            }
        }

        return true;
    };

    $scope.validateOblIssues = function () {

        var noSel = 0;
        var noDesc = 0;

        if ($scope.aq.obligationIssue == 1) {

            for (var i = 0; i < $scope.selectedObligationIssues.length; i++) {

                if ($scope.selectedObligationIssues[i].selVal == true) {
                    noSel++;
                    if ($scope.selectedObligationIssues[i].description != "" && $scope.selectedObligationIssues[i].description != undefined) {
                        noDesc++;
                    }
                } else {
                    $scope.selectedObligationIssues[i].description = "";
                }
            }

            if (noSel < 1) {
                $scope.errorSelObligIssues = "Debe seleccionar al menos una opción.";
                return false;
            } else if (noSel > noDesc) {
                $scope.errorSelObligIssues = "Debe indicar una causa para cada opción seleccionada.";
                return false;
            } else {
                $scope.errorSelObligIssues = "";
                return true;
            }
        }

        return true;
    };

    $scope.validateLst = function () {

        if ($scope.validateRelAbroad() == true && $scope.validateOblIssues() == true)
            return true;
        return false;
    };


    $scope.myFormatDate = function (dateMil) {

        var strDt = "";
        var date;

        if (dateMil && dateMil != "null") {

            date = new Date(dateMil);

            var dd, mm, yyyy;

            dd = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
            mm = date.getMonth() < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
            yyyy = date.getFullYear();

            strDt = dd + "/" + mm + "/" + yyyy;
        }

        return strDt;
    };

    $scope.init = function () {
        $scope.loadAdditionalQuestion();
    };

    $timeout(function () {
        $scope.init();
    }, 0);

    $scope.WaitFor = false;
    $scope.Model = {};

    $scope.submitIdCaseParam = function (formId, urlToPost, id) {

        $(formId).validate();

        if ($scope.validateLst() == false) {
            $scope.Invalid = true;
            return false;
        }

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;

        var url = urlToPost + id;

        $.post(url, $(formId).serialize())
            .success($scope.handleSuccess)
            .error($scope.handleError);

        return true;
    };

    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                $scope.aqSuccessMsg = resp.message;
                $scope.$apply();
                return;
            }

            $scope.aqErrorMsg = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.aqErrorMsg = "Error inesperado de datos. Por favor intente más tarde.";
        }
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.aqErrorMsg = "Error de red. Por favor intente más tarde.";
        $scope.$apply();
    };

    $scope.cancel = function () {
        $scope.Model.dlg.modal('hide');
        $scope.Model.def.reject({ isCancel: true });
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