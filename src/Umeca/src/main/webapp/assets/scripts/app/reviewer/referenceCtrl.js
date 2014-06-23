app.controller('referenceController', function($scope, $timeout) {
    $scope.r = {};
    $scope.lstRelationship = [];
    $scope.lstDocumentType = [];
    $scope.r.rel = 0;
    $scope.r.doc = 0;

    $scope.init = function(){
        if($scope.lstRelationship === undefined || $scope.lstRelationship.length <= 0)
            return;

        if($scope.r.relId === undefined){
            $scope.r.rel = $scope.lstRelationship[0];
            $scope.r.relId = $scope.r.rel.id;
        }
        else{
            for(var i=0; i < $scope.lstRelationship.length; i++){
                var rel = $scope.lstRelationship[i];

                if(rel.id === $scope.r.relId){
                    $scope.r.rel = rel;
                    break;
                }
            }
        }
        if($scope.lstDocumentType === undefined || $scope.lstDocumentType.length <= 0)
            return;

        if($scope.r.docId === undefined){
            $scope.r.doc = $scope.lstDocumentType[0];
            $scope.r.docId = $scope.r.doc.id;
        }
        else{
            for(var i=0; i < $scope.lstDocumentType.length; i++){
                var doc = $scope.lstDocumentType[i];

                if(doc.id === $scope.r.docId){
                    $scope.r.doc = doc;
                    break;
                }
            }
        }


    };


    $timeout(function() {
        $scope.init();
    }, 0);
    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};

    $scope.submit = function (formId, urlToPost, hasReturnId) {

        if ($(formId).valid() == false) {
            $scope.Invalid = true;
            return false;
        }
        $scope.WaitFor = true;

        if (hasReturnId === true) {
            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccessWithId)
                .error($scope.handleError);
        }
        else {
            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccess)
                .error($scope.handleError);
        }
        return true;
    };

    $scope.handleSuccessWithId = function (resp) {
        $scope.WaitFor = false;

        try {
            if(resp.hasError===undefined){
                resp=resp.responseMessage;
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
            if(resp.hasError===undefined){
                resp=resp.responseMessage;}
            if (resp.hasError === false) {
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