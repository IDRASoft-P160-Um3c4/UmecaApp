app.controller('crimeController', function ($scope, $timeout) {
    $scope.c = {};
    $scope.listElection = [];
    $scope.c.federal = 0;
    $scope.listCrime = [];
    $scope.optionsCrime = [];


    $scope.init = function () {
        $(".chosen-select").chosen();
        if ($scope.listCrime == undefined) {
            $scope.listCrime = [];
        }
        if ($scope.listElection === undefined || $scope.listElection.length <= 0)
            return;

        if ($scope.c.federalId === undefined) {
            $scope.c.federal = $scope.listElection[0];
            $scope.c.federalId = $scope.c.federal.id;
        }
        if($scope.optionsCrime.length>0){
            $scope.c.crime =$scope.optionsCrime[0];
        }
        $scope.c.crime =  undefined;
        $scope.cleanArray();
    };


    $timeout(function () {
        $scope.init();
    }, 0);

    $scope.validateCrime = function(){
        $scope.listMsgError = [];
        valid = true;
        var strArticle = $scope.c.article+"";
        if($scope.c.crime == undefined){
            $scope.listMsgError.push("Debe seleccionar un delito");
            valid=false;
        }

        if ( $scope.c.article== undefined || (strArticle.length > 100 || strArticle.length < 1)){
                $scope.listMsgError.push("La longitud del artículo debe ser entre 1 y 100 caracteres");
                valid = false;
            }
        if ($scope.c.comment!=undefined && $scope.c.comment.length > 255 ){
            $scope.listMsgError.push("La longitud del comentario debe ser entre 1 y 255 caracteres");
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
        a.crime=  {};
        a.crime=$scope.c.crime;
        a.comment = $scope.c.comment;
        a.federal.id = $scope.c.federal.id;
        a.federal.name = $scope.c.federal.name;
        a.article = $scope.c.article;
        $scope.listCrime.push(a);
        $scope.c.federal = $scope.listElection[0];
        $scope.c.comment = undefined;
        $scope.c.article = undefined;
        $scope.c.crime =  undefined;
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