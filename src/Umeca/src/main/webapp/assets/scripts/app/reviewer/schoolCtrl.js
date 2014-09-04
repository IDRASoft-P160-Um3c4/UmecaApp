app.controller('schoolController', function($scope, $timeout,$q) {
    $scope.school = {};
    $scope.lstLevel = [];
    $scope.lstDegree = [];
    $scope.school.degree = 0;
    $scope.school.level = 0;
    $scope.content = "School";
    $scope.Model = {};
    $scope.Model.def= $q.defer();
    $scope.Model.dlg="";
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

   $scope.fillModel = function(){
       var template ="NO ESTUDIA";
       if($scope.block == false){
            $scope.school.name=template;
           $scope.school.phone = template;
           $scope.school.address = template;
           $scope.school.specification = template;
           for(var i=0 ; i<$scope.lstLevel.length; i++){
               if($scope.lstLevel[i].name == "Otro"){
                   $scope.school.level = $scope.lstLevel[i];
                   $scope.school.levelId = $scope.lstLevel[i].id;
                   $scope.lstDegree =  $scope.lstLevel[i].degrees;
                   $scope.school.degree =$scope.lstDegree[0];
                   $scope.school.degreeId = $scope.school.degree.id;
                   break;
               }
           }
       }else{
           $scope.school.name="";
           $scope.school.phone = "";
           $scope.school.address = "";
           $scope.school.specification = "";
       }
   }
});