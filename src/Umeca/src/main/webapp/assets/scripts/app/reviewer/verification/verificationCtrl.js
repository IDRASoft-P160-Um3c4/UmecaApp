app.controller('verificationController', function($scope, $timeout, $q,sharedSvc) {
    $scope.def= $q.defer();
    $scope.verification=true;
    $scope.nameScope="estoy en verificationController";
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


    $scope.doConfirmVerifEqual = function (code,id) {
        var data = {
            code: code
        };
        if(id!=undefined){
            data.id=id;
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
            code: code
        };
        if(id!=undefined){
            data.id=id;
        }
        var urlToGo= $scope.urlVerifNotKnow;
        var def = $q.defer();
        sharedSvc.showConf({ title: "Confirmación de verificación", message: "Establecer que la fuente no conoce la información proporcionada por el imputado", type: "inverse" }).
            then(function () {
                $scope.doPost(data, urlToGo, def);
            }, def.reject);
        return def.promise;
    };


    $scope.doPost = function (data, urlToGo, def) {
        var settings = {
            dataType: "json",
            type: "POST",
            url: urlToGo,
            data: data,
            success: function (resp) {
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



});

app.controller('innerVerificationController', function($scope, $timeout, $q) {

    $scope.init = function(){
    //   alert("lalala");
    };

    $timeout(function() {
        $scope.init();
    }, 0);


});