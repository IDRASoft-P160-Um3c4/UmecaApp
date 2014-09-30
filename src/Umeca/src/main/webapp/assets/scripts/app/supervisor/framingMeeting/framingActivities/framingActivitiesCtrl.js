app.controller('framingActivitiesController', function ($scope, $timeout, $http) {

    $scope.act = {};

    $scope.actErrorMsg ="";
    $scope.actSuccessMsg="";
    $scope.specification = {};
    $scope.lstActivity = [];
    $scope.activityModel = [];
    $scope.activityList = [];
    $scope.relActivities = [];

    $scope.init = function () {
        $scope.loadFramingActivities();
        $(".chosen-select").chosen();
        $scope.matchActivities();
    };

    $timeout(function () {
        $scope.init();
    }, 0);


    $scope.selectedActivities = function (lstActivity, lstActivitySelect) {

        for (var i = 0; i < lstActivitySelect.length; i++) {
            for (var j = 0; j < lstActivity.length; j++) {
                if (lstActivity[j].id === lstActivitySelect[i].id) {
                    $scope.activityModel.push(lstActivity[j]);
                    if (lstActivity[j].specification) {
                        $scope.specification[lstActivity[j].name] = lstActivitySelect[i].specification;
                    }
                }
            }
        }
        $scope.matchActivities();
    };

    $scope.matchActivities = function () {
        $scope.relActivities = [];
        for (var i = 0; i < $scope.activityModel.length; i++) {
            var model = {};
            model.activity = {};
            model.activity.id = $scope.activityModel[i].id;
            if ($scope.specification[$scope.activityModel[i].name] != undefined) {
                model.specification = $scope.specification[$scope.activityModel[i].name];
            } else {
                model.specification = "";
            }
            $scope.relActivities.push(model);
        }
        if($scope.relActivities.length==0){
            $scope.activities = "";
        }else{
            $scope.activities = JSON.stringify($scope.relActivities);
        }
        return true;
    };

    $scope.WaitFor = false;
    $scope.Model = {};

    $scope.loadFramingActivities = function () {

        var currentTimeout = null;
        var ajaxConf;

        var url = $('#hidUrlAct').attr("value");
        var idCase = $('#hidIdCaseAct').attr("value");

        ajaxConf = {
            method: "POST",
            params: {idCase: idCase}/*,
             dataType: "json",
             contentType: "application/json"*/
        };

        ajaxConf.url = url;

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (data) {
                    $scope.fillActivities(data);
                });
        }, 200);
    };


    $scope.fillActivities = function (data) {
        $scope.act.occName=data.occName;
        $scope.act.occPlace=data.occPlace;
        $scope.act.occPhone=data.occPhone;
        $scope.act.activities=data.activities;
    };


    $scope.submitIdCaseParam = function (formId, urlToPost, id) {

        $(formId).validate();

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;

        var url = urlToPost + id;
        $scope.actSuccessMsg="";
        $scope.actErrorMsg="";
        $.post(url, $(formId).serialize())
            .success($scope.handleSuccess)
            .error($scope.handleError);

        return true;
    };

    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                $scope.actSuccessMsg = resp.message;
                $scope.$apply();
                return;
            }

            $scope.actErrorMsg = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.actErrorMsg = "Error inesperado de datos. Por favor intente más tarde.";
        }
    };

    $scope.handleError = function () {
        $scope.WaitFor = false;
        $scope.actErrorMsg = "Error de red. Por favor intente más tarde.";
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