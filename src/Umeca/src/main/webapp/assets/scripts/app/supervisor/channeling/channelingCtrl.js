app.controller('channelingController', function($scope) {
    $scope.m = {};

    $scope.initCatalogs = function(){
        $scope.district = window.initCatalog($scope.lstDistrict, $scope.m.districtId);
        $scope.m.districtId = $scope.district.id;
        /*
        $scope.educationLevel = window.initCatalog($scope.lstEducationLevel, $scope.m.educationLevelId);
        $scope.m.educationLevelId = $scope.educationLevel.id;

        $scope.preventionType = window.initCatalog($scope.lstPreventionType, $scope.m.preventionTypeId);
        $scope.m.preventionTypeId = $scope.preventionType.id;

        $scope.economicSupport = window.initCatalog($scope.lstEconomicSupport, $scope.m.economicSupportId);
        $scope.m.economicSupportId = $scope.economicSupport.id;

        $scope.institutionType = window.initCatalog($scope.lstInstitutionType, $scope.m.institutionTypeId);
        $scope.m.institutionTypeId = $scope.institutionType.id;
        */
        $scope.channelingType = window.initCatalog($scope.lstChannelingType, $scope.m.channelingTypeId);
        $scope.m.channelingTypeId = $scope.channelingType.id;

        $scope.onChangeChannelingType();

        //lstDistrict = ${lstDistrict}; lstEducationLevel = ${lstEducationLevel}; lstPreventionType = ${lstPreventionType};
        //lstEconomicSupport = ${lstEconomicSupport}; lstInstitutionType = ${lstInstitutionType}; lstChannelingType = ${lstChannelingType};
    };

    $scope.onChangeChannelingType = function () {
        $scope.clearAllCatalog();
        switch($scope.channelingType.optionB){
            case "AE":
                $scope.economicSupport = $scope.selectCatalog($scope.lstEconomicSupport, $scope.lstEconomicSupportNew, $scope.m.economicSupportId, $scope.m, 'economicSupportId');
                $scope.institutionType = $scope.selectCatalog($scope.lstInstitutionType, $scope.lstInstitutionTypeNew, $scope.m.institutionTypeId, $scope.m, 'institutionTypeId', $scope.channelingType.optionA);
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