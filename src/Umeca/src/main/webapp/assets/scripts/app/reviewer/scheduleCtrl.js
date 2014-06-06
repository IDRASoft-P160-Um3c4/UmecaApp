app.controller('scheduleController', function($scope, $timeout) {
    $scope.s = {};
    $scope.lstDayWeek = [];
    $scope.s.dayWeek = 0;
    $scope.listSchedule = [];

    $scope.init = function(){
        if($scope.listSchedule == undefined){
            $scope.listSchedule = [];
        }
        if($scope.lstDayWeek === undefined || $scope.lstDayWeek.length <= 0)
            return;

        if($scope.s.dayWeekId === undefined){
            $scope.s.dayWeek = $scope.lstDayWeek[0];
            $scope.s.dayWeekId = $scope.s.dayWeek.id;
        }
        else{
            for(var i=0; i < $scope.lstDayWeek.length; i++){
                var dayWeek = $scope.lstDayWeek[i];

                if(dayWeek.id === $scope.s.dayWeekId){
                    $scope.s.dayWeek = dayWeek;
                    break;
                }
            }
        }

    };


    $timeout(function() {
        $scope.init();
    }, 0);

   $scope.addSchedule = function(){
      if($scope.s.dayWeek ==undefined ||  $scope.s.start == undefined || $scope.s.end == undefined){
          $scope.msgError="Favor de seleccionar un día, una hora de incio y una hora de fin";
      }else{
          $scope.msgError="";
          var a ={};
          a.day = {};
          a.day.id = $scope.s.dayWeek.id;
          a.day.name = $scope.s.dayWeek.name;
          a.start = $scope.s.start;
          a.end = $scope.s.end;
          $scope.listSchedule.push( a);
          $scope.s.dayWeek == $scope.lstDayWeek[0];
          $scope.s.start = undefined;
          $scope.s.end = undefined;
          $scope.cleanArray();
      }
   };

    $scope.deleteSchedule = function(index){
      $scope.listSchedule.splice(index,1);
        $scope.cleanArray();

    };

    $scope.cleanArray  = function (){
        var abc = $scope.listSchedule;

        $scope.schString = JSON.stringify(abc);
        /*for(var item in $scope.listSchedule){
            delete item[$$hashKey];
        } */
    }
});