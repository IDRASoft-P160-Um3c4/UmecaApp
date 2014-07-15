app.controller('socialNetworkController', function($scope, $timeout) {
    $scope.p = {};
    $scope.lstRel = [];
    $scope.lstDoc = [];
    $scope.lstLiv = [];
    $scope.lstDep = [];
    $scope.p.rel = 0;
    $scope.p.doc = 0;
    $scope.p.liv = 0;
    $scope.p.dep = 0;


    $scope.init = function(){
        if($scope.lstRel === undefined || $scope.lstRel.length <= 0)
            return;

        if($scope.p.relId === undefined){
            $scope.p.rel = $scope.lstRel[0];
            $scope.p.relId = $scope.p.rel.id;
        }
        else{
            for(var i=0; i < $scope.lstRel.length; i++){
                var rel = $scope.lstRel[i];

                if(rel.id === $scope.p.relId){
                    $scope.p.rel = rel;
                    break;
                }
            }
        }
        if($scope.lstDoc === undefined || $scope.lstDoc.length <= 0)
            return;

        if($scope.p.docId === undefined){
            $scope.p.doc = $scope.lstDoc[0];
            $scope.p.docId = $scope.p.doc.id;
        }
        else{
            for(var i=0; i < $scope.lstDoc.length; i++){
                var doc = $scope.lstDoc[i];

                if(doc.id === $scope.p.docId){
                    $scope.p.doc = doc;
                    break;
                }
            }
        }
        if($scope.lstLiv === undefined || $scope.lstLiv.length <= 0)
            return;

        if($scope.p.livId === undefined){
            $scope.p.liv = $scope.lstLiv[0];
            $scope.p.livId = $scope.p.liv.id;
        }
        else{
            for(var i=0; i < $scope.lstLiv.length; i++){
                var liv = $scope.lstLiv[i];

                if(liv.id === $scope.p.livId){
                    $scope.p.liv = liv;
                    break;
                }
            }
        }


        if($scope.lstDep === undefined || $scope.lstDep.length <= 0)
            return;

        if($scope.p.depId === undefined){
            $scope.p.dep = $scope.lstDep[0];
            $scope.p.depId = $scope.p.dep.id;
        }
        else{
            for(var i=0; i < $scope.lstDep.length; i++){
                var dep = $scope.lstDep[i];

                if(dep.id === $scope.p.depId){
                    $scope.p.dep = dep;
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