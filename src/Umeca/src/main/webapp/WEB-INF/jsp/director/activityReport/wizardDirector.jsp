<script>
    $(document).ready(function () {
        jQuery("#GridDirectorId").jqGrid({
            url: '<c:url value='/director/activityReport/listDirector.json' />',
            autoencode:true,
            datatype: "json",
            mtype: 'POST',
            postData: {
                startDate: '',
                endDate: ''
            },            colNames: ['ID', 'Agregar', 'Informe','Descripci&oacute;n', 'Fecha', 'Estatus'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'Action', width: 110, align: "center", sortable: false, search: false,formatter:window.actionFormatter},
                { name: 'reportName', index: 'reportName', width: 350, align: "center", sorttype: 'string', search: false },
                { name: 'description', index: 'description', width: 450, align: "center", sorttype: 'string', search: false },
                { name: 'stCreationDate', index: 'stCreationDate', width: 180, align: "center", sorttype: 'string', search: false },
                { name: 'status', index: 'status', width: 120, align: "center", sorttype: 'string', search: false }
            ],
            rowNum: 100,
            rowList: [100, 200, 500, 1000],
            pager: '#GridPagerDirector',
            sortname: 'id',
            height: 350,
            viewrecords: true,
            shrinkToFit: false,
            sortorder: "desc",
            caption: "&nbsp;",
            altRows: true,
            gridComplete: function () {
                var ids = $(this).jqGrid('getDataIDs');
                for (var i = 0; i < ids.length; i++) {
                    var cl = ids[i];
                    var row = $(this).getRowData(cl);
                    var status = parseInt(row.status);
                    var be = "";

                    be += "<input type='checkbox' ng-model='m.lstAct.c"+ cl +"' ng-init='m.lstAct.c"+ cl +" = false;' ng-change='change(m.lstAct.c"+ cl +", " + cl + ")' value='"+cl+"'/>  ";

                    $(this).jqGrid('setRowData', ids[i], { Action: be });
                }
            },
            loadComplete: function () {
                var table = this;
                setTimeout(function () {
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);

                $("#idWizardDirector").injector().invoke(function ($compile, $rootScope) {
                    $compile($("#idWizardDirector"))($rootScope);
                    $rootScope.$apply();
                });

            }
        });

        jQuery("#GridDirectorId").jqGrid('navGrid', '#GridPagerDirector', {
            edit: false,
            add: false,
            refresh: false,
            del: false,
            search: false});

        jQuery("#GridDirectorId").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    });

</script>

<div class="row" ng-controller="wizardDirectorController" id="idWizardDirector" >
    <div class="col-xs-12">
        <div class="widget-box transparent">
            <div class="widget-header">
                <h4 class="widget-title">Actividades del director para agregar al informe</h4>
            </div>

            <div class="widget-body">
                <div class="widget-main padding-6 no-padding-left no-padding-right">
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-2">
                            <button type="button" class="btn btn-success" ng-click="next('evaluation')">
                                <i class="glyphicon glyphicon-backward"></i>&nbsp;&nbsp;Regresar</button>
                        </div>
                        <div class="col-xs-4 element-right">
                            <button type="button" class="btn btn-success" ng-click="next('project')">
                                Siguiente&nbsp;&nbsp;<i class="glyphicon glyphicon-forward"></i></button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1 element-center">
                            <h4>Seleccione rango de fechas</h4>
                        </div>
                    </div>
                    <div class="space-10"></div>
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <div class="col-xs-4 col-xs-offset-1">
                                <div class="row">
                                    <div class="col-xs-4 element-right">
                                        <label for="date-pk-act-start" >Fecha inicial:</label>
                                    </div>
                                    <div class="col-xs-8">
                                        <div class="input-group">
                                            <input class="form-control date-picker" id="date-pk-act-start" readonly="readonly"
                                                   type="text" data-date-format="dd-mm-yyyy" ng-model="m.startDate"  ng-init="m.startDate = '${startDate}'"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-4 element-center">
                                <div class="row">
                                    <div class="col-xs-4 element-right">
                                        <label for="date-pk-act-end" >Fecha final:</label>
                                    </div>
                                    <div class="col-xs-8">
                                        <div class="input-group">
                                            <input class="form-control date-picker" id="date-pk-act-end" readonly="readonly"
                                                   type="text" data-date-format="dd-mm-yyyy" ng-model="m.endDate"  ng-init="m.endDate = '${endDate}'"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-3 element-center">
                                <button type="button" class="btn btn-primary" ng-click="search('#GridDirectorId')">
                                    <i class="glyphicon glyphicon-search"></i>&nbsp;Buscar</button>
                            </div>
                        </div>
                    </div>
                    <div class="space-10"></div>
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-2">
                            <div class="checkbox i-checks col-xs-8">
                                <label>
                                    <input type="checkbox" ng-model="m.selectAll" ng-change="selectAll()" > Agregar todos
                                </label>
                            </div>
                        </div>
                    </div>
                    <div id="angJsjqGridDirectorId" ng-controller="modalDlgController">
                        <table id="GridDirectorId" class="element-center" style="margin: auto"></table>
                        <div id="GridPagerDirector"></div>
                        <div class="blocker" ng-show="working">
                            <div>
                                Procesando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                            </div>
                        </div>
                    </div>
                    <div class="space-10"></div>
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-2">
                            <button type="button" class="btn btn-success" ng-click="next('evaluation')">
                                <i class="glyphicon glyphicon-backward"></i>&nbsp;&nbsp;Regresar</button>
                        </div>
                        <div class="col-xs-4 element-right">
                            <button type="button" class="btn btn-success" ng-click="next('project')">
                                Siguiente&nbsp;&nbsp;<i class="glyphicon glyphicon-forward"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


