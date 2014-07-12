﻿app.controller('upsertVerificationController', function ($scope, $rootScope, $timeout) {
    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};
    $scope.verification = false;
    $scope.nameScope = "estoy en UPSERT verificationController";
    $scope.generalComponent = false;


    $rootScope.$on('SetIdList', function (event,idList) {
        $scope.idList=idList;
    });

    $scope.init = function () {
        $('.date-picker').datepicker({autoclose: true, endDate: new Date()}).next().on(ace.click_event, function () {
            $(this).prev().focus();
        });
        var allInput = $("input:text");
        var allTextArea = $("textarea");
        $("#divElementVerif").find(allInput).each(function () {
            $(this).val("");
        });
        $("#divElementVerif").find(allTextArea).each(function () {
            $(this).val("");
        });
    };

    $scope.enableProperties = function () {

        $("input:text").each(function () {
            $(this).prop('disabled', false);
        });
        $("textarea").each(function () {
            $(this).prop('disabled', false);
        });
        $("select").each(function () {
            $(this).prop('disabled', false);
        });
        $("input:radio").each(function () {
            $(this).removeAttr('disabled');
        });
    };

    $scope.disableProperties = function () {
        $("input:text").each(function () {
            $(this).attr('disabled', 'disabled');
        });
        $("select").each(function () {
            $(this).prop("disabled", true);
            ;
        });
        $("textarea").each(function () {
            $(this).attr('disabled', 'disabled');
        });
        $("input:radio").each(function () {
            $(this).attr('disabled', 'disabled');
        });
    };

    $timeout(function () {
        $scope.init();
    }, 0);

    $scope.validateVerif = function (formSerialize) {
        var hasError = false;
        var submitElement = [];
        var vars = formSerialize.split("&");
        for (var i = 0; i < vars.length; i++) {
            var psEqual = vars[i].split("=");
            var e = {};
            e.name = psEqual[0];
            e.value = psEqual[1];

            var allElement = $("[name='" + psEqual[0] + "']");
            $("#divElementVerif").find(allElement).each(function () {
                var message = "";
                if (e.value == undefined || e.value == "") {
                    var elementAux = $(this);
                    var isHide = false;
                    var isParent = false;
                    do {
                        isHide = elementAux.hasClass("ng-hide");
                        isParent = (elementAux.attr("id") == "divElementVerif");
                        elementAux = elementAux.parent();
                    } while (!isHide && !isParent);
                    if (!isHide && isParent) {
                        message = $(this).attr("data-val-required");
                    }
                } else {
                    var valMax = $(this).attr("data-val-length-max");
                    var valMin = $(this).attr("data-val-length-min");
                    var pattern = $(this).attr("data-val-regex-pattern");
                    if (valMax != undefined && valMin != undefined && ($(this).val().length > parseInt(valMax) || $(this).val().length < parseInt(valMin))) {
                        message = $(this).attr("data-val-length")
                    }
                    if (pattern != undefined && $(this).val().match("^[0-9]+$")== null) {
                        message = $(this).attr("data-val-regex");
                    }
                }
                var spanVal = $(this).siblings("span");
                if(spanVal.hasClass("input-group-addon")){
                    spanVal = $(this).parent().siblings('span');
                }
                var c= $(this).is("input:text");
                if (message != "") {
                    spanVal.css("display","block");
                    spanVal.addClass("field-validation-error");
                    spanVal.removeClass("field-validation-valid");
                    spanVal.text(message);
                    hasError = true;
                } else {
                    if($(this).is("input:text") || $(this).is("textarea")){
                        spanVal.css("display","none");
                        spanVal.addClass("field-validation-valid");
                        spanVal.removeClass("field-validation-error");
                    }
                    if(e.value!=""){
                        var adding=true;
                        if($(this).is("input:radio")){
                            for(var z = 0 ; z<submitElement.length;z++){
                                if(submitElement[z].name == e.name){
                                    adding = false;
                                }
                            }
                        }
                        if(adding){
                            submitElement.push(e);
                        }
                    }
                }
            });

        }
        if (hasError)
            return null;
        return submitElement;
    };

    $scope.submit = function (formId) {
        var formSerialize = $(formId).serialize();
        var result = $scope.validateVerif(formSerialize);
        if (result == null) {
            return false;
        }
        if($scope.idList==undefined){
            $scope.idList = "";
        }
        var content = JSON.stringify(result);
        content = "val=" + content + "&&idCase=" + $scope.idCase + "&&idSource=" + $scope.idSource +"&&idList="+$scope.idList;
        $scope.WaitFor = true;
        $.post($scope.urlToGoSave, content)
            .success($scope.handleSuccess)
            .error($scope.handleError);
        return true;
    };


    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;
        $scope.MsgError = "";

        try {

            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }

            if (resp.hasError === false) {
                $scope.disableProperties();
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
        $scope.MsgError = "";
        $scope.verification = true;
        $scope.disableProperties();
        if ($scope.Model.dlg != undefined) {
            $scope.Model.dlg.modal('hide');
        }
        if ($scope.Model.def != undefined) {
            $scope.Model.def.reject({ isCancel: true });
        }
    };

    $scope.setDlg = function (dlg, urlToSubmit) {
        if ($scope.Model == undefined || $scope.Model.dlg == undefined) {
            $scope.Model.dlg = dlg;
            $scope.Model.url = urlToSubmit;
        }
    };

});
