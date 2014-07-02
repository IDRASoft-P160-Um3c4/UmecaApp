app.directive('verifAddress', function ($http, $timeout) {
    return function (scope, elem, attr) {

        var currentTimeout = null;

        elem.on('click', function () {
            var levelChild = scope.$eval(attr.levelChild);
            var idElement = attr.idElement;
            var divModValid ="#dlgUpModalIdAddress";
            var scopeNew = angular.element(divModValid).scope();
            scopeNew.Model.def = scope.def;
            scopeNew.Model.dlg=$(divModValid);
            scopeNew.Model.dlg.modal('show');
            scopeNew.init='=';
            scopeNew.verification = false;
            scopeNew.enableProperties();
            $.validator.unobtrusive.parse("#FormCatIdAddress");
            $(divModValid).injector().invoke(function ($compile, $rootScope) {
                $compile($(divModValid))($rootScope);
                $rootScope.$apply();
            });
            scopeNew.setDlg(scopeNew.Model.dlg);
            ////eliminar valores y eliminar readonly

        });
    };
});