app.controller('meetingController', function($scope, $timeout) {
    $scope.model = {};
    $scope.verification=false;
    $scope.selectSource=false;

    $scope.init = function(){
    };


    $timeout(function() {
        $scope.init();
    }, 0);
    $scope.WaitFor = false;
    $scope.listMsgErrorCon = [];
    $scope.listMsgError ={};
    $scope.Model = {};
    $scope.validf = {};

    $scope.changeZIndex = function(elementClick){
        $("#liPersonalData").css("z-index","0");
        $("#liImputedHome").css("z-index","0");
        $("#liReference").css("z-index","0");
        $("#liSocialNetwork").css("z-index","0");
        $("#liJob").css("z-index","0");
        $("#liSchool").css("z-index","0");
        $("#liDrug").css("z-index","0");
        $("#liLeaveCountry").css("z-index","0");
        $("#"+elementClick).css("z-index","1");

    };

    $scope.submit = function (formId, urlToPost, hasReturnId) {
        $scope.Invalid = true;
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

        $scope.listMsgError ={};
        try {
            if(resp.hasError===undefined){
                resp=resp.responseMessage;}
            if (resp.hasError === false) {
                window.cancelMeeting();
                return;
            }
            var obj = JSON.parse(resp.message);
            if(obj.groupMessage != undefined){
                for(var i=0; i < obj.groupMessage.length; i++){
                    var g1= obj.groupMessage[i];
                    $scope.listMsgError[g1.section]=g1.messages;
                }
            }
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

app.controller('scController', function($scope, $timeout) {

    $scope.submit = function (formId, urlToPost, hasReturnId) {
        $scope.Invalid = true;
        $scope.WaitFor = true;

            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccess)
                .error($scope.handleError);
        return true;
    };


    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;
        try {
            if(resp.hasError===undefined){
                resp=resp.responseMessage;}
            if (resp.hasError === false) {
                $scope.msgSuccess=resp.message;
                $scope.$apply();
                return;
            }
            $scope.msgError=resp.message;
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

});