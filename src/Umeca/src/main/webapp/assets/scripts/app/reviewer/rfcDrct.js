app.directive('rfcCalculated', ['$document', function($document) {
    return function(scope, element, attr) {
     /*   element.on('blur', function(event) {
            var rfc= "";
            if(scope.m.name!=undefined)
                rfc=scope.m.name;
            if(scope.m.lastNameP !=undefined)
                rfc=rfc+scope.m.lastNameP;
            if(scope.m.lastNameM !=undefined)
                rfc=rfc+scope.m.lastNameM;
            if(scope.m.dateBirth !=undefined)
                rfc = rfc+scope.m.dateBirth;
            scope.m.rfc=rfc;
            return scope;
        });
        element.on('mouseup', function(event) {
            var rfc= "";
            if(scope.m.name!=undefined)
                rfc=scope.m.name;
            if(scope.m.lastNameP !=undefined)
                rfc=rfc+scope.m.lastNameP;
            if(scope.m.lastNameM !=undefined)
                rfc=rfc+scope.m.lastNameM;
            if(scope.m.dateBirth !=undefined)
            {
                var dateBirth=scope.m.dateBirth;
                dateBirth =dateBirth.replace(/-/gi,"");
                rfc = rfc+(dateBirth);
            }
            scope.m.rfc=rfc.substr(0,10);
            return scope;
        });*/
    };
}]);