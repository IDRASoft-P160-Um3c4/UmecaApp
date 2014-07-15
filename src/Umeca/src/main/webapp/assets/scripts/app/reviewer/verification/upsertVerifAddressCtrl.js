app.controller('verificationAddressController', function($scope, $timeout, $q, $rootScope) {
    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};
    $scope.verification = false;
    $scope.generalComponent = false;
    $scope.init = function(){

      };

    $scope.enableProperties = function () {

        $("input:text").each(function () {
            $(this).prop('disabled', false);
        });
        $("textarea").each(function () {
            $(this).prop('disabled', false);
        });
        $("select").each(function () {
            $(this).prop('disabled', false);
        });
        $("input:radio").each(function () {
            $(this).removeAttr('disabled');
        });
    };

    $scope.disableProperties = function () {
        $("input:text").each(function () {
            $(this).attr('disabled', 'disabled');
        });
        $("select").each(function () {
            $(this).prop("disabled", true);
            ;
        });
        $("textarea").each(function () {
            $(this).attr('disabled', 'disabled');
        });
        $("input:radio").each(function () {
            $(this).attr('disabled', 'disabled');
        });
    };

    $timeout(function() {
        $scope.init();
    }, 0);


    $rootScope.$on('SetIdList', function (event,idList) {
        $scope.idList=idList;
    });

    $rootScope.$on('SetCodeAddress', function (event,code) {
        $scope.code=code;
    });

    $scope.submit = function (formId) {
        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }

        $scope.WaitFor = true;

        var formSerialize = $(formId).serialize();
        var content = formSerialize + "&&idCase=" + $scope.idCase + "&&idSource=" + $scope.idSource + "&&code="+ $scope.code+"&&idList="+$scope.idList;
        $scope.WaitFor = true;
        $.post($scope.urlToGoSaveAddress, content)
            .success($scope.handleSuccess)
            .error($scope.handleError);
        return true;
    };


    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;
        $scope.MsgError = "";

        try {

            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }

            if (resp.hasError === false) {
                $scope.disableProperties();
                $scope.Model.dlg.modal('hide');
                $scope.Model.def.resolve({ isCancel: false });
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
        $scope.MsgError = "";
        $scope.verification = true;
        $scope.disableProperties();
        if ($scope.Model.dlg != undefined) {
            $scope.Model.dlg.modal('hide');
        }
        if ($scope.Model.def != undefined) {
            $scope.Model.def.reject({ isCancel: true });
        }
    };

    $scope.setDlg = function (dlg, urlToSubmit) {
        if ($scope.Model == undefined || $scope.Model.dlg == undefined) {
            $scope.Model.dlg = dlg;
            $scope.Model.url = urlToSubmit;
        }
    };

});
