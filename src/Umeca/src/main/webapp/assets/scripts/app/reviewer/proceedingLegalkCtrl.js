app.controller('proceedingLegalController', function($scope, $timeout) {
    $scope.m = {};
    $scope.listElection = [];
    $scope.lstRelationship = [];
    $scope.m.rel = 0;
    $scope.m.complyPM = 0;
    $scope.m.complyCSPP = 0;
    $scope.m.complyProcessAbove = 0;
    $scope.validf ={};
    $scope.listMsgErrorCon=[];
    $scope.nameAddress="domicileVictim."


    $scope.init = function(){
        if($scope.lstRelationship === undefined || $scope.lstRelationship.length <= 0)
            return;

        if($scope.m.relId === undefined){
            $scope.m.rel = $scope.lstRelationship[0];
            $scope.m.relId = $scope.m.rel.id;
        }
        if($scope.listElection === undefined || $scope.listElection.length <= 0)
            return;

        if($scope.m.complyPMId === undefined){
            $scope.m.complyPM = $scope.listElection[0];
            $scope.m.complyPMId = $scope.m.complyPM.id;
        }

        if($scope.m.complyCSPPId === undefined){
            $scope.m.complyCSPP = $scope.listElection[0];
            $scope.m.complyCSPPId = $scope.m.complyCSPP.id;
        }
        if($scope.m.complyProcessAboveId === undefined){
            $scope.m.complyProcessAbove = $scope.listElection[0];
            $scope.m.complyProcessAboveId = $scope.m.complyProcessAbove.id;
        }

    };


    $timeout(function() {
        $scope.init();
    }, 0);
    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};

    $scope.submit = function (formId, urlToPost, hasReturnId) {
        var forms = formId.split(",");
        var val = true;
        for(var i=0; i<forms.length; i++){
            if($(forms[i]).valid() == false){
                $scope.validf["form"+i] = true;
                val  = false;
            }else{
                $scope.validf["form"+i] = false;
            }
        }
        if (!val) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;

             $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccess)
                .error($scope.handleError);
        return true;
    };

    $scope.changeZIndex = function(elementClick){
        $("#liLegalPrevious").css("z-index","0");
        $("#liLegalActual").css("z-index","0");
        $("#"+elementClick).css("z-index","1");

    };

    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        $scope.listMsgError ={};
        try {
            if(resp.hasError===undefined){
                resp=resp.responseMessage;}
            if (resp.hasError === false) {
                window.cancelLegal();
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

});