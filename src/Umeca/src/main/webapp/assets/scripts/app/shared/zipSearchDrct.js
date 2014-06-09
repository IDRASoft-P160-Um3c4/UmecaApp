app.directive('zipSearch', function ($http, $timeout) {
    return function (scope, elem) {

        var currentTimeout = null;

        var ajaxConf = {
            method: 'POST',
            url: '/catalogs/locationsByZipCode.json'
        };

        elem.on('keyup change blur', function () {
            if (scope.zipCode == undefined || scope.zipCode.length < 4 || scope.zipCode.length > 5)
                return;

            ajaxConf.params = {zipCode : scope.zipCode};

            if (currentTimeout) {
                $timeout.cancel(currentTimeout);
            }

            currentTimeout = $timeout(function() {
                $http(ajaxConf)
                    .success(function (data) {
                        data.locations=jQuery.parseJSON(data.locations);
                        if (data.locations == undefined || data.locations.length === 0) {
                            scope.clear();
                            return;
                        }

                        scope.listLocation = data.locations;
                        scope.a.location =data.locations[0];
                        //scope.selectedLocation = data.Locations[0];

                    });
            }, 200);
        });
    };
});