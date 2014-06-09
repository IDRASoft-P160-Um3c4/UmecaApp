app.controller('schoolController', function($scope, $timeout) {
    $scope.school = {};
    $scope.lstLevel = [];
    $scope.lstGrade = [];
    $scope.school.grade = 0;
    $scope.school.level = 0;

    $scope.init = function(){
        if($scope.lstLevel === undefined || $scope.lstLevel.length <= 0)
            return;
        if($scope.gradeId == undefined){
            if($scope.school.levelId === undefined){
                $scope.school.level = $scope.lstLevel[0];
                $scope.school.levelId = $scope.school.level.id;
                $scope.lstGrade =  $scope.lstLevel[0].grades;
                $scope.school.grade =$scope.lstGrade[0];
                $scope.school.gradeId = $scope.school.grade.id;
            }
         }else{
           for(var i = 0; i<$scope.lstLevel.length;i++){
               var grade = $scope.lstLevel[i].grades;
               $scope.lstGrade = grade;
               for(var j=0; j<grade.length; j++){
                   if($scope.gradeId === grade[j].id ){
                       $scope.school.level = $scope.lstLevel[i];
                       $scope.school.levelId = $scope.lstLevel[i].id;
                       $scope.school.grade = $scope.lstLevel[i].grades[j];
                       $scope.school.gradeId = $scope.lstLevel[i].grades[j].id;
                       $scope.$apply();
                       return;
                   }
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