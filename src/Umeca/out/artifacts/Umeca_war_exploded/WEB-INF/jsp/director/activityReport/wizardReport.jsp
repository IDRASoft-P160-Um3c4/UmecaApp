<div class="row" ng-controller="wizardReportController">
    <div class="col-xs-12">
        <div class="widget-box transparent">
            <div class="widget-header">
                <h4 class="widget-title">Datos del informe</h4>
            </div>

            <div class="widget-body">
                <div class="widget-main padding-6 no-padding-left no-padding-right">
                    <div class="row">
                        <div class="space-10">&nbsp;</div>
                        <div class="col-xs-8">
                            <div class="col-xs-4 element-right">
                                <label for="date-pk-act-start">Fecha del informe:</label>
                                <br/>
                                <small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/01)</small>
                            </div>
                            <div class="col-xs-4">
                                <div class="input-group">
                                    <input class="form-control date-picker" id="date-pk-act-start"
                                           type="text" data-date-format="dd-mm-yyyy" ng-model="m.startDate" ng-change="change()"
                                           ng-init="m.startDate = '${todayDate}'"/>
                                <span class="input-group-addon">
                                    <i class="icon-calendar bigger-110"></i>
                                </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-2 element-right">
                            <button type="button" class="btn btn-success" ng-click="next('activity')">
                                Siguiente&nbsp;&nbsp;<i class="glyphicon glyphicon-forward"></i></button>
                        </div>
                    </div>
                    <div class="space-10"></div>
                    <div class="row">
                        <div class="col-xs-8">
                            <div class="col-xs-4 element-right">
                                <label>Nombre del informe:</label>
                            </div>
                            <div class="col-xs-4">
                                <input class="form-control" type="text" ng-model="m.reportName" ng-change="change()"/>
                            </div>
                        </div>
                    </div>
                    <div class="space-10"></div>
                    <div class="row">
                        <div class="col-xs-8">
                            <div class="col-xs-4 element-right">
                                <label>Descripci&oacute;n del informe:</label>
                            </div>
                            <div class="col-xs-4">
                                <textarea class="form-control" cols="20" rows="5" type="text" ng-model="m.reportDesc" ng-change="change()"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="space-10"></div>
                </div>
            </div>
        </div>
    </div>
</div>


