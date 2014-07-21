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
                        if (data.data == undefined || data.data.length === 0) {
                           // scope.clear();
                            return;
                        }
                        scope.listLocation = data.data;
                        scope.location =scope.listLocation[0];
                        scope.locationId = scope.location.id;
                        scope.zipCode = scope.location.zipCode;
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
            $scope.center = new google.maps.LatLng(18.9245121,-99.2326088);

            $scope.map_options = {
                zoom: 14,
                center: new google.maps.LatLng(18.9245121,-99.2326088),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };

            // create map
            $scope.map = new google.maps.Map(document.getElementById(attrs.id),$scope.map_options);

            // configure marker
            $scope.marker_options = {
                map: $scope.map,
                position: $scope.center
            };

            // create marker
            $scope.marker = new google.maps.Marker($scope.marker_options);

            $scope.$watch('selected', function () {

                window.setTimeout(function(){

                    google.maps.event.trigger($scope.map, 'resize');
                    $scope.map.setCenter($scope.center);
                },100);

            });
        }
    }
});