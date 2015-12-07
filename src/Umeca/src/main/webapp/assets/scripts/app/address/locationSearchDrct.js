app.directive('findLocation', function ($http, $timeout) {
    return function (scope, elem, attr) {

        var currentTimeout = null;

        var urlRequest = attr['urlRequest'];

        var ajaxConf = {
            method: 'POST',
            url: urlRequest
        };

        elem.on('change blur', function () {
            if (scope.municipalityId == undefined )
                return;

            ajaxConf.params = {idMun : scope.municipalityId};

            if (currentTimeout) {
                $timeout.cancel(currentTimeout);
            }

            currentTimeout = $timeout(function() {
                $http(ajaxConf)
                    .success(function (data) {
                        data.data=jQuery.parseJSON(data.data);
                        if (data.data == undefined) {
                           // scope.clear();
                            return;
                        }
                        scope.listLocation = data.data;
                        scope.location =scope.listLocation[0];
                        scope.locationId = scope.location.id;
                        scope.zipCode = scope.location.zipCode;
                        if(scope.refreshMap!= undefined){
                            scope.refreshMap();
                        }


                    });
            }, 200);
        });
    };
});

app.directive('map', function () {
    return {
        restrict: 'E',
        replace: true,
        template: '<div></div>',
        link: function($scope, element, attrs) {
            if($scope.lat == undefined){
                $scope.lat=18.9245121;
                $scope.lng =-99.2326088;
            }
            $scope.point = new google.maps.LatLng($scope.lat,$scope.lng);

            $scope.map_options = {
                zoom: 14,
                center: $scope.point,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            $scope.map = new google.maps.Map(document.getElementById(attrs.id),$scope.map_options);
            google.maps.event.trigger($scope.map, 'resize');
            $scope.addMarker($scope.point);
            google.maps.event.addListener($scope.map, 'click', function(event) {
                console.log("punto en mapa");
                var lat;
                var lng;
                if(event.latLng.k==undefined||event.latLng.B==undefined){
                    lat = event.latLng.lat();
                    lng = event.latLng.lng();
                }else{
                    lat = event.latLng.k;
                    lng = event.latLng.B;
                }
               $scope.addMarker(new google.maps.LatLng(lat,lng),true);
            });
            $scope.$watch('selected', function () {
            window.setTimeout(function(){
                    google.maps.event.trigger($scope.map, 'resize');
                    $scope.map.setCenter($scope.point);
                },100);

            });
        }
    }
});
