//Data
var cities = [
    {
        city: 'Cuernavaca, Morelos',
        desc: 'Adolfo López Mateos 203A',
        lat: 18.9245121,
        long: -99.2326088
    }
];

var currentTimeout;

app.controller('addressMapOnlyController', function ($scope, $timeout, $http, $rootScope,$sce) {
    $scope.lat = [];
    $scope.long = [];

    $scope.map = [];
    $scope.markers = [];

    $scope.init = function () {
        var listaDeMaps = $(".maps");
        var counter = 0;
        for(counter =0;counter < listaDeMaps.length;counter++){
            var elemt = listaDeMaps[counter];
            var desompouseId = elemt.id.split("-");
            var index = desompouseId[1];
            $scope.lat[index] = elemt.attributes.lat.nodeValue;
            $scope.long[index] = elemt.attributes.lng.nodeValue;
            $scope.point = new google.maps.LatLng($scope.lat[index],$scope.long[index]);
            $scope.map_options = {
                zoom: 14,
                center: $scope.point,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            $scope.map[index] = new google.maps.Map(listaDeMaps[counter],$scope.map_options);
            google.maps.event.trigger($scope.map, 'resize');
            $scope.map[index].setCenter($scope.point);

            google.maps.event.addListener( $scope.map[index], 'click', function(event) {
                $scope.refreshMap();
                console.log("click map");
            });

            window.setTimeout(function(){
                $scope.refreshMap();
            },1000);
        }

        //console.log("end init map");
    };


    $scope.refreshMap = function () {
                try {
                    if ($scope.markers.length > 0) {
                        for (var i = 0; i < $scope.markers.length; i++) {
                            $scope.markers[i].setMap(null);
                        }
                    }
                    for(var count=0 ;count < $scope.map.length;count++) {

                        console.log("index" + count);
                        //var lat = 23.634501;
                        //var lng = -102.552784;
                        var point = new google.maps.LatLng($scope.lat[count], $scope.long[count]);

                        var marker = new google.maps.Marker({
                            position: point,
                            map: $scope.map[count]
                        });
                        $scope.markers.push(marker);

                        $scope.map[count].setCenter(point);
                        $scope.map[count].setZoom(14);
                        google.maps.event.trigger($scope.map[count], 'resize');
                    }
                    //$scope.addMarker($scope.point);
                } catch (e) {
                      console.log("e->"+ e.message);
                }
    };

    $timeout(function () {
        $scope.init();
    }, 0);

});