app.controller('hearingFormatController', function ($scope, $timeout, $http) {

        $scope.m = {};
        $scope.hasError;
        $scope.m.arrmntType;

        $scope.m.hasSearch = false;

        $scope.m.hasHF = false;
        $scope.m.canSave = true;
//        $scope.m.errArrmntSel;
        $scope.lstArrangeShow = [];

        $scope.validateSave = function () {

            $scope.hasError = false;

            $scope.validateInitEnd();
            $scope.validateBthDay();

            $scope.validateHType();
            $scope.validateExtension();
            $scope.validateCrtlDet();
            $scope.validateFormImp();
            $scope.validateLinkProc();
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
                $scope.m.errTime = "La hora de termino no puede ser menor/igual a la hora de inicio";
                return;
            }
            else
                $scope.m.errTime = "";

            if ($scope.m.initTime == "" || $scope.m.initTime == null) {
                $scope.hasError = true;
                $scope.m.errTimeIn = "Hora de inicio es un campo requerido";
                return;
            }
            else
                $scope.m.errTimeIn = "";


            if ($scope.m.endTime == "" || $scope.m.endTime == null) {
                $scope.hasError = true;
                $scope.m.errTime = "Hora de termino es un campo requerido";
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

            if ($scope.m.canSave != false) {
                $scope.m.errFormImp = "";
                $scope.m.impDate = "";
                $scope.m.errFormImpDate = "";
            }
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

            if ($scope.m.canSave != false) {
                $scope.m.errLinkProc = "";
                $scope.m.linkageRoom = "";
                $scope.m.errLnkRoom = "";
                $scope.m.linkageDate = "";
                $scope.m.errLnkDt = "";
                $scope.m.linkageTime = "";
                $scope.m.errLnkTm = "";

            }
        };

        $scope.searchCase = function (idFolder) {
            var currentTimeout = null;

            var ajaxConf = {
                method: 'POST',
                url: '/supervisor/searchCase.json'
            };

            ajaxConf.data = idFolder;

            if (currentTimeout) {
                $timeout.cancel(currentTimeout);
            }

            currentTimeout = $timeout(function () {
                $http(ajaxConf)
                    .success(function (data) {

                        $scope.fillFormat(data);
                        $scope.searchArrangements(data.idFolderCode);
                        $scope.m.hasSearch = true;

                    });
            }, 200);
        };

        $scope.disField = function (val) {

            if (val)
                $("#FormFormatId :input").attr("disabled", true);
            else
                $("#FormFormatId :input").attr("disabled", false);

        }

        $scope.clAllForm = function () {

            $scope.m = {};
            //Removes validation from input-fields
            $('.input-validation-error').addClass('input-validation-valid');

            $('.input-validation-error').removeClass('input-validation-error');
            //Removes validation message after input-fields
            $('.field-validation-error').addClass('field-validation-valid');
            $('.field-validation-error').removeClass('field-validation-error');
            //Removes validation summary
            $('.validation-summary-errors').addClass('validation-summary-valid');
            $('.validation-summary-errors').removeClass('validation-summary-errors');
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

            $scope.clAllForm();

            $scope.m.idFolderParam = data.idFolderCode;
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

            $scope.m.impBthDay = $scope.myFormatDate(data.imputedBirthDate);

            $scope.calcAge();
            $scope.m.imputedTel = data.imputedTel;
            $scope.m.crimes = data.crimes;
            $scope.m.addtionalData = data.additionalData;
            $scope.m.ctrlDet = data.controlDetention;
            $scope.m.ext = data.extension;
            $scope.m.hType = data.hearingType;
            $scope.m.impDate = $scope.myFormatDate(data.imputationDate);
            $scope.m.linkageRoom = data.linkageRoom;
            $scope.m.linkageDate = $scope.myFormatDate(data.linkageDate);


            $scope.m.linkageTime = data.linkageTime;
            $scope.m.canSave = data.canSave;
            $scope.m.hasHF = data.hasHF;
            $scope.m.terms = data.terms;

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
                        $scope.searchCase(resp.message);
                    }
                })
                .error(function () {
                    $scope.WaitFor = false;
                    $scope.MsgError = "Error de red. Por favor intente más tarde.";
                    $scope.$apply();
                });

            return true;
        };


        $scope.searchArrangements = function (idFolder) {

            var currentTimeout = null;

            var ajaxConf = {
                method: 'POST',
                url: '/supervisor/searchArrangements.json'
            };

            ajaxConf.data = $scope.m.idFolderParam;

            if (currentTimeout) {
                $timeout.cancel(currentTimeout);
            }

            currentTimeout = $timeout(function () {
                $http(ajaxConf)
                    .success(function (data) {
                        $scope.m.lstArrangementShow = data;
                        $scope.disField($scope.m.hasHF);
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

            var hasSel = false;
            var hasDesc = false;

            if ($scope.m.hasHF == false) {
                for (var i=0; i < $scope.m.lstArrangementShow.length ; i++) {

                    if ($scope.m.lstArrangementShow[i].selVal == true) {
                        hasSel = true;
                        if ($scope.m.lstArrangementShow[i].description != ""&&$scope.m.lstArrangementShow[i].description != undefined) {
                            hasDesc = true;
                        }
                        break;
                    }
                }

                if (hasSel == false) {
                    $scope.m.hasError = true;
                    $scope.m.errArrmntSel = "Debe seleccionar al menos una medida cautelar";
                    return;
                }else
                if (hasDesc == false) {
                    $scope.m.hasError = true;
                    $scope.m.errArrmntSel = "Debe indicar la descripción de cada medida cautelar seleccionada";
                    return;
                }else
                    $scope.m.errArrmntSel="";

            }
        };

        $scope.init = function () {
            $scope.m.errTime = "";
            $scope.disField(true);
            $("#idFolderParam").attr("disabled", false);
            $("#btnSearch").attr("disabled", false);
        };

        $timeout(function () {
            $scope.init();
        }, 0);

    }
)
;