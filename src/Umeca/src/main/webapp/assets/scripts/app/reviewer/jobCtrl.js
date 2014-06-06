app.controller('jobController', function($scope, $timeout) {
    $scope.j = {};
    $scope.lstRegisterType = [];
    $scope.j.registerType = 0;

    $scope.init = function(){

        var jdpStart=$("#jdpStart").val();
        if(jdpStart == ""){
            $("#jdpStart").val("2000/01/01");
            jdpStart=$("#jdpStart").val();
        }
        jdpStart= jdpStart.replace(/-/g,"/");
        $("#jdpStart").val(jdpStart.replace("00:00:00.0",""));

        var jdbEnd=$("#jdbEnd").val();
        if(jdbEnd == ""){
            $("#jdbEnd").val("2000/01/01");
            jdbEnd=$("#jdbEnd").val();
        }
        jdbEnd= jdbEnd.replace(/-/g,"/");
        $("#jdbEnd").val(jdbEnd.replace("00:00:00.0",""));

        var jdpStartCurrent=$("#jdpStartCurrent").val();
        if(jdpStartCurrent == ""){
            $("#jdpStartCurrent").val("2000/01/01");
            jdpStartCurrent=$("#jdpStartCurrent").val();
        }
        jdpStartCurrent= jdpStartCurrent.replace(/-/g,"/");
        $("#jdpStartCurrent").val(jdpStartCurrent.replace("00:00:00.0",""));
        if($scope.lstRegisterType === undefined || $scope.lstRegisterType.length <= 0)
            return;

        if($scope.j.registerTypeId === undefined){
            $scope.j.registerType = $scope.lstRegisterType[0];
            $scope.j.registerTypeId = $scope.j.registerType.id;
        }
        else{
            for(var i=0; i < $scope.lstRegisterType.length; i++){
                var type = $scope.lstRegisterType[i];

                if(type.id === $scope.j.registerTypeId){
                    $scope.j.registerType = type;
                    break;
                }
            }
        }
    };


    $timeout(function() {
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
            if(resp.hasError===undefined){
                resp=resp.responseMessage;
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
            if(resp.hasError===undefined){
                resp=resp.responseMessage;}
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