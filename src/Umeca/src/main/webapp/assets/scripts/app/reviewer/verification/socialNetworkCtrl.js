app.controller('socialNetworkVerificationController', function($scope, $rootScope) {
    $scope.lstRel = [];
    $scope.lstDoc = [];
    $scope.lstLiv = [];
    $scope.lstDep = [];

    $scope.showChoicesSection = function(idSection, idList, idSource, sectionName, listView){
        var arg = [idSection, idList, idSource, sectionName, listView]
        $rootScope.$broadcast('ShowChoicesBySection',arg);
    };

    $scope.init = function(){
        if($scope.lstRel && $scope.lstRel.length > 0 ){
            if($scope.lsn.relId === undefined){
                $scope.lsn.rel = $scope.lstRel[0];
                $scope.lsn.relId = $scope.lsn.rel.id;
            }
            else{
                for(var i = 0; i < $scope.lstRel.length; i++){
                    var rel = $scope.lstRel[i];
                    if(rel.id === $scope.lsn.relId){
                        $scope.lsn.rel = rel;
                        break;
                    }
                }
            }
        }

        if($scope.lstDoc && $scope.lstDoc.length > 0 ){
            if($scope.lsn.docId === undefined) {
                console.log($scope.lstDoc.length - 1)
                $scope.lsn.doc = $scope.lstDoc[$scope.lstDoc.length - 1];
                $scope.lsn.docId = $scope.lsn.doc.id;
            }
            else {
                for (var i = 0; i < $scope.lstDoc.length; i++) {
                    var doc = $scope.lstDoc[i];
                    if (doc.id === $scope.lsn.docId) {
                        $scope.lsn.doc = doc;
                        break;
                    }
                }
            }
        }

        if($scope.lstLiv && $scope.lstLiv.length > 0){
            if($scope.lsn.livId === undefined){
                $scope.lsn.liv = $scope.lstLiv[0];
                $scope.lsn.livId = $scope.lsn.liv.id;
            }
            else{
                for(var i=0; i < $scope.lstLiv.length; i++){
                    var liv = $scope.lstLiv[i];

                    if(liv.id === $scope.lsn.livId){
                        $scope.lsn.liv = liv;
                        break;
                    }
                }
            }
        }

        if($scope.lstDep && $scope.lstDep.length > 0){
            if($scope.lsn.depId === undefined){
                $scope.lsn.dep = $scope.lstDep[0];
                $scope.lsn.depId = $scope.lsn.dep.id;
            }
            else{
                for(var i=0; i < $scope.lstDep.length; i++){
                    var dep = $scope.lstDep[i];

                    if(dep.id === $scope.lsn.depId){
                        $scope.lsn.dep = dep;
                        break;
                    }
                }
            }
        }

    };

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


    $scope.fillModel = function(){
        var template= "NO TIENE";
        var template2 = "Ninguno";
        if($scope.lsn.block === false){
            $scope.name = template;
            for(var i= 0; i < $scope.lstRel.length ; i++){
                if($scope.lstRel[i].name == template2){
                    $scope.lsn.rel = $scope.lstRel[i];
                    $scope.lsn.relId = $scope.lstRel[i].id;
                    break;
                }
            }
            $scope.phone = template;
            for(var i= 0; i < $scope.lstDoc.length ; i++){
                if($scope.lstDoc[i].name == template2){
                    $scope.lsn.doc = $scope.lstDoc[i];
                    $scope.lsn.docId = $scope.lstDoc[i].id;
                    break;
                }
            }

            $scope.age = 0;
            $scope.lsn.isAccompaniment = false;
            for(var i= 0; i < $scope.lstDep.length ; i++){
                if($scope.lstDep[i].name == "No"){
                    $scope.lsn.dep = $scope.lstDep[i];
                    $scope.lsn.depId = $scope.lstDep[i].id;
                    break;
                }
            }
            for(var i= 0; i < $scope.lstLiv.length ; i++){
                if($scope.lstLiv[i].name == "Si"){
                    $scope.lsn.liv = $scope.lstLiv[i];
                    $scope.lsn.livId = $scope.lstLiv[i].id;
                    break;
                }
            }
        }else{
            $scope.name = "";
            $scope.lsn.rel = $scope.lstRel[0];
            $scope.lsn.relId = $scope.lstRel[0].id;
            $scope.phone = template;
            $scope.lsn.doc = $scope.lstDoc[0];
            $scope.lsn.docId = $scope.lstDoc[0].id;
            $scope.age = "";
            $scope.phone = "";
            $scope.lsn.isAccompaniment = false;
            $scope.lsn.dep = $scope.lstDep[0];
            $scope.lsn.depId = $scope.lstDep[0].id;
            $scope.lsn.liv = $scope.lstLiv[0];
            $scope.lsn.livId = $scope.lstLiv[0].id;
            $scope.address="";
        }
    };

    $scope.lstSourceInfoSocial = {};

    $rootScope.$on('showAnswered', function (event, lstFieldInfo) {
        for (var a = 0; a < lstFieldInfo.length; a++) {
            if (lstFieldInfo[a].sectionCode == 3) {
                if(lstFieldInfo[a].code=='socialNetwork.comment')
                    $scope.lstSourceInfoSocial[lstFieldInfo[a].code] = true;
                else
                    $scope.lstSourceInfoSocial[lstFieldInfo[a].code + "." + lstFieldInfo[a].idFieldList] = true;
            }
        }
        $scope.$apply();
    });

    $scope.lstFinalInfoSocial = {};

    $rootScope.$on('showFinalAnswered', function (event, lstFieldInfo) {
        for (var a = 0; a < lstFieldInfo.length; a++) {
            if (lstFieldInfo[a].sectionCode == 3) {
                if(lstFieldInfo[a].code=='socialNetwork.comment')
                    $scope.lstFinalInfoSocial[lstFieldInfo[a].code] = true;
                else
                    $scope.lstFinalInfoSocial[lstFieldInfo[a].code + "." + lstFieldInfo[a].idFieldList] = true;
            }
        }
        $scope.$apply();
    });

});