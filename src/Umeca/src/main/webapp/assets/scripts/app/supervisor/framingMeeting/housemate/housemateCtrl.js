app.controller('housemateController', function ($scope, $timeout,$rootScope) {


    $scope.hm = {};

    $scope.init = function () {
        $scope.fillSelRelationship();
    };

    $timeout(function () {
        $scope.init();
    }, 0);

    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};

    $scope.fillSelRelationship = function () {

        if ($scope.lstRelationship === undefined || $scope.lstRelationship.length <= 0)
            return;

        if ($scope.hm.relationshipId === undefined) {
            $scope.hm.relationship = $scope.lstRelationship[0];
            $scope.hm.relationshipId = $scope.hm.relationship.id;
        }
        else {
            for (var i = 0; i < $scope.lstRelationship.length; i++) {
                var rel = $scope.lstRelationship[i];

                if (rel.id === $scope.hm.relationshipId) {
                    $scope.hm.relationship = rel;
                    break;
                }
            }
        }

    };

    $scope.submitIdCaseParam = function (formId, urlToPost, id) {


        $(formId).validate();

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;

        var url = urlToPost + id;

        $.post(url, $(formId).serialize())
            .success($scope.handleSuccess)
            .error($scope.handleError);

        return true;
    };

    $scope.handleSuccessWithId = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                $rootScope.$broadcast("onLastId", resp.Id);
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


    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;

        try {
            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }
            if (resp.hasError === false) {
                $scope.Model.dlg.modal('hide');
                $scope.Model.def.resolve({ isCancel: false });
                $rootScope.$broadcast("reloadEnvironment");
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