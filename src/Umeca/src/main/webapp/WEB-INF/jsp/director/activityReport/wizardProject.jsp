<script>
    $(document).ready(function () {
        jQuery("#GridProjectId").jqGrid({
            url: '<c:url value='/director/activityReport/listProject.json' />',
            autoencode:true,
            datatype: "json",
            mtype: 'POST',
            postData: {
                startDate: '',
                endDate: ''
            },            colNames: ['ID', 'Agregar', 'Proyecto','Actividad','Descripci&oacute;n', 'Fecha', 'Estatus'],
            colModel: [
                { name: 'id', index: 'id', hidden: true },
                { name: 'Action', width: 110, align: "center", sortable: false, search: false,formatter:window.actionFormatter},
                { name: 'name', index: 'name', width: 200, align: "center", sorttype: 'string', search: false },
                { name: 'activityName', index: 'activityName', width: 250, align: "center", sorttype: 'string', search: false },
                { name: 'activityDescription', index: 'activityDescription', width: 400, align: "center", sorttype: 'string', search: false },
                { name: 'stCreationDate', index: 'stCreationDate', width: 150, align: "center", sorttype: 'string', search: false },
                { name: 'status', index: 'status', width: 120, align: "center", sorttype: 'string', search: false }
            ],
            rowNum: 100,
            rowList: [100, 200, 500, 1000],
            pager: '#GridPagerProject',
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

                $("#idWizardProject").injector().invoke(function ($compile, $rootScope) {
                    $compile($("#idWizardProject"))($rootScope);
                    $rootScope.$apply();
                });

            }
        });

        jQuery("#GridProjectId").jqGrid('navGrid', '#GridPagerProject', {
            edit: false,
            add: false,
            refresh: false,
            del: false,
            search: false});

        jQuery("#GridProjectId").jqGrid('filterToolbar', {
            stringResult: true,
            searchOperators: true,
            searchOnEnter: true,
            multipleSearch: true,
            ignoreCase: true
        });
    });

</script>

<div class="row" ng-controller="wizardProjectController" id="idWizardProject" >
    <div class="col-xs-12">
        <div class="widget-box transparent">
            <div class="widget-header">
                <h4 class="widget-title">Proyectos</h4>
            </div>

            <div class="widget-body">
                <div class="widget-main padding-6 no-padding-left no-padding-right">
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-2">
                            <button type="button" class="btn btn-success" ng-click="next('management')">
                                <i class="glyphicon glyphicon-backward"></i>&nbsp;&nbsp;Regresar</button>
                        </div>
                        <div class="col-xs-4 element-right">
                            <button type="button" class="btn btn-success" ng-click="next('organizations')">
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
                                <button type="button" class="btn btn-primary" ng-click="search('#GridProjectId')">
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
                    <div id="angJsjqGridProjectId" ng-controller="modalDlgController">
                        <table id="GridProjectId" class="element-center" style="margin: auto"></table>
                        <div id="GridPagerProject"></div>
                        <div class="blocker" ng-show="working">
                            <div>
                                Procesando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                            </div>
                        </div>
                    </div>
                    <div class="space-10"></div>
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-2">
                            <button type="button" class="btn btn-success" ng-click="next('management')">
                                <i class="glyphicon glyphicon-backward"></i>&nbsp;&nbsp;Regresar</button>
                        </div>
                        <div class="col-xs-4 element-right">
                            <button type="button" class="btn btn-success" ng-click="next('organizations')">
                                Siguiente&nbsp;&nbsp;<i class="glyphicon glyphicon-forward"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


