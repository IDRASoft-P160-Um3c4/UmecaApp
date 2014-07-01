app.controller('upsertVerificationController', function ($scope, $rootScope, $timeout) {
    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};
    $scope.verification=false;
    $scope.init = function(){
        $('.date-picker').datepicker({autoclose:true, endDate:new Date()}).next().on(ace.click_event, function(){
            $(this).prev().focus();
        });
    };
    $timeout(function() {
        $scope.init();
    }, 0);
    $scope.submit = function (formId, urlToPost, hasReturnId) {

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


    $scope.submitRedirect = function (formId, urlToPost, hasReturnId, validate) {

        var stVal = true;

        if (validate != undefined)
            stVal = validate();

        if ($(formId).valid() == false || stVal == false) {
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
                .success($scope.handleSuccessRedirect)
                .error($scope.handleError);
        }
        return true;
    };

    $scope.returnUrl = function (urlToGo) {
        window.goToUrlMvcUrl(urlToGo, "");
    };


    $scope.handleSuccessRedirect = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                window.goToUrlMvcUrl(resp.urlToGo, "");
                return;
            }

            $scope.MsgError = resp.message;

            $scope.$apply();

        } catch (e) {
            $scope.MsgError = "Error inesperado de datos. Por favor intente más tarde.";
        }
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
                $scope.Model.def.resolve({ isCancel: false });
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
        if($scope.Model.dlg != undefined){
            $scope.Model.dlg.modal('hide');
        }
        if($scope.Model.def != undefined){
            $scope.Model.def.reject({ isCancel: true });
        }
    };

    $scope.setDlg = function (dlg, urlToSubmit) {
        $scope.Model.dlg = dlg;
        $scope.Model.url = urlToSubmit;
    };
});
