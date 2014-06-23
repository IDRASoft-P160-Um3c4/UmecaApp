app.controller('addressController', function($scope, $timeout, $http) {
    $scope.a = {};
    $scope.listLocation = [];
    $scope.listElection = [];
    $scope.listRegisterType = [];
    $scope.a.belong = 0;
    $scope.a.type=0;
    $scope.content = "Address";
    $scope.nameAddress = "address.";

    $scope.init = function(){
        if($scope.listRegisterType === undefined || $scope.listRegisterType.length <= 0)
            return;

        if($scope.a.typeId === undefined){
            $scope.a.type = $scope.listRegisterType[0];
            $scope.a.typeId = $scope.a.type.id;
        }
        else{
            for(var i=0; i < $scope.listRegisterType.length; i++){
                var type = $scope.listRegisterType[i];

                if(type.id === $scope.a.typeId){
                    $scope.a.type =type;
                    break;
                }
            }
        }
        if($scope.listElection === undefined || $scope.listElection.length <= 0)
            return;

        if($scope.a.belongId === undefined){
            $scope.a.belong = $scope.listElection[0];
            $scope.a.belongId = $scope.a.belong.id;
        }
        else{
            for(var i=0; i < $scope.listElection.length; i++){
                var bel = $scope.listElection[i];

                if(bel.id === $scope.a.belongId){
                    $scope.a.belong = bel;
                    break;
                }
            }
        }

        if($scope.zipCode != "" && $scope.zipCode!= undefined){
            var ajaxConf = {
                method: 'POST',
                url: $scope.url
            };
            ajaxConf.params = {zipCode : $scope.zipCode};
            $http(ajaxConf).success(function (data) {
                    data.data=jQuery.parseJSON(data.data);
                    if (data.data == undefined || data.data.length === 0) {
                        $scope.clear();
                        return;
                    }
                    $scope.listLocation = data.data;
                    $scope.a.location =$scope.listLocation[0];
                    $scope.a.locationId = $scope.a.location.id;
                });
        }
    };


    $timeout(function() {
        $scope.init();
    }, 0);
    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};

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