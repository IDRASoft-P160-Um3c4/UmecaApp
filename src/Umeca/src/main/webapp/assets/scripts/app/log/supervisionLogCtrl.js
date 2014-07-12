app.controller("supervisionLogController", function($scope){

    $scope.reconstructedLstActMonPlan = [];

    $scope.idToObject = function(id, lstCat){
        for(var i=0; i<lstCat.length; i++){
            var cat = lstCat[i];
            if(cat.id == id){
                return cat;
            }
        }
        return undefined;
    };

    $scope.setStatus = function(status){
        switch(status){
            case -1:
                return "No definido";
            case 0:
                return "Incumplido";
            case 1:
                return "Cumplido";
            default:
                return "NA";
        }
    }

    $scope.generateAssignedArrangements = function(actMonPlanId){
        var bFound = false;
        var lstAssignedArrangements = [];
        for(var i=$scope.lstActMonPlanArrangement.length-1; i>=0; i--){
            var mpArr = $scope.lstActMonPlanArrangement[i];
            if(mpArr.actMonPlanId === actMonPlanId){
                bFound = true;
                var assArr = $scope.idToObject(mpArr.assignedArrangementId, $scope.lstHfAssignedArrangement);
                lstAssignedArrangements.push({
                    name: assArr.name,
                    status: $scope.setStatus(mpArr.status)
                });

                $scope.lstActMonPlanArrangement.splice(i,1);
            }

            if(bFound === true && mpArr.actMonPlanId !== actMonPlanId)
                break;
        }

        return lstAssignedArrangements;
    }

    $scope.constructActMonPlan = function(){

        $scope.reconstructedLstActMonPlan = [];

        for(var i=0; i<$scope.lstActMonPlan.length; i++){
            var act = $scope.lstActMonPlan[i];

            var actSup = $scope.idToObject(act.actSupervisionId, $scope.lstActivities);
            var aidSource = $scope.idToObject(act.aidSourceId, $scope.lstSources);

            //id
            var lstAssignedArrangements = $scope.generateAssignedArrangements(act.id);
            var comments = (act.comments === undefined || act.comments === null) ? "NA" : act.comments;

            var actRec = {
                start: act.start,
                end: act.end,
                supActivity: (actSup === undefined ? "NA" : actSup.name),
                aidSource: (aidSource === undefined ? "NA" : aidSource.name + " / " + aidSource.description),
                lstAssignedArrangements: lstAssignedArrangements,
                status: act.status,
                comments: comments
            };

            $scope.reconstructedLstActMonPlan.push(actRec);

        }

    }


    $scope.splitAssignedArrangements = function(actMonPlanId){
        var bFound = false;
        var lstAssignedArrangementsOk = [];
        var lstAssignedArrangementsFailed = [];

        for(var i=$scope.lstActMonPlanArrangement.length-1; i>=0; i--){
            var mpArr = $scope.lstActMonPlanArrangement[i];
            if(mpArr.actMonPlanId === actMonPlanId){
                bFound = true;
                var assArr = $scope.idToObject(mpArr.assignedArrangementId, $scope.lstHfAssignedArrangement);

                switch(mpArr.status){
                    case 1:
                        lstAssignedArrangementsOk.push({
                            name: assArr.name,
                            status: $scope.setStatus(mpArr.status)
                        });
                        break;
                    case 0:
                        lstAssignedArrangementsFailed.push({
                            name: assArr.name,
                            status: $scope.setStatus(mpArr.status)
                        });
                        break;
                    default:
                        break;
                }

                $scope.lstActMonPlanArrangement.splice(i,1);
            }

            if(bFound === true && mpArr.actMonPlanId !== actMonPlanId)
                break;
        }

        return {lstOk: lstAssignedArrangementsOk, hasOk: (lstAssignedArrangementsOk.length > 0),
            lstFailed: lstAssignedArrangementsFailed, hasFailed: (lstAssignedArrangementsFailed.length > 0)};
    }

    $scope.constructActMonPlanAccomplishment = function(){
        $scope.reconstructedLstActMonPlanOk = [];
        $scope.reconstructedLstActMonPlanFailed = [];

        for(var i=0; i<$scope.lstActMonPlan.length; i++){
            var act = $scope.lstActMonPlan[i];

            var actSup = $scope.idToObject(act.actSupervisionId, $scope.lstActivities);
            var aidSource = $scope.idToObject(act.aidSourceId, $scope.lstSources);

            //id
            var splitInfo = $scope.splitAssignedArrangements(act.id);
            var comments = (act.comments === undefined || act.comments === null) ? "NA" : act.comments;

            if(splitInfo.hasOk){
                var actRec = {
                    start: act.start,
                    end: act.end,
                    supActivity: (actSup === undefined ? "NA" : actSup.name),
                    aidSource: (aidSource === undefined ? "NA" : aidSource.name + " / " + aidSource.description),
                    lstAssignedArrangements: splitInfo.lstOk,
                    status: act.status,
                    comments: comments
                };

                $scope.reconstructedLstActMonPlanOk.push(actRec);
            }

            if(splitInfo.hasFailed){
                var actRec = {
                    start: act.start,
                    end: act.end,
                    supActivity: (actSup === undefined ? "NA" : actSup.name),
                    aidSource: (aidSource === undefined ? "NA" : aidSource.name + " / " + aidSource.description),
                    lstAssignedArrangements: splitInfo.lstFailed,
                    status: act.status,
                    comments: comments
                };

                $scope.reconstructedLstActMonPlanFailed.push(actRec);
            }

        }
    }

});