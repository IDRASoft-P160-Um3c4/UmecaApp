app.controller('tecRevController', function ($scope, $timeout) {

    $scope.m = {};

    //seccion 1A

    $scope.m.totS1A;

    $scope.m.lstS1ASrv = [];

    $scope.m.lstS1A = [];

    $scope.m.lstS1A = [
        {"pts": 3, "msg": "Tiene más de dos años viviendo con su familia (padres, pareja o hijos) +3", "sel": false},
        {"pts": 2, "msg": "Tiene entre un año y dos viviendo con su familia (padres, pareja o hijos) +2", "sel": false},
        {"pts": 1, "msg": "Tiene menos de un año viviendo con su familia (padres, pareja, hijos) +1", "sel": false},
        {"pts": -1, "msg": "Vive sólo pero tiene una red familiar/social en la localidad que lo pueda apoyar. -1", "sel": false},
        {"pts": -2, "msg": "Vive sólo y no tiene una red familiar/social de apoyo. -2", "sel": false},
        {"pts": +1, "msg": "Tiene dependientes económicos en el estado. +1", "sel": false}
    ]
    $scope.fS1A = function (index) {
        if ($scope.m.lstS1A[index].sel == true) {
            $scope.m.totS1A += $scope.m.lstS1A[index].pts;
        } else {
            $scope.m.totS1A -= $scope.m.lstS1A[index].pts;
        }
    };
    //seccion 1A

    //seccion 1B

    $scope.lstS1B = [];

    $scope.lstS1B = [
        {"pts": 4, "msg": "Tiene más de 5 años viviendo el mismo domicilio en el Estado de Morelos. +4", "sel": false},
        {"pts": 3, "msg": "Tiene entre 3 y 5 años viviendo en el mismo domicilio en el Estado. +3", "sel": false},
        {"pts": 2, "msg": "Tiene entre 2-3 años viviendo en el mismo domicilio en el Estado. +2", "sel": false},
        {"pts": 1, "msg": 'Tiene más de un año viviendo en el mismo domicilio en el Estado o más de dos años viviendo en el Estado pero en diversos domicilios. +1', "sel": false},
        {"pts": -2, "msg": "Tiene menos de 1 año viviendo en el Estado o menos de 1 año en el mismo domicilio. -2", "sel": false},
        {"pts": -3, "msg": "Tiene domicilio primario fuera del Estado. -3", "sel": false},
        {"pts": -4, "msg": "No tiene domicilio fijo. -4", "sel": false},
        {"pts": -4, "msg": "Proporcionó dirección falsa. -4", "sel": false}
    ]

    $scope.fS1B = function () {
        //debugger;
        $scope.m.totS1B = $scope.lstS1B[$scope.m.selS1B].pts;
    };
    //seccion 1B

    //seccion 1C

    $scope.lstS1C = [];

    $scope.lstS1C = [
        {"pts": 2, "msg": "Es propietario de la vivienda que habita. +2", "sel": false},
        {"pts": 1, "msg": "Tiene más de 1 año rentando la vivienda que habita. +1", "sel": false},
        {"pts": -1, "msg": "Tiene menos de 1 año rentando o en vivienda prestada -1", "sel": false},
        {"pts": -2, "msg": "Está de visita en el estado. -2", "sel": false}
    ]

    $scope.fS1C = function () {
        $scope.m.totS1C = $scope.lstS1C[$scope.m.selS1C].pts;
    };
    //seccion 1C

    $scope.lstS1C = [];

    $scope.lstS1C = [
        {"pts": 2, "msg": "Es propietario de la vivienda que habita. +2", "sel": false},
        {"pts": 1, "msg": "Tiene más de 1 año rentando la vivienda que habita. +1", "sel": false},
        {"pts": -1, "msg": "Tiene menos de 1 año rentando o en vivienda prestada -1", "sel": false},
        {"pts": -2, "msg": "Está de visita en el estado. -2", "sel": false}
    ]

    $scope.fS1C = function () {
        $scope.m.totS1C = $scope.lstS1C[$scope.m.selS1C].pts;
    };

    //seccion 1D

    $scope.lstS1D = [];

    $scope.lstS1D = [
        {"pts": 3, "msg": "Tiene más de 2 años con empleo o estudiando. +3", "sel": false},
        {"pts": 2, "msg": "Tiene entre 1-2 años con empleo o estudiando. +2", "sel": false},
        {"pts": -1, "msg": "Tiene más de 6 meses y menos de 12 meses con empleo o estudiando. -1", "sel": false},
        {"pts": -2, "msg": "Tiene menos de 6 meses con empleo o estudiando. -2", "sel": false},
        {"pts": -4, "msg": "Actualmente no tiene empleo ni estudia. -4", "sel": false}
    ]

    $scope.fS1D = function () {
        $scope.m.totS1D = $scope.lstS1D[$scope.m.selS1D].pts;
    };

    //seccion 1D

    //seccion 1E

    $scope.lstS1E = [];

    $scope.lstS1E = [
        {"pts": +2, "msg": "Dentro de los últimos 2 años no lo han corrido del empleo o expulsado de la escuela. +2", "sel": false},
        {"pts": -2, "msg": "Dentro de los últimos 2 años lo han corrido o expulsado de la escuela 1 o más veces. -2", "sel": false},
        {"pts": 0, "msg": "Actualmente no trabaja ni estudia. +0", "sel": false}
    ]

    $scope.fS1E = function () {
        $scope.m.totS1E = $scope.lstS1E[$scope.m.selS1E].pts;
    };

    //seccion 1E


    //seccion 2A

    $scope.m.totS2A;

    $scope.m.lstS2ASrv = [];

    $scope.m.lstS2A = [];

    $scope.m.lstS2A = [
        {"pts": -1, "msg": "Tiene más de un proceso abierto.	-1", "sel": false},
        {"pts": -2, "msg": "Posible pena a imponer es superior a 6 años. -2", "sel": false},
        {"pts": -3, "msg": "Delito asociado con uno federal. -2", "sel": false}
    ]

    $scope.fS2A = function (index) {
        if ($scope.m.lstS2A[index].sel == true) {
            $scope.m.totS2A += $scope.m.lstS2A[index].pts;
        } else {
            $scope.m.totS2A -= $scope.m.lstS2A[index].pts;
        }
    };

    //seccion 2A

    //seccion 2B

    $scope.lstS2B = [];

    $scope.lstS2B = [
        {"pts": +2, "msg": "Dentro de los últimos 2 años no lo han corrido del empleo o expulsado de la escuela. +2", "sel": false},
        {"pts": -2, "msg": "Dentro de los últimos 2 años lo han corrido o expulsado de la escuela 1 o más veces. -2", "sel": false},
        {"pts": 0, "msg": "Actualmente no trabaja ni estudia. +0", "sel": false}
    ]

    $scope.fS2B = function () {
        $scope.m.totS2B = $scope.lstS2B[$scope.m.selS2B].pts;
    };

    //seccion 2B

    //seccion 3

    $scope.lstS3 = [];

    $scope.lstS3 = [
        {"pts": +2, "msg": "Dentro de los últimos 2 años no lo han corrido del empleo o expulsado de la escuela. +2", "sel": false},
        {"pts": -2, "msg": "Dentro de los últimos 2 años lo han corrido o expulsado de la escuela 1 o más veces. -2", "sel": false},
        {"pts": 0, "msg": "Actualmente no trabaja ni estudia. +0", "sel": false}
    ]

    $scope.fS3 = function () {
        $scope.m.totS3 = $scope.lstS3[$scope.m.selS3].pts;
    };

    //seccion 3

    //seccion 4

    $scope.m.totS4;

    $scope.m.lstS4Srv = [];

    $scope.m.lstS4 = [];

    $scope.m.lstS4 = [
        {"pts": -2, "msg": "Conoce a la víctima. -2", "sel": false},
        {"pts": -2, "msg": "Conoce a los testigos. -2", "sel": false}
    ]

    $scope.fS4 = function (index) {
        if ($scope.m.lstS4[index].sel == true) {
            $scope.m.totS4 += $scope.m.lstS4[index].pts;
        } else {
            $scope.m.totS4 -= $scope.m.lstS4[index].pts;
        }
    };

    //seccion 4

    //seccion 5

    $scope.m.totS5;

    $scope.m.lstS5Srv = [];

    $scope.m.lstS5 = [];

    $scope.m.lstS5 = [
        {"pts": -1, "msg": "Consume alcohol más de 2 veces al mes al punto de llegar a estado de ebriedad. -1", "sel": false},
        {"pts": -2, "msg": "Consume alcohol 1 a 2 veces por semana al punto de llegar a estado de ebriedad. -2", "sel": false},
        {"pts": -3, "msg": "Consume marihuana, cocaína o solventes 1-2 veces por semana. -3", "sel": false},
        {"pts": -4, "msg": "Consume alcohol más de 3 veces por semana al punto de emborracharse y otra substancia. -4", "sel": false},
        {"pts": -5, "msg": "Consume todos los días cualquier substancia. -5", "sel": false}
    ]
    $scope.fS5 = function (index) {
        if ($scope.m.lstS5[index].sel == true) {
            $scope.m.totS5 += $scope.m.lstS5[index].pts;
        } else {
            $scope.m.totS5 -= $scope.m.lstS5[index].pts;
        }
    };

    //seccion 5

    //calcular riesgo

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