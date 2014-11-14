app.controller('framingMeetingController', function ($scope, $timeout, $http, $rootScope, $sce) {

    $scope.fm = {}

    $scope.FMsuccessMsg = "";
    $scope.FMerrorMsg = "";

    $scope.FMerrorMsgLst = "";
    $scope.listMsgError = {};

    $scope.addressSuccessMsg = "";
    $scope.housemateSuccessMsg = "";
    $scope.referencesSuccessMsg = "";
    $scope.drugsSuccessMsg = "";
    $scope.activitesSuccessMsg = "";
    $scope.jobSuccessMsg = "";
    $scope.victimSuccessMsg = "";


    $scope.addressErrorMsg = "";
    $scope.housemateErrorMsg = "";
    $scope.referencesErrorMsg = "";
    $scope.drugsErrorMsg = "";
    $scope.activitesErrorMsg = "";
    $scope.jobErrorMsg = "";
    $scope.victimErrorMsg = "";


    $scope.disableView = function () {

        if ($scope.fm.objView.canTerminate == false) {
            $("#divFM :input").attr("disabled", true);
        }
        else {
            $("#divFM :input").attr("disabled", false);
        }
    };

    $scope.changeZIndex = function (elementClick) {
        $("#liPersonalData").css("z-index", "0");
        $("#liImputedHome").css("z-index", "0");
        $("#liReference").css("z-index", "0");
        $("#liSocialNetwork").css("z-index", "0");
        $("#liJob").css("z-index", "0");
        $("#liSchool").css("z-index", "0");
        $("#liFinger").css("z-index", "0");
        $("#liActivities").css("z-index", "0");
        $("#liAnalysis").css("z-index", "0");
        $("#liQuestion").css("z-index", "0");
        $("#liDrug").css("z-index", "0");
        $("#liLeaveCountry").css("z-index", "0");
        $("#liFinger").css("z-index", "0");
        $("#" + elementClick).css("z-index", "1");

    };

    $scope.returnFM = function () {

        if ($scope.returnId && $scope.returnId != null && $scope.returnId != undefined && $scope.returnId > 0) {
            window.goToUrlMvcUrl($scope.urlManagerSup);
        }
        else
            window.goToUrlMvcUrl($scope.urlIndex);
    };

    $scope.init = function () {
        $scope.disableView();
    };

    $timeout(function () {
        $scope.init();
    }, 0);


    $scope.resizeMap = function () {
        $timeout(function () {
            $rootScope.$broadcast("resizeMap");
        }, 10);

    };

    $scope.doTerminate = function () {
        var currentTimeout = null;
        var url = "doTerminate.json";
        var idCase = $scope.fm.objView.idCase;
        var ajaxConf;

        ajaxConf = {
            method: "GET",
            params: {idCase: idCase}
        };

        ajaxConf.url = url;

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (data) {

                    if (data.hasError == undefined) {
                        data = data.responseMesage;
                    }

                    if (data.hasError == true) {
                        var obj = JSON.parse(data.message);
                        if (obj.groupMessage != undefined) {
                            for (var i = 0; i < obj.groupMessage.length; i++) {
                                var g1 = obj.groupMessage[i];
                                $scope.listMsgError[g1.section] = $sce.trustAsHtml(g1.messages);
                            }
                        }
                    }
                    else
                        $scope.returnFM();
                });
        }, 200);
    };

    $scope.openNewPage = function (url, id) {
        var params = [];
        params["idParam"] = id;
        window.goToNewUrl(url, params, {opts: "fullscreen=no, top=0, left=0, width=500, height=300"});
    };

    $scope.submitComments = function (formId, urlToPost, id) {
        $(formId).validate();

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;

        var url = urlToPost + id;

        $scope.actSuccessMsg = "";
        $scope.actErrorMsg = "";

        $.post(url, $(formId).serialize())
            .success(function (resp) {
                $scope.handleSuccessComment(resp, formId);
            })
            .error($scope.handleErrorComments(formId));

        return true;
    };

    $scope.handleSuccessComment = function (resp, formId) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                var arrMsg = resp.message.split('|');
                var idMsg = arrMsg[0];
                var msg = arrMsg[1];

                switch (idMsg) {
                    case "1":
                        $scope.addressSuccessMsg = $sce.trustAsHtml(msg);
                        break;
                    case "2":
                        $scope.housemateSuccessMsg = $sce.trustAsHtml(msg);
                        break;
                    case "3":
                        $scope.referencesSuccessMsg = $sce.trustAsHtml(msg);
                        break;
                    case "4":
                        $scope.drugsSuccessMsg = $sce.trustAsHtml(msg);
                    case "5":
                        $scope.activitesSuccessMsg = $sce.trustAsHtml(msg);
                        break;
                    case "6":
                        $scope.jobSuccessMsg = $sce.trustAsHtml(msg);
                        break;
                    case "7":
                        $scope.victimSuccessMsg = $sce.trustAsHtml(msg);
                        break;
                }
            }
        } catch (e) {
            switch (formId) {
                case "FormCommentAddress":
                    $scope.addressErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                    break;
                case "FormCommentHousemate":
                    $scope.housemateErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                    break;
                case "FormCommentReferences":
                    $scope.referencesErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                    break;
                case "FormCommentDrugs":
                    $scope.drugsErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                    break;
                case "FormCommentActivities":
                    $scope.activitesErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                    break;
                case "FormCommentJob":
                    $scope.jobErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                    break;
                case "FormCommentVictim":
                    $scope.victimErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                    break;
            }
        }
        $scope.$apply();
        return;
    };

    $scope.handleErrorComments = function (formId) {
        $scope.WaitFor = false;

        switch (formId) {
            case "FormCommentAddress":
                $scope.addressErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                break;
            case "FormCommentHousemate":
                $scope.housemateErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                break;
            case "FormCommentReferences":
                $scope.referencesErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                break;
            case "FormCommentDrugs":
                $scope.drugsErrorMsg = $sce.trustAsHtml("Error inesperado de datos. Por favor intente más tarde.");
                break;
        }
    };

})
;