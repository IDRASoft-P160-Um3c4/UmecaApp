app.directive('verifComp', function ($http, $timeout) {
    return function (scope, elem, attr) {

        var currentTimeout = null;

        elem.on(' click', function () {
            var levelChild = scope.$eval(attr.levelChild);
            var finalElem=elem;
            for(var i=0; i<levelChild; i++){
              finalElem = finalElem.parent();
            }
            var codeVerif = finalElem.html();
            var divModValid ="#dlgUpModalId";
            var scopeNew = angular.element(divModValid).scope();
            scopeNew.Model.def = scope.def;
            $("#divElementVerif").empty().append(codeVerif);
            ///////////////////////////////////
            scopeNew.Model.dlg=$(divModValid);
            scopeNew.Model.dlg.modal('show');
            $.validator.unobtrusive.parse("#FormCatId");
            $(divModValid).injector().invoke(function ($compile, $rootScope) {
                $compile($(divModValid))($rootScope);
                $rootScope.$apply();
            });
            scopeNew.setDlg(scopeNew.Model.dlg);
            //window.showModalFormDlg(divModValid, "#FormCatId");
            //scopeNew.$apply();
        });
    };
});