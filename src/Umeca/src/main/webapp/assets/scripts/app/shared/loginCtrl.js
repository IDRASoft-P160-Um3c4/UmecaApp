﻿app.controller('loginController', function ($scope, $http) {

    var msgErrorDefault;
    $scope.WaitFor = false;
    $scope.m = { username:"", password: "" };
    $scope.idModal = "#modalLogin";

    var resetLogin = function () {
        $scope.WaitFor = false;
    };


    var onError = function () {
        $scope.msgError = msgErrorDefault;
        resetLogin();
    };

    $scope.$on('onLinkLogin', function () {
        $scope.clear();
        $($scope.idModal).modal('show');
    });

    var onSuccess = function (jResp) {
        try {
            if (jResp.hasError) {
                $scope.msgError = jResp.message;
            }
            else {
                //Redirect
                window.location.replace(jResp.urlToGo);
                return false;
            }
        } catch (e) {
            $scope.msgError = e;
        }

        resetLogin();
        return false;
    };

    $scope.clear = function () {
        $scope.msgError = "";
        $scope.m.username = "";
        $scope.m.password = "";
    };

    $scope.login = function (formId, msgError) {

        msgErrorDefault = msgError;
        $.validator.unobtrusive.parse(formId);
        if ($(formId).valid() == false) {
            return false;
        }

        $scope.WaitFor = true;

        var m = $scope.m;


        $http({
            method: 'POST',
            url: '/loginAccount.json',
            data: JSON.stringify({
                username: m.username,
                password: m.password
            }),
            cache: false,
            headers: {'Content-Type': 'application/json'}
        }).success(onSuccess).error(onError);
    };
});

