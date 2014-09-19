app.controller('framingMeetingController', function ($scope, $timeout, $http, $rootScope, $sce) {

        $scope.fm = {}

        $scope.FMsuccessMsg = "";
        $scope.FMerrorMsg = "";

        $scope.FMerrorMsgLst = "";
        $scope.listMsgError ={};

        $scope.disableView = function () {

            if ($scope.fm.objView.canTerminate == false) {
                $("#divFM :input").attr("disabled", true);
            }
            else {
                $("#divFM :input").attr("disabled", false);
            }

        };

        $scope.changeZIndex = function(elementClick){
            $("#liPersonalData").css("z-index","0");
            $("#liImputedHome").css("z-index","0");
            $("#liReference").css("z-index","0");
            $("#liSocialNetwork").css("z-index","0");
            $("#liJob").css("z-index","0");
            $("#liSchool").css("z-index","0");
            $("#liFinger").css("z-index","0");
            $("#liActivities").css("z-index","0");
            $("#liAnalysis").css("z-index","0");
            $("#liQuestion").css("z-index","0");
            $("#liDrug").css("z-index","0");
            $("#liLeaveCountry").css("z-index","0");
            $("#"+elementClick).css("z-index","1");

        };

        $scope.returnFM = function () {

            if ($scope.returnId && $scope.returnId != null && $scope.returnId != undefined && $scope.returnId > 0) {
                window.goToUrlMvcUrl($scope.urlManagerSup);
            }
            else
                window.goToUrlMvcUrl($scope.urlIndex);
        };

        $scope.init = function () {
            $scope.disableView();
        };

        $timeout(function () {
            $scope.init();
        }, 0);


        $scope.resizeMap = function () {
            $timeout(function () {
                $rootScope.$broadcast("resizeMap");
            }, 10);

        };

        $scope.doTerminate = function () {
            var currentTimeout = null;
            var url = "doTerminate.json";
            var idCase = $scope.fm.objView.idCase;
            var ajaxConf;

            ajaxConf = {
                method: "GET",
                params: {idCase: idCase}
            };

            ajaxConf.url = url;

            if (currentTimeout) {
                $timeout.cancel(currentTimeout);
            }

            currentTimeout = $timeout(function () {
                $http(ajaxConf)
                    .success(function (data) {

                        if(data.hasError==undefined){
                           data = data.responseMesage;
                        }

                        if (data.hasError == true) {
                            var obj = JSON.parse(data.message);
                            if(obj.groupMessage != undefined){
                                for(var i=0; i < obj.groupMessage.length; i++){
                                    var g1= obj.groupMessage[i];
                                    $scope.listMsgError[g1.section]= $sce.trustAsHtml( g1.messages);
                                }
                            }
                        }
                        else
                            $scope.returnFM();
                    });
            }, 200);
        };


    }
)
;