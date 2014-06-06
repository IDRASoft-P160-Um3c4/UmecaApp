app.controller('hearingFormatController', function ($scope, $timeout, $http) {

    $scope.m = {};
    $scope.hasError;
    $scope.m.arrmntType;
    $scope.m.canSave = true;

    $scope.validateSave = function () {

        $scope.hasError = false;

        $scope.validateInitEnd();
        $scope.validateBthDay();

        $scope.validateHType();
        $scope.validateExtension();
        $scope.validateCrtlDet();
        $scope.validateFormImp();
        $scope.validateLinkProc();

        if ($scope.hasError == true)
            return false;

        return true;
    };

    $scope.validateBthDay = function () {

        if (!$scope.m.impBthDay) {
            $scope.hasError = true;
            $scope.m.errBth = "Fecha de nacimiento es un campo requerido";
        }
        else {
            $scope.m.errBth = "";
            if ($scope.m.impAge < 18) {
                $scope.hasError = true;
                $scope.m.errAge = "No puede registrar a un menor de edad";
            }
            else
                $scope.m.errAge = "";
        }


    }

    $scope.validateInitEnd = function () {

        //alert($scope.m.endTime)

        if ($scope.m.initTime == "" || $scope.m.initTime == null) {
            $scope.hasError = true;
            $scope.m.errTimeIn = "Hora de inicio es un campo requerido";
        }
        else
            $scope.m.errTimeIn = "";

        if ($scope.m.endTime == "" || $scope.m.endTime == null) {
            $scope.hasError = true;
            $scope.m.errTime = "Hora de termino es un campo requerido";
        }
        else
            $scope.m.errTime = "";


        if (Date.parse('01/01/2014 ' + $scope.m.initTime) >= Date.parse('01/01/2014 ' + $scope.m.endTime)) {
            $scope.hasError = true;
            $scope.m.errTime = "La hora de termino no puede ser menor/igual a la hora de inicio";
        }
        else
            $scope.m.errTime = "";

    };

    $scope.validateCrtlDet = function () {

        if (!$scope.m.ctrlDet) {
            $scope.hasError = true;
            $scope.m.errCtrlDet = "Debe seleccionar una opción";
        } else {
            $scope.clCrtlDet();
        }

    };

    $scope.clCrtlDet = function () {
        $scope.m.errCtrlDet = "";
    };


    $scope.validateExtension = function () {

        if (!$scope.m.ext) {
            $scope.hasError = true;
            $scope.m.errExt = "Debe seleccionar una opción";
        } else {
            $scope.clExtension();
        }

    };

    $scope.clExtension = function () {
        $scope.m.errExt = "";
    };

    $scope.validateHType = function () {
        if (!$scope.m.hType) {
            $scope.hasError = true;
            $scope.m.errHtype = "Debe seleccionar una opción";
        } else {
            $scope.clHType();
        }
    };

    $scope.clHType = function () {
        $scope.m.errHtype = "";
    };


    $scope.validateFormImp = function () {

        if (!$scope.m.formImp) {
            $scope.hasError = true;
            $scope.m.errFormImp = "Debe seleccionar una opción";
        }
        else
            $scope.m.errFormImp = "";

        if ($scope.m.formImp == 1 && (!$scope.m.impDate || $scope.m.impDate == "")) {
            $scope.hasError = true;
            $scope.m.errFormImpDate = "Fecha es un campo requerido";
        }
        else {
            $scope.m.errFormImpDate = "";
        }
    };

    $scope.clFormImp = function () {
        $scope.m.errFormImp = "";
        $scope.m.impDate = "";
        $scope.m.errFormImpDate = "";
    };

    $scope.validateLinkProc = function () {

        if (!$scope.m.vincProcess) {
            $scope.hasError = true;
            $scope.m.errLinkProc = "Debe seleccionar una opción";
        }
        else
            $scope.m.errLinkProc = "";

        if ($scope.m.vincProcess == 1) {

            if (!$scope.m.linkageRoom || $scope.m.linkageRoom == "") {
                $scope.hasError = true;
                $scope.m.errLnkRoom = "Sala es un campo requerido";
            }
            else {
                $scope.m.errLnkRoom = "";
            }

            if (!$scope.m.linkageDate || $scope.m.linkageDate == "") {
                $scope.hasError = true;
                $scope.m.errLnkDt = "Fecha es un campo requerido";
            }
            else {
                $scope.m.errLnkDt = "";
            }

            if (!$scope.m.linkageTime || $scope.m.linkageTime == "") {
                $scope.hasError = true;
                $scope.m.errLnkTm = "Hora es un campo requerido";
            }
            else {
                $scope.m.errLnkTm = "";
            }

        }
    };

    $scope.clLinkProc = function () {
        $scope.m.errLinkProc = "";
        $scope.m.linkageRoom = "";
        $scope.m.errLnkRoom = "";
        $scope.m.linkageDate = "";
        $scope.m.errLnkDt = "";
        $scope.m.linkageTime = "";
        $scope.m.errLnkTm = "";
    };

    $scope.searchCase = function (idFolder) {
        //debugger;
        var currentTimeout = null;

        var ajaxConf = {
            method: 'POST',
            url: '/supervisor/searchCase.json'
        };

        ajaxConf.data = { 'idFolder': idFolder };

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (data) {

                    $scope.fillFormat(data);
                });
        }, 200);
    };

    $scope.fillFormat = function (data) {

        $scope.m.arrmntType = data.arrangementType;
        $scope.m.numberDate = data.numberDate;
        $scope.m.room = data.room;
        $scope.m.initTime = data.initTime;
        $scope.m.endTime = data.endTime;
        $scope.m.judgeName = data.judgeName;
        $scope.m.mpName = data.mpName;
        $scope.m.defenderName = data.defenderName;
        $scope.m.imputedName = data.imputedName;
        $scope.m.imputedFLastName = data.imputedFLastName;
        $scope.m.imputedSLastName = data.imputedSLastName;
        $scope.m.impBthDay = data.imputedBirthDate;
        $scope.calcAge();
        $scope.m.imputedTel = data.imputedTel;
        $scope.m.crimes = data.crimes;
        $scope.m.addtionalData = data.additionalData;
        $scope.m.ctrlDet = data.controlDetention;
        $scope.m.ext = data.extension;
        $scope.m.hType = data.hearingType;
        $scope.m.impDate = data.imputationDate;
        $scope.m.linkageRoom = data.linkageRoom;
        $scope.m.linkageDate = data.linkageDate;
        $scope.m.linkageTime = data.linkageTime;
        $scope.m.canSave = data.canSave;


        //  private String terms;
        // public String idFolderCode;

    };

    $scope.searchArrangements = function (arrangementType) {

        var currentTimeout = null;

        var ajaxConf = {
            method: 'POST',
            url: '/supervisor/searchCase.json'
        };

        ajaxConf.data = { 'idFolder': idFolder };

        if (currentTimeout) {
            $timeout.cancel(currentTimeout);
        }

        currentTimeout = $timeout(function () {
            $http(ajaxConf)
                .success(function (data) {


                });
        }, 200);
    };

    $scope.calcAge = function () {

        //mm,dd,yy segun el formato del date picker
        var arrDate = $scope.m.impBthDay.split('/');
        var dtBthObj = new Date(parseInt(arrDate[2], 10), parseInt(arrDate[0], 10), parseInt(arrDate[1], 10));
        var ageDifMs = Date.now() - dtBthObj.getTime();
        var ageDate = new Date(ageDifMs);
        $scope.m.impAge = Math.abs(ageDate.getUTCFullYear() - 1970);

        if ($scope.m.impAge < 18) {
            $scope.hasError = true;
            $scope.m.errAge = "No puede registrar a un menor de edad";
        }
        else
            $scope.m.errAge = "";

        $scope.m.errBth = "";
    };

    $scope.init = function () {
        $scope.m.errTime = "";
    };


    $timeout(function () {
        $scope.init();
    }, 0);

})
;