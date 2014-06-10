app.controller('upsertActivityEventController', function ($scope, $timeout, $q) {
    $scope.Title = "";
    var th = this;
    var dlgMsgBox = $('#UpsertActivityEventDlgId');


    $scope.hideMsg = function (rMsg) {
        th.respMsg = rMsg;
        dlgMsgBox.modal('hide');
    };

    $scope.showDlg = function (cfg) {
        $scope.Title = cfg.title;

        var def = $q.defer();

        $timeout(function () {
            dlgMsgBox.modal('show');
            dlgMsgBox.on('hidden.bs.modal', function () {
                if (th.respMsg.IsOk === true) {
                    def.resolve();
                }
                else {
                    def.reject();
                }
            });
        }, 1);

        return def.promise;
    };

    $scope.save = function () {
        $scope.IsOk = true;
        $scope.hideMsg($scope);
    };


    $scope.cancel = function () {
        $scope.IsOk = false;
        $scope.hideMsg($scope);
    };
});