app.controller('proceedingLegalController', function ($scope, $timeout, $sce, $http) {
    $scope.m = {};
    $scope.listElection = [];
    $scope.lstRelationship = [];
    $scope.m.rel = 0;
    $scope.m.complyPM = 0;
    $scope.m.complyCSPP = 0;
    $scope.m.complyProcessAbove = 0;
    $scope.validf = {};
    $scope.listMsgErrorCon = [];
    $scope.nameAddress = "domicileVictim."


    $scope.init = function () {
        if ($scope.managereval == true) {
            $("input:text").each(function () {
                $(this).attr('disabled', 'disabled');
            });
            $("input:checkbox").each(function () {
                $(this).attr('disabled', 'disabled');
            });
            $("select").each(function () {
                $(this).prop("disabled", true);
                ;
            });
            $("textarea").each(function () {
                $(this).attr('disabled', 'disabled');
            });
        }
        if ($scope.listElection === undefined || $scope.listElection.length <= 0)
            return;

        if ($scope.m.complyPMId === undefined) {
            $scope.m.complyPM = $scope.listElection[0];
            $scope.m.complyPMId = $scope.m.complyPM.id;
        } else {
            for (var i = 0; i < $scope.listElection.length; i++) {
                var n = $scope.listElection[i];
                if (n.id == $scope.m.complyPMId) {
                    $scope.m.complyPM = n;
                    $scope.m.complyPMId = n.id;
                }
            }
        }

        if ($scope.m.complyCSPPId === undefined) {
            $scope.m.complyCSPP = $scope.listElection[0];
            $scope.m.complyCSPPId = $scope.m.complyCSPP.id;
        } else {
            for (var i = 0; i < $scope.listElection.length; i++) {
                var n = $scope.listElection[i];
                if (n.id == $scope.m.complyCSPPId) {
                    $scope.m.complyCSPP = n;
                    $scope.m.complyCSPPId = n.id;
                }
            }
        }
        if ($scope.m.complyProcessAboveId === undefined) {
            $scope.m.complyProcessAbove = $scope.listElection[0];
            $scope.m.complyProcessAboveId = $scope.m.complyProcessAbove.id;
        } else {
            for (var i = 0; i < $scope.listElection.length; i++) {
                var n = $scope.listElection[i];
                if (n.id == $scope.m.complyProcessAboveId) {
                    $scope.m.complyProcessAbove = n;
                    $scope.m.complyProcessAboveId = n.id;
                }
            }
        }
        $scope.verifNumber();
        $scope.fillSelect("m", "state", "lstStates", "stateId");
        $timeout(function () {
            if ($scope.m.state !== undefined)
                $scope.getMun();
        }, 0);
    };

    $scope.verifNumber = function () {
        if ($scope.m.openProcessNumber == 0 && $scope.m.numberConvictions == 0) {
            for (var i = 0; i < $scope.listElection.length; i++) {
                var n = $scope.listElection[i];
                if (n.name == "No aplica") {
                    $scope.m.complyProcessAbove = n;
                    $scope.m.complyProcessAboveId = n.id;
                    $scope.m.complyCSPP = n;
                    $scope.m.complyCSPPId = n.id;
                    $scope.m.complyPMId = n.id;
                    $scope.m.complyPM = n;
                }
            }
        }
    };

    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};

    $scope.submit = function (formId, urlToPost, hasReturnId) {
//        var forms = formId.split(",");
//        var val = true;
//        for(var i=0; i<forms.length; i++){
//            if($(forms[i]).valid() == false){
//                $scope.validf["form"+i] = true;
//                val  = false;
//            }else{
//                $scope.validf["form"+i] = false;
//            }
//        }
//        if (!val) {
//            $scope.Invalid = true;
//            return false;
//        }
        $scope.WaitFor = true;

        $.post(urlToPost, $(formId).serialize())
            .success($scope.handleSuccess)
            .error($scope.handleError);
        return true;
    };

    $scope.showMessageError = function (elementClick) {
        $("#divErrorMessage").show();
        var position = $(".tab-content").position();
        $("#divErrorMessage").css("left", position.left + 5);
        $("#divErrorMessage").addClass("errorMessageClass");
        $scope.entityError = elementClick;
    };

    $scope.hideMessageError = function () {
        $("#divErrorMessage").hide();
    };
    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        $scope.listMsgError = {};
        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                if (resp.title == "redirect") {
                    window.cancelLegal();
                } else if (resp.title == "current") {
                    $scope.msgExitoCurrent = $sce.trustAsHtml(resp.message);
                    $scope.$apply();
                } else if (resp.title == "previous") {
                    $scope.msgExitoPrevious = $sce.trustAsHtml(resp.message);
                    $scope.$apply();
                }

                return;
            }
            var obj = JSON.parse(resp.message);
            if (obj.groupMessage != undefined) {
                for (var i = 0; i < obj.groupMessage.length; i++) {
                    var g1 = obj.groupMessage[i];
                    $scope.listMsgError[g1.section] = $sce.trustAsHtml(g1.messages);
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

    $scope.searchPreviousCase = function () {
        var data = {};
        data.sName = $scope.sName;
        data.sLastNameP = $scope.sLastNameP;
        data.sLastNameM = $scope.sLastNameM;
        data.idCase = $scope.idCase;
        $.post($scope.urlSearchPreviousCase, data)
            .success($scope.handleSuccessFindPrevious)
            .error($scope.handleErrorFindPrevious);
    };

    $scope.handleSuccessFindPrevious = function (resp) {
        $scope.listLegalBefore = JSON.parse(resp.message);
        if ($scope.listLegalBefore.length == 0) {
            $scope.sNameS = $scope.sName;
            $scope.sLastNamePS = $scope.sLastNameP;
            $scope.sLastNameMS = $scope.sLastNameM;
        }
        $scope.$apply();
    };

    $scope.handleErrorFindPrevious = function (resp) {
        $scope.msgErrorFind = "Error de red. Favor de intentarlo mas tarde"
        $scope.$apply();
    };

    $scope.formatHtml = function (sHtml) {
        return $sce.trustAsHtml(sHtml);
    };

    $scope.fillSelect = function (obj, prop, list, model) {
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

    $scope.getMun = function () {

        if ($scope.m.state === undefined)
            return;

        var urlMun = $("#urlMun").val();
        var currentTimeout = null;
        var ajaxConf = {
            method: 'POST',
            url: urlMun
        };

        ajaxConf.params = {idState: $scope.m.state.id};

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (result) {
                    $scope.lstMun = result;
                    if (currentTimeout) {
                        $timeout.cancel(currentTimeout);
                    }
                    $scope.fillSelect("m", "municipality", "lstMun", "municipalityId");
                    $timeout(function () {
                        if ($scope.m.municipality !== undefined)
                            $scope.getLoc();
                    }, 0);
                });
        }, 0);
    };

    $scope.getLoc = function () {
        if ($scope.m.municipality === undefined)
            return;

        var urlLoc = $("#urlLoc").val();
        var currentTimeout = null;
        var ajaxConf = {
            method: 'POST',
            url: urlLoc
        };

        ajaxConf.params = {idMun: $scope.m.municipality.id};

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (result) {
                    $scope.lstLocation = result;
                    if (currentTimeout) {
                        $timeout.cancel(currentTimeout);
                    }
                    $timeout(function () {
                        $scope.fillSelect("m", "location", "lstLocation", "locationId");
                    }, 0);
                });
        }, 0);
    };


    $timeout(function () {
        $scope.init();
    }, 0);

});