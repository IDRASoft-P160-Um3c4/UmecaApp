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