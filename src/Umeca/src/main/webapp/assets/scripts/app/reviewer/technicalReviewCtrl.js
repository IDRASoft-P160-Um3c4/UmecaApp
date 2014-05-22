app.controller('tecRevController', function ($scope, $timeout) {

    $scope.m = {};

    //seccion 5

    $scope.m.isEval = false;

    $scope.m.totRisk;

    $scope.fEval = function () {
        $scope.m.isEval = true;

        $scope.m.totRisk = $scope.m.totS1A + $scope.m.totS1B + $scope.m.totS1C + $scope.m.totS1D + $scope.m.totS1E +
            $scope.m.totS2A + $scope.m.totS2B +
            $scope.m.totS3 + $scope.m.totS4 + $scope.m.totS5;
    };

    /**/

    $scope.m.actTab = 0;
    $scope.m.secIdx = 0;

    $scope.m.extras = [];

    $scope.sectionList = [];

    $scope.lstTot=[];

    $scope.lstQuestSel=[];

    $scope.toObject = function (json) {
        return JSON.parse(json);
    }

    $scope.findQuestion = function (id) {
        for (var i = 0; i < $scope.lstQuestSel.length; i++) {
            if ($scope.lstQuestSel[i] == id)
                return i;
        }
        return null;
    }

    $scope.changeVal = function (type, totId, questionId, value) {

        var idx = null;
        idx = $scope.findQuestion(questionId);

        if (type == "checkbox") {

            if (idx != null) { //si ya lo habia seleccionado lo quito y resto el valor a su respectivo total
                $scope.lstQuestSel.splice(idx, 1);
                $scope.lstTot[totId] = parseInt($scope.lstTot[totId]) - parseInt(value);

            } else {
                $scope.lstQuestSel.push(questionId);
                $scope.lstTot[totId] = parseInt($scope.lstTot[totId]) + parseInt(value);
            }
        }
        else
        if (type == "radio") {

            if (idx != null) { //si ya lo habia seleccionado lo sustituyo la pregunta seleccionada y sustituyo el valor del total
                $scope.lstQuestSel[idx]=questionId;
            } else {
                $scope.lstQuestSel.push(questionId);
            }
            $scope.lstTot[totId] = value;
        }
    };
//a
    $scope.calcRisk = function(){

        //validar que se haya seleccionado al menos una pregunta de cada seccion;

        for(totSec in $scope.lstTot)
            $scope.totTecRev = parseInt($scope.totTecRev)+parseInt($scope.lstTot[totSec]);

        $scope.totTecRevxxx = "cadena de mierda";

        $scope.flgIsEvaluated=1;
    };

    $scope.init = function () {

        /*
         $scope.fS1B();
         $scope.fS1C();
         $scope.fS1D();
         $scope.fS1E();
         $scope.fS2B();
         $scope.fS3();*/
    };


    $timeout(function () {
        $scope.init();
    }, 0);


});