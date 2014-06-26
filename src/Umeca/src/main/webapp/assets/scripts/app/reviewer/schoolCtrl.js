app.controller('schoolController', function($scope, $timeout) {
    $scope.school = {};
    $scope.lstLevel = [];
    $scope.lstDegree = [];
    $scope.school.degree = 0;
    $scope.school.level = 0;
    $scope.content = "School";

    $scope.init = function(){
        if($scope.lstLevel === undefined || $scope.lstLevel.length <= 0)
            return;
        if($scope.degreeId == undefined){
            if($scope.school.levelId === undefined){
                $scope.school.level = $scope.lstLevel[0];
                $scope.school.levelId = $scope.school.level.id;
                $scope.lstDegree =  $scope.lstLevel[0].degrees;
                $scope.school.degree =$scope.lstDegree[0];
                $scope.school.degreeId = $scope.school.degree.id;
            }
         }else{
           for(var i = 0; i<$scope.lstLevel.length;i++){
               var grade = $scope.lstLevel[i].degrees;
               $scope.lstDegree = grade;
               for(var j=0; j<grade.length; j++){
                   if($scope.degreeId === grade[j].id ){
                       $scope.school.level = $scope.lstLevel[i];
                       $scope.school.levelId = $scope.lstLevel[i].id;
                       $scope.school.degree = $scope.lstLevel[i].degrees[j];
                       $scope.school.degreeId = $scope.lstLevel[i].degrees[j].id;
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


            $.post(urlToPost, $(formId).serialize())
                .success($scope.handleSuccess)
                .error($scope.handleError);

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