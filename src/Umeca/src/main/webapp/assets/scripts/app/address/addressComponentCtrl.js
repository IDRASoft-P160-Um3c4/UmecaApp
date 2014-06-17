app.controller('addressComponentController', function ($scope, $timeout, $http) {
    $scope.listLocation = [];
    $scope.listState = [];
    $scope.listMunicipality = [];
    $scope.model = {};


    $scope.clear = function () {
        $scope.msgZipCode = "El código postal " + $scope.zipCode + " no existe.";
        $scope.zipCode = "";
    }
    $scope.init = function () {
        if ($scope.model.zipCode != undefined) {
            $scope.id = $scope.model.id;
            $scope.zipCode = $scope.model.zipCode;
            $scope.street = $scope.model.street;
            $scope.outNum = $scope.model.outNum;
            $scope.innNum = $scope.model.innNum;
        }
        if ($scope.zipCode != "" && $scope.zipCode != undefined) {
            var ajaxConf = {
                method: 'POST',
                url: $scope.urlZipCode
            };
            ajaxConf.params = {zipCode: $scope.zipCode};
            $http(ajaxConf)
                .success(function (data) {
                    data.data=jQuery.parseJSON(data.data);
                    if (data.data == undefined || data.data.length === 0) {
                        $scope.clear();
                        return;
                    }
                    $scope.msgZipCode="";
                    var firstLocation = data.data[0];
                    for(var i=0; i<$scope.listState.length; i++){
                        if(firstLocation.stateId == $scope.listState[i].id){
                            $scope.state = $scope.listState[i];
                            $scope.stateId = $scope.state.id;
                        }
                    }
                    var ajaxConf = {
                        method: 'POST',
                        url: $scope.urlMunicipality
                    };
                    ajaxConf.params = {idState : $scope.stateId};
                    $http(ajaxConf)
                        .success(function (data) {
                            data.data=jQuery.parseJSON(data.data);
                            if (data.data == undefined || data.data.length === 0) {
                                //  cat={};
                                return;
                            }
                            $scope.listMunicipality = data.data;
                            for(var i=0; i<$scope.listMunicipality.length; i++){
                                if(firstLocation.munId == $scope.listMunicipality[i].id){
                                    $scope.municipality = $scope.listMunicipality[i];
                                    $scope.municipalityId = $scope.municipality.id;
                                }
                            }
                            ajaxConf = {
                                method: 'POST',
                                url: $scope.urlLocation
                            };
                            ajaxConf.params = {idMun : $scope.municipalityId};
                            $http(ajaxConf)
                                .success(function (data) {
                                    data.data=jQuery.parseJSON(data.data);
                                    if (data.data == undefined || data.data.length === 0) {
                                        // $scope.clear();
                                        return;
                                    }
                                    $scope.listLocation = data.data;
                                    for(var i =0 ; i<$scope.listLocation.length;i++){
                                        if(firstLocation.id == $scope.listLocation[i].id){
                                            $scope.location =$scope.listLocation[i];
                                            $scope.locationId = $scope.location.id;
                                            $scope.zipCode = $scope.location.zipCode;
                                        }
                                    }
                                });
                        });
                    $scope.listLocation = data.data;
                    $scope.location =$scope.listLocation[0];
                    $scope.locationId = $scope.location.id;
                });
        } else {
            if ($scope.listState != undefined && $scope.listState.length > 0) {
                $scope.state = $scope.listState[0];
                $scope.stateId = $scope.state.id;
                var ajaxConf = {
                    method: 'POST',
                    url: $scope.urlMunicipality
                };
                ajaxConf.params = {idState: $scope.stateId};
                $http(ajaxConf)
                    .success(function (data) {
                        data.data = jQuery.parseJSON(data.data);
                        if (data.data == undefined || data.data.length === 0) {
                            //  cat={};
                            return;
                        }
                        $scope.listMunicipality = data.data;
                        $scope.municipality = $scope.listMunicipality[0];
                        $scope.municipalityId = $scope.municipality.id;
                        ajaxConf = {
                            method: 'POST',
                            url: $scope.urlLocation
                        };
                        ajaxConf.params = {idMun: $scope.municipalityId};
                        $http(ajaxConf)
                            .success(function (data) {
                                data.data = jQuery.parseJSON(data.data);
                                if (data.data == undefined || data.data.length === 0) {
                                    return;
                                }
                                $scope.listLocation = data.data;
                                $scope.location = $scope.listLocation[0];
                                $scope.locationId = $scope.location.id;
                                $scope.zipCode = $scope.location.zipCode;
                            });

                    });

            }
        }
    };


    $timeout(function () {
        $scope.init();
    }, 0);

});