app.controller('detentionCarouselController', function ($scope, $rootScope, $interval, $http, $timeout) {

    $scope.init = function () {
        $scope.updateDetainedLid();
    };

    $scope.calcTerm = function (item, list, index) {

        var _48hrsMil = 172800000;
        var _1hourMil = 3600000;
        var _1minMil = 60000;

        var OutOfTimeMil = $scope.m.OutOfTimePeriod * _1hourMil;

        var totDue = "";
        var totLeft = "";
        if (item.isProsecute === false) {
            var milNow = parseInt(item.nowL, 10);
            var milDate = parseInt(item.initDateL, 10);
            var milTime = parseInt(item.initTimeL, 10);

            var dt = new Date(milTime);
            var dtA = new Date(milDate);

            var lowDt = new Date(dtA.getFullYear(), dtA.getMonth(), dtA.getDate(), dt.getHours(), dt.getMinutes(), dt.getSeconds());
            var lowL = lowDt.getTime();
            var upL  = lowL + _48hrsMil;

            var stillVisible = upL + OutOfTimeMil;

            if (milNow <= upL) {

                var dueMil = milNow - lowL;
                var hrsDue = Math.floor(dueMil / _1hourMil);
                var minDue = dueMil % _1hourMil;
                minDue = Math.floor(minDue / _1minMil);

                var leftMil = upL - milNow;
                var hrsLeft = Math.floor(leftMil / _1hourMil);
                var minLeft = leftMil % _1hourMil;
                minLeft = Math.floor(minLeft / _1minMil);

                if (hrsDue > -1 && minDue > -1)
                    totDue = hrsDue + " hrs. " + minDue + " min.";
                if (hrsLeft > -1 && minLeft > -1)
                    totLeft = hrsLeft + " hrs. " + minLeft + " min.";
            } else if (milNow > stillVisible) {
                list.splice(index, 1);
                return;
            }
            else {
                totDue = "El plazo ha vencido.";
                totLeft = "El plazo ha vencido.";
                item.class = "outOfDate";
            }
        }
        else {
            totDue = "Judicializado.";
            totLeft = "Judicializado.";
        }

        item.dueTime = totDue;
        item.timeLeft = totLeft;


    };

    $scope.updateDetainedLid = function(){
        var url = "detainedCarousel/getDetainedList.json";
        $http.get(url).then(function (response) {
            var temp = response.data;
            for (var index = temp.length - 1; index >= 0; --index) {
                $scope.calcTerm(temp[index], temp, index)
            };
            $scope.items = temp;
        });
        $scope.cropList();
    };

    $scope.beginVerticalScroll = function(){
        var first = undefined;
        var newElement = undefined;
        var currentItem = undefined;

        $interval(

            function(){

                if($scope.items.length !== 0){

                    if(first !== undefined){
                        newElement = $scope.items[0];
                    }

                    if(first === undefined){
                        first = $scope.items[0];
                    }

                    if(first === undefined && newElement === undefined || (newElement !== undefined && newElement.id === first.id)){
                        $( "ul li" ).removeClass("repeat-item");
                        first.class = "";
                        $scope.updateDetainedLid();
                        first = undefined;
                        newElement = undefined;
                        $timeout(function() {
                            $( "ul li" ).addClass("repeat-item");
                        }, 1000);
                        return;
                    }

                    currentItem = $scope.items.shift();
                    $scope.addItem(currentItem);
                }else{
                    $scope.updateDetainedLid();
                }
            },
            //3000
            $scope.m.CarouselRollTime * 1000
        );
    };

    $scope.addItem = function (item) {
        $interval(function(){
            $scope.cropList();
            $scope.items.push(item);
        }, 1250,1);
    };

    $scope.cropList = function (item){
        $timeout(function() {

            var listItems = $('ul li');
            var listContainer = $('ul');
            listItems.show();

            var listHeight = 0;

            for(var i = 0; i < listItems.length; i++) {
                listHeight += listItems[i].scrollHeight + 1;

                //[0] to access the DOM Element
                if(listContainer[0].offsetHeight < listHeight) {
                    $(listItems[i]).hide();
                }
            }
        }, 100);
    }

}).directive('drctv', ["$interval",function($interval){
    return {

        link: function ($scope) {
            $scope.beginVerticalScroll();
        }
    };

}]);