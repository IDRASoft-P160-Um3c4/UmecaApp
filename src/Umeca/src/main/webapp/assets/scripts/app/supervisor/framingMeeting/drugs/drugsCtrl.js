app.controller('drugsFMController', function($scope, $timeout) {
    $scope.d = {};
    $scope.lstType = [];
    $scope.lstPer = [];
    $scope.d.type = 0;
    $scope.d.per = 0;

    $scope.init = function(){
        $('.date-picker').datepicker({autoclose:true, endDate:new Date()}).next().on(ace.click_event, function(){
            $(this).prev().focus();
        });
        var lastUse=$("#lastUse").val();
        lastUse= lastUse.replace(/-/g,"/");
        $("#lastUse").val(lastUse.replace("00:00:00.0",""));
        if($scope.lstType === undefined || $scope.lstType.length <= 0)
            return;

        if($scope.d.typeId === undefined){
            $scope.d.type = $scope.lstType[0];
            $scope.d.typeId = $scope.d.type.id;
        }
        else{
            for(var i=0; i < $scope.lstType.length; i++){
                var type = $scope.lstType[i];

                if(type.id === $scope.d.typeId){
                    $scope.d.type = type;
                    break;
                }
            }
        }
        if($scope.lstPer === undefined || $scope.lstPer.length <= 0)
            return;

        if($scope.d.perId === undefined){
            $scope.d.per = $scope.lstPer[0];
            $scope.d.perId = $scope.d.per.id;
        }
        else{
            for(var i=0; i < $scope.lstPer.length; i++){
                var per = $scope.lstPer[i];

                if(per.id === $scope.d.perId){
                    $scope.d.per = per;
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

    $scope.submitIdCaseParam = function (formId, urlToPost, id) {

        $(formId).validate();

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
                $rootScope.$broadcast("reloadEnvironment");
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