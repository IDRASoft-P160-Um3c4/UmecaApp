app.controller('leavingController', function($scope, $timeout) {
    $scope.l = {};
    $scope.listLocation = [];
    $scope.listElection = [];
    $scope.listCountry = [];
    $scope.l.oc= 0;
    $scope.l.doc = 0;
    $scope.l.cfId = 0;
    $scope.l.cfId = 0;
    $scope.country=0;
    $scope.state=0;
    $scope.m = {};

    $scope.init = function(){
        if($scope.listCountry === undefined || $scope.listCountry.length <= 0)
            return;

        if($scope.countryId === undefined){
            $scope.country = $scope.listCountry[0];
            $scope.countryId = $scope.country.id;
        }
        else{
            for(var i=0; i < $scope.listCountry.length; i++){
                var country = $scope.listCountry[i];

                if(country.id === $scope.countryId){
                    $scope.country = country;
                    break;
                }
            }
        }
        if($scope.listElection === undefined || $scope.listElection.length <= 0)
            return;

        if($scope.l.ocId === undefined){
            $scope.l.oc = $scope.listElection[0];
            $scope.l.ocId = $scope.l.oc.id;
        }
        else{
            for(var i=0; i < $scope.listElection.length; i++){
                var oc = $scope.listElection[i];

                if(oc.id === $scope.l.ocId){
                    $scope.l.oc = oc;
                    break;
                }
            }
        }

        if($scope.l.docId === undefined){
            $scope.l.doc = $scope.listElection[0];
            $scope.l.docId = $scope.l.doc.id;
        }
        else{
            for(var i=0; i < $scope.listElection.length; i++){
                var doc = $scope.listElection[i];

                if(doc.id === $scope.l.docId){
                    $scope.l.doc = doc;
                    break;
                }
            }
        }

        if($scope.l.cfId === undefined){
            $scope.l.cf = $scope.listElection[0];
            $scope.l.cfId = $scope.l.cf.id;
        }
        else{
            for(var i=0; i < $scope.listElection.length; i++){
                var cf = $scope.listElection[i];

                if(cf.id === $scope.l.cfId){
                    $scope.l.cf = cf;
                    break;
                }
            }
        }

        if($scope.l.facId === undefined){
            $scope.l.fac = $scope.listElection[0];
            $scope.l.facId = $scope.l.fac.id;
        }
        else{
            for(var i=0; i < $scope.listElection.length; i++){
                var fac = $scope.listElection[i];

                if(fac.id === $scope.l.facId){
                    $scope.l.fac = fac;
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
                $scope.msgExito = resp.message;
                $scope.$apply();
                return;
            }
            $scope.msgError = resp.message;
            $scope.$apply();

        } catch (e) {
            $scope.msgError = "Error inesperado de datos. Por favor intente más tarde.";
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