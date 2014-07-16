app.controller('choiceInformationController', function($scope, $timeout, $q,sharedSvc) {
    $scope.def= $q.defer();
    $scope.selectSource=true;
    $scope.init = function(){
        $(".icon-ok-circle").each(function() {
            $( this ).css("cursor","pointer");
            $( this ).attr("rel","tooltip");
            $( this ).attr("title","Coincide la información");
        });
        $(".icon-remove-circle").each(function() {
            $( this ).css("cursor","pointer");
            $( this ).attr("rel","tooltip");
            $( this ).attr("title","No coincide la información");
        });

        $(".icon-ban-circle").each(function() {
            $( this ).css("cursor","pointer");
            $( this ).attr("rel","tooltip");
            $( this ).attr("title","No conoce la información");
        });
        $("input:text").each(function() {
            $( this ).attr('disabled','disabled');
        });
        $("input:radio").each(function() {
            $( this ).attr('disabled','disabled');
        });
        $("select").each(function(){
            $(this).prop("disabled", true);;
        });
        $("textarea").each(function() {
            $( this ).attr('disabled','disabled');
        });
    };


    $timeout(function() {
        $scope.init();
    }, 0);

    $scope.showChoices = function(code,id){
        window.showChoices(code,id);
    };

            $scope.pastToJson = function(string){
                var result = jQuery.parseJSON( string );
                if(result == ""){
                    return  "[]";
                }else{
                    return result;
                }
            } ;

    $scope.terminateVerification = function(urlTerminate){
        $scope.WaitFor = true;

        $.post(urlTerminate)
            .success($scope.handleSuccess)
            .error($scope.handleError);
    };

    $scope.changeZIndex = function(elementClick){
        $("#liImputed").css("z-index","0");
        $("#liImputedHome").css("z-index","0");
        $("#liReference").css("z-index","0");
        $("#liSocialNetwork").css("z-index","0");
        $("#liJob").css("z-index","0");
        $("#liSchool").css("z-index","0");
        $("#liDrug").css("z-index","0");
        $("#liLeaveCountry").css("z-index","0");
        $("#"+elementClick).css("z-index","1");

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


    $scope.setDlg = function (dlg, urlToSubmit) {
        $scope.Model.dlg = dlg;
        $scope.Model.url = urlToSubmit;

        dlg.on('hidden.bs.modal', function () {
            dlg.data('modal', null);
            dlg.replaceWith("");
        });
    };

});

