app.controller('hearingFormatController', function ($scope, $timeout, $http) {

        $scope.m = {};
        $scope.a = {};

        $scope.m.errTime;
        $scope.hasError;
        $scope.MsgError;
        $scope.MsgErrorContact = "";

        $scope.validateSave = function () {

            $scope.hasError = false;

            $scope.validateInitEnd();
            $scope.validateBthDay();
            $scope.validateArrangementSel();

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

            if (Date.parse('01/01/2014 ' + $scope.m.initTime) >= Date.parse('01/01/2014 ' + $scope.m.endTime)) {
                $scope.hasError = true;
                $scope.m.errTime = "La hora de término debe ser mayor a la hora de inicio";
                return;
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

        $scope.validateExtension = function () {

            if (!$scope.m.ext) {
                $scope.hasError = true;
                $scope.m.errExt = "Debe seleccionar una opción";
            } else {
                $scope.clExtension();
            }

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

        $scope.addContact = function () {

            if ($scope.validateContact())
                return;

            var jsonRow = {"name": $scope.m.contactName, "phone": $scope.m.contactPhone, "address": $scope.m.contactAddress};

            $scope.m.lstContactData.push(jsonRow);

            $scope.m.contactName = "";
            $scope.m.contactPhone = "";
            $scope.m.contactAddress = "";

        };

        $scope.validateContact = function () {

            $scope.MsgErrorContact = "";

            if ($scope.m.contactName == "" || $scope.m.contactPhone == "" || $scope.m.contactAddress == "" || !($scope.m.contactName) || !($scope.m.contactPhone) || !($scope.m.contactAddress)) {
                $scope.MsgErrorContact = "Debe proporcionar todos los campos para agregar el contacto."
                return true;
            }

            return false
        };

        $scope.removeContact = function (index) {
            if ($scope.m.canSave == true)
                $scope.m.lstContactData.splice(index, 1);
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

        $scope.disableView = function (val) {

            if (val)
                $("#FormFormatId :input").attr("disabled", true);
            else
                $("#FormFormatId :input").attr("disabled", false);

        }

        $scope.myFormatDate = function (dateMil) {

            var strDt = "";
            var date;

            if (dateMil && dateMil != "null") {

                date = new Date(dateMil);

                var dd, mm, yyyy;

                dd = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
                mm = date.getMonth() < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
                yyyy = date.getFullYear();

                strDt = dd + "/" + mm + "/" + yyyy;
            }

            return strDt;
        };

        $scope.fillFormat = function (data) {

            $scope.m.canSave = data.canSave;
            $scope.m.canEdit = data.canEdit;
            $scope.m.disableAll = data.disableAll;

            //audiencia
            $scope.m.idCase = data.idCase;
            $scope.m.idFolder = data.idFolder;
            $scope.m.idJudicial = data.idJudicial;
            $scope.m.room = data.room;
            $scope.m.appointmentDate = $scope.myFormatDate(data.appointmentDate);
            $scope.m.initTime = data.initTime;
            $scope.m.endTime = data.endTime;
            $scope.m.judgeName = data.judgeName;
            $scope.m.mpName = data.mpName;
            $scope.m.defenderName = data.defenderName;

            //imputado
            $scope.m.imputedName = data.imputedName;
            $scope.m.imputedFLastName = data.imputedFLastName;
            $scope.m.imputedSLastName = data.imputedSLastName;
            $scope.m.impBthDay = $scope.myFormatDate(data.imputedBirthDate);
            $scope.calcAge();
            $scope.m.imputedTel = data.imputedTel;
            //todo falta domicilio

            $scope.m.crimes = data.crimes;
            $scope.m.additionalData = data.additionalData;

            //radios
            $scope.m.ctrlDet = data.controlDetention;

            $scope.m.ext = data.extension;

            $scope.m.formImp = data.impForm;
            $scope.m.impDate = $scope.myFormatDate(data.imputationDate);

            $scope.m.vincProcess = data.vincProcess;
            $scope.m.linkageRoom = data.linkageRoom;
            $scope.m.linkageDate = $scope.myFormatDate(data.linkageDate);
            $scope.m.linkageTime = data.linkageTime;
            $scope.m.arrType = data.arrangementType;
            $scope.m.nationalArrangement = data.nationalArrangement;
            $scope.m.terms = data.terms;

            $scope.m.userName = data.userName;
            //

            if (data.lstArrangement != undefined)
                $scope.m.lstArrangementShow = $.parseJSON(data.lstArrangement);

            if (data.lstContactData != undefined)
                $scope.m.lstContactData = $.parseJSON(data.lstContactData);
            else
                $scope.m.lstContactData = [];

        };

        $scope.submitReloadHF = function (formId, urlToPost, hasReturnId, validate) {

            var stVal = true;

            if (validate != undefined)
                stVal = validate();

            if ($(formId).valid() == false || stVal == false) {
                $scope.Invalid = true;
                return false;
            }

            $scope.WaitFor = true;

            $.post(urlToPost, $(formId).serialize())
                .success(function (resp) {
                    if (resp.hasError === undefined) {
                        resp = resp.responseMessage;
                    }

                    if (resp.hasError == false) {
                        window.goToUrlMvcUrl(resp.urlToGo);
                    }

                    if (resp.hasError == true) {
                        $scope.MsgError = resp.messgae;
                    }
                })
                .error(function () {
                    $scope.WaitFor = false;
                    $scope.MsgError = "Error de red. Por favor intente más tarde.";
                    $scope.$apply();
                });

            return true;
        };

        $scope.loadArrangements = function () {

            if ((!$scope.m.arrType) || $scope.m.arrType == "" || $scope.m.disableAll == true) {
                return;
            }

            var currentTimeout = null;
            var urlType = $('#url3').attr("value");

            var ajaxConf;

            ajaxConf = {
                method: "POST",
                params : {national: $scope.m.nationalArrangement, idTipo:$scope.m.arrType},
                dataType: "json",
                contentType: "application/json"
            };

            ajaxConf.url = urlType;

            if (currentTimeout) {
                $timeout.cancel(currentTimeout);
            }

            currentTimeout = $timeout(function () {
                $http(ajaxConf)
                    .success(function (data) {
                        $scope.m.lstArrangementShow = data;
                    });
            }, 200);
        };

        $scope.calcAge = function () {

            //dd/mm/yyyy o en milisegundos
            if ($scope.m.impBthDay != null && $scope.m.impBthDay != "") {

                var arrBth = [];
                arrBth = $scope.m.impBthDay.split('/');

                var dtBthObj;

                if (arrBth.length > 0)
                    dtBthObj = new Date(arrBth[2], arrBth[1], arrBth[0]);
                else
                    dtBthObj = new Date($scope.m.impBthDay);

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
            }
        };

        $scope.validateArrangementSel = function () {

            var noSel = 0;
            var noDesc = 0;

            if (!$scope.m.lstArrangementShow || $scope.m.disableAll == true)
                return;

            for (var i = 0; i < $scope.m.lstArrangementShow.length; i++) {

                if ($scope.m.lstArrangementShow[i].selVal == true) {
                    noSel++;
                    if ($scope.m.lstArrangementShow[i].description != "" && $scope.m.lstArrangementShow[i].description != undefined) {
                        noDesc++;
                    }
                } else {
                    $scope.m.lstArrangementShow[i].description = "";
                }

            }

            if (noSel < 1) {
                $scope.m.hasError = true;
                $scope.m.errArrmntSel = "Debe seleccionar al menos una medida cautelar";
                return;
            } else if (noSel > noDesc) {
                $scope.m.hasError = true;
                $scope.m.errArrmntSel = "Debe indicar una descripción para cada medida cautelar seleccionada";
                return;
            } else
                $scope.m.errArrmntSel = "";


        };

        $scope.init = function () {
            $scope.fillFormat($scope.m);
            $scope.disableView($scope.m.disableAll);
        };

        $timeout(function () {
            $scope.init();
        }, 0);

    }
)
;