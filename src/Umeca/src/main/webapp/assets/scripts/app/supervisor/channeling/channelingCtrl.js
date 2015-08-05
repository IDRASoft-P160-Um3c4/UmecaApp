app.controller('channelingController', function($scope) {
    $scope.m = {};

    $scope.initCatalogs = function(){
        $scope.district = window.initCatalog($scope.lstDistrict, $scope.m.districtId);
        $scope.m.districtId = $scope.district.id;

        $scope.channelingType = window.initCatalog($scope.lstChannelingType, $scope.m.channelingTypeId);
        $scope.m.channelingTypeId = $scope.channelingType.id;

        $scope.onChangeChannelingType();

    };

    $scope.initDropCatalogs = function(){
        $scope.m.channelingDropType = $scope.lstChannelingDropType[0];
        $scope.m.channelingDropTypeId = $scope.m.channelingDropType.id;
    };

    $scope.onChangeChannelingType = function () {
        $scope.clearAllCatalog();

        $scope.institutionType = $scope.selectCatalog($scope.lstInstitutionType, $scope.lstInstitutionTypeNew, $scope.m.institutionTypeId, $scope.m, 'institutionTypeId', $scope.channelingType.optionA);

        switch($scope.channelingType.optionB){
            case "AE":
                $scope.economicSupport = $scope.selectCatalog($scope.lstEconomicSupport, $scope.lstEconomicSupportNew, $scope.m.economicSupportId, $scope.m, 'economicSupportId');
                break;
            case "ET":
            case "EM":
            case "TMP":
                break;
            case "PTA":
                $scope.preventionType = $scope.selectCatalog($scope.lstPreventionType, $scope.lstPreventionTypeNew, $scope.m.preventionTypeId, $scope.m, 'preventionTypeId');
                break;
            case "ES":
                $scope.educationLevel = $scope.selectCatalog($scope.lstEducationLevel, $scope.lstEducationLevelNew, $scope.m.educationLevelId, $scope.m, 'educationLevelId');
                break;
        }
    };

    $scope.selectCatalog = function(lstCatalog, lstCatalogNew, catalogId, jsonData, catalogKey, slstCatalogIds){
        var catalog;

        if(slstCatalogIds !== undefined){
            var lstCatalogIds = slstCatalogIds.split(",");
            for(var j=0, len = lstCatalog.length; j<len; j++){
                var item = lstCatalog[j];
                for(var k=0, lenK = lstCatalogIds.length; k<lenK; k++){
                    if(item.id == lstCatalogIds[k]){
                        lstCatalogNew.push(item);
                        break;
                    }
                }
            }
        }
        else{
            for(j=0, len = lstCatalog.length; j<len; j++) {
                lstCatalogNew.push(lstCatalog[j]);
            }
        }

        if(catalogId === undefined){
            catalog = lstCatalogNew[0];
        }
        else{

            for(var i=lstCatalogNew.length-1; i>=0; i--){
                catalog = lstCatalogNew[i];
                if(catalog.id === catalogId)
                    break;
            }
        }

        if(jsonData !== undefined && catalogKey !== undefined)
            jsonData[catalogKey] = catalog.id;

        return catalog;
    };

    $scope.clearAllCatalog = function(lstCatalog){
        $scope.lstEducationLevelNew = [];
        $scope.lstPreventionTypeNew = [];
        $scope.lstEconomicSupportNew = [];
        $scope.lstInstitutionTypeNew = [];
        $scope.lstInstitutionTypeNew = [];
    };

});