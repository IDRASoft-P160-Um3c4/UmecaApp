app.controller('meetingController', function($scope, $timeout, $sce, $rootScope) {
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
    $scope.cancelMeeting=false;

    $scope.showMessageError = function(elementClick){
        $("#divErrorMessage").show();
        var position = $(".tab-content").position();
        $("#divErrorMessage").css("left",position.left+5);
        $("#divErrorMessage").addClass("errorMessageClass");

        $scope.entityError=elementClick;
    };

    $scope.hideMessageError = function(){
        $("#divErrorMessage").hide();
    };

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

        $scope.listMsgError ={};
        try {
            if(resp.hasError===undefined){
                resp=resp.responseMessage;}
            if (resp.hasError === false) {
                window.cancelMeeting();
                return;
            }
            var obj = JSON.parse(resp.message);

            $scope.cancel();

            if(obj.groupMessage != undefined){
                for(var i=0; i < obj.groupMessage.length; i++){
                    var g1= obj.groupMessage[i];
                    $scope.listMsgError[g1.section]= $sce.trustAsHtml( g1.messages);
                }
            }

            $scope.$emit('updateListMsgError', $scope.listMsgError);
            $scope.$apply();

        } catch (e) {
            $scope.MsgError =  "Error inesperado de datos. Por favor intente más tarde.";
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

    $rootScope.$on('updateListMsgError', function (event, lstMsgError) {
        $scope.listMsgError = lstMsgError;
    });

})
app.controller('scController', function($scope, $timeout, $sce) {

    $scope.upsertComment = function (idCase, urlToPost, typeComment) {
        $scope.Invalid = true;
        $scope.WaitFor = true;
        var aa = {};
        aa.idCase =idCase;
        aa.typeComment = typeComment;
        aa.comment = $scope.comment;
        $.post(urlToPost, aa)
            .success($scope.handleSuccess)
            .error($scope.handleError);

        return true;
    };




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
                $scope.msgSuccess=$sce.trustAsHtml(resp.message);
                $scope.$apply();
                return;
            }
            $scope.msgError=$sce.trustAsHtml(resp.message);
            $scope.$apply();
        } catch (e) {
            $scope.msgError = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
        }
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.msgError = $sce.trustAsHtml( "Error de red. Por favor intente más tarde.");
        $scope.$apply();
    };
});


app.controller('newMeetController', function ($scope) {
        $scope.m = {};


        $scope.init = function () {
            $scope.fillSelectD("m", "district", "lstDistrict", "districtId");
        };

        $scope.fillSelectD = function (obj, prop, list, model) {
            if ($scope[list] === undefined || $scope[list].length <= 0)
                return;

            if ($scope[model] === undefined || $scope[model] === -1)
                $scope[obj][prop] = $scope[list][0];
            else {
                for (var i = 0; i < $scope[list].length; i++) {
                    var rel = $scope[list][i];
                    if (rel.id === $scope[model]) {
                        $scope[obj][prop] = rel;
                        break;
                    }
                }
            }
        };


    }
)
;
