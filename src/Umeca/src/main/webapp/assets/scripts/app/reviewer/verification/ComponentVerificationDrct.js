app.directive('verifComp', function ($http, $timeout) {
    return function (scope, elem, attr) {

        var currentTimeout = null;

        elem.on('click', function () {
            //$("#hdnNameControllerChild").val("personalDataController");
            var levelChild = scope.$eval(attr.levelChild);
            //parents,sister,child -> obtiene el modelo a
            var idElement = attr.idElement;
            var finalElem=elem;
            for(var i=0; i<levelChild; i++){
              finalElem = finalElem.parent();
            }
            var codeVerif = finalElem.html();
            var divModValid ="#dlgUpModalId";
            var scopeNew = angular.element(divModValid).scope();
            var scopeElement = finalElem.scope();
            scopeNew.Model.def = scope.def;
            codeVerif +=' <input type="hidden" id="hdnNameControllerChild" ng-model="childScopeName" value="personalDataController" ng-update-hidden>';
            $("#divElementVerif").empty().append(codeVerif);
            //$("#divElementVerif").empty().append();
            scopeNew.Model.dlg=$(divModValid);
            scopeNew.Model.dlg.modal('show');
            scopeNew.verification = false;
            scopeNew.enableProperties();
            $.validator.unobtrusive.parse("#FormCatId");

            $(divModValid).injector().invoke(function ($compile, $rootScope) {
                $compile($(divModValid))($rootScope);
                $rootScope.$apply();
            });
            scopeNew.setDlg(scopeNew.Model.dlg);
            ////eliminar valores y eliminar readonly

        });
    };
});