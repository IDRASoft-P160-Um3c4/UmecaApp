app.controller('verificationActivitiesController', function($scope, $timeout, $q, $rootScope) {
    $scope.WaitFor = false;
    $scope.MsgError = "";
    $scope.Model = {};
    $scope.verification = false;
    $scope.generalComponent = false;
    $scope.activities="";
    $scope.init = function(){

      };

    $rootScope.$on('refreshActivities', function (event,activities) {
        $scope.activities=activities;
    });


    $timeout(function() {
        $scope.init();
    }, 0);

    $scope.validateVerif = function () {
        $scope.MsgError="";
      if($scope.activities==""||$scope.activities==undefined){
          $scope.MsgError="Debe agregar una actividad"
          return null;
      }else if($scope.activities !=undefined && $scope.activities=="false"){
          $scope.MsgError="Debe agregar las especificaciones de cada actividad requerida"
          return null;
      }else{
         return $scope.activities;
      }
    };
    $scope.submit = function (formId) {
        var formSerialize = $(formId).serialize();
        var result = $scope.validateVerif(formSerialize);
        if (result == null) {
            return false;
        }

        var content= {};
        content.name="socialEnvironment.activities";
        content.value=result;
        var aux = [];
        aux[0] = content;
        var contentJson = JSON.stringify(aux);
        $scope.WaitFor = true;

        var formSerialize = $(formId).serialize();
        var content = "val=" + contentJson + "&&idCase=" + $scope.idCase + "&&idSource=" + $scope.idSource;
        $scope.WaitFor = true;
        $.post($scope.urlToGoSave, content)
            .success($scope.handleSuccess)
            .error($scope.handleError);
        return true;
    };

    $scope.cancel = function () {
        $scope.Model.dlg.modal('hide');
    };


    $scope.handleSuccess = function (resp) {
        $scope.WaitFor = false;
        $scope.MsgError = "";

        try {

            if (resp.hasError === undefined) {
                resp = resp.responseMessage;
            }

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


    $scope.setDlg = function (dlg, urlToSubmit) {
        if ($scope.Model == undefined || $scope.Model.dlg == undefined) {
            $scope.Model.dlg = dlg;
            $scope.Model.url = urlToSubmit;
        }
    };

});
