app.controller('upsertVerificationController', function ($scope, $rootScope, $timeout) {
    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};
    $scope.verification=false;
    $scope.nameScope="estoy en UPSERT verificationController";
    $scope.childScopeName="";
    $scope.childScope = null;

    $scope.init = function(){
        $('.date-picker').datepicker({autoclose:true, endDate:new Date()}).next().on(ace.click_event, function(){
            $(this).prev().focus();
        });
        if($scope.childScopeName != ""){
            $scope.childScope = angular.element(document.getElementById($scope.childScopeName)).scope();

        }
    };

    $scope.enableProperties = function (){

        $("input:text").each(function() {
            $( this ).removeAttr('disabled');
        });
        $("textarea").each(function() {
            $( this ).removeAttr('disabled');
        });
        $("select").each(function(){
            $(this).prop('disabled',false);
        });
        $("input:radio").each(function() {
            $( this ).removeAttr('disabled','disabled');
        });
    };

    $scope.disableProperties = function (){
        $("input:text").each(function() {
            $( this ).attr('disabled','disabled');
        });
        $("select").each(function(){
            $(this).prop("disabled", true);;
        });
        $("textarea").each(function() {
            $( this ).attr('disabled','disabled');
        });
        $("input:radio").each(function() {
            $( this ).attr('disabled','disabled');
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
            $.post(urlToPost, $(formId).serialize())
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
        $scope.disableProperties();
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
