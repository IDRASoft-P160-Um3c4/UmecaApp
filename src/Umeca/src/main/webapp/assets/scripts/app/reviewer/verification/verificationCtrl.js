app.controller('verificationController', function($scope, $timeout, $q,sharedSvc) {
    $scope.def= $q.defer();
    $scope.verification=true;
    $scope.nameScope="estoy en verificationController";
    $scope.init = function(){
        if($scope.managereval != undefined && $scope.managereval == true){
            $scope.verification = false;
            $scope.showSchedule = true;
        }
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


    $scope.doConfirmVerifEqual = function (code,id) {
        var data = {
            code: code,
            idCase: $scope.idCase,
            idSource: $scope.idSource
        };
        if(id!=undefined){
            data.idList=id;
        }
        var urlToGo= $scope.urlVerifTrue;
        var def = $q.defer();
        sharedSvc.showConf({ title: "Confirmación de verificación", message: "Establecer que la respuesta de la fuente es igual a lo proporcionado por el imputado", type: "success" }).
            then(function () {
                $scope.doPost(data, urlToGo, def);
            }, def.reject);
        return def.promise;
    };

    $scope.doConfirmVerifNotKnow = function (code,id) {
        var data = {
            code: code,
            idCase: $scope.idCase,
            idSource: $scope.idSource
        };
        if(id!=undefined){
            data.idList=id;
        }
        var urlToGo= $scope.urlVerifNotKnow;
        var def = $q.defer();
        sharedSvc.showConf({ title: "Confirmación de verificación", message: "Establecer que la fuente no conoce la información proporcionada por el imputado", type: "inverse" }).
            then(function () {
                $scope.doPost(data, urlToGo, def);
            }, def.reject);
        return def.promise;
    };

    $scope.terminateMeetingSource = function(urlTerminate){
        var data = {
            idCase: $scope.idCase,
            idSource: $scope.idSource
        };
        var urlToGo= urlTerminate;
        var def = $q.defer();
        sharedSvc.showConf({ title: "Confirmación para terminar entrevista", message: "Esta seguro que desea terminar la entrevista con la fuente.<br/><b> Una vez terminada la entrevista no podrá modificar la información proporcionada.</b>", type: "primary" }).
            then(function () {
                $scope.doPost(data, urlToGo, def,true);
            }, def.reject);
        return def.promise;
    };

    $scope.doPost = function (data, urlToGo, def, redirect) {
        var settings = {
            dataType: "json",
            type: "POST",
            url: urlToGo,
            data: data,
            success: function (resp) {
                if (resp.hasError === undefined) {
                    resp = resp.responseMessage;
                }
                if (resp.hasError === true) {
                    sharedSvc.showMsg(
                        {
                            title: resp.title,
                            message: resp.message,
                            type: "danger"
                        }).then(function () { def.reject({ isError: true }); });
                }
                else {
                    def.resolve();
                    if(redirect!=undefined && redirect==true){
                        window.cancelMeetingSource();
                    }
                }
            },
            error: function () {
                sharedSvc.showMsg(
                    {
                        title: "Error de red",
                        message: "<strong>No fue posible conectarse al servidor</strong> <br/><br/>Por favor intente más tarde",
                        type: "danger"
                    }).then(function () { def.reject({ isError: true }); });
            }
        };

        $.ajax(settings);
    };

            $scope.pastToJson = function(string){
                var result = jQuery.parseJSON( string );
                if(result == ""){
                    return  "[]";
                }else{
                    return result;
                }
            } ;

});

app.controller('innerVerificationController', function($scope, $timeout, $q) {

    $scope.init = function(){
        $('.date-picker').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
        if($scope.generalComponent){
            $scope.MsgError = "";
            var allSelectElements = $( "select" );
            $( "#divElementVerif" ).find( allSelectElements).each(function(){
                var nameModel = $(this).attr("ng-model");
                var nameModelComplete=nameModel.split(".");
                var nameModelLast = nameModelComplete[nameModelComplete.length-1];
                var toUpperName=nameModelLast.charAt(0).toUpperCase() + nameModelLast.substring(1);

                if(toUpperName == "Degree"){
                    $scope.lstDegree = $scope.school.level.degrees;
                    $scope.school.degree = $scope.lstDegree[0];
                    $scope.school.degreeId = $scope.lstDegree[0].id;
                }else{
                    var nameList = "list" + toUpperName;
                    if($scope[nameList] == undefined){
                        nameList = "lst" + toUpperName;
                        if($scope[nameList] == undefined){
                            nameList = "listElection";
                        }
                    }
                    if($scope[nameModelComplete[0]] == undefined){
                        $scope[nameModelComplete[0]] = {};
                    }
                    $scope[nameModelComplete[0]][nameModelComplete[1]]= $scope[nameList][0];
                    $scope[nameModelComplete[0]][nameModelComplete[1]+"Id"]= $scope[nameList][0].id;
                }
            });
          var divTypeImputedHome = $(".removeClassHide")
            $( "#divElementVerif" ).find(divTypeImputedHome).each(function(){
               $(this).removeClass("ng-hide");
            });
        }
        var allInput = $("input:text");
        var allTextArea = $("textarea");
        $("#divElementVerif").find(allInput).each(function (){
            $(this).val("");
        });
        $("#divElementVerif").find(allTextArea).each(function (){
            $(this).val("");
        });
    };

    $timeout(function() {
        $scope.init();
    }, 0);


});


app.controller('innerActivitiesController', function($scope, $timeout, $q,$rootScope) {

    $scope.specification = {};
    $scope.lstActivity = [];
    $scope.activityModel = [];
    $scope.activityList = [];
    $scope.pCSelected = [];
    $scope.relActivities = [];

    $scope.init = function(){
        $("#slctActivityV").chosen();
    };

    $timeout(function() {
        $scope.init();
    }, 0);

    $scope.matchActivities = function(){
        $scope.relActivities=[];
        for(var i = 0 ; i< $scope.activityModel.length; i++){
            var model = {};
            model.activity= {};
            model.activity.id =  $scope.activityModel[i].id;
            if($scope.activityModel[i].specification &&($scope.specification[$scope.activityModel[i].name] == undefined || $scope.specification[$scope.activityModel[i].name] == "")){
                $scope.activities = "false";
                $rootScope.$broadcast('refreshActivities',$scope.activities);
                return;
            }
            if($scope.specification[$scope.activityModel[i].name] != undefined){
                model.specification  =$scope.specification[$scope.activityModel[i].name];
            }else{
                model.specification = "";
            }
            $scope.relActivities.push(model);
        }
        $scope.activities =  JSON.stringify($scope.relActivities);
        $rootScope.$broadcast('refreshActivities',$scope.activities);

        return true;
    };

});
