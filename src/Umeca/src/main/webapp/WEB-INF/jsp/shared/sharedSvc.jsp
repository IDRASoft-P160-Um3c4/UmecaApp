
<script>
    app.service('sharedSvc', function ($timeout, $q) {

        //Dialogo para la espera de algun evento
        var dlgProcessing = $('#ProcessingDlgId');
        var th = this;
        this.cfgProc = {toProcessing: undefined, procCount: 0};

        this.onProcTimeOut = function () {
            th.cfgProc.procCount++;
            if (th.cfgProc.procCount > 100)
                th.cfgProc.procCount = 0;
            th.cfgProc.toProcessing = $timeout(th.onProcTimeOut, 150);
        };

        this.showProcessing = function () {
            dlgProcessing.modal('show');
            th.cfgProc.procCount = 1;
            th.cfgProc.toProcessing = $timeout(th.onProcTimeOut, 400);
        };

        this.hideProcessing = function () {
            $timeout.cancel(th.cfgProc.toProcessing);
            dlgProcessing.modal('hide');
        };

        //Dialogo para mensajes con acciones de exito, informacion, advertencia o error
        var dlgMsgBox = $('#MessageBoxDlgId');

        this.cfgMsg = {title: '', message: '', type: ''};
        this.respMsg = {};
        this.respData = {};

        this.showDlg = function (cfg) {
            th.cfgMsg = cfg;
            var def = $q.defer();

            $timeout(function () {
                dlgMsgBox.modal('show');
                dlgMsgBox.on('hidden.bs.modal', function () {
                    if (th.respMsg.IsOk === true) {
                        def.resolve(th.respMsg);
                    }
                    else {
                        def.reject();
                    }
                });
            }, 1);

            return def.promise;
        };

        this.showMsg = function (cfg) {
            dlgMsgBox = $('#MessageBoxDlgId');
            return th.showDlg(cfg);
        };


        this.showConf = function (cfg) {
            dlgMsgBox = $('#ConfirmationDlgId');
            return th.showDlg(cfg);
        };

        this.hideMsg = function (rMsg, data) {
            th.respMsg = rMsg;
            th.respData = data;
            dlgMsgBox.modal('hide');
        };

        this.showConfPass = function (cfg) {
            dlgMsgBox = $('#ConfirmationPassDlgId');
            return th.showDlg(cfg);
        };

        this.doPost = function (data, urlToGo, redirect) {
            var def = $q.defer();
            var settings = {
                dataType: "json",
                type: "POST",
                url: urlToGo,
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (resp) {
                    if (resp.hasError === undefined) {
                        resp = resp.responseMessage;
                    }
                    if (resp.hasError === true) {
                        th.showMsg(
                                {
                                    title: resp.title,
                                    message: resp.message,
                                    type: "danger"
                                }).then(function () {
                                    def.reject({isError: true});
                                });
                    }
                    else {
                        def.resolve({msg: resp.message});
                        if (redirect != undefined && redirect == true) {
                            window.cancelMeetingSource();
                        }
                    }
                },
                error: function () {
                    th.showMsg(
                            {
                                title: "Error de red",
                                message: "<strong>No fue posible conectarse al servidor</strong> <br/><br/>Por favor intente m&aacute;s tarde",
                                type: "danger"
                            }).then(function () {
                                def.reject({isError: true});
                            });
                }
            };
            $.ajax(settings);
            return def.promise;
        };
    });


    app.controller('processingController', function ($scope, sharedSvc) {
        $scope.sharedSvc = sharedSvc;
        $scope.count = 0;

        $scope.$watch('sharedSvc.cfgProc.procCount', function (count) {
            $scope.count = count;
        });
    });

    app.controller('messageController', function ($scope, $sce, sharedSvc, $compile) {
        $scope.sharedSvc = sharedSvc;

        $scope.$watch('sharedSvc.cfgMsg', function (cfg) {
            $scope.Title = window.returnTrustHtml($sce,cfg.title);
            $scope.Message = window.returnTrustHtml($sce,cfg.message);
            $scope.Type = cfg.type;
        });

        $scope.ok = function () {
            $scope.IsOk = true;
            sharedSvc.hideMsg($scope);
        };

        $scope.alert = function (title, message, type) {
            $scope.Title = window.returnTrustHtml($sce,title);
            $scope.Message = window.returnTrustHtml($sce,message);
            $scope.Type = type;
        };


    });

    app.controller('confirmationController', function ($scope, $sce, sharedSvc) {
        $scope.sharedSvc = sharedSvc;
        $scope.m = {};

        $scope.setParametersModal = function (msg, type, title) {
            $scope.Title = window.returnTrustHtml($sce,title);
            $scope.Message = window.returnTrustHtml($sce,msg);
            $scope.Type = type;
        }

        $scope.$watch('sharedSvc.cfgMsg', function (cfg) {
            $scope.Title = window.returnTrustHtml($sce,cfg.title);
            $scope.Message = window.returnTrustHtml($sce,cfg.message);
            $scope.Type = cfg.type;

            try {
                if (cfg.choiceA === undefined || cfg.choiceA.lstItems === undefined || cfg.choiceA.lstItems.length === 0) {
                    $scope.LstChoiceA = [];
                    $scope.TitleChoiceA = window.returnTrustHtml($sce,"");
                }
                else {
                    $scope.TitleChoiceA = window.returnTrustHtml($sce,cfg.choiceA.title);
                    $scope.LstChoiceA = cfg.choiceA.lstItems;
                    $scope.m.choiceA = $scope.LstChoiceA[0];
                }
            } catch (e) {
                $scope.LstChoiceA = [];
            }
        });

        $scope.yes = function () {
            $scope.IsOk = true;
            sharedSvc.hideMsg($scope, $scope.m);
        };

        $scope.no = function () {
            $scope.IsOk = false;
            sharedSvc.hideMsg($scope);
        };

    });

    app.controller('confirmationPassController', function ($scope, $sce, sharedSvc) {
        $scope.sharedSvc = sharedSvc;
        $scope.m = {};

        $scope.$watch('sharedSvc.cfgMsg', function (cfg) {
            $scope.Title = window.returnTrustHtml($sce,cfg.title);
            $scope.Message = window.returnTrustHtml($sce,cfg.message);
            $scope.AgreeCheck = window.returnTrustHtml($sce,cfg.agreeCheck);
            $scope.Type = cfg.type;
            $scope.m.isAgree = false;
            $scope.m.password = "";
        });

        $scope.yes = function () {
            $scope.IsOk = true;
            sharedSvc.hideMsg($scope);
        };

        $scope.no = function () {
            $scope.IsOk = false;
            sharedSvc.hideMsg($scope);
        };

    });

</script>

<div id="ProcessingDlgId" class="modal fade" ng-controller="processingController" data-backdrop="static"
     data-keyboard="false" ng-cloak>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="alert alert-info"><h4>Procesando...</h4></div>
                <br/>
                <progressbar class="progress-striped active" value="count" type="info"></progressbar>
            </div>
        </div>
    </div>
</div>


<div id="MessageBoxDlgId" class="modal fade" ng-controller="messageController" data-backdrop="static" ng-cloak>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div class="alert alert-{{Type=='primary'?'info':Type}}">
                    <button type="button" class="close" ng-click="ok()">&times;</button>
                    <h4 class="modal-title element-center" ng-bind-html="Title"></h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="element-center" ng-bind-html="Message"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-{{Type}}" ng-click="ok()">Aceptar</button>
            </div>
        </div>
    </div>
</div>


<div id="ConfirmationDlgId" class="modal fade" ng-controller="confirmationController" data-backdrop="static" ng-cloak>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div class="alert alert-{{Type=='primary'?'info':Type}}">
                    <button type="button" class="close" ng-click="no()">&times;</button>
                    <h4 class="modal-title element-center" ng-bind-html="Title"></h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="element-center" ng-bind-html="Message"></div>
                <div ng-show="LstChoiceA !== undefined && LstChoiceA.length > 0">
                    <br/>
                    <br/>

                    <div class="row">
                        <div class="col-xs-6 col-xs-offset-3">
                            <div class="row">
                                <div class="col-xs-12 element-center">
                                    <span ng-bind-html="TitleChoiceA"></span>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-xs-12">
                                    <select class="form-control element-center"
                                            ng-model="m.choiceA"
                                            ng-options="e.name for e in LstChoiceA">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-{{Type}}" ng-click="yes()">Si</button>
                <button type="button" class="btn btn-default" ng-click="no()">No</button>
            </div>
        </div>
    </div>
</div>

<div id="ConfirmationPassDlgId" class="modal fade" ng-controller="confirmationPassController" data-backdrop="static"
     ng-cloak>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <div class="alert alert-{{Type=='primary'?'info':Type}}">
                    <button type="button" class="close" ng-click="no()">&times;</button>
                    <h4 class="modal-title element-center">
                        <div class="element-center" ng-bind-html="Title"></div>
                    </h4>
                </div>
            </div>
            <div class="modal-body">
                <div class="element-center" ng-bind-html="Message"></div>
            </div>
            <br/>

            <div class="hr hr-2"></div>
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2">
                    <div class="checkbox element-left">
                        <input name="form-field-checkbox" type="checkbox" ng-model="m.isAgree"
                               ng-init="m.isAgree=false;"/>
                        <span class="lbl" ng-bind-html="AgreeCheck"></span>
                    </div>
                </div>
            </div>
            <div class="hr hr-2"></div>
            <div class="row" ng-show="m.isAgree">
                <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                    <div class="widget-box">
                        <div class="widget-header widget-header-small header-color-dark">
                            <h6>Ingrese su contrase&ntilde;a para validar su usuario</h6>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main padding-12">
                                <input id="password" type="password" name="password"
                                       ng-model="m.password" class="form-control"
                                       rows="8">
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-{{Type}}" ng-show="m.isAgree && m.password"
                        ng-click="yes()">Continuar
                </button>
                <button type="button" class="btn btn-default" ng-click="no()">Cancelar</button>
            </div>
        </div>
    </div>
</div>


<div id="dlgUpsert"></div>
