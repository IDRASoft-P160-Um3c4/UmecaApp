app.controller('crimeController', function ($scope, $timeout) {
    $scope.c = {};
    $scope.listElection = [];
    $scope.c.federal = 0;
    $scope.listCrime = [];


    $scope.init = function () {
        if ($scope.listCrime == undefined) {
            $scope.listCrime = [];
        }
        if ($scope.listElection === undefined || $scope.listElection.length <= 0)
            return;

        if ($scope.c.federalId === undefined) {
            $scope.c.federal = $scope.listElection[0];
            $scope.c.federalId = $scope.c.federal.id;
        }
    };


    $timeout(function () {
        $scope.init();
    }, 0);

    $scope.validateCrime = function(){
        $scope.listMsgError = [];
        var valid = true;
        if ($scope.c.name == undefined || $scope.c.article == undefined || $scope.c.name == "" || $scope.c.article == "") {
            $scope.listMsgError.push("Para agregar un delito debe ingresar un nombre y un artículo.");
            return false;
        }
        var strName= $scope.c.name+"";
        if (strName.length > 200 || strName.length < 5){
            $scope.listMsgError.push("La longitud del delito debe ser entre 5 y 200 caracteres");
            valid = false;
        }
        var strArticle = $scope.c.article+"";
        if (strArticle.length > 100 || strArticle.length < 1){
                $scope.listMsgError.push("La longitud del artículo debe ser entre 1 y 100 caracteres");
                valid = false;
            }
        return valid;
    }
    $scope.addCrime = function () {
        if($scope.validateCrime() == false){
            return false;
        }

        var a = {};
        a.federal = {};
        a.federal.id = $scope.c.federal.id;
        a.federal.name = $scope.c.federal.name;
        a.name = $scope.c.name;
        a.article = $scope.c.article;
        $scope.listCrime.push(a);
        $scope.c.federal = $scope.listElection[0];
        $scope.c.name = undefined;
        $scope.c.article = undefined;
        $scope.cleanArray();

    };

    $scope.deleteCrime = function (index) {
        $scope.listCrime.splice(index, 1);
        $scope.cleanArray();

    };

    $scope.cleanArray = function () {
        var abc = $scope.listCrime;
        $scope.crimeString = JSON.stringify(abc);
        /*for(var item in $scope.listSchedule){
         delete item[$$hashKey];
         } */
    }
});