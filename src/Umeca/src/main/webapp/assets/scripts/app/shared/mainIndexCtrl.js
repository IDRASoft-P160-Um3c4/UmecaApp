app.controller("mainIndexController", function($scope, sharedSvc){

    $scope.deleteMsg = function(id, urlToGo){
        sharedSvc.showConf({ title: "Confirmar eliminación", message: "¿Desea eliminar el mensaje de confirmación?", type: "warning" })
            .then($scope.doDeleteMsg(id, urlToGo));
    }

    $scope.doDeleteMsg = function(id, urlToPost){
        $.ajax({
            url: urlToPost,
            type: "POST",
            data: JSON.stringify({id: id}),
            success: $scope.handleSuccess,
            error: $scope.handleError,
            dataType: "json",
            contentType: "application/json"
        });
    }

    $scope.handleSuccess = function(data){
       /* try{
            $scope.workingTrack = false;
            $scope.$apply();
            if(data.hasError === true){
                sharedSvc.showMsg({title: "Planes de seguimiento",message: data.message,type: "danger"});
                return;
            }

            var info = $scope.processActivities(data);
            $scope.m.calendar.fullCalendar('removeEvents');

            for(i=0; i<info.length; i++){
                $scope.m.calendar.fullCalendar('renderEvent', info[i], true);
            }
            $scope.m.calendar.fullCalendar('unselect');
        }catch(ex){
            sharedSvc.showMsg({
                title: "Error de red",
                message: "<strong>No fue posible conectarse al servidor</strong> <br/><br/>Por favor intente más tarde",
                type: "danger"
            });
        }*/
    }

    $scope.handleError = function(data){
       /* $scope.workingTrack = false;
        sharedSvc.showMsg({
            title: "Error de red",
            message: "<strong>No fue posible conectarse al servidor</strong> <br/><br/>Por favor intente más tarde",
            type: "danger"
        });*/
    }
});
